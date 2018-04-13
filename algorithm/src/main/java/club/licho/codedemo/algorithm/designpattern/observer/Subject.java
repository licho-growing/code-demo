package club.licho.codedemo.algorithm.designpattern.observer;

/**
 * 代表一个状态可被改变的主题，用来让观察者进行订阅
 * @author licho
 * @ClassName: Subject
 * @Description:
 * @date 2018-04-10 0:02
 */
public interface Subject {
    /**
     * 订阅主题
     * @param observer
     * @return
     */
    boolean register(Observer observer);

    /**
     * 取消订阅。
     * @param observer
     * @return
     */
    boolean remove(Observer observer);

    /**
     * 通知所有的观察者
     * @param args
     */
    void notifyAll(Object args);

    /**
     * 标志状态改变了
     */
    void setChanged();
}
