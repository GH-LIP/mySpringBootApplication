package com.test;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Test {

    public static void main(String[] args) throws Exception {
        String str = "http://cnas-qa.oss-cn-shenzhen.aliyuncs.com/cnas/reports/889200733826580480_02030123012_%E6%98%8E%E5%9F%BA%E5%8C%BB%E9%99%A2/KEDU20210923009%20%E5%91%BC%E5%90%B8%E6%9C%BA.pdf";
        String subStr = str.substring(str.lastIndexOf("/") + 1);
        str = URLDecoder.decode(subStr, StandardCharsets.UTF_8.name());
        System.out.println(str);
    }
}
