package com.wangwenjun.juc.utils.phaser;

import java.io.File;
import java.io.FileFilter;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/17
 * QQ交流群:601980517，463962286
 ***************************************/
public class Scan
{
    public static void main(String[] args) {
        File file = new File("G:\\Teaching\\汪文君多线程深入详解实战视频\\汪文君多线程编程实战视频-第三阶段");
        File[] wmvs = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith("wmv");
            }
        });

        for(File f:wmvs){
            System.out.println(f.getName().substring(0,f.getName().length()-3));
        }

    }
}
