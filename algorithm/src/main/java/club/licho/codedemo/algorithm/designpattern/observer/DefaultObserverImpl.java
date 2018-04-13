package club.licho.codedemo.algorithm.designpattern.observer;

import club.licho.codedemo.algorithm.sort.IArraySort;

/**
 * ClassName:DefaultObserverImpl
 *
 * @author licho
 * @create 2018-04-10 0:10
 */
public class DefaultObserverImpl implements Observer {
    private Subject subject;//保存自己监听的主题对象的引用，真正开发中也可以不需要。
    public DefaultObserverImpl(Subject subject){
        this.subject=subject;
        subject.register(this);
    }
    @Override
      public <IArraySort> void update(Subject subject, IArraySort obj) {

        //1.1通过pull的方式获取主题状态。
        if(subject instanceof DefaultSubjectImpl) {
            DefaultObserverImpl cast = DefaultObserverImpl.class.cast(subject);
            //1.2通过cast去获取具体的数据。cast.getter()
        }
        //2.1通过pull的方式来获取
        //2.2将obj转化成相关对象，然后获取数据
    }
}
