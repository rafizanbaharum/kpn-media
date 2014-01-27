package my.gov.kpn.media.core.dao.impl;

/**
 * @author : alif haikal razak
 */
public class DirectoryNotExistException extends Exception {

    private static final long serialVersionUID = 7996516744853733268L;

    private static final String MESSAGE_FORMAT = "Directory '%d' does not exist";

    public DirectoryNotExistException(Long directoryId) {
        super(String.format(MESSAGE_FORMAT, directoryId));
    }
}
