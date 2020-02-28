package com.java.test.service.impl.first;

import com.java.base.mapper.IBaseMapper;
import com.java.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.test.mapper.first.UserBaseInfoDOMapper;
import com.java.test.po.first.UserBaseInfoDO;
import com.java.test.service.first.UserBaseInfoService;

import java.util.List;

/**
 * @function 功能 : first Service层业务实现
 * @author   创建人:
 * @date     创建日期:2019-12-31 10:39:10
 */
@Service
public class UserBaseInfoServiceImpl extends BaseServiceImpl<UserBaseInfoDO> implements UserBaseInfoService{

    @Autowired
    private UserBaseInfoDOMapper mapper;

    @Override
    protected IBaseMapper<UserBaseInfoDO> getBaseDao() {
        return mapper;
    }

    @Override
    public List<UserBaseInfoDO> listAllDO() {
        List<UserBaseInfoDO> userBaseInfoDOS = mapper.listAllDO();
        return userBaseInfoDOS;
    }
}

