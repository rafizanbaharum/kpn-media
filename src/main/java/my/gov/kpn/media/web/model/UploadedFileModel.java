package my.gov.kpn.media.web.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
public class UploadedFileModel {

    private Long directoryId;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Long directoryId) {
        this.directoryId = directoryId;
    }
}
