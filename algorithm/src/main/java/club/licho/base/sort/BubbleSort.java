package club.licho.base.sort;

import java.util.Comparator;

/**
 * 冒泡排序，时间复杂度为O(n^2),适合在数据量比较小的时候使用
 */
public class BubbleSort implements IArraySort {
    @Override
    public <T> void sort(T[] arrays, Comparator<T> comparator) {
        if(arrays==null||arrays.length<1)
            throw new RuntimeException("数组不能为空");
        for(int i=0;i<arrays.length-1;i++){
            boolean flag=true;
            for(int j=0;j<arrays.length-i-1;j++){
                if(comparator.compare(arrays[j],arrays[j+1])>0){
                    T temp=arrays[j];
                    arrays[j]=arrays[j+1];
                    arrays[j+1]=temp;
                    flag=false;
                }
            }
            if(flag)
                break;
        }
    }
}
