package my.gov.kpn.media.core.model;

import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 26/04/14
 */
public interface KpnPrincipal extends KpnMetaObject {

    String getName();

    void setName(String name);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    boolean isLocked();

    void setLocked(boolean locked);

    KpnPrincipalType getPrincipalType();

    void setPrincipalType(KpnPrincipalType principalType);

    Set<KpnPrincipalRole> getRoles();

    void setRoles(Set<KpnPrincipalRole> roles);

}
