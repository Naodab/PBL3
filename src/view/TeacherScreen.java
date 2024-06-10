package view;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import controller.TableTeacherCTL;
import controller.TeacherController;
import custom.MyButton;
import custom.MyTable;
import custom.MyTextField;
import custom.PanelRound;
import custom.TableActionTcCellEditor;
import custom.TableActionTcCellRender;
import model.BoardingClass;
import model.BoardingFee;
import model.CBBItem;
import model.EatingHistory;
import model.Food;
import model.Invoice;
import model.Student;
import model.Teacher;

import java.awt.Component;

public class TeacherScreen extends JFrame {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	private static final int HORIZONTAL_SCALE = 5;
	private static final int VERTICAL_SCALE = 12;
	private static final int GAP_MENU = 30;
	private static final int PADDING = 30;
	private static final int HEIGHT_BUTTON = 45;
	private static final int X_BUTTON = 0;
	private static final int Y_BUTTON = 227;
	private static final Color RIGHT_COLOR = new Color(242, 224, 191);
	private static final Color LEFT_COLOR = new Color(209, 174, 191);
	private static final Color BUTTON_FG_COLOR = Color.white;
	private static final Color SEARCH_COLOR = new Color(244, 203, 198);
	private static final Color BUTTON_COLOR = new Color(0, 45, 97);
	private static final Color BUTTON_OVER_COLOR = new Color(24, 89, 158);
	private static final Color TABLEHEADER_COLOR = new Color(184, 207, 238);
	private static final Font COLUMN_TABLE_FONT = new Font("Times New Roman", Font.BOLD, 15);

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout card_right;

	// controller to deal with action for teacher
	private TeacherController tc;

	// controller to deal with action for a table
	// private TableController tableCL;

	// this is the last login for this teacher
	public Teacher teacher;

	// served for button1 (teacher information) (textfields and buttons)
	private MyTextField txtName, txtDateOfBirth, txtClassID, txtTeacherID;
	private ButtonGroup buttonGroupGender;
	private JRadioButton radioButtonMale, radioButtonFemale;
	public MyTextField txtEmail, txtPhoneNumber, txtAddress;
	public MyButton buttonSaveInforTeacher = new MyButton(), buttonEditInforTeacher = new MyButton();
	public boolean isEditable;

	// served for button2 (payment history)
	private MyTable paymentTable;
	public DefaultTableModel dataPayment;
	public JComboBox<CBBItem> jcbbPayment;
	public MyButton buttonSavePayment;
	public Date paymentDay;
	public List<Integer> listStatus;

	// served for button3 (student information)
	private MyTable tableStudents;
	public DefaultTableModel dataStudent1;

	// served for button4 (foodday)
	private MyTable foodTable;
	public DefaultTableModel dataFood;
	public JComboBox<String> jcbbEating;

	// served for button5 (monitor student)
	public MyButton buttonAbsence = new MyButton(), buttonPhysical = new MyButton();
	public MyButton buttonSaveAbsence = new MyButton(), buttonSavePhysical = new MyButton();
	public Date absenceDay;
	public DefaultTableModel dataStudent2, dataStudent3;

	// served for buttons side bar
	public MyButton button1;
	public MyButton button2;
	public MyButton button3;
	public MyButton button4;
	public MyButton button5;
	public PanelRound panel_right;
	public MyButton logOut;
	public MyButton changePw;

	public static void main(String[] args) {
		new TeacherScreen(2);
	}

	public TeacherScreen(int teacher_id) {
		tc = new TeacherController(this);
		this.teacher = tc.selectTeacherById(teacher_id);
		initComponents();
	}

