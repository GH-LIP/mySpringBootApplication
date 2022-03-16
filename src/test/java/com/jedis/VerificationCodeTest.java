package com.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class VerificationCodeTest {
    private static String verificationCode_phone = "verificationCode_";
    private static String verificationCode_phone_time = "verificationCode_time_";

    // 目标： 模拟手机验证码发送，要求每天最多发送三次
    // 思路：使用redis存储验证码和已发送的次数，并进行验证
    public static void main(String[] args) {

        String phone = "189";
        String code = sendVerificationCode(phone);
        if(code != null && !"".equals(code)) {
            checkVerificationCode(phone, code);
        }

    }

    // 检查验证码是否正确
    public static void checkVerificationCode(String phone, String code) {
        if(phone == null || "".equals(phone)) {
            System.out.println("请输入正确的手机号");
        }
        if(code == null || "".equals(code)) {
            System.out.println("请输入正确的验证码");
        }
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String cacheCode = jedis.get(verificationCode_phone + phone);
        if(cacheCode == null || "".equals(cacheCode)) {
            System.out.println("请检查redis缓存是否存在该手机的验证码");
        } else {
            if (cacheCode.equals(code)) {
                System.out.println("验证通过");
            } else {
                System.out.println("验证码不正确");
            }
        }
        jedis.close();
    }

    // 发送验证码
    public static String sendVerificationCode(String phone) {
        if(phone == null || "".equals(phone)) {
            System.out.println("请输入正确的手机号");
            return null;
        }
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String sentTime = jedis.get(verificationCode_phone_time + phone);
        String code = "";
        if(sentTime == null) {
            code = createRandomCode();
            jedis.set(verificationCode_phone_time + phone, sentTime);
        } else {
            int s = Integer.parseInt(sentTime);
            if (Integer.parseInt(sentTime) <= 2) {
                code = createRandomCode();
                jedis.incr(verificationCode_phone_time + phone); // 加1
            } else {
                System.out.println("同一手机号每天最多发送三次验证码，后续会陆续放宽验证请求，敬请期待!");
            }
        }
        if(!"".equals(code)) {
            System.out.println("发送的验证码为：" + code);
            jedis.setex(verificationCode_phone + phone, 10,code); // 过期后取值为 null，因为redis已经删去该条key
        }
        jedis.close();
        return code;
    }

    // 生成一个6位数的随机验证码
    public static String createRandomCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        return code;
    }
}
