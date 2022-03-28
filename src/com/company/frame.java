package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

import static java.lang.Math.pow;

public class frame extends JFrame {
    private JFrame frame;
    int iter_but;


    //tables_temp необходим для хранения значений таблицы. Первый индекс - номер таблицы от 0 до 5. Второй и третий - значения таблицы
    //V_W_temp - таблица для результатов расчетов
    double [][][] tables_temp = new double[5][5][5];
    double [][][] V_W_temp = new double[6][6][2];

    JLabel VW[][] = new JLabel[2][7];

    //Создаем окно для работы с таблицами
    public frame(double [][][] tables, int i, double[][][] indx_V_W, String name){
        frame = new JFrame(name);
        frame.setSize(825, 750);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); frame.getContentPane().setBackground(new Color(40,40,40));

        //iter_but необходим для запоминания над какой таблицей мы работаем в данный момент
        this.iter_but = i;
        tables_temp = tables.clone();
        V_W_temp = indx_V_W.clone();

        //Функция для ручного заполнения таблицы и автоматического ввода значений в table_temp
        TextFilling();

    }

    private JTextField[][] field;

    //Функция для ручного заполнения таблицы и автоматического ввода значений в table_temp
    private void TextFilling(){
        textChangedListener listen = new textChangedListener();
        //Создаем поля для ввода значений
        field = new JTextField[5][5]; Font font = new Font("Times new Roman", Font.BOLD ,30);

        //Добавляем listener для обработки ввода в поля
        textChangedListener listener = new textChangedListener();

        //Настройка полей для ввода + автоматическое заполнение главной диагонали матрицы единицами
        for (int i = 0; i< 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j] = new JTextField();
                field[i][j].setBackground(new Color (252, 175, 175));
                field[i][j].setBounds(100 * (j + 1) + 5, 100 * (i + 1) + 5, 95, 95);

                if (i == j) {
                    field[i][j].setText("1");
                    tables_temp[iter_but][i][j] = 1;
                }

                field[i][j].setHorizontalAlignment(JTextField.CENTER);
                field[i][j].setFont(font);

                frame.add(field[i][j]);
                field[i][j].addKeyListener(listener);
            }
        }


        //Данный участок кода необходим для АВТОЗАПОЛНЕНИЯ при ПОВТОРНОМ открытии таблицы.
        //Поля, обозначенные единицами или пустыми кнопками заполняются единицами ПОСЛЕ нажатия на кнопку Start.
        //В listener для кнопки Start встроена функция заполнения пустых полей таблицы единицами.
        StringBuilder str_for_field;
        for (int i = 0; i< 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i!=j && tables_temp[iter_but][i][j]!=1){
                    str_for_field = new StringBuilder();
                    for (int h =0; h<String.valueOf(tables_temp[iter_but][i][j]).length();h++){
                        if (String.valueOf(tables_temp[iter_but][i][j]).charAt(h)=='0'){
                            field[i][j].setText(listen.fromStr(String.valueOf(tables_temp[iter_but][j][i]))); break;
                        }
                        else if (String.valueOf(tables_temp[iter_but][i][j]).charAt(h)=='.') { field[i][j].setText(String.valueOf(str_for_field)); break; }
                        else str_for_field.append(String.valueOf(tables_temp[iter_but][i][j]).charAt(h));
                    }
                }
            }
        }



        //Для обозначения номеров полей сверху и слева создаем надписи по порядку с 1 по 5
        JLabel lab[][] = new JLabel[2][5];
        for (int i=0; i<2;i++){
                for (int j=0; j<5;j++) {
                    lab[i][j] = new JLabel(Integer.toString(j+1));

                    if (i==0) lab[i][j].setBounds(100 * (j+1) + 5, 5, 95, 95);
                    else lab[i][j].setBounds(5, 100 * (j+1) + 5, 95, 95);

                    lab[i][j].setOpaque(true);
                    lab[i][j].setBackground(new Color(219, 96, 96));
                    lab[i][j].setFont(font); lab[i][j].setHorizontalAlignment(JLabel.CENTER);

                    frame.add(lab[i][j]);
                }
        }

        //Блоки label для показателя расчетов
        for (int x=0; x<2; x++){
            for (int y =0; y<7; y++){
                if (x==1 && y == 6) continue;
                    VW[x][y] = new JLabel();
                    VW[x][y].setBounds(100*(x+6)+5, 100*y+5, 95, 95);
                    VW[x][y].setFont(new Font("Times new Roman", Font.BOLD, 25));
                    VW[x][y].setOpaque(true);
                    VW[x][y].setBackground(new Color(252, 149, 149));
                    VW[x][y].setHorizontalAlignment(JLabel.CENTER);

                    frame.add(VW[x][y]);
            }
        } VW[0][0].setText("V(i)"); VW[1][0].setText("W(2i)");


        //Добавляем label для СЗ, ИС и ОС
        JLabel[] DownLabels = new JLabel[3];

        for (int i =0; i<3; i++){
            DownLabels[i] = new JLabel();
            DownLabels[i].setBounds(10, 620+i*27, 300, 29);
            DownLabels[i].setFont(new Font("Times new Roman", Font.BOLD, 20));
            DownLabels[i].setForeground(new Color(250,250,250));
            frame.add(DownLabels[i]);
        } DownLabels[0]. setText("СЗ = N/A"); DownLabels[1].setText("ИС = N/A"); DownLabels[2].setText("ОС = N/A");


        //Кнопка для начала расчетов
        JButton Start = new JButton("Start");
        Start.setBounds(705, 605, 95, 95);
        Start.setBackground(new Color(81, 217, 120));
        Start.setBorder(null); Start.setFont(new Font("Times new Roman", Font.BOLD, 35));
        final double[] temp_num = {0};
        MathContext context = new MathContext(3, RoundingMode.HALF_UP);
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp_num[0] = 0;
                for (int i=0; i<5; i++){
                    V_W_temp[iter_but][i][0] = 1;
                    for (int k = 0; k < 5; k++) V_W_temp[iter_but][i][0] *= tables_temp[iter_but][i][k];
                        V_W_temp[iter_but][i][0] = pow(V_W_temp[iter_but][i][0], 0.2);
                        VW[0][i+1].setText(String.valueOf(new BigDecimal(V_W_temp[iter_but][i][0], context)));
                        temp_num[0] += V_W_temp[iter_but][i][0];
                } V_W_temp[iter_but][5][0] = temp_num[0]; VW[0][6].setText(String.valueOf(new BigDecimal(V_W_temp[iter_but][5][0], context)));

                for (int i=0; i<5; i++){
                    V_W_temp[iter_but][i][1] = V_W_temp[iter_but][i][0]/temp_num[0];
                    VW[1][1+i].setText(String.valueOf(new BigDecimal(V_W_temp[iter_but][i][1], context)));
                }

                for(int i=0; i<5; i++){
                    for (int j =0; j<5; j++){
                        if (field[i][j].getText().equals("")) field[i][j].setText("1");
                    }
                }

                //Расчет СЗ и изменение соответствующего label
                double SZ, res = 0;
                for (int i =0; i<5; i++) {
                    SZ = 0;
                    for (int j =0; j<5; j++){
                        SZ += tables_temp[iter_but][j][i];
                    }
                    res+=SZ*V_W_temp[iter_but][i][1];
                }
                DownLabels[0].setText("СЗ = " + String.valueOf(res));

                //Расчет ИС и изменение соответствующего label
                res = (res-5)/4;
                DownLabels[1].setText("ИС = " + String.valueOf(res));

                //Расчет ОС и изменение соответствующего label
                res = res/1.12;
                DownLabels[2].setText("ОС = " + String.valueOf(res));
            }
        });


        frame.add(Start);



    }

    //listener для обработки ввода чисел в поля ввода
    class textChangedListener implements KeyListener
    {
        //При нажатии любой управляющей клавиши после ввода будет запущен обработчик
        public void keyPressed(KeyEvent e){
            String num;
            int i =0, j=0, flag=0;

            //Вычисляем какое поле было изменено
            JTextField text = (JTextField)e.getSource();

            for (i=0; i<5; i++){
                for (j =0; j<5; j++) {
                    if (text == field[i][j]) {
                        flag = 1;
                        break;
                    }

                    //При заполнении или изменении значений на главной диагонали - меняем их значение на "1"
                    if(j==i && !field[i][j].getText().equals("1")) field[i][j].setText("1");
                }
                if (flag==1) break;
            }

            //При заполнении одного поля, заполняется симметричное текущему и данные значения затем конвертируются в числа и вбиваются в таблицу значений
            if(i!=j) {
                num = field[i][j].getText(); field[j][i].setText(fromStr(num));
                tables_temp[iter_but][i][j] = toDigit(field[i][j].getText());
                tables_temp[iter_but][j][i] = toDigit(field[j][i].getText());
            }
            //При заполнении или изменении значений на главной диагонали - меняем их значение на "1"
            else {field[i][j].setText("1"); tables_temp[iter_but][i][j] = 1; }

        }

        //Функция, конвертирующая введенные значения таблицы из String в double, при этом также обрабатывается симметричные поля
        //Например: (String) "2" && "1/2" -> (Double) 2 && 0.5
        private double toDigit(String num){if (!Objects.equals(num, "")) {
            double res = 0;
            StringBuilder temp = new StringBuilder();
            if (num.length()<2){
                res = Integer.parseInt(num);
            }
            else if (num.charAt(1) == '/') {
                for (int i = 2; i < num.length(); i++) temp.append(num.charAt(i));
                res = 1 / (Double.parseDouble(temp.toString()));
            } return res;
        } return 0;
        }


        //Функция, определяющая введенное значение и возвращающее симметричное значение матрицы: "2" -> "1/2", "1/2" -> "2"
        //При вводе "1/1" или "1" автоматически присваивает значение "1" для текущего и симметричного поля
        //Если число == x.0, то автоматически возвращается только x (необходимо для обработки случая повторного открытия таблиц)
        public String fromStr(String num){
            String temp = " ", res_str = " ";
            for(int i=0; i<num.length(); i++){
                if (num.charAt(i) == '/') {
                    for(int j=i+1; j<num.length(); j++){
                        res_str = (new StringBuilder(temp).insert(temp.length() ,num.charAt(j)).toString());
                    } return res_str;
                }
                else if (num.charAt(i) == '.') {
                    for (int j=0; j<i; j++)
                        res_str = (new StringBuilder(temp).insert(temp.length() ,num.charAt(j)).toString());
                    return "1/"+res_str;
                }
            } temp = "1/";
            res_str = (new StringBuilder(temp).insert(temp.length() ,num).toString());
            if (res_str.equals("1/1")) res_str = "1";
            return res_str;
        }
        public void keyReleased(KeyEvent e){ } public void keyTyped(KeyEvent e){ }
    }
}