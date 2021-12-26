package com.gautam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {
    JLabel l1,l2,l3;
    JTextField tf1;
    JButton b1,b2;
    String formno;
    public Withdraw(String card){
        super("Withdraw");
        this.formno=card;
        setLayout(null);
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/images/withdraw/bg.png"))));
        l1 = new JLabel("WITHDRAWAL",JLabel.CENTER);
        l1.setFont(new Font("montserrat extrabold",Font.BOLD,45));
        l2 = new JLabel("Enter the amount you want to Withdraw ",JLabel.CENTER);
        l2.setFont(new Font("montserrat",Font.BOLD,25));
        l3 = new JLabel("Maximum amount allowed for Withdraw at a time is Rs 10,000",JLabel.CENTER);
        l3.setFont(new Font("calibri",Font.BOLD,18));
        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);

        tf1 = new JTextField();
        tf1.setFont(new Font("product sans medium",Font.PLAIN,22));
        tf1.setOpaque(false);
        tf1.setCaretColor(Color.WHITE);
        tf1.setForeground(Color.WHITE);
        tf1.setBorder(null);
        tf1.setBounds(100,150,450,50);
        tf1.setHorizontalAlignment(SwingConstants.CENTER);
        tf1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char e = ke.getKeyChar();
                if(!Character.isDigit(e))
                    ke.consume();
            }
        });
        add(tf1);
        JPanel l = new JPanel();
        l.setBounds(100,200,450,2);
        l.setBackground(Color.WHITE);
        add(l);
        l1.setBounds(0,20,650,80);
        add(l1);
        l2.setBounds(0,100,650,30);
        add(l2);
        l3.setBounds(0,200,650,30);
        add(l3);

        for (int i=0;i<btn.length;i++){
            btn[i]=new JButton(new ImageIcon(getClass().getResource("/images/withdraw/"+btnurl[i])));
            btn[i].setContentAreaFilled(false);
            btn[i].setBorderPainted(false);
            add(btn[i]);
            btn[i].addActionListener(this);
            btn[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    int i=0;
                    if(e.getSource()==btn[0]) i=0;
                    if(e.getSource()==btn[1]) i=1;
                    btn[i].setIcon((new ImageIcon(getClass().getResource("/images/withdraw/"+btnurl[i+2]))));
                }
                public void mouseExited(MouseEvent e) {
                    int i=0;
                    if(e.getSource()==btn[0]) i=0;
                    if(e.getSource()==btn[1]) i=1;
                    btn[i].setIcon((new ImageIcon(getClass().getResource("/images/withdraw/"+btnurl[i]))));
                }
            });
        }
        btn[0].setBounds(100,300,200,50);
        btn[1].setBounds(350,300,200,50);
        setResizable(false);
        setSize(650,450);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Withdraw("");
    }
    JButton[] btn={b1,b2};
    String[] btnurl={"withdraw.png","back.png","withdraw1.png","back1.png"};
    public void actionPerformed(ActionEvent e) {
        int amount,bal = 0;
        String withdraw="";
        if(e.getSource()==btn[0]){
            try{
                Conn c1= new Conn();
                Date date = new Date();
                SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
                String tdate = dt.format(date);
                withdraw = tf1.getText();
                if(withdraw.equals("")){
                    JOptionPane.showMessageDialog(null,"Enter Amount to Withdraw");return;
                }
                amount=Integer.parseInt(withdraw);
                if(amount>10000){
                    JOptionPane.showMessageDialog(null,"You can't withdraw amount over Rs 10,000");
                    return;
                }
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
            catch(Exception ex){JOptionPane.showMessageDialog(null,"Error Occurred.");}
        }
        else{
            setVisible(false);
            new Transactions(formno).setVisible(true);
        }
    }
}
