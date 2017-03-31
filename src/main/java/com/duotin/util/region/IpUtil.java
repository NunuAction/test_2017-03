package com.duotin.util.region;

import com.duotin.util.HttpUtils;
import com.duotin.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: guoqi
 * Date: 15/11/26
 * Time: 下午4:25
 */
public final class IpUtil {

    private static final Logger LOG = LoggerFactory
            .getLogger(IpUtil.class);

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        LOG.error("x-forwarded-for ip:"+ip);


        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");


        LOG.error("X-Real-IP ip:"+ip);

        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }


    /**
     * 根据ip获取省份
     *
     * @param ip
     * @return
     */
    public static String getProvince(String ip) {

        try {

            String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip;
            String htmlGet = HttpUtils.getHtmlGet(url);


            Map<String, Object> map = com.duotin.util.JsonUtils.toMap(htmlGet);

            Object province = map.get("province");
            if (province == null) {
                return null;
            }

            return String.valueOf(province);
        } catch (Exception ex) {
            return null;
        }


    }


}
