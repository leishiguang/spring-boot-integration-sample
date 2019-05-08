package com.supermap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页Controller
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * 重定向主页
     */
    @RequestMapping({"", "index"})
    public String index() {
        return "redirect:/views/index.html";
    }


}
