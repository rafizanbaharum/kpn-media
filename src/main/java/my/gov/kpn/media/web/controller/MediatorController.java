package my.gov.kpn.media.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@Controller
@RequestMapping("/")
public class MediatorController {

    @RequestMapping
    public String getDashboard() {
        return "redirect:/dashboard";
    }
}
