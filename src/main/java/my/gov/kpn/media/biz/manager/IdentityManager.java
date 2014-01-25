package my.gov.kpn.media.biz.manager;

import my.gov.kpn.media.core.model.KpnGroup;
import my.gov.kpn.media.core.model.KpnPrincipal;
import my.gov.kpn.media.core.model.KpnUser;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 1/15/14
 */
public interface IdentityManager {

    KpnPrincipal findPrincipalByName(String name);

    KpnUser findUserByUsername(String username);

    KpnGroup findGroupByName(String name);

    KpnUser findUserById(Long id);

    KpnGroup findGroupById(Long id);

    List<KpnUser> findUsers(Integer offset, Integer limit);

    List<KpnGroup> findGroups(Integer offset, Integer limit);

    List<KpnGroup> findGroups(KpnUser user);

    List<KpnGroup> findGroups(KpnUser user, Integer offset, Integer limit);

    List<KpnUser> findGroupMembers(KpnGroup group);

    List<KpnUser> findGroupMembers(KpnGroup group, Integer offset, Integer limit);

    Integer countUser();

    Integer countGroup();

    void saveUser(KpnUser user);

    void updateUser(KpnUser user);

    void saveGroup(KpnGroup group);

    void updateGroup(KpnGroup group);

    void addGroupMember(KpnGroup group, KpnUser user);
}
