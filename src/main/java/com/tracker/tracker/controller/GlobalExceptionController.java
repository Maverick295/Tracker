package com.tracker.tracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionController implements ErrorController {
    @ExceptionHandler({
            NoResourceFoundException.class

    })
    public ModelAndView notFoundException(HttpServletRequest request, Exception e) {
        return new ModelAndView("not-found")
                .addObject("url", request.getRequestURL())
                .addObject("class", e.getClass().getSimpleName())
                .addObject("exception", e.getMessage());
    }

    @ExceptionHandler({
            Exception.class,
            NullPointerException.class
    })
    public ModelAndView internalServerErrorException(Exception e) {
        return new ModelAndView("internal-server-error")
                .addObject("class", e.getClass().getSimpleName())
                .addObject("exception", e.getMessage());
    }

    @ExceptionHandler({
            Exception.class
    })
    public ModelAndView badRequestException(Exception e) {
        return new ModelAndView("bad-request")
                .addObject("class", e.getClass().getSimpleName())
                .addObject("ex", e.getMessage());
    }
}
