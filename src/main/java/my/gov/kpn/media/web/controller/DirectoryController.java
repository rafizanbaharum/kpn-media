package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.web.converter.Converter;
import my.gov.kpn.media.web.model.DirectoryModel;
import my.gov.kpn.media.web.model.MediaModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private RepositoryManager repositoryManager;

    @Autowired
    private Converter converter;

    @RequestMapping(value = "view/{code}", method = RequestMethod.GET)
    public String viewDirectory(@PathVariable(value = "code") String directoryCode, ModelMap modelMap) {
        KpnDirectory directory = repositoryManager.findDirectoryByCode(directoryCode);
        List<KpnMedia> medias = directory.getMedias();

        DirectoryModel directoryModel = converter.convert(directory);
        List<MediaModel> mediaModels = converter.convertMedias(medias);

        modelMap.addAttribute("directoryModel", directoryModel);
        modelMap.addAttribute("mediaModels", mediaModels);

        return "directory/view";
    }

    // TODO updateDirectory()
    //
    // TODO removeDirectory()
}
