package com.gautam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    String formno;
    JLabel l1,l8;
    JButton b1,b2,b3,b4,b5,b6,b7;
    JButton[] btn={b1,b2,b3,b4,b5,b6,b7};
    String[] btnurl={"r100.png","r200.png","r500.png","r1000.png","r2000.png","r5000.png","rback.png",
            "r_100.png","r_200.png","r_500.png","r_1000.png","r_2000.png","r_5000.png","r_back.png"};
    String[] btnamt={"100","200","500","1000","2000","5000",""};
    public FastCash(String card){
        super("Fast Cash");
        formno=card;
        setLayout(null);
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/images/fastcash/bg.png"))));
        l1=new JLabel("FAST CASH");
        l1.setForeground(Color.WHITE);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setVerticalAlignment(SwingConstants.CENTER);
        l1.setFont(new Font("MONTSERRAT EXTRABOLD", Font.BOLD,40));
        l1.setBounds(50,0,550,100);
        add(l1);
        int i;
        for(i=0;i< btn.length;i++){
            btn[i] = new JButton(new ImageIcon(getClass().getResource("/images/fastcash/"+btnurl[i])));
            btn[i].setOpaque(false);
            btn[i].setContentAreaFilled(false);
            btn[i].setBorderPainted(false);
            add(btn[i]);
            btn[i].addActionListener(this);
            btn[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    int i=0;
                    if(e.getSource()==btn[0]) i=0;
                    if(e.getSource()==btn[1]) i=1;
                    if(e.getSource()==btn[2]) i=2;
                    if(e.getSource()==btn[3]) i=3;
                    if(e.getSource()==btn[4]) i=4;
                    if(e.getSource()==btn[5]) i=5;
                    if(e.getSource()==btn[6]) i=6;
                    btn[i].setIcon((new ImageIcon(getClass().getResource("/images/fastcash/"+btnurl[i+7]))));
                }
                public void mouseExited(MouseEvent e) {
                    int i=0;
                    if(e.getSource()==btn[0]) i=0;
                    if(e.getSource()==btn[1]) i=1;
                    if(e.getSource()==btn[2]) i=2;
                    if(e.getSource()==btn[3]) i=3;
                    if(e.getSource()==btn[4]) i=4;
                    if(e.getSource()==btn[5]) i=5;
                    if(e.getSource()==btn[6]) i=6;
                    btn[i].setIcon((new ImageIcon(getClass().getResource("/images/fastcash/"+btnurl[i]))));
                }
            });
        }
        btn[0].setBounds(50,100,200,50);
        btn[1].setBounds(400,100,200,50);
        btn[2].setBounds(50,200,200,50);
        btn[3].setBounds(400,200,200,50);
        btn[4].setBounds(50,300,200,50);
        btn[5].setBounds(400,300,200,50);
        btn[6].setBounds(175,400,300,60);

        setResizable(false);
        setSize(650,565);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new FastCash("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==btn[6]){
            setVisible(false);
            new Transactions(formno).setVisible(true);}
        else{
            String fastcash="0";
            if(ae.getSource()==btn[0]) fastcash="100";
            else if(ae.getSource()==btn[1]) fastcash="200";
            else if(ae.getSource()==btn[2]) fastcash="500";
            else if(ae.getSource()==btn[3]) fastcash="1000";
            else if(ae.getSource()==btn[4]) fastcash="2000";
            else if(ae.getSource()==btn[5]) fastcash="5000";
            try{
                Integer amount = Integer.parseInt(fastcash);
                Integer bal=0;
                Conn c1= new Conn();
                Date date = new Date();
                SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
                String tdate = dt.format(date);
                String balance ="Select balance from bank where Form_no='"+formno+"' order by tdate desc limit 1";
                ResultSet rs = c1.s.executeQuery(balance);
                while(rs.next()){
                    bal = Integer.parseInt(rs.getString("balance"));
                }
                if(amount>bal){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    setVisible(false);
                    new Transactions(formno).setVisible(true);
                    return;
                }
                bal-=amount;
                String query = "INSERT INTO BANK VALUES('"+formno+"','"+tdate+"','WITHDRAW','"+amount+"','"+bal+"');";
                int r = c1.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs "+amount+" withdrawn successfully.");
                setVisible(false);
                new Transactions(formno).setVisible(true);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
}
