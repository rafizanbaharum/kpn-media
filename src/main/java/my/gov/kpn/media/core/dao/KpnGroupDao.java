package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.*;

import java.util.List;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
public interface KpnGroupDao {

    // finders

    KpnGroup findById(Long id);

    KpnGroup findByName(String name);

    List<KpnGroup> findAll();

    List<String> findAllGroupName();

    List<KpnPrincipal> findMembers(KpnGroup group);

    List<KpnPrincipal> findMembers(KpnGroup group, KpnPrincipalType type);

    List<KpnGroup> findMemberships(KpnPrincipal principal);

    List<KpnPrincipal> findMembers(KpnGroup group, Integer offset, Integer limit);

    List<KpnGroup> find(Integer offset, Integer limit);

    List<KpnGroup> find(String filter, Integer offset, Integer limit);

    List<KpnGroup> findParentGroup(KpnGroup group);

    KpnGroupMember findGroupMember(KpnGroup group, KpnPrincipal principal);

    Integer count();

    Integer count(String filter);

    boolean isMemberOf(KpnGroup group, KpnPrincipal principal);

    void save(KpnGroup group, KpnUser user);

    void update(KpnGroup group, KpnUser user);

    void deactivate(KpnGroup group, KpnUser user);

    void remove(KpnGroup group, KpnUser user);

    void addMember(KpnGroup group, KpnPrincipal principal, KpnUser user);

    void addMembers(KpnGroup group, List<KpnPrincipal> principals, KpnUser user);

    void removeMember(KpnGroup group, KpnPrincipal principal);
}
