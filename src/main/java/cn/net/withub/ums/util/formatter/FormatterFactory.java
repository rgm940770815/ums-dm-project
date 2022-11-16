/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.util.formatter;

import cn.net.withub.ums.util.formatter.impl.JsonFormatterFactoryImpl;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Diluka
 */
public abstract class FormatterFactory {

    public abstract <T extends Formatter> T getFormatter(String name);

    private static final Map<String, FormatterFactory> catalog = new HashMap<>();

    static {
        catalog.put("Json", new JsonFormatterFactoryImpl());
    }

    public static <T extends FormatterFactory> T getFactory(String name) {
        return (T) catalog.get(name);
    }
}
