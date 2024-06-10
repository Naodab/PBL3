package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.LogInCTL;
import custom.MyButton;
import custom.MyPasswordField;
import custom.MyTextField;
import custom.PanelRound;


public class LogIn extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelRound panelRound;
	private JLabel title1;
	private JLabel title2;
	private PanelRound panel;
	private JLabel Hello;
	private JLabel warning;
	private JLabel lblNewLabel;
	private MyTextField textField;
	private MyPasswordField passwordField;
	private JLabel background;
	private JLabel exit;

	public LogIn() {
		try {
			warning = new JLabel("", JLabel.CENTER);
			this.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init() throws IOException {
		final int WIDTH = 1400;
		final int HEIGHT = 800;
		final int SIZE_EXIT = 20;
		final Color content_Color = new Color(206, 158, 182);
		final LogInCTL lgc = new LogInCTL(this);
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 3));
		contentPane.setBounds(0, 0, getWidth(), getHeight());

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelRound = new PanelRound(50);
		panelRound.setBground(Color.white);
		panelRound.setBorderWidth(2);
		panelRound.setBorderColor(new Color(235, 235, 235));
		panelRound.setBounds(WIDTH / 6 , HEIGHT / 6, WIDTH * 2 / 3, HEIGHT * 2 / 3);
		contentPane.add(panelRound);
		panelRound.setLayout(null);

		background = new JLabel("New label");
		background.setBounds(panelRound.getHeight() / 20, panelRound.getHeight() / 20, panelRound.getHeight() * 9 / 10,
				panelRound.getHeight() * 9 / 10);
		background.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(LogIn.class.getResource("img/background.png"))
						.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH)));
		panelRound.add(background);

		title1 = new JLabel("TRƯỜNG TIỂU HỌC");
		title1.setHorizontalAlignment(SwingConstants.LEFT);
		title1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		title1.setForeground(new Color(0, 0, 0));
		title1.setBounds(539, 39, 394, 65);
		panelRound.add(title1);

		title2 = new JLabel("SỐ I HÒA PHƯỚC");
		title2.setHorizontalAlignment(SwingConstants.LEFT);
		title2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		title2.setForeground(new Color(0, 0, 0));
		title2.setBounds(539, 80, 393, 55);
		panelRound.add(title2);
		
		panel = new PanelRound();
		panel.setBounds(525, 226, 387, 189);
		panel.setRadius(25);
		panel.setBground(content_Color);
		panel.setBorderColor(new Color(235, 235, 235));
		panel.setBorderWidth(1);
		panelRound.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Tài khoản:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(29, 28, 333, 24);
		panel.add(lblNewLabel);
		
		textField = new MyTextField(this);
		textField.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		textField.setBounds(29, 57, 333, 31);
		textField.setBackground(content_Color);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblMtKhu.setBounds(29, 100, 333, 24);
		panel.add(lblMtKhu);
		
		passwordField = new MyPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		passwordField.setBackground(content_Color);
		passwordField.setBounds(29, 122, 333, 31);
		panel.add(passwordField);
		
		MyButton logIn = new MyButton();
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logIn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		logIn.setText("Đăng nhập");
		logIn.setRadius(20);
		logIn.setColor(content_Color);
		logIn.setBorderColor(content_Color);
		logIn.setColorOver(new Color(222, 184, 200));
		logIn.setBounds(560, 450, 326, 35);
		logIn.addActionListener(lgc);
		panelRound.add(logIn);
		
		Hello = new JLabel("Xin chào, chúng tôi rất vui vì sự trở lại của bạn.");
		Hello.setHorizontalAlignment(SwingConstants.LEFT);
		Hello.setForeground(new Color(0, 0, 0));
		Hello.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		Hello.setBounds(540, 114, 393, 55);
		panelRound.add(Hello);
		
		warning = new JLabel("");
		warning.setHorizontalAlignment(SwingConstants.CENTER);
		warning.setForeground(new Color(255, 0, 0));
		warning.setBounds(525, 196, 387, 20);
		warning.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panelRound.add(warning);
		
		exit = new JLabel("X");
		exit.setForeground(Color.red);
		exit.setFont(new Font("Times New Roman", Font.BOLD, 17));
		exit.setBounds(WIDTH - SIZE_EXIT, 0, SIZE_EXIT, SIZE_EXIT);
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(exit);
		this.setVisible(true);
	}

	public String getUsername() {
		return this.textField.getText();
	}

	public String getPassword() {
		char[] pw = this.passwordField.getPassword();
		return new String(pw);
	}
	
	public void setWarning(String str) {
		warning.setText(str);
		this.repaint();
	}
	
	public void end(int situation, int id) {
		this.dispose();
		if (situation == 1) {
			System.out.println("skdjfh");
			new ParentsScreen(id);
		} else if (situation == 2) {
			new TeacherScreen(id);
		} else if (situation == 3) {
			new AdminScreen();
		}
	}

	public static void main(String[] args) {
		new LogIn();
	}
}
