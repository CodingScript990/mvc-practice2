package org.example.controller;

import org.example.annotation.Controller;
import org.example.annotation.RequestMapping;
import org.example.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HealthCheckController Class -> Controller Call
@Controller
public class HealthCheckController {
    // RequestMapping -> /health (path) 인데, method 는 RequestMethod Get 요청!
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    // HTTP Request, Response method -> Dependency HTTP Servlet Req, Res add
    public String home(HttpServletRequest req, HttpServletResponse res) {
        // return ok
        return "ok";
    }
}
