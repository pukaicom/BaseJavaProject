package com.pk.base;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by pukai on 17/4/6.
 */

public class BounceFrame extends JFrame {
    private BallComponent comp;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    public static final int STEPS = 100000;
    public static final int DELAY = 3;

    public BounceFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("跳动的小球");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonJPanel = new JPanel();
        addButton(buttonJPanel, "Start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        });
        addButton(buttonJPanel, "Close", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(buttonJPanel, BorderLayout.SOUTH);

    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton j = new JButton(title);
        c.add(j);
        j.addActionListener(listener);
    }

    public void addBall() {

        Ball ball = new Ball();
        comp.add(ball);
        new Thread(new MyRunnable(ball)).start();
    }

    public class MyRunnable implements Runnable {
        private Ball myBall;

        public MyRunnable(Ball ball) {
            myBall = ball;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i <= STEPS; i++) {
                    myBall.move(comp.getBounds());
                    comp.repaint();
                    Thread.sleep(DELAY);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
