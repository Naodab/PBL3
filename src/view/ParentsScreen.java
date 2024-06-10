package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.CardLayout;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ParentsController;
import controller.TableTeacherCTL;
import custom.MyButton;
import custom.MyTable;
import custom.MyTextField;
import custom.NonEditableTableModel;
import custom.PanelMenu;
import custom.PanelRound;
import custom.TableActionTcCellEditor;
import custom.TableActionTcCellRender;
import model.Absence;
import model.BoardingClass;
import model.BoardingFee;
import model.CBBItem;
import model.EatingHistory;
import model.Invoice;
import model.Parents;
import model.Student;
import model.Teacher;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

import javax.swing.JRadioButton;

public class ParentsScreen extends JFrame {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	private static final int HORIZONTAL_SCALE = 5;
	private static final int VERTICAL_SCALE = 12;
	private static final int GAP_MENU = 30;
	private static final int PADDING = 30;
	private static final int HEIGHT_BUTTON = 60;
	private static final int X_BUTTON = 0;
	private static final int Y_BUTTON = 250;

	private static final Color RIGHT_COLOR = new Color(242, 224, 191);
	private static final Color LEFT_COLOR = new Color(209, 174, 191);
	private static final Color SEARCH_COLOR = new Color(244, 203, 198);
	private static final Color TABLEHEADER_COLOR = new Color(184, 207, 238);
	private static final Color BUTTON_FG_COLOR = Color.white;
	private static final Color FOREGROUND = new Color(0, 45, 97);
	private static final Color BUTTON_COLOR = new Color(0, 45, 97);
	private static final Color BUTTON_OVER_COLOR = new Color(24, 89, 158);
	private static final Color BUTTON_CLICK_COLOR = new Color(117, 81, 6);

	private static final Font CONTROL_FONT = new Font("Times New Roman", Font.BOLD, 20);
	private static final Font ROW_TABLE_FONT = new Font("Times New Roman", Font.PLAIN, 18);
	private static final Font TEXTFIELD_FONT = new Font("Times New Roman", Font.BOLD, 20);
	private static final Font COLUMN_TABLE_FONT = new Font("Times New Roman", Font.BOLD, 15);
	private static final Font LABEL_CHILD_PANEL = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public CardLayout card_right;
	private MyTextField textFiel_HoTenPH;
	public PanelRound panel_right;

	private Parents parents = new Parents();
	public Absence absence = new Absence();
	public Student student = new Student();
	public BoardingClass boardingclass;
	private JTextField textField_DiaChiPH;
	private JTextField textField_MaPhuHuynh;
	private JTextField textField_SDT_PH;
	private JTextField textField_Email_PH;
	public MyButton button1;
	public MyButton button2;
	public MyButton button3;
	public MyButton button4;
	public MyButton logOut;
	public MyButton changePw;
	private MyTable table2;
	private PanelRound panelInvoice;
	public JComboBox<CBBItem> comboBoxStudent;
	private PanelMenu[] panelMenus;
	private ParentsController parentsController;
	private ButtonGroup btnSex;

