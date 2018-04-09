package club.licho.base.sort;

import java.util.Comparator;

import javax.validation.constraints.NotNull;

public interface IArraySort {
    /**
     * 对arrays数组进行比较
     * @param arrays
     * @param comparator
     */
    <T> void sort(T[] arrays, Comparator<T> comparator);

    /**
     * 将数组中的两个值进行交换
     * @param arrays
     * @param pos1
     * @param pos2
     * @param <T>
     */
    default <T> void swap( T[] arrays, int pos1, int pos2){
      if(arrays==null || arrays.length<=0 || pos1<0||pos2<0)
          throw new RuntimeException("参数异常!");
      if(pos1>arrays.length-1||pos2>arrays.length-1)
          throw new ArrayIndexOutOfBoundsException("pos1或者pos2参数造成数据越界");
      T temp=arrays[pos1];
      arrays[pos1]=arrays[pos2];
      arrays[pos2]=temp;
    }
}
