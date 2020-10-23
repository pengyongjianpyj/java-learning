package com.pengyj.springbootmybatis.domain.mapper;

import com.pengyj.springbootmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-10-23 13:58
 */
@Mapper
@Repository
public interface UserXmlMapper {

    List<User> findAllUserXml();

}
