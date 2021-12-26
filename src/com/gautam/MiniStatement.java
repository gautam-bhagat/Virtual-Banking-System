package com.gautam;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.Objects;

public class MiniStatement extends JFrame implements ActionListener{
    DefaultTableModel model = new DefaultTableModel();
    Container cnt= this.getContentPane();
    JTable jtbl=new JTable(model){
        public boolean isCellEditable(int row,int column){
            return false;
        }
    };
    DefaultTableCellRenderer ren = (DefaultTableCellRenderer)jtbl.getDefaultRenderer(Object.class);
    String formno;
    public MiniStatement(String form_no) {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new Transactions(formno).setVisible(true);
                setVisible(false);
            }
        });

        ImageIcon logo=new ImageIcon(getClass().getResource("/images/logo.png"));
        setIconImage(logo.getImage());

        this.formno=form_no;
        setTitle("Mini Statement");
        setSize(615,740);
        setLocation(100,10);
        setVisible(true);
        //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);


        ren.setHorizontalAlignment(SwingConstants.RIGHT);
        jtbl.setShowGrid(true);
        jtbl.setBorder(null);
        jtbl.setFocusable(false);
        jtbl.setIntercellSpacing(new Dimension(0,0));
        jtbl.setRowHeight(25);

        jtbl.getTableHeader().setFont(new Font("segoe ui semibold",Font.PLAIN,18));
        jtbl.getTableHeader().setOpaque(false);
        jtbl.getTableHeader().setForeground(Color.WHITE);
        jtbl.setSelectionForeground(Color.WHITE);
        jtbl.getTableHeader().setBackground(Color.decode("#137bdd"));

        jtbl.setSelectionBackground(new Color(232,57,95));
        jtbl.setShowVerticalLines(false);
        jtbl.setShowHorizontalLines(false);
        jtbl.setFont(new Font("segoe ui semibold",Font.PLAIN,14));
        int i=0;

        try{

            Conn c1  = new Conn();
            ResultSet rs1 = c1.s.executeQuery("select balance from bank where form_no='"+formno+"' order by tdate desc limit 1;");
            while (rs1.next()){String balance=rs1.getString("balance");}

            cnt.setLayout(null);
            model.addColumn("Date");
            model.addColumn("Mode");
            model.addColumn("Amount");
            model.addColumn("Balance");
            ResultSet rs = c1.s.executeQuery("select * from bank where form_no='"+formno+"';");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("tdate").substring(0,19),rs.getString("tmode"),rs.getString("amount"),rs.getString("balance")});
            }

        }catch(Exception e){e.printStackTrace();}
        
        JScrollPane pg=new JScrollPane(jtbl);
        pg.getViewport().setBackground(Color.WHITE);
        pg.setBounds(0,0,600,700);
        cnt.add(pg);

    }
    public static void main(String[] args) {
        MiniStatement c=new MiniStatement("1111");
    }

    public void actionPerformed(ActionEvent e) {
            new Login().setVisible(true);
        }
    }


