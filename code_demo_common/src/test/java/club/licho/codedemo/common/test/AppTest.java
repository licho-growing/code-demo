package club.licho.codedemo.common.test;

import com.google.common.collect.Maps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        String b=new String("abc");
        String c="abc";
        String d="abc";
        System.out.println(b==c);
        System.out.println(c==d);
    }
    @Test
    public void test(){
        Map<String,String> map= Maps.newHashMap();
        map.put("1","1243");
        map.put("2","23435");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            iterator.remove();
        }
        System.out.println(map.size());
    }
}
