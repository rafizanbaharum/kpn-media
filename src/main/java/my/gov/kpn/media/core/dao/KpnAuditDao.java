package my.gov.kpn.media.core.dao;

import my.gov.kpn.media.core.model.KpnAudit;
import my.gov.kpn.media.core.model.KpnUser;
import my.gov.kpn.media.core.model.impl.KpnAuditImpl;

/**
 * @author rafizan.baharum
 * @since 11/9/13
 */
public interface KpnAuditDao extends DaoSupport<Long, KpnAudit, KpnAuditImpl> {

    void save(KpnAudit audit, KpnUser user);
}
