package com.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Connection;

public class SqlUtil {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {

    }

    @Test
    public void test () {
        FileInputStream fis = null;
        Connection connection;
        try {
            connection = dataSource.getConnection("lip", "a438521i..");

            fis = new FileInputStream(new File(""));
            FileChannel ic = fis.getChannel();

            int len = -1;
            int size = 1000;
            ByteBuffer bf = ByteBuffer.allocate(size);

            while ((len = ic.read(bf)) != -1) {
                bf.flip();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
