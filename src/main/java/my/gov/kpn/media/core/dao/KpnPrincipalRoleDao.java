package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.KpnRoleType;
import my.gov.kpn.media.core.model.KpnUser;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
public interface KpnPrincipalRoleDao {

    void grant(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnRoleType roleType, KpnUser user);

    void grant(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnRoleType[] roleTypes, KpnUser user);

    void revoke(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnRoleType roleType, KpnUser user);

    void revoke(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnRoleType[] roleTypes, KpnUser user);

    void revokeAll(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnUser user);

    void overwrite(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnRoleType roleType, KpnUser user);

    void overwrite(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnRoleType[] roleTypes, KpnUser user);

}
