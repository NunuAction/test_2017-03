package com.duotin;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apple on 16/2/18.
 */
public class TestMaven {

    public static void main(String[] args) {

        Integer x=2;
        Integer y=2;
        System.out.println(x==y);
        System.out.println(Integer.parseInt(getNumFromString("fdsfas045")));
    }

    public static void sayHello(){
        System.out.println(NumTrans.get("456"));
    }


    private static String getNumFromString(String source){
        String regex = "\\d*";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(source);

        while (m.find()) {
            if (!"".equals(m.group()))
                return m.group();
        }
        return "";
    }

}
