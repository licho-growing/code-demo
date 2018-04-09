package club.licho.demo.algorithm.util;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

import club.licho.base.algorithm.ArraysGenerator;
import club.licho.base.designpattern.DynamicProxy;
import club.licho.base.sort.DirectInsertSort;
import club.licho.base.sort.IArraySort;
import club.licho.base.sort.SelectSort;

import  static org.junit.Assert.*;

public class DemoTest {
    private final int MAX_NUMS=100000;
    @Test
    public void test(){
        Integer[] randomArray = ArraysGenerator.getRandomArray(Integer.class, 100, 10000);
        System.out.println(randomArray);
    }
    @Test
    public void test2(){
        Integer [] randomArrasy=ArraysGenerator.getRandomArray(Integer.class,MAX_NUMS,10000);
        Integer[] buff=new Integer[MAX_NUMS];
        System.arraycopy(randomArrasy,0,buff,0,MAX_NUMS);
        DynamicProxy proxy=DynamicProxy.getDynamicProxy(new DirectInsertSort());
        IArraySort sort=(IArraySort) Proxy.newProxyInstance(DemoTest.class.getClassLoader(), new Class[]{IArraySort.class},proxy);
        sort.sort(randomArrasy,Integer::compareTo);
        Arrays.sort(buff);
        assertEquals(buff,randomArrasy);
    }
    @Test
    public void testSelectSort(){
        Integer [] randomArrasy=ArraysGenerator.getRandomArray(Integer.class,MAX_NUMS,10000);
        Integer[] buff=new Integer[MAX_NUMS];
        System.arraycopy(randomArrasy,0,buff,0,MAX_NUMS);
        DynamicProxy proxy=DynamicProxy.getDynamicProxy(new SelectSort());
        IArraySort sort=(IArraySort) Proxy.newProxyInstance(DemoTest.class.getClassLoader(), new Class[]{IArraySort.class},proxy);
        sort.sort(randomArrasy,Integer::compareTo);
        Arrays.sort(buff);
        assertEquals(buff,randomArrasy);

    }
}
