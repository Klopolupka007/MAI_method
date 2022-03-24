package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class frame extends JFrame {
    private JLabel jLabel, jLabel1; JFrame frame;
    public JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    public frame(){
        frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(626, 645);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        graphs fields = new graphs();
        frame.add(fields, null);
        TextFilling();
    }
    public JTextField field[];
    public void TextFilling(){
        field = new JTextField[25]; Font font = new Font("Times new Roman", Font.BOLD ,30);
        textChangedListener listener = new textChangedListener();
        for (int i = 0, j=0, k=0; i< field.length; i++, k++) {field[i] = new JTextField();
            if (k==j)
                field[i].setText("1");
            if (i % 5 == 0 && i!=0) {j++; k = 0;}
            field[i].setBounds(5+(100*(k+1)), 5+(100*(j+1)), 95, 95);
            field[i].setHorizontalAlignment(JTextField.CENTER); field[i].setFont(font);
            frame.add(field[i]);
            field[i].addKeyListener(listener);
        } // создаем 25 элементов TextField
    }
    class textChangedListener implements KeyListener
    {
        public void keyPressed(KeyEvent e){
            int iter=0, stepen5, stepen2;
            String num;
            JTextField text = (JTextField)e.getSource();
            for (int i=0; i<field.length; i++){
                if (text == field[i]) {iter = i; break;}
            }
            //ищем степень 5, т.е. индекс строки
            stepen5 = iter/5;
            //ищем степень 2, т.е. максимальная степень для итерации к симметричным элементам матрицы
            stepen2 = 4-stepen5;
            if (iter == stepen2) { field[iter].setText("1"); }
            else if (iter > stepen2) { num = field[iter].getText(); field[(int) Math.abs(Math.pow(5, (stepen5+1))-1-iter)].setText(fromStr(num));}
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
            System.out.println(res_str);
            return res_str;
        }
        public void keyReleased(KeyEvent e){}

        public void keyTyped(KeyEvent e){}
    }
}

class graphs extends JPanel{
        public void paintComponent(Graphics g) {
            Font font = new Font("Times new Roman", Font.BOLD ,30);
            g.setFont(font);
            for (int i=0; i<6;i++) {
                g.setColor(new Color(170, 204, 67));
                g.fillRect(100*i+5, 5, 95, 95);
                g.fillRect(5, 100*i+5, 95, 95);
                //g.setColor(new Color (191, 230, 75));
                //g.fillRect(100*i+5, 100*i+5, 95, 95);
                g.setColor(Color.DARK_GRAY);
                if (i!=0) {
                    g.drawString(Integer.toString(i), i * 100 + 45, 60);
                    g.drawString(Integer.toString(i), 40, i * 100 + 60);
                    //g.drawString(Integer.toString(1), i * 100 + 45, i * 100 + 60);
                }
            }
        }
}