	public JComboBox<CBBItem> comboBox;
	public PanelRound panelEatingHistory;
	private MyTextField tfYear;
	private MyTextField tfMonth;
	private MyTextField tfDay;
	public MyTextField textTeacher;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JLabel anhthe;
	public List<Integer> teacher_ids;
	public MyButton btnTTCTGV;
	public MyButton btnTTCTHS;
	private JLabel lblLchSNgy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParentsScreen frame = new ParentsScreen(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public ParentsScreen(int parents_id) {
		parentsController = new ParentsController(this);
		parents = parentsController.selectParentsById(parents_id);
		initComponents();
	}

	public ParentsScreen() {
		parentsController = new ParentsController(this);
		parents.setParents_id(1);
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
		teacher_ids = new ArrayList<Integer>();

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
		logOut.setFont(new Font("Times New Roman", Font.BOLD, 15));
		logOut.setColorClick(new Color(117, 81, 6));
		logOut.setColor(BUTTON_COLOR);
		logOut.setColorOver(BUTTON_OVER_COLOR);
		logOut.setForeground(BUTTON_FG_COLOR);
		logOut.setText("Đăng xuất");
		logOut.setFocusPainted(false);
		logOut.setBorderColor(Color.black);
		logOut.setRadius(20);
		logOut.setBounds(PADDING, controlBar.getHeight() - 2 * PADDING, controlBar.getWidth() - 2 * PADDING, PADDING);
		logOut.addActionListener(parentsController);
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
		changePw.addActionListener(parentsController);
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
		button1.setFont(CONTROL_FONT);
		button1.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		button1.setText("Thông tin cá nhân        ");
		button1.setColor(LEFT_COLOR);
		button1.setOpaque(true);
		button1.setFocusPainted(false);
		button1.setColorOver(new Color(224, 195, 210));
		button1.setBounds(X_BUTTON + 2, Y_BUTTON, controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON);
		button1.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(ParentsScreen.class.getResource("img/icon/reading.png"))
						.getScaledInstance(HEIGHT_BUTTON - 10, HEIGHT_BUTTON - 10, Image.SCALE_SMOOTH)));
		button1.addActionListener(parentsController);
		controlBar.add(button1);

