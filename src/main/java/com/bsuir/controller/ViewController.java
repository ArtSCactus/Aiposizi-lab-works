package com.bsuir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Controller
public class ViewController {
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        String message = "Hello Spring Boot + JSP";

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/view" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {

        model.addAttribute("message","view");

        return "index";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model){
        return "index";
    }
}
