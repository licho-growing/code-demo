package club.licho.codedemo.algorithm.designpattern.observer;

/**
 * @author licho
 * @ClassName: Observer
 * @Description: 通用观察者接口
 * @date 2018-04-10 0:02
 */
public interface Observer {
    /**
     * 当主题状态改变时，通过该方法获取到通知。
     * @param subject   发出通知的subject对象
     * @param obj   需要传递的信息，也可以为null，这是从subject中获取信息的两种不同方式，push--obj,pull---->subject
     */
   <T> void update(Subject subject,T obj);
}
