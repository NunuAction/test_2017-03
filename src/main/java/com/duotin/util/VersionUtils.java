package com.duotin.util;

/**
 * 版本处理工具类
 * <p/>
 * Created by apple on 16/4/5.
 */
public class VersionUtils {

    public static int compare(String version1Para, String version2Para) {

        if (version1Para == null || version2Para == null) {
            return -2;
        }

        String[] version1s = version1Para.split("\\.");
        String[] version2s = version2Para.split("\\.");

        int minLength = Math.min(version1s.length, version2s.length);
        for (int i = 0; i < minLength; i++) {
            if (!NumberUtils.isNumber(version1s[i]) || !NumberUtils.isNumber(version2s[i])) {
                return -2;
            }
            if (Integer.parseInt(version1s[i]) > Integer.parseInt(version2s[i])) {
                return 1;
            } else if (Integer.parseInt(version1s[i]) < Integer.parseInt(version2s[i])) {
                return -1;
            }
        }
        if (version1s.length > version2s.length) {
            return 1;
        } else if (version1s.length < version2s.length) {
            return -1;
        } else {
            return 0;
        }
    }

    public static boolean versionLessThan3(String platform, String version) {
        if ("ios".equalsIgnoreCase(platform)) {
            return VersionUtils.compare(version, "3") < 0;
        } else if ("android".equalsIgnoreCase(platform)) {
            return VersionUtils.compare(version, "3") < 0;
        }
        return true;
    }
}