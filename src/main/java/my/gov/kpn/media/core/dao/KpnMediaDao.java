package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.core.model.KpnUser;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/27/13
 */
public interface KpnMediaDao {

    // ====================================================================================================
    // HELPERS
    // ====================================================================================================

    KpnMedia newInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    KpnMedia findById(Long id);

    KpnMedia findByName(String name);

    List<KpnMedia> find(Integer offset, Integer limit);

    List<KpnMedia> find(String filter, Integer offset, Integer limit);

    List<KpnMedia> find(KpnDirectory directory, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    Integer count(KpnDirectory directory);

    boolean isExists(String name);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(KpnMedia Media, KpnUser user);

    void update(KpnMedia Media, KpnUser user);

    void deactivate(KpnMedia Media, KpnUser user);

    void remove(KpnMedia Media, KpnUser user);
}