	public void initComponents() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 100));
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		contentPane.add(panel);
		panel.setLayout(null);

		PanelRound controlBar = new PanelRound();
		controlBar.setBounds(39, 33, WIDTH / HORIZONTAL_SCALE, HEIGHT / VERTICAL_SCALE * (VERTICAL_SCALE - 1));
		controlBar.setBground(LEFT_COLOR);
		controlBar.setRadius(40);
		controlBar.setBorderWidth(2);
		controlBar.setBorderColor(Color.black);
		panel.add(controlBar);
		controlBar.setLayout(null);

		logOut = new MyButton();
		logOut.setFont(COLUMN_TABLE_FONT);
		logOut.setColorClick(new Color(117, 81, 6));
		logOut.setColor(BUTTON_COLOR);
		logOut.setColorOver(BUTTON_OVER_COLOR);
		logOut.setForeground(BUTTON_FG_COLOR);
		logOut.setText("Đăng xuất");
		logOut.setFocusPainted(false);
		logOut.setBorderColor(new Color(0, 0, 0));
		logOut.setRadius(20);
		logOut.setBounds(PADDING, controlBar.getHeight() - 2 * PADDING, controlBar.getWidth() - 2 * PADDING, PADDING);
		logOut.addActionListener(tc);
		controlBar.add(logOut);

		changePw = new MyButton();
		changePw.setFont(COLUMN_TABLE_FONT);
		changePw.setColorClick(new Color(117, 81, 6));
		changePw.setColor(Color.white);
		changePw.setColorOver(new Color(230, 230, 230));
		changePw.setForeground(BUTTON_COLOR);
		changePw.setText("Thay đổi mật khẩu");
		changePw.setFocusPainted(false);
		changePw.setBorderColor(new Color(0, 0, 0));
		changePw.setRadius(20);
		changePw.setBounds(PADDING, controlBar.getHeight() - 2 * PADDING - 10 - logOut.getHeight(),
				controlBar.getWidth() - 2 * PADDING, PADDING);
		changePw.addActionListener(tc);
		controlBar.add(changePw);

		JLabel title1 = new JLabel("TRƯỜNG TIỂU HỌC");
		title1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title1.setForeground(Color.BLACK);
		title1.setBounds(PADDING, PADDING, controlBar.getWidth() - 2 * PADDING, PADDING);
		controlBar.add(title1);

		JLabel title2 = new JLabel("SỐ I HÒA PHƯỚC");
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setForeground(title1.getForeground());
		title2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		title2.setForeground(Color.BLACK);
		title2.setBounds(PADDING, 2 * PADDING, controlBar.getWidth() - 2 * PADDING, PADDING);
		controlBar.add(title2);

		button1 = new MyButton();
		button1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		button1.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		button1.setText("Thông tin cá nhân   ");
		button1.setColor(LEFT_COLOR);
		button1.setOpaque(true);
		button1.setFocusPainted(false);
		button1.setColorOver(new Color(224, 195, 210));
		button1.setBounds(X_BUTTON + 2, Y_BUTTON, controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON);
		button1.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(TeacherScreen.class.getResource("img/icon/personalInfor.png"))
						.getScaledInstance(HEIGHT_BUTTON - 10, HEIGHT_BUTTON - 10, Image.SCALE_SMOOTH)));
		button1.addActionListener(tc);
		controlBar.add(button1);

		button2 = new MyButton();
		button2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		button2.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button2.setText("Thu học phí             ");
		button2.setColor(LEFT_COLOR);
		button2.setOpaque(true);
		button2.setFocusPainted(false);
		button2.setColorOver(new Color(224, 195, 210));
		button2.setBounds(X_BUTTON + 2, Y_BUTTON + (HEIGHT_BUTTON - 1), controlBar.getWidth() - 2 * X_BUTTON - 4,
				HEIGHT_BUTTON);
		button2.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(TeacherScreen.class.getResource("img/icon/pay.png"))
						.getScaledInstance(HEIGHT_BUTTON - 10, HEIGHT_BUTTON - 10, Image.SCALE_SMOOTH)));
		button2.addActionListener(tc);
		controlBar.add(button2);

		button3 = new MyButton();
		button3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		button3.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button3.setText("Thông tin học sinh  ");
		button3.setColor(LEFT_COLOR);
		button3.setOpaque(true);
		button3.setFocusPainted(false);
		button3.setColorOver(new Color(224, 195, 210));
		button3.setBounds(X_BUTTON + 2, Y_BUTTON + 2 * (HEIGHT_BUTTON - 1), controlBar.getWidth() - 2 * X_BUTTON - 4,
				HEIGHT_BUTTON);
		button3.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(TeacherScreen.class.getResource("img/icon/reading.png"))
						.getScaledInstance(HEIGHT_BUTTON - 10, HEIGHT_BUTTON - 10, Image.SCALE_SMOOTH)));
		button3.addActionListener(tc);
		controlBar.add(button3);

		button4 = new MyButton();
		button4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		button4.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button4.setText("Theo dõi ngày ăn    ");
		button4.setColor(LEFT_COLOR);
		button4.setOpaque(true);
		button4.setFocusPainted(false);
		button4.setColorOver(new Color(224, 195, 210));
		button4.setBounds(X_BUTTON + 2, Y_BUTTON + 3 * (HEIGHT_BUTTON - 1), controlBar.getWidth() - 2 * X_BUTTON - 4,
				HEIGHT_BUTTON);
		button4.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(TeacherScreen.class.getResource("img/icon/eat2.png"))
						.getScaledInstance(HEIGHT_BUTTON - 10, HEIGHT_BUTTON - 10, Image.SCALE_SMOOTH)));
		button4.addActionListener(tc);
		controlBar.add(button4);

		button5 = new MyButton();
		button5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		button5.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button5.setText("Giám sát học sinh   ");
		button5.setColor(LEFT_COLOR);
		button5.setOpaque(true);
		button5.setFocusPainted(false);
		button5.setColorOver(new Color(224, 195, 210));
		button5.setBounds(X_BUTTON + 2, Y_BUTTON + 4 * (HEIGHT_BUTTON - 1), controlBar.getWidth() - 2 * X_BUTTON - 4,
				HEIGHT_BUTTON);
		button5.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(TeacherScreen.class.getResource("img/icon/management.png"))
						.getScaledInstance(HEIGHT_BUTTON - 10, HEIGHT_BUTTON - 10, Image.SCALE_SMOOTH)));
		button5.addActionListener(tc);
		controlBar.add(button5);

		panel_right = new PanelRound();
		panel_right.setBounds(controlBar.getX() + controlBar.getWidth() + GAP_MENU, controlBar.getY(),
				WIDTH / HORIZONTAL_SCALE * (HORIZONTAL_SCALE - 1) - HEIGHT / VERTICAL_SCALE - GAP_MENU,
				controlBar.getHeight());
		panel_right.setRadius(40);
		panel_right.setBorderColor(new Color(250, 250, 250));
		panel.add(panel_right);
		panel_right.setLayout(new CardLayout(0, 0));
		card_right = (CardLayout) panel_right.getLayout();

		PanelRound panel_1 = new PanelRound();
		ListTeacherInfor(panel_right, panel_1);
		buttonEditInforTeacher.addActionListener(tc);
		buttonSaveInforTeacher.addActionListener(tc);

		PanelRound panel_2 = new PanelRound();
		paymentHistoryInfor(panel_right, panel_2);
		jcbbPayment.addActionListener(tc);
		buttonSavePayment.addActionListener(tc);

		PanelRound panel_3 = new PanelRound();
		ListStudentsInfor(panel_right, panel_3);

		PanelRound panel_4 = new PanelRound();
		foodHistory(panel_right, panel_4);
		jcbbEating.addActionListener(tc);

		PanelRound panel_5 = new PanelRound();
		monitorAbsence(panel_right, panel_5);
		buttonAbsence.addActionListener(tc);
		buttonSaveAbsence.addActionListener(tc);

		PanelRound panel_6 = new PanelRound();
		monitorPhysical(panel_right, panel_6);
		buttonPhysical.addActionListener(tc);
		buttonSavePhysical.addActionListener(tc);

		JLabel background = new JLabel();
		background.setBounds(0, 0, WIDTH, HEIGHT);
		background.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(TeacherScreen.class.getResource("img/bk.jpg"))
						.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH)));
		contentPane.add(background);

		setVisible(true);

	}

	public void setCardRight(String name) {
		card_right.show(panel_right, name);
	}

	public void viewTeacher() {
		txtName.setText(teacher.getName());
		txtDateOfBirth.setText(teacher.getDateOfBirth().toString());
		txtAddress.setText(teacher.getAddress());
		BoardingClass bc = tc.selectBoardingClassById(teacher.getBoardingClass_id());
		txtClassID.setText(bc.getName());
		txtEmail.setText(teacher.getEmail());
		if (teacher.getSex() == true) {
			radioButtonMale.setSelected(true);
			radioButtonFemale.setSelected(false);
			AbstractButton button = radioButtonFemale;
			button.getModel().setEnabled(false);
		} else {
			radioButtonMale.setSelected(false);
			radioButtonFemale.setSelected(true);
			AbstractButton button = radioButtonMale;
			button.getModel().setEnabled(false);
		}
		txtPhoneNumber.setText(teacher.getPhoneNumber());
		txtTeacherID.setText(teacher.getTeacher_id() + "");
	}

	public void ListTeacherInfor(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		childPanel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);
		parentPanel.add(childPanel, "teacherInformation");

		JLabel lblHeader = new JLabel("THÔNG TIN CÁ NHÂN");
		lblHeader.setForeground(Color.BLACK);
		lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHeader.setBounds(0, PADDING + 50, parentPanel.getWidth(), 45);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		childPanel.add(lblHeader);

		JLabel avatar = new JLabel();
		avatar.setOpaque(true);
		avatar.setBounds(PADDING + 67, PADDING + 139, 150, 175);
		String path = System.getProperty("user.dir") + "\\avatar\\Teacher\\" + teacher.getTeacher_id() + ".jpg";
		File file = new File(path);
		if (file.exists()) {
			avatar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(path)
					.getScaledInstance(avatar.getWidth(), avatar.getHeight(), Image.SCALE_SMOOTH)));

		}
		childPanel.add(avatar);

		JLabel lblName = new JLabel("Họ và tên:", SwingConstants.RIGHT);
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblName.setBounds(15, 20, 100, 31);

		JLabel lblDateOfBirth = new JLabel("Ngày sinh:", SwingConstants.RIGHT);
		lblDateOfBirth.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDateOfBirth.setBounds(15, 68, 100, 31);

		JLabel lblGender = new JLabel("Giới tính:", SwingConstants.RIGHT);
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGender.setBounds(15, 114, 100, 31);

		JLabel lblTeacherID = new JLabel("Mã giáo viên:", SwingConstants.RIGHT);
		lblTeacherID.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTeacherID.setBounds(370, 20, 150, 31);

		JLabel lblClass = new JLabel("Lớp theo dạy:", SwingConstants.RIGHT);
		lblClass.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblClass.setBounds(370, 68, 150, 31);

		JLabel lblAddress = new JLabel("Địa chỉ:", SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAddress.setBounds(32, 24, 150, 31);

		JLabel lblPhone = new JLabel("Số điện thoại:", SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPhone.setBounds(32, 70, 150, 31);

		JLabel lblEmail = new JLabel("Email: ", SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEmail.setBounds(32, 116, 150, 31);

		txtName = new MyTextField();
		txtName.setFocusable(false);
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtName.setBounds(115 + 10, 20, 230, 31);
		txtName.setBackground(SEARCH_COLOR);
		txtName.setEditable(false);
		txtName.setBorder(null);

		txtDateOfBirth = new MyTextField();
		txtDateOfBirth.setFocusable(false);
		txtDateOfBirth.setFocusTraversalKeysEnabled(false);
		txtDateOfBirth.setBounds(115 + 10, 68, 230, 31);
		txtDateOfBirth.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDateOfBirth.setBackground(SEARCH_COLOR);
		txtDateOfBirth.setEditable(false);
		txtDateOfBirth.setBorder(null);

		buttonGroupGender = new ButtonGroup();
		radioButtonMale = new JRadioButton("Nam");
		radioButtonMale.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		radioButtonMale.setBackground(SEARCH_COLOR);
		radioButtonMale.setBounds(115 + 10, 114, 80, 31);
		radioButtonMale.setFocusPainted(false);
		radioButtonFemale = new JRadioButton("Nữ");
		radioButtonFemale.setBackground(SEARCH_COLOR);
		radioButtonFemale.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		radioButtonFemale.setBounds(115 + 10 + 80, 114, 80, 31);
		radioButtonFemale.setFocusPainted(false);
		buttonGroupGender.add(radioButtonMale);
		buttonGroupGender.add(radioButtonFemale);

		txtAddress = new MyTextField();
		txtAddress.setBounds(182 + 10, 24, 570, 31);
		txtAddress.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtAddress.setBackground(SEARCH_COLOR);
		txtAddress.setEditable(false);
		txtAddress.setBorder(null);

		txtTeacherID = new MyTextField();
		txtTeacherID.setFocusable(false);
		txtTeacherID.setBounds(520 + 10, 20, 50, 31);
		txtTeacherID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTeacherID.setBackground(SEARCH_COLOR);
		txtTeacherID.setEditable(false);
		txtTeacherID.setForeground(BUTTON_COLOR);
		txtTeacherID.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeacherID.setBorder(null);

		txtEmail = new MyTextField();
		txtEmail.setBounds(182 + 10, 116, 570, 31);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtEmail.setBackground(SEARCH_COLOR);
		txtEmail.setEditable(false);
		txtEmail.setBorder(null);

		txtPhoneNumber = new MyTextField();
		txtPhoneNumber.setBounds(182 + 10, 70, 570, 31);
		txtPhoneNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtPhoneNumber.setBackground(SEARCH_COLOR);
		txtPhoneNumber.setEditable(false);
		txtPhoneNumber.setBorder(null);

		txtClassID = new MyTextField();
		txtClassID.setFocusTraversalKeysEnabled(false);
		txtClassID.setFocusable(false);
		txtClassID.setColumns(10);
		txtClassID.setBounds(520 + 10, 68, 50, 31);
		txtClassID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtClassID.setBackground(SEARCH_COLOR);
		txtClassID.setEditable(false);
		txtClassID.setForeground(BUTTON_COLOR);
		txtClassID.setBorder(null);
		txtClassID.setHorizontalAlignment(SwingConstants.CENTER);

		buttonSaveInforTeacher.setFont(new Font("Times New Roman", Font.BOLD, 19));
		buttonSaveInforTeacher.setText("Lưu thông tin");
		buttonSaveInforTeacher.setRadius(20);
		buttonSaveInforTeacher.setBounds(6 * PADDING + 380, 645, parentPanel.getWidth() - 25 * PADDING - 120, 40);
		buttonSaveInforTeacher.setFocusPainted(false);
		buttonSaveInforTeacher.setColorClick(new Color(117, 81, 6));
		buttonSaveInforTeacher.setColor(BUTTON_COLOR);
		buttonSaveInforTeacher.setForeground(BUTTON_FG_COLOR);
		buttonSaveInforTeacher.setColorOver(BUTTON_OVER_COLOR);
		buttonSaveInforTeacher.setBorderColor(Color.black);
		buttonSaveInforTeacher.setVisible(false);

		buttonEditInforTeacher.setFont(new Font("Times New Roman", Font.BOLD, 19));
		buttonEditInforTeacher.setText("Chỉnh sửa thông tin");
		buttonEditInforTeacher.setRadius(20);
		buttonEditInforTeacher.setBounds(10 * PADDING + buttonSaveInforTeacher.getWidth() + 290, 645,
				parentPanel.getWidth() - 25 * PADDING - 90, 40);
		buttonEditInforTeacher.setFocusPainted(false);
		buttonEditInforTeacher.setColorClick(new Color(117, 81, 6));
		buttonEditInforTeacher.setColor(BUTTON_COLOR);
		buttonEditInforTeacher.setForeground(BUTTON_FG_COLOR);
		buttonEditInforTeacher.setColorOver(BUTTON_OVER_COLOR);
		buttonEditInforTeacher.setBorderColor(Color.black);

		PanelRound firstPanel = new PanelRound();
		firstPanel.setBounds(294, 169, 632, 175);
		firstPanel.setBground(SEARCH_COLOR);
		firstPanel.setRadius(40);
		firstPanel.setBorderWidth(2);
		firstPanel.setLayout(null);
		firstPanel.add(lblName);
		firstPanel.add(txtName);
		firstPanel.add(lblDateOfBirth);
		firstPanel.add(txtDateOfBirth);
		firstPanel.add(lblGender);
		firstPanel.add(radioButtonMale);
		firstPanel.add(radioButtonFemale);
		firstPanel.add(lblTeacherID);
		firstPanel.add(txtTeacherID);
		firstPanel.add(lblClass);
		firstPanel.add(txtClassID);

		PanelRound secondPanel = new PanelRound();
		secondPanel.setBounds(97, 385, 829, 190);
		secondPanel.setBground(SEARCH_COLOR);
		secondPanel.setRadius(40);
		secondPanel.setBorderWidth(2);
		secondPanel.setLayout(null);
		secondPanel.add(lblEmail);
		secondPanel.add(txtEmail);
		secondPanel.add(lblAddress);
		secondPanel.add(txtAddress);
		secondPanel.add(lblPhone);
		secondPanel.add(txtPhoneNumber);

		childPanel.add(buttonEditInforTeacher);
		childPanel.add(buttonSaveInforTeacher);
		childPanel.add(firstPanel);
		childPanel.add(secondPanel);
		viewTeacher();
	}

	public void viewStudent(String category) {
		// because "Thông tin học sinh" and "Điểm danh" uses the same function at the
		// same time, using "category" parameter to distinct them
		List<Student> listStudent = tc.getListStudentOfBoardingClass_id(teacher.getBoardingClass_id());
		if (category.equals("ListStudentsInfor")) {
			for (int i = 0; i < listStudent.size(); i++) {
				String namePa = tc.getNameParentsById(listStudent.get(i).getParents_id());
				dataStudent1.addRow(new Object[] { i + 1, listStudent.get(i).getStudent_id(),
						listStudent.get(i).getName(), namePa });
			}
		} else if (category.equals("monitorAbsence")) {
			for (int i = 0; i < listStudent.size(); i++) {
				dataStudent2.addRow(
						new Object[] { i + 1, listStudent.get(i).getStudent_id(), listStudent.get(i).getName() });
			}
		}
	}

	public String getNameOfClass() {
		BoardingClass bc = tc.selectBoardingClassById(teacher.getBoardingClass_id());
		return bc.getName();
	}

	public void ListStudentsInfor(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		childPanel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);
		parentPanel.add(childPanel, "studentInformation");

		tableStudents = new MyTable();

		JLabel lblHeader = new JLabel();
		lblHeader.setForeground(Color.BLACK);
		lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHeader.setText("THÔNG TIN HỌC SINH - LỚP " + getNameOfClass().toUpperCase());
		lblHeader.setBounds(0, PADDING + 20, parentPanel.getWidth(), 45);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		childPanel.add(lblHeader);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5 * PADDING - 60, 127 - 10, parentPanel.getWidth() - 10 * PADDING + 120, 456 + 50);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		childPanel.add(scrollPane);

		scrollPane.setViewportView(tableStudents);
		tableStudents.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tableStudents.setRowHeight(30);

//		dataStudent1 = new DefaultTableModel();
//		Object[] columns = {"STT", "Mã HS", "Họ và tên HS", "Họ và tên PH", "Chi tiết HS", "Chi tiết PH"};
//		dataStudent1.setColumnIdentifiers(columns);

		String[] columns = { "STT", "Mã HS", "Họ và tên HS", "Họ và tên PH", "Chi tiết HS", "Chi tiết PH" };
		boolean isEditable[] = { false, false, false, false, true, true };
		dataStudent1 = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return isEditable[column];
			}
		};
		dataStudent1.setColumnIdentifiers(columns);

		viewStudent("ListStudentsInfor");

		tableStudents.setModel(dataStudent1);

		JLabel lbl = new JLabel("Tổng:");
		lbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl.setBounds(641 + 90, 613 + 20, 60, 32);
		childPanel.add(lbl);

		JLabel lblAmount = new JLabel();
		lblAmount.setForeground(BUTTON_COLOR);
		lblAmount.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAmount.setBounds(700 + 90, 613 + 20, 103, 32);
		lblAmount.setText(tc.getAmountStudentOfBoardingClass_id(teacher.getBoardingClass_id()) + " học sinh");
		childPanel.add(lblAmount);

		TableColumnModel columnModel = tableStudents.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(70);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);

		JTableHeader header = tableStudents.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 18));
		header.setBackground(TABLEHEADER_COLOR);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tableStudents.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableStudents.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableStudents.getColumnModel().getColumn(4).setCellRenderer(new TableActionTcCellRender());
		tableStudents.getColumnModel().getColumn(5).setCellRenderer(new TableActionTcCellRender());

		tableStudents.getColumnModel().getColumn(4)
				.setCellEditor(new TableActionTcCellEditor(new TableTeacherCTL("Student", dataStudent1)));
		tableStudents.getColumnModel().getColumn(5)
				.setCellEditor(new TableActionTcCellEditor(new TableTeacherCTL("Parents", dataStudent1)));
		tableStudents.setGridColor(Color.BLACK);
	}

	public void viewCombobox() {
		for (CBBItem cbb : BoardingFee.getIdAndName()) {
			jcbbPayment.addItem(cbb);
		}
	}

	public void viewPayment(int id) {
		this.listStatus = new ArrayList<Integer>();
		List<Student> listStudents = tc.getListStudentOfBoardingClass_id(teacher.getBoardingClass_id());
		int numberOfRows = 1;
		for (Student std : listStudents) {
			Invoice invoice = tc.selectInvoiceByBfeeIdAndStd_id(id, std.getStudent_id());
			int status = invoice.getStatusPayment();
			String str_status = "";
			this.listStatus.add(status);
			if (status == 1 || status == 0)
				str_status = "Chưa nộp";
			else if (status == 2)
				str_status = "Đã nộp";
			else if (status == 3)
				str_status = "Đã in";
			dataPayment.addRow(new Object[] { numberOfRows, std.getStudent_id(), std.getName(), invoice.getInvoice_id(),
					str_status });
			numberOfRows++;
		}
	}

	public void paymentHistoryInfor(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		childPanel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);
		parentPanel.add(childPanel, "paymentHistory");

		paymentTable = new MyTable();

		JLabel lblHeader = new JLabel("THU HỌC PHÍ");
		lblHeader.setForeground(Color.BLACK);
		lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHeader.setBounds(0, PADDING - 8, parentPanel.getWidth(), 45);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		childPanel.add(lblHeader);

		jcbbPayment = new JComboBox<CBBItem>();
		jcbbPayment.setBounds(3 * lblHeader.getWidth() / 4 - 16, lblHeader.getHeight() + 50 - 25, 200, 28);
		jcbbPayment.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		jcbbPayment.setBackground(SEARCH_COLOR);
		jcbbPayment.addItem(new CBBItem(0, ""));
		childPanel.add(jcbbPayment);

		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String currentDateTime = dateFormat.format(currentDate);
		String[] arrOfStr = currentDateTime.split("/");
		LocalDate day = LocalDate.of(Integer.parseInt(arrOfStr[2]), Integer.parseInt(arrOfStr[1]),
				Integer.parseInt(arrOfStr[0]));
		paymentDay = Date.valueOf(day);

		JLabel lblPayment = new JLabel("Đợt nộp tiền: ");
		lblPayment.setForeground(Color.BLACK);
		lblPayment.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPayment.setBounds(jcbbPayment.getX() - 165, lblHeader.getHeight() + 43 - 25, 200, 45);
		lblPayment.setHorizontalAlignment(SwingConstants.CENTER);
		childPanel.add(lblPayment);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(3 * PADDING - 20, 150 - 35, parentPanel.getWidth() - 6 * PADDING + 40, 456 + 80);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		childPanel.add(scrollPane);

		buttonSavePayment = new MyButton();
		buttonSavePayment.setFont(new Font("Times New Roman", Font.BOLD, 17));
		buttonSavePayment.setText("Lưu thay đổi");
		buttonSavePayment.setRadius(20);
		buttonSavePayment.setBounds(jcbbPayment.getX() + jcbbPayment.getWidth() / 4 + 30, 665,
				parentPanel.getWidth() - 30 * PADDING, 40);
		buttonSavePayment.setFocusPainted(false);
		buttonSavePayment.setColorClick(new Color(117, 81, 6));
		buttonSavePayment.setColor(BUTTON_COLOR);
		buttonSavePayment.setColorOver(BUTTON_OVER_COLOR);
		buttonSavePayment.setForeground(BUTTON_FG_COLOR);
		buttonSavePayment.setBorderColor(Color.black);
		buttonSavePayment.setVisible(false);
		childPanel.add(buttonSavePayment);

		scrollPane.setViewportView(paymentTable);
		paymentTable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		paymentTable.setRowHeight(30);

