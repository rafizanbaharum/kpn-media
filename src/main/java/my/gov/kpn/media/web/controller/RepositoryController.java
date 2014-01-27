package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.biz.manager.RepositoryManager;
import my.gov.kpn.media.web.converter.Converter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Controller
@RequestMapping("/repository")
public class RepositoryController {

    private static final Logger log = Logger.getLogger(RepositoryController.class);

    @Autowired
    private Environment env;

    @Autowired
    private RepositoryManager repositoryManager;

    @Autowired
    private Converter converter;


}
