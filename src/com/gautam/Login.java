package com.gautam;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import static java.awt.Color.*;

public class Login extends JFrame implements ActionListener {
    public Login(){
        setLayout(null);
        this.getContentPane().setBackground(Color.decode("#137bdd"));
        setUndecorated(true);
        setVisible(true);
        int i=0;
        for(i=0;i<label.length;i++){
            label[i] = new JLabel(labels[i]);
            label[i].setForeground(WHITE);
            label[i].setFont(new Font("Montserrat semibold",Font.PLAIN,24));
            add(label[i]);
        }
        label[0].setFont(new Font("Montserrat ExtraBold", Font.BOLD,60));
        label[0].setBounds(20,30,500,100);
        label[0].setHorizontalAlignment(SwingConstants.LEADING);
        label[1].setBounds(50,150,200,20);
        label[2].setBounds(50,200,200,20);
        
        int j=0;
        for(j=0;j<btn.length;j++){
            btn[j]=new JButton(new ImageIcon(getClass().getResource("/images/login/"+btn_img[j])));
            btn[j].setBorderPainted(false);
            btn[j].setOpaque(false);
            btn[j].setContentAreaFilled(false);
            btn[j].addActionListener(this);
            add(btn[j]);
            btn[j].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    int j=0;
                    if(e.getSource()==btn[0]) j=0;
                    if(e.getSource()==btn[1]) j=1;
                    if(e.getSource()==btn[2]) j=2;
                    if(e.getSource()==btn[3]) return;

                    btn[j].setIcon(new ImageIcon(getClass().getResource("/images/login/"+btn_img[j+4])));
                }
                public void mouseExited(MouseEvent e) {
                    int j=0;
                    if(e.getSource()==btn[0]) j=0;
                    if(e.getSource()==btn[1]) j=1;
                    if(e.getSource()==btn[2]) j=2;
                    btn[j].setIcon(new ImageIcon(getClass().getResource("/images/login/"+btn_img[j])));
                }});
        }
        btn[0].setBounds(60,270,150,60);
        btn[2].setBounds(290,270,150,60);
        btn[1].setBounds(150,350,200,60);
        btn[3].setBounds(450,0,50,50);
        tf = new JTextField();
        tf.setCaretColor(WHITE);
        tf.setFont(new Font("product sans medium",Font.PLAIN,24));
        tf.setBounds(250,150,200,20);
        tf.setBorder(null);
        tf.setOpaque(false);
        tf.setDocument(new JTextFieldLimit(12));
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        tf.setForeground(Color.WHITE);
        tf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char e = ke.getKeyChar();
                if(!Character.isDigit(e))
                    ke.consume();
            }
        });

        pf = new JPasswordField(4);
        pf.setFont(new Font("product sans medium",Font.PLAIN,40));
        pf.setBounds(250,200,200,20);
        pf.setBorder(null);
        pf.setOpaque(false);
        pf.setCaretColor(WHITE);
        pf.setForeground(Color.WHITE);
        pf.setDocument(new JTextFieldLimit(4));
        pf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char e = ke.getKeyChar();
                if(!Character.isDigit(e))
                    ke.consume();
            }
        });
        pf.setHorizontalAlignment(SwingConstants.CENTER);
        line1=new JPanel();
        line1.setBounds(250,172,200,2);
        line1.setBackground(Color.WHITE);
        line2=new JPanel();
        line2.setBounds(250,222,200,2);
        line2.setBackground(Color.WHITE);

        add(tf);
        add(pf);
        add(line1);
        add(line2);
        setResizable(false);
        setSize(500,450);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String account_no,pin;
        account_no = tf.getText();
        pin = pf.getText();
                if(e.getSource()==btn[0]) {
                    try {
                    Conn c1 = new Conn();
                    String q =  "select * from login where account_no = '"+account_no+"' and pin = '"+pin+"';";
                    ResultSet rs = c1.s.executeQuery(q);

                    while(rs.next()) {
                        this.setVisible(false);
                        new Transactions(account_no.substring(8)).setVisible(true);
                        return;
                    }
                    JOptionPane.showMessageDialog(null,"Wrong Card or Pin");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                }else if(e.getSource()==btn[2]){
                    tf.setText("");
                    pf.setText("");
                }else if(e.getSource()==btn[1]){
                    new Signup().setVisible(true);
                    setVisible(false);
                }
                else if (e.getSource()==btn[3])
                    System.exit(0);

        }
    public static void main(String[] args) {
        Login l = new Login();
    }
    private JTextField tf;
    private JPasswordField pf;
    private JPanel line1,line2;
    private JLabel l1,l2,l3;
    private JButton signin,signup,clr,cross;
    JLabel[] label ={l1,l2,l3};
    JButton[] btn={signin,signup,clr,cross};
    String[] btn_img = {"signin.png","signup.png","clear.png","cross.png","signin1.png","signup1.png","clear1.png"};
    String[] labels={"LOGIN","ACCOUNT NO :","PIN :"};
}
class JTextFieldLimit extends PlainDocument{
    private int limit;
    public JTextFieldLimit(int limit){
        this.limit=limit;
    }
    public void insertString(int offset, String str, AttributeSet set) throws BadLocationException{
        if(str==null)
            return;
        else if((getLength()+str.length())<= limit){
            str=str.toUpperCase();
            super.insertString(offset,str,set);
        }
    }
}
