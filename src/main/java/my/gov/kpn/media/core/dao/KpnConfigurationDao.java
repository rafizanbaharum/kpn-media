package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.KpnConfiguration;
import my.gov.kpn.media.core.model.KpnUser;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/27/13
 */
public interface KpnConfigurationDao {

    // ====================================================================================================
    // HELPERS
    // ====================================================================================================

    KpnConfiguration newInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    KpnConfiguration findById(Long id);

    KpnConfiguration findByKey(String key);

    List<KpnConfiguration> find(Integer offset, Integer limit);

    List<KpnConfiguration> find(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(KpnConfiguration configuration, KpnUser user);

    void update(KpnConfiguration configuration, KpnUser user);

    void deactivate(KpnConfiguration configuration, KpnUser user);

    void remove(KpnConfiguration configuration, KpnUser user);
}
