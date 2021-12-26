package com.gautam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.Color.WHITE;

public class PinChange extends JFrame implements ActionListener {
    JLabel l1,l2,l3;
    JPasswordField t1,t2;
    JButton b1,b2;
    String formno;
    JPasswordField[] pf={t1,t2};
    JLabel[] label={l1,l2,l3};
    JButton[] btn={b1,b2};
    String[] btnurl={"change.png","back.png","change1.png","back1.png"};
    String[] labels={"CHANGE YOUR PIN","New PIN:","Re-Enter New PIN:"};
    public PinChange(String card){

        super("Pin Change");
        getContentPane().setBackground(Color.decode("#137bdd"));
        int i=0;
        formno=card;
        for(i=0;i<label.length;i++){
            label[i] = new JLabel(labels[i],JLabel.LEADING);
            label[i].setFont(new Font("Montserrat semibold",Font.PLAIN,24));
            label[i].setForeground(Color.WHITE);
            add(label[i]);
        }
        label[0].setFont(new Font("Montserrat extrabold",Font.PLAIN,34));
        label[0].setHorizontalAlignment(JLabel.CENTER);
        label[0].setBounds(0,30,650,50);
        label[1].setBounds(50,100,275,35);
        label[2].setBounds(50,150,275,35);

        for(i=0;i<pf.length;i++){
            pf[i] = new JPasswordField();
            pf[i].setFont(new Font("product sans medium",Font.PLAIN,40));
            pf[i].setBorder(null);
            pf[i].setOpaque(false);
            pf[i].setCaretColor(WHITE);
            pf[i].setForeground(Color.WHITE);
            pf[i].setHorizontalAlignment(SwingConstants.CENTER);
            pf[i].setDocument(new JTextFieldLimit(4));
            pf[i].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {
                    char e = ke.getKeyChar();
                    if(!Character.isDigit(e))
                        ke.consume();
                }
            });
            add(pf[i]);
        }
        pf[0].setBounds(325,100,275,25);
        pf[1].setBounds(325,150,275,25);

        JPanel line1=new JPanel();
        line1.setBounds(325,127,270,2);
        line1.setBackground(Color.WHITE);
        JPanel line2=new JPanel();
        line2.setBounds(325,177,270,2);
        line2.setBackground(Color.WHITE);
        add(line1);
        add(line2);

        for(i=0;i< btn.length;i++){
            btn[i] = new JButton(new ImageIcon(getClass().getResource("/images/pinchange/"+btnurl[i])));
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
                    btn[i].setIcon((new ImageIcon(getClass().getResource("/images/pinchange/"+btnurl[i+2]))));
                }
                public void mouseExited(MouseEvent e) {
                    int i=0;
                    if(e.getSource()==btn[0]) i=0;
                    if(e.getSource()==btn[1]) i=1;
                    btn[i].setIcon((new ImageIcon(getClass().getResource("/images/pinchange/"+btnurl[i]))));
                }
            });
        }
        btn[0].setBounds(100,225,200,50);
        btn[1].setBounds(350,225,200,50);
        setLayout(null);
        setResizable(false);
        setSize(650,350);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new PinChange("1111");
    }

    public void actionPerformed(ActionEvent ae) {
        try{

            if(ae.getSource()==btn[0]){
                String npin = pf[0].getText();
                String rpin = pf[1].getText();
                if (npin.equals("")){
                    if(rpin.equals("")){
                        JOptionPane.showMessageDialog(null,"Enter Pin to Change Pin");return;}
                }
                if(!npin.equals(rpin)){
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }
                Conn c1 = new Conn();
                String q1 = "update login set pin = '"+rpin+"' where form_no = '"+formno+"';";
                c1.s.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(formno).setVisible(true);

            }else{
                new Transactions(formno).setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
