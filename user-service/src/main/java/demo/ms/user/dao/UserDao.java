package demo.ms.user.dao;


import java.util.List;

import demo.ms.common.utils.MyMapper;
import demo.ms.user.api.dto.UserQueryDTO;
import demo.ms.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-05 10:32:54.
 *
 * @author yanfeng
 * @since 2019-05-05 10:32:54
 */
@Mapper
public interface UserDao extends MyMapper<UserEntity> {
    List<UserEntity> selectByQueryParams(UserQueryDTO queryDTO);
    UserEntity query(@Param("id") Integer id);
}
