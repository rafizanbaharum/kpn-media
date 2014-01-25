package my.gov.kpn.media.biz.integration.springsecurity;

import my.gov.kpn.media.core.model.KpnUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 1/15/14
 */
public class KpnUserDetails implements UserDetails {

    private KpnUser user;
    private Set<GrantedAuthority> grantedAuthorities;

    public KpnUserDetails() {
    }

    public KpnUserDetails(KpnUser user, Set<GrantedAuthority> grantedAuthorities) {
        this.user = user;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getName();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return user.isEnabled();
    }

    public void setUser(KpnUser user) {
        this.user = user;
    }

    public KpnUser getUser() {
        return user;
    }

    public String getFirstName() {
        return user.getName();
    }

    public String getLastName() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Long getId() {
        return user.getId();
    }
}
