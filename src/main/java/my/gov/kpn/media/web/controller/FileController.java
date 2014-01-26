package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.core.model.impl.KpnMediaImpl;
import my.gov.kpn.media.web.model.UploadedFileModel;
import my.gov.kpn.media.web.validator.FileValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
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
    private Environment env;

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
    public String upload(
            @ModelAttribute("uploadedFile") UploadedFileModel uploadedFile,
            BindingResult result, MultipartHttpServletRequest request, HttpServletResponse response) {

        Iterator<String> fileNames = request.getFileNames();
        log.debug("fileNames = " + Arrays.asList(fileNames));
        MultipartFile mpf = null;
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            mpf = request.getFile(fileName);

            fileValidator.validate(mpf, result);
            log.debug("mpf = " + mpf.getOriginalFilename());

            if (result.hasErrors()) {
                return "/directory/view/" + uploadedFile.getDirectoryCode();
            }

            try {
                // create media
                KpnMedia media = new KpnMediaImpl();
                media.setName(mpf.getOriginalFilename());
                media.setFileSize(mpf.getSize() / ONE_MB + " Kb");
                media.setContentType(mpf.getContentType());
                media.setBytes(mpf.getBytes());
                // TODO
                media.setDescription(null);
                media.setPath("/fake/path");

                KpnDirectory directory = repositoryManager.findDirectoryByCode(uploadedFile.getDirectoryCode());
                repositoryManager.addMedia(directory, media);

                String baseDir = env.getProperty("base.dir");
                File file = new File(MessageFormat.format("{0}/{1}/{2}", baseDir, directory.getName(), mpf.getOriginalFilename()));

                if (!file.exists()) {
                    boolean newFile = file.createNewFile();
                    if (newFile) {
                        FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(file));
                    }
                }
                log.debug(MessageFormat.format("File with name {0} has been created", mpf.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/directory/view/" + uploadedFile.getDirectoryCode();
    }

    // TODO download()
}
