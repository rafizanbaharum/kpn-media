package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.core.dao.impl.DirectoryNotExistException;
import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.KpnMedia;
import my.gov.kpn.media.web.converter.Converter;
import my.gov.kpn.media.web.model.DirectoryModel;
import my.gov.kpn.media.web.model.MediaModel;
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
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private Converter converter;

    @Autowired
    private RepositoryManager repositoryManager;

    @RequestMapping(method = RequestMethod.GET)
    public String dashboard(ModelMap modelMap) {
        modelMap.put("directoryModels", converter.convertDirectories(repositoryManager.findDirectories()));
        return "/dashboard";
    }

    @RequestMapping(value = "/directory/{id}", method = RequestMethod.GET)
    public String directory(@PathVariable Long id, ModelMap modelMap) throws DirectoryNotExistException {
        KpnDirectory directory = repositoryManager.findDirectoryById(id);
        List<KpnMedia> medias = directory.getMedias();
        DirectoryModel directoryModel = converter.convert(directory);
        List<MediaModel> mediaModels = converter.convertMedias(medias);

        modelMap.addAttribute("directoryModel", directoryModel);
        modelMap.addAttribute("mediaModels", mediaModels);
        return "/directory";
    }
}
