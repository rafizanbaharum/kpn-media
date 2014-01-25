package my.gov.kpn.media.core.dao.impl;

import my.gov.kpn.media.core.dao.KpnPrincipalDao;
import my.gov.kpn.media.core.model.KpnMetaState;
import my.gov.kpn.media.core.model.KpnPrincipal;
import my.gov.kpn.media.core.model.KpnPrincipalType;
import my.gov.kpn.media.core.model.impl.KpnPrincipalImpl;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
@Repository("principalDao")
public class KpnPrincipalDaoImpl extends DaoSupport<Long, KpnPrincipal, KpnPrincipalImpl> implements KpnPrincipalDao {

    private static final Logger log = Logger.getLogger(KpnPrincipalDaoImpl.class);

    @Override
    public List<KpnPrincipal> findAllPrincipals() {
        Session session = sessionFactory.getCurrentSession();
        List<KpnPrincipal> results = new ArrayList<KpnPrincipal>();
        Query query = session.createQuery("select p from KpnUser p where p.metadata.state = :state order by p.name");
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        results.addAll((List<KpnPrincipal>) query.list());

        Query queryGroup = session.createQuery("select p from KpnGroup p where p.metadata.state = :state order by p.name ");
        queryGroup.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        results.addAll((List<KpnPrincipal>) queryGroup.list());
        return results;
    }

    @Override
    public List<KpnPrincipal> findPrincipals(String filter) {
        Session session = sessionFactory.getCurrentSession();
        List<KpnPrincipal> results = new ArrayList<KpnPrincipal>();
        Query query = session.createQuery("select p from KpnPrincipal p where p.metadata.state = :state and p.name like :filter order by p.name");
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        query.setString("filter", WILDCARD + filter + WILDCARD);
        results.addAll((List<KpnPrincipal>) query.list());
        return results;
    }

    @Override
    public List<KpnPrincipal> findPrincipals(String filter, KpnPrincipalType type) {
        Session session = sessionFactory.getCurrentSession();
        List<KpnPrincipal> results = new ArrayList<KpnPrincipal>();
        Query query = session.createQuery("select p from KpnPrincipal p where " +
                "p.metadata.state = :state " +
                "and p.name like :filter " +
                "and p.principalType = :principalType " +
                "order by p.name");
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("principalType", type.ordinal());
        results.addAll((List<KpnPrincipal>) query.list());
        return results;
    }

    @Override
    public List<KpnPrincipal> findPrincipals(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from KpnPrincipal p");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<KpnPrincipal>) query.list();
    }

    @Override
    public KpnPrincipal findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        log.debug("Searching for principal " + name);
        Query query = session.createQuery("select p from KpnPrincipal p where p.name = :name");
        query.setString("name", name);
        return (KpnPrincipal) query.uniqueResult();
    }
}
