package com.duotin.util.vcar2;

import com.duotin.api.beans.RecommendType;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: wangyongxing(wangyongxing@duotin.com)
 * Date: 15-10-23 下午2:33
 *
 * @Description:
 */
public class H5RedConstants {


    public static String H5_REDIRECT_HISTORY = "duotin://forward/more";
    public static String H5_REDIRECT_CONTENT = "duotin://forward/content";
    public static String H5_REDIRECT_ALBUM = "duotin://forward/album";
    public static String H5_REDIRECT_PODCAST = "duotin://forward/podcast";
    public static String H5_REDIRECT_RADIO = "duotin://forward/radio";
    public static String H5_REDIRECT_TOPIC = "duotin://forward/topic";
    public static String H5_REDIRECT_RANK = "duotin://forward/rank";
    public static String H5_REDIRECT_NEW_ADD = "duotin://forward/albumLatestAdd";
    public static String H5_REDIRECT_PUBLIC_LISTEN = "duotin://forward/publicListening";
    public static String H5_REDIRECT_HOT_CHANNEL = "duotin://forward/hotChannel";
    public static String H5_REDIRECT_TOPICS = "duotin://forward/topics";
    public static String H5_REDIRECT_MUSIC = "duotin://forward/song";
    public static String H5_REDIRECT_SONG_LIST = "duotin://forward/songlist";
    public static String H5_REDIRECT_MUSIC_RANK_LIST = "duotin://forward/ranklist";
    public static String H5_REDIRECT_MUSIC_SECONED_CATEGORY = "duotin://forward/categoryId";
    public static String H5_REDIRECT_LIVE_SHOW = "duotin://forward/liveList";
    public static String H5_REDIRECT_ONE_LIVE = "duotin://forward/live";
    public static String H5_REDIRECT_CATEGORY = "duotin://forward/categoryList";
    public static String H5_REDIRECT_FIRST_CAR = "duotin://forward/categoryFirstCar";
    public static String H5_REDIRECT_SECOND_FM = "duotin://forward/categorySecondFM";


    public static Map<String, String> map = new HashMap<String, String>();

    /**
     * 车听宝弃用协议
     */
    public static Set<String> FM_PROTOCAL =new HashSet<String>();

    static {
        map.put("1", H5_REDIRECT_ALBUM);
        map.put("2", H5_REDIRECT_CONTENT);
        map.put("3", H5_REDIRECT_TOPIC);
        map.put("5", H5_REDIRECT_PODCAST);
        map.put("6", H5_REDIRECT_RADIO);
        map.put("7", H5_REDIRECT_RANK);
        map.put("8", H5_REDIRECT_NEW_ADD);
        map.put("9", H5_REDIRECT_PUBLIC_LISTEN);
        map.put("10", H5_REDIRECT_HOT_CHANNEL);
        map.put("11", H5_REDIRECT_TOPICS);
        map.put("12", H5_REDIRECT_MUSIC);
        map.put("13", H5_REDIRECT_SONG_LIST);
        map.put("15", H5_REDIRECT_MUSIC_SECONED_CATEGORY);
        map.put("16", H5_REDIRECT_LIVE_SHOW);
        map.put("17", H5_REDIRECT_ONE_LIVE);
        map.put("19", H5_REDIRECT_CATEGORY);
        map.put("20", H5_REDIRECT_FIRST_CAR);
        map.put("21", H5_REDIRECT_SECOND_FM);

        FM_PROTOCAL.addAll(Arrays.asList("6","7","8","9","3","11","5"));
    }

    public static String getHref(String type, String value) {
        if (RecommendType.VALUE_TYPE.contains(type)) {
            return map.get(type) + "/" + value;
        } else if (RecommendType.URL_TYPE.contains(type)) {
            return value;
        } else if (RecommendType.NONE_TYPE.contains(type)) {
            return map.get(type);
        }
        return "";
    }


}
