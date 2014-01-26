package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.web.converter.Converter;
import my.gov.kpn.media.web.model.DirectoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @ModelAttribute(value = "commandDirectory")
    private DirectoryModel newDirectoryModel() {
        return new DirectoryModel();
    }
}
