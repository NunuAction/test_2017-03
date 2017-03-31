package com.duotin.util.region;

import com.alibaba.fastjson.JSONObject;
import com.duotin.util.HttpUtils;
import com.duotin.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author penglai
 * @Date 2017/3/22.
 */
public class RegionUtils {

    public static final String COUNTRY = "全国";
    public static final String PROVINCE = "全省";

    /**
     * 返回地区
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public static List<String> getProvince(Double latitude, Double longitude) {
        String url = "http://api.map.baidu.com/geocoder/v2/?ak=2BRsyeb4y6vuZu2m6ABu9nqC&location="
                + latitude + "," + longitude + "&output=json";
        String postRes = HttpUtils.post(url, new HashMap<String, String>());

        JSONObject jsonObject = JSONObject.parseObject(postRes);
        JSONObject data = (JSONObject) jsonObject.get("result");
        JSONObject dataListTop = (JSONObject) data.get("addressComponent");

        String province = (String) dataListTop.get("province");
        String city = (String) dataListTop.get("city");

        List<String> list = new ArrayList<>();
        if (StringUtil.isEmpty(province)) {
            list.add(COUNTRY);
        } else {
            list.add(province.substring(0, province.length() - 1));
        }
        if (StringUtil.isEmpty(city)) {
            list.add(PROVINCE);
        } else {
            list.add(city.substring(0, city.length() - 1));
        }

        return list;
    }
}
