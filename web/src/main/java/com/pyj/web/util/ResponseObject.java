package com.pyj.web.util;

import lombok.Data;

@Data
public class ResponseObject {
  /**
   * 状态吗.
   */
  private String code;
  /**
   * 响应信息.
   */
  private String msg;
}
