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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.text.MessageFormat;
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

    @RequestMapping(method = RequestMethod.GET)
    public String listDirectory(ModelMap modelMap) {
        modelMap.put("directoryModels", converter.convertDirectories(repositoryManager.findDirectories()));
        return "/directory/list";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDirectory(DirectoryModel directoryModel, ModelMap modelMap) {
        KpnDirectory directory = new KpnDirectoryImpl();
        directory.setName(directoryModel.getName());
        repositoryManager.saveDirectory(directory);

        String baseDir = env.getProperty("base.dir");
        boolean mkdirs = new File(baseDir + "/" + directory.getId()).mkdirs();
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

        return "/download";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateDirectory(@ModelAttribute DirectoryModel directoryModel, ModelMap modelMap) throws DirectoryNotExistException {
        KpnDirectory directory = repositoryManager.findDirectoryById(directoryModel.getId());
        directory.setName(directoryModel.getName());
        repositoryManager.updateDirectory(directory);
        return "redirect:/directory/view/" + directory.getId();
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String removeDirectory(@PathVariable Long id, ModelMap modelMap) throws DirectoryNotExistException {
        KpnDirectory directory = repositoryManager.findDirectoryById(id);
        String baseDir = env.getProperty("base.dir");
        File dir = new File(MessageFormat.format("{0}/{1}", baseDir, directory.getId()));
        File[] files = dir.listFiles();

        // delete child
        for (File file : files) {
            file.delete();
        }
        dir.delete();
        repositoryManager.removeDirectory(directory);
        return "redirect:/directory/list";
    }
}
