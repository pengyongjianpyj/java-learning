package com.java.test.service.first;

import com.java.base.service.IBaseService;
import com.java.test.po.first.UserBaseInfoDO;

import java.util.List;

/**
 * @function 功能 :first Service层业务实现接口
 * @author   创建人:
 * @date     创建日期:2019-12-31 10:39:10
 */

public interface UserBaseInfoService extends IBaseService<UserBaseInfoDO> {

    List<UserBaseInfoDO> listAllDO();

}

