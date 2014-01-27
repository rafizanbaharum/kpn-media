package my.gov.kpn.media.core.model.impl;

import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.core.model.KpnMetadata;

import javax.persistence.*;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 12/19/13
 */
@Table(name = "KPN_DRCT")
@Entity(name = "KpnDirectory")
public class KpnDirectoryImpl implements KpnDirectory {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_KPN_DRCT")
    @SequenceGenerator(name = "SEQ_KPN_DRCT", sequenceName = "SEQ_KPN_DRCT", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(targetEntity = KpnMediaImpl.class, mappedBy = "directory")
    private List<KpnMedia> medias;

    @Embedded
    private KpnMetadata metadata = new KpnMetadata();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<KpnMedia> getMedias() {
        return medias;
    }

    public void setMedias(List<KpnMedia> medias) {
        this.medias = medias;
    }

    public KpnMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(KpnMetadata metadata) {
        this.metadata = metadata;
    }

}

