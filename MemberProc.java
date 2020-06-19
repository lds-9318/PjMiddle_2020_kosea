import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
import java.awt.event.*;
 
public class MemberProc extends JFrame implements ActionListener {
   
   
    JPanel p;
    JTextField tfamynum, tfName, tfAddr, tfgunnum, tfclas;
//    JTextField tfTel1, tfTel2, tfTel3; //��ȭ
    JComboBox cbspec; //�Ҵ�
//    JPasswordField pfPwd; //��й�ȣ   
    JTextField tfYear, tfMonth, tfDate; //�������
    JTextField tffdate1,tffdate2,tffdate3; //�Կ���
    JRadioButton rbOne, rbTwo, rbThree; //1, 2, 3
    
    JButton btnInsert, btnCancel, btnUpdate,btnDelete; //���, ���, ���� , ���� ��ư
   
    GridBagLayout gb;
    GridBagConstraints gbc;
    Member_List mList ;
   
    public MemberProc(){ //���Կ� ������
       
        createUI(); // UI�ۼ����ִ� �޼ҵ�
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
       
       
    }//������
   
    public MemberProc(Member_List mList){ //���Կ� ������
       
        createUI(); // UI�ۼ����ִ� �޼ҵ�
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
        this.mList = mList;
       
    }//������
    public MemberProc(String amynum,Member_List mList){ // ����/������ ������
        createUI();
        btnInsert.setEnabled(false);
        btnInsert.setVisible(false);
        this.mList = mList;
       
       
        System.out.println("amynum="+amynum);
       
        MemberDAO dao = new MemberDAO();
        MemberDTO vMem = dao.getMemberDTO(amynum);
        viewData(vMem);
       
       
    }//id�� ������ ����
 
       
    //MemberDTO �� ȸ�� ������ ������ ȭ�鿡 �������ִ� �޼ҵ�
    private void viewData(MemberDTO vMem){
       
        String amynum = vMem.getAmynum();
        String name = vMem.getName();
        String birth = vMem.getbirth();
        String address = vMem.getAddress();
        String fdate = vMem.getFdate();
        String clas = vMem.getClas();
        String spec = vMem.getSpec();
        String posit = vMem.getPosit();
        String gunnum= vMem.getGunnum();
        
       
        //ȭ�鿡 ����
        tfamynum.setText(amynum);
        tfamynum.setEditable(false); //���� �ȵǰ�
//        pfPwd.setText(""); //��й�ȣ�� �Ⱥ����ش�.
        tfName.setText(name);
        tfYear.setText(birth.substring(0, 4));
        tfMonth.setText(birth.substring(4, 6));
        tfDate.setText(birth.substring(6, 8));
//        String[] tels = tel.split("-");
//        tfTel1.setText(tels[0]);
//        tfTel2.setText(tels[1]);
//        tfTel3.setText(tels[2]);
        tfAddr.setText(address);
       
        tffdate1.setText(fdate.substring(0, 4));
        tffdate2.setText(fdate.substring(4, 6));
        tffdate3.setText(fdate.substring(6, 8));
        
        tfclas.setText(clas);
        cbspec.setSelectedItem(spec);
       
       
        if(posit.equals("1")){
            rbOne.setSelected(true);
        }else if(posit.equals("2")){
            rbTwo.setSelected(true);
        }else if(posit.equals("3")) {
        	rbThree.setSelected(true);
        }
       
        tfgunnum.setText(gunnum);
        
   
       
    }//viewData
   
   
   
