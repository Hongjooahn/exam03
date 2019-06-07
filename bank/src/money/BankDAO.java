package money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BankDAO {
	Connection con;
	ResultSet rs;
	String url;
	String user;
	String passwd;

	public BankDAO() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		url = "jdbc:mysql://localhost:3306/bank";
		user = "root";
		passwd = "1234";
		con = DriverManager.getConnection(url, user, passwd);

	}

	public void insert(BankDTO dto) throws SQLException {

		String sql = "insert into member values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, dto.getId());
		ps.setString(2, dto.getName());
		ps.setString(3, dto.getAge());
		ps.setString(4, dto.getTel());
		ps.executeUpdate();

		JOptionPane.showMessageDialog(null, "회원 등록 완료!");
	}

	public void update(BankDTO dto) throws SQLException {
		String sql = "update member set tel=? where id=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, dto.getTel());
		ps.setString(2, dto.getId());
		ps.executeUpdate();

		JOptionPane.showMessageDialog(null, "수정 완료!");
	}

	public void delete(String id) throws SQLException {
		String sql = "delete from member where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, id);
		ps.executeUpdate();

		JOptionPane.showMessageDialog(null, "삭제 완료!");
	}

	public void search(String id) throws SQLException {
		BankDTO dto = new BankDTO();
		String sql = "select* from member where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, id);
		rs = ps.executeQuery();
		
			while (rs.next()) {
				
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getString(3));
				dto.setTel(rs.getString(4));
			}
			if (rs.getString(1) == null && rs.getString(2) == null) {
				JOptionPane.showMessageDialog(null, "id가 존재하지 않습니다.");
			}
			else {
			JOptionPane.showMessageDialog(null, dto.getId()+"\r\n"+dto.getName()+"\r\n"+dto.getAge()+"\r\n"+dto.getTel());
			}
		}
//		return dto;

	public ArrayList selectall() throws Exception {
		ArrayList list = new ArrayList();
		String sql = "select * from member";
		PreparedStatement ps = con.prepareStatement(sql);
		
		rs = ps.executeQuery();
		while(rs.next()) {
			BankDTO dto = new BankDTO();
			dto.setId(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setAge(rs.getString(3));
			dto.setTel(rs.getString(4));
			
			list.add(dto);
		}
		

		return list;

	}

}
