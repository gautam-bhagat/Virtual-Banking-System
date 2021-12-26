package com.gautam;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JFrame {

    JProgressBar progress;
    JLabel l2,l3;
    Welcome(){
        this.setLayout(null);
        this.setContentPane(new JLabel((new ImageIcon(getClass().getResource("/images/welcome/background.png")))));
        this.setUndecorated(true);
        this.setVisible(true);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);

        int i = 0;

        l2=new JLabel("Loading ...",JLabel.LEADING);
        l2.setFont(new Font("Calibri",Font.PLAIN,22));
        l2.setForeground(Color.decode("#137bdd"));
        l2.setBounds(100,500,300,20);

        l3=new JLabel("0 %",JLabel.RIGHT);
        l3.setBounds(620,500,80,20);
        l3.setFont(new Font("calibri",Font.PLAIN,22));
        l3.setForeground(Color.decode("#137bdd"));

        progress =new JProgressBar(0,100);
        progress.setForeground(Color.decode("#137bdd"));
        progress.setBorderPainted(false);
        progress.setOpaque(false);
        progress.setBounds(-10,-10,620,70);

        JPanel pl = new JPanel();
        pl.setBackground(Color.LIGHT_GRAY);
        pl.setBounds(100,468,600,30);
        pl.setLayout(null);
        add(pl);
        pl.add(progress);
        add(l2);
        add(l3);
        while(i <=100){
            try{
                progress.setValue(i);
                if(i==10)
                    l2.setText("Turning on Modules ...");
                if(i==20)
                    l2.setText("Loading Modules ...");
                if(i==50)
                    l2.setText("Connecting to Database ...");
                if(i==70)
                    l2.setText("Connection Successful !");
                if(i==80)
                    l2.setText("Launching Application ...");

                Thread.sleep(100);
                i+=1;
                if(i%10==0)
                    l3.setText(i+" %");
            }
            catch(Exception ignored){}
        }
        dispose();
        new Login();
    }


    public static void main(String[] args) {
        Welcome wel = new Welcome();

    }
}
