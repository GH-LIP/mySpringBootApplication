package com.test.io;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @auther: LIP
 * @create: 2021-03-21 8:42
 * @desc:
 */
public class IoTest {

    private static String defualtTarget = "D:\\documents\\工作\\品荟\\数据库\\新路径.sql";

    public static void main(String[] args) {
//        String filePath = "D:\\documents\\工作\\品荟\\数据库\\结构+数据\\admin_module.sql";
//        String targetStructurePath = "D:\\documents\\工作\\品荟\\数据库\\结构+数据\\tables-part-structure.sql";
//        extractTableStructure(filePath, targetStructurePath);

//        String targetDataPath = "D:\\documents\\工作\\品荟\\数据库\\结构+数据\\data$$.sql";
//        extractTableData(filePath, targetDataPath);

//        String tablePath1 = "D:\\documents\\工作\\品荟\\数据库\\结构+数据\\tables-structure.sql";
//        List<String> list1 = readFileByIO (tablePath1);
//        String tablePath2 = "D:\\documents\\工作\\品荟\\数据库\\结构+数据\\tables-part-structure.sql";
//        List<String> list2 = readFileByIO (tablePath2);
//        String targetPath = "D:\\documents\\工作\\品荟\\数据库\\结构+数据\\tables-escaped-structure.sql";
//        list1.removeAll(list2);
//        extractEscapedTableStructure(tablePath1, targetPath, list1);

//        String p1 = "D:\\documents\\工作\\品荟\\数据库\\结构+数据\\tables-structure.sql";
//        String p2 = "D:\\documents\\工作\\品荟\\数据库\\结构+数据\\tables-xxx.sql";
//        extractTableData_logicError(p1, p2);

    }

    /**
     * InputStream读取文件(按行读取)
     *
     * @param filePath
     */
    public static List<String> readFileByIO(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        List<String> list = new LinkedList();
        try {
            inputStream = new FileInputStream(filePath);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String str = null, tableName = "";
            while ((str = reader.readLine()) != null) {
                // 输出文件内容
                if (str.indexOf("DROP TABLE IF EXISTS") >= 0) {
                    tableName = str.substring(str.indexOf("`")+1, str.lastIndexOf("`"));
                    list.add(tableName);
                }
            }
//            System.out.println(list.size()+"张表\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }
    }

    /**
     * OutputStream 输出文件(按行读取)
     *
     * @param filePath
     */
    public static void writeFileByIO(String filePath, List<String> list) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        List<String> list1 = new LinkedList();

    }

