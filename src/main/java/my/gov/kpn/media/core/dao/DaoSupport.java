package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.KpnUser;
import org.hibernate.Session;

import java.util.List;

public interface DaoSupport<K, I, E> {

    public I findById(K k);

    public List<I> find();

    public List<I> find(Integer offset, Integer limit);

    public void save(I entity, KpnUser user);

    public void saveOrUpdate(I entity, KpnUser user);

    public void save(Session session, I i, KpnUser user);

    public void update(I entity, KpnUser user);

    public void deactivate(I entity, KpnUser user);

    public void remove(I entity, KpnUser user);
}
