package com.test.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @auther: LIP
 * @create: 2021-01-20 9:37
 */
public class JDBCConn {

    public static void main(String[] args) {
        // jdbc 获取服务器连接
        try {
            // 1. 导入jar包（1.5和1.8版本的驱动不同）  见maven的配置文件：pom.xml
            // 2. 注册驱动
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); //如果驱动不存在，就会报编译期错误，耦合度太高。
            Class.forName("com.mysql.jdbc.Driver");  // 相比上面的代码，耦合度降低，要想再次降低，可以将驱动放入配置文件进行读取
            // 3. 获取mysql数据库连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ry?" +
                            "characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true",
                    "lip","a438521i..");
            // 4. 获取数据库的预编译对象
            PreparedStatement pstm = conn.prepareStatement("select user_name from sys_user");
            // 5. 执行sql得到结果集
            ResultSet rs = pstm.executeQuery();
            // 6. 遍历结果集
            while (rs.next()) {
                System.out.println("姓名是："+ rs.getString("user_name"));
            }
            // 7. 关闭连接
            rs.close();
            pstm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