//		dataPayment = new DefaultTableModel();
//		Object[] columns = {"Số thứ tự", "Mã học sinh", "Họ và tên", "Mã hóa đơn", "Trạng thái"};
//		dataPayment.setColumnIdentifiers(columns);

		String[] columns = { "Số thứ tự", "Mã học sinh", "Họ và tên", "Mã hóa đơn", "Trạng thái" };
		boolean isEditable[] = { false, false, false, false, true };
		dataPayment = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return isEditable[column];
			}
		};
		dataPayment.setColumnIdentifiers(columns);

		viewCombobox();
		paymentTable.setModel(dataPayment);

		TableColumnModel columnModel = paymentTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(20);
		columnModel.getColumn(1).setPreferredWidth(20);
		columnModel.getColumn(2).setPreferredWidth(120);
		columnModel.getColumn(4).setPreferredWidth(70);

		JTableHeader header = paymentTable.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 20));
		header.setBackground(TABLEHEADER_COLOR);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		paymentTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		paymentTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		paymentTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		paymentTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		paymentTable.setGridColor(Color.BLACK);
	}

	public void viewComboboxEating() {
		int numberOfItems = tc.getSizeOfBoardingFee();
		for (int i = 1; i <= numberOfItems; i++) {
			BoardingFee bf = tc.selectBoardingFeeById(i);
			LocalDate date = bf.getStart_day().toLocalDate();
			jcbbEating.addItem(i + " - tháng " + date.getMonthValue());
		}
	}

	public void viewFood(int boardingFee_id) {
		int numberOfRows = 1;
		List<EatingHistory> listEhis = tc.selectListEhisByBFeeid(boardingFee_id);
		for (EatingHistory ehis : listEhis) {
			String mainFood = "", subFood = "";
			List<Food> foods = tc.selectFoodsOfMenu(ehis.getMenu_id());
			for (Food food : foods) {
				if (food.getCategory()) {
					mainFood += food.getName() + ", ";
				} else {
					subFood += food.getName() + ", ";
				}
			}
			if (mainFood.length() > 0)
				mainFood = mainFood.substring(0, mainFood.length() - 2);
			if (subFood.length() > 0)
				subFood = subFood.substring(0, subFood.length() - 2);
			else
				subFood = "Không có";
			dataFood.addRow(new Object[] { numberOfRows++, ehis.getEating_day(), mainFood, subFood });
		}
	}

	public void foodHistory(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		childPanel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);
		parentPanel.add(childPanel, "foodHistory");

		foodTable = new MyTable();

		JLabel lblHeader = new JLabel("THEO DÕI NGÀY ĂN");
		lblHeader.setForeground(Color.BLACK);
		lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHeader.setBounds(0, PADDING, parentPanel.getWidth(), 45);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		childPanel.add(lblHeader);

		JLabel lblEating = new JLabel("Thực đơn cho đợt nộp học phí: ");
		lblEating.setForeground(Color.BLACK);
		lblEating.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblEating.setBounds(jcbbPayment.getX() - 275, lblHeader.getHeight() + 18 + 20, 270, 45);
		lblEating.setHorizontalAlignment(SwingConstants.CENTER);
		childPanel.add(lblEating);

		jcbbEating = new JComboBox<String>();
		jcbbEating.setBounds(3 * lblHeader.getWidth() / 4 - 16, lblHeader.getHeight() + 50 - 25 + 20, 200, 28);
		jcbbEating.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		jcbbEating.setBackground(SEARCH_COLOR);
		jcbbEating.addItem("");
		childPanel.add(jcbbEating);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5 * PADDING - 80, 130, parentPanel.getWidth() - 10 * PADDING + 150, 530);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		childPanel.add(scrollPane);

		scrollPane.setViewportView(foodTable);
		foodTable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		foodTable.setRowHeight(30);

		String[] columns = { "STT", "Ngày ăn", "Món chính", "Món phụ" };
		boolean isEditable[] = { false, false, false, false };
		dataFood = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return isEditable[column];
			}
		};
		dataFood.setColumnIdentifiers(columns);

		viewComboboxEating();
		viewFood(1);
		foodTable.setModel(dataFood);

		TableColumnModel columnModel = foodTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(10);
		columnModel.getColumn(1).setPreferredWidth(10);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(100);

		foodTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JTableHeader header = foodTable.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 20));
		header.setBackground(TABLEHEADER_COLOR);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		foodTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		foodTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		foodTable.setGridColor(Color.BLACK);
	}

	public void monitorAbsence(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		childPanel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);
		panel_right.add(childPanel, "monitorAbsence");

		tableStudents = new MyTable();

		JLabel lblHeader = new JLabel("ĐIỂM DANH HỌC SINH");
		lblHeader.setForeground(Color.BLACK);
		lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHeader.setBounds(0, PADDING + 10, parentPanel.getWidth(), 45);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		childPanel.add(lblHeader);

		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String currentDateTime = dateFormat.format(currentDate);
		String[] arrOfStr = currentDateTime.split("/");
		LocalDate day = LocalDate.of(Integer.parseInt(arrOfStr[2]), Integer.parseInt(arrOfStr[1]),
				Integer.parseInt(arrOfStr[0]));
		absenceDay = Date.valueOf(day);

		JLabel lblDateAndTime = new JLabel("Ngày " + currentDateTime);
		lblDateAndTime.setForeground(Color.BLACK);
		lblDateAndTime.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDateAndTime.setBounds(0, lblHeader.getHeight() + 35, parentPanel.getWidth(), 45);
		lblDateAndTime.setHorizontalAlignment(SwingConstants.CENTER);
		childPanel.add(lblDateAndTime);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(4 * PADDING, 127, parentPanel.getWidth() - 8 * PADDING, 456);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		childPanel.add(scrollPane);

		scrollPane.setViewportView(tableStudents);
		tableStudents.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tableStudents.setRowHeight(30);

		String[] columns = { "Số thứ tự", "Mã học sinh", "Họ và tên", "Vắng (1)/Có mặt (0)" };
		boolean isEditable[] = { false, false, false, true };
		dataStudent2 = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return isEditable[column];
			}
		};
		dataStudent2.setColumnIdentifiers(columns);

		viewStudent("monitorAbsence");

		tableStudents.setModel(dataStudent2);

		JLabel lbl = new JLabel("Tổng:");
		lbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl.setBounds(700, 600, 60, 32);
		childPanel.add(lbl);

		JLabel lblAmount = new JLabel();
		lblAmount.setForeground(Color.RED);
		lblAmount.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAmount.setBounds(760, 600, 103, 32);
		lblAmount.setText(tc.getAmountStudentOfBoardingClass_id(teacher.getBoardingClass_id()) + " học sinh");
		childPanel.add(lblAmount);

		TableColumnModel columnModel = tableStudents.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(150);

		JTableHeader header = tableStudents.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 20));
		header.setBackground(TABLEHEADER_COLOR);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tableStudents.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableStudents.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableStudents.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tableStudents.setGridColor(Color.BLACK);

		buttonSaveAbsence.setFont(new Font("Times New Roman", Font.BOLD, 17));
		buttonSaveAbsence.setText("Lưu thay đổi");
		buttonSaveAbsence.setRadius(20);
		buttonSaveAbsence.setBounds(15 * PADDING - 330, 650, parentPanel.getWidth() - 30 * PADDING, 40);
		buttonSaveAbsence.setFocusPainted(false);
		buttonSaveAbsence.setColorClick(new Color(117, 81, 6));
		buttonSaveAbsence.setColor(BUTTON_COLOR);
		buttonSaveAbsence.setColorOver(BUTTON_OVER_COLOR);
		buttonSaveAbsence.setForeground(BUTTON_FG_COLOR);
		buttonSaveAbsence.setBorderColor(Color.black);

		buttonPhysical.setFont(new Font("Times New Roman", Font.BOLD, 17));
		buttonPhysical.setText("Cập nhật thể trạng học sinh");
		buttonPhysical.setRadius(20);
		buttonPhysical.setBounds(15 * PADDING + 200, 650, parentPanel.getWidth() - 30 * PADDING + 130, 40);
		buttonPhysical.setFocusPainted(false);
		buttonPhysical.setColorClick(new Color(117, 81, 6));
		buttonPhysical.setColor(BUTTON_COLOR);
		buttonPhysical.setColorOver(BUTTON_OVER_COLOR);
		buttonPhysical.setForeground(BUTTON_FG_COLOR);
		buttonPhysical.setBorderColor(Color.black);

		childPanel.add(buttonSaveAbsence);
		childPanel.add(buttonPhysical);
	}

	public void viewStudentPhysical() {
		List<Student> listStudent = tc.getListStudentOfBoardingClass_id(teacher.getBoardingClass_id());
		for (int i = 0; i < listStudent.size(); i++) {
			dataStudent3.addRow(new Object[] { i + 1, listStudent.get(i).getStudent_id(), listStudent.get(i).getName(),
					listStudent.get(i).getHeight(), listStudent.get(i).getWeight() });
		}
	}

	public void monitorPhysical(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		childPanel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);
		parentPanel.add(childPanel, "monitorPhysical");

		tableStudents = new MyTable();

		JLabel lblHeader = new JLabel("CẬP NHẬT THỂ TRẠNG HỌC SINH");
		lblHeader.setForeground(Color.BLACK);
		lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblHeader.setBounds(0, PADDING + 20, parentPanel.getWidth(), 45);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		childPanel.add(lblHeader);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(4 * PADDING - 75, 127, parentPanel.getWidth() - 8 * PADDING + 150, 456);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		childPanel.add(scrollPane);

		scrollPane.setViewportView(tableStudents);
		tableStudents.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tableStudents.setRowHeight(30);

		dataStudent3 = new DefaultTableModel();
		Object[] columns = { "STT", "Mã HS", "Họ và tên", "Chiều cao (m)", "Cân nặng (kg)", "Cập nhật chiều cao",
				"Cập nhật cân nặng" };
		dataStudent3.setColumnIdentifiers(columns);

		viewStudentPhysical();
		tableStudents.setModel(dataStudent3);

		JLabel lbl = new JLabel("Tổng:");
		lbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl.setBounds(700, 600, 60, 32);
		childPanel.add(lbl);

		JLabel lblAmount = new JLabel();
		lblAmount.setForeground(Color.RED);
		lblAmount.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAmount.setBounds(760, 600, 103, 32);
		lblAmount.setText(tc.getAmountStudentOfBoardingClass_id(teacher.getBoardingClass_id()) + " học sinh");
		childPanel.add(lblAmount);

		TableColumnModel columnModel = tableStudents.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(5);
		columnModel.getColumn(1).setPreferredWidth(5);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(30);
		columnModel.getColumn(4).setPreferredWidth(30);

		JTableHeader header = tableStudents.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 17));
		header.setBackground(TABLEHEADER_COLOR);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tableStudents.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableStudents.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableStudents.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tableStudents.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tableStudents.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		tableStudents.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

		tableStudents.setGridColor(Color.BLACK);

		buttonSavePhysical.setFont(new Font("Times New Roman", Font.BOLD, 17));
		buttonSavePhysical.setText("Lưu thay đổi");
		buttonSavePhysical.setRadius(20);
		buttonSavePhysical.setBounds(15 * PADDING - 330, 650, parentPanel.getWidth() - 30 * PADDING, 40);
		buttonSavePhysical.setFocusPainted(false);
		buttonSavePhysical.setColorClick(new Color(117, 81, 6));
		buttonSavePhysical.setColor(BUTTON_COLOR);
		buttonSavePhysical.setColorOver(BUTTON_OVER_COLOR);
		buttonSavePhysical.setForeground(BUTTON_FG_COLOR);
		buttonSavePhysical.setBorderColor(Color.black);

		buttonAbsence.setFont(new Font("Times New Roman", Font.BOLD, 17));
		buttonAbsence.setText("Điểm danh học sinh");
		buttonAbsence.setRadius(20);
		buttonAbsence.setBounds(15 * PADDING + 200, 650, parentPanel.getWidth() - 30 * PADDING + 130, 40);
		buttonAbsence.setFocusPainted(false);
		buttonAbsence.setColorClick(new Color(117, 81, 6));
		buttonAbsence.setColor(BUTTON_COLOR);
		buttonAbsence.setColorOver(BUTTON_OVER_COLOR);
		buttonAbsence.setForeground(BUTTON_FG_COLOR);
		buttonAbsence.setBorderColor(Color.black);

		childPanel.add(buttonAbsence);
		childPanel.add(buttonSavePhysical);
	}

	public String getUserName() {
		return this.teacher.getPhoneNumber();
	}
}