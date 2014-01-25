package my.gov.kpn.media.biz.integration.springsecurity;

import my.gov.kpn.media.core.dao.KpnPrincipalDao;
import my.gov.kpn.media.core.dao.KpnUserDao;
import my.gov.kpn.media.core.model.KpnPrincipalRole;
import my.gov.kpn.media.core.model.KpnUser;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 1/13/14
 */
@Service("userDetailService")
@Transactional
public class KpnUserDetailService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(KpnUserDetailService.class);

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    private KpnPrincipalDao principalDao;

    @Autowired
    private KpnUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException, DataAccessException {
        log.debug("loading username: " + s);
        KpnUser user = userDao.findByUsername(s);
        Session session = sessionFactory.getCurrentSession();
        if (user == null)
            throw new UsernameNotFoundException("No such user");
        log.debug(user.getUsername() + " " + user.getPassword());
        return new KpnUserDetails(user, loadGrantedAuthoritiesFor(user));
    }

    private Set<GrantedAuthority> loadGrantedAuthoritiesFor(KpnUser user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        //load all roles which ties to user
        for (KpnPrincipalRole role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleType().name()));
        }
        log.info("load auth for " + user.getName() + "#" + user.getId());
        return grantedAuthorities;
    }


}
