package com.current.test;

/**
 * Created by Ivan on 2016/7/8.
 * 并发和原子操作
 */
public class P1 {

    private long b = 0;//long型8字节，如果在32位机器上赋值需要两步操作，这两步操作也不是原子性的，所以如果下面的程序运行在32位虚拟机上就会打印错误信息

    public void set1() {
        b = 0;
    }

    public void set2() {
        b = -1;
    }

    public void check() {
        //System.out.println(b);

        if (0 != b && -1 != b) {
            System.out.println("error");
            System.err.println("Error");
        }
    }

    public static void main(final String[] args) {
        final P1 v = new P1();

        // 线程 1：设置 b = 0
        final Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    v.set1();
                }
            };
        };
        t1.start();

        // 线程 2：设置 b = -1
        final Thread t2 = new Thread() {
            public void run() {
                while (true) {
                    v.set2();
                }
            };
        };
        t2.start();

        // 线程 3：检查 0 != b && -1 != b
        final Thread t3 = new Thread() {
            public void run() {
                while (true) {
                    v.check();
                }
            };
        };
        t3.start();
    }

}
