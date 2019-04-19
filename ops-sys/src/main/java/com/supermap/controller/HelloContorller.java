package com.supermap.controller;

import com.supermap.annotation.Hello;
import com.supermap.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * AOP 模板
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Controller
@RequestMapping("/hello")
public class HelloContorller {

    private final HelloService helloService;

    @Autowired
    public HelloContorller(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * 方法 AOP
     */
    @RequestMapping("/say")
    @ResponseBody
    public String say(String name) {
        helloService.sayHello(name);
        return "success";
    }

    /**
     * 自定义注解，应用 AOP
     */
    @RequestMapping("/world")
    @ResponseBody
    @Hello(name = "world", type = Hello.HELLO_TYPE.ADD)
    public String world() {
        return "siccess";
    }
}
