package com.tracker.tracker.controllers.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController implements ErrorController {
    @ExceptionHandler({
            NoResourceFoundException.class,
    })
    public ModelAndView notFoundException(Exception e) {
        log.error("Error message(404) " + e.getMessage() + ". Class: " + e.getClass().getSimpleName());
        return new ModelAndView("errorstempl/not-found");
    }

    @ExceptionHandler({
            NullPointerException.class,
    })
    public ModelAndView internalServerErrorException(Exception e) {
        log.error("Error message(500) " + e.getMessage() + ". Class: " + e.getClass().getSimpleName());
        return new ModelAndView("errorstempl/internal-server-error");
    }

    @ExceptionHandler({
            NotReadablePropertyException.class,
    })
    public ModelAndView otherException(Exception e) {
        log.error("Other error message " + e.getMessage() + ". Class: " + e.getClass().getSimpleName());
        return new ModelAndView("errorstempl/other-error");
    }
}