    private void createUI(){
        this.setTitle("ȸ������");
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
       
       
        //���̵�
        JLabel bId = new JLabel("���� : ");
        tfamynum = new JTextField(20);     
        //�׸���鿡 ���̱�
        gbAdd(bId, 0, 0, 1, 1);
        gbAdd(tfamynum, 1, 0, 3, 1);
       
       
       
        //�̸�
        JLabel bName = new JLabel("�̸� :");
        tfName = new JTextField(20);
        gbAdd(bName,0,2,1,1);
        gbAdd(tfName,1,2,3,1);
       
        //����
      //����
        JLabel bBirth= new JLabel("����: ");
        tfYear = new JTextField(6);
        tfMonth = new JTextField(6);
        tfDate = new JTextField(6);
        JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pBirth.add(tfYear);
        pBirth.add(new JLabel("/"));
        pBirth.add(tfMonth);
        pBirth.add(new JLabel("/"));
        pBirth.add(tfDate);
        gbAdd(bBirth, 0,3,1,1);
        gbAdd(pBirth, 1, 3, 3,1);
        
//        �Կ���
        JLabel bfdate= new JLabel("�Կ���: ");
        tffdate1 = new JTextField(6);
        tffdate2 = new JTextField(6);
        tffdate3 = new JTextField(6);
        JPanel pfdate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pfdate.add(tffdate1);
        pfdate.add(new JLabel("/"));
        pfdate.add(tffdate2);
        pfdate.add(new JLabel("/"));
        pfdate.add(tffdate3);
        gbAdd(bfdate, 0,5,1,1);
        gbAdd(pfdate, 1, 5, 3,1);
        
        //�ּ�
        JLabel bAddr = new JLabel("�ּ�: ");
        tfAddr = new JTextField(20);
        gbAdd(bAddr, 0,4,1,1);
        gbAdd(tfAddr, 1, 4, 3,1);
       
        //���
        JLabel bclas = new JLabel("��� :");
        tfclas = new JTextField(6);
        gbAdd(bclas,0,6,1,1);
        gbAdd(tfclas,1,6,3,1);
        
        
        //��Ư��       
        JLabel bspec = new JLabel("��Ư�� : ");
        String[] arrspec = {"---", "���Ѽ�", "������", "��纴","�λ��", "�屳"};
        cbspec = new JComboBox(arrspec);
        JPanel pspec = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pspec.add(cbspec);       
        gbAdd(bspec, 0,7,1,1);
        gbAdd(pspec,1,7,3,1);
       
        //�Ҵ�
        JLabel bposit = new JLabel("�Ҵ� : ");
        JPanel pposit = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbOne = new JRadioButton("1�Ҵ�",true);
        rbTwo = new JRadioButton("2�Ҵ�",true);
        rbThree = new JRadioButton("3�Ҵ�",true);
        ButtonGroup group = new ButtonGroup();
        group.add(rbOne);
        group.add(rbTwo);
        group.add(rbThree);
        pposit.add(rbOne);
        pposit.add(rbTwo);
        pposit.add(rbThree);
        gbAdd(bposit, 0,8,1,1);
        gbAdd(pposit,1,8,3,1);
        gbAdd(pposit,2,8,5,1);
       
        //�ѱ��ȣ
        JLabel bgunnum = new JLabel("�ѱ��ȣ : ");
        tfgunnum = new JTextField(20);
        gbAdd(bgunnum, 0,9,1,1);
        gbAdd(tfgunnum,1,9,3,1);

       
        //��ư
        JPanel pButton = new JPanel();
        btnInsert = new JButton("����");
        btnUpdate = new JButton("����"); 
        btnDelete = new JButton("Ż��");
        btnCancel = new JButton("���");     
        pButton.add(btnInsert);
        pButton.add(btnUpdate);
        pButton.add(btnDelete);
        pButton.add(btnCancel);    
        gbAdd(pButton, 0, 10, 4, 1);
       
        //��ư�� �����⸦ ������
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnCancel.addActionListener(this);
        btnDelete.addActionListener(this);
       
        setSize(350,500);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) //���α׷�����
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //dispose(); //����â�� �ݴ´�.
       
       
    }//createUI
   
    //�׸���鷹�̾ƿ��� ���̴� �޼ҵ�
    private void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gb.setConstraints(c, gbc);
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }
   
    public static void main(String[] args) {
       
        new MemberProc();
    }
   
 
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnInsert){
            insertMember();
            System.out.println("insertMember() ȣ�� ����");
        }else if(ae.getSource() == btnCancel){
            this.dispose();    
        }else if(ae.getSource() == btnUpdate){
            UpdateMember();            
        }else if(ae.getSource() == btnDelete){

            int x = JOptionPane.showConfirmDialog(this,"���� �����Ͻðڽ��ϱ�?","����",JOptionPane.YES_NO_OPTION);
           
            if (x == JOptionPane.OK_OPTION){
                deleteMember();
            }else{
                JOptionPane.showMessageDialog(this, "������ ����Ͽ����ϴ�.");
            }
        }
       

        mList.jTableRefresh();
       
    }//actionPerformed 
   
   
    private void deleteMember() {
        String amynum = tfamynum.getText();
        String name = tfName.getText();
        if(amynum.length()==0){ 
           
            JOptionPane.showMessageDialog(this, "������ �� �Է��ϼ���!");
            return; //�޼ҵ� ��
        }

        MemberDAO dao = new MemberDAO();
        boolean ok = dao.deleteMember(amynum, name);
       
        if(ok){
            JOptionPane.showMessageDialog(this, "�����Ϸ�");
            dispose();         
           
        }else{
            JOptionPane.showMessageDialog(this, "��������");
           
        }          
       
    }//deleteMember
   
    private void UpdateMember() {
       

        MemberDTO dto = getViewData();     

        MemberDAO dao = new MemberDAO();
        boolean ok = dao.updateMember(dto);
       
        if(ok){
            JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "��������: ���� Ȯ���ϼ���");   
        }
    }
 
    private void insertMember(){
       

        MemberDTO dto = getViewData();
        MemberDAO dao = new MemberDAO();       
        boolean ok = dao.insertMember(dto);
       
        if(ok){
           
            JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.");
            dispose();
           
        }else{
           
            JOptionPane.showMessageDialog(this, "������ ���еǾ����ϴ�.");
        }
       
       
       
    }//insertMember
   
    public MemberDTO getViewData(){
       

        MemberDTO dto = new MemberDTO();
        String amynum = tfamynum.getText(); //����
        
        String name = tfName.getText(); //�̸�
        String fdate1 = tffdate1.getText(); //�Կ���
        String fdate2 = tffdate2.getText();//�Կ���
        String fdate3 = tffdate3.getText();//�Կ���
        String fdate = fdate1+"-"+fdate2+"-"+fdate3; //�Կ��� ����
        String address = tfAddr.getText(); //�ּ�
        String birth1 = tfYear.getText(); //�������
        String birth2 = tfMonth.getText(); //����
        String birth3 = tfDate.getText(); // ����
        //String birth = birth1+"/"+birth2+"/"+birth3;
        String birth = birth1+"-"+birth2+"-"+birth3; //��������
        String clas = tfclas.getText(); //���
        String spec = (String)cbspec.getSelectedItem(); //��Ư��
        String posit = ""; //�Ҵ�
        if(rbOne.isSelected()){
            posit = "1�Ҵ�";
        }else if(rbTwo.isSelected()){
            posit = "2�Ҵ�";
        }else if(rbThree.isSelected()) {
        	posit = "3�Ҵ�";
        }
       
        String gunnum = tfgunnum.getText(); //�ѱ�ѹ�
        
       
        //dto�� ��´�.
        dto.setAmynum(amynum);

        dto.setName(name);
        dto.setFdate(fdate);
        dto.setAddress(address);
        dto.setbirth(birth);
        dto.setClas(clas);
        dto.setSpec(spec);
        dto.setPosit(posit);
        dto.setGunnum(gunnum);
        
       
        return dto;
    }
   
}//end
