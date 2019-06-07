package money;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankManager extends JFrame {
	private JTextField id_txf;
	private JTextField name_txf;
	private JTextField tel_txf;
	private JTextField age_txf;
	private JButton accept_add;
	private JButton accept_edit;
	private String changeid;

	public BankManager() {
		getContentPane().setLayout(null);

		setSize(485, 547);
		JPanel pnl = new JPanel();
		pnl.setBounds(12, 10, 445, 183);
		getContentPane().add(pnl);

		JPanel list_pnl = new JPanel();
		list_pnl.setBounds(12, 203, 445, 295);
		getContentPane().add(list_pnl);
		list_pnl.setVisible(false);

		JPanel input_pnl = new JPanel();
		input_pnl.setBounds(12, 203, 445, 295);
		getContentPane().add(input_pnl);
		input_pnl.setLayout(null);
		input_pnl.setVisible(false);

		JButton create_btn = new JButton("<< 신규 고객 추가 >>");
		create_btn.setBounds(92, 5, 261, 29);
		create_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				input_pnl.setVisible(true);
				list_pnl.setVisible(false);
				accept_add.setVisible(true);
				accept_edit.setVisible(false);

			}
		});
		pnl.setLayout(null);
		create_btn.setFont(new Font("굴림", Font.PLAIN, 18));
		pnl.add(create_btn);

		JButton edit_btn = new JButton("<< 고객 정보 수정 >>");
		edit_btn.setBounds(92, 39, 261, 29);
		edit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input_pnl.setVisible(true);
				list_pnl.setVisible(false);
				accept_add.setVisible(false);
				accept_edit.setVisible(true);

				name_txf.setEnabled(false);
				age_txf.setEnabled(false);
				JOptionPane.showMessageDialog(null, "바꾸는 ID와 바뀔 비밀번호를 입력해주세요.");
			}
		});
		edit_btn.setFont(new Font("굴림", Font.PLAIN, 18));
		pnl.add(edit_btn);

		JButton delete_btn = new JButton("<< 고객 탈퇴 >>");
		delete_btn.setBounds(113, 73, 219, 29);
		delete_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String delete_id = JOptionPane.showInputDialog("삭제하고 싶은 ID를 입력해주세요");
				BankDAO dao;
				try {
					dao = new BankDAO();
					dao.delete(delete_id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		delete_btn.setFont(new Font("굴림", Font.PLAIN, 18));
		pnl.add(delete_btn);

		JButton select_btn = new JButton("<< 고객 검색 >>");
		select_btn.setBounds(112, 107, 219, 29);
		select_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String searchid = JOptionPane.showInputDialog("검색하고 싶은 ID입력");
					BankDAO dao = new BankDAO();
					dao.search(searchid);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		select_btn.setFont(new Font("굴림", Font.PLAIN, 18));
		pnl.add(select_btn);

		JButton selectall_btn = new JButton("<< 전체 고객 리스트 >>");
		selectall_btn.setFont(new Font("굴림", Font.PLAIN, 18));
		selectall_btn.setBounds(83, 144, 279, 29);
		selectall_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BankDAO dao = new BankDAO();
					ArrayList list = dao.selectall();

					for (int i = 0; i < list.size(); i++) {
						System.out.println(list.get(i));

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pnl.add(selectall_btn);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(123, 115, 11, 15);
		input_pnl.add(lblNewLabel);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(123, 140, 34, 15);
		input_pnl.add(lblName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(123, 165, 22, 15);
		input_pnl.add(lblAge);

		JLabel lblTel = new JLabel("TEL");
		lblTel.setBounds(123, 190, 23, 15);
		input_pnl.add(lblTel);

		id_txf = new JTextField();
		id_txf.setBounds(170, 112, 116, 21);
		input_pnl.add(id_txf);
		id_txf.setColumns(10);

		name_txf = new JTextField();
		name_txf.setBounds(169, 137, 116, 21);
		input_pnl.add(name_txf);
		name_txf.setColumns(10);

		age_txf = new JTextField();
		age_txf.setBounds(170, 162, 116, 21);
		input_pnl.add(age_txf);
		age_txf.setColumns(10);

		tel_txf = new JTextField();
		tel_txf.setBounds(170, 187, 116, 21);
		input_pnl.add(tel_txf);
		tel_txf.setColumns(10);

		accept_edit = new JButton("고객 수정");
		accept_edit.setBounds(232, 220, 97, 23);
		input_pnl.add(accept_edit);
		accept_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BankDTO dto = new BankDTO();
					BankDAO dao = new BankDAO();

					dto.setId(id_txf.getText());
					dto.setTel(name_txf.getText());

					dao.update(dto);

				} catch (Exception e1) {

					e1.printStackTrace();

				}

				input_pnl.setVisible(false);
			}
		});

		accept_add = new JButton("고객 추가");
		accept_add.setBounds(123, 220, 97, 23);
		accept_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BankDTO dto = new BankDTO();
					BankDAO dao = new BankDAO();

					dto.setId(id_txf.getText());
					dto.setName(name_txf.getText());
					dto.setAge(age_txf.getText());
					dto.setTel(name_txf.getText());

					dao.insert(dto);

				} catch (Exception e1) {

					e1.printStackTrace();

				}

				input_pnl.setVisible(false);
			}
		});

		input_pnl.add(accept_add);

		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		BankManager bg = new BankManager();
	}
}
