package my.gov.kpn.media.biz;

import my.gov.kpn.media.biz.integration.springsecurity.KpnUserDetails;
import my.gov.kpn.media.core.model.KpnUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
public class Util {

    public static KpnUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return ((KpnUserDetails) auth.getPrincipal()).getUser();
        } else {
            return null;
        }
    }

}
