package my.gov.kpn.media.core.model;

import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
public interface KpnGroup extends KpnPrincipal {

    Set<KpnGroupMember> getMembers();

    void setMembers(Set<KpnGroupMember> members);
}
