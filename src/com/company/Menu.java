package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.renderable.ContextualRenderedImageFactory;

public class Menu extends  JFrame{
     JButton button[];String []criteria = {"Критерии", "Рейтинг", "Стоимость", "Количество часов игры", "Количество игроков", "Возрастное ограничение", "Auto", "Диаграмма", "Вывести расчеты"};
    public Menu() {
        JFrame Main_frame = new JFrame("Метод МАИ – Видеоигры");
        Main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main_frame.setSize(215, 405);
        Main_frame.setVisible(true);
        Main_frame.setResizable(false);
        Main_frame.setLocationRelativeTo(null);
        Main_frame.setLayout(null);
        setLayout(new FlowLayout());
        button = new JButton[8];

        double [][][] tables = new double[5][5][5];

        for (int i = 0; i < 9; i++) {
            button[i] = new JButton(criteria[i]);
            button[i].setBounds(5, 40 * i + 5, 190, 35);
            if (i<6) button[i].setBackground(new Color(48, 185, 170));
            else if (i<7) button[i].setBackground(new Color(240, 134, 80));
            else button[i].setBackground(new Color(81, 217, 120));
            button[i].setVisible(true); button[i].setBorder(null);
            Main_frame.add(button[i]); button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame table = new frame(tables);
                }
            });
        }
    }
}
