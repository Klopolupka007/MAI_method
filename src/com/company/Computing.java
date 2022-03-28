package com.company;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

import static java.lang.Math.pow;

public class Computing extends JFrame {
    JFrame frame = new JFrame("Расчеты");
    public Computing (double [][][] W, double [] result, double tables[][][]){
        Arrays.fill(result, 0);
        frame.setLayout(null);
        frame.setSize(500,900);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(40,40,40));

        JTextArea Text = new JTextArea("Расчеты.\n");
        Text.setFont(new Font( "Times new Roman", Font.PLAIN, 16));
        Text.setBounds(10, 10, 465, 843);
        //Text.setEditable(false);
        Text.setBackground(new Color(240, 146, 146));
        Text.setLineWrap(true);
        Text.setVisible(true);

        //Добавляем scroll на текстовую панель
        JScrollPane scroll = new JScrollPane(Text);
        scroll.setBounds(10, 10, 465, 843);
       // scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        frame.add(scroll);


        MathContext context = new MathContext(3, RoundingMode.HALF_UP);
        //Записываем расчеты для V, W, СЗ, ИС и ОС в TextArea
        //Для V и W
        StringBuilder calculations = new StringBuilder("");
        for (int i=0; i<6; i++){
            //Для V(i)
            for (int j =0; j<5;j++) {
                Text.append("\nV(К"); Text.append(String.valueOf(i+1)); Text.append(String.valueOf(j+1)); Text.append(") = (");
                for (int k =0; k<5; k++){
                    Text.append(String.valueOf(new BigDecimal(tables[i][j][k],context)));
                    if (k!=4) Text.append(" x ");
                    else Text.append(")^1/5 = ");
                }
                calculations.append(String.valueOf(new BigDecimal(W[i][j][0], context)));
                if (j == 4) calculations.append(" = "); else calculations.append(" + ");
                Text.append(String.valueOf(new BigDecimal(W[i][j][0], context)));
            }
            calculations.append(String.valueOf(new BigDecimal(W[i][5][0], context)));
            Text.append("\nСумма V(К"); Text.append(String.valueOf(i+1));
            Text.append("Y) = ");
            Text.append(String.valueOf(calculations));
            Text.append("\n");
            calculations = new StringBuilder("");
            //Для W(i)
            for (int j =0; j<5; j++){
                Text.append("W(3К"); Text.append(String.valueOf(i+1)); Text.append(String.valueOf(j+1));
                Text.append(") = "); Text.append(String.valueOf(new BigDecimal(W[i][j][0], context))); Text.append("/");
                Text.append(String.valueOf(new BigDecimal(W[i][5][0], context))); Text.append(" = ");
                Text.append(String.valueOf(new BigDecimal(W[i][j][1], context))); Text.append("\n");
            }
        } Text.append("\n");

        //Делаем главные расчеты и записываем их в TextArea


        for (int i =0; i<6; i++){
            calculations = new StringBuilder(" = ");
            for (int j=1; j<6; j++){
                result[i] += W[0][j-1][1]*W[j][i][1];
                calculations.append(String.valueOf(new BigDecimal(W[0][j - 1][1], context))); calculations.append(" x ");
                calculations.append(String.valueOf(new BigDecimal(W[j][i][1], context)));
                if (j!=5) calculations.append(" + ");
                else calculations.append(" = ");
                if(j==1){
                    Text.append("\nW"+String.valueOf(i+1)+" = W2" + String.valueOf(i+1) + " x W3К"+String.valueOf(j)+String.valueOf(i+1));
                }
                else {
                    Text.append(" + W2" + String.valueOf(i + 1) + " x W3К" + String.valueOf(j) + String.valueOf(i + 1));
                }
            }
            Text.append(String.valueOf(calculations)); Text.append(String.valueOf(new BigDecimal(result[i], context)));
        }


    }

}
