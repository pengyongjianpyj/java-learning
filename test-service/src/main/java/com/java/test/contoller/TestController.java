package com.java.test.contoller;

import com.java.test.po.first.UserBaseInfoDO;
import com.java.test.service.first.UserBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pengyongjian
 * @Description:
 * @date 2019-12-31 11:30
 */
@RestController
@RequestMapping
public class TestController {
    @Autowired
    private UserBaseInfoService userBaseInfoService;

    @RequestMapping("/test")
    public String hello() throws Exception {
        List<UserBaseInfoDO> userBaseInfoS = userBaseInfoService.listAll();
        List<UserBaseInfoDO> userBaseInfoDOS = userBaseInfoService.listAllDO();
        UserBaseInfoDO userBaseInfoDO = userBaseInfoDOS.get(0);
        return "hello world!" + userBaseInfoDO;
    }
}
