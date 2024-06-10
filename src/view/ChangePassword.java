package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import controller.ChangePasswordCTL;
import custom.MyButton;
import custom.MyPasswordField;
import custom.MyTextField;
import custom.PanelRound;
import model.User;

public class ChangePassword extends JDialog {

	private static final long serialVersionUID = 1L;
	private final Color BACKGROUND = new Color(0, 45, 97);
	private final Color RIGHT_COLOR = new Color(242, 224, 191);
	private final Color COLOR1 = new Color(244, 203, 198);
	private final Color LABEL_COLOR = new Color(0, 0, 0);

	private final Font LABEL_FONT = new Font("Times New Roman", Font.PLAIN, 20);
	private final Font TEXT_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18);
	private final Font BUTTON_FONT = new Font("Times New Roman", Font.BOLD, 16);
	private final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 30);
	private final JPanel contentPanel = new JPanel();

	private User user = new User();
	private String username;
	private boolean isEditable;
	public MyPasswordField txtNewPass, txtOldPass, txtNewPassAgain;
	private JLabel title;
	private ChangePasswordCTL cpc;

	public ChangePassword(String username) {
		cpc = new ChangePasswordCTL(this);
		this.user = new User();
		this.username = username;
		this.user.setUsername(this.username);
		user = cpc.selectByUsername(username);
		System.out.println(user.getUsername() + "/" + user.getPassword() + "/" + user.getPosition() + "/" + user.getLastLogin().toString());
		initComponents();
	}

	public void initComponents() {
		setModal(true);
		setUndecorated(true);
		setSize(522, 370);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		contentPanel.setBackground(BACKGROUND);

		title = new JLabel("THAY ĐỔI MẬT KHẨU");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(TITLE_FONT);
		title.setForeground(COLOR1);
		title.setBounds(20, 21, 502, 45);
		contentPanel.add(title);

		PanelRound panel = new PanelRound();
		panel.setBorderWidth(3);
		panel.setBorderColor(Color.black);
		panel.setRadius(25);
		panel.setBground(COLOR1);
		panel.setBounds(20, 76, 481, 230);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblAccount = new JLabel("Tài khoản:");
		lblAccount.setBounds(0, 39, 202, 30);
		panel.add(lblAccount);
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setForeground(BACKGROUND);
		lblAccount.setFont(LABEL_FONT);
		
		MyTextField txtAccount = new MyTextField();
		txtAccount.setLineColor(Color.BLACK);
		txtAccount.setHorizontalAlignment(SwingConstants.CENTER);
		txtAccount.setForeground(new Color(0, 45, 97));
		txtAccount.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtAccount.setBackground(new Color(244, 203, 198));
		txtAccount.setBounds(215, 35, 238, 30);
		txtAccount.setText(user.getUsername());
		txtAccount.setEditable(false);
		panel.add(txtAccount);

		JLabel lblOldPass = new JLabel("Nhập mật khẩu cũ:");
		lblOldPass.setBounds(0, 80, 202, 30);
		panel.add(lblOldPass);
		lblOldPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOldPass.setForeground(BACKGROUND);
		lblOldPass.setFont(LABEL_FONT);
		
		txtOldPass = new MyPasswordField();
		txtOldPass.setLineColor(Color.BLACK);
		txtOldPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtOldPass.setForeground(new Color(0, 45, 97));
		txtOldPass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtOldPass.setBackground(new Color(244, 203, 198));
		txtOldPass.setBounds(215, 75, 238, 30);
		panel.add(txtOldPass);

		JLabel lblNewPass = new JLabel("Nhập mật khẩu mới:");
		lblNewPass.setBounds(0, 120, 202, 30);
		panel.add(lblNewPass);
		lblNewPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPass.setForeground(BACKGROUND);
		lblNewPass.setFont(LABEL_FONT);
		
		txtNewPass = new MyPasswordField();
		txtNewPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewPass.setBounds(215, 115, 238, 30);
		panel.add(txtNewPass);
		txtNewPass.setLineColor(Color.black);
		txtNewPass.setForeground(BACKGROUND);
		txtNewPass.setFont(TEXT_FONT);
		txtNewPass.setBackground(COLOR1);
		
		JLabel lblNewPassAgain = new JLabel("Nhập lại mật khẩu mới:");
		lblNewPassAgain.setBounds(0, 160, 202, 30);
		panel.add(lblNewPassAgain);
		lblNewPassAgain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPassAgain.setForeground(BACKGROUND);
		lblNewPassAgain.setFont(LABEL_FONT);

		txtNewPassAgain = new MyPasswordField();
		txtNewPassAgain.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewPassAgain.setBounds(215, 155, 238, 30);
		panel.add(txtNewPassAgain);
		txtNewPassAgain.setLineColor(new Color(244, 203, 198));
		txtNewPassAgain.setLineColor(Color.black);
		txtNewPassAgain.setForeground(BACKGROUND);
		txtNewPassAgain.setFont(TEXT_FONT);
		txtNewPassAgain.setBackground(COLOR1);

		MyButton btnCancel = new MyButton();
		btnCancel.setFocusPainted(false);
		btnCancel.setText("Đóng");
		btnCancel.setRadius(15);
		btnCancel.setColor(COLOR1);
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setBorderColor(LABEL_COLOR);
		btnCancel.setForeground(LABEL_COLOR);
		btnCancel.setColorOver(RIGHT_COLOR);
		btnCancel.setBounds(377, 316, 103, 30);
		btnCancel.addActionListener(cpc);
		contentPanel.add(btnCancel);

		MyButton btnOK = new MyButton();
		btnOK.setText("Lưu");
		btnOK.setRadius(20);
		btnOK.setForeground(LABEL_COLOR);
		btnOK.setFocusPainted(false);
		btnOK.setColorOver(btnCancel.getColorOver());
		btnOK.setColor(COLOR1);
		btnOK.setFont(BUTTON_FONT);
		btnOK.setBorderColor(LABEL_COLOR);
		btnOK.addActionListener(cpc);
		btnOK.setBounds(btnCancel.getX() - btnCancel.getWidth() - 10, btnCancel.getY(), btnCancel.getWidth(),
				btnCancel.getHeight());
		contentPanel.add(btnOK);
		
		this.setVisible(true);
	}
	
	public boolean getIsEditable() {
		return this.isEditable;
	}
	
	public User getUser() {
		return this.user;
	}
	
	@SuppressWarnings("deprecation")
	public boolean checkBeforeSaving() {
		boolean check = true;
		if (txtNewPass.getText().equals("") || txtOldPass.getText().equals("") || txtNewPassAgain.getText().equals("")) {
			check = false;
			JOptionPane.showMessageDialog(this, "Bạn đã chưa nhập mật khẩu cũ\n"
												+ "hoặc chưa nhập mật khẩu mới\nhoặc chưa xác nhận lại mật khẩu mới!");
		}
		else if (!txtOldPass.getText().equals(user.getPassword())) {
			check = false;
			JOptionPane.showMessageDialog(this, "Mật khẩu cũ bạn nhập vào không đúng!");
		}
		else if (txtNewPass.getText().length() < 6) {
			check = false;
			JOptionPane.showMessageDialog(this, "Chiều dài của mật khẩu mới phải nhiều hơn 6 kí tự!");
		}
		else if (!txtNewPass.getText().equals(txtNewPassAgain.getText())) {
			check = false;
			JOptionPane.showMessageDialog(this, "Mật khẩu mới và xác nhận mật khẩu không trùng khớp!");
		}
		return check;
	}
	
	public static void main(String[] args) {
		try {
			ChangePassword dialog = new ChangePassword("0905601223");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
