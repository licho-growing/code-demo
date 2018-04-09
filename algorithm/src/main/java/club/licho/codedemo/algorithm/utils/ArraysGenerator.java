package club.licho.codedemo.algorithm.utils;

import org.apache.commons.collections.CollectionUtils;
import org.jdom.output.DOMOutputter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * 数组自动生层器
 */
public class ArraysGenerator {
    public static HashSet<Class> validType=new HashSet<>();
    public static final int defaultMaxNum=10000;
    static {
        validType.addAll(Arrays.asList(Long.class,Integer.class,Short.class, Double.class,Byte.class));
    }
    /**
     * 随机生成数字类型的数组
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Number> T[] getRandomArray(Class<T> clazz, int size, int maxNums){
        if(size<=0)
            throw new RuntimeException("随机生成的数组大小不能为0");
        if(!validType.contains(clazz))
            throw new RuntimeException("不支持的随机生成类型");
        ArrayList<T> list=new ArrayList<>();
        Random random = new Random(new Date().getTime());
        for(int i=0;i<size;i++){
            if(clazz.equals(Long.class)){
                long l = random.nextLong();
                list.add((T)Long.valueOf(l));
            }else if(clazz.equals(Integer.class)){
                int i1 = random.nextInt(maxNums);
                list.add((T)Integer.valueOf(i1));
            }else if(clazz.equals(Short.class)){
                int i1 = random.nextInt(Short.MAX_VALUE + 1);
                list.add((T)Short.valueOf(Integer.toString(i1)));
            }else if(clazz.equals(Byte.class)){
                int i1=random.nextInt(Byte.MAX_VALUE+1);
                list.add((T)Byte.valueOf(Integer.toString(i1)));
            }else if(clazz.equals(Double.class)){
                Double d=random.nextDouble()*maxNums;
                list.add((T)d);
            }
        }
        T[] result = list.toArray((T[])Array
                .newInstance(clazz, size)) ;
        return result;
    }
}
