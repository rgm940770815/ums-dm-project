package cn.net.withub.ums.subitemAudit;


import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldDescribe {

    String value() default "";
}
