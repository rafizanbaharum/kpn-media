package my.gov.kpn.media.core.dao.impl;

import my.gov.kpn.media.core.dao.KpnPrincipalDao;
import my.gov.kpn.media.core.dao.KpnUserDao;
import my.gov.kpn.media.core.model.KpnGroup;
import my.gov.kpn.media.core.model.KpnMetaState;
import my.gov.kpn.media.core.model.KpnUser;
import my.gov.kpn.media.core.model.impl.KpnUserImpl;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
@Repository("userDao")
public class KpnUserDaoImpl extends DaoSupport<Long, KpnUser, KpnUserImpl> implements KpnUserDao {

    private static final Logger log = Logger.getLogger(KpnUserDaoImpl.class);

    @Autowired
    private KpnPrincipalDao principalDao;


    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public List<KpnGroup> findUserGroups(KpnUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from KpnGroup r inner join r.groupMembers m where m.principal = :user");
        query.setEntity("user", user);
        return (List<KpnGroup>) query.list();
    }

    @Override
    public boolean isExists(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from KpnUser u where " +
                "u.name = :username");
        query.setString("username", username);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public KpnUser findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (KpnUser) session.get(KpnUserImpl.class, id);
    }

    @Override
    public KpnUser findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from KpnUser u where u.name = :username");// and u.metadata.state = :state");
        query.setString("username", username);
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        return (KpnUser) query.uniqueResult();
    }

    public KpnUser findByRealName(String realname) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from KpnUser u where u.realname = :realname");
        query.setString("realname", realname);
        return (KpnUser) query.uniqueResult();
    }

    @Override
    public List<KpnUser> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from KpnUser s");
        return (List<KpnUser>) query.list();
    }

    @Override
    public List<KpnUser> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from KpnUser s");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<KpnUser>) query.list();
    }

    @Override
    public List<KpnUser> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from KpnUser s where (s.name like :filter or s.realname like upper(:filter))");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<KpnUser>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(u) from KpnUser u");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from KpnUser s where s.name like :filter " +
                "or s.realname like upper(:filter)");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }
}
