package demo.ms.user.controller;


import com.github.pagehelper.PageInfo;
import demo.ms.common.api.Response;
import demo.ms.user.api.UserFeginClient;
import demo.ms.user.api.dto.UserDTO;
import demo.ms.user.api.dto.UserQueryDTO;
import demo.ms.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-05 10:32:54.
 *
 * @author yanfeng
 * @since 2019-05-05 10:32:54
 */
@Slf4j
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("/api/v1/user")
public class UserController implements UserFeginClient {
    @Autowired
    private UserService userService;

    /**
     * 根据ID查询详情.
     *
     * @param id 主键
     * @return 详情
     */
    @Override
    @ApiOperation("获取详情")
    public Response<UserDTO> get(Integer id) {
        return Response.ok(userService.getUser(id));
    }

    /**
     * 根据条件查询列表，带分页功能.
     *
     * @param userQueryDTO 查询参数
     * @return 分页/列表
     */
    @Override
    @ApiOperation("获取列表")
    public Response<PageInfo<UserDTO>> getList(UserQueryDTO userQueryDTO) {
        return Response.ok(userService.queryList(userQueryDTO));
    }

    /**
     * 新增.
     *
     * @param userDTO 新增数据
     * @return 成功返回数据/失败返回异常
     */
  /*  @Override
    @ApiOperation("新增")
    public Response<UserDTO> add(UserDTO userDTO) {
        return Response.ok(userService.addUser(userDTO));
    }*/

    /**
     * 修改.
     *
     * @param userDTO 修改数据
     * @return 成功返回数据/失败返回异常
     */
   /* @Override
    @ApiOperation("修改")
    public Response<UserDTO> update(Integer id, UserDTO userDTO) {
        return Response.ok(userService.updateUser(id, userDTO));
    }*/
}
