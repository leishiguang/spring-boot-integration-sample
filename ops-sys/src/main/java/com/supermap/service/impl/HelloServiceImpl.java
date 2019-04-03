package com.supermap.service.impl;

import com.supermap.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * 测试Service

 * @author leishiguang
 * @date 2019/03/10
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        if(name == null || "".equals(name.trim())){
            throw new RuntimeException("Parameter is null");
        }
        System.out.println("Hello "+name);
    }
}
