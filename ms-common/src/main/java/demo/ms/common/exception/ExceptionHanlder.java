package demo.ms.common.exception;

import demo.ms.common.enums.PlatformCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;


/**
 *
 * 描      述 :  ExceptionHanlder
 * <p>
 *
 * @author :
 */
@Slf4j
public class ExceptionHanlder {

    /**
     * 统一异常处理方法<br/>
     * <p>
     * 创建人：
     * 创建日期：   2018-08-05
     * 描述    :    统一异常处理方法
     * 用户名&密码
     *
     * @param e
     * @throws BaseException
     */
    public static void process(Exception e, String errorCode) throws BaseException {
        if (e instanceof BaseException) {
            log.error(e.getMessage(), e);
            throw (BaseException) e;
        } else {
            log.error(e.getMessage(), e);

            //重置errorCode
            if (StringUtils.isBlank(errorCode)) {
                errorCode = PlatformCodeEnum.SYSTEM_ERROR.getCode();
            }
            throw new BaseException(errorCode, e.getMessage());
        }
    }
}