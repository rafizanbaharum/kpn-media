package my.gov.kpn.media.web.controller;

import my.gov.kpn.media.core.dao.impl.DirectoryNotExistException;
import my.gov.kpn.media.core.dao.impl.MediaNotExistException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger log = Logger.getLogger(ExceptionController.class);
    private static final String ERROR_MSG = "errorMsg";

    @ResponseStatus(value = CONFLICT)
    public void handleConflict() {
        // todo
    }

    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler({DirectoryNotExistException.class, MediaNotExistException.class})
    public ModelAndView handleNotFound(HttpServletResponse response, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("exception", exception);
        mav.addObject("responseStatus", NOT_FOUND);
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("exception", exception);
        return mav;
    }
}
