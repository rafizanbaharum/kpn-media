package my.gov.kpn.media.core.dao.impl;

import my.gov.kpn.media.core.dao.KpnPrincipalRoleDao;
import my.gov.kpn.media.core.model.*;
import my.gov.kpn.media.core.model.impl.KpnPrincipalRoleImpl;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static my.gov.kpn.media.core.model.KpnPrincipalType.GROUP;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
@Repository("principalRoleDao")
public class KpnPrincipalRoleDaoImpl implements KpnPrincipalRoleDao {

    private static final Logger log = Logger.getLogger(KpnPrincipalRoleDaoImpl.class);

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public void grant(KpnPrincipal principal, KpnRoleType roleType, KpnUser user) {
        Session session = sessionFactory.getCurrentSession();
        // save principal role
        KpnPrincipalRole principalRole = new KpnPrincipalRoleImpl();
        principalRole = prepareMetadata(principalRole, user);
        principalRole.setRoleType(roleType);
        principalRole.setPrincipal(principal);
        session.save(principalRole);
    }

    @Override
    public void grant(KpnPrincipal principal, KpnRoleType[] roleTypes, KpnUser user) {
        for (KpnRoleType role : roleTypes) {
            grant(principal, role, user);
        }
    }

    private Query getQueryString(KpnPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select r from KpnPrincipalRole r where r.principal = :principal and r.role = :role");
    }

    @Override
    public void revoke(KpnPrincipal principal, KpnRoleType roleType, KpnUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = getQueryString(principal);
        query.setEntity("principal", principal);
        query.setString("role", roleType.name());
        session.delete(query.uniqueResult());
    }

    @Override
    public void revoke(KpnPrincipal principal, KpnRoleType[] roleTypes, KpnUser user) {
        // TODO:

    }

    @Override
    public void revokeAll(KpnPrincipal principal, KpnUser user) {
        if (principal.getPrincipalType().equals(GROUP)) {
            revokeAll(principal);
        } else if (principal.getPrincipalType().equals(KpnPrincipalType.USER)) {
            revokeAll(principal);
        } else
            throw new IllegalArgumentException("Unknown principal type");
    }

    @Override
    public void overwrite(KpnPrincipal principal, KpnRoleType roleType, KpnUser user) {
        // TODO:

    }

    @Override
    public void overwrite(KpnPrincipal principal, KpnRoleType[] roleTypes, KpnUser user) {
        // TODO:
    }

    private void update(KpnUser user, KpnRoleType[] roleTypes, KpnUser adminUser) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from KpnPrincipalRole r where r.principal = :principal");
        query.setEntity("principal", user);
        List<KpnPrincipalRole> userRoles = (List<KpnPrincipalRole>) query.list();

        List<KpnPrincipalRole> newUserRoles = new ArrayList<KpnPrincipalRole>();
        for (KpnRoleType roleType : roleTypes) {
            KpnPrincipalRole principalRole = new KpnPrincipalRoleImpl();
            principalRole = prepareMetadata(principalRole, user);
            principalRole.setRoleType(roleType);
            principalRole.setPrincipal(user);
            newUserRoles.add(principalRole);
        }
        for (KpnPrincipalRole userRole : userRoles) {
            if (!newUserRoles.contains(userRole)) {
                System.out.println(userRole.getRoleType() + " removed");
                session.delete(userRole);
            }
        }

        for (KpnPrincipalRole newUserRole : newUserRoles) {
            if (!userRoles.contains(newUserRole)) {
                session.save(newUserRole);
            }
        }
    }

    public void update(KpnPrincipal principal, KpnPrincipalRole[] roles, KpnUser user) {
        if (principal.getPrincipalType().equals(GROUP)) {
            update((KpnGroup) principal, roles, user);
        } else if (principal.getPrincipalType().equals(KpnPrincipalType.USER)) {
            update((KpnUser) principal, roles, user);
        } else
            throw new IllegalArgumentException("Unknown principal type");
    }

    private void revokeAll(KpnPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from KpnPrincipalRole r where r.principal = :principal");
        query.setEntity("principal", principal);
        List<KpnPrincipalRole> roles = (List<KpnPrincipalRole>) query.list();
        for (KpnPrincipalRole role : roles) {
            session.delete(role);
        }
    }

    protected KpnPrincipalRole prepareMetadata(KpnPrincipalRole principalRole, KpnUser user) {
        KpnMetadata metadata = new KpnMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        metadata.setState(KpnMetaState.ACTIVE);
        principalRole.setMetadata(metadata);
        return principalRole;
    }
}