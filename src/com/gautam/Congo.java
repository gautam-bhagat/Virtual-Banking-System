package com.gautam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Congo extends JFrame {
    JLabel back,gif,acc_created,acc_no,account,pinacc,pinlbl;
    String formno,account_no,pin;
    JButton btn;
    Congo(String formno,String account_no,String pin){
        this.formno=formno;
        this.account_no=account_no;
        this.pin=pin;
        setUndecorated(true);
        setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700,500);
        setLayout(null);
        back = new JLabel(new ImageIcon(getClass().getResource("/images/congo/dialogback.png")));
        back.setBounds(0,0,700,500);
        add(back);
        gif=new JLabel(new ImageIcon(getClass().getResource("/images/congo/congrats.gif")));
        gif.setBounds(150,0,400,250);
        back.add(gif);

        acc_created=new JLabel("Congratulations, Your Account has been created",JLabel.CENTER);
        acc_no=new JLabel("Account No : ",JLabel.RIGHT);
        pinlbl=new JLabel("Pin : ",JLabel.RIGHT);
        account=new JLabel(account_no,JLabel.LEFT);
        pinacc=new JLabel(pin,JLabel.LEFT);

        acc_created.setBounds(0,250,700,50);
        acc_no.setBounds(0,300,350,40);
        pinlbl.setBounds(0,340,350,40);

        account.setBounds(350,300,350,40);
        pinacc.setBounds(350,340,350,40);

        acc_created.setFont(new Font("cambria",Font.PLAIN,24));
        acc_no.setFont(new Font("cambria",Font.PLAIN,22));
        pinlbl.setFont(new Font("cambria",Font.PLAIN,22));
        account.setFont(new Font("cambria",Font.PLAIN,22));
        pinacc.setFont(new Font("cambria",Font.PLAIN,22));

        btn=new JButton(new ImageIcon(getClass().getResource("/images/congo/continue.png")));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/congo/continue1.png")));
            }
            public void mouseExited(MouseEvent e) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/congo/continue.png")));
            }
        });
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Deposit(formno).setVisible(true);
            }
        });
        btn.setFont(new Font("montserrat extrabold",Font.PLAIN,16));
        btn.setBounds(250,400,200,50);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBorder(null);

        back.add(acc_created);
        back.add(acc_no);
        back.add(pinlbl);
        back.add(account);
        back.add(pinacc);
        back.add(btn);

        setLocationRelativeTo(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        Congo c=new Congo("1111","11111111111","1111");
    }
}
