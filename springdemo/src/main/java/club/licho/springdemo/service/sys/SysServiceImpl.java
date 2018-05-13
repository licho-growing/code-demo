package club.licho.springdemo.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Connection;

import javax.sql.DataSource;

import club.licho.springdemo.bean.config.DataSourceChanged;
import club.licho.springdemo.bean.config.DataSourceType;

/**
 * ClassName:SysServiceImpl
 *
 * @author licho
 * @create 2018-05-13 11:19
 */
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @DataSourceChanged(type = DataSourceType.SLAVE)
    @Override
    public String getResult() {
        try{
            Connection connection = dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}
