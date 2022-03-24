package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class frame extends JFrame {
    private JLabel jLabel, jLabel1;
    public JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    public frame(){
        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(626, 645);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        graphs fields = new graphs();
        frame.add(fields, null);
            t1 = new JTextField();
            Font font = new Font("Times new Roman", Font.BOLD ,30); t1.setBounds(210,110, 85, 85);
            t1.setHorizontalAlignment(JTextField.CENTER); t1.setFont(font);
            frame.setLayout(null);
            frame.add(t1);

            t2 = new JTextField(); t2.setBounds(315,110, 85, 85);t2.setBackground(Color.LIGHT_GRAY);
            t2.setHorizontalAlignment(JTextField.CENTER); t2.setFont(font); frame.add(t2);
        t1.addKeyListener(new textChangedListener());
        t2.addKeyListener(new textChangedListener());
    }
    class textChangedListener implements KeyListener
    {
        public void keyPressed(KeyEvent e){
            String str = t1.getText();
            t2.setText("1/"+str);
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
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(100*i+5, 5, 95, 95);
                g.fillRect(5, 100*i+5, 95, 95);
                g.fillRect(100*i+5, 100*i+5, 95, 95);
                g.setColor(Color.DARK_GRAY);
                if (i!=0) {
                    g.drawString(Integer.toString(i), i * 100 + 45, 60);
                    g.drawString(Integer.toString(i), 40, i * 100 + 60);
                    g.drawString(Integer.toString(1), i * 100 + 45, i * 100 + 60);
                }
            }
        }
}
