package club.licho.codedemo.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.applet.Main;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import club.licho.codedemo.algorithm.utils.ArraysGenerator;


/**
 * ClassName:MainApplication
 *
 * @author licho
 * @create 2018-03-19 22:02
 */
public class MainApplication {
    private static final Logger log= LoggerFactory.getLogger(MainApplication.class);
    public static void main(String[] args) throws Exception{
        URL url=new URL("http://www.baidu.com");
        Reader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        CharArrayWriter writer=new CharArrayWriter();
        int temp=-1;
        while((temp=reader.read())!=-1){
            writer.write(temp);
        }

        log.info(writer.toString());
    }
}
