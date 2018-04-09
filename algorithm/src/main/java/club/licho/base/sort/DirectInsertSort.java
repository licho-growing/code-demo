package club.licho.base.sort;

import java.util.Comparator;

/**
 * 常见插入排序(1,直接插入排序，2.希尔排序)中的一种
 * 算法思路，当前i个元素有序时，将第i+1个元素插入有序队列。只需从将第i+1个元素从第i个元素开始，依次向前比较
 * 找出第i+1个元素应该所在的位置。然后进行值交换即可。
 * 直接插入排序，O(n^2)
 */
public class DirectInsertSort implements IArraySort{
    @Override
    public <T> void sort(T[] arrays, Comparator<T> comparator) {
            //i代表准备插入的元素
            for(int i=1;i<arrays.length;i++){
                T temp=arrays[i];
                int j=i-1;
                //当前面的第j个元素大于temp时，那么将其后移，空出位置。
                while (j>-1&&comparator.compare(arrays[j],temp)>0){
                    swap(arrays,j,j+1);
                    j--;
                }
                arrays[j+1]=temp;
            }
    }
}
