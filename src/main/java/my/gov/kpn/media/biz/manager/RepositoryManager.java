package my.gov.kpn.media.biz.manager;


import my.gov.kpn.media.core.dao.impl.DirectoryNotExistException;
import my.gov.kpn.media.core.dao.impl.MediaNotExistException;
import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 1/13/14
 */
public interface RepositoryManager {

    KpnDirectory findDirectoryById(Long id) throws DirectoryNotExistException;

    KpnDirectory findDirectoryByName(String name);

    KpnMedia findMediaById(Long id) throws MediaNotExistException;

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
