package club.licho.codedemo.common.util;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:GeneratorRandomName
 *
 * @author licho
 * @create 2018-04-16 9:14
 */
public class GeneratorRandomName {
    private static Map<String,AtomicInteger> defaultName=Maps.newConcurrentMap();
    static {
        defaultName.put("messageClient",new AtomicInteger(0));
        defaultName.put("messageServer",new AtomicInteger(0));
    }
    public static String getDefaultName(String type){
        final StringBuilder builder=new StringBuilder();
       defaultName.computeIfPresent(type,(entry,value)->{
           builder.append(entry)
                   .append(value.incrementAndGet());//自增1
           return value;
       });
       return builder.toString();
    }
}
