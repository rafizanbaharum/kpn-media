package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.KpnPrincipalType;
import my.gov.kpn.media.core.model.KpnUser;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
public interface KpnPrincipalDao {

        //principal

        my.gov.kpn.media.core.model.KpnPrincipal findById(Long id);

        my.gov.kpn.media.core.model.KpnPrincipal findByName(String name);

        List<my.gov.kpn.media.core.model.KpnPrincipal> findAllPrincipals();

        List<my.gov.kpn.media.core.model.KpnPrincipal> findPrincipals(String filter);

        List<my.gov.kpn.media.core.model.KpnPrincipal> findPrincipals(String filter, KpnPrincipalType type);

        List<my.gov.kpn.media.core.model.KpnPrincipal> findPrincipals(Integer offset, Integer limit);

        // cruds

        void save(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnUser user);

        void update(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnUser user);

        void deactivate(my.gov.kpn.media.core.model.KpnPrincipal principal, KpnUser user);

    }
