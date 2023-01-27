package com.myprogect.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoss extends JFrame {
    private JButton btnHit, btnUpgrade, btnQuit;
    private JLabel lblPlayerName, lblBossName, lblBossHP,lblPlayerHp, lblPlayerDamage, lblPlayerMoney, lblGameResult, lblNotification;
    private String nameBoss = "Гавкошмыг";
    private String nameMe  = JOptionPane.showInputDialog("Введи свое имя, воин");
    private ImageIcon backgroundImage = new ImageIcon("res/PhoneGra.jpg");
    private ImageIcon bossImage = new ImageIcon("res/Boss.png");
    private ImageIcon moneyImage = new ImageIcon("res/Monetka.png");
    private ImageIcon paperImage = new ImageIcon("res/Bumaga.png");
    private ImageIcon hitImage = new ImageIcon("res/hit.png");
    private ImageIcon upgradeImage = new ImageIcon("res/upgrade.png");
    private ImageIcon quitImage = new ImageIcon("res/quit.png");
    private int HpMe = 10;
    private int HpBoss = 45;
    private int Money = 0;
    private int DamageMe = 1;
    private int DamageBoss = 2;
    private int Price = 2;

    private boolean gameOver = false;

    public static void main(String[] args) {
        new GameBoss();
    }

    private GameBoss() {
        Action action = new Action();
        unitFrame();

        JLabel background = new JLabel(backgroundImage);
        setContentPane(background);
        background.setLayout(null);

        JLabel bossIcon = new JLabel(bossImage);
        bossIcon.setBounds(415, 55, 350, 500);

        JLabel moneyIcon = new JLabel(moneyImage);
        moneyIcon.setBounds(10, 10, 50, 50);

        JLabel paperIcon = new JLabel(paperImage);
        paperIcon.setBounds(-27, 75, 400, 400);

        btnHit = new JButton(hitImage);
        btnHit.setBounds(75, 240, 190, 45);
        btnUpgrade = new JButton(upgradeImage);
        btnUpgrade.setBounds(75, 290, 190, 45);
        btnQuit = new JButton(quitImage);
        btnQuit.setBounds(75, 340, 190, 45);

        lblPlayerName = new JLabel(nameMe);
        lblPlayerName.setBounds(138, 120, 130, 50);
        lblPlayerName.setFont(new Font("Arial", Font.BOLD, 20));

        lblBossName = new JLabel(nameBoss);
        lblBossName.setBounds(530, 535, 130, 50);
        lblBossName.setFont(new Font("Arial", Font.BOLD, 20));

        lblBossHP = new JLabel("");
        lblBossHP.setBounds(533, 20, 300, 60);
        lblBossHP.setFont(new Font("Arial", Font.BOLD, 30));

        lblPlayerDamage = new JLabel("");
        lblPlayerDamage.setBounds(65, 170, 300, 60);
        lblPlayerDamage.setFont(new Font("Arial", Font.BOLD, 15));

        lblPlayerMoney = new JLabel("");
        lblPlayerMoney.setBounds(65,6, 300, 60);
        lblPlayerMoney.setFont(new Font("Arial", Font.BOLD, 20));

        lblPlayerHp = new JLabel("");
        lblPlayerHp.setBounds(65, 145, 300, 60);
        lblPlayerHp.setFont(new Font("Arial", Font.BOLD, 15));

        lblGameResult = new JLabel("");
        lblGameResult.setBounds(75, 490, 300, 60);
        lblGameResult.setFont(new Font("Arial", Font.BOLD, 14));

        lblNotification = new JLabel("Уведомления:");
        lblNotification.setBounds(38, 460, 300, 60);
        lblNotification.setFont(new Font("Arial", Font.BOLD, 18));

        background.add(btnHit);
        background.add(btnUpgrade);
        background.add(btnQuit);
        background.add(lblPlayerName);
        background.add(lblBossName);
        background.add(lblBossHP);
        background.add(lblPlayerDamage);
        background.add(lblPlayerMoney);
        background.add(lblPlayerHp);
        background.add(lblGameResult);
        background.add(lblNotification);
        background.add(paperIcon);
        background.add(moneyIcon);
        background.add(bossIcon);

        btnHit.addActionListener(action);
        btnUpgrade.addActionListener(action);
        btnQuit.addActionListener(action);
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

                if (e.getSource() == btnHit) {
                    if (!gameOver) {
                        HpBoss -= DamageMe;
                        Money++;
                        lblBossHP.setText("HP: " + HpBoss);
                        lblPlayerMoney.setText(""+ Money);
                        HpMe -= DamageBoss;
                        lblPlayerHp.setText("Здоровье: " + HpMe);
                        checkGameOver();
                    }
                } else if (e.getSource() == btnUpgrade) {
                    if (!gameOver && Money >= Price) {
                        DamageMe++;
                        Money -= Price;
                        lblPlayerDamage.setText("Твой урон: " + DamageMe);
                        lblPlayerMoney.setText("" + Money);
                        Price *=2;
                    } else {
                        JOptionPane.showMessageDialog(null, "Недостаточно денег");
                    }
                } else if (e.getSource() == btnQuit) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Ты уверн, что хочешь выйти из игры?", "Выход", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
            }
        }
    }
    private void checkGameOver() {
        if (HpBoss <= 0) {
            lblGameResult.setText("Ты победил получается");
            gameOver = true;
        } else if (HpMe <= 0) {
            lblGameResult.setText("<html>Тебя нахлабучил Гавкошмыг <br> и ты возродился</html>");
            HpMe = 10;
        }
    }
}
