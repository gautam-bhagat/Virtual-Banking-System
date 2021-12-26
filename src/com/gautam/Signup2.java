package com.gautam;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Color.WHITE;

public class Signup2 extends JFrame implements ActionListener {
    String formno;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JComboBox c1,c2,c3;
    JComboBox[] comboBoxes={ c1,c2,c3};
    JTextField tf1,tf2,tf3;
    JTextField[] tfs={ tf1,tf2,tf3};
    JRadioButton r1,r2;
    ButtonGroup b1;
    JPanel p1,p2,p3;
    JPanel[] pl={p1,p2,p3};
    JButton b = new JButton(new ImageIcon(getClass().getResource("/images/signup/next.png")));
    JLabel[] lbl={l1,l2,l3,l4,l5,l6,l7,l8};
    String[] lblurl={"ADDITIONAL DETAILS","Religion :","Caste Category :","Mobile Number :","Occupation :","PAN Number :",
            "Aadhaar Number","Senior Citizen :"};
    public Signup2(String form_no){
        getContentPane().setBackground(Color.decode("#137bdd"));
        this.formno = form_no;
        setLayout(null);
        Font f1 = new Font("Cambria",Font.BOLD,20);

        int i=0,y=50;
        for(i=0;i<lbl.length;i++){
            lbl[i] = new JLabel(lblurl[i],JLabel.LEADING);
            lbl[i].setVerticalAlignment(SwingConstants.CENTER);
            lbl[i].setFont(new Font("montserrat semibold",Font.PLAIN,22));
            lbl[i].setForeground(WHITE);
            add(lbl[i]);
            if(i>0)
              lbl[i].setBounds(100,y+=50,300,50);
        }
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
                    int pos=tfs[i].getCaretPosition();
                    tfs[i].setText(tfs[i].getText().toUpperCase());
                    tfs[i].setCaretPosition(pos);
                }
            });
        }
        tfs[0].setBounds(400,200,300,50);
        tfs[1].setBounds(400,300,300,50);
        tfs[2].setBounds(400,350,300,50);
        for(i=0;i<pl.length;i++){
            pl[i]=new JPanel();
            pl[i].setBackground(WHITE);
            add(pl[i]);
        }
        pl[0].setBounds(400,240,300,2);
        pl[1].setBounds(400,340,300,2);
        pl[2].setBounds(400,390,300,2);

        String[] religion = {"HINDUISM","SIKHISM","MUSLIM","CHRISTIANITY","JAIN","BUDDHISM","OTHER"};
        String[] category={"GENERAL","OBC","ST","SC","NT","NC","OTHER"};
        String[] income ={"BUSINESS","AGRICULTURE","SERVICE","SALARIED","STUDENT","OTHER"};

        comboBoxes[0] = new JComboBox<>(religion);
        comboBoxes[1]= new JComboBox<>(category);
        comboBoxes[2]=  new JComboBox<>(income);
        for(i=0;i<comboBoxes.length;i++){
            comboBoxes[i].setForeground(WHITE);
            comboBoxes[i].setBorder(new LineBorder(WHITE));
            comboBoxes[i].setOpaque(false);
            comboBoxes[i].setFont(new Font("segoe ui semibold",Font.PLAIN,22));
            comboBoxes[i].setBackground(Color.decode("#137bdd"));
            add(comboBoxes[i]);
        }

        r1=new JRadioButton("YES");
        r1.setFont(new Font("segoe ui Semibold",Font.PLAIN,20));
        r1.setBackground(Color.decode("#137bdd"));
        r1.setForeground(WHITE);
        r2=new JRadioButton("NO");
        r2.setFont(new Font("segoe ui Semibold",Font.PLAIN,20));
        r2.setForeground(WHITE);
        r2.setBackground(Color.decode("#137bdd"));

        b1 = new ButtonGroup();
        b1.add(r1);
        b1.add(r2);

        lbl[0].setBounds(0,35,800,50);
        lbl[0].setFont(new Font("Montserrat extrabold",Font.PLAIN,45));
        lbl[0].setHorizontalAlignment(JLabel.CENTER);
        comboBoxes[0].setBounds(400,100,300,40);
        comboBoxes[1].setBounds(400,150,300,40);
        comboBoxes[2].setBounds(400,250,300,40);

        r1.setBounds(400,410,150,30);
        add(r1);
        r2.setBounds(550,410,150,30);
        add(r2);

        b.setBounds(300,500,200,50);
        add(b);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);

        b.addActionListener(this);
        setResizable(false);
        setSize(800,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {

        String religion="",category="",mobile="",occupation="",pan_no="",aadhaar_no="",senior_citizen="";
        religion=(String)comboBoxes[0].getSelectedItem();
        category=(String)comboBoxes[1].getSelectedItem();
        mobile=tfs[0].getText().toUpperCase();
        occupation=(String)comboBoxes[2].getSelectedItem();
        pan_no=tfs[1].getText().toUpperCase();
        aadhaar_no=tfs[2].getText().toUpperCase();
        if(r1.isSelected())
            senior_citizen="YES";
        else if(r2.isSelected())
            senior_citizen="NO";


        try{
            if(religion.equals("") || category.equals("") || mobile.equals("") ||occupation.equals("")||pan_no.equals("")
                    ||aadhaar_no.equals("")||senior_citizen.equals(""))
                JOptionPane.showMessageDialog(null,"Fill all the Fields");
            else{
                Conn con = new Conn();
                String iq="INSERT INTO signup2 VALUES ('"+formno+"','"+religion+"','"+category+"','"+mobile+"','"
                        +occupation+"','"+pan_no+"','"+aadhaar_no+"','"+senior_citizen+"')";
                con.s.executeUpdate(iq);
                new Signup3(formno).setVisible(true);
                setVisible(false);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error!!");
        }
    }
    public static void main(String[] args) {
        new Signup2("");
    }
}
