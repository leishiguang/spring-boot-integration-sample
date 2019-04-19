package com.supermap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * HomeTestController 测试
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Controller
public class HomeTestController {

    @RequestMapping("/webhome")
    public String home(ModelMap modelMap) {

        modelMap.put("name", "Hello Web");

        List<String> list = new ArrayList<>();
        list.add("web a");
        list.add("web b");
        list.add("web c");
        list.add("web d");
        modelMap.put("list", list);

        return "homeweb";
    }
}
