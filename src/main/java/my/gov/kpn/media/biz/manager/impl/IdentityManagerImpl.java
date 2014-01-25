package my.gov.kpn.media.biz.manager.impl;

import my.gov.kpn.media.biz.Util;
import my.gov.kpn.media.biz.manager.IdentityManager;
import my.gov.kpn.media.core.dao.KpnGroupDao;
import my.gov.kpn.media.core.dao.KpnPrincipalDao;
import my.gov.kpn.media.core.dao.KpnUserDao;
import my.gov.kpn.media.core.model.KpnGroup;
import my.gov.kpn.media.core.model.KpnPrincipal;
import my.gov.kpn.media.core.model.KpnUser;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 1/15/14
 */
@Service("identityManager")
@Transactional
public class IdentityManagerImpl implements IdentityManager {

    private static final Logger LOG = Logger.getLogger(IdentityManagerImpl.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private KpnPrincipalDao principalDao;

    @Autowired
    private KpnUserDao userDao;

    @Autowired
    private KpnGroupDao groupDao;

    @Override
    public KpnPrincipal findPrincipalByName(String name) {
        return principalDao.findByName(name);
    }

    @Override
    public KpnUser findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public KpnGroup findGroupByName(String name) {
        return groupDao.findByName(name);
    }

    @Override
    public KpnUser findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public KpnGroup findGroupById(Long id) {
        return groupDao.findById(id);
    }

    @Override
    public List<KpnUser> findUsers(Integer offset, Integer limit) {
        return userDao.find(offset, limit);
    }

    @Override
    public List<KpnGroup> findGroups(Integer offset, Integer limit) {
        return groupDao.find(offset, limit);
    }

    @Override
    public List<KpnGroup> findGroups(KpnUser user) {
        return groupDao.findMemberships(user);
    }

    @Override
    public List<KpnGroup> findGroups(KpnUser user, Integer offset, Integer limit) {
        return groupDao.findMemberships(user);
    }

    @Override
    public List<KpnUser> findGroupMembers(KpnGroup group) {
        return groupDao.findMembers(group);
    }

    @Override
    public List<KpnUser> findGroupMembers(KpnGroup group, Integer offset, Integer limit) {
        return groupDao.findMembers(group, offset, limit);
    }

    @Override
    public Integer countUser() {
        return userDao.count();
    }

    @Override
    public Integer countGroup() {
        return groupDao.count();
    }

    @Override
    public void addGroupMember(KpnGroup group, KpnUser user) {
        groupDao.addMember(group, user, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void saveUser(KpnUser user) {
        userDao.save(user, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateUser(KpnUser user) {
        userDao.update(user, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void saveGroup(KpnGroup group) {
        groupDao.save(group, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateGroup(KpnGroup group) {
        groupDao.update(group, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
}
