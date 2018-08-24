package com.michael.springBoot.controller;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    //应用管理bean
    @Autowired
    ApplicationInfoManager applicationInfoManager;

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

    /**
     * 手动修改服务的状态,实现控制服务的上下线.
     * @param status 目标状态.up; down; starting; outOfService;unknown;
     * @return
     */
    @RequestMapping("/changeServiceStatus")
    @ResponseBody
    public Object changeServiceStatus(String status) {
        InstanceInfo.InstanceStatus lastStatus = applicationInfoManager.getInfo().getStatus();
        InstanceInfo.InstanceStatus instanceStatus = null;
        switch (status){
            case "up":
                instanceStatus = InstanceInfo.InstanceStatus.UP;
                break;
            case "down":
                instanceStatus = InstanceInfo.InstanceStatus.DOWN;
                break;
            case "starting":
                instanceStatus = InstanceInfo.InstanceStatus.STARTING;
                break;
            case "outOfService":
                instanceStatus = InstanceInfo.InstanceStatus.OUT_OF_SERVICE;
                break;
            case "unknown":
                instanceStatus = InstanceInfo.InstanceStatus.UNKNOWN;
                break;
            default:
                return "请求参数错误";
        }
        applicationInfoManager.getInfo().setStatus(instanceStatus);
        InstanceInfo.InstanceStatus currentStatus = applicationInfoManager.getInfo().getStatus();
        return "修改成功,修改之前的状态为:" + lastStatus +",修改后的当前状态为:" + currentStatus;
    }
}
