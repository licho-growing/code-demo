package hibernate.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import hibernate.bean.User;

/**
 * ClassName:SessionContext
 *
 * @author licho
 * @create 2018-05-22 17:03
 */
public class SessionContext {
    private static final SessionFactory factory;
    static {
        //通过builder可以添加自定义类加载器或者手工添加一个集成器
        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        factory=new MetadataSources(build).addPackage("hibernate.bean")
                .buildMetadata().buildSessionFactory();
    }
    public static Session getCurrentSession(){
        return factory.getCurrentSession();
    }

}
