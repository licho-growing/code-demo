package club.licho.springdemo.bean.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

import club.licho.springdemo.bean.config.DataSourceChanged;
import club.licho.springdemo.bean.config.DynamicDataSource;

/**
 * ClassName:定义增强类，对于可能标注@DataSourceChanged的注解的方法进行拦截,在该方法执行前对数据源进行切换。
 *
 * @author licho
 * @create 2018-05-13 10:42
 */
@Aspect
public class DataSourceAutoChanged implements Ordered{
    private static final Logger log= LoggerFactory.getLogger(DataSourceChanged.class);
    /**
     * 在任何被@DataSourceChanged
     * @param joinPoint 拦截点信息。
     * 1.getArgs()获取连接点方法运行时的入参列表
     * 2.getSignature()获取连接点的方法签名对象
     * 3.getTarget()获取连接点所在的目标对象
     * 4.getThis()获取代理对象本身
     */
    @Before("within(club.licho.springdemo.service..*)"+"&& @annotation(target)")
    public void beforeAdvisor(JoinPoint joinPoint,DataSourceChanged target){
        if(log.isDebugEnabled()){
            log.debug("动态切换数据源对连接点"+joinPoint.toLongString()+"进行了前置拦截，获取的拦截参数为:{}"+target.toString());
        }
        DynamicDataSource.changeDataSource(target);
        if(log.isDebugEnabled()){
            log.debug("动态切换数据源对连接点"+joinPoint.toLongString()+"前置拦截结束");
        }


    }

    /**
     * 不管目标方法是正常执行还是返回了异常，都需要清除之前的数据源改变信息。
     * @param joinPoint
     */
    @After("within(club.licho.springdemo.service..*)"+"&& @annotation(target)")
    public void afterAdvisor(JoinPoint joinPoint,DataSourceChanged target){
        if(log.isDebugEnabled()){
            log.info("动态切换数据源对当前数据源进行清除的final增强开始，连接点为："+joinPoint.toLongString());
        }

        DynamicDataSource.clearChanged();
        if(log.isDebugEnabled()){
            log.info("动态切换数据源对当前数据源进行清除的final增强结束、连接点为："+joinPoint.toLongString());
        }

    }

    /**
     * 事务管理器的连接点优先级是Ordered.LOWEST_PRECEDENCE,我们只需要优先级比其高即可。
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE-1;
    }
}
