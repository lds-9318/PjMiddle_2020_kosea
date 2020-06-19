import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
 
public class Member_List extends JFrame implements MouseListener,ActionListener{
   
    Vector v;  
    Vector cols;
    DefaultTableModel model;
    JTable jTable;
    JScrollPane pane;
    JPanel pbtn;
    JButton btnInsert;
       
   
    public Member_List(){
        super("�� �������� ��ȸ");
        
        MemberDAO dao = new MemberDAO();
        v = dao.getMemberList();
        System.out.println("v="+v);
        
        cols = getColumn();
       
       
       
        model = new DefaultTableModel(v, cols);
       
        
        jTable = new JTable(model);
        pane = new JScrollPane(jTable);
        add(pane);
       
        pbtn = new JPanel();
        btnInsert = new JButton("�������");
        pbtn.add(btnInsert);
        add(pbtn,BorderLayout.NORTH);
       
       
        jTable.addMouseListener(this); 
        btnInsert.addActionListener(this); 
       
        setSize(600,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end ������
   
   
    //JTable
    public Vector getColumn(){
        Vector col = new Vector();
        col.add("����");
        col.add("�̸�");
        col.add("�������");
        col.add("�ּ�");
        col.add("�Կ���");
        col.add("���");
        col.add("��Ư��");
        col.add("�Ҽ�(�Ҵ�)");
        col.add("�ѱ�ѹ�");
        
       
        return col;
    }//getColumn
   
   

    public void jTableRefresh(){
       
        MemberDAO dao = new MemberDAO();
        DefaultTableModel model= new DefaultTableModel(dao.getMemberList(), getColumn());
        jTable.setModel(model);    
       
    }
   
    public static void main(String[] args) {
        new Member_List();
    }//main
    @Override
    public void mouseClicked(MouseEvent e) {

        int r = jTable.getSelectedRow();
        String amynum = (String) jTable.getValueAt(r, 0);

        MemberProc mem = new MemberProc(amynum,this); 
               
    }
    @Override
    public void mouseEntered(MouseEvent e) {

       
    }
    @Override
    public void mouseExited(MouseEvent e) {

       
    }
    @Override
    public void mousePressed(MouseEvent e) {

       
    }
    @Override
    public void mouseReleased(MouseEvent e) {

       
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnInsert ){
            new MemberProc(this);
        }
       
    }
   
}
