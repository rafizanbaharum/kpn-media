package my.gov.kpn.media.core.model;

import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 11/7/13
 */
public interface KpnAudit extends KpnMetaObject {

    /**
     * @return
     */
    String getIp();

    void setIp(String ip);

    /**
     * @return
     */
    String getName();

    void setName(String name);

    /**
     * @return
     */
    Date getDateDownloaded();

    void setDateDownloaded(Date dateDownloaded);

}
