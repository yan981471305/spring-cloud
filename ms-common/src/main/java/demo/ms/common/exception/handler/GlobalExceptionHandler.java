package demo.ms.common.exception.handler;

import demo.ms.common.api.Response;
import demo.ms.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Enumeration;
import java.util.Set;

/**
 *
 * 描      述 :  GlobalExceptionHandler 全局异常处理
 * <p>
 *
 * @author :
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @Resource
    Environment environment;

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn("缺少请求参数", e);
        return Response.error("缺少请求参数");
    }

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response<?> handleHttpMessageNotReadableException(WebRequest req, HttpMessageNotReadableException e) {

        log.error("参数解析失败", e);
        return Response.error("参数解析失败");
    }

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String code = error.getDefaultMessage();
        return Response.error(code);
    }

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<?> handleServiceException(ConstraintViolationException e) {
        log.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return Response.error("参数验证失败" + message);
    }

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(BindException.class)
    public Response<?> handleBindException(BindException e) {
        log.error("参数绑定失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return Response.error("参数绑定失败=" + message);
    }

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(ValidationException.class)
    public Response<?> handleValidationException(ValidationException e) {
        log.error("参数验证失败", e);
        return Response.error("参数验证失败");
    }

    /**
     * 404 - Not Found
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Response<?> noHandlerFoundException(NoHandlerFoundException e) {
        log.error("Not Found", e);
        return Response.error("Not Found=" + e);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        return Response.error("request_method_not_supported");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("不支持当前媒体类型", e);
        return Response.error("content_type_not_supported");
    }

    /**
     * 操作数据或库出现异常
     */
    @ExceptionHandler(BaseException.class)
    public Response<?> handleBusinessException(BaseException e) {
        log.warn("message = {},body", e.getMessage(), e);
        if (StringUtils.isNotBlank(e.getErrorCode())) {
            return Response.error(e.getErrorCode(),environment.getProperty(e.getErrorCode()));
        } else {
            return Response.error(e.getMessage());
        }
    }

    /*@ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Response<?> handleAccessDeniedException(HttpServletRequest req, AccessDeniedException e) {
        log.warn("url = {},message = {}", getFullURL(req), e.getMessage());
        return Response.error("403", e.getMessage());
    }*/


    /**
     * 获取其它异常。包括500
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public Response<?> jsonErrorHandler(HttpServletRequest req, Exception e) {
        if (e instanceof BaseException) {
            return Response.error(e.getMessage());
        } else {
            log.error("head={},url = {},message = {}", this.getHeaderString(req), getFullURL(req), e.getMessage(), e);
            return Response.error(e.getMessage());
        }
    }


    private String getHeaderString(HttpServletRequest request) {
        StringBuilder headersString = new StringBuilder();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            headersString.append(headerName + ":" + request.getHeader(headerName)).append(" ");
        }
        return headersString.toString();
    }

    private String getFullURL(HttpServletRequest request) {

        StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null) {
            url.append("?");
            url.append(request.getQueryString());
        }
        return url.toString();
    }
}
