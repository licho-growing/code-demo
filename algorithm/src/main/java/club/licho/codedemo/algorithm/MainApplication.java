package club.licho.codedemo.algorithm;

import java.util.Arrays;

import club.licho.codedemo.algorithm.utils.ArraysGenerator;


/**
 * ClassName:MainApplication
 *
 * @author licho
 * @create 2018-03-19 22:02
 */
public class MainApplication {
    public static void main(String[] args) {
        Integer[] randomArray = ArraysGenerator.getRandomArray(Integer.class, 10, 10000);
        System.out.println(Arrays.toString(randomArray));
    }
}
