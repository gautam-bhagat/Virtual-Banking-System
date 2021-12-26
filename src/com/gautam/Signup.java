package com.gautam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.*;

import static java.awt.Color.WHITE;

public class Signup extends JFrame implements ActionListener, PropertyChangeListener {
    Random ran = new Random();
    String formno="";
    public CalendarWindow calendarWindow=new CalendarWindow();

    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
    JLabel[] lbl={l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12};
    String[] lblurl={"CREATE ACCOUNT","Personal Details","Full Name :","Father's Name :","Date of Birth :","Gender :",
            "Email Address :", "Martial Status :", "Address :","City :","Pin Code :","State :"};
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7;
    JTextField[] tfs={tf1,tf2,tf3,tf4,tf5,tf6,tf7};

    JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11p,p12;
    JPanel[] pl={p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11p,p12};

    JRadioButton g1,g2,g3,m1,m2,m3;
    JRadioButton[] r_btn={g1,g2,g3,m1,m2,m3};
    String[] r_btnurl={"Male","Female","Transgender","Single","Married","Engaged"};

    JButton b1,b2;
    JButton[] btn={b1,b2};
    String[] btnurl={"next.png","calendar.png"};
    ButtonGroup g = new ButtonGroup();
    ButtonGroup m = new ButtonGroup();
    JFormattedTextField  textField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.LONG));

    public Signup(){
        textField.setValue(new Date());
        calendarWindow.addPropertyChangeListener(this);
        int i=0,y=50,py=88;
        for(i=0;i<4;i++){
            int r = ran.nextInt(9);
            formno += r;
        }
        setLayout(null);
        getContentPane().setBackground(Color.decode("#137bdd"));

        for(i=0;i<lbl.length;i++){
            lbl[i] = new JLabel(lblurl[i]);
            lbl[i].setFont(new Font("montserrat semibold",Font.PLAIN,18));
            lbl[i].setForeground(WHITE);
            add(lbl[i]);
            if(i>1)
                lbl[i].setBounds(100,y+=50,300,50);
        }
        lbl[0].setFont(new Font("montserrat extrabold",Font.PLAIN,38));
        lbl[0].setHorizontalAlignment(JLabel.CENTER);
        lbl[1].setHorizontalAlignment(JLabel.CENTER);
        lbl[1].setFont(new Font("montserrat extrabold",Font.BOLD,26));
        lbl[0].setBounds(0,0,800,60);
        lbl[1].setBounds(0,50,800,50);

        for(i=0;i<tfs.length;i++){
            tfs[i] = new JTextField();
            tfs[i].setFont(new Font("Segoe UI Semibold",Font.PLAIN,22));
            tfs[i].setBorder(null);
            tfs[i].setOpaque(false);
            tfs[i].setCaretColor(WHITE);
            tfs[i].setForeground(WHITE);
            tfs[i].setHorizontalAlignment(SwingConstants.CENTER);
            add(tfs[i]);
            tfs[i].addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    int i=0;
                   if(e.getSource()==tfs[0]) i=0;
                   if(e.getSource()==tfs[1]) i=1;
                   if(e.getSource()==tfs[2]) i=2;
                   if(e.getSource()==tfs[3]) i=3;
                   if(e.getSource()==tfs[4]) i=4;
                   if(e.getSource()==tfs[5]) i=5;
                   if(e.getSource()==tfs[6]) i=6;
                   int pos=tfs[i].getCaretPosition();
                   tfs[i].setText(tfs[i].getText().toUpperCase());
                   tfs[i].setCaretPosition(pos);
                }
            });
        }
        for(i=0;i<pl.length;i++){
            pl[i]=new JPanel();
            if(i>1)
                pl[i].setBounds(300,py+=50,400,2);
            pl[i].setBackground(WHITE);
            if(i==5||i==7)continue;
            add(pl[i]);
        }
        tfs[0].setBounds(300,100,400,50);
        tfs[1].setBounds(300,150,400,50);
        tfs[2].setBounds(300,300,400,50);
        tfs[3].setBounds(300,400,400,50);
        tfs[4].setBounds(300,450,400,50);
        tfs[5].setBounds(300,500,400,50);
        tfs[6].setBounds(300,550,400,50);

        textField.setBounds(300,200,350,40);
        textField.setFont(new Font("Segoe UI Semibold",Font.PLAIN,22));
        textField.setBorder(null);
        textField.setOpaque(false);
        textField.setCaretColor(WHITE);
        textField.setForeground(WHITE);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setValue(new Date());
        add(textField);

        for(i=0;i<r_btn.length;i++){
            r_btn[i]=new JRadioButton(r_btnurl[i]);
            r_btn[i].setBackground(Color.decode("#137bdd"));
            r_btn[i].setForeground(WHITE);
            r_btn[i].setFont(new Font("Segoe ui semibold",Font.PLAIN,22));
            r_btn[i].setBorderPainted(false);
            r_btn[i].setContentAreaFilled(false);
            add(r_btn[i]);
            g.add(r_btn[i]);
            if(i>2)m.add(r_btn[i]);
        }
        r_btn[0].setBounds(300,250,120,50);
        r_btn[1].setBounds(430,250,120,50);
        r_btn[2].setBounds(570,250,150,50);
        r_btn[3].setBounds(300,350,150,50);
        r_btn[4].setBounds(450,350,150,50);
        r_btn[5].setBounds(600,350,150,50);

        for(i=0;i<btn.length;i++){
            btn[i] = new JButton(new ImageIcon(getClass().getResource("/images/signup/"+btnurl[i])));
            add(btn[i]);
            btn[i].addActionListener(this);
            btn[i].setBorderPainted(false);
            btn[i].setContentAreaFilled(false);
        }
        btn[0].setBounds(300,625,200,50);
        btn[1].setBounds(650,200,50,50);
        setResizable(false);
        setSize(800,750);
        setVisible(true);
        setLocation(300,0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==btn[0]){
        String full_name="",fathers_name="",dob="",gender="",email="",martial_status="",address="",city="",pin_code="",state="";
        full_name = tfs[0].getText().toUpperCase();
        fathers_name = tfs[1].getText().toUpperCase();
        dob=textField.getText();
        if(r_btn[0].isSelected())
            gender="MALE";
        else if(r_btn[1].isSelected())
            gender="FEMALE";
        else if(r_btn[2].isSelected())
            gender="TRANSGENDER";

        email = tfs[2].getText().toUpperCase();

        if(r_btn[3].isSelected())
            martial_status="SINGLE";
        else if(r_btn[4].isSelected())
            martial_status="MARRIED";
        else if(r_btn[5].isSelected())
            martial_status="ENGAGED";

        address= tfs[3].getText().toUpperCase();
        city= tfs[4].getText().toUpperCase();
        pin_code= tfs[5].getText();
        state= tfs[6].getText().toUpperCase();
        try{
            if(full_name.equals("") || fathers_name.equals("") || dob.equals("") ||gender.equals("")||email.equals("")||
                    martial_status.equals("")||address.equals("")||city.equals("")||pin_code.equals("")||state=="")
                JOptionPane.showMessageDialog(null, "Enter All Fields");
            else{
                Conn con = new Conn();
                String iq="INSERT INTO signup VALUES ('"+formno+"','"+full_name+"','"+fathers_name+"','"+dob+"','"+gender+"','"+email+"'," +
                        "'"+martial_status+"','"+address+"','"+city+"','"+pin_code+"','"+state+"');";
                con.s.executeUpdate(iq);
                new Signup2(formno).setVisible(true);
                setVisible(false);
            }
        }
        catch(java.sql.SQLIntegrityConstraintViolationException ex){
            JOptionPane.showMessageDialog(null,"Application from this form has been sent already.\n" +
                    "Try to Apply again");
        }
        catch(Exception ignored){}}
    else{
        //render the calendar window below the text field
        calendarWindow.setLocation(textField.getLocationOnScreen().x, (textField.getLocationOnScreen().y + textField.getHeight()));
        //get the Date and assign it to the calendar
        Date d = (Date)textField.getValue();

        calendarWindow.resetSelection(d);
        if(calendarWindow.isVisible()==true){}
        else
            calendarWindow.setUndecorated(true);
            calendarWindow.setVisible(true);

    }
    }
    public static void main(String[] args) {
        new Signup();
    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("selectedDate")) {

            java.util.Calendar cal = (java.util.Calendar)event.getNewValue();
            Date selDate =  cal.getTime();
            textField.setValue(selDate);
    }
}}
