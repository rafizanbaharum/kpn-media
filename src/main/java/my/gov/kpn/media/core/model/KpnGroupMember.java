package my.gov.kpn.media.core.model;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
public interface KpnGroupMember extends KpnMetaObject {

    KpnGroup getGroup();

    void setGroup(KpnGroup group);

    KpnUser getMember();

    void setMember(KpnUser member);
}

