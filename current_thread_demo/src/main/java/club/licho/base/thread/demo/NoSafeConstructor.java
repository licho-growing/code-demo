package club.licho.base.thread.demo;

import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.apache.commons.configuration.event.EventSource;

/**
 * ClassName:NoSafeConstructor
 *
 * @author licho
 * @create 2018-05-09 9:32
 */
public class NoSafeConstructor {
    public NoSafeConstructor(EventSource source){
        source.addConfigurationListener(new ConfigurationListener() {
            @Override
            public void configurationChanged(ConfigurationEvent configurationEvent) {
                //这个地方已经包含了NoSafeConstructor的隐含引用，this已经被泄露，而这时该this对象并未
                //构造完成，在多线程环境下，这就是一个不安全的对象发布
                NoSafeConstructor.this.doSomething();
            }
        });
    }
    public void doSomething(){

    }
}
