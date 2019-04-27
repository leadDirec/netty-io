package com.leadDirec.infra;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;


public class Configurer {
	   /* spring prop-config */
    private static Properties properties = new Properties();

    public static String getEnvConf(String jvmKey, String key) {
        String jvm = System.getProperty(jvmKey);
        String env = System.getProperty(key);
        return StringUtils.isNotEmpty(jvm) ? jvm : //
                (StringUtils.isNotEmpty(env) ? "classpath:config/spring/" + env
                        + "-env-conf.properties" : //
                        "classpath:config/spring/dev-env-conf.properties");
    }

    /* Loading configuration files, JVM and defValue, one of two */
    public static String getConfig(String key, String defValue) {
        String serconfig = System.getProperty(key);
        return StringUtils.isNotEmpty(serconfig) ? "file:" + serconfig
                : defValue;
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    public void setProperties(Properties properties) {
        Configurer.properties = properties;
    }

}
