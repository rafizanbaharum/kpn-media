package my.gov.kpn.media.biz.manager;


import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 1/13/14
 */
public interface RepositoryManager {

    KpnDirectory findDirectoryById(Long id);

    KpnDirectory findDirectoryByName(String name);

    KpnDirectory findDirectoryByCode(String code);

    List<KpnDirectory> findDirectories();

    List<KpnDirectory> findDirectories(Integer offset, Integer limit);

    List<KpnMedia> findMedias(Integer offset, Integer limit);

    List<KpnMedia> findMedias(KpnDirectory directory, Integer offset, Integer limit);

    Integer countDirectory();

    Integer countMedia();

    Integer countMedia(KpnDirectory directory);

    boolean isDirectoryExists(String name);

    boolean isMediaExists(String name);

    void saveDirectory(KpnDirectory directory);

    void updateDirectory(KpnDirectory directory);

    void removeDirectory(KpnDirectory directory);

    void addMedia(KpnDirectory directory, KpnMedia media);

    void updateMedia(KpnDirectory directory, KpnMedia media);

    void removeMedia(KpnDirectory directory, KpnMedia media);

}
