package demo.ms.user.service;

import com.github.pagehelper.PageInfo;
import demo.ms.user.api.dto.UserDTO;
import demo.ms.user.api.dto.UserQueryDTO;

/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-05 10:32:54.
 *
 * @author yanfeng
 * @since 2019-05-05 10:32:54
 */
public interface UserService {

    /**
     * 查询  详情.
     *
     * @param id 主键
     * @return 详情
     */
    UserDTO getUser(Integer id);


    /**
     * 查询  列表.
     *
     * @param userQueryDTO 查询参数
     * @return 分页/列表
     */
    PageInfo<UserDTO> queryList(UserQueryDTO userQueryDTO);

    /**
     * 新增  记录.
     *
     * @param userDTO 新增数据
     * @return 成功返回数据/失败返回异常
     */
    //  UserDTO addUser(UserDTO userDTO);

    /**
     * 修改  记录.
     *
     * @param id      主键
     * @param userDTO 修改数据
     * @return 成功返回数据/失败返回异常
     */
    //  UserDTO updateUser(Integer id, UserDTO userDTO);
}
