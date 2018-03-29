package club.licho.algorithm.test;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * ClassName:Demo
 *
 * @author licho
 * @create 2018-03-29 13:36
 */
public class Demo {
    @Test
    public void testOptional(){
        String flag = "1,2,3";
        String flag2 = null;
        String[] splitResult = Optional.ofNullable(flag).orElse("").split(",");
        //虽然为null，但是返回""空字符串，不为null。
        String[] splitResult2 = Optional.ofNullable(flag2).orElse("").split(",");
        splitResult2=Optional.ofNullable(flag2).isPresent() ? flag2.split(",") :null;
        assertEquals(3, splitResult.length);
        assertNull(splitResult2);
    }
}
