package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.core.model.KpnUser;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/27/13
 */
public interface KpnDirectoryDao {

    // ====================================================================================================
    // HELPERS
    // ====================================================================================================

    KpnDirectory newInstance();

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    KpnDirectory findById(Long id);

    KpnDirectory findByName(String name);

    List<KpnDirectory> find(Integer offset, Integer limit);

    List<KpnDirectory> find(String filter, Integer offset, Integer limit);

    List<KpnMedia> findMedia(String filter, Integer offset, Integer limit);

    List<KpnMedia> findMedia(KpnDirectory directory, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    Integer countMedia();

    Integer countMedia(KpnDirectory directory);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void save(KpnDirectory Directory, KpnUser user);

    void update(KpnDirectory Directory, KpnUser user);

    void deactivate(KpnDirectory Directory, KpnUser user);

    void remove(KpnDirectory Directory, KpnUser user);

    void addMedia(KpnDirectory directory, KpnMedia media, KpnUser user);

    void updateMedia(KpnDirectory directory, KpnMedia media, KpnUser user);

    void removeMedia(KpnDirectory directory, KpnMedia media, KpnUser user);

}
