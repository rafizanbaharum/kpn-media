package my.gov.kpn.media.core.dao.impl;

import my.gov.kpn.media.core.dao.KpnMetaObjectDao;
import my.gov.kpn.media.core.model.KpnMetaObject;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
@SuppressWarnings({"unchecked"})
@Repository("objectDao")
public class KpnMetaObjectDaoImpl implements KpnMetaObjectDao {

    private static final Logger log = Logger.getLogger(KpnMetaObjectDaoImpl.class);

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    @Override
    public KpnMetaObject findObjectById(String entityName, Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (KpnMetaObject) session.get(entityName, id);
    }
}
