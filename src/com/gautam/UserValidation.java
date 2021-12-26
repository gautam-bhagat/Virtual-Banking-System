package com.gautam;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

import static java.awt.Color.decode;

public class UserValidation extends JFrame {
    JLabel back,process,validate,name;
    String form_no,fullname;
    public UserValidation(String form_no) {
        this.form_no=form_no;
        setLayout(null);
        setSize(700,500);
        setUndecorated(true);
        setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        back = new JLabel(new ImageIcon(getClass().getResource("/images/congo/dialogback.png")));
        back.setBounds(0,0,700,500);
        add(back);
        back.setLayout(null);
        validate = new JLabel(new ImageIcon(getClass().getResource("/images/congo/validate.gif")));
        validate.setBounds((250),280,400,50);
        back.add(validate);
        process = new JLabel(new ImageIcon(getClass().getResource("/images/congo/process.gif")));
        process.setBounds(250,60,200,200);
        back.add(process);
        String s = "select full_name from signup where form_no= "+form_no+";";
        try{
            Conn c1=new Conn();
            ResultSet rs= c1.s.executeQuery(s);
            while (rs.next()){
                fullname=rs.getString("full_name");
            }
        }
        catch (Exception e){e.printStackTrace();}

        name=new JLabel("WELCOME, "+fullname,JLabel.CENTER);
        name.setFont(new Font("MONTSERRAT EXTRABOLD", Font.PLAIN,28));
        name.setForeground(decode("#137bdd"));
        name.setBounds(0,400,700,50);
        back.add(name);
        setVisible(true);

        try{Thread.sleep(6000);new Transactions(form_no).setVisible(true); }catch(Exception ignored){}
    }

    public static void main(String[] args) {
        new UserValidation("1111");
    }
}
