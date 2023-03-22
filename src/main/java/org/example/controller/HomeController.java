package org.example.controller;

import org.example.annotation.Controller;
import org.example.annotation.RequestMapping;

// HomeController Class -> Controller Call
@Controller
public class HomeController {
    // RequestMapping -> / (path)
    @RequestMapping("/")
    // HTTP Request, Response method
    public String home(HttpServletRequest request, HttpServletResponse response) {
        // return home
        return "Home";
    }
}
