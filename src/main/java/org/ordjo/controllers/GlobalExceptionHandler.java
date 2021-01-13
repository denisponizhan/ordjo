package org.ordjo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${message.error.exception}")
    private String exceptionMessage;

    @Value("${message.error.duplicate.user}")
    private String duplicateUserMessage;

    @Value("${message.error.invalid.user}")
    private String invalidUserMessage;

    //tmp
    @ExceptionHandler(value = MultipartException.class)
    @ResponseBody
    String fileUploadHandler(Exception e) {
        System.out.println("===================== ************ =====================");
        System.out.println("===================== ************ =====================");
        System.out.println("===================== ************ =====================");
        System.out.println("===================== ************ =====================");
        System.out.println("===================== ************ =====================");
        e.printStackTrace();
        System.out.println("===================== ************ =====================");
        System.out.println("===================== ************ =====================");
        System.out.println("===================== ************ =====================");
        System.out.println("===================== ************ =====================");
        System.out.println("===================== ************ =====================");

        return "An error occurred while uploading a file";
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("message", exceptionMessage);
        modelAndView.setViewName("app.exception");
        return modelAndView;
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ModelAndView duplicateUserHandler(HttpServletRequest req, DataIntegrityViolationException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("message", duplicateUserMessage);
        modelAndView.setViewName("app.exception");
        return modelAndView;
    }

    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    public ModelAndView invalidUserHandler(HttpServletRequest req, InvalidDataAccessApiUsageException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("message", invalidUserMessage);
        modelAndView.setViewName("app.exception");
        return modelAndView;
    }
}
