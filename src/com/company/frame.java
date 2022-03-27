package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class frame extends JFrame {
    private JFrame frame;
    public frame(double [][][] tables){
        frame = new JFrame("Table");
        frame.setSize(900, 645);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        TextFilling();
    }
    private JTextField[][] field;
    private void TextFilling(){
        field = new JTextField[5][5]; Font font = new Font("Times new Roman", Font.BOLD ,30);
        textChangedListener listener = new textChangedListener();
        for (int i = 0; i< 5; i++) {
            for (int j=0; j<5; j++) {
                field[i][j] = new JTextField();
                field[i][j].setBounds(100*(j+1)+5, 100*(i+1)+5, 95,95);
                if (i==j) field[i][j].setText("1");
                field[i][j].setHorizontalAlignment(JTextField.CENTER); field[i][j].setFont(font);
                frame.add(field[i][j]); field[i][j].addKeyListener(listener);
            }
        }
        JLabel lab[][] = new JLabel[2][5];
        for (int i=0; i<2;i++){
                for (int j=0; j<5;j++) {
                    lab[i][j] = new JLabel(Integer.toString(j+1));
                    if (i==0) lab[i][j].setBounds(100 * (j+1) + 5, 5, 95, 95);
                    else lab[i][j].setBounds(5, 100 * (j+1) + 5, 95, 95);
                    lab[i][j].setOpaque(true);
                    lab[i][j].setBackground(new Color(204, 204, 204));
                    lab[i][j].setFont(font); lab[i][j].setHorizontalAlignment(JLabel.CENTER);
                    frame.add(lab[i][j]);
                }
        }
    }
    class textChangedListener implements KeyListener
    {
        public void keyPressed(KeyEvent e){
            String num;
            int i =0, j=0, flag=0;
            JTextField text = (JTextField)e.getSource();
            for (i=0; i<5; i++){
                for (j =0; j<5; j++) {
                    if (text == field[i][j]) {
                        flag = 1;
                        break;
                    }
                    if(j==i && !field[i][j].getText().equals("1")) field[i][j].setText("1");
                }
                if (flag==1) break;
            }
            if(i!=j) {num = field[i][j].getText(); field[j][i].setText(fromStr(num));}
            else field[i][j].setText("1");
        }
        public String fromStr(String num){
            String temp = " ", res_str = " ";
            for(int i=0; i<num.length(); i++){
                if (num.charAt(i) == '/') {
                    for(int j=i+1; j<num.length(); j++){
                        res_str = (new StringBuilder(temp).insert(temp.length() ,num.charAt(j)).toString());
                    } return res_str;
                }
            } temp = "1/";
            res_str = (new StringBuilder(temp).insert(temp.length() ,num).toString());
            if (res_str.equals("1/1")) res_str = "1";
            return res_str;
        }
        public void keyReleased(KeyEvent e){ } public void keyTyped(KeyEvent e){ }
    }
}