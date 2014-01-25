package my.gov.kpn.media.core.dao.impl;

import my.gov.kpn.media.core.dao.KpnConfigurationDao;
import my.gov.kpn.media.core.model.KpnConfiguration;
import my.gov.kpn.media.core.model.KpnMetaState;
import my.gov.kpn.media.core.model.impl.KpnConfigurationImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static my.gov.kpn.media.core.model.KpnMetaState.ACTIVE;

/**
 * @author rafizan.baharum
 * @since 11/27/13
 */
@Repository("configurationDao")
public class KpnConfigurationDaoImpl extends DaoSupport<Long, KpnConfiguration, KpnConfigurationImpl> implements KpnConfigurationDao {
    // =============================================================================
    // HELPER METHODS
    // =============================================================================

    @Override
    public KpnConfiguration newInstance() {
        return new KpnConfigurationImpl();
    }


    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public KpnConfiguration findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (KpnConfiguration) session.get(KpnConfigurationImpl.class, id);
    }

    @Override
    public KpnConfiguration findByKey(String key) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rn from KpnConfiguration rn where rn.key = :key");
        query.setString("key", key);
        query.setCacheable(true);
        return (KpnConfiguration) query.uniqueResult();
    }

    @Override
    public List<KpnConfiguration> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rn from KpnConfiguration rn where " +
                "rn.metadata.state = :state");
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<KpnConfiguration> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select rn from KpnConfiguration rn where " +
                "(rn.key like upper(:filter) " +
                "or upper(rn.value) like upper(:filter)) " +
                "and rn.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rn) from KpnConfiguration rn where " +
                "rn.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(rn) from KpnConfiguration rn where " +
                "(rn.key like upper(:filter) " +
                "or upper(rn.value) like upper(:filter)) " +
                "and rn.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }
}
    