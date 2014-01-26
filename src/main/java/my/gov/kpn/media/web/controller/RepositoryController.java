package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.web.converter.Converter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String saveDirectory() {
        log.debug("Saving directory");
        return "/repository/directory/save";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeDirectory() {
        return "/repository/directory/remove";
    }

}
