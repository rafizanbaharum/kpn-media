package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.core.model.KpnDirectory;
import my.gov.kpn.media.core.model.impl.KpnDirectoryImpl;
import my.gov.kpn.media.web.converter.Converter;
import my.gov.kpn.media.web.model.DirectoryModel;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Controller
@RequestMapping("/repository")
public class RepositoryController {

    private static final Logger log = Logger.getLogger(RepositoryController.class);

    @Autowired
    private RepositoryManager repositoryManager;

    @Autowired
    private Converter converter;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listDirectory() {
        return "/repository/directory/list";
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String viewDirectory() {
        return "/repository/directory/view";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addDirectory() {
        return "/repository/directory/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDirectory(DirectoryModel directoryModel, ModelMap modelMap) {
        KpnDirectory directory = new KpnDirectoryImpl();
        directory.setName(directoryModel.getName());
        directory.setCode(RandomStringUtils.randomAlphanumeric(10));  // generate unique code
        repositoryManager.saveDirectory(directory);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeDirectory() {
        return "/repository/directory/remove";
    }

}
