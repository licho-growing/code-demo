package club.licho.springdemo.bean.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解只可以标注在类的方法上，表明需要更改默认数据源
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited//可继承的，方法上标注该注解，那么该类的子类覆盖该方法也会继承该注解
/**
 * 请注意，如果不标明value值，那么将会在你的默认数据源类型列表中轮询一个，如果表明了value值，那么将会使用指定的数据源名称。例如salve ,如果
 * 不存在该数据源，将会抛出异常。
 * @return
 */
public @interface DataSourceChanged {

    /**
     * 使用指定数据源名称
     * @return
     */
    String value() default "";

    /**
     * 可以指定数据源类型。如果不指定，
     * @return
     */
    DataSourceType type() default DataSourceType.MASTER;
}
