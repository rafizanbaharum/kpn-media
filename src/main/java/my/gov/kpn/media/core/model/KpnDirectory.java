package my.gov.kpn.media.core.model;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 1/25/14
 */
public interface KpnDirectory extends KpnMetaObject{

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    List<KpnMedia> getMedias();

    void setMedias(List<KpnMedia> medias);

}
