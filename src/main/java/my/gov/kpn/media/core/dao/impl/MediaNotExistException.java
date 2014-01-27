package my.gov.kpn.media.core.dao.impl;

/**
 * @author : alif haikal razak
 */
public class MediaNotExistException extends Exception {

    private static final long serialVersionUID = 7996516744853733268L;

    private static final String MESSAGE_FORMAT = "Media '%d' does not exist";

    public MediaNotExistException(Long mediaId) {
        super(String.format(MESSAGE_FORMAT, mediaId));
    }
}
