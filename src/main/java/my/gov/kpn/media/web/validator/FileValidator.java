package my.gov.kpn.media.web.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Component("fileValidator")
public class FileValidator implements Validator {

    private static final Logger log = Logger.getLogger(FileValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object uploadedFile, Errors errors) {
        log.debug("Validating files...");

        MultipartFile file = (MultipartFile) uploadedFile;

        if (file.getSize() == 0) {
            errors.rejectValue("file", "uploadForm.selectFile",
                    "Please select a file!");
        }
    }
}
