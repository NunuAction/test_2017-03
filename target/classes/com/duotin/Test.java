package com.duotin;

import com.duotin.util.HttpUtils;
import com.duotin.util.NumberUtils;
import com.duotin.util.VersionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by apple on 16/3/31.
 */
public class Test {

    private static final String postUrl="http://fs02.duotin.com/file/storage/delete";


    public static void main(String[] args) {

        Map<String,String> para=new HashedMap();
        para.put("url","http://c5.duotin.com/c206/M03/02/9A/wKgBfVhvARqAK770ACKuyP7U1Qw529.mp3");

        String result=HttpUtils.post(postUrl,para);

        Integer begin=result.indexOf("http");
        Integer end=result.indexOf(".mp3");


        String resultUrl= result.substring(begin,end+4);

        String s= StringEscapeUtils.unescapeJava(resultUrl);

        System.out.println(result);

        for (String s1 : para.keySet()) {

        }

        for (int i = 0; i < 10; i++) {

        }

        

    }

}
