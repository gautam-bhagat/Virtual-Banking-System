package com.gautam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Transactions extends JFrame implements ActionListener {
    String form_no;
    int i=0;
    JLabel l1;
    String[] btnurl={"deposit.png","withdraw.png","fastcash.png","balance.png","pinchange.png","ministatement.png","exit.png",
            "deposit1.png","withdraw1.png","fastcash1.png","balance1.png","pinchange1.png","ministatement1.png","exit1.png"};
    public Transactions(String formno){
        super("Transactions");
        this.form_no=formno;
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/images/transactions/transactionsbg.png"))));
        setLayout(null);

        l1=new JLabel("TRANSACTIONS");
        l1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                l1.setFont(new Font("MONTSERRAT EXTRABOLD", Font.BOLD,50)); }
            public void mouseExited(MouseEvent e) {
                l1.setFont(new Font("MONTSERRAT EXTRABOLD", Font.BOLD,40));}
        });
        l1.setForeground(Color.WHITE);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setVerticalAlignment(SwingConstants.CENTER);
        l1.setFont(new Font("MONTSERRAT EXTRABOLD", Font.BOLD,40));
        l1.setBounds(50,0,550,100);
        add(l1);

        for(i=0;i< btn.length;i++){
            btn[i] = new JButton(new ImageIcon(getClass().getResource("/images/transactions/"+btnurl[i])));
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
                    btn[i].setIcon((new ImageIcon(getClass().getResource("/images/transactions/"+btnurl[i+7]))));
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
                    btn[i].setIcon((new ImageIcon(getClass().getResource("/images/transactions/"+btnurl[i]))));
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
        setSize(650,550);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        Transactions t =new Transactions("1111");
    }

    public void actionPerformed(ActionEvent ae) {
        int bal=0;
        if (ae.getSource()==btn[0]){
            new Deposit(form_no).setVisible(true);
            setVisible(false);}
        if (ae.getSource()==btn[1]){
            new Withdraw(form_no).setVisible(true);
            setVisible(false);}
        if (ae.getSource()==btn[2]){
            new FastCash(form_no).setVisible(true);
            setVisible(false);}
        if (ae.getSource()==btn[3]) {
            try{
                Conn c1 = new Conn();
                String balance ="Select balance from bank where Form_no='"+form_no+"' order by tdate desc limit 1";
                ResultSet rs = c1.s.executeQuery(balance);
                while(rs.next()){
                    bal = Integer.parseInt(rs.getString("balance"));
                }
                JOptionPane.showMessageDialog(null,"Your Account balance is Rs "+bal);
            }
            catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
        }
        if (ae.getSource()==btn[4]){
            new PinChange(form_no).setVisible(true);
            setVisible(false);}
        if (ae.getSource()==btn[5]){
            new MiniStatement(form_no).setVisible(true);
            this.setVisible(false);
            }
        if (ae.getSource()==btn[6])
            System.exit(0);

    }
    private JButton b1,b2,b3,b4,b5,b6,b7;
    JButton[] btn={b1,b2,b3,b4,b5,b6,b7};
}
