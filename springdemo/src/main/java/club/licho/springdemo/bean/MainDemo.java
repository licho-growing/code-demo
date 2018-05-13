package club.licho.springdemo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

import javax.sql.DataSource;

import club.licho.springdemo.bean.config.DataSourceChanged;
import club.licho.springdemo.bean.config.DynamicDataSource;
import club.licho.springdemo.service.sys.SysService;

/**
 * ClassName:MainDemo
 *
 * @author licho
 * @create 2018-04-26 21:57
 */
public class MainDemo {
    public static void main(String[] args)throws Exception {
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        classPathXmlApplicationContext.getBean("propertesDemo");
        System.out.println("容器初始化成功");
//        // 得到Preson，并使用
//        Person person = classPathXmlApplicationContext.getBean("person", Person.class);
//        System.out.println(person);
//        DataSource dataSource = (DataSource)classPathXmlApplicationContext.getBean("dataSource");
//        Connection connection = dataSource.getConnection();
        SysService bean = classPathXmlApplicationContext.getBean(SysService.class);
        bean.getResult();
        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext) classPathXmlApplicationContext).registerShutdownHook();
    }
}
