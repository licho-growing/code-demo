package club.licho.codedemo.algorithm.designpattern;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

public class DynamicProxy implements InvocationHandler {
    private static final Logger log= LoggerFactory.getLogger(DynamicProxy.class);
    private Object obj;
    private DynamicProxy(Object obj){
        this.obj=obj;
    }
    public static DynamicProxy getDynamicProxy(Object obj){
        return new DynamicProxy(obj);
    }
    /**
     * 对方法的运行时间进行拦截。
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.debug("性能检测器开始工作,检测到方法"+method.getName()+"开始执行");
        long beginTime=System.currentTimeMillis();
        try{
            Object invoke = method.invoke(this.obj, args);
            return invoke;
        }catch (Exception e){
            log.debug("方法"+method.getName()+"调用发生异常",e);
            throw new RuntimeException();
        }finally {
            long endTime=System.currentTimeMillis();
            long total=endTime-beginTime;
            log.debug("方法"+method.getName()+"执行结束，总共耗费"+(total>1000 ? total/1000 +"秒": total+"毫秒"));
        }
    }


}
