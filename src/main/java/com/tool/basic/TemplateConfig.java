package com.tool.basic;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.Properties;

/**
 * Created by asmin on 2016/9/22.
 */
public class TemplateConfig {

    public TemplateConfig(){

    }

    private static final String TEMPLATE_CONF = "config.properties";

    private static Properties props = new Properties();

    static {
        try {
            props.load(new InputStreamReader(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(TEMPLATE_CONF),"utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return props.getProperty(key);
    }

    public static void main(String[] args){
        String abc = TemplateConfig.getValue("PicUrl");
        System.out.print(abc);
    }
}
