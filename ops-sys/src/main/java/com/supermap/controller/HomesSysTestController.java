package com.supermap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomesSysTestController {

    @RequestMapping("/homesys")
    public String home(ModelMap modelMap) {

        modelMap.put("name", "Magical Sam");

        List<String> list = new ArrayList<>();
        list.add("homesys a");
        list.add("sam b");
        list.add("sam c");
        list.add("sam d");
        modelMap.put("list", list);

        return "homesys";
    }
}
