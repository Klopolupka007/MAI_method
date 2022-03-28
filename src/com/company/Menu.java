package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
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
        Main_frame.getContentPane().setBackground(new Color(40,40,40));

         button = new JButton[9];
        double [][][] tables = new double[6][5][5];

        //таблица indx_V_W хранит расчетные данные
        double [][][] indx_V_W = new double[6][6][2];

        //resArr - массив, хранящий итоговые значения после расчетов для сравнения альтернатив между собой
        double [] resArr = new double[5];

        for (int i =0; i<6; i++){
            for (int j = 0; j<6;j++){
                for (int k =0; k<2; k++){
                    indx_V_W[i][j][k] = 1;
                }
            }
        }

         for (int i =0; i<6; i++){
             for (int j = 0; j<5;j++){
                 for (int k =0; k<5; k++){
                     tables[i][j][k] = 1;
                 }
             }
         }

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
                    frame table; Computing computing;
                    String temp_str_auto = "";
                    for (int j=0; j<9; j++) {
                        if (button[j] == e.getSource()) {
                            //Таблицы - ручное заполнение
                            if (j < 6) {
                                table = new frame(tables, j, indx_V_W, criteria[j]);
                                button[6].setBackground(new Color(240, 134, 80));
                            }
                            //Функция автозаполнения
                            else if (j == 6) {
                                button[6].setBackground(Color.gray);
                                String symbol = "";
                                try (BufferedReader reader = new BufferedReader(new FileReader("src/com/company/autoFilling.txt"))) {
                                    for (int x = 0; x < 6; x++) {
                                        for (int y = 0; y < 6; y++) {
                                            symbol = reader.readLine();
                                            for (int z = 0, z1 = 0; z < symbol.length(); z++) {
                                                if (symbol.charAt(z) == ' ') continue;
                                                else if (symbol.charAt(z) == '1') {
                                                    if (z + 1 != symbol.length()) {
                                                        if (symbol.charAt(z + 1) == '/') {
                                                            temp_str_auto = String.valueOf(symbol.charAt(z + 2));
                                                            tables[x][y][z1] = 1 / Double.parseDouble(temp_str_auto);
                                                            z += 2;
                                                            z1++;
                                                            continue;
                                                        }
                                                    } else {
                                                        tables[x][y][z1] = Double.parseDouble(String.valueOf(symbol.charAt(z)));
                                                        z1++;
                                                        continue;
                                                    }
                                                    tables[x][y][z1] = Double.parseDouble(String.valueOf(symbol.charAt(z)));
                                                    z1++;
                                                } else {
                                                    tables[x][y][z1] = Double.parseDouble(String.valueOf(symbol.charAt(z)));
                                                    z1++;
                                                }
                                            }
                                        }
                                    }
                                } catch (IOException ioException) {
                                    System.out.println("error");
                                }
                            }
                            //Окно с расчетами для отчета или истории вычислений
                            else if (j == 7){
                                computing = new Computing(indx_V_W, resArr, tables);
                            }
                            //Окно с диаграммой
                        }

                    }
                }
            });
        }
    }
}


