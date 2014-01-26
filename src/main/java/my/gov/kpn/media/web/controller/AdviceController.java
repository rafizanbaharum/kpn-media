package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.web.model.DirectoryModel;
import my.gov.kpn.media.web.model.UploadedFileModel;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@ControllerAdvice
public class AdviceController {

    private static final Logger log = Logger.getLogger(AdviceController.class);


    @ModelAttribute(value = "commandDirectory")
    private DirectoryModel newDirectoryModel() {
        return new DirectoryModel();
    }

    @ModelAttribute(value = "commandFile")
    private UploadedFileModel UploadedFileModel() {
        return new UploadedFileModel();
    }
}
