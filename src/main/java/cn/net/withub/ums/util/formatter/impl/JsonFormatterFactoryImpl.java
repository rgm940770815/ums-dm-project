/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.util.formatter.impl;

import cn.net.withub.ums.util.formatter.FormatterFactory;
import cn.net.withub.ums.util.formatter.Formatter;
import java.util.HashMap;
import java.util.Map;

public class JsonFormatterFactoryImpl extends FormatterFactory {

    private static final Map<String, Formatter> catalog = new HashMap<>();

    static {
        catalog.put("FlexiGrid", new FlexiGridJsonFormatterImpl());
        catalog.put("MMGrid", new MmGridJsonFormatterImpl());
    }

    @Override
    public <T extends Formatter> T getFormatter(String name) {
        return (T) catalog.get(name);
    }

}
