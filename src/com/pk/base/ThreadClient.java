package com.pk.base;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Created by pukai on 17/4/6.
 */

public class ThreadClient {
    public static void main(String[] args) {
        //new Thread(new MyRunnable(0)).start();
        //new Thread(new MyRunnable(1)).start();
       EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BounceFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }

    static class MyRunnable implements Runnable {
        private int i;

        public MyRunnable(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            while (true) {
                Thread thread = Thread.currentThread();
                if (!thread.isInterrupted()) {
                    System.out.println(i);
                    thread.interrupt();
                }
            }
        }
    }
}
