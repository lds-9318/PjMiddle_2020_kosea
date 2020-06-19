import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
import java.awt.event.*;
 
public class MemberProc extends JFrame implements ActionListener {
   
   
    JPanel p;
    JTextField tfamynum, tfName, tfAddr, tfgunnum, tfclas;
//    JTextField tfTel1, tfTel2, tfTel3; //전화
    JComboBox cbspec; //소대
//    JPasswordField pfPwd; //비밀번호   
    JTextField tfYear, tfMonth, tfDate; //생년월일
    JTextField tffdate1,tffdate2,tffdate3; //입영일
    JRadioButton rbOne, rbTwo, rbThree; //1, 2, 3
    
    JButton btnInsert, btnCancel, btnUpdate,btnDelete; //등록, 취소, 수정 , 삭제 버튼
   
    GridBagLayout gb;
    GridBagConstraints gbc;
    Member_List mList ;
   
    public MemberProc(){ //가입용 생성자
       
        createUI(); // UI작성해주는 메소드
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
       
       
    }//생성자
   
    public MemberProc(Member_List mList){ //가입용 생성자
       
        createUI(); // UI작성해주는 메소드
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
        this.mList = mList;
       
    }//생성자
    public MemberProc(String amynum,Member_List mList){ // 수정/삭제용 생성자
        createUI();
        btnInsert.setEnabled(false);
        btnInsert.setVisible(false);
        this.mList = mList;
       
       
        System.out.println("amynum="+amynum);
       
        MemberDAO dao = new MemberDAO();
        MemberDTO vMem = dao.getMemberDTO(amynum);
        viewData(vMem);
       
       
    }//id를 가지고 생성
 
       
    //MemberDTO 의 회원 정보를 가지고 화면에 셋팅해주는 메소드
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
        
       
        //화면에 세팅
        tfamynum.setText(amynum);
        tfamynum.setEditable(false); //편집 안되게
//        pfPwd.setText(""); //비밀번호는 안보여준다.
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
        this.setTitle("회원정보");
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
       
       
        //아이디
        JLabel bId = new JLabel("군번 : ");
        tfamynum = new JTextField(20);     
        //그리드백에 붙이기
        gbAdd(bId, 0, 0, 1, 1);
        gbAdd(tfamynum, 1, 0, 3, 1);
       
       
       
        //이름
        JLabel bName = new JLabel("이름 :");
        tfName = new JTextField(20);
        gbAdd(bName,0,2,1,1);
        gbAdd(tfName,1,2,3,1);
       
        //생일
      //생일
        JLabel bBirth= new JLabel("생일: ");
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
        
//        입영일
        JLabel bfdate= new JLabel("입영일: ");
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
        
        //주소
        JLabel bAddr = new JLabel("주소: ");
        tfAddr = new JTextField(20);
        gbAdd(bAddr, 0,4,1,1);
        gbAdd(tfAddr, 1, 4, 3,1);
       
        //계급
        JLabel bclas = new JLabel("계급 :");
        tfclas = new JTextField(6);
        gbAdd(bclas,0,6,1,1);
        gbAdd(tfclas,1,6,3,1);
        
        
        //주특기       
        JLabel bspec = new JLabel("주특기 : ");
        String[] arrspec = {"---", "소총수", "운전병", "취사병","부사관", "장교"};
        cbspec = new JComboBox(arrspec);
        JPanel pspec = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pspec.add(cbspec);       
        gbAdd(bspec, 0,7,1,1);
        gbAdd(pspec,1,7,3,1);
       
        //소대
        JLabel bposit = new JLabel("소대 : ");
        JPanel pposit = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbOne = new JRadioButton("1소대",true);
        rbTwo = new JRadioButton("2소대",true);
        rbThree = new JRadioButton("3소대",true);
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
       
