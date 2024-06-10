package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import controller.UserDetailController;
import custom.MyButton;
import custom.MyTextField;
import custom.PanelRound;
import model.User;
import javax.swing.JRadioButton;

public class UserDetail extends JDialog {

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
	private MyTextField textUsername;

	private UserDetailController udctl;
	private User user;
	private boolean isNew;
	private boolean isEditable;
	private MyTextField textDay;
	private MyTextField textMonth;
	private MyTextField textYear;
	private JLabel title;
	private MyTextField textNewPassword;
	private MyTextField textOldPassword;
	private MyTextField textPosition;
	private MyTextField textId;
	private ButtonGroup btnActive;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UserDetail dialog = new UserDetail("0000453257", true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UserDetail() {
		udctl = new UserDetailController(this);
		isNew = true;
		isEditable = true;
		initComponents();
	}

	public UserDetail(String username, boolean isEditable) {
		udctl = new UserDetailController(this);
		isNew = false;
		this.isEditable = isEditable;
		this.user = udctl.selectUserByUsername(username);
		initComponents();
	}

	public void initComponents() {
		setModal(true);
		setUndecorated(true);
		setSize(480, 450);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		contentPanel.setBackground(BACKGROUND);

		title = new JLabel("CHI TIẾT TÀI KHOẢN");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(TITLE_FONT);
		title.setForeground(COLOR1);
		title.setBounds(20, 21, 438, 45);
		contentPanel.add(title);

		PanelRound panel = new PanelRound();
		panel.setBorderWidth(3);
		panel.setBorderColor(Color.black);
		panel.setRadius(25);
		panel.setBground(COLOR1);
		panel.setBounds(20, 76, 438, 306);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblThiGian = new JLabel("Tài khoản:");
		lblThiGian.setBounds(21, 40, 91, 30);
		panel.add(lblThiGian);
		lblThiGian.setHorizontalAlignment(SwingConstants.LEFT);
		lblThiGian.setForeground(BACKGROUND);
		lblThiGian.setFont(LABEL_FONT);

		textUsername = new MyTextField();
		textUsername.setBounds(122, 40, 295, 30);
		panel.add(textUsername);
		textUsername.setLineColor(Color.black);
		textUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textUsername.setForeground(BACKGROUND);
		textUsername.setFont(TEXT_FONT);
		textUsername.setBackground(COLOR1);
		textUsername.setEditable(false);

		JLabel lblStart = new JLabel("Lần cuối đăng nhập:");
		lblStart.setBounds(21, 84, 170, 30);
		panel.add(lblStart);
		lblStart.setHorizontalAlignment(SwingConstants.LEFT);
		lblStart.setForeground(BACKGROUND);
		lblStart.setFont(LABEL_FONT);

		textDay = new MyTextField();
		textDay.setEditable(false);
		textDay.setBounds(201, 80, 22, 30);
		panel.add(textDay);
		textDay.setHorizontalAlignment(SwingConstants.RIGHT);
		textDay.setLineColor(Color.black);
		textDay.setForeground(BACKGROUND);
		textDay.setFont(TEXT_FONT);
		textDay.setBackground(COLOR1);

		JLabel lblSeperate_2 = new JLabel("/");
		lblSeperate_2.setBounds(223, 80, 25, 30);
		panel.add(lblSeperate_2);
		lblSeperate_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeperate_2.setForeground(BACKGROUND);
		lblSeperate_2.setFont(new Font("Times New Roman", Font.BOLD, 30));

		textMonth = new MyTextField();
		textMonth.setEditable(false);
		textMonth.setBounds(233, 80, 22, 30);
		panel.add(textMonth);
		textMonth.setHorizontalAlignment(SwingConstants.RIGHT);
		textMonth.setLineColor(Color.black);
		textMonth.setForeground(BACKGROUND);
		textMonth.setFont(TEXT_FONT);
		textMonth.setBackground(COLOR1);

		JLabel lblSeperate_1_1 = new JLabel("/");
		lblSeperate_1_1.setBounds(255, 80, 25, 30);
		panel.add(lblSeperate_1_1);
		lblSeperate_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeperate_1_1.setForeground(BACKGROUND);
		lblSeperate_1_1.setFont(new Font("Times New Roman", Font.BOLD, 30));

		textYear = new MyTextField();
		textYear.setEditable(false);
		textYear.setBounds(265, 80, 44, 30);
		panel.add(textYear);
		textYear.setLineColor(Color.black);
		textYear.setForeground(BACKGROUND);
		textYear.setFont(TEXT_FONT);
		textYear.setBackground(COLOR1);

		MyButton btnCancel = new MyButton();
		btnCancel.setFocusPainted(false);
		btnCancel.setText("Đóng");
		btnCancel.setRadius(15);
		btnCancel.setColor(COLOR1);
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setBorderColor(LABEL_COLOR);
		btnCancel.setForeground(LABEL_COLOR);
		btnCancel.setColorOver(RIGHT_COLOR);
		btnCancel.setBounds(355, 392, 103, 30);
		btnCancel.addActionListener(udctl);
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
		btnOK.addActionListener(udctl);
		btnOK.setBounds(btnCancel.getX() - btnCancel.getWidth() - 10, btnCancel.getY(), btnCancel.getWidth(),
				btnCancel.getHeight());
		
		JLabel lblHotng = new JLabel("Hoạt động:");
		lblHotng.setHorizontalAlignment(SwingConstants.LEFT);
		lblHotng.setForeground(new Color(0, 45, 97));
		lblHotng.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHotng.setBounds(21, 121, 91, 30);
		panel.add(lblHotng);
		
		btnActive = new ButtonGroup();
		rdbtnYes = new JRadioButton("Có");
		rdbtnYes.setFont(TEXT_FONT);
		rdbtnYes.setForeground(BACKGROUND);
		rdbtnYes.setBackground(COLOR1);
		rdbtnYes.setFocusPainted(false);
		rdbtnYes.setBounds(129, 126, 62, 21);
		btnActive.add(rdbtnYes);
		panel.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("Không");
		rdbtnNo.setFont(TEXT_FONT);
		rdbtnNo.setForeground(BACKGROUND);
		rdbtnNo.setBackground(COLOR1);
		rdbtnNo.setFocusPainted(false);
		rdbtnNo.setBounds(193, 126, 87, 21);
		btnActive.add(rdbtnNo);
		panel.add(rdbtnNo);
		
		JLabel lblNgiDng = new JLabel("Mã người dùng:");
		lblNgiDng.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgiDng.setForeground(new Color(0, 45, 97));
		lblNgiDng.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgiDng.setBounds(21, 161, 128, 30);
		panel.add(lblNgiDng);
		
		textId = new MyTextField();
		textId.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setEditable(false);
		textId.setLineColor(Color.BLACK);
		textId.setForeground(new Color(0, 45, 97));
		textId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textId.setBackground(new Color(244, 203, 198));
		textId.setBounds(154, 162, 54, 30);
		panel.add(textId);
		
		JLabel lblChcV = new JLabel("Chức vụ:");
		lblChcV.setHorizontalAlignment(SwingConstants.LEFT);
		lblChcV.setForeground(new Color(0, 45, 97));
		lblChcV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblChcV.setBounds(218, 161, 87, 30);
		panel.add(lblChcV);
		
		textPosition = new MyTextField();
		textPosition.setEditable(false);
		textPosition.setHorizontalAlignment(SwingConstants.CENTER);
		textPosition.setLineColor(Color.BLACK);
		textPosition.setForeground(new Color(0, 45, 97));
		textPosition.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textPosition.setBackground(new Color(244, 203, 198));
		textPosition.setBounds(299, 161, 118, 30);
		panel.add(textPosition);
		
		JLabel lblMtKhuHin = new JLabel("Mật khẩu hiện tại:");
		lblMtKhuHin.setHorizontalAlignment(SwingConstants.LEFT);
		lblMtKhuHin.setForeground(new Color(0, 45, 97));
		lblMtKhuHin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMtKhuHin.setBounds(21, 201, 144, 30);
		panel.add(lblMtKhuHin);
		
		textOldPassword = new MyTextField();
		textOldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textOldPassword.setEditable(false);
		textOldPassword.setLineColor(Color.BLACK);
		textOldPassword.setForeground(new Color(0, 45, 97));
		textOldPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textOldPassword.setBackground(new Color(244, 203, 198));
		textOldPassword.setBounds(175, 201, 242, 30);
		panel.add(textOldPassword);
		
		JLabel lblMtKhuMi = new JLabel("Mật khẩu mới:");
		lblMtKhuMi.setHorizontalAlignment(SwingConstants.LEFT);
		lblMtKhuMi.setForeground(new Color(0, 45, 97));
		lblMtKhuMi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMtKhuMi.setBounds(21, 241, 144, 30);
		panel.add(lblMtKhuMi);
		
		textNewPassword = new MyTextField();
		textNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textNewPassword.setLineColor(Color.BLACK);
		textNewPassword.setForeground(new Color(0, 45, 97));
		textNewPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		textNewPassword.setBackground(new Color(244, 203, 198));
		textNewPassword.setBounds(175, 241, 242, 30);
		panel.add(textNewPassword);
		
		if (isEditable) {
			contentPanel.add(btnOK);
		} else {
			textNewPassword.setEditable(false);
		}
		if (!isNew) {
			initInfo();
		}
		this.setVisible(true);
	}

	public void initInfo() {
		LocalDate date = user.getLastLogin().toLocalDate();
		textDay.setText(date.getDayOfMonth() + "");
		textMonth.setText(date.getMonthValue() + "");
		textYear.setText(date.getYear() + "");
		String position = "Phụ huynh";
		if (user.getPosition().equals("Teacher"))
			position = "Giáo viên";
		else if (user.getPosition().equals("Adimn")) {
			position = "Quản trị viên";
		}
		textPosition.setText(position);
		textOldPassword.setText(user.getPassword());
		if (user.isAvtive()) {
			rdbtnYes.setSelected(true);
			if (!isEditable)
				rdbtnNo.setEnabled(false);
		} else {
			rdbtnNo.setSelected(true);
			if (!isEditable)
				rdbtnYes.setEnabled(false);
		}
		textUsername.setText(user.getUsername());
		String id = udctl.selectUserId(user.getPosition(), user.getUsername());
		textId.setText(id);
	}

	public boolean checkBeforeSave() {
		boolean result = true;
		String newPas = textNewPassword.getText();
		if (newPas.length() < 6) {
			textNewPassword.setBackground(RIGHT_COLOR);
			result = false;
			JOptionPane.showMessageDialog(this, "Mật khẩu tối thiểu 6 kí tự!");
		}
		return result;
	}
	
	public User getUser() {
		if (user == null)
			user = new User();
		user.setPassword(textNewPassword.getText());
		user.setAvtive(rdbtnYes.isSelected());
		return user;
	}
	
	public boolean getIsNew() {
		return this.isNew;
	}
	
	public boolean getIsEditable() {
		return this.isEditable;
	}
}
