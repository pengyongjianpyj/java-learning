package com.pyj.web.po;

import lombok.Data;

/**
 * LoginPo
 *
 * @author pengyongjian
 * @date 2021/8/12 2:26 下午
 */
@Data
public class LoginPo {

  private String session_key;

  private String openid;

}
