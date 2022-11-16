/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.entity.factory;

import cn.net.withub.ums.util.ReflectionTools;
import cn.net.withub.ums.util.StringTools;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 实体类工厂
 *
 * @author Diluka
 */
@Component
public class EntityFactory {

    private static final Map<String, Class<?>> catalog = new HashMap<>();
    private static Logger logger = LogManager.getLogger(EntityFactory.class);

    static {
        Set<Class<?>> entityClasses = ReflectionTools.getClasses("cn.net.withub.ums.entity", "Ums");
        for (Class<?> entityClass : entityClasses) {
            if (entityClass.getSimpleName().endsWith("View")
                    || entityClass.getSimpleName().endsWith("Criteria")
                    || entityClass.getSimpleName().endsWith("Criterion")) {
                continue;
            }

            String className = entityClass.getSimpleName();
            catalog.put(className, entityClass);
            catalog.put(className.replaceAll("^Ums", ""), entityClass);
            catalog.put(className.replaceAll("^Ums", "").substring(0, 1).toLowerCase() + className.replaceAll("^Ums", "").substring(1), entityClass);
            catalog.put(className.replaceAll("^Ums", "").toLowerCase(), entityClass);
            catalog.put(StringTools.camelOrPascalToUnderline(className.replaceAll("^Ums", "")).toLowerCase(), entityClass);
        }
    }

    /**
     * 创建实体类
     *
     * @param <T>
     * @param tableName 表名，e.g. UmsAbroadInfo or abroadInfo or AbroadInfo or
     * abroadinfo or abroad_info 均可
     * @param properties 实体的属性
     * @return
     */
    public <T> T createEntity(String tableName, Map properties) {
        try {
            T t = createEntity(tableName);
            if (t != null) {
                for (Object key : properties.keySet()) {
                    if (properties.get(key).getClass().isArray()) {
                        Object[] args = (Object[]) properties.get(key);
                        ReflectionTools.setField(t, key.toString(), args[0]);
                    } else {
                        ReflectionTools.setField(t, key.toString(), properties.get(key));
                    }
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建实体类
     *
     * @param <T>
     * @param tableName 表名，e.g. UmsAbroadInfo or abroadInfo or AbroadInfo or
     * abroadinfo or abroad_info 均可
     * @param properties 实体的属性
     * @return
     */
    public <T> T createEntity2(String tableName, Map properties) {
        Object ke = null;
        try {
            T t = createEntity(tableName);
            if (t != null) {
                for (Object key : properties.keySet()) {
                    if (properties.get(key) != null && properties.get(key).getClass().isArray()) {
                        Object[] args = (Object[]) properties.get(key);
                        try {
                            ReflectionTools.setField(t, key.toString(), args[0]);
                        } catch (NoSuchFieldException e) {
                            logger.debug("ignore field " + key.toString());
                        }
                    } else {
                        try {
                            ke = key;
                            if (key.equals("sort_no")) {
                                throw new NoSuchFieldException();
                            }
                            ReflectionTools.setField(t, StringTools.underlineToCamel(key.toString()), properties.get(key));
                        } catch (NoSuchFieldException e) {
                            logger.debug("ignore field " + key.toString());
                        }
                    }
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(ke);
            return null;
        }
    }

    /**
     * 创建空实体类
     *
     * @param <T>
     * @param tableName 表名
     * @return
     * @throws Exception 任何异常
     */
    public <T> T createEntity(String tableName) throws Exception {
        return (T) catalog.get(tableName).newInstance();
    }

}
