package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.web.model.UploadedFileModel;
import my.gov.kpn.media.web.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Controller
public class UploadController {

    @Autowired
    private FileValidator fileValidator;

    @RequestMapping("/fileUploadForm")
    public ModelAndView getUploadForm(
            @ModelAttribute("uploadedFile") UploadedFileModel uploadedFile,
            BindingResult result) {
        return new ModelAndView("uploadForm");
    }

    @RequestMapping("/fileUpload")
    public ModelAndView fileUploaded(
            @ModelAttribute("uploadedFile") UploadedFileModel uploadedFile,
            BindingResult result) {

        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, result);
        String fileName = file.getOriginalFilename();

        if (result.hasErrors()) {
            return new ModelAndView("uploadForm");
        }

        try {
            InputStream inputStream = file.getInputStream();
            File newFile = new File("C:/kpn_media/temp" + fileName); // to do use KPN_CNFG
            if (!newFile.exists()) {
                if (newFile.createNewFile()) {
                    OutputStream outputStream = new FileOutputStream(newFile);
                    int read = 0;
                    byte[] bytes = new byte[1024];

                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("showFile", "message", fileName);
    }
}
