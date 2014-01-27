package my.gov.kpn.media.web.converter;

import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.web.model.DirectoryModel;
import my.gov.kpn.media.web.model.MediaModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Component("converter")
public class Converter {

    public DirectoryModel convert(KpnDirectory directory) {
        DirectoryModel model = new DirectoryModel();
        model.setId(directory.getId());
        model.setName(directory.getName());
        return model;
    }


    public MediaModel convert(KpnMedia media) {
        MediaModel model = new MediaModel();
        model.setId(media.getId());
        model.setName(media.getName());
        model.setDescription(media.getDescription());
        model.setContentType(media.getContentType());
        model.setFileSize(media.getFileSize());
        model.setPath(media.getPath());
        return model;
    }

    public List<DirectoryModel> convertDirectories(List<KpnDirectory> directories) {
        List<DirectoryModel> models = new ArrayList<DirectoryModel>();
        for (KpnDirectory directory : directories) {
            models.add(convert(directory));
        }
        return models;
    }

    public List<MediaModel> convertMedias(List<KpnMedia> medias) {
        List<MediaModel> models = new ArrayList<MediaModel>();
        for (KpnMedia media : medias) {
            models.add(convert(media));
        }
        return models;
    }
}
