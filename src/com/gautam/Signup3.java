package com.gautam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {
    String form_no;
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JLabel[] lbl={l1,l2,l3,l4,l5,l6,l7};
    String[] lblurl={"ACCOUNT DETAILS","Account Type :","Your Account Number :","XXXX-XXXX-",
            "Account PIN","XXXX","Services :"};
    JRadioButton r1,r2,r3,r4;
    JRadioButton [] r_btn={r1,r2,r3,r4};
    String[] r_btnurl={"Saving Account","Current Account","Fixed Deposit Account","Recurring Deposit Account"};
    ButtonGroup a1;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    JCheckBox [] cb={c1,c2,c3,c4,c5,c6,c7};
    JButton b1,b2;
    JButton [] btn={b1,b2};
    String[] btnurl={"submit.png","cancel.png"};
    public Signup3( String formno){
        this.form_no=formno;
        setLayout(null);
        int i=0;
        getContentPane().setBackground(Color.decode("#137bdd"));

        for(i=0;i<lbl.length;i++){
            lbl[i] = new JLabel(lblurl[i]);
            lbl[i].setFont(new Font("Montserrat semibold",Font.PLAIN,22));
            add(lbl[i]);
            lbl[i].setForeground(Color.WHITE);
        }
        lbl[0].setHorizontalAlignment(SwingConstants.CENTER);
        lbl[0].setFont(new Font("montserrat extrabold",Font.PLAIN,45));
        lbl[0].setBounds(0,30,800,60);
        lbl[1].setBounds(100,100,300,50);
        lbl[2].setBounds(100,300,300,50);
        lbl[3].setBounds(400,300,300,50);
        lbl[3].setText(lbl[3].getText()+formno);
        lbl[4].setBounds(100,350,300,50);
        lbl[5].setBounds(400,350,300,50);
        lbl[6].setBounds(100,420,300,50);

        for(i=0;i<r_btn.length;i++){
            r_btn[i]= new JRadioButton(r_btnurl[i]);
            r_btn[i].setForeground(Color.WHITE);
            r_btn[i].setFont(new Font("montserrat semibold",Font.PLAIN,20));
            add(r_btn[i]);
            r_btn[i].setBackground(Color.decode("#137bdd"));
        }
        r_btn[0].setBounds(100,150,300,50);
        r_btn[1].setBounds(400,150,300,50);
        r_btn[2].setBounds(100,200,300,50);
        r_btn[3].setBounds(400,200,400,50);
        a1=new ButtonGroup();
        a1.add(r_btn[0]);
        a1.add(r_btn[1]);
        a1.add(r_btn[2]);
        a1.add(r_btn[3]);

        cb[0] = new JCheckBox("ATM Card");
        cb[1] = new JCheckBox("Net Banking");
        cb[2] = new JCheckBox("Mobile Banking");
        cb[3] = new JCheckBox("Cheque Book");
        cb[4] = new JCheckBox("E-Mail Alerts");
        cb[5] = new JCheckBox("E-Statement");
        cb[6] = new JCheckBox("I hereby, declare all the details entered are genuine and best known to me.");
        for(i=0;i<cb.length;i++){
            cb[i].setFont(new Font("montserrat semibold",Font.PLAIN,20));
            cb[i].setBackground(Color.decode("#137bdd"));
            cb[i].setForeground(Color.WHITE);
            add(cb[i]);
        }

        cb[0].setBounds(100,470,200,30);
        cb[1].setBounds(400,470,200,30);
        cb[2].setBounds(100,500,200,30);
        cb[3].setBounds(400,500,200,30);
        cb[4].setBounds(100,530,200,30);
        cb[5].setBounds(400,530,200,30);
        cb[6].setBounds(100,580,600,20);
        cb[6].setFont(new Font("montserrat semibold",Font.PLAIN,15));

        for(i=0;i<btn.length;i++){
            btn[i]=new JButton(new ImageIcon(getClass().getResource("/images/signup/"+btnurl[i])));
            btn[i].setBorderPainted(false);
            btn[i].setContentAreaFilled(false);
            btn[i].addActionListener(this);
            add(btn[i]);
        }
        btn[0].setBounds(100,620,200,50);
        btn[1].setBounds(500,620,200,50);

        setResizable(false);
        setVisible(true);
        setSize(800,750);
        setLocation(300,0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae){
        String account_type=null,services="",account_no="",pin="";
        if(r_btn[0].isSelected())
            account_type=r_btn[0].getText().toUpperCase();
        else if(r_btn[1].isSelected())
            account_type=r_btn[1].getText().toUpperCase();
        else if(r_btn[2].isSelected())
            account_type=r_btn[2].getText().toUpperCase();
        else if(r_btn[3].isSelected())
            account_type=r_btn[3].getText().toUpperCase();

        if(cb[0].isSelected())
            services+=" ATM_CARD";
        if(cb[1].isSelected())
            services+= " NET_BANKING";
        if(cb[2].isSelected())
            services+=" MOBILE_BANKING";
        if(cb[3].isSelected())
            services+=" CHEQUE_BOOK";
        if(cb[4].isSelected())
            services+=" E-MAIL_ALERTS";
        if(cb[5].isSelected())
            services+=" E-STATEMENT";

        cb[6].setSelected(true);

        Random r = new Random();
        String digit4="";
        int l;
        for(int i=0;i<4;i++){
            l = r.nextInt(9);
            digit4 = digit4 + l;
        }

        account_no= "5916"+(digit4+form_no);
        int p;
        for(int i=0;i<4;i++){
            p = r.nextInt(9);
            pin+=p;
        }

        String sq="INSERT INTO SIGNUP3 VALUES('"+form_no+"','"+account_type+"','"+services+"','"+account_no+"');";
        String lq="INSERT INTO LOGIN VALUES('"+form_no+"','"+account_no+"','"+pin+"')";

        if(ae.getSource()==btn[0]) {
            if(account_type==""||services==""||account_no==""||pin=="")
                JOptionPane.showMessageDialog(null,"Fill up all Fields.");
            else{
                try {
                    Conn c1= new Conn();
                    c1.s.executeUpdate(sq);
                    c1.s.executeUpdate(lq);
                    setVisible(false);
                    new Congo(form_no,account_no,pin).setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        }
        if(ae.getSource()==btn[1]) {
            try {
                Conn c2 = new Conn();
                c2.s.executeUpdate("delete from signup where form_no='" + form_no + "';");
                c2.s.executeUpdate("delete from signup2 where form_no='" + form_no + "';");
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new Signup3("3063");
    }
}
