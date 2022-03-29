package com.company;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class diagram extends JFrame {

    String[] str = {"The Witcher 3: Wild Hunt", "Sid Meier's Civilization V", "Cyberpunk 2077", "Grand Theft Auto V", "Fallout: New Vegas"};
    int[] result = new int[5];
    double[]res1;
    public diagram(double[]res) {
        setLayout(null);
        setResizable(false);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(40,40,40));
        setVisible(true);

        res1 = res;
        for (int i=0; i<result.length; i++){
            result[i] = (int) (res[i]*1000);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setFont(new Font("Times new Roman", Font.PLAIN, 15));
        gr2d.setColor(Color.LIGHT_GRAY);
        gr2d.drawLine(100, 100, 100, 950);
        gr2d.drawLine(100, 950, 950, 950);

        MathContext context = new MathContext(3, RoundingMode.HALF_UP);
        for (int i=0; i<result.length; i++){
            gr2d.setColor(new Color(50, 129, 249));
            gr2d.fill3DRect(160+(165*i), 950-Math.round(result[i]*2), 60, Math.round(result[i]*2), true);
            gr2d.setColor(Color.WHITE);
            gr2d.drawLine(30, 950-Math.round(result[i]*2), 219+(165*i), 950-Math.round(result[i]*2));
            gr2d.drawString("Альтернатива " + String.valueOf(i+1), 145+(165*i), 965);
            gr2d.drawString(str[i], 145+(165*i), 990);
            gr2d.drawString(String.valueOf(new BigDecimal(res1[i], context)), 35, 945-Math.round(result[i]*2));
        }
    }
}
