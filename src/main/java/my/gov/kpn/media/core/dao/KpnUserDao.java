package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.KpnGroup;
import my.gov.kpn.media.core.model.KpnUser;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
public interface KpnUserDao {

    KpnUser findById(Long id);

    KpnUser findByUsername(String username);

    KpnUser findByRealName(String realname);

    List<KpnUser> findAll();

    List<KpnUser> find(Integer offset, Integer limit);

    List<KpnUser> find(String filter, Integer offset, Integer limit);

    List<KpnGroup> findUserGroups(KpnUser user);

    Integer count();

    Integer count(String filter);

    boolean isExists(String username);

    // cruds

    void save(KpnUser suser, KpnUser user);

    void update(KpnUser suser, KpnUser user);

    void deactivate(KpnUser suser, KpnUser user);

    void remove(KpnUser suser, KpnUser user);

}
