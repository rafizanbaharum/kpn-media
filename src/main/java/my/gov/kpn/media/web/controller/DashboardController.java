package my.gov.kpn.media.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @RequestMapping(method = RequestMethod.GET)
    public String dashboard() {
        return "/dashboard";
    }
}
