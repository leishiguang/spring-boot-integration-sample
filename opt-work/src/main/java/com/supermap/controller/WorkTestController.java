package com.supermap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorkTestController {

    @RequestMapping("/workhome")
    @ResponseBody
    public String workHome(){
        return "Hello Work";
    }
}
