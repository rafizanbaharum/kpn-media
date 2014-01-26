package my.gov.kpn.media.web.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger log = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    private String handleException() {
        return "/error"; // TODO error page
    }
}
