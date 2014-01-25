package my.gov.kpn.media.core.model.impl;

import my.gov.kpn.media.core.model.KpnMetadata;
import my.gov.kpn.media.core.model.KpnPrincipal;
import my.gov.kpn.media.core.model.KpnPrincipalRole;
import my.gov.kpn.media.core.model.KpnPrincipalType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 7/10/13
 */
@Table(name = "KPN_PCPL")
@Entity(name = "KpnPrincipal")
@Inheritance(strategy = InheritanceType.JOINED)
public class KpnPrincipalImpl implements KpnPrincipal, Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_KPN_PCPL")
    @SequenceGenerator(name = "SEQ_KPN_PCPL", sequenceName = "SEQ_KPN_PCPL", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "ENABLED")
    private boolean enabled = true;

    @Column(name = "LOCKED")
    private boolean locked;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "PRINCIPAL_TYPE")
    private KpnPrincipalType principalType;

    @OneToMany(targetEntity = KpnPrincipalRoleImpl.class, mappedBy = "principal", fetch = FetchType.EAGER)
    private Set<KpnPrincipalRole> roles;

    @Embedded
    private KpnMetadata metadata = new KpnMetadata();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public KpnPrincipalType getPrincipalType() {
        return principalType;
    }

    public void setPrincipalType(KpnPrincipalType principalType) {
        this.principalType = principalType;
    }

    public Set<KpnPrincipalRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<KpnPrincipalRole> roles) {
        this.roles = roles;
    }

    public KpnMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(KpnMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "CmPrincipalImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + locked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KpnPrincipalImpl that = (KpnPrincipalImpl) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
