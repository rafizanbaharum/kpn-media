package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.core.model.impl.KpnMediaImpl;
import my.gov.kpn.media.web.model.UploadedFileModel;
import my.gov.kpn.media.web.validator.FileValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    private static final Logger log = Logger.getLogger(FileController.class);

    private static final int ONE_MB = 1024;

    @Autowired
    private RepositoryManager repositoryManager;

    @Autowired
    private FileValidator fileValidator;

    @RequestMapping("/form")
    public String getUploadForm(
            @ModelAttribute("uploadedFile") UploadedFileModel uploadedFile,
            BindingResult result) {
        return "";
    }

    @RequestMapping("/upload")
    public ModelAndView upload(
            @ModelAttribute("uploadedFile") UploadedFileModel uploadedFile,
            BindingResult result, MultipartHttpServletRequest request, HttpServletResponse response) {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile mpf = null;

        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            mpf = request.getFile(fileName);

            fileValidator.validate(mpf, result);

            if (result.hasErrors()) {
                return new ModelAndView("upload_form");
            }

            try {
                // TODO Use manager
                // create media
                KpnMedia media = new KpnMediaImpl();
                media.setName(mpf.getOriginalFilename());
                media.setFileSize(mpf.getSize() / ONE_MB + " Kb");
                media.setContentType(mpf.getContentType());
                media.setBytes(mpf.getBytes());
                // TODO
                media.setDescription("this is description");
                media.setPath("/fake/path");
                media.setDirectory(null);

                log.debug("OK!");

                File file = new File("C:/kpn_media/" + mpf.getOriginalFilename());
                if (!file.exists()) {
                    boolean newFile = file.createNewFile();
                    if (newFile) {
                        FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(file));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("showFile", "message", mpf.getOriginalFilename());
    }
}
