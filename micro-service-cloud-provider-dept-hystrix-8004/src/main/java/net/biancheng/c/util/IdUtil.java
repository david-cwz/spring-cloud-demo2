package net.biancheng.c.util;

/**
 * @author chenwz38
 * @Date: 2022/7/5
 */
public class IdUtil {
    private static Integer i = 1;
    public static String simpleUUID() {
        return (i++).toString();
    }
}
