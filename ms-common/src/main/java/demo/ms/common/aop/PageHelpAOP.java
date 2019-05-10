package demo.ms.common.aop;

import com.github.pagehelper.PageHelper;
import demo.ms.common.utils.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class PageHelpAOP {

    //使用线程本地变量
    private static final ThreadLocal<PageBean> pageBeanContext = new ThreadLocal<>();

    //Service层切点
    @Pointcut("@annotation(demo.ms.common.pagehelper.Pagehelpservice)")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(demo.ms.common.pagehelper.Pagehelpcontroller)")
    public void controllerAspect() {
    }

    //拦截controller自定义注解
    @Before("controllerAspect()")
    public void controllerAop(JoinPoint joinPoint) throws Exception {
        log.info("Controller正在执行PageHelperAop");
        PageBean pageBean = null;

        Object[] args = joinPoint.getArgs();
        //获取类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //通过反射获取参数列表
        Map<String, Object> nameAndArgs = this.getFieldsName(this.getClass(), clazzName, methodName, args);


        pageBean = new PageBean();
        pageBean.setCurrentPage((Integer) nameAndArgs.get("currentPage"));
        pageBean.setPageSize((Integer) nameAndArgs.get("pageSize"));

        //将分页参数放置线程变量中
        pageBeanContext.set(pageBean);
    }

    //拦截自定义注解
    @Before("serviceAspect()")
    public void serviceImplAop() {
        log.info("Service正在执行PageHelperAop");
        PageBean pageBean = pageBeanContext.get();
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
    }

    /**
     * 通过反射获取参数列表
     *
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            map.put(attr.variableName(i + pos), args[i]);
        }
        return map;
    }

}

