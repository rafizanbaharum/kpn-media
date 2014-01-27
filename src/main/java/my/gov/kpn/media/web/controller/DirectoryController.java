package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.core.dao.impl.DirectoryNotExistException;
import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.core.model.impl.KpnDirectoryImpl;
import my.gov.kpn.media.web.converter.Converter;
import my.gov.kpn.media.web.model.DirectoryModel;
import my.gov.kpn.media.web.model.MediaModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Controller
@RequestMapping("/directory")
public class DirectoryController {

    private static final Logger log = Logger.getLogger(DirectoryController.class);

    @Autowired
    private Environment env;

    @Autowired
    private RepositoryManager repositoryManager;

    @Autowired
    private Converter converter;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDirectory(DirectoryModel directoryModel, ModelMap modelMap) {
        KpnDirectory directory = new KpnDirectoryImpl();
        directory.setName(directoryModel.getName());
        repositoryManager.saveDirectory(directory);

        String baseDir = env.getProperty("base.dir");
        boolean mkdirs = new File(baseDir + "/" + directory.getName()).mkdirs();
        return "redirect:/directory/view/" + directory.getId();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewDirectory(@Valid @NotNull @PathVariable(value = "id") Long directoryId, ModelMap modelMap) throws DirectoryNotExistException {
        KpnDirectory directory = repositoryManager.findDirectoryById(directoryId);
        List<KpnMedia> medias = directory.getMedias();

        DirectoryModel directoryModel = converter.convert(directory);
        List<MediaModel> mediaModels = converter.convertMedias(medias);

        modelMap.addAttribute("directoryModel", directoryModel);
        modelMap.addAttribute("mediaModels", mediaModels);

        return "/directory/view";
    }

    // TODO updateDirectory()
    //
    // TODO removeDirectory()
}
