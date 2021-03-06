package my.gov.kpn.media.core.model.impl;

import my.gov.kpn.media.core.model.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author rafizan.baharum
 * @since 7/12/13
 */
@Table(name = "KPN_GROP_MMBR")
@Entity(name = "KpnGroupMember")
public class KpnGroupMemberImpl implements KpnGroupMember, Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_KPN_GROP_MMBR")
    @SequenceGenerator(name = "SEQ_KPN_GROP_MMBR", sequenceName = "SEQ_KPN_GROP_MMBR", allocationSize = 1)
    private Long id;

    @OneToOne(targetEntity = KpnGroupImpl.class)
    @JoinColumn(name = "GROUP_ID")
    private KpnGroup group;

    @OneToOne(targetEntity = KpnUserImpl.class)
    @JoinColumn(name = "MEMBER_ID")
    private KpnUser member;

    @Embedded
    private KpnMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KpnGroup getGroup() {
        return group;
    }

    public void setGroup(KpnGroup group) {
        this.group = group;
    }

    public KpnUser getMember() {
        return member;
    }

    public void setMember(KpnUser member) {
        this.member = member;
    }

    public KpnMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(KpnMetadata metadata) {
        this.metadata = metadata;
    }
}
