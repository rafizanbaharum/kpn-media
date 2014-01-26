package my.gov.kpn.media.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Controller
@RequestMapping("/gate")
public class GateController {

    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public String in(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/out", method = RequestMethod.GET)
    public String out(ModelMap model) {
        return "logout";
    }
}
