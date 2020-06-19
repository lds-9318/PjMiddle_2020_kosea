
import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MemberDAO {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
//    192.168.0.223
	private static final String USER = "kosea"; // DB ID
	private static final String PASS = "kosea2019a"; // DB �н�����
	Member_List mList;

	public MemberDAO() {

	}

	public MemberDAO(Member_List mList) {
		this.mList = mList;
		System.out.println("DAO=>" + mList);
	}

	/** DB���� �޼ҵ� */

	public Connection getConn() {
		Connection con = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	/** �ѻ���� ȸ�� ������ ��� �޼ҵ� */
	public MemberDTO getMemberDTO(String id) {

		MemberDTO dto = new MemberDTO();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {

			con = getConn();
			String sql = "select * from TB_Army where amynum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setAmynum(rs.getString("amynum"));
				dto.setName(rs.getString("name"));
				dto.setbirth(rs.getString("birth"));
				dto.setAddress(rs.getString("address"));
				dto.setFdate(rs.getString("fdate"));
				dto.setClas(rs.getString("clas"));
				dto.setSpec(rs.getString("spec"));
				dto.setPosit(rs.getString("posit"));
				dto.setGunnum(rs.getString("gunnum"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	/** �������Ʈ ��� */
	public Vector getMemberList() {

		Vector data = new Vector();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {

			con = getConn();
			String sql = "select * from TB_ARMY order by amynum asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String amynum = rs.getString("amynum");
				String name = rs.getString("name");
				String birth = rs.getString("birth");
				String address = rs.getString("address");
				String fdate = rs.getString("fdate");
				String clas = rs.getString("clas");
				String spec = rs.getString("spec");
				String posit = rs.getString("posit");
				String gunnum = rs.getString("gunnum");

				Vector row = new Vector();
				row.add(amynum);
				row.add(name);
				row.add(birth);
				row.add(address);
				row.add(fdate);
				row.add(clas);
				row.add(spec);
				row.add(posit);
				row.add(gunnum);

				data.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}// getMemberList()

	/** ȸ�� ��� */
	public boolean insertMember(MemberDTO dto) {

		boolean ok = false;

		Connection con = null; // ����
		PreparedStatement ps = null; // ���

		try {

			con = getConn();
			String sql = "insert into TB_Army(" + "amynum,name,birth,address,fdate,clas," + "spec,posit,gunnum) "
					+ "values(?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAmynum());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getbirth());
			ps.setString(4, dto.getAddress());
			ps.setString(5, dto.getFdate());
			ps.setString(6, dto.getClas());
			ps.setString(7, dto.getSpec());
			ps.setString(8, dto.getPosit());
			ps.setString(9, dto.getGunnum());

			int r = ps.executeUpdate(); // ���� -> ����

			if (r > 0) {
				System.out.println("��� ����");
				ok = true;
			} else {
				System.out.println("��� ����");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ok;
	}// insertMmeber

	// ȸ������ ����
	public boolean updateMember(MemberDTO vMem) {
		System.out.println("dto=" + vMem.toString());
		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {

			con = getConn();
			String sql = "update TB_Army set  name=?, birth=?, address=?, fdate=?, clas=?, spec=?, posit=?, gunnum=?"
					+"where amynum=? ";

			ps = con.prepareStatement(sql);
			
			
			
			ps.setString(1, vMem.getName());
			ps.setString(2, vMem.getbirth());
			ps.setString(3, vMem.getAddress());
			ps.setString(4, vMem.getFdate());
			ps.setString(5, vMem.getClas());
			ps.setString(6, vMem.getSpec());
			ps.setString(7, vMem.getPosit());
			ps.setString(8, vMem.getGunnum());
			ps.setString(9, vMem.getAmynum());

			int r = ps.executeUpdate();

//			System.out.println(r);
			if (r > 0)
				ok = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ok;
	}

	// ȸ������ ���� :
	public boolean deleteMember(String amynum, String name) {

		boolean ok = false;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from TB_Army where amynum=? and name=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, amynum);
			ps.setString(2, name);
			int r = ps.executeUpdate();

			if (r > 0)
				ok = true;

		} catch (Exception e) {
			System.out.println(e + "-> �����߻�");
		}
		return ok;
	}

	/** DB������ �ٽ� �ҷ����� */
	public void userSelectAll(DefaultTableModel model) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getConn();
			String sql = "select * from TB_Army order by name asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// DefaultTableModel�� �ִ� ������ �����
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10) };

				model.addRow(data);
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
