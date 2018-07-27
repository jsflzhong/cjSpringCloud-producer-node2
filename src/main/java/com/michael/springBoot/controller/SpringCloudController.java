package com.michael.springBoot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cj
 * springCloud测试controller
 * 2018/3/28
 */
@Controller
public class SpringCloudController {

    Logger logger = LoggerFactory.getLogger(SpringCloudController.class);

    /**
     * 测试通过eureka服务注册中心,来提供远程服务.
     * @param name 用户名 随意写
     * @return String
     * @author cj
     */
    @RequestMapping("/testHello")
    @ResponseBody
    public Object testHello(String name) {
        return "hello " + name + ",@@@this is testHello handler in ServiceProvider-node2";
    }
}
