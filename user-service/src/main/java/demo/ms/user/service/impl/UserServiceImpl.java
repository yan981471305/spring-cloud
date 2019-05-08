package demo.ms.user.service.impl;

import com.github.pagehelper.PageInfo;
import demo.ms.common.exception.BaseException;
import demo.ms.user.api.dto.UserDTO;
import demo.ms.user.api.dto.UserQueryDTO;
import demo.ms.user.exception.UserErrorCodeException;
import demo.ms.user.dao.UserDao;
import demo.ms.user.entity.UserEntity;
import demo.ms.user.service.UserService;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-05 10:32:54.
 *
 * @author yanfeng
 * @since 2019-05-05 10:32:54
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

   /* @Autowired
    private ModelMapper modelMapper;*/

    /**
     * 查询  详情.
     *
     * @param id 主键
     * @return 详情
     */
    @Override
    public UserDTO getUser(Integer id) {
            UserEntity user = userDao.query(id);
        if (user == null) {
            throw new BaseException(UserErrorCodeException.USER_STATTUS);
        }

        return  null;
    }



    /**
     * 查询  列表.
     *
     * @param userQueryDTO 查询参数
     * @return 分页/列表
     */
    @Override
    public PageInfo<UserDTO> queryList(UserQueryDTO userQueryDTO) {
        // selectByQueryParams 需要自己写实现
       /* List<UserDTO> result = userDao.selectByQueryParams(userQueryDTO).stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return new PageInfo<>(result);*/return null;
    }

    /**
     * 新增  记录.
     *
     * @param userDTO 新增数据
     * @return 成功返回数据/失败返回异常
     */
 /*   @Override
    public UserDTO addUser(UserDTO userDTO) {
            UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        if (userDao.insert(user) != 1) {
            throw new BaseException(PlatformCodeEnum.PARAM_IS_NULL.getCode(),"错误");
        }
        return modelMapper.map(user, UserDTO.class);
    }*/

    /**
     * 修改  记录.
     *
     * @param id 主键
     * @param userDTO 修改数据
     * @return 成功返回数据/失败返回异常
     */
  /*  @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
            UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        user.setId(id);
        if (userDao.updateByPrimaryKey(user) != 1) {
            throw new BaseException(PlatformCodeEnum.PARAM_IS_NULL.getCode(),"错误");
        }
        return modelMapper.map(user, UserDTO.class);
    }*/
}
