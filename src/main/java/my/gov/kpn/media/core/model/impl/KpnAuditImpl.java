package my.gov.kpn.media.core.model.impl;

import my.gov.kpn.media.core.model.KpnAudit;
import my.gov.kpn.media.core.model.KpnMetadata;

import javax.persistence.*;
import java.util.Date;

/**
 * @author rafizan.baharum
 * @since 12/19/13
 */
@Table(name = "KPN_AUDT")
@Entity(name = "KpnAudit")
public class KpnAuditImpl implements KpnAudit {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_KPN_AUDT")
    @SequenceGenerator(name = "SEQ_KPN_AUDT", sequenceName = "SEQ_KPN_AUDT", allocationSize = 1)
    private Long id;

    @Column(name = "IP")
    private String ip;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE_DOWNLOADED")
    private Date dateDownloaded;

    @Embedded
    private KpnMetadata metadata = new KpnMetadata();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateDownloaded() {
        return dateDownloaded;
    }

    public void setDateDownloaded(Date dateDownloaded) {
        this.dateDownloaded = dateDownloaded;
    }

    public KpnMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(KpnMetadata metadata) {
        this.metadata = metadata;
    }
}

