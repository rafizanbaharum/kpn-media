package my.gov.kpn.media.core.model;

/**
 * @author rafizan.baharum
 * @since 26/04/14
 */
public interface KpnUser extends KpnPrincipal {

    String getUsername();

    void setUsername(String username);

    String getRealname();

    void setRealname(String realname);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);
}
