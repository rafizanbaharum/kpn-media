package my.gov.kpn.media.core.dao.impl;

import my.gov.kpn.media.core.dao.KpnDirectoryDao;
import my.gov.kpn.media.core.model.*;
import my.gov.kpn.media.core.model.impl.KpnDirectoryImpl;
import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static my.gov.kpn.media.core.model.KpnMetaState.ACTIVE;

/**
 * @author rafizan.baharum
 * @since 11/27/13
 */
@Repository("directoryDao")
public class KpnDirectoryDaoImpl extends DaoSupport<Long, KpnDirectory, KpnDirectoryImpl> implements KpnDirectoryDao {
    // =============================================================================
    // HELPER METHODS
    // =============================================================================

    @Override
    public KpnDirectory newInstance() {
        return new KpnDirectoryImpl();
    }


    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public KpnDirectory findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (KpnDirectory) session.get(KpnDirectoryImpl.class, id);
    }

    @Override
    public KpnDirectory findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from KpnDirectory d where d.name = :name");
        query.setString("name", name);
        query.setCacheable(true);
        return (KpnDirectory) query.uniqueResult();
    }

    @Override
    public List<KpnDirectory> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from KpnDirectory d where " +
                "d.metadata.state = :state");
        query.setInteger("state", KpnMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<KpnDirectory> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from KpnDirectory d where " +
                "(d.name like upper(:filter) " +
                "or upper(d.description) like upper(:filter)) " +
                "and d.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<KpnMedia> findMedia(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from KpnMedia m where " +
                "(m.name like upper(:filter) " +
                "or upper(m.description) like upper(:filter)) " +
                "and m.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public List<KpnMedia> findMedia(KpnDirectory directory, Integer offset, Integer limit) {
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
        Query query = session.createQuery("select count(d) from KpnDirectory d where " +
                "d.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(d) from KpnDirectory d where " +
                "(d.name like upper(:filter) " +
                "or upper(d.description) like upper(:filter)) " +
                "and d.metadata.state = :state");
        query.setString("filter", filter);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countMedia() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(m) from KpnMedia m where " +
                "m.metadata.state = :state");
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countMedia(KpnDirectory directory) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(m) from KpnMedia m where " +
                "m.directory = :directory " +
                "and m.metadata.state = :state");
        query.setEntity("directory", directory);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public void addMedia(KpnDirectory directory, KpnMedia media, KpnUser user) {
        Validate.notNull(directory, "directory should not be null");
        Validate.notNull(media, "media should not be null");

        // session
        Session session = sessionFactory.getCurrentSession();
        media.setDirectory(directory);

        // prepare metadata
        KpnMetadata metadata = new KpnMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreator(user.getId());
        media.setMetadata(metadata);
        session.save(media);
    }

    @Override
    public void updateMedia(KpnDirectory directory, KpnMedia media, KpnUser user) {
        Validate.notNull(directory, "directory should not be null");
        Validate.notNull(media, "media should not be null");

        // session
        Session session = sessionFactory.getCurrentSession();
        media.setDirectory(directory);

        // prepare metadata
        KpnMetadata metadata = media.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        media.setMetadata(metadata);
        session.update(media);
    }

    @Override
    public void removeMedia(KpnDirectory directory, KpnMedia media, KpnUser user) {
        Validate.notNull(directory, "directory should not be null");
        Validate.notNull(media, "media should not be null");

        // session
        Session session = sessionFactory.getCurrentSession();
        media.setDirectory(directory);

        // prepare metadata
        KpnMetadata metadata = media.getMetadata();
        metadata.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setDeleter(user.getId());
        metadata.setState(KpnMetaState.INACTIVE);
        media.setMetadata(metadata);
        session.update(media);
    }
}
    