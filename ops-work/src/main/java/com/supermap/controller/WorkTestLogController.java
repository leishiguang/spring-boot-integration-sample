package com.supermap.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * WorkTestController 测试日志的一些
 *
 * @author leishiguang
 * @date 2019/03/15
 */
@Controller
@RequestMapping("/worktest")
@Slf4j
public class WorkTestLogController {

    @RequestMapping("/log")
    @ResponseBody
    public String workHome() {
        log.debug("Slf4j debug 日志");
        log.info("Slf4j info 日志");
        log.error("Slf4j error 日志");
        return "Hello Work";
    }

}
