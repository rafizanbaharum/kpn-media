package my.gov.kpn.media.biz.manager.impl;

import my.gov.kpn.media.biz.Util;
import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.core.dao.KpnDirectoryDao;
import my.gov.kpn.media.core.dao.KpnMediaDao;
import my.gov.kpn.media.core.dao.impl.DirectoryNotExistException;
import my.gov.kpn.media.core.dao.impl.MediaNotExistException;
import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 1/13/14
 */
@Service("directoryManager")
@Transactional
public class RepositoryManagerImpl implements RepositoryManager {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private KpnDirectoryDao directoryDao;

    @Autowired
    private KpnMediaDao mediaDao;

    @Override
    public KpnDirectory findDirectoryById(Long id) throws DirectoryNotExistException {
        KpnDirectory directory = directoryDao.findById(id);
        if (null != directory) {
            return directory;
        }

        throw new DirectoryNotExistException(id);

    }

    @Override
    public KpnDirectory findDirectoryByName(String name) {
        return directoryDao.findByName(name);
    }

    @Override
    public KpnMedia findMediaById(Long id) throws MediaNotExistException {
        KpnMedia media = mediaDao.findById(id);
        if (media != null) {
            return media;
        }
        throw new MediaNotExistException(id);
    }

    @Override
    public List<KpnDirectory> findDirectories() {
        return directoryDao.find(0, 99999);
    }

    @Override
    public List<KpnDirectory> findDirectories(Integer offset, Integer limit) {
        return directoryDao.find(offset, limit);
    }

    @Override
    public List<KpnMedia> findMedias(Integer offset, Integer limit) {
        return mediaDao.find(offset, limit);
    }

    @Override
    public List<KpnMedia> findMedias(KpnDirectory directory, Integer offset, Integer limit) {
        return mediaDao.find(directory, offset, limit);
    }

    @Override
    public Integer countDirectory() {
        return directoryDao.count();

    }

    @Override
    public Integer countMedia() {
        return mediaDao.count();
    }

    @Override
    public Integer countMedia(KpnDirectory directory) {
        return mediaDao.count(directory);
    }

    @Override
    public boolean isDirectoryExists(String name) {
        return directoryDao.isExists(name);

    }

    @Override
    public boolean isMediaExists(String name) {
        return mediaDao.isExists(name);
    }

    @Override
    public void saveDirectory(KpnDirectory directory) {
        directoryDao.save(directory, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateDirectory(KpnDirectory directory) {
        directoryDao.update(directory, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeDirectory(KpnDirectory directory) {
        directoryDao.remove(directory, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addMedia(KpnDirectory directory, KpnMedia media) {
        directoryDao.addMedia(directory, media, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateMedia(KpnDirectory directory, KpnMedia media) {
        directoryDao.updateMedia(directory, media, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeMedia(KpnDirectory directory, KpnMedia media) {
        directoryDao.removeMedia(directory, media, Util.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
}
