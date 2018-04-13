package club.licho.codedemo.algorithm.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:DefaultSubjectImpl
 *
 * @author licho
 * @create 2018-04-10 0:12
 */
public class DefaultSubjectImpl implements Subject {
    private List<Observer> observerList=new ArrayList<>();
    private boolean changed=false;
    @Override
    public boolean register(Observer observer) {
        boolean add = observerList.add(observer);
        return add;
    }

    @Override
    public boolean remove(Observer observer) {
        if(observerList.contains(observer)){
             return observerList.remove(observer);
        }
        return false;
    }

    @Override
    public void notifyAll(Object args) {
        if(!changed)
            return;
        for (Observer observer:observerList){
            observer.update(this,args);
        }
    }

    @Override
    public void setChanged() {
        this.changed=true;
    }
}
