package com.supermap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SysTestController {

    /**
     * freemarker Demo
     * 模板文件在 ops-web 包中
     */
    @RequestMapping("/syshome")
    public String syshome(ModelMap modelMap) {
        modelMap.put("name", "This is a name");
        List<String> list = new ArrayList<>();
        list.add("First Element");
        list.add("Second");
        list.add("第三个");
        list.add("四号Element");
        modelMap.put("list", list);
        return "homesys";
    }

    /**
     * layui Demo
     */
    @RequestMapping("/layuidemo")
    public String layuidemo(){
        return "layuidemo";
    }
}
