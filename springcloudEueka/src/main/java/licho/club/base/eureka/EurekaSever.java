package licho.club.base.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ClassName:EurekaSever
 *
 * @author licho
 * @create 2018-05-08 23:18
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class EurekaSever {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaSever.class).web(true).run(args);
    }
}
