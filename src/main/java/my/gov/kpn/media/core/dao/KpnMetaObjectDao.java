package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.KpnMetaObject;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
public interface KpnMetaObjectDao {

    KpnMetaObject findObjectById(String entityName, Long id);

}
