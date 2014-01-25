package my.gov.kpn.media.core.dao.impl;

import my.gov.kpn.media.core.dao.KpnMediaDao;
import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.core.model.KpnMetaState;
import my.gov.kpn.media.core.model.impl.KpnMediaImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static my.gov.kpn.media.core.model.KpnMetaState.ACTIVE;

/**
 * @author rafizan.baharum
 * @since 11/27/13
 */
@Repository("mediaDao")
public class KpnMediaDaoImpl extends DaoSupport<Long, KpnMedia, KpnMediaImpl> implements KpnMediaDao {
    // =============================================================================
    // HELPER METHODS
    // =============================================================================

    @Override
    public KpnMedia newInstance() {
        return new KpnMediaImpl();
    }

    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public KpnMedia findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (KpnMedia) session.get(KpnMediaImpl.class, id);
    }

    @Override
    public KpnMedia findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from KpnMedia m where m.name = :name");
        query.setString("name", name);
        query.setCacheable(true);
        return (KpnMedia) query.uniqueResult();
    }

    @Override
    public List<KpnMedia> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from KpnMedia m where " +
                "m.metadata.state = :state");
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<KpnMedia> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from KpnMedia m where " +
                "(m.name like upper(:filter) " +
                "or upper(m.value) like upper(:filter)) " +
                "and m.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<KpnMedia> find(KpnDirectory directory, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from KpnMedia m where " +
                "m.directory = :directory " +
                "and m.metadata.state = :state");
        query.setEntity("directory", directory);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(m) from KpnMedia m where " +
                "m.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(m) from KpnMedia m where " +
                "(m.name like upper(:filter) " +
                "or upper(m.value) like upper(:filter)) " +
                "and m.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(KpnDirectory directory) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(m) from KpnMedia m where " +
                "m.directory = :directory " +
                "and m.metadata.state = :state");
        query.setEntity("directory", directory);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }


    @Override
    public boolean isExists(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from KpnMedia u where " +
                "u.name = :name");
        query.setString("name", name);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

}
    