package my.gov.kpn.media.core.model;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
public interface KpnMedia extends KpnMetaObject {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    String getContentType();

    void setContentType(String contentType);

    String getFileSize();

    void setFileSize(String fileSize);

    String getPath();

    void setPath(String path);

    KpnDirectory getDirectory();

    void setDirectory(KpnDirectory directory);

    byte[] getBytes();

    void setBytes(byte[] bytes);
}
