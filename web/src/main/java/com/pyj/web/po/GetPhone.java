package com.pyj.web.po;

import lombok.Data;

/**
 * GetPhone
 *
 * @author pengyongjian
 * @date 2021/8/12 1:57 下午
 */
@Data
public class GetPhone {

  private String encryptedData;

  private String iv;
}