		button2 = new MyButton();
		button2.setFont(CONTROL_FONT);
		button2.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button2.setText("Lịch sử ngày ăn           ");
		button2.setColor(LEFT_COLOR);
		button2.setOpaque(true);
		button2.setFocusPainted(false);
		button2.setColorOver(new Color(224, 195, 210));
		button2.setBounds(X_BUTTON + 2, Y_BUTTON + (HEIGHT_BUTTON - 1), controlBar.getWidth() - 2 * X_BUTTON - 4,
				HEIGHT_BUTTON);
		button2.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(ParentsScreen.class.getResource("img/icon/menu.png"))
						.getScaledInstance(HEIGHT_BUTTON - 10, HEIGHT_BUTTON - 10, Image.SCALE_SMOOTH)));
		button2.addActionListener(parentsController);
		controlBar.add(button2);

		button3 = new MyButton();
		button3.setFont(CONTROL_FONT);
		button3.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button3.setText("Lịch sử nộp tiền          ");
		button3.setColor(LEFT_COLOR);
		button3.setOpaque(true);
		button3.setFocusPainted(false);
		button3.setColorOver(new Color(224, 195, 210));
		button3.setBounds(X_BUTTON + 2, Y_BUTTON + 2 * (HEIGHT_BUTTON - 1), controlBar.getWidth() - 2 * X_BUTTON - 4,
				HEIGHT_BUTTON);
		button3.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(ParentsScreen.class.getResource("img/icon/class.png"))
						.getScaledInstance(HEIGHT_BUTTON - 10, HEIGHT_BUTTON - 10, Image.SCALE_SMOOTH)));
		button3.addActionListener(parentsController);
		controlBar.add(button3);

		panel_right = new PanelRound();
		panel_right.setBounds(controlBar.getX() + controlBar.getWidth() + GAP_MENU, controlBar.getY(),
				WIDTH / HORIZONTAL_SCALE * (HORIZONTAL_SCALE - 1) - HEIGHT / VERTICAL_SCALE - GAP_MENU,
				controlBar.getHeight());
		panel_right.setRadius(40);
		panel_right.setBorderColor(new Color(250, 250, 250));
		panel.add(panel_right);
		panel_right.setLayout(new CardLayout(0, 0));
		card_right = (CardLayout) panel_right.getLayout();

		PanelRound panelTTCN = new PanelRound();
		thongtincanhan(panel_right, panelTTCN);

		panelInvoice = new PanelRound();
		quanlyhoadon(panel_right, panelInvoice);

		JLabel lblLchSng = new JLabel("LỊCH SỬ ĐÓNG HỌC PHÍ");
		lblLchSng.setHorizontalAlignment(SwingConstants.CENTER);
		lblLchSng.setForeground(FOREGROUND);
		lblLchSng.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblLchSng.setBounds(10, 24, 1004, 42);
		panelInvoice.add(lblLchSng);

		panelEatingHistory = new PanelRound();
		quanlyngayan(panel_right, panelEatingHistory);

		JLabel lblNewLabel = new JLabel("Thời gian :");
		lblNewLabel.setFont(LABEL_CHILD_PANEL);
		lblNewLabel.setBounds(266, 79, 133, 32);
		panelEatingHistory.add(lblNewLabel);
		
		lblLchSNgy = new JLabel("LỊCH SỬ NGÀY ĂN");
		lblLchSNgy.setHorizontalAlignment(SwingConstants.CENTER);
		lblLchSNgy.setForeground(FOREGROUND);
		lblLchSNgy.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblLchSNgy.setBounds(10, 35, 1004, 42);
		panelEatingHistory.add(lblLchSNgy);

		JLabel background = new JLabel();
		background.setBounds(0, 0, WIDTH, HEIGHT);
		background.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(ParentsScreen.class.getResource("img/bk.jpg"))
						.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH)));

		contentPane.add(background);
		this.setVisible(true);
	}

	public void initInfoParents() {
		textFiel_HoTenPH.setText("" + parents.getName());
		textField_DiaChiPH.setText("" + parents.getAddress());
		textField_SDT_PH.setText("" + parents.getPhoneNumber());
		textField_MaPhuHuynh.setText("" + parents.getParents_id());
		textField_Email_PH.setText("" + parents.getEmail());
		if (!parents.getSex()) {
			rdbtnFemale.setSelected(true);
			rdbtnMale.setEnabled(false);
		} else {
			rdbtnMale.setSelected(true);
			rdbtnFemale.setEnabled(false);
		}
		for (int i = 0; i < parents.getStudent_id().size(); i++) {
			student = parentsController.selectStudentById(parents.getStudent_id().get(i));
			comboBoxStudent.addItem(new CBBItem(student.getStudent_id(), student.getName()));
			int teacher_id = parentsController.selectTeacherIdByBoardingClassId(student.getBoardingClass_id());
			teacher_ids.add(teacher_id);
		}
		LocalDate date = parents.getDateOfBirth().toLocalDate();
		tfDay.setText(date.getDayOfMonth() + "");
		tfMonth.setText(date.getMonthValue() + "");
		tfYear.setText(date.getYear() + "");
		String path = System.getProperty("user.dir") + "\\avatar\\Parents\\" + parents.getParents_id() + ".jpg";
		File ava = new File(path);
		if (ava.exists()) {
			anhthe.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(path)
					.getScaledInstance(anhthe.getWidth(), anhthe.getHeight(), Image.SCALE_SMOOTH)));
		}
		if (teacher_ids.size() > 0) {
			int tc_id = teacher_ids.get(0);
			Teacher tc = parentsController.selectTeacherById(tc_id);
			textTeacher.setText(tc.getName());
		}
	}

	public void thongtincanhan(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);
		JLabel lblThngTinC = new JLabel("THÔNG TIN CÁ NHÂN ");
		lblThngTinC.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngTinC.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblThngTinC.setBounds(10, 33, 1004, 42);
		lblThngTinC.setForeground(FOREGROUND);
		childPanel.add(lblThngTinC);

		PanelRound panel_1 = new PanelRound();
		panel_1.setBackground(Color.PINK);
		panel_1.setRadius(20);
		panel_1.setBounds(128, 342, 772, 240);
		panel_1.setBground(SEARCH_COLOR);
		panel_1.setBorderWidth(2);
		panel_1.setBorderColor(new Color(0, 0, 0));
		childPanel.add(panel_1);
		panel_1.setLayout(null);

		PanelRound panel_2 = new PanelRound();
		panel_2.setRadius(20);
		panel_2.setBounds(296, 142, 604, 175);
		panel_2.setBground(SEARCH_COLOR);
		panel_2.setBorderWidth(2);
		panel_2.setBorderColor(Color.BLACK);
		childPanel.add(panel_2);
		panel_2.setLayout(null);

		JLabel Label_HoTenPH = new JLabel("Họ và tên  :");
		Label_HoTenPH.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_HoTenPH.setBounds(20, 21, 109, 26);
		panel_2.add(Label_HoTenPH);
		Label_HoTenPH.setFont(LABEL_CHILD_PANEL);

		JLabel Label_NgaySinhPH = new JLabel("Ngày sinh  :");
		Label_NgaySinhPH.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_NgaySinhPH.setBounds(20, 75, 109, 26);
		panel_2.add(Label_NgaySinhPH);
		Label_NgaySinhPH.setFont(LABEL_CHILD_PANEL);

		JLabel Label_GioiTinhPH = new JLabel("Giới tính   :");
		Label_GioiTinhPH.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_GioiTinhPH.setBounds(20, 125, 109, 26);
		panel_2.add(Label_GioiTinhPH);
		Label_GioiTinhPH.setFont(LABEL_CHILD_PANEL);

		JLabel Label_DiaChiPH = new JLabel("Địa chỉ    :");
		Label_DiaChiPH.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_DiaChiPH.setBounds(70, 37, 148, 26);
		panel_1.add(Label_DiaChiPH);
		Label_DiaChiPH.setFont(LABEL_CHILD_PANEL);

		JLabel Label_MaPhuHuynh = new JLabel("Mã phụ huynh :");
		Label_MaPhuHuynh.setBounds(315, 75, 142, 26);
		panel_2.add(Label_MaPhuHuynh);
		Label_MaPhuHuynh.setFont(LABEL_CHILD_PANEL);

		JLabel Label_SDT_PH = new JLabel("Số điện thoại :");
		Label_SDT_PH.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_SDT_PH.setBounds(70, 73, 148, 26);
		panel_1.add(Label_SDT_PH);
		Label_SDT_PH.setFont(LABEL_CHILD_PANEL);

		JLabel Label_Gmail_PH = new JLabel(" Email :");
		Label_Gmail_PH.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_Gmail_PH.setBounds(70, 109, 148, 26);
		panel_1.add(Label_Gmail_PH);
		Label_Gmail_PH.setFont(LABEL_CHILD_PANEL);

		JLabel Label_TTHS = new JLabel("Thông tin học sinh :");
		Label_TTHS.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_TTHS.setFont(LABEL_CHILD_PANEL);
		Label_TTHS.setBounds(48, 147, 170, 26);
		panel_1.add(Label_TTHS);

		JLabel Label_TTGV = new JLabel("Thông tin giáo viên :");
		Label_TTGV.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_TTGV.setFont(LABEL_CHILD_PANEL);
		Label_TTGV.setBounds(22, 183, 196, 26);
		panel_1.add(Label_TTGV);

		textField_DiaChiPH = new MyTextField();
		textField_DiaChiPH.setBounds(241, 37, 469, 26);
		textField_DiaChiPH.setBackground(SEARCH_COLOR);
		panel_1.add(textField_DiaChiPH);
		textField_DiaChiPH.setFont(TEXTFIELD_FONT);
		textField_DiaChiPH.setFocusable(false);
		textField_DiaChiPH.setFocusTraversalKeysEnabled(false);
		textField_DiaChiPH.setEditable(false);
		textField_DiaChiPH.setColumns(10);

		textField_SDT_PH = new MyTextField();
		textField_SDT_PH.setBounds(241, 73, 469, 26);
		textField_SDT_PH.setBackground(SEARCH_COLOR);
		panel_1.add(textField_SDT_PH);
		textField_SDT_PH.setFont(TEXTFIELD_FONT);
		textField_SDT_PH.setFocusable(false);
		textField_SDT_PH.setFocusTraversalKeysEnabled(false);
		textField_SDT_PH.setEditable(false);
		textField_SDT_PH.setColumns(10);

		textField_Email_PH = new MyTextField();
		textField_Email_PH.setBounds(241, 109, 469, 26);
		textField_Email_PH.setBackground(SEARCH_COLOR);
		panel_1.add(textField_Email_PH);
		textField_Email_PH.setFont(TEXTFIELD_FONT);
		textField_Email_PH.setFocusable(false);
		textField_Email_PH.setFocusTraversalKeysEnabled(false);
		textField_Email_PH.setEditable(false);
		textField_Email_PH.setColumns(10);

		textField_MaPhuHuynh = new MyTextField();
		textField_MaPhuHuynh.setHorizontalAlignment(SwingConstants.CENTER);
		textField_MaPhuHuynh.setBounds(467, 75, 67, 26);
		textField_MaPhuHuynh.setBackground(SEARCH_COLOR);
		panel_2.add(textField_MaPhuHuynh);
		textField_MaPhuHuynh.setFont(TEXTFIELD_FONT);
		textField_MaPhuHuynh.setFocusable(false);
		textField_MaPhuHuynh.setFocusTraversalKeysEnabled(false);
		textField_MaPhuHuynh.setEditable(false);
		textField_MaPhuHuynh.setColumns(10);

		textFiel_HoTenPH = new MyTextField();
		textFiel_HoTenPH.setHorizontalAlignment(SwingConstants.CENTER);
		textFiel_HoTenPH.setBounds(139, 21, 395, 26);
		textFiel_HoTenPH.setBackground(SEARCH_COLOR);
		panel_2.add(textFiel_HoTenPH);
		textFiel_HoTenPH.setFocusTraversalKeysEnabled(false);
		textFiel_HoTenPH.setFocusable(false);
		textFiel_HoTenPH.setEditable(false);
		textFiel_HoTenPH.setFont(TEXTFIELD_FONT);
		textFiel_HoTenPH.setColumns(10);

		anhthe = new JLabel();
		anhthe.setBounds(128, 142, 150, 175);
		childPanel.add(anhthe);

		tfDay = new MyTextField();
		tfDay.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDay.setLineColor(Color.black);
		tfDay.setForeground(Color.black);
		tfDay.setFont(TEXTFIELD_FONT);
		tfDay.setBackground(SEARCH_COLOR);
		tfDay.setEditable(false);
		tfDay.setBounds(139, 75, 25, 28);
		panel_2.add(tfDay);

		JLabel seperator = new JLabel("/");
		seperator.setHorizontalAlignment(SwingConstants.CENTER);
		seperator.setForeground(Color.black);
		seperator.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 41));
		seperator.setBounds(159, 75, 31, 40);
		panel_2.add(seperator);

		tfMonth = new MyTextField();
		tfMonth.setHorizontalAlignment(SwingConstants.RIGHT);
		tfMonth.setLineColor(Color.black);
		tfMonth.setForeground(Color.black);
		tfMonth.setFont(TEXTFIELD_FONT);
		tfMonth.setBackground(SEARCH_COLOR);
		tfMonth.setEditable(false);
		tfMonth.setBounds(186, 76, 25, 28);
		panel_2.add(tfMonth);

		JLabel seperator_1 = new JLabel("/");
		seperator_1.setHorizontalAlignment(SwingConstants.CENTER);
		seperator_1.setForeground(Color.black);
		seperator_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 41));
		seperator_1.setBounds(206, 75, 31, 40);
		panel_2.add(seperator_1);

		tfYear = new MyTextField();
		tfYear.setHorizontalAlignment(SwingConstants.RIGHT);
		tfYear.setLineColor(Color.black);
		tfYear.setForeground(Color.black);
		tfYear.setFont(TEXTFIELD_FONT);
		tfYear.setBackground(SEARCH_COLOR);
		tfYear.setEditable(false);
		tfYear.setBounds(235, 76, 50, 28);
		panel_2.add(tfYear);

		btnSex = new ButtonGroup();
		rdbtnMale = new JRadioButton("Nam");
		rdbtnMale.setFont(TEXTFIELD_FONT);
		rdbtnMale.setFocusPainted(false);
		rdbtnMale.setBackground(SEARCH_COLOR);
		rdbtnMale.setBounds(139, 125, 103, 26);
		btnSex.add(rdbtnMale);
		panel_2.add(rdbtnMale);

		rdbtnFemale = new JRadioButton("Nữ");
		rdbtnFemale.setFont(TEXTFIELD_FONT);
		rdbtnFemale.setFocusPainted(false);
		rdbtnFemale.setBackground(SEARCH_COLOR);
		rdbtnFemale.setBounds(246, 125, 103, 26);
		btnSex.add(rdbtnFemale);
		panel_2.add(rdbtnFemale);

		comboBoxStudent = new JComboBox<CBBItem>();
		comboBoxStudent.setBounds(236, 145, 335, 31);
		comboBoxStudent.setFont(LABEL_CHILD_PANEL);
		comboBoxStudent.addActionListener(parentsController);
		panel_1.add(comboBoxStudent);

		btnTTCTHS = new MyButton();
		btnTTCTHS.setText("Chi tiết");
		btnTTCTHS.setRadius(20);
		btnTTCTHS.setForeground(Color.WHITE);
		btnTTCTHS.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnTTCTHS.setFocusPainted(false);
		btnTTCTHS.setColorOver(BUTTON_OVER_COLOR);
		btnTTCTHS.setColorClick(BUTTON_CLICK_COLOR);
		btnTTCTHS.setColor(BUTTON_COLOR);
		btnTTCTHS.setBorderColor(Color.BLACK);
		btnTTCTHS.addActionListener(parentsController);
		btnTTCTHS.setBounds(581, 146, 129, 30);
		panel_1.add(btnTTCTHS);

		textTeacher = new MyTextField();
		textTeacher.setFont(TEXTFIELD_FONT);
		textTeacher.setFocusable(false);
		textTeacher.setFocusTraversalKeysEnabled(false);
		textTeacher.setEditable(false);
		textTeacher.setBackground(SEARCH_COLOR);
		textTeacher.setBounds(241, 183, 330, 26);
		panel_1.add(textTeacher);

		btnTTCTGV = new MyButton();
		btnTTCTGV.setText("Chi tiết");
		btnTTCTGV.setRadius(20);
		btnTTCTGV.setForeground(Color.WHITE);
		btnTTCTGV.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnTTCTGV.setFocusPainted(false);
		btnTTCTGV.setColorOver(BUTTON_OVER_COLOR);
		btnTTCTGV.setColorClick(BUTTON_CLICK_COLOR);
		btnTTCTGV.setColor(BUTTON_COLOR);
		btnTTCTGV.setBorderColor(Color.BLACK);
		btnTTCTGV.setBounds(581, 179, 129, 30);
		btnTTCTGV.addActionListener(parentsController);
		panel_1.add(btnTTCTGV);

		initInfoParents();
		parentPanel.add(childPanel, "detailParents");
	}
	
	public void setMenuEatingHistory(List<EatingHistory> ehises) {
		int count = ehises.size();
		for (int i = 0; i < count; i++) {
			EatingHistory ehis = ehises.get(i);
			panelMenus[i].setMenu(ehis.getMenu_id());
 		}
	}
	
	public void setCBBEhis(List<CBBItem> cbbs) {
		for (CBBItem eht : cbbs) {
			comboBox.addItem(eht);
		}
		comboBox.setSelectedIndex(comboBox.getItemCount() - 1);
	}

	public void quanlyngayan(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		comboBox = new JComboBox<CBBItem>();
		comboBox.setFont(ROW_TABLE_FONT);
		comboBox.setBounds(409, 77, 299, 34);
		childPanel.add(comboBox);

		panelMenus = new PanelMenu[5];

		panelMenus[0] = new PanelMenu();
		panelMenus[0].setTitle("Thứ 2");
		panelMenus[0].setBounds(44, 123, 230, 225);
		childPanel.add(panelMenus[0]);

		panelMenus[1] = new PanelMenu();
		panelMenus[1].setTitle("Thứ 3");
		panelMenus[1].setBounds(376, 123, 230, 225);
		childPanel.add(panelMenus[1]);

		panelMenus[2] = new PanelMenu();
		panelMenus[2].setTitle("Thứ 4");
		panelMenus[2].setBounds(713, 123, 230, 225);
		childPanel.add(panelMenus[2]);

		panelMenus[3] = new PanelMenu();
		panelMenus[3].setTitle("Thứ 5");
		panelMenus[3].setBounds(210, 416, 230, 225);
		childPanel.add(panelMenus[3]);

		panelMenus[4] = new PanelMenu();
		panelMenus[4].setTitle("Thứ 6");
		panelMenus[4].setBounds(550, 416, 230, 225);
		childPanel.add(panelMenus[4]);

		comboBox.addActionListener(parentsController);
		parentPanel.add(childPanel, "eatingHistory");
	}

	public void quanlyhoadon(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		boolean[] editableColumnsListInvoice = { false, false, false, false, false, false, true };
		NonEditableTableModel modelListTeacher = new NonEditableTableModel(new Object[][] {},
				new String[] { "STT", "Mã hóa đơn", "Học sinh", "Đợt thu tiền", "Trạng thái", "Chi phí nộp", "" },
				editableColumnsListInvoice);

		table2 = new MyTable();
		table2.setModel(modelListTeacher);
		table2.setSelectionBackground(TABLEHEADER_COLOR);
		table2.setGridColor(new Color(0, 0, 0));
		table2.setRowHeight(30);
		table2.setFont(ROW_TABLE_FONT);
		table2.getColumnModel().getColumn(0).setPreferredWidth(30);
		table2.getColumnModel().getColumn(1).setPreferredWidth(75);
		table2.getColumnModel().getColumn(2).setPreferredWidth(150);
		table2.getColumnModel().getColumn(3).setPreferredWidth(150);
		table2.getColumnModel().getColumn(4).setPreferredWidth(70);
		table2.getColumnModel().getColumn(5).setPreferredWidth(70);

		table2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		TableTeacherCTL ctl = new TableTeacherCTL("Invoice", (DefaultTableModel) table2.getModel());
		table2.getColumnModel().getColumn(6).setCellRenderer(new TableActionTcCellRender());
		table2.getColumnModel().getColumn(6).setCellEditor(new TableActionTcCellEditor(ctl));

		JTableHeader headerTeacher = table2.getTableHeader();
		headerTeacher.setFont(COLUMN_TABLE_FONT);
		headerTeacher.setLayout(null);
		headerTeacher.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerTeacher.setBackground(TABLEHEADER_COLOR);
		
		JScrollPane scrollPane_1 = new JScrollPane(table2);
		scrollPane_1.setBounds(29, 76, 964, 627);
		panelInvoice.add(scrollPane_1);

		DefaultTableModel model = (DefaultTableModel) table2.getModel();
		int count = 1;
		for (int i = 0; i < parents.getStudent_id().size(); i++) {
			student = parentsController.selectStudentById(parents.getStudent_id().get(i));
			String name = student.getName();
			List<Invoice> result = parentsController.getInvoiceByStudentId(student.getStudent_id());
			for (Invoice iv : result) {
				String status = "Chưa nộp";
				if (iv.getStatusPayment() == 2) {
					status = "Chưa in";
				} else if (iv.getStatusPayment() == 3) {
					status = "Đã in";
				}
				int bFee_id = iv.getBoardingFee_id();
				BoardingFee bfee = parentsController.selectBoardingFeeById(bFee_id);
				LocalDate date = bfee.getStart_day().toLocalDate();
				String boardingFee = "Tháng " + date.getMonthValue() + " năm " + date.getYear();
				Object[] row = {count++, iv.getInvoice_id(), name, boardingFee, status, iv.getMoney()};
				model.addRow(row);
			}
		}
		parentPanel.add(childPanel, "invoice1");
	}

	public void setCardRight(String name) {
		this.card_right.show(panel_right, name);
	}
	
	public String getUserName() {
		return parents.getPhoneNumber();
	}
}