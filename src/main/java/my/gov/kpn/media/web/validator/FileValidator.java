package my.gov.kpn.media.web.validator;

import my.gov.kpn.media.web.model.UploadedFileModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Component("fileValidator")
public class FileValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object uploadedFile, Errors errors) {
        UploadedFileModel file = (UploadedFileModel) uploadedFile;

        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "uploadForm.selectFile",
                    "Please select a file!");
        }
    }
}
