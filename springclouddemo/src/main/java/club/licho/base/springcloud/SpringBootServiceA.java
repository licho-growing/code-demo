package club.licho.base.springcloud;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * ClassName:club.licho.base.springcloud.SpringBootServiceA
 *
 * @author licho
 * @create 2018-05-08 22:47
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class SpringBootServiceA {
    private final Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootServiceA.class,args);
    }
    @RequestMapping("/hello")
    public String hello(){
        ServiceInstance localServiceInstance = client.getLocalServiceInstance();
        logger.info("/hello,host:"+localServiceInstance.getHost()+",port"+localServiceInstance.getPort()+",serviceId:"+localServiceInstance.getServiceId());
        return "hello";
    }
    @RequestMapping("/index")
    public String index(Map<String,String> parameters){
        return JSON.toJSONString(parameters);
    }
}
