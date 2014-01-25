package my.gov.kpn.media.core.model.impl;

import my.gov.kpn.media.core.model.KpnGroup;
import my.gov.kpn.media.core.model.KpnGroupMember;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 7/10/13
 */
@Table(name = "KPN_GROP")
@Entity(name = "KpnGroup")
public class KpnGroupImpl extends KpnPrincipalImpl implements KpnGroup {

    @OneToMany(targetEntity = KpnGroupMemberImpl.class, mappedBy = "group")
    private Set<KpnGroupMember> members;

    public Set<KpnGroupMember> getMembers() {
        return members;
    }

    public void setMembers(Set<KpnGroupMember> members) {
        this.members = members;
    }
}
