package demo.ms.user.api.dto;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * 版      权 :  com.each
 * 包      名 : com.grass.acct.org.dto
 * 描      述 :  请求参数
 * 创  建  人 :  yanfeng
 *
 * @author yanfeng
 * 创建 时 间:  2019-05-05 10:32:54
 */

@Getter
@Setter
@ToString
public class UserDTO {


    /**
     *
     */
    @ApiModelProperty(value = "姓名")
    private String name;


    /**
     *
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;


}
