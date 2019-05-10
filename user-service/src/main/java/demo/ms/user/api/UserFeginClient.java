package demo.ms.user.api;

import com.github.pagehelper.PageInfo;
import demo.ms.common.api.Response;
import demo.ms.user.api.dto.UserDTO;
import demo.ms.user.api.dto.UserQueryDTO;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "user-service",path = "/api/v1/user")
@Api(value = "user接口", description = "user相关服务")
public interface UserFeginClient {

    /**
     * 查询详情.
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("/{id}")
    Response<UserDTO> get(@PathVariable(value = "id") Integer id);

    /**
     * 查询列表/分页.
     *
     * @param userQueryDTO 查询参数
     * @return 分页/列表
     */
    @GetMapping
    Response<PageInfo<UserDTO>> getList(UserQueryDTO userQueryDTO);

    /**
     * 新增.
     *
     * @param userDTO 新增数据
     * @return 成功返回数据/失败返回异常
     */
   // @PostMapping
//    Response<UserDTO> add(@RequestBody UserDTO userDTO);

    /**
     * 修改.
     *
     * @param userDTO 修改数据
     * @return 成功返回数据/失败返回异常
     */
  //  @PutMapping("/{id}")
 //   Response<UserDTO> update(@PathVariable(value = "id") Integer id,
                        //     @RequestBody UserDTO userDTO);
}
