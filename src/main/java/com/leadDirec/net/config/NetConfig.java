package com.leadDirec.net.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangdao
 */
public class NetConfig {

    public static final int DEFAULT_WORK_THREADS = 0;

    public static final String KEY_WORK_THREADS = "workThreads";

    public static final int DEFAULT_BOSS_THREADS = 1;

    public static final String KEY_BOSS_THREADS = "bossThreads";

    public static final int DEFAULT_SO_BACKLOG = 128 * 8;

    public static final String KEY_SO_BACKLOG = "soBacklog";

    public static final String KEY_IDLE_TIMEOUT = "idleTimeout";

    public static final int DEFAULT_IDLE_TIMEOUT = 300;

    private String host;

    private int port;

    private Map<String, String> parameters;

    public NetConfig(String host, int port, Map<String, String> parameters) {
        this.host = host;
        this.port = port;
        if (parameters == null) {
            parameters = new HashMap<String, String>();
        }
        this.parameters = parameters;
    }

    public NetConfig(Map<String, String> parameters) {
        if (parameters == null) {
            parameters = new HashMap<String, String>();
        }
        this.parameters = parameters;
    }

    /**
     * 获取host
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置host
     *
     * @param host
     *            the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取port
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * 设置port
     *
     * @param port
     *            the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    public int getParameterInt(String key) {
        String v = getParameterStr(key);
        if (v == null) {
            return 0;
        }
        try {
            return Integer.parseInt(v);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getParameterInt(String key, int defaultValue) {
        String v = getParameterStr(key);
        if (v == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(v);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getParameterStr(String key) {
        return parameters.get(key);
    }

    public String getParameterStr(String key, String defaultValue) {
        String v = getParameterStr(key);
        if (v == null) {
            return defaultValue;
        }
        return v;
    }
}
