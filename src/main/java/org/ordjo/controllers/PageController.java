package org.ordjo.controllers;

import org.ordjo.model.StatusUpdate;
import org.ordjo.service.StatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @Autowired
    StatusUpdateService statusUpdateService;

    @RequestMapping("/")
    ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("app.homepage");
        StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
        modelAndView.getModel().put("statusUpdate", latestStatusUpdate);

        return modelAndView;
    }

    @RequestMapping("/about")
    String about() {
        return "app.about";
    }
}
