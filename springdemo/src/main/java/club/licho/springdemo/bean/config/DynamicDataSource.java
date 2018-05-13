package club.licho.springdemo.bean.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 支持动态数据源
 * ClassName:DynamicDataSource
 *可以根据相关配置底层自动切换到相关数据源。默认是连接master，连接从库使用slave轮询的方式。
 * AbstractRoutingDataSource是spring jdbc库中提供用来支持动态路由的。
 * @author licho
 * @create 2018-05-12 23:03
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
    private static final ThreadLocal<DataSourceChanged> dataSourceType=new ThreadLocal<>();//相当于一个开关，如果本地线程副本绑定了值
    private volatile AtomicInteger next=new AtomicInteger(0);
    /**
     * 通过该静态方法切换数据源。
     * @param sourceName
     */
    public static void changeDataSource(DataSourceChanged sourceName){
        if(sourceName==null){
            log.info("请指定需要变更的数据源名称",new NullPointerException("DataSourceChanged引用为null"));
        }
       dataSourceType.set(sourceName);
    }

    /**
     * 切换回默认数据源
     */
    public static void clearChanged(){
        log.info("清除当前线程绑定的的数据源："+dataSourceType.get()==null ? "defaultType" :dataSourceType.get().value());
        dataSourceType.remove();
    }
    //就切换到其指定的数据源。
    /**
     *
     * @return 返回最后使用的数据源的key。需要先配置AbstractRoutingDataSource的targetDataSources中注入。
     * 也可以直接指定某个DataSource.
     */
    @Override
    protected Object determineCurrentLookupKey() {
        this.log.info("当前线程信息{}",Thread.currentThread().toString());
        DataSourceChanged changedDataType = dataSourceType.get();//判断是否更换数据源
        String resultDataSource=null;//最后使用的数据源名称
        if(changedDataType!=null){
            //本次需要更换数据源
            String changedValue = changedDataType.value();
            DataSourceType type = changedDataType.type();//数据源类型
            DataSourceType[] values = DataSourceType.values();//所有的数据源类型
            for(int i=0;i<values.length;i++){
                if(values[i].equals(type)){
                    List<String> soureList = values[i].getSoureList();
                    if(changedValue!=null&&changedValue.isEmpty()){
                        resultDataSource=getRandomDataSource(soureList);
                    }else{
                        //使用指定数据源
                        assertDataSourceExist(soureList,changedValue);
                        resultDataSource=changedValue;
                    }
                    //找到所需数据源
                    break;
                }
            }
            //没找到那么使用默认数据源
        }
        this.log.info("最后选择的数据源名称为:{}",resultDataSource==null ? "defaultDataSource":resultDataSource);
        //使用默认数据源
        return resultDataSource;
    }

    /**
     * 从目标数据源列表中随机获取一个数据源
     * @return
     */
    private String getRandomDataSource(List<String> sourceList){
        //使用轮询算法获取一个随机的数据源。
        int total=sourceList.size();
        int current=next.getAndAdd(1);
        if(current<0){
            synchronized (this){
                if(current<0){
                    next=new AtomicInteger(0);//重新初始化
                }
            }
        }
        return sourceList.get(current%total);
    }

    /**
     * 判断soureList是否包含key
     * @param sourceList
     * @param key
     * @return
     */
    private void assertDataSourceExist(List<String> sourceList,String key){
        throw  new NullPointerException();
    }


}