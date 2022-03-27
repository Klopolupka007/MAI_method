package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Menu extends  JFrame{
    JButton button[];String []criteria = {"Критерии", "Рейтинг", "Стоимость", "Количество часов игры", "Количество игроков", "Возрастное ограничение", "Auto", "Вывести расчеты", "Диаграмма"};
    int j =0;

    //Создаем Главное меню приложения, состоящее из 9 кнопок, названия которых выше в массиве criteria[]
    //Кнопки до "Auto" отвечают за заполнение и редактирования таблицы данных каждого критерия и самих критериев, в том числе
    //Кнопка "Auto" автоматически заполняет все таблицы заранее подготовленными данными
    //Кнопка "Вывести расчеты" нужна для упрощения ручного расчета и введения истории всех расчетов в каждой таблице
    //Кнопка "Диаграмма" выводит итоговую диаграмму альтернатив, основываясь на введенных данных и расчетах
     public Menu() {
        JFrame Main_frame = new JFrame("Метод МАИ – Видеоигры");
        Main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main_frame.setSize(215, 405);//215
        Main_frame.setVisible(true);
        Main_frame.setResizable(false);
        Main_frame.setLocationRelativeTo(null);
        Main_frame.setLayout(null);

        button = new JButton[9];
        double [][][] tables = new double[6][5][5];

        //таблица indx_V_W хранит расчетные данные
        double [][][] indx_V_W = new double[6][6][5];

        for (int i =0; i<9;i++){
            j=i;
            button[i] = new JButton(criteria[i]);
            button[i].setBounds(5, 40 * i + 5, 190, 35);

            if (i<6) button[i].setBackground(new Color(48, 185, 170));
            else if (i<7) button[i].setBackground(new Color(240, 134, 80));
            else button[i].setBackground(new Color(81, 217, 120));

            button[i].setVisible(true); button[i].setBorder(null);
            //Listener для кнопок
            Main_frame.add(button[i]); button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame table;
                    for (int j=0; j<9; j++) {
                        if (button[j] == e.getSource() && j < 6) table = new frame(tables, j, indx_V_W, criteria[j]);
                    }
                }
            });
        }
    }
}


