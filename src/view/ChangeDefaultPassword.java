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

import controller.ChangeDefaultPasswordCTL;
import custom.MyButton;
import custom.MyTextField;
import custom.PanelRound;

public class ChangeDefaultPassword extends JDialog {

	private static final long serialVersionUID = 1L;
	private final Color BACKGROUND = new Color(0, 45, 97);
	private final Color RIGHT_COLOR = new Color(242, 224, 191);
	private final Color COLOR1 = new Color(244, 203, 198);
	private final Color LABEL_COLOR = new Color(0, 0, 0);

	private final Font LABEL_FONT = new Font("Times New Roman", Font.PLAIN, 20);
	private final Font BUTTON_FONT = new Font("Times New Roman", Font.BOLD, 16);
	private final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 30);
	private final JPanel contentPanel = new JPanel();

	private JLabel title;
	public MyTextField txtNewPass;

	public ChangeDefaultPassword() {
		initComponents();
	}

	public void initComponents() {
		setModal(true);
		setUndecorated(true);
		setSize(550, 280);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		ChangeDefaultPasswordCTL cpc = new ChangeDefaultPasswordCTL(this);
		contentPanel.setBackground(BACKGROUND);

		title = new JLabel("THAY ĐỔI MẬT KHẨU MẶC ĐỊNH");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(TITLE_FONT);
		title.setForeground(COLOR1);
		title.setBounds(20, 21, 520, 45);
		contentPanel.add(title);

		PanelRound panel = new PanelRound();
		panel.setBorderWidth(3);
		panel.setBorderColor(Color.black);
		panel.setRadius(25);
		panel.setBground(COLOR1);
		panel.setBounds(20, 76, 520, 151);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblAccount = new JLabel("Mật khẩu cũ:");
		lblAccount.setBounds(0, 39, 202, 30);
		panel.add(lblAccount);
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setForeground(BACKGROUND);
		lblAccount.setFont(LABEL_FONT);

		MyTextField txtOldPass = new MyTextField();
		txtOldPass.setLineColor(Color.BLACK);
		txtOldPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtOldPass.setForeground(new Color(0, 45, 97));
		txtOldPass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtOldPass.setBackground(new Color(244, 203, 198));
		txtOldPass.setBounds(215, 35, 258, 30);
		txtOldPass.setEditable(false);
		String oldPass = cpc.getDefaultPass();
		txtOldPass.setText(oldPass.substring(1, oldPass.length() - 1));
		panel.add(txtOldPass);

		JLabel lblMtKhuMi = new JLabel("Mật khẩu mới:");
		lblMtKhuMi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMtKhuMi.setForeground(new Color(0, 45, 97));
		lblMtKhuMi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMtKhuMi.setBounds(0, 85, 202, 30);
		panel.add(lblMtKhuMi);

		txtNewPass = new MyTextField();
		txtNewPass.setText((String) null);
		txtNewPass.setLineColor(Color.BLACK);
		txtNewPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtNewPass.setForeground(new Color(0, 45, 97));
		txtNewPass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtNewPass.setBackground(new Color(244, 203, 198));
		txtNewPass.setBounds(215, 81, 258, 30);
		panel.add(txtNewPass);

		MyButton btnCancel = new MyButton();
		btnCancel.setFocusPainted(false);
		btnCancel.setText("Đóng");
		btnCancel.setRadius(15);
		btnCancel.setColor(COLOR1);
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setBorderColor(LABEL_COLOR);
		btnCancel.setForeground(LABEL_COLOR);
		btnCancel.setColorOver(RIGHT_COLOR);
		btnCancel.setBounds(418, 237, 103, 30);
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
		btnOK.setBounds(305, 237, btnCancel.getWidth(), btnCancel.getHeight());
		contentPanel.add(btnOK);

		this.setVisible(true);
	}

	public boolean checkBeforeSaving() {
		boolean check = true;
		if (txtNewPass.getText().equals("")) {
			check = false;
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mặc định mới!");
		} else if (txtNewPass.getText().length() < 6) {
			check = false;
			JOptionPane.showMessageDialog(this, "Mật khẩu tối thiểu 6 kí tự!");
		}
		return check;
	}

	public static void main(String[] args) {
		try {
			ChangeDefaultPassword dialog = new ChangeDefaultPassword();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
