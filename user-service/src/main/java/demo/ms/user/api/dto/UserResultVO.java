package demo.ms.user.api.dto;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-05 10:32:54.
 *
 * @author yanfeng
 * @since 2019-05-05 10:32:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "")
public class UserResultVO extends Page {
    @ApiModelProperty(value = "")
    private String name;

    @ApiModelProperty(value = "")
    private Integer age;

}
