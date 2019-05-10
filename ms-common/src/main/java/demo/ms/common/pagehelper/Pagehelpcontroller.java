package demo.ms.common.pagehelper;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pagehelpcontroller {
    /**
     * 分页注解controller层
     */
}

