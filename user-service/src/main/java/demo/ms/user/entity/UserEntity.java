package demo.ms.user.entity;

import demo.ms.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;


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
@Table(name = "user")
@ApiModel(value = "用户实体")
public class UserEntity extends BaseEntity {
    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer age;

}
