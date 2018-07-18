package club.codedemo.spring.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;

import hibernate.bean.Course;
import hibernate.bean.Teacher;
import hibernate.bean.User;
import hibernate.factory.SessionContext;

/**
 * ClassName:HibernateContextStartUp
 *
 * @author licho
 * @create 2018-05-22 13:44
 */
public class HibernateContextStartUp {
    private SessionFactory factory;
    private Session session;
    private StandardServiceRegistry build;
    private Integer count=0;
    @AfterSuite
    public void destoryFactory(){
        StandardServiceRegistryBuilder.destroy(build);//容器启动失败优雅的关闭
    }
    @BeforeSuite
    public void initSessionFactory(){
        //通过builder可以添加自定义类加载器或者手工添加一个集成器
        build = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        factory=new MetadataSources(build).addAnnotatedClass(User.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Teacher.class)
                .buildMetadata().buildSessionFactory();
    }
    @BeforeMethod
    public void initSession(){
        System.out.println("init:"+count++);
        session=factory.openSession();
        session.beginTransaction();
    }
    @AfterMethod
    public void commitSession(){
        System.out.println("commit"+count++);
        session.getTransaction().commit();
    }
    @Test
    public void startContext() {
        User user = new User();
        user.setName("lichon");
        user.setPass("123456");
        user.setDelFlag(false);
        user.setCreateTime(new Date());
        user.setAssociateCount("17770030629");
        user.setUpdateTime(new Date());
        session.save(user);
    }
    @Test
    public void test2(){
        try{
            User user = session.byId(User.class).load("ac1018b3-6386-19be-8163-8689c0a90000");
            System.out.println(user.getClass().toString());
        }catch (Exception e){
            session.getTransaction().rollback();
        }
    }

    /**
     * 测试自然ID访问
     * 1.通过自然id查找到name对应的实体ID，再根据ID查询实体数据。
     */
    @Test
    public void test3(){
        User user = session.byNaturalId(User.class).using("name", "lichon").load();
        System.out.println(user);
    }
    @Test
    public void test4(){
        User reference = session.byNaturalId(User.class).using("name", "lichon").getReference();
        System.out.println("实体类型:"+reference.getClass().toString());
        System.out.println(reference);
//        reference.setDelFlag(Boolean.FALSE);//不会真的update
        reference.setDelFlag(Boolean.TRUE);//这时会真的update
        //select-before-update,在更新之前会先查询，判断是否真的要进行更新。
        session.saveOrUpdate(reference);
    }
    @Test
    public void test5(){
        String updateUser="update User AS s set s.name= :name where s.id= :id";
        session.createQuery(updateUser)
                .setString("name","lichonglin")
                .setString("id","1018b3-638c-1ae4-8163-8cfae8c70000")
                .executeUpdate();

    }
}
