package com.duotin;

import com.duotin.util.VersionUtils;
import org.junit.*;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 *
 * 版本比较单元测试
 *
 * Created by apple on 16/4/5.
 */
public class VersionUtilsTest {

    @org.junit.Test
    public void testCompare() {
        String version1="3.3.1";
        String version2="3.3.1";
        Integer result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==0,"版本比较出错");

        version1="3.3.1";
        version2="2.3.1";
        result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==1,"版本比较出错");

        version1="3.3.1";
        version2="4.3.1";
        result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==-1,"版本比较出错");

        version1="3.3.a";
        version2="4.3.1";
        result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==-1,"版本比较出错");

        version1="3.3.1";
        version2="4.a.1";
        result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==-1,"版本比较出错");

        version1="3.3.1";
        version2="3.3.1.1";
        result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==-1,"版本比较出错");

        version1="3.3.1.1.2";
        version2="3.3.1.1";
        result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==1,"版本比较出错");

        version1="3";
        version2="3.3";
        result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==-1,"版本比较出错");

        version1="3";
        version2="3";
        result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==0,"版本比较出错");

        version1="2.22.4";
        version2="3.1.2";
        result= VersionUtils.compare(version1, version2);
        Assert.isTrue(result==-1,"版本比较出错");

        System.out.println("hello");
    }
}
