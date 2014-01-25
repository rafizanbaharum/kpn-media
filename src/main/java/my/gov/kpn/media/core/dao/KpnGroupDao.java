package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.*;

import java.util.List;

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

    List<KpnUser> findMembers(KpnGroup group);

    List<KpnUser> findMembers(KpnGroup group, KpnPrincipalType type);

    List<KpnGroup> findMemberships(KpnUser user);

    List<KpnUser> findMembers(KpnGroup group, Integer offset, Integer limit);

    List<KpnGroup> find(Integer offset, Integer limit);

    List<KpnGroup> find(String filter, Integer offset, Integer limit);

    KpnGroupMember findGroupMember(KpnGroup group, KpnUser user);

    Integer count();

    Integer count(String filter);

    boolean isMemberOf(KpnGroup group, KpnUser principal);

    void save(KpnGroup group, KpnUser user);

    void update(KpnGroup group, KpnUser user);

    void deactivate(KpnGroup group, KpnUser user);

    void remove(KpnGroup group, KpnUser user);

    void addMember(KpnGroup group, KpnUser principal, KpnUser user);

    void addMembers(KpnGroup group, List<KpnUser> principals, KpnUser user);

    void removeMember(KpnGroup group, KpnUser user);
}
