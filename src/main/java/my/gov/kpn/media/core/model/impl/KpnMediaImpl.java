package my.gov.kpn.media.core.model.impl;

import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.core.model.KpnMetadata;

import javax.persistence.*;

/**
 * @author rafizan.baharum
 * @since 12/19/13
 */
@Table(name = "KPN_MDIA")
@Entity(name = "KpnMedia")
public class KpnMediaImpl implements KpnMedia {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_KPN_AUDT")
    @SequenceGenerator(name = "SEQ_KPN_AUDT", sequenceName = "SEQ_KPN_AUDT", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PATH")
    private String path;

    @Column(name = "MIME_TYPE")
    private String mimeType;

    @OneToOne(targetEntity = KpnDirectoryImpl.class)
    @JoinColumn(name = "DIRECTORY_ID")
    private KpnDirectory directory;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public KpnDirectory getDirectory() {
        return directory;
    }

    public void setDirectory(KpnDirectory directory) {
        this.directory = directory;
    }

    public KpnMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(KpnMetadata metadata) {
        this.metadata = metadata;
    }
}

