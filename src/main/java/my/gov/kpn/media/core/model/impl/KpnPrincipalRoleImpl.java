package my.gov.kpn.media.core.model.impl;

import my.gov.kpn.media.core.model.KpnMetadata;
import my.gov.kpn.media.core.model.KpnPrincipal;
import my.gov.kpn.media.core.model.KpnPrincipalRole;
import my.gov.kpn.media.core.model.KpnRoleType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author rafizan.baharum
 * @since 7/12/13
 */
@Table(name = "KPN_PCPL_ROLE")
@Entity(name = "KpnPrincipalRole")
public class KpnPrincipalRoleImpl implements KpnPrincipalRole, Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_KPN_PCPL_ROLE")
    @SequenceGenerator(name = "SEQ_KPN_PCPL_ROLE", sequenceName = "SEQ_KPN_PCPL_ROLE", allocationSize = 1)
    private Long id;

    @Column(name = "ROLE_TYPE")
    private KpnRoleType roleType;

    @OneToOne(targetEntity = KpnPrincipalImpl.class)
    @JoinColumn(name = "PRINCIPAL_ID")
    private KpnPrincipal principal;

    @Embedded
    private KpnMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KpnRoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(KpnRoleType roleType) {
        this.roleType = roleType;
    }

    public KpnPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(KpnPrincipal principal) {
        this.principal = principal;
    }

    public KpnMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(KpnMetadata metadata) {
        this.metadata = metadata;
    }
}
