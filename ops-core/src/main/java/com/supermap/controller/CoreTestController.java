package com.supermap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * core 包的测试
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Controller
public class CoreTestController {

    @RequestMapping("/corehome")
    @ResponseBody
    public String coreHome() {
        return "Hello Core";
    }
}
