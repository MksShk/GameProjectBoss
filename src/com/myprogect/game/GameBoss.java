package com.myprogect.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameBoss extends JFrame {
    private JButton btnHit, btnUpgrade, btnQuit,btnInfo;
    private JLabel lblPlayerName, lblBossName, lblBossHP,lblPlayerHp, lblPlayerDamage, lblPlayerMoney, lblGameResult, lblNotification;
    private ImageIcon backgroundImage = new ImageIcon("res/PhoneGra.jpg");
    private ImageIcon bossImage = new ImageIcon("res/Boss.png");
    private ImageIcon moneyImage = new ImageIcon("res/Monetka.png");
    private ImageIcon paperImage = new ImageIcon("res/Bumaga.png");
    private ImageIcon hitImage = new ImageIcon("res/hit.png");
    private ImageIcon upgradeImage = new ImageIcon("res/upgrade.png");
    private ImageIcon quitImage = new ImageIcon("res/quit.png");
    private ImageIcon infoImage = new ImageIcon("res/info.png");
    private int Money = 10;
    private int Price = 2;
    private GameCharacters player = new GameCharacters(JOptionPane.showInputDialog("Введи свое имя, воин"),10,1);
    private GameCharacters boss = new GameCharacters("Гавкошмыг",20,2);

    private boolean gameOver = false;

    static FightManager fightManager;

    public static void main(String[] args) {
        new GameBoss();
        fightManager = new FightManager();
    }

    private GameBoss() {
        Action action = new Action();
        unitFrame();
        addComponentsToFrame();

        btnHit.addActionListener(action);
        btnUpgrade.addActionListener(action);
        btnQuit.addActionListener(action);
        btnInfo.addActionListener(action);
        updateLabels();
    }
    private void unitFrame() {
        setTitle("KillTheBoss");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800,650);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    private void addComponentsToFrame() {
        JLabel background = new JLabel(backgroundImage);
        setContentPane(background);
        background.setLayout(null);

        JLabel bossIcon = new JLabel(bossImage);
        bossIcon.setBounds(415, 55, 350, 500);

        JLabel moneyIcon = new JLabel(moneyImage);
        moneyIcon.setBounds(10, 10, 50, 50);

        JLabel paperIcon = new JLabel(paperImage);
        paperIcon.setBounds(-27, 75, 400, 400);

        btnInfo = new JButton(infoImage);
        btnInfo.setBounds(730, 10, 50, 30);
        btnHit = new JButton(hitImage);
        btnHit.setBounds(75, 240, 190, 45);
        btnUpgrade = new JButton(upgradeImage);
        btnUpgrade.setBounds(75, 290, 190, 45);
        btnQuit = new JButton(quitImage);
        btnQuit.setBounds(75, 340, 190, 45);

        lblPlayerName = new JLabel(player.name);
        lblPlayerName.setBounds(138, 120, 130, 50);
        lblPlayerName.setFont(new Font("Arial", Font.BOLD, 20));

        lblBossName = new JLabel(boss.name);
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
        background.add(btnInfo);
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
    }

    public class Action extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj == btnHit) {
                if (!fightManager.getBattleStatus()) {
                    fightManager.startNewBattle(new Hero(), new Boss());
                }
                fightManager.runBattleRound();
                lblGameResult.setText(fightManager.getBattleResult().getMessage());
                lblBossHP.setText("HP: " + fightManager.getC2().sayHealthPointStatus());
                lblPlayerHp.setText("Здоровье: " + fightManager.getC1().sayHealthPointStatus());
                // hit();
            } else if (obj == btnUpgrade) {
                upgrade();
            }  else if (obj == btnQuit) {
                fightManager.stopBattle();
                // quit();
            } else if (obj == btnInfo) {
                info();
            }
        }
    }
    private void hit() {
        if (!gameOver) {
            boss.hp -= player.damage;
            Money++;
            lblBossHP.setText("HP: " + boss.hp);
            lblPlayerMoney.setText(""+ Money);
            player.hp -= boss.damage;
            lblPlayerHp.setText("Здоровье: " + player.hp);
            checkGameOver();
        }
    }

    private void upgrade() {
        if (!gameOver && Money >= Price && player.damage < 5) {
            player.damage++;
            Money -= Price;
            lblPlayerDamage.setText("Твой урон: " + player.damage);
            lblPlayerMoney.setText("" + Money);
            Price *=2;
            lblGameResult.setText("Ты увеличил свой урон на : 1");
        } else if (player.damage >= 5) {
            JOptionPane.showMessageDialog(null, "Урон максимален");
        } else {
            JOptionPane.showMessageDialog(null, "Недостаточно денег");
        }
    }

    private void quit() {
        int confirm = JOptionPane.showConfirmDialog(null, "Ты уверн, что хочешь выйти из игры?", "Выход", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void info() {
        JOptionPane.showOptionDialog(GameBoss.this,
                "Цель: Убить Босса" + System.lineSeparator() +
                        "- За каждый удар тебе дается по 1 монете"
                        + System.lineSeparator() + "- Ты можешь увеличить урон, нажав Прокачать"
                        + System.lineSeparator() + "- Начальная стоимость прокачки - 2 монеты, последующуя в два раза дороже предыдущей"
                        + System.lineSeparator() + System.lineSeparator() + "P.S Будь осторожней, ведь босс бьет тебя в ответ", "Информация",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    }

    private void checkGameOver() {
        if (boss.hp <=0) {
            boss.hp = 0;
            updateLabels();
            lblGameResult.setText("Ты победил получается");
            gameOver = true;
            int choice = JOptionPane.showConfirmDialog(null, "Вы хотите перезапустить игру?", "Вы прошли игру",JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                main(null);
            } else if (choice == JOptionPane.NO_OPTION){
                System.exit(0);
            }
        }
        if (player.hp <= 0) {
            lblGameResult.setText("<html>Тебя нахлабучил " + boss.name +  " <br> и ты возродился</html>");
            boss.hp = 20 + player.damage;
            player.hp = 10 + boss.damage;
        }
    }

    private void updateLabels() { //Обновление информации
        lblPlayerMoney.setText("" + Money);
        lblPlayerHp.setText("Здоровье: " + player.hp);
        lblPlayerDamage.setText("Твой урон: " + player.damage);
        lblBossHP.setText("HP: " + boss.hp);
        lblGameResult.setText("");
    }
}