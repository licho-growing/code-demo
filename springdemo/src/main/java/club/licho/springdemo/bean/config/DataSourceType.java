package club.licho.springdemo.bean.config;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 对所拥有的的数据源进行分类注册。后期增加数据源只需更改这个类即可。
 */
public enum DataSourceType {
    MASTER(Arrays.asList("master")),
    SLAVE(Arrays.asList("slave1"));
    private final List<String> soureList;
    DataSourceType(List<String> sourceNames){
        this.soureList=sourceNames==null ? Collections.emptyList():sourceNames;
    }

    public List<String> getSoureList() {
        return soureList;
    }
}
