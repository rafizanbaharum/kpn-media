package my.gov.kpn.media.core.dao.impl;

import my.gov.kpn.media.core.dao.KpnGroupDao;
import my.gov.kpn.media.core.model.*;
import my.gov.kpn.media.core.model.impl.KpnGroupImpl;
import my.gov.kpn.media.core.model.impl.KpnGroupMemberImpl;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
@Repository("groupDao")
public class KpnGroupDaoImpl extends DaoSupport<Long, KpnGroup, KpnGroupImpl> implements KpnGroupDao {

    private static final Logger log = Logger.getLogger(KpnGroupDaoImpl.class);

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public KpnGroup findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (KpnGroup) session.get(KpnGroupImpl.class, id);
    }

    @Override
    public List<KpnGroup> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from KpnGroup g order by g.name");
        return (List<KpnGroup>) query.list();
    }

    @Override
    public List<String> findAllGroupName() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g.name from KpnGroup g");
        return (List<String>) query.list();
    }

    @Override
    public KpnGroup findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from KpnGroup g where g.name = :name");
        query.setString("name", name);
        return (KpnGroup) query.uniqueResult();
    }

    @Override
    public List<KpnUser> findMembers(KpnGroup group) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.member from KpnGroupMember gm where " +
                "gm.group = :group " +
                "order by gm.member.name");
        query.setEntity("group", group);
        return (List<KpnUser>) query.list();
    }

    /**
     * XXX: ClassCastException issues
     * XXX: select gm.member wil get you abstract KpnPrincipal
     * XXX: not extension classes
     *
     * @param group
     * @param type
     * @return
     */
    @Override
    public List<KpnUser> findMembers(KpnGroup group, KpnPrincipalType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;

        String selectGroup = "select g from KpnGroup g where " +
                "id in (select gm.member.id from KpnGroupMember gm where " +
                "gm.group = :group " +
                "and gm.member.principalType = :type )" +
                "order by g.name";
        String selectUser = "select u from KpnUser u where " +
                "id in (select gm.member.id from KpnGroupMember gm where " +
                "gm.group = :group " +
                "and gm.member.principalType = :type )" +
                "order by u.name";
        switch (type) {
            case USER:
                query = session.createQuery(selectUser);
                break;
            case GROUP:
                query = session.createQuery(selectGroup);
                break;
        }
        query.setEntity("group", group);
        query.setInteger("type", type.ordinal());
        return (List<KpnUser>) query.list();
    }

    @Override
    public List<KpnGroup> findMemberships(KpnUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.group from KpnGroupMember gm inner join gm.member where " +
                "gm.member = :user");
        query.setEntity("user", user);
        return (List<KpnGroup>) query.list();
    }

    @Override
    public List<KpnUser> findMembers(KpnGroup group, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.member from KpnGroupMember gm where " +
                "gm.group = :group " +
                "order by gm.member.name");
        query.setEntity("group", group);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<KpnUser>) query.list();
    }

    @Override
    public List<KpnGroup> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from KpnGroup g order by g.name");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<KpnGroup>) query.list();
    }


    @Override
    public List<KpnGroup> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct g from KpnGroup g where " +
                "g.name like upper(:filter) " +
                "and g.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<KpnGroup>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from KpnGroup g where " +
                "g.metadata.state = :state");
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from KpnGroup g where " +
                "g.name like upper(:filter) " +
                "and g.metadata.state = :state");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isMemberOf(KpnGroup group, KpnUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from KpnGroupMember g where " +
                "g.group = :group " +
                "and g.member = :principal");
        query.setEntity("group", group);
        query.setEntity("principal", user);
        return ((Long) query.uniqueResult()).intValue() >= 1;
    }

    @Override
    public KpnGroupMember findGroupMember(KpnGroup group, KpnUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from KpnGroupMember g where " +
                "g.group = :group " +
                "and g.member = :user");
        query.setEntity("group", group);
        query.setEntity("user", user);
        return (KpnGroupMember) query.uniqueResult();
    }

// =============================================================================
    // CRUD METHODS
    // =============================================================================

    @Override
    public void addMember(KpnGroup group, KpnUser member, KpnUser user) {
        Validate.notNull(group, "Group should not be null");
        Validate.notNull(member, "Group member should not be null");

        // session
        Session session = sessionFactory.getCurrentSession();
        KpnGroupMember groupMember = new KpnGroupMemberImpl();
        groupMember.setGroup(group);
        groupMember.setMember(member);

        // prepare metadata
        KpnMetadata metadata = new KpnMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        groupMember.setMetadata(metadata);
        session.save(groupMember);
    }

    @Override
    public void addMembers(KpnGroup group, List<KpnUser> users, KpnUser user) {
        List<KpnUser> principalGroups = findMembers(group);
        List<KpnUser> newPrincipals = new ArrayList<KpnUser>();

        for (KpnUser principal : users) {
            newPrincipals.add(principal);
        }

        for (KpnUser principalGroup : principalGroups) {
            if (!newPrincipals.contains(principalGroup)) {
                removeMember(group, principalGroup);
            }
        }

        for (KpnUser newGroup : newPrincipals) {
            if (!principalGroups.contains(newGroup)) {
                addMember(group, newGroup, user);
            }
        }
    }

    @Override
    public void removeMember(KpnGroup group, KpnUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from KpnGroupMember g where g.group = :group and g.member = :principal");
        query.setEntity("group", group);
        query.setEntity("user", user);
        KpnGroupMember groupMember = (KpnGroupMember) query.uniqueResult();
        session.delete(groupMember);
    }
}
