package com.supermap.service.impl;

import com.supermap.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * 测试Service
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
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
