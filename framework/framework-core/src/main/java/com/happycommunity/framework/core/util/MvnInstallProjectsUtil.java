package com.happycommunity.framework.core.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Danny
 * @Title: MvnInstallProjectsUtil
 * @Description: 执行脚本/命令行
 * @Created on 2018-09-12 10:37:26
 */
public class MvnInstallProjectsUtil {

    /**
     * 打包所有项目工具类（开发用）
     * /Users/dannyhoo/github/01_projects_danny/HappyCommunity/happycommunity-framework
     * /Users/dannyhoo/github/01_projects_danny/HappyCommunity/happycommunity-user
     * /Users/dannyhoo/github/01_projects_danny/HappyCommunity/happycommunity-business
     * /Users/dannyhoo/github/01_projects_danny/HappyCommunity/happycommunity-gateway
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> rootPackagePathList = Arrays.asList(args);
        for (String rootPackagePath : rootPackagePathList) {
            MvnInstallProjectsUtil.install(rootPackagePath);
        }
    }

    private static boolean install(String rootPackagePath) throws IOException, InterruptedException {
        File file = new File(rootPackagePath);
        if (file.exists()) {
            String unzipCommand = "";
            long startTime = System.currentTimeMillis();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("cd " + rootPackagePath);
            // 创建并返回一个本机进程Process
            Process process = runtime.exec("mvn clean install -DskipTests");
            // 实时清空Process的InputStream缓存，防止阻塞
            ProcessWatcher processWatcher = new MvnInstallProjectsUtil().new ProcessWatcher(process);
            processWatcher.start();
            int exitcode = process.waitFor();
            ArrayList<String> commandStream = processWatcher.getStream();
            processWatcher.setOver(true);
            System.out.println(rootPackagePath + " 目录 install 结束，耗时：" + (System.currentTimeMillis() - startTime) + "，执行结果:" + exitcode + "，目标文件夹：" + rootPackagePath);
            return true;
        } else {
            System.out.println("项目不存在！");
        }
        return false;
    }

    class ProcessWatcher extends Thread {
        Process process;
        boolean over;
        ArrayList<String> stream;

        public ProcessWatcher(Process process) {
            this.process = process;
            over = false;
            stream = new ArrayList<String>();
        }

        public void run() {
            try {
                if (process == null) return;
                Scanner br = new Scanner(process.getInputStream());
                while (true) {
                    if (process == null || over) break;
                    while (br.hasNextLine()) {
                        String tempStream = br.nextLine();
                        if (tempStream.trim() == null || tempStream.trim().equals("")) continue;
                        stream.add(tempStream);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public ArrayList<String> getStream() {
            return stream;
        }
    }

}
