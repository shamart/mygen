package com.github.utils;

import freemarker.template.Configuration;

public class FtlConfUtil {

    public static Configuration getConf() {
        Configuration configuration
                = new Configuration(Configuration.getVersion());
        configuration.setClassLoaderForTemplateLoading(FtlConfUtil.class.getClassLoader(), "/");
        configuration.setDefaultEncoding("utf-8");
        return configuration;
    }
}
