package club.licho.codedemo.algorithm.sort;

import java.util.Comparator;

import javax.validation.constraints.NotNull;

/**
 * 选择排序，时间复杂度为O(n^2),适用于数据量比较小的排序
 * 算法思路:
 * 遍历数组n-1次，每次找出未排序集合部分的最大元素或者最小元素放在第i个位置，i代表遍历次数[0,n-1]
 */
public class SelectSort implements IArraySort {
    @Override
    public <T> void sort( T[] arrays, Comparator<T> comparator) {
        if(arrays==null ||arrays.length==0)
            throw new RuntimeException("不能够对空数组进行排序");
        for(int i=0;i<arrays.length-1;i++){
            //找出从i到arrays.length-1中最大或者最小元素的下标
            int min=i;
            for(int j=i+1;j<arrays.length;j++){
                if(comparator.compare(arrays[j],arrays[min])<0){
                    min=j;
                }
            }
            //
            if(min!=i){
                //交换i和min位置的元素值
                swap(arrays,min,i);
            }
        }
    }
}
