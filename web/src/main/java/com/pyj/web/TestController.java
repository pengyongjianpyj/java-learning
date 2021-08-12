package com.pyj.web;

import com.alibaba.fastjson.JSONObject;
import com.pyj.web.po.GetPhone;
import com.pyj.web.po.LoginPo;
import com.pyj.web.util.DataDecoder;
import com.pyj.web.util.HttpUtils;
import com.pyj.web.util.ResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/6/3 6:02 下午
 */
@RestController
@Api(tags = "TestController")
public class TestController {

  private Map<String, String> map = new HashMap<>();

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String test(){
    return "hello world!";
  }

  @ApiOperation(value = "登录", httpMethod = "GET")
  @ApiParam(value = "code")
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(String code) throws Exception{
    String url = "https://api.weixin.qq.com/sns/jscode2session" +
        "?appid=wx4a0a750fc453b6cc" +
        "&secret=ac70f655c4ff21073395e767703b9fb3" +
        "&js_code=" + code +
        "&grant_type=authorization_code";
    ResponseObject responseObject = HttpUtils.doGet(url);
    // {"session_key":"yUfyE4+CY30rurIkpZKOAg==","openid":"oQLWI6NkM9x1fJDX3yxAz2-5peSM"}
    String msg = responseObject.getMsg();
    LoginPo loginPo = JSONObject.parseObject(msg, LoginPo.class);
    if (Objects.nonNull(loginPo)) {
        if (StringUtils.isEmpty(loginPo.getSession_key()) || !StringUtils.isEmpty(loginPo.getOpenid())) {
            map.put(loginPo.getSession_key(), loginPo.getOpenid());
            return loginPo.getSession_key();
        }
    }
    System.out.println(msg);
    return msg;
  }

  @RequestMapping(value = "/testPhoneNumber", method = RequestMethod.POST)
  public String testPhoneNumber(@RequestBody GetPhone getPhone, @RequestHeader String session){
    JSONObject userInfo = DataDecoder.getUserInfo(getPhone.getEncryptedData(), "yUfyE4+CY30rurIkpZKOAg==", getPhone.getIv());
    System.out.println(JSONObject.toJSONString(getPhone));
    System.out.println(JSONObject.toJSONString(userInfo));

    return "hello world!";
  }

}