    /**
     * 抽离表格结构
     * @param filePath 文件路径
     */
    public static void extractTableStructure(String filePath, String targetPath) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }
        if (StringUtils.isEmpty(targetPath)) {
            targetPath = defualtTarget;
        }
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        StringBuffer stringBuffer = null;
        try {
            List<String> lists = Files.readAllLines(Paths.get(filePath));
            int startToEnd = 0;     // 每一个表的执行语句统计行数
            int tableCount = 0;     // 统计表格数
            int afterCount = 0;     // 转化后行数
            int globalCount = 0;
            boolean start = false, end = false;  // 开始 结束  标志
            stringBuffer = new StringBuffer();

            for (String str : lists) {
                if (StringUtils.isEmpty(str)) {
                    continue;
                }
                globalCount++;
                if (start && !end) {
                    stringBuffer.append(str + "\r\n");
                    startToEnd++;
                    afterCount++;
                }
                if (str.indexOf("DROP TABLE IF EXISTS") >= 0) {
                    stringBuffer.append(str + "\r\n");
                    start = true;
                    end = false;
                    startToEnd = 1;
                    tableCount++;
                    afterCount++;
                }
                if (str.indexOf(") ENGINE =") >= 0) {
                    stringBuffer.append("-- 上表共"+ (startToEnd++) +"行语句\r\n");
                    start = false;
                    end = true;
                    afterCount++;
                }
                if (globalCount == lists.size()) {
                    String s = "表格数：" + tableCount + "，转化前行数：" + globalCount + "("+ lists.size() +")，转化后行数：" + (++afterCount);
                    System.out.println(s);
                    stringBuffer.append("-- 统计  表格数："+ tableCount +"，转化前行数："+ globalCount +"("+ lists.size() +")，转化后行数："+ (++afterCount) +"\r\n");
                }
            }
            fileWriter = new FileWriter(targetPath);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void extractEscapedTableStructure(String filePath, String targetPath, List<String> tableNames) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }
        if (StringUtils.isEmpty(targetPath)) {
            targetPath = defualtTarget;
        }
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        StringBuffer stringBuffer;
        try {
            List<String> lists = Files.readAllLines(Paths.get(filePath));
            int startToEnd = 0;     // 每一个表的执行语句统计行数
            int tableCount = 0;     // 统计表格数
            int afterCount = 0;     // 转化后行数
            int globalCount = 0;
            boolean start = false, end = false;  // 开始 结束  标志
            stringBuffer = new StringBuffer();

            String tableName = "";

            for (int i = 0; i < tableNames.size(); i++) {
                tableName = tableNames.get(i);
                for (String str : lists) {
                    if (StringUtils.isEmpty(str)) {
                        continue;
                    }
                    globalCount++;
                    if (start && !end) {
                        stringBuffer.append(str + "\r\n");
                        startToEnd++;
                        afterCount++;
                    }
                    if (str.trim().indexOf("DROP TABLE IF EXISTS `" + tableName + "`") >= 0) {
                        stringBuffer.append(str + "\r\n");
                        start = true;
                        end = false;
                        startToEnd = 1;
                        tableCount++;
                        afterCount++;
                    }
                    if (start && !end && str.indexOf(") ENGINE =") >= 0) {
                        stringBuffer.append("-- 上表共" + (startToEnd++) + "行语句\r\n");
                        start = false;
                        end = true;
                        afterCount++;
                    }

                    if (globalCount == lists.size()) {
                        String s = "表格数：" + tableCount + "，转化前行数：" + globalCount + "(" + lists.size() + ")，转化后行数：" + (++afterCount);
                        System.out.println(s);
                        stringBuffer.append("-- 统计  表格数：" + tableCount + "，转化前行数：" + globalCount + "(" + lists.size() + ")，转化后行数：" + (++afterCount) + "\r\n");
                    }
                }
            }
            fileWriter = new FileWriter(targetPath);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param filePath 抽离表格创建语句
     */
    public static void extractTableData(String filePath, String targetPath) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }
        if (StringUtils.isEmpty(targetPath)) {
            targetPath = defualtTarget;
        }
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        StringBuffer stringBuffer = null;
        try {
            List<String> lists = Files.readAllLines(Paths.get(filePath));
            int index = 1;          // 分割文本计数器
            int afterCount = 0;     // 转化后行数
            int globalCount = 0;
            stringBuffer = new StringBuffer();

            for (String str : lists) {
                if (StringUtils.isEmpty(str)) {
                    continue;
                }
                globalCount++;
                if (str.indexOf("INSERT INTO") >= 0) {
                    stringBuffer.append(str + "\r\n");
                    afterCount++;
                }
                if (globalCount % 100000 == 0) {
                    // 每10W行数据保存一个文件
                    fileWriter = new FileWriter(targetPath.replace("$$", ""+ (index++)));
                    bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(stringBuffer.toString());
                    // 清空缓存
                    stringBuffer = new StringBuffer();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("效率与质量并重！");
            }
        }
    }


    /**
     * 通过 FileChannel 从指定的文件中读取数据(按行读取)  fixme 抽离表格数
     * 据插入语句
     * @param filePath 源文件
     * @param targetPath 目标文件
     */
    public static void extractTableData_logicError (String filePath, String targetPath) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }
        if (StringUtils.isEmpty(targetPath)) {
            targetPath = defualtTarget;
        }
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileInChannel = null;
        FileChannel fileOutChannel = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            fileOutputStream = new FileOutputStream(targetPath);
            fileInChannel = fileInputStream.getChannel();
            fileOutChannel = fileOutputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.clear();
            buffer.put("".getBytes());
            fileInChannel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                fileInChannel.write(buffer);
            }
            //
            // fileInChannel.transferTo(0, fileInChannel.size(), fileOutChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInChannel != null) {
                    fileInChannel.close();
                }
                if (fileOutChannel != null) {
                    fileOutChannel.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("一遍過感觉 feel good!!!");
            }
        }
    }

    /**
     * 文件夹遍历
     * @param file
     */
    public static void directoryErgodic (File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                directoryErgodic(files[i]);
            }
        } else if (file.isFile()) {
            System.out.println("》》文件名："+file.getName());
        }
    }

    public static void testList () {
        new ArrayList<String>().add("");
        Collections.synchronizedList(new ArrayList());
        new CopyOnWriteArrayList<String>().add("");
        List list = new ArrayList(10);

    }

    @Test
    public void test () {
        File file = new File("D:\\softwares\\安装包\\programSoftwares\\JetBrains");
        directoryErgodic(file);
    }

}
