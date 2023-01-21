package com.myprogect.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoss extends JFrame {
    private static JButton b1,b2;
    private static JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    private static String nameBoss = "Гавкошмыг";
    private static String nameMe  = JOptionPane.showInputDialog("Введи свое имя, воин");
    private static String q,w,b,r,t,y,u,i;
    private static int HpMe = 10;
    private static int HpBoss = 45;
    private static int Money = 0;
    private static int DamageMe = 1;
    private static int DamageBoss = 2;
    private static int Price = 2;

    public static void main(String[] args) {
        new GameBoss();
    }
        private GameBoss() {
        Action action = new Action();
        unitFrame();
        setLayout(null);
        b1 = new JButton("Ударить");
        b1.setBounds(40,290,170,60);
        b2 = new JButton("Прокачать");
        b2.setBounds(40,360,170,60);
        l1 = new JLabel(nameMe);
        l1.setBounds(30,10,130,50);
        l2 = new JLabel(nameBoss);
        l2.setBounds(650,10,130,50);
        l3 = new JLabel("");
        l3.setBounds(170,150,300,60);
        l4 = new JLabel("");
        l4.setBounds(170,170,300,60);
        l5 = new JLabel("");
        l5.setBounds(170,190,300,60);
        l6 = new JLabel("");
        l6.setBounds(170,210,300,60);
        l7 = new JLabel("");
        l7.setBounds(170,230,300,60);
        l8 = new JLabel("");
        l8.setBounds(170,250,300,60);
        l9 = new JLabel("");
        l9.setBounds(170,270,300,60);
        l10 = new JLabel("");
        l10.setBounds(170,390,300,60);
        add(b1);
        add(b2);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);
        add(l9);
        add(l10);

        b1.addActionListener(action);
        b2.addActionListener(action);
        }
        private void unitFrame() {
        setTitle("KillTheBoss");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800,650);
        setLocationRelativeTo(null);
        setResizable(false);
        }
        public class Action implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                while (true) {
                    if (e.getSource() == b1) {
                        HpBoss -= DamageMe;
                        Money++;
                        q = "У " + nameBoss + " осталось " + HpBoss + " здоровья";
                        w = "Твой баланс " + Money;
                        l3.setText(q);
                        l4.setText(w);
                        if (HpBoss <= 0) {
                            b  = "Ты убил босса";
                            l5.setText(b);
                            break;
                        }

                        HpMe -= DamageBoss;
                        r = nameMe + " Теперь у тебя " + HpMe + " здоровья";
                        l6.setText(r);
                        if (HpMe <= 0) {
                            t = "Тебя убил босс и ты возродился";
                            l7.setText(t);
                            HpMe = 10;
                        }
                    }
                    if (e.getSource() == b2) {
                        if (Money >= Price) {
                            DamageMe++;
                            Money = Money - Price;
                            y = "Ты прокачал свой урон за " + Price + " Теперь твой баланс равен " + Money;
                            u = "Нынешний урон: " + DamageMe;
                            l8.setText(y);
                            l9.setText(u);
                            Price *=2;
                        } else  {
                            i = "У тебя недостаточно денег";
                            l10.setText(i);
                     }
                 }
             }
         }
    }
}





