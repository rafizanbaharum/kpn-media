package my.gov.kpn.media.core.dao.impl;

import my.gov.kpn.media.core.model.KpnMetaObject;
import my.gov.kpn.media.core.model.KpnMetaState;
import my.gov.kpn.media.core.model.KpnMetadata;
import my.gov.kpn.media.core.model.KpnUser;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
public class DaoSupport<K, I, E> implements InitializingBean {

    private static final Logger log = Logger.getLogger(DaoSupport.class);

    public static final String WILDCARD = "%";

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    // entity
    private Class<I> interfaceClass;
    private Class<E> entityClass;


    @Override
    public void afterPropertiesSet() throws Exception {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        interfaceClass = (Class<I>) genericSuperclass.getActualTypeArguments()[1];
        entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[2];
    }

    public I refresh(I i) {
        sessionFactory.getCurrentSession().refresh(i);
        return i;
    }

    public I findById(K k) {
        Session session = sessionFactory.getCurrentSession();
        return (I) session.get(entityClass, (Serializable) k);
    }

    /**
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    public List<I> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a "
                + " from " + interfaceClass.getName() + "  a");
        return (List<I>) query.list();
    }

    /**
     * @param offset
     * @param limit
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    public List<I> find(Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a "
                + " from " + entityClass.getName() + " a");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<I>) query.list();
    }

    protected void prepareMetadata(I i, KpnUser user) {

        if (i instanceof KpnMetaObject) {
            KpnMetadata metadata = null;
            if (((KpnMetaObject) i).getMetadata() != null)
                metadata = ((KpnMetaObject) i).getMetadata();
            else
                metadata = new KpnMetadata();
            metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            metadata.setCreator(user.getId());
            metadata.setState(KpnMetaState.ACTIVE);
            ((KpnMetaObject) i).setMetadata(metadata);
        }
    }

    /**
     * @param entity
     * @param user
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    public void save(I entity, KpnUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        try {
            Session session = sessionFactory.getCurrentSession();
            prepareMetadata(entity, user);
            session.save(entity);
        } catch (HibernateException e) {
            log.debug("error occurred", e);
        }
    }

    /**
     * @param entity
     * @param user
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    public void saveOrUpdate(I entity, KpnUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        try {
            Session session = sessionFactory.getCurrentSession();
            prepareMetadata(entity, user);
            session.saveOrUpdate(entity);
        } catch (HibernateException e) {
            log.debug("error occurred", e);
        }
    }

    public void save(Session session, I i, KpnUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(i, "Object cannot be null");

        prepareMetadata(i, user);
        session.save(i);
    }

    /**
     * @param entity
     * @param user
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    public void update(I entity, KpnUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        Session session = sessionFactory.getCurrentSession();
        KpnMetadata metadata = ((KpnMetaObject) entity).getMetadata();
        if (null == metadata) {
            metadata = new KpnMetadata();
            metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            metadata.setCreator(user.getId());
            metadata.setState(KpnMetaState.ACTIVE);
        }
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        ((KpnMetaObject) entity).setMetadata(metadata);
        session.update(entity);
    }

    /**
     * @param entity
     * @param user
     * @return
     * @throws org.springframework.dao.DataAccessException
     *
     */
    public void deactivate(I entity, KpnUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");

        Session session = sessionFactory.getCurrentSession();
        KpnMetadata metadata = ((KpnMetaObject) entity).getMetadata();
        if (null == metadata) {
            metadata = new KpnMetadata();
            metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            metadata.setCreator(user.getId());
        }

        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifier(user.getId());
        metadata.setState(KpnMetaState.INACTIVE);
        ((KpnMetaObject) entity).setMetadata(metadata);
        session.update(entity);
    }

    /**
     * @param entity
     * @param user
     */
    public void remove(I entity, KpnUser user) {
        Validate.notNull(user, "User cannot be null");
        Validate.notNull(entity, "Object cannot be null");
        Session session = sessionFactory.getCurrentSession();

        KpnMetadata metadata = ((KpnMetaObject) entity).getMetadata();
        metadata.setState(KpnMetaState.INACTIVE);
        metadata.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setDeleter(user.getId());
        ((KpnMetaObject) entity).setMetadata(metadata);
        session.update(entity);
    }
}