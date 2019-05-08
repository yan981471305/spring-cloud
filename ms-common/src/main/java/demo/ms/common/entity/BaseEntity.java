package demo.ms.common.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


/**
 * 版      权 :  jariec.com
 * 包      名 : com.jariec.zmo.frame.core.entity
 * 描      述 :  BaseEntity - 持久层BaseEntity对象
 * 创建 时 间:  2018/08/05
 * <p>
 *
 * @author :
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
  /*  @Column(updatable = false)
    private Date createTime;

    *//**
     * 创建人
     *//*
    @Column(updatable = false)
    private Integer createuserid;

    *//**
     *
     *//*
    private Date updateTime;

    *//**
     * 修改人
     *//*
    private Integer updateuserid;*/
}
