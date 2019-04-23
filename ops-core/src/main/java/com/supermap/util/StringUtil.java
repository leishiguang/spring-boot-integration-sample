package com.supermap.util;

/**
 * 字符串的一些帮助类
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
public class StringUtil {

    /**
     * 获取数据源的名称：[传入name]@[结尾ip]/[实例名]<br/>
     * 如：<br/>
     * <ol>
     * <li>url为cdserver:1521:testa，数据库名称为bdck@cdserver/testa</>
     * <li>url为127.0.0.1:testa，数据库名称为bdck@1/testa</>
     * </ol>
     *
     * @param prefix 前缀
     * @param url    数据库连接 url，目前仅支持 oracle
     * @return 数据源名称
     */
    public static String generateDbName(String prefix, String url) {
        StringBuilder result = new StringBuilder();
        String p = "@";
        result.append(prefix);
        String[] a = url.split(":");
        String ip = a[3];
        String[] ips = ip.split("\\.");
        String lastip = ips.length > 0 ? ips[ips.length - 1] : ip;
        if (!lastip.startsWith(p)) {
            result.append(p);
        }
        String db = a[a.length - 1];
        result.append(lastip).append("/").append(db);
        return result.toString();
    }
}
