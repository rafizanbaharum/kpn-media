package my.gov.kpn.media.core.model;

/**
 * @author rafizan.baharum
 * @since 26/04/14
 */
public interface KpnMetaObject {

    Long getId();

    KpnMetadata getMetadata();

    void setMetadata(KpnMetadata metadata);
}