        //총기번호
        JLabel bgunnum = new JLabel("총기번호 : ");
        tfgunnum = new JTextField(20);
        gbAdd(bgunnum, 0,9,1,1);
        gbAdd(tfgunnum,1,9,3,1);

       
        //버튼
        JPanel pButton = new JPanel();
        btnInsert = new JButton("가입");
        btnUpdate = new JButton("수정"); 
        btnDelete = new JButton("탈퇴");
        btnCancel = new JButton("취소");     
        pButton.add(btnInsert);
        pButton.add(btnUpdate);
        pButton.add(btnDelete);
        pButton.add(btnCancel);    
        gbAdd(pButton, 0, 10, 4, 1);
       
        //버튼에 감지기를 붙이자
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnCancel.addActionListener(this);
        btnDelete.addActionListener(this);
       
        setSize(350,500);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) //프로그램종료
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //dispose(); //현재창만 닫는다.
       
       
    }//createUI
   
    //그리드백레이아웃에 붙이는 메소드
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
            System.out.println("insertMember() 호출 종료");
        }else if(ae.getSource() == btnCancel){
            this.dispose();    
        }else if(ae.getSource() == btnUpdate){
            UpdateMember();            
        }else if(ae.getSource() == btnDelete){

            int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);
           
            if (x == JOptionPane.OK_OPTION){
                deleteMember();
            }else{
                JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
            }
        }
       

        mList.jTableRefresh();
       
    }//actionPerformed 
   
   
    private void deleteMember() {
        String amynum = tfamynum.getText();
        String name = tfName.getText();
        if(amynum.length()==0){ 
           
            JOptionPane.showMessageDialog(this, "군번을 꼭 입력하세요!");
            return; //메소드 끝
        }

        MemberDAO dao = new MemberDAO();
        boolean ok = dao.deleteMember(amynum, name);
       
        if(ok){
            JOptionPane.showMessageDialog(this, "삭제완료");
            dispose();         
           
        }else{
            JOptionPane.showMessageDialog(this, "삭제실패");
           
        }          
       
    }//deleteMember
   
    private void UpdateMember() {
       

        MemberDTO dto = getViewData();     

        MemberDAO dao = new MemberDAO();
        boolean ok = dao.updateMember(dto);
       
        if(ok){
            JOptionPane.showMessageDialog(this, "수정되었습니다.");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "수정실패: 값을 확인하세요");   
        }
    }
 
    private void insertMember(){
       

        MemberDTO dto = getViewData();
        MemberDAO dao = new MemberDAO();       
        boolean ok = dao.insertMember(dto);
       
        if(ok){
           
            JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
            dispose();
           
        }else{
           
            JOptionPane.showMessageDialog(this, "가입이 실패되었습니다.");
        }
       
       
       
    }//insertMember
   
    public MemberDTO getViewData(){
       

        MemberDTO dto = new MemberDTO();
        String amynum = tfamynum.getText(); //군번
        
        String name = tfName.getText(); //이름
        String fdate1 = tffdate1.getText(); //입영일
        String fdate2 = tffdate2.getText();//입영일
        String fdate3 = tffdate3.getText();//입영일
        String fdate = fdate1+"-"+fdate2+"-"+fdate3; //입영일 조합
        String address = tfAddr.getText(); //주소
        String birth1 = tfYear.getText(); //생년월일
        String birth2 = tfMonth.getText(); //생년
        String birth3 = tfDate.getText(); // 생일
        //String birth = birth1+"/"+birth2+"/"+birth3;
        String birth = birth1+"-"+birth2+"-"+birth3; //생일조합
        String clas = tfclas.getText(); //계급
        String spec = (String)cbspec.getSelectedItem(); //주특기
        String posit = ""; //소대
        if(rbOne.isSelected()){
            posit = "1소대";
        }else if(rbTwo.isSelected()){
            posit = "2소대";
        }else if(rbThree.isSelected()) {
        	posit = "3소대";
        }
       
        String gunnum = tfgunnum.getText(); //총기넘버
        
       
        //dto에 담는다.
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
