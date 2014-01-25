package my.gov.kpn.media.core.model;

/**
 * @author rafizan.baharum
 * @since 26/04/14
 */
public interface KpnPrincipalRole extends KpnMetaObject {

    /**
     * @return
     */
    KpnRoleType getRoleType();

    void setRoleType(KpnRoleType roleType);

    /**
     * @return
     */
    KpnPrincipal getPrincipal();

    void setPrincipal(KpnPrincipal principal);

}
