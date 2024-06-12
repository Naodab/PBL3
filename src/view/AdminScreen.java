package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.AdminController;
import controller.TableController;
import controller.TableTeacherCTL;
import custom.MyButton;
import custom.MyTable;
import custom.MyTextField;
import custom.NonEditableTableModel;
import custom.PanelRound;
import custom.TableActionCellEditor;
import custom.TableActionCellRender;
import custom.TableActionTcCellEditor;
import custom.TableActionTcCellRender;
import model.BoardingClass;
import model.BoardingFee;
import model.CBBItem;
import model.EatingHistory;
import model.Food;
import model.Invoice;
import model.Menu;
import model.Parents;
import model.Student;
import model.Teacher;
import model.User;

public class AdminScreen extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	private static final int HORIZONTAL_SCALE = 5;
	private static final int VERTICAL_SCALE = 12;
	private static final int GAP_MENU = 30;
	private static final int PADDING = 30;
	private static final int HEIGHT_BUTTON_CONTROL = 45;
	private static final int X_BUTTON = 0;
	private static final int Y_BUTTON = 200;

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
	private static final Font COLUMN_TABLE_FONT = new Font("Times New Roman", Font.BOLD, 15);
	private static final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 22);
	private static final Font LABEL_CHILD_PANEL = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18);
	private static final Font SEPERATOR_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28);
	private static final Font ACCROSS_FONT = new Font("Times New Roman", Font.BOLD, 25);

	private JPanel contentPane;
	private CardLayout card_right;
	private PanelRound panel_right;

	private MyButton logOut;
	private MyButton button1;
	private MyButton button2;
	private MyButton button3;
	private MyButton button4;
	private MyButton button5;
	private MyButton button6;
	private MyButton button7;
	private MyButton button9;
	private MyButton button10;

	private JTextField tfsearchMenu;
	private JTextField tfsearchStudent;
	private JTextField tfsearchParents;
	private JTextField tfsearchTeacher;
	private JTextField tfsearchClass;
	private JTextField tfsearchFood;
	private JTextField tfsearchAccount;
	private JTextField tfsearchInvoice;
	private JTextField tfsearchEHis;
	public AdminController adminController;

	private TableController tableStudentController;
	private TableController tableParentsController;
	private TableController tableTeacherController;
	private TableController tableClassController;
	private TableController tableFoodController;
	private TableController tableAccountController;
	private TableController tableMenuController;

	private ButtonGroup grbtnSortStudent;
	private ButtonGroup grbtnSortParents;
	private ButtonGroup grbtnSortTeacher;
	private ButtonGroup grbtnSortClass;
	private ButtonGroup grbtnSortFood;
	private ButtonGroup grbtnSortAccount;

	private JComboBox<String> searchstudents;
	private JComboBox<String> searchParents;
	private JComboBox<String> searchTeacher;
	private JComboBox<String> searchClass;
	private JComboBox<String> searchFood;
	private JComboBox<String> searchAccount;
	private JComboBox<String> searchInvoice;
	private JComboBox<String> searchEHis;
	private JComboBox<String> searchMenus;

	private MyTable tableListStudent;
	private MyTable tableListParents;
	private MyTable tableListTeacher;
	private MyTable tableListClass;
	private MyTable tableListFood;
	private MyTable tableListAccount;
	private MyTable tableInvoice;
	private MyTable tableListEHis;
	private MyTable tableListMenu;

	private MyButton btnSeeMoreStd;
	private MyButton btnSeeMorePr;
	private MyButton btnSeeMoreTc;
	private MyButton btnSeeMoreCl;
	private MyButton btnSeeMoreFood;
	private MyButton btnSeeMoreAc;

	private MyButton btnSeeAllStd;
	private MyButton btnSeeAllPr;
	private MyButton btnSeeAllTc;
	private MyButton btnSeeAllCl;
	private MyButton btnSeeAllFood;
	private MyButton btnSeeAllAc;

	private JComboBox<String> comboSortStudent;
	private JComboBox<String> comboSortParents;
	private JComboBox<String> comboSortTeacher;
	private JComboBox<String> comboSortClass;
	private JComboBox<String> comboSortFood;
	private JComboBox<String> comboSortAccount;

	private MyButton btnSearchStudent;
	private MyButton btnSearchParents;
	private MyButton btnSearchTeacher;
	private MyButton btnSearchClass;
	private MyButton btnSearchFood;
	private MyButton btnSearchAccount;
	private MyButton btnSearchInvoice;
	private MyButton btnSearchMenu;

	private MyTextField tfBFee_id;
	private MyTextField tfTotalAMount;
	private MyTextField tfTotalPayed;
	private MyTextField tfStdPayed;
	private MyTextField tfStdTotal;
	private MyTextField tfInvoice;
	private MyTextField lblYearTo;
	private MyTextField tfMonthTo;
	private MyTextField tfDayTo;
	private MyTextField tfYearFrom;
	private MyTextField tfMonthFrom;
	private MyTextField tfDayFrom;
	private MyTextField tfAmount;

	private JLabel amountOfStd;
	private JLabel amountOfPr;
	private JLabel amountOfTc;
	private JLabel amountOfCl;
	private JLabel amountOfFood;
	private JLabel amountOfAc;

	private MyButton btnPrint;
	private MyButton btnAddBFee;
	private MyButton btnSeeAllIn;
	private MyButton btnSeeMoreInvoice;

	private MyButton btnCancelEHis;
	private MyButton btnSaveEHis;

	public JComboBox<CBBItem> comboBoxBFee;
	public JComboBox<CBBItem> comboBoxEHis;

	private boolean critical;
	private JLabel amountOfMenu;
	private MyButton btnSeeList;
	private MyButton changePw;
	private PanelRound panelEatingHistory;

	public AdminScreen() {
		adminController = new AdminController(this);
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

		panel_right = new PanelRound();
		panel_right.setBounds(controlBar.getX() + controlBar.getWidth() + GAP_MENU, controlBar.getY(),
				WIDTH / HORIZONTAL_SCALE * (HORIZONTAL_SCALE - 1) - HEIGHT / VERTICAL_SCALE - GAP_MENU,
				controlBar.getHeight());
		panel_right.setRadius(40);
		panel_right.setBorderColor(new Color(250, 250, 250));
		panel.add(panel_right);
		panel_right.setLayout(new CardLayout(0, 0));
		card_right = (CardLayout) panel_right.getLayout();

		PanelRound panelStudent = new PanelRound();
		manageStudent(panel_right, panelStudent);

		PanelRound panelParent = new PanelRound();
		manageParents(panel_right, panelParent);

		PanelRound panelTeacher = new PanelRound();
		manageTeachers(panel_right, panelTeacher);

		PanelRound panelClass = new PanelRound();
		manageClass(panel_right, panelClass);

		PanelRound panelMenu = new PanelRound();
		manageMenu(panel_right, panelMenu);

		PanelRound panelFood = new PanelRound();
		manageFood(panel_right, panelFood);

		panelEatingHistory = new PanelRound();
		manageEatingHistory(panel_right, panelEatingHistory);

		PanelRound panelBoardingFee = new PanelRound();
		manageBoardingFee(panel_right, panelBoardingFee);

		PanelRound panelAccount = new PanelRound();
		manageAccount(panel_right, panelAccount);

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
		logOut.addActionListener(adminController);
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
		changePw.addActionListener(adminController);
		controlBar.add(changePw);

		JLabel title1 = new JLabel("TRƯỜNG TIỂU HỌC");
		title1.setFont(TITLE_FONT);
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title1.setForeground(Color.BLACK);
		title1.setBounds(PADDING, PADDING, controlBar.getWidth() - 2 * PADDING, PADDING);
		controlBar.add(title1);

		JLabel title2 = new JLabel("SỐ I HÒA PHƯỚC");
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setForeground(title1.getForeground());
		title2.setFont(TITLE_FONT);
		title2.setBounds(PADDING, 2 * PADDING, controlBar.getWidth() - 2 * PADDING, PADDING);
		controlBar.add(title2);

		button1 = new MyButton();
		button1.setFont(CONTROL_FONT);
		button1.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		button1.setText("Quản lý học sinh        ");
		button1.setColor(LEFT_COLOR);
		button1.setOpaque(true);
		button1.setFocusPainted(false);
		button1.setColorOver(new Color(224, 195, 210));
		button1.setBounds(X_BUTTON + 2, Y_BUTTON, controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON_CONTROL);
		button1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(AdminScreen.class.getResource("img/icon/reading.png"))
				.getScaledInstance(HEIGHT_BUTTON_CONTROL - 10, HEIGHT_BUTTON_CONTROL - 10, Image.SCALE_SMOOTH)));
		button1.addActionListener(adminController);
		controlBar.add(button1);

		button2 = new MyButton();
		button2.setFont(CONTROL_FONT);
		button2.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button2.setText("Quản lý phụ huynh    ");
		button2.setColor(LEFT_COLOR);
		button2.setOpaque(true);
		button2.setFocusPainted(false);
		button2.setColorOver(new Color(224, 195, 210));
		button2.setBounds(X_BUTTON + 2, Y_BUTTON + (HEIGHT_BUTTON_CONTROL - 1),
				controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON_CONTROL);
		button2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(AdminScreen.class.getResource("img/icon/parents.png"))
				.getScaledInstance(HEIGHT_BUTTON_CONTROL - 10, HEIGHT_BUTTON_CONTROL - 10, Image.SCALE_SMOOTH)));
		button2.addActionListener(adminController);
		controlBar.add(button2);

		button3 = new MyButton();
		button3.setFont(CONTROL_FONT);
		button3.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button3.setText("Quản lý giáo viên       ");
		button3.setColor(LEFT_COLOR);
		button3.setOpaque(true);
		button3.setFocusPainted(false);
		button3.setColorOver(new Color(224, 195, 210));
		button3.setBounds(X_BUTTON + 2, Y_BUTTON + 2 * (HEIGHT_BUTTON_CONTROL - 1),
				controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON_CONTROL);
		button3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(AdminScreen.class.getResource("img/icon/teacher.png"))
				.getScaledInstance(HEIGHT_BUTTON_CONTROL - 10, HEIGHT_BUTTON_CONTROL - 10, Image.SCALE_SMOOTH)));
		button3.addActionListener(adminController);
		controlBar.add(button3);

		button4 = new MyButton();
		button4.setFont(CONTROL_FONT);
		button4.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button4.setText("Quản lý lớp bán trú   ");
		button4.setColor(LEFT_COLOR);
		button4.setOpaque(true);
		button4.setFocusPainted(false);
		button4.setColorOver(new Color(224, 195, 210));
		button4.setBounds(X_BUTTON + 2, Y_BUTTON + 3 * (HEIGHT_BUTTON_CONTROL - 1),
				controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON_CONTROL);
		button4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(AdminScreen.class.getResource("img/icon/class.png"))
				.getScaledInstance(HEIGHT_BUTTON_CONTROL - 10, HEIGHT_BUTTON_CONTROL - 10, Image.SCALE_SMOOTH)));
		button4.addActionListener(adminController);
		controlBar.add(button4);

		button5 = new MyButton();
		button5.setFont(CONTROL_FONT);
		button5.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button5.setText("Quản lý thực đơn       ");
		button5.setColor(LEFT_COLOR);
		button5.setOpaque(true);
		button5.setFocusPainted(false);
		button5.setColorOver(new Color(224, 195, 210));
		button5.setBounds(X_BUTTON + 2, Y_BUTTON + 4 * (HEIGHT_BUTTON_CONTROL - 1),
				controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON_CONTROL);
		button5.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(AdminScreen.class.getResource("img/icon/menu.png"))
				.getScaledInstance(HEIGHT_BUTTON_CONTROL - 10, HEIGHT_BUTTON_CONTROL - 10, Image.SCALE_SMOOTH)));
		button5.addActionListener(adminController);
		controlBar.add(button5);

		button10 = new MyButton();
		button10.setFont(CONTROL_FONT);
		button10.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button10.setText("Quản lý món ăn          ");
		button10.setColor(LEFT_COLOR);
		button10.setOpaque(true);
		button10.setFocusPainted(false);
		button10.setColorOver(new Color(224, 195, 210));
		button10.setBounds(X_BUTTON + 2, Y_BUTTON + 5 * (HEIGHT_BUTTON_CONTROL - 1),
				controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON_CONTROL);
		button10.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(AdminScreen.class.getResource("img/icon/food.png"))
				.getScaledInstance(HEIGHT_BUTTON_CONTROL - 10, HEIGHT_BUTTON_CONTROL - 10, Image.SCALE_SMOOTH)));
		button10.addActionListener(adminController);
		controlBar.add(button10);

		button6 = new MyButton();
		button6.setFont(CONTROL_FONT);
		button6.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button6.setText("Lịch sử ngày ăn          ");
		button6.setColor(LEFT_COLOR);
		button6.setOpaque(true);
		button6.setFocusPainted(false);
		button6.setColorOver(new Color(224, 195, 210));
		button6.setBounds(X_BUTTON + 2, Y_BUTTON + 6 * (HEIGHT_BUTTON_CONTROL - 1),
				controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON_CONTROL);
		button6.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(AdminScreen.class.getResource("img/icon/eat.png"))
				.getScaledInstance(HEIGHT_BUTTON_CONTROL - 10, HEIGHT_BUTTON_CONTROL - 10, Image.SCALE_SMOOTH)));
		button6.addActionListener(adminController);
		controlBar.add(button6);

		button7 = new MyButton();
		button7.setFont(CONTROL_FONT);
		button7.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button7.setText("Quản lý thu tiền          ");
		button7.setColor(LEFT_COLOR);
		button7.setOpaque(true);
		button7.setFocusPainted(false);
		button7.setColorOver(new Color(224, 195, 210));
		button7.setBounds(X_BUTTON + 2, Y_BUTTON + 7 * (HEIGHT_BUTTON_CONTROL - 1),
				controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON_CONTROL);
		button7.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(AdminScreen.class.getResource("img/icon/recieve.png"))
				.getScaledInstance(HEIGHT_BUTTON_CONTROL - 10, HEIGHT_BUTTON_CONTROL - 10, Image.SCALE_SMOOTH)));
		button7.addActionListener(adminController);
		controlBar.add(button7);

		button9 = new MyButton();
		button9.setFont(CONTROL_FONT);
		button9.setBorder(new MatteBorder(1, 0, 1, 0, Color.black));
		button9.setText("Quản lý tài khoản          ");
		button9.setColor(LEFT_COLOR);
		button9.setOpaque(true);
		button9.setFocusPainted(false);
		button9.setColorOver(new Color(224, 195, 210));
		button9.setBounds(X_BUTTON + 2, Y_BUTTON + 8 * (HEIGHT_BUTTON_CONTROL - 1),
				controlBar.getWidth() - 2 * X_BUTTON - 4, HEIGHT_BUTTON_CONTROL);
		button9.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(AdminScreen.class.getResource("img/icon/account.png"))
				.getScaledInstance(HEIGHT_BUTTON_CONTROL - 10, HEIGHT_BUTTON_CONTROL - 10, Image.SCALE_SMOOTH)));
		button9.addActionListener(adminController);
		controlBar.add(button9);

		JLabel background = new JLabel();
		background.setBounds(0, 0, WIDTH, HEIGHT);
		background.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("img/bk.jpg"))
						.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH)));
		contentPane.add(background);

		setVisible(true);
	}

	public void addRowsStudent(List<String> ids, boolean isDeletaAll) {
		if (isDeletaAll)
			deleteAllRowOf(tableListStudent);
		DefaultTableModel model = (DefaultTableModel) tableListStudent.getModel();
		int count = model.getRowCount() + 1;
		for (String id : ids) {
			Student std = adminController.getStudentById(Integer.parseInt(id));
			String sex = (std.getSex() ? "Nam" : "Nữ");
			String sM = (std.isSubMeal() ? "Có" : "Không");
			String parents = (std.getParents_id() == 0) ? "Không có" : std.getParents_id() + "";
			String dbc = (std.getBoardingClass_id() == 0) ? "Không có"
					: adminController.getNameBoardingClass(std.getBoardingClass_id());
			model.addRow(new Object[] { count++, std.getStudent_id(), std.getName(), sex, std.getDateOfBirth(),
					std.getAddress(), parents, dbc, sM });
		}
		amountOfStd.setText("Tổng cộng: " + adminController.getSizeOfStudent() + " học sinh");
	}

	public void manageStudent(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		JLabel lbTitle = new JLabel("QUẢN LÝ HỌC SINH");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbTitle.setBounds(PADDING, PADDING - 10, parentPanel.getWidth() - 2 * PADDING, 40);
		lbTitle.setForeground(FOREGROUND);
		childPanel.add(lbTitle);

		boolean[] editableColumnsListStudent = { false, false, false, false, false, false, false, false, false, true };
		NonEditableTableModel modelListStudent = new NonEditableTableModel(new Object[][] {}, new String[] { "STT",
				"Mã học sinh", "Họ và tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã PH", "LBT", "Ăn phụ", "" },
				editableColumnsListStudent);

		tableListStudent = new MyTable();
		tableListStudent.setSelectionBackground(TABLEHEADER_COLOR);
		tableListStudent.setGridColor(new Color(0, 0, 0));
		tableListStudent.setRowHeight(30);
		tableListStudent.setFont(ROW_TABLE_FONT);
		tableListStudent.setModel(modelListStudent);
		tableListStudent.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListStudent.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableListStudent.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableListStudent.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableListStudent.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableListStudent.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableListStudent.getColumnModel().getColumn(6).setPreferredWidth(60);
		tableListStudent.getColumnModel().getColumn(7).setPreferredWidth(60);
		tableListStudent.getColumnModel().getColumn(8).setPreferredWidth(50);

		tableListStudent.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableStudentController = new TableController(tableListStudent, this, "Student");
		tableListStudent.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
		tableListStudent.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(tableStudentController));

		JTableHeader headerStudent = tableListStudent.getTableHeader();
		headerStudent.setFont(COLUMN_TABLE_FONT);
		headerStudent.setLayout(null);
		headerStudent.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerStudent.setBackground(TABLEHEADER_COLOR);

		JScrollPane scrollStudent = new JScrollPane(tableListStudent);
		scrollStudent.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollStudent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollStudent.setBounds(30, 120, 964, 500);
		childPanel.add(scrollStudent);

		tfsearchStudent = new JTextField();
		tfsearchStudent.setBounds(640, 80, 200, 30);
		tfsearchStudent.setFont(ROW_TABLE_FONT);
		tfsearchStudent.setBackground(SEARCH_COLOR);
		tfsearchStudent.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		childPanel.add(tfsearchStudent);

		btnSearchStudent = new MyButton();
		btnSearchStudent.setFont(LABEL_CHILD_PANEL);
		btnSearchStudent.setText("Tìm kiếm");
		btnSearchStudent.setFocusPainted(false);
		btnSearchStudent.setColor(SEARCH_COLOR);
		btnSearchStudent.setColorOver(LEFT_COLOR);
		btnSearchStudent.setBorderColor(Color.black);
		btnSearchStudent.addActionListener(adminController);
		btnSearchStudent.setBounds(tfsearchStudent.getX() - 98, tfsearchStudent.getY(), 100,
				tfsearchStudent.getHeight());
		childPanel.add(btnSearchStudent);

		searchstudents = new JComboBox<String>();
		searchstudents.addItem("Mã học sinh");
		searchstudents.addItem("Họ và tên");
		searchstudents.addItem("Địa chỉ");
		searchstudents.addItem("Năm sinh");
		searchstudents.addItem("Mã phụ huynh");
		searchstudents.addItem("LBT");
		searchstudents.setFont(ROW_TABLE_FONT);
		searchstudents.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		searchstudents.addActionListener(adminController);
		searchstudents.setBounds(tfsearchStudent.getX() + tfsearchStudent.getWidth(), tfsearchStudent.getY(), 140,
				tfsearchStudent.getHeight());
		searchstudents.setBackground(SEARCH_COLOR);
		childPanel.add(searchstudents);

		comboSortStudent = new JComboBox<String>();
		comboSortStudent.addItem("Mã học sinh");
		comboSortStudent.addItem("Họ và tên");
		comboSortStudent.addItem("Năm sinh");
		comboSortStudent.setBackground(SEARCH_COLOR);
		comboSortStudent.setFont(ROW_TABLE_FONT);
		comboSortStudent.setBorder(searchstudents.getBorder());
		comboSortStudent.setBounds(124, 80, searchstudents.getWidth(), 30);
		childPanel.add(comboSortStudent);

		MyButton btnSortStudent = new MyButton();
		btnSortStudent.setFont(LABEL_CHILD_PANEL);
		btnSortStudent.setText("Sắp xếp");
		btnSortStudent.setFocusPainted(false);
		btnSortStudent.setColor(SEARCH_COLOR);
		btnSortStudent.setColorOver(LEFT_COLOR);
		btnSortStudent.setBorderColor(Color.black);
		btnSortStudent.setBounds(comboSortStudent.getX() - 78, tfsearchStudent.getY(), 80, tfsearchStudent.getHeight());
		btnSortStudent.addActionListener(adminController);
		childPanel.add(btnSortStudent);

		grbtnSortStudent = new ButtonGroup();
		JRadioButton rdibtnUpStudent = new JRadioButton("↑");
		rdibtnUpStudent.setFont(ACCROSS_FONT);
		rdibtnUpStudent.setBackground(RIGHT_COLOR);
		rdibtnUpStudent.setFocusPainted(false);
		rdibtnUpStudent.setBounds(comboSortStudent.getX() + comboSortStudent.getWidth() + 10, comboSortStudent.getY(),
				40, comboSortStudent.getHeight());
		grbtnSortStudent.add(rdibtnUpStudent);
		childPanel.add(rdibtnUpStudent);

		JRadioButton rdibtnDownStudent = new JRadioButton("↓");
		rdibtnDownStudent.setFont(ACCROSS_FONT);
		rdibtnDownStudent.setBackground(RIGHT_COLOR);
		rdibtnDownStudent.setFocusPainted(false);
		rdibtnDownStudent.setBounds(rdibtnUpStudent.getX() + rdibtnUpStudent.getWidth(), comboSortStudent.getY(), 40,
				comboSortStudent.getHeight());
		grbtnSortStudent.add(rdibtnDownStudent);
		childPanel.add(rdibtnDownStudent);

		JRadioButton rdibtnNoneStudent = new JRadioButton("Không");
		rdibtnNoneStudent.setFont(LABEL_CHILD_PANEL);
		rdibtnNoneStudent.setBackground(RIGHT_COLOR);
		rdibtnNoneStudent.setFocusPainted(false);
		rdibtnNoneStudent.setSelected(true);
		rdibtnNoneStudent.setBounds(rdibtnDownStudent.getX() + rdibtnDownStudent.getWidth(), comboSortStudent.getY(),
				100, comboSortStudent.getHeight());
		grbtnSortStudent.add(rdibtnNoneStudent);
		childPanel.add(rdibtnNoneStudent);

		btnSeeMoreStd = new MyButton();
		btnSeeMoreStd.setText("Xem thêm");
		btnSeeMoreStd.setRadius(20);
		btnSeeMoreStd.setForeground(BUTTON_FG_COLOR);
		btnSeeMoreStd.setFont(COLUMN_TABLE_FONT);
		btnSeeMoreStd.setFocusPainted(false);
		btnSeeMoreStd.setColorOver(BUTTON_OVER_COLOR);
		btnSeeMoreStd.setColor(BUTTON_COLOR);
		btnSeeMoreStd.setBorderColor(Color.BLACK);
		btnSeeMoreStd.setBounds(52, 664, 127, 30);
		btnSeeMoreStd.addActionListener(adminController);
		childPanel.add(btnSeeMoreStd);

		btnSeeAllStd = new MyButton();
		btnSeeAllStd.setText("Xem tất cả");
		btnSeeAllStd.setRadius(20);
		btnSeeAllStd.setForeground(BUTTON_FG_COLOR);
		btnSeeAllStd.setFont(COLUMN_TABLE_FONT);
		btnSeeAllStd.setFocusPainted(false);
		btnSeeAllStd.setColorOver(BUTTON_OVER_COLOR);
		btnSeeAllStd.setColor(BUTTON_COLOR);
		btnSeeAllStd.setBorderColor(Color.BLACK);
		btnSeeAllStd.addActionListener(adminController);
		btnSeeAllStd.setBounds(189, 664, 127, 30);
		childPanel.add(btnSeeAllStd);

		MyButton btnAdd = new MyButton();
		btnAdd.setForeground(BUTTON_FG_COLOR);
		btnAdd.setText("Thêm học sinh");
		btnAdd.setRadius(20);
		btnAdd.setFont(COLUMN_TABLE_FONT);
		btnAdd.setFocusPainted(false);
		btnAdd.setColorOver(BUTTON_OVER_COLOR);
		btnAdd.setColor(BUTTON_COLOR);
		btnAdd.setBorderColor(Color.BLACK);
		btnAdd.setBounds(810, 664, 164, 30);
		btnAdd.addActionListener(adminController);
		childPanel.add(btnAdd);

		amountOfStd = new JLabel();
		amountOfStd.setFont(LABEL_CHILD_PANEL);
		amountOfStd.setForeground(FOREGROUND);
		amountOfStd.setBounds(780, scrollStudent.getY() + scrollStudent.getHeight(), 280, 30);
		amountOfStd.setText("Tổng cộng: " + adminController.getSizeOfStudent() + " học sinh");
		childPanel.add(amountOfStd);

		adminController.doAddMore();
		parentPanel.add(childPanel, "listStudent");
	}

	public void addRowsParents(List<String> ids, boolean isDeleteAll) {
		if (isDeleteAll)
			deleteAllRowOf(tableListParents);
		DefaultTableModel model = (DefaultTableModel) tableListParents.getModel();
		int count = model.getRowCount() + 1;
		for (String id : ids) {
			if (!id.equals("0")) {
				Parents pr = adminController.getParentsById(Integer.parseInt(id));
				String sex = (pr.getSex() ? "Nam" : "Nữ");
				model.addRow(new Object[] { count++, pr.getParents_id(), pr.getName(), sex, pr.getDateOfBirth(),
						pr.getAddress(), pr.getPhoneNumber() });
			}
		}
		amountOfPr.setText("Tổng cộng: " + adminController.getSizeOfParents() + " phụ huynh");
	}

	public void manageParents(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		JLabel lbTitle = new JLabel("QUẢN LÝ PHỤ HUYNH");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbTitle.setBounds(PADDING, PADDING - 10, parentPanel.getWidth() - 2 * PADDING, 40);
		lbTitle.setForeground(FOREGROUND);
		childPanel.add(lbTitle);

		boolean[] editableColumnsListParents = { false, false, false, false, false, false, false, true };
		NonEditableTableModel modelListParents = new NonEditableTableModel(new Object[][] {}, new String[] { "STT",
				"Mã phụ huynh", "Họ và tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "" },
				editableColumnsListParents);

		tableListParents = new MyTable();
		tableListParents.setSelectionBackground(TABLEHEADER_COLOR);
		tableListParents.setGridColor(new Color(0, 0, 0));
		tableListParents.setRowHeight(30);
		tableListParents.setFont(ROW_TABLE_FONT);
		tableListParents.setModel(modelListParents);
		tableListParents.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListParents.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableListParents.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableListParents.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableListParents.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableListParents.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableListParents.getColumnModel().getColumn(6).setPreferredWidth(60);
		tableListParents.getColumnModel().getColumn(7).setPreferredWidth(60);

		tableListParents.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableParentsController = new TableController(tableListParents, this, "Parents");
		tableListParents.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
		tableListParents.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(tableParentsController));

		JTableHeader headerParents = tableListParents.getTableHeader();
		headerParents.setFont(COLUMN_TABLE_FONT);
		headerParents.setLayout(null);
		headerParents.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerParents.setBackground(TABLEHEADER_COLOR);

		JScrollPane scrollParents = new JScrollPane(tableListParents);
		scrollParents.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollParents.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollParents.setBounds(30, 120, 964, 500);
		childPanel.add(scrollParents);

		tfsearchParents = new JTextField();
		tfsearchParents.setBounds(640, 80, 200, 30);
		tfsearchParents.setFont(ROW_TABLE_FONT);
		tfsearchParents.setBackground(SEARCH_COLOR);
		tfsearchParents.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		childPanel.add(tfsearchParents);

		btnSearchParents = new MyButton();
		btnSearchParents.setFont(LABEL_CHILD_PANEL);
		btnSearchParents.setText("Tìm kiếm");
		btnSearchParents.setFocusPainted(false);
		btnSearchParents.setColor(SEARCH_COLOR);
		btnSearchParents.setColorOver(LEFT_COLOR);
		btnSearchParents.setBorderColor(Color.black);
		btnSearchParents.addActionListener(adminController);
		btnSearchParents.setBounds(tfsearchStudent.getX() - 98, tfsearchStudent.getY(), 100,
				tfsearchStudent.getHeight());
		childPanel.add(btnSearchParents);

		searchParents = new JComboBox<String>();
		searchParents.addItem("Mã phụ huynh");
		searchParents.addItem("Họ và tên");
		searchParents.addItem("Địa chỉ");
		searchParents.addItem("Năm sinh");
		searchParents.addItem("Số điện thoại");
		searchParents.setFont(ROW_TABLE_FONT);
		searchParents.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		searchParents.setFocusable(true);
		searchParents.setBounds(tfsearchParents.getX() + tfsearchParents.getWidth(), tfsearchParents.getY(), 140,
				tfsearchParents.getHeight());
		searchParents.setBackground(SEARCH_COLOR);
		childPanel.add(searchParents);

		comboSortParents = new JComboBox<String>();
		comboSortParents.addItem("Mã phụ huynh");
		comboSortParents.addItem("Họ và tên");
		comboSortParents.addItem("Năm sinh");
		comboSortParents.setBackground(SEARCH_COLOR);
		comboSortParents.setFont(ROW_TABLE_FONT);
		comboSortParents.setBorder(searchstudents.getBorder());
		comboSortParents.setBounds(124, 80, searchstudents.getWidth(), 30);
		childPanel.add(comboSortParents);

		MyButton btnSortParents = new MyButton();
		btnSortParents.setFont(LABEL_CHILD_PANEL);
		btnSortParents.setText("Sắp xếp");
		btnSortParents.setFocusPainted(false);
		btnSortParents.setColor(SEARCH_COLOR);
		btnSortParents.setColorOver(LEFT_COLOR);
		btnSortParents.setBorderColor(Color.black);
		btnSortParents.setBounds(comboSortParents.getX() - 78, tfsearchParents.getY(), 80, tfsearchParents.getHeight());
		btnSortParents.addActionListener(adminController);
		childPanel.add(btnSortParents);

		grbtnSortParents = new ButtonGroup();
		JRadioButton rdibtnUpParents = new JRadioButton("↑");
		rdibtnUpParents.setFont(ACCROSS_FONT);
		rdibtnUpParents.setBackground(RIGHT_COLOR);
		rdibtnUpParents.setFocusPainted(false);
		rdibtnUpParents.setBounds(comboSortParents.getX() + comboSortParents.getWidth() + 10, comboSortParents.getY(),
				40, comboSortParents.getHeight());
		rdibtnUpParents.setSelected(true);
		grbtnSortParents.add(rdibtnUpParents);
		childPanel.add(rdibtnUpParents);

		JRadioButton rdibtnDownParents = new JRadioButton("↓");
		rdibtnDownParents.setFont(ACCROSS_FONT);
		rdibtnDownParents.setBackground(RIGHT_COLOR);
		rdibtnDownParents.setFocusPainted(false);
		rdibtnDownParents.setBounds(rdibtnUpParents.getX() + rdibtnUpParents.getWidth(), comboSortParents.getY(), 40,
				comboSortParents.getHeight());
		grbtnSortParents.add(rdibtnDownParents);
		childPanel.add(rdibtnDownParents);

		JRadioButton rdibtnNoneParents = new JRadioButton("Không");
		rdibtnNoneParents.setFont(LABEL_CHILD_PANEL);
		rdibtnNoneParents.setBackground(RIGHT_COLOR);
		rdibtnNoneParents.setFocusPainted(false);
		rdibtnNoneParents.setSelected(true);
		rdibtnNoneParents.setBounds(rdibtnDownParents.getX() + rdibtnDownParents.getWidth(), comboSortParents.getY(),
				100, comboSortStudent.getHeight());
		grbtnSortParents.add(rdibtnNoneParents);
		childPanel.add(rdibtnNoneParents);

		MyButton btnAddParents = new MyButton();
		btnAddParents.setForeground(BUTTON_FG_COLOR);
		btnAddParents.setText("Thêm phụ huynh");
		btnAddParents.setRadius(20);
		btnAddParents.setFont(COLUMN_TABLE_FONT);
		btnAddParents.setFocusPainted(false);
		btnAddParents.setColorOver(BUTTON_OVER_COLOR);
		btnAddParents.setColor(BUTTON_COLOR);
		btnAddParents.setBorderColor(Color.BLACK);
		btnAddParents.setBounds(810, 664, 164, 30);
		btnAddParents.addActionListener(adminController);
		childPanel.add(btnAddParents);

		btnSeeMorePr = new MyButton();
		btnSeeMorePr.setText("Xem thêm");
		btnSeeMorePr.setRadius(20);
		btnSeeMorePr.setForeground(BUTTON_FG_COLOR);
		btnSeeMorePr.setFont(COLUMN_TABLE_FONT);
		btnSeeMorePr.setFocusPainted(false);
		btnSeeMorePr.setColorOver(BUTTON_OVER_COLOR);
		btnSeeMorePr.setColor(BUTTON_COLOR);
		btnSeeMorePr.setBorderColor(Color.BLACK);
		btnSeeMorePr.setBounds(52, 664, 127, 30);
		btnSeeMorePr.addActionListener(adminController);
		childPanel.add(btnSeeMorePr);

		btnSeeAllPr = new MyButton();
		btnSeeAllPr.setText("Xem tất cả");
		btnSeeAllPr.setRadius(20);
		btnSeeAllPr.setForeground(BUTTON_FG_COLOR);
		btnSeeAllPr.setFont(COLUMN_TABLE_FONT);
		btnSeeAllPr.setFocusPainted(false);
		btnSeeAllPr.setColorOver(BUTTON_OVER_COLOR);
		btnSeeAllPr.setColor(BUTTON_COLOR);
		btnSeeAllPr.setBorderColor(Color.BLACK);
		btnSeeAllPr.addActionListener(adminController);
		btnSeeAllPr.setBounds(189, 664, 127, 30);
		childPanel.add(btnSeeAllPr);

		amountOfPr = new JLabel();
		amountOfPr.setFont(LABEL_CHILD_PANEL);
		amountOfPr.setForeground(FOREGROUND);
		amountOfPr.setBounds(780, scrollParents.getY() + scrollParents.getHeight(), 280, 30);
		childPanel.add(amountOfPr);
		parentPanel.add(childPanel, "listParents");
	}

	public void addRowsTeacher(List<String> ids, boolean isDeleteAll) {
		if (isDeleteAll)
			deleteAllRowOf(tableListTeacher);
		DefaultTableModel model = (DefaultTableModel) tableListTeacher.getModel();
		int count = model.getRowCount() + 1;
		for (String id : ids) {
			Teacher tc = adminController.getTeacherByFoodById(Integer.parseInt(id));
			String sex = (tc.getSex() ? "Nam" : "Nữ");
			String dbc = (tc.getBoardingClass_id() == 0) ? "Không có"
					: adminController.getNameBoardingClass(tc.getBoardingClass_id());
			model.addRow(new Object[] { count++, tc.getTeacher_id(), tc.getName(), sex, tc.getDateOfBirth(),
					tc.getAddress(), tc.getPhoneNumber(), dbc });
		}
		amountOfTc.setText("Tổng cộng: " + adminController.getSizeOfTeacher() + " giáo viên");
	}

	public void manageTeachers(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		JLabel lbTitle = new JLabel("QUẢN LÝ GIÁO VIÊN");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbTitle.setBounds(PADDING, PADDING - 10, parentPanel.getWidth() - 2 * PADDING, 40);
		lbTitle.setForeground(FOREGROUND);
		childPanel.add(lbTitle);

		boolean[] editableColumnsListTeacher = { false, false, false, false, false, false, false, false, true };
		NonEditableTableModel modelListTeacher = new NonEditableTableModel(new Object[][] {}, new String[] { "STT",
				"Mã giáo viên", "Họ và tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Lớp quản lí", "" },
				editableColumnsListTeacher);

		tableListTeacher = new MyTable();
		tableListTeacher.setModel(modelListTeacher);
		tableListTeacher.setSelectionBackground(TABLEHEADER_COLOR);
		tableListTeacher.setGridColor(new Color(0, 0, 0));
		tableListTeacher.setRowHeight(30);
		tableListTeacher.setFont(ROW_TABLE_FONT);
		tableListTeacher.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListTeacher.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableListTeacher.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableListTeacher.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableListTeacher.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableListTeacher.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableListTeacher.getColumnModel().getColumn(6).setPreferredWidth(60);
		tableListTeacher.getColumnModel().getColumn(7).setPreferredWidth(60);

		tableListTeacher.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableTeacherController = new TableController(tableListTeacher, this, "Teacher");
		tableListTeacher.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
		tableListTeacher.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(tableTeacherController));

		JTableHeader headerTeacher = tableListTeacher.getTableHeader();
		headerTeacher.setFont(COLUMN_TABLE_FONT);
		headerTeacher.setLayout(null);
		headerTeacher.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerTeacher.setBackground(TABLEHEADER_COLOR);

		JScrollPane scrollTeacher = new JScrollPane(tableListTeacher);
		scrollTeacher.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollTeacher.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTeacher.setBounds(30, 120, 964, 500);
		childPanel.add(scrollTeacher);

		tfsearchTeacher = new JTextField();
		tfsearchTeacher.setBounds(640, 80, 200, 30);
		tfsearchTeacher.setFont(ROW_TABLE_FONT);
		tfsearchTeacher.setBackground(SEARCH_COLOR);
		tfsearchTeacher.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		childPanel.add(tfsearchTeacher);

		btnSearchTeacher = new MyButton();
		btnSearchTeacher.setFont(LABEL_CHILD_PANEL);
		btnSearchTeacher.setText("Tìm kiếm");
		btnSearchTeacher.setFocusPainted(false);
		btnSearchTeacher.setColor(SEARCH_COLOR);
		btnSearchTeacher.setColorOver(LEFT_COLOR);
		btnSearchTeacher.setBorderColor(Color.black);
		btnSearchTeacher.addActionListener(adminController);
		btnSearchTeacher.setBounds(tfsearchStudent.getX() - 98, tfsearchStudent.getY(), 100,
				tfsearchStudent.getHeight());
		childPanel.add(btnSearchTeacher);

		searchTeacher = new JComboBox<String>();
		searchTeacher.addItem("Mã giáo viên");
		searchTeacher.addItem("Họ và tên");
		searchTeacher.addItem("Lớp quản lí");
		searchTeacher.addItem("Số điện thoại");
		searchTeacher.addItem("Địa chỉ");
		searchTeacher.addItem("Năm sinh");
		searchTeacher.setFont(ROW_TABLE_FONT);
		searchTeacher.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		searchTeacher.setFocusable(true);
		searchTeacher.addActionListener(adminController);
		searchTeacher.setBounds(tfsearchTeacher.getX() + tfsearchTeacher.getWidth(), tfsearchTeacher.getY(), 140,
				tfsearchTeacher.getHeight());
		searchTeacher.setBackground(SEARCH_COLOR);
		childPanel.add(searchTeacher);

		comboSortTeacher = new JComboBox<String>();
		comboSortTeacher.addItem("Mã giáo viên");
		comboSortTeacher.addItem("Họ và tên");
		comboSortTeacher.addItem("Năm sinh");
		comboSortTeacher.setBackground(SEARCH_COLOR);
		comboSortTeacher.setFont(ROW_TABLE_FONT);
		comboSortTeacher.setBorder(searchstudents.getBorder());
		comboSortTeacher.setBounds(124, 80, searchstudents.getWidth(), 30);
		childPanel.add(comboSortTeacher);

		MyButton btnSortTeacher = new MyButton();
		btnSortTeacher.setFont(LABEL_CHILD_PANEL);
		btnSortTeacher.setText("Sắp xếp");
		btnSortTeacher.setFocusPainted(false);
		btnSortTeacher.setColor(SEARCH_COLOR);
		btnSortTeacher.setColorOver(LEFT_COLOR);
		btnSortTeacher.setBorderColor(Color.black);
		btnSortTeacher.setBounds(comboSortTeacher.getX() - 78, tfsearchTeacher.getY(), 80, tfsearchTeacher.getHeight());
		btnSortTeacher.addActionListener(adminController);
		childPanel.add(btnSortTeacher);

		grbtnSortTeacher = new ButtonGroup();
		JRadioButton rdibtnUpTeacher = new JRadioButton("↑");
		rdibtnUpTeacher.setFont(ACCROSS_FONT);
		rdibtnUpTeacher.setBackground(RIGHT_COLOR);
		rdibtnUpTeacher.setFocusPainted(false);
		rdibtnUpTeacher.setBounds(comboSortTeacher.getX() + comboSortTeacher.getWidth() + 10, comboSortTeacher.getY(),
				40, comboSortTeacher.getHeight());
		rdibtnUpTeacher.setSelected(true);
		grbtnSortTeacher.add(rdibtnUpTeacher);
		childPanel.add(rdibtnUpTeacher);

		JRadioButton rdibtnDownTeacher = new JRadioButton("↓");
		rdibtnDownTeacher.setFont(ACCROSS_FONT);
		rdibtnDownTeacher.setBackground(RIGHT_COLOR);
		rdibtnDownTeacher.setFocusPainted(false);
		rdibtnDownTeacher.setBounds(rdibtnUpTeacher.getX() + rdibtnUpTeacher.getWidth(), comboSortTeacher.getY(), 40,
				comboSortTeacher.getHeight());
		grbtnSortTeacher.add(rdibtnDownTeacher);
		childPanel.add(rdibtnDownTeacher);

		JRadioButton rdibtnNoneTeacher = new JRadioButton("Không");
		rdibtnNoneTeacher.setFont(LABEL_CHILD_PANEL);
		rdibtnNoneTeacher.setBackground(RIGHT_COLOR);
		rdibtnNoneTeacher.setFocusPainted(false);
		rdibtnNoneTeacher.setSelected(true);
		rdibtnNoneTeacher.setBounds(rdibtnDownTeacher.getX() + rdibtnDownTeacher.getWidth(), comboSortTeacher.getY(),
				100, comboSortStudent.getHeight());
		grbtnSortTeacher.add(rdibtnNoneTeacher);
		childPanel.add(rdibtnNoneTeacher);

		MyButton btnAddTeacher = new MyButton();
		btnAddTeacher.setForeground(BUTTON_FG_COLOR);
		btnAddTeacher.setText("Thêm giáo viên");
		btnAddTeacher.setRadius(20);
		btnAddTeacher.setFont(COLUMN_TABLE_FONT);
		btnAddTeacher.setFocusPainted(false);
		btnAddTeacher.setColorOver(BUTTON_OVER_COLOR);
		btnAddTeacher.setColor(BUTTON_COLOR);
		btnAddTeacher.setBorderColor(Color.BLACK);
		btnAddTeacher.setBounds(810, 664, 164, 30);
		btnAddTeacher.addActionListener(adminController);
		childPanel.add(btnAddTeacher);

		btnSeeMoreTc = new MyButton();
		btnSeeMoreTc.setText("Xem thêm");
		btnSeeMoreTc.setRadius(20);
		btnSeeMoreTc.setForeground(BUTTON_FG_COLOR);
		btnSeeMoreTc.setFont(COLUMN_TABLE_FONT);
		btnSeeMoreTc.setFocusPainted(false);
		btnSeeMoreTc.setColorOver(BUTTON_OVER_COLOR);
		btnSeeMoreTc.setColor(BUTTON_COLOR);
		btnSeeMoreTc.setBorderColor(Color.BLACK);
		btnSeeMoreTc.setBounds(52, 664, 127, 30);
		btnSeeMoreTc.addActionListener(adminController);
		childPanel.add(btnSeeMoreTc);

		btnSeeAllTc = new MyButton();
		btnSeeAllTc.setText("Xem tất cả");
		btnSeeAllTc.setRadius(20);
		btnSeeAllTc.setForeground(BUTTON_FG_COLOR);
		btnSeeAllTc.setFont(COLUMN_TABLE_FONT);
		btnSeeAllTc.setFocusPainted(false);
		btnSeeAllTc.setColorOver(BUTTON_OVER_COLOR);
		btnSeeAllTc.setColor(BUTTON_COLOR);
		btnSeeAllTc.setBorderColor(Color.BLACK);
		btnSeeAllTc.addActionListener(adminController);
		btnSeeAllTc.setBounds(189, 664, 127, 30);
		childPanel.add(btnSeeAllTc);

		amountOfTc = new JLabel();
		amountOfTc.setFont(LABEL_CHILD_PANEL);
		amountOfTc.setForeground(FOREGROUND);
		amountOfTc.setBounds(780, scrollTeacher.getY() + scrollTeacher.getHeight(), 280, 30);
		childPanel.add(amountOfTc);
		parentPanel.add(childPanel, "listTeacher");
	}

	public void addRowsClass(List<String> ids, boolean isDeleteAll) {
		if (isDeleteAll)
			deleteAllRowOf(tableListClass);
		DefaultTableModel model = (DefaultTableModel) tableListClass.getModel();
		int count = model.getRowCount() + 1;
		for (String id : ids) {
			int ma = Integer.parseInt(id);
			if (ma > 0) {
				BoardingClass bc = adminController.getBoardingClassById(ma);
				String tc = adminController.getNameTeacherById(bc.getTeacher_id());
				model.addRow(new Object[] { count++, bc.getBoardingClass_id(), bc.getName(), bc.getRoom(),
						bc.getStudent_ids().size(), bc.getNumberOfBed(), tc });
			}
		}
		amountOfCl.setText("Tổng cộng: " + adminController.getSizeOfBoardingClass() + " lớp");
	}

	public void manageClass(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		JLabel lbTitle = new JLabel("QUẢN LÝ LỚP BÁN TRÚ");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbTitle.setBounds(PADDING, PADDING - 10, parentPanel.getWidth() - 2 * PADDING, 40);
		lbTitle.setForeground(FOREGROUND);
		childPanel.add(lbTitle);

		boolean[] editableColumnsListClass = { false, false, false, false, false, false, false, true };
		NonEditableTableModel modelListClass = new NonEditableTableModel(new Object[][] {}, new String[] { "STT",
				"Mã lớp học", "Tên lớp", "Phòng", "Số học sinh", "Số chỗ ngủ", "Giáo viên quản lí", "" },
				editableColumnsListClass);

		tableListClass = new MyTable();
		tableListClass.setSelectionBackground(TABLEHEADER_COLOR);
		tableListClass.setGridColor(new Color(0, 0, 0));
		tableListClass.setRowHeight(30);
		tableListClass.setFont(ROW_TABLE_FONT);
		tableListClass.setModel(modelListClass);
		tableListClass.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListClass.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableListClass.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableListClass.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableListClass.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableListClass.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableListClass.getColumnModel().getColumn(6).setPreferredWidth(150);

		tableListClass.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableClassController = new TableController(tableListClass, this, "BoardingClass");
		tableListClass.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
		tableListClass.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(tableClassController));

		JTableHeader headerClass = tableListClass.getTableHeader();
		headerClass.setFont(COLUMN_TABLE_FONT);
		headerClass.setLayout(null);
		headerClass.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerClass.setBackground(TABLEHEADER_COLOR);

		JScrollPane scrollClass = new JScrollPane(tableListClass);
		scrollClass.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollClass.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollClass.setBounds(30, 120, 964, 500);
		childPanel.add(scrollClass);

		tfsearchClass = new JTextField();
		tfsearchClass.setBounds(640, 80, 200, 30);
		tfsearchClass.setFont(ROW_TABLE_FONT);
		tfsearchClass.setBackground(SEARCH_COLOR);
		tfsearchClass.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		childPanel.add(tfsearchClass);

		btnSearchClass = new MyButton();
		btnSearchClass.setFont(LABEL_CHILD_PANEL);
		btnSearchClass.setText("Tìm kiếm");
		btnSearchClass.setFocusPainted(false);
		btnSearchClass.setColor(SEARCH_COLOR);
		btnSearchClass.setColorOver(LEFT_COLOR);
		btnSearchClass.setBorderColor(Color.black);
		btnSearchClass.addActionListener(adminController);
		btnSearchClass.setBounds(tfsearchStudent.getX() - 98, tfsearchStudent.getY(), 100, tfsearchStudent.getHeight());
		childPanel.add(btnSearchClass);

		searchClass = new JComboBox<String>();
		searchClass.addItem("Mã lớp học");
		searchClass.addItem("Tên lớp");
		searchClass.addItem("Phòng");
		searchClass.addItem("Số chỗ ngủ");
		searchClass.setFont(ROW_TABLE_FONT);
		searchClass.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		searchClass.setFocusable(true);
		searchClass.addActionListener(adminController);
		searchClass.setBounds(tfsearchClass.getX() + tfsearchClass.getWidth(), tfsearchClass.getY(), 140,
				tfsearchClass.getHeight());
		searchClass.setBackground(SEARCH_COLOR);
		childPanel.add(searchClass);

		comboSortClass = new JComboBox<String>();
		comboSortClass.addItem("Mã lớp");
		comboSortClass.addItem("Tên lớp");
		comboSortClass.addItem("Số học sinh");
		comboSortClass.addItem("Số chỗ ngủ");
		comboSortClass.setBackground(SEARCH_COLOR);
		comboSortClass.setFont(ROW_TABLE_FONT);
		comboSortClass.setBorder(searchstudents.getBorder());
		comboSortClass.setBounds(124, 80, searchstudents.getWidth(), 30);
		childPanel.add(comboSortClass);

		MyButton btnSortClass = new MyButton();
		btnSortClass.setFont(LABEL_CHILD_PANEL);
		btnSortClass.setText("Sắp xếp");
		btnSortClass.setFocusPainted(false);
		btnSortClass.setColor(SEARCH_COLOR);
		btnSortClass.setColorOver(LEFT_COLOR);
		btnSortClass.setBorderColor(Color.black);
		btnSortClass.setBounds(comboSortClass.getX() - 78, tfsearchClass.getY(), 80, tfsearchClass.getHeight());
		btnSortClass.addActionListener(adminController);
		childPanel.add(btnSortClass);

		grbtnSortClass = new ButtonGroup();
		JRadioButton rdibtnUpClass = new JRadioButton("↑");
		rdibtnUpClass.setFont(ACCROSS_FONT);
		rdibtnUpClass.setBackground(RIGHT_COLOR);
		rdibtnUpClass.setFocusPainted(false);
		rdibtnUpClass.setBounds(comboSortClass.getX() + comboSortClass.getWidth() + 10, comboSortClass.getY(), 40,
				comboSortClass.getHeight());
		rdibtnUpClass.setSelected(true);
		grbtnSortClass.add(rdibtnUpClass);
		childPanel.add(rdibtnUpClass);

		JRadioButton rdibtnDownClass = new JRadioButton("↓");
		rdibtnDownClass.setFont(ACCROSS_FONT);
		rdibtnDownClass.setBackground(RIGHT_COLOR);
		rdibtnDownClass.setFocusPainted(false);
		rdibtnDownClass.setBounds(rdibtnUpClass.getX() + rdibtnUpClass.getWidth(), comboSortClass.getY(), 40,
				comboSortClass.getHeight());
		grbtnSortClass.add(rdibtnDownClass);
		childPanel.add(rdibtnDownClass);

		JRadioButton rdibtnNoneClass = new JRadioButton("Không");
		rdibtnNoneClass.setFont(LABEL_CHILD_PANEL);
		rdibtnNoneClass.setBackground(RIGHT_COLOR);
		rdibtnNoneClass.setFocusPainted(false);
		rdibtnNoneClass.setSelected(true);
		rdibtnNoneClass.setBounds(rdibtnDownClass.getX() + rdibtnDownClass.getWidth(), comboSortClass.getY(), 100,
				comboSortStudent.getHeight());
		grbtnSortClass.add(rdibtnNoneClass);
		childPanel.add(rdibtnNoneClass);

		MyButton btnAddClass = new MyButton();
		btnAddClass.setForeground(BUTTON_FG_COLOR);
		btnAddClass.setText("Thêm lớp bán trú");
		btnAddClass.setRadius(20);
		btnAddClass.setFont(COLUMN_TABLE_FONT);
		btnAddClass.setFocusPainted(false);
		btnAddClass.setColorOver(BUTTON_OVER_COLOR);
		btnAddClass.setColor(BUTTON_COLOR);
		btnAddClass.setBorderColor(Color.BLACK);
		btnAddClass.setBounds(810, 664, 164, 30);
		btnAddClass.addActionListener(adminController);
		childPanel.add(btnAddClass);

		btnSeeMoreCl = new MyButton();
		btnSeeMoreCl.setText("Xem thêm");
		btnSeeMoreCl.setRadius(20);
		btnSeeMoreCl.setForeground(BUTTON_FG_COLOR);
		btnSeeMoreCl.setFont(COLUMN_TABLE_FONT);
		btnSeeMoreCl.setFocusPainted(false);
		btnSeeMoreCl.setColorOver(BUTTON_OVER_COLOR);
		btnSeeMoreCl.setColor(BUTTON_COLOR);
		btnSeeMoreCl.setBorderColor(Color.BLACK);
		btnSeeMoreCl.setBounds(52, 664, 127, 30);
		btnSeeMoreCl.addActionListener(adminController);
		childPanel.add(btnSeeMoreCl);

		btnSeeAllCl = new MyButton();
		btnSeeAllCl.setText("Xem tất cả");
		btnSeeAllCl.setRadius(20);
		btnSeeAllCl.setForeground(BUTTON_FG_COLOR);
		btnSeeAllCl.setFont(COLUMN_TABLE_FONT);
		btnSeeAllCl.setFocusPainted(false);
		btnSeeAllCl.setColorOver(BUTTON_OVER_COLOR);
		btnSeeAllCl.setColor(BUTTON_COLOR);
		btnSeeAllCl.setBorderColor(Color.BLACK);
		btnSeeAllCl.addActionListener(adminController);
		btnSeeAllCl.setBounds(189, 664, 127, 30);
		childPanel.add(btnSeeAllCl);

		amountOfCl = new JLabel();
		amountOfCl.setFont(LABEL_CHILD_PANEL);
		amountOfCl.setForeground(FOREGROUND);
		amountOfCl.setBounds(780, scrollClass.getY() + scrollClass.getHeight(), 280, 30);
		childPanel.add(amountOfCl);

		parentPanel.add(childPanel, "listClass");
	}

	public void setListMenu(List<Integer> ids) {

	}

	public void addRowsMenu(List<String> ids, boolean isDeleteAll) {
		if (isDeleteAll)
			deleteAllRowOf(tableListMenu);
		DefaultTableModel model = (DefaultTableModel) tableListMenu.getModel();
		int count = model.getRowCount() + 1;
		for (String id : ids) {
			int ma = Integer.parseInt(id);
			Menu menu = adminController.getMenuById(ma);
			String mainFood = "";
			String subFood = "";
			List<Food> foods = adminController.getFoodsOfMenu(ma);
			for (Food food : foods) {
				if (food.getCategory())
					mainFood += food.getName() + ", ";
				else
					subFood = food.getName();
			}
			String status = menu.isActive() ? "Có" : "Không";
			if (mainFood.length() != 0)
				mainFood = mainFood.substring(0, mainFood.length() - 2);
			model.addRow(new Object[] { count++, id, mainFood, subFood, status });
		}
		amountOfMenu.setText("Tổng cộng: " + adminController.getSizeOfMenu() + " thực đơn");
	}

	public void manageMenu(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		JLabel lbTitle = new JLabel("QUẢN LÝ THỰC ĐƠN");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbTitle.setBounds(PADDING, PADDING - 10, parentPanel.getWidth() - 2 * PADDING, 40);
		lbTitle.setForeground(FOREGROUND);
		childPanel.add(lbTitle);

		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		boolean[] editableColumnsListMenu = { false, false, false, false, false, true };
		NonEditableTableModel modelListMenu = new NonEditableTableModel(new Object[][] {},
				new String[] { "STT", "Mã thực đơn", "Món chính", "Món phụ", "Được thêm vào tháng sau", "" },
				editableColumnsListMenu);

		tableListMenu = new MyTable();
		tableListMenu.setSelectionBackground(TABLEHEADER_COLOR);
		tableListMenu.setGridColor(new Color(0, 0, 0));
		tableListMenu.setRowHeight(30);
		tableListMenu.setFont(ROW_TABLE_FONT);
		tableListMenu.setModel(modelListMenu);
		tableListMenu.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListMenu.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableListMenu.getColumnModel().getColumn(2).setPreferredWidth(300);
		tableListMenu.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableListMenu.getColumnModel().getColumn(4).setPreferredWidth(150);

		tableListMenu.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableMenuController = new TableController(tableListMenu, this, "Menu");
		tableListMenu.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
		tableListMenu.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(tableMenuController));

		JTableHeader headerMenu = tableListMenu.getTableHeader();
		headerMenu.setFont(COLUMN_TABLE_FONT);
		headerMenu.setLayout(null);
		headerMenu.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerMenu.setBackground(TABLEHEADER_COLOR);

		JScrollPane scrollMenu = new JScrollPane(tableListMenu);
		scrollMenu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollMenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMenu.setBounds(30, 120, 964, 500);
		childPanel.add(scrollMenu);

		tfsearchMenu = new JTextField();
		tfsearchMenu.setBounds(640, 80, 200, 30);
		tfsearchMenu.setFont(ROW_TABLE_FONT);
		tfsearchMenu.setBackground(SEARCH_COLOR);
		tfsearchMenu.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		childPanel.add(tfsearchMenu);

		btnSearchMenu = new MyButton();
		btnSearchMenu.setFont(LABEL_CHILD_PANEL);
		btnSearchMenu.setText("Tìm kiếm");
		btnSearchMenu.setFocusPainted(false);
		btnSearchMenu.setColor(SEARCH_COLOR);
		btnSearchMenu.setColorOver(LEFT_COLOR);
		btnSearchMenu.setBorderColor(Color.black);
		btnSearchMenu.addActionListener(adminController);
		btnSearchMenu.setBounds(tfsearchMenu.getX() - 98, tfsearchMenu.getY(), 100, tfsearchMenu.getHeight());
		childPanel.add(btnSearchMenu);

		searchMenus = new JComboBox<String>();
		searchMenus.addItem("Mã thực đơn");
		searchMenus.addItem("Món chính");
		searchMenus.addItem("Món phụ");
		searchMenus.setFont(ROW_TABLE_FONT);
		searchMenus.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		searchMenus.addActionListener(adminController);
		searchMenus.setBounds(tfsearchMenu.getX() + tfsearchMenu.getWidth(), tfsearchMenu.getY(), 140,
				tfsearchMenu.getHeight());
		searchMenus.setBackground(SEARCH_COLOR);
		childPanel.add(searchMenus);

		JLabel lblStatus = new JLabel("Danh sách thực đơn:");
		lblStatus.setForeground(FOREGROUND);
		lblStatus.setFont(LABEL_CHILD_PANEL);
		lblStatus.setBounds(50, tfsearchMenu.getY(), 230, tfsearchMenu.getHeight());
		childPanel.add(lblStatus);

		MyButton btnSeeMoreStd = new MyButton();
		btnSeeMoreStd.setText("Xem thêm");
		btnSeeMoreStd.setRadius(20);
		btnSeeMoreStd.setForeground(BUTTON_FG_COLOR);
		btnSeeMoreStd.setFont(COLUMN_TABLE_FONT);
		btnSeeMoreStd.setFocusPainted(false);
		btnSeeMoreStd.setColorOver(BUTTON_OVER_COLOR);
		btnSeeMoreStd.setColor(BUTTON_COLOR);
		btnSeeMoreStd.setBorderColor(Color.BLACK);
		btnSeeMoreStd.setBounds(52, 664, 127, 30);
		btnSeeMoreStd.addActionListener(adminController);
		childPanel.add(btnSeeMoreStd);

		MyButton btnSeeAllStd = new MyButton();
		btnSeeAllStd.setText("Xem tất cả");
		btnSeeAllStd.setRadius(20);
		btnSeeAllStd.setForeground(BUTTON_FG_COLOR);
		btnSeeAllStd.setFont(COLUMN_TABLE_FONT);
		btnSeeAllStd.setFocusPainted(false);
		btnSeeAllStd.setColorOver(BUTTON_OVER_COLOR);
		btnSeeAllStd.setColor(BUTTON_COLOR);
		btnSeeAllStd.setBorderColor(Color.BLACK);
		btnSeeAllStd.addActionListener(adminController);
		btnSeeAllStd.setBounds(189, 664, 127, 30);
		childPanel.add(btnSeeAllStd);

		MyButton btnAdd = new MyButton();
		btnAdd.setForeground(BUTTON_FG_COLOR);
		btnAdd.setText("Thêm thực đơn");
		btnAdd.setRadius(20);
		btnAdd.setFont(COLUMN_TABLE_FONT);
		btnAdd.setFocusPainted(false);
		btnAdd.setColorOver(BUTTON_OVER_COLOR);
		btnAdd.setColor(BUTTON_COLOR);
		btnAdd.setBorderColor(Color.BLACK);
		btnAdd.setBounds(810, 664, 164, 30);
		btnAdd.addActionListener(adminController);
		childPanel.add(btnAdd);

		amountOfMenu = new JLabel();
		amountOfMenu.setFont(LABEL_CHILD_PANEL);
		amountOfMenu.setForeground(FOREGROUND);
		amountOfMenu.setBounds(780, scrollMenu.getY() + scrollMenu.getHeight(), 280, 30);
		childPanel.add(amountOfMenu);

		adminController.doAddMore();
		parentPanel.add(childPanel, "listMenu");

		parentPanel.add(childPanel, "listMenu");
	}

	public void addRowsFood(List<String> ids, boolean isDeleteAll) {
		if (isDeleteAll)
			deleteAllRowOf(tableListFood);
		DefaultTableModel model = (DefaultTableModel) tableListFood.getModel();
		int count = model.getRowCount() + 1;
		for (String id : ids) {
			Food food = adminController.getFoodByFoodById(Integer.parseInt(id));
			String category = food.getCategory() ? "Chính" : "Phụ";
			model.addRow(
					new Object[] { count++, food.getFood_id(), food.getName(), category, food.getMenu_ids().size() });
		}
		amountOfFood.setText("Tổng cộng: " + adminController.getSizeOfFood() + " món ăn");
	}

	public void manageFood(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		JLabel lbTitle = new JLabel("QUẢN LÝ MÓN ĂN");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbTitle.setBounds(PADDING, PADDING - 10, parentPanel.getWidth() - 2 * PADDING, 40);
		lbTitle.setForeground(FOREGROUND);
		childPanel.add(lbTitle);

		boolean[] editableColumnsListFood = { false, false, false, false, false, true };
		NonEditableTableModel modelListFood = new NonEditableTableModel(new Object[][] {},
				new String[] { "STT", "Mã món ăn", "Tên món ăn", "Loại", "Số thực đơn có mặt", "" },
				editableColumnsListFood);

		tableListFood = new MyTable();
		tableListFood.setSelectionBackground(TABLEHEADER_COLOR);
		tableListFood.setGridColor(new Color(0, 0, 0));
		tableListFood.setRowHeight(30);
		tableListFood.setFont(ROW_TABLE_FONT);
		tableListFood.setModel(modelListFood);
		tableListFood.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListFood.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableListFood.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableListFood.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableListFood.getColumnModel().getColumn(4).setPreferredWidth(70);

		tableListFood.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableFoodController = new TableController(tableListFood, this, "Food");
		tableListFood.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
		tableListFood.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(tableFoodController));

		JTableHeader headerFood = tableListFood.getTableHeader();
		headerFood.setFont(COLUMN_TABLE_FONT);
		headerFood.setLayout(null);
		headerFood.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerFood.setBackground(TABLEHEADER_COLOR);

		JScrollPane scrollFood = new JScrollPane(tableListFood);
		scrollFood.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollFood.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollFood.setBounds(30, 120, 964, 500);
		childPanel.add(scrollFood);

		tfsearchFood = new JTextField();
		tfsearchFood.setBounds(640, 80, 200, 30);
		tfsearchFood.setFont(ROW_TABLE_FONT);
		tfsearchFood.setBackground(SEARCH_COLOR);
		tfsearchFood.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		childPanel.add(tfsearchFood);

		btnSearchFood = new MyButton();
		btnSearchFood.setFont(LABEL_CHILD_PANEL);
		btnSearchFood.setText("Tìm kiếm");
		btnSearchFood.setFocusPainted(false);
		btnSearchFood.setColor(SEARCH_COLOR);
		btnSearchFood.setColorOver(LEFT_COLOR);
		btnSearchFood.setBorderColor(Color.black);
		btnSearchFood.addActionListener(adminController);
		btnSearchFood.setBounds(tfsearchStudent.getX() - 98, tfsearchStudent.getY(), 100, tfsearchStudent.getHeight());
		childPanel.add(btnSearchFood);

		searchFood = new JComboBox<String>();
		searchFood.addItem("Mã món ăn");
		searchFood.addItem("Tên món ăn");
		searchFood.addItem("Loại");
		searchFood.setFont(ROW_TABLE_FONT);
		searchFood.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		searchFood.setFocusable(true);
		searchFood.setBounds(tfsearchFood.getX() + tfsearchFood.getWidth(), tfsearchFood.getY(), 140,
				tfsearchFood.getHeight());
		searchFood.setBackground(SEARCH_COLOR);
		childPanel.add(searchFood);

		comboSortFood = new JComboBox<String>();
		comboSortFood.addItem("Mã món ăn");
		comboSortFood.addItem("Tên món ăn");
		comboSortFood.addItem("Số thực đơn có mặt");
		comboSortFood.setBackground(SEARCH_COLOR);
		comboSortFood.setFont(ROW_TABLE_FONT);
		comboSortFood.setBorder(searchstudents.getBorder());
		comboSortFood.setBounds(124, 80, searchstudents.getWidth(), 30);
		childPanel.add(comboSortFood);

		MyButton btnSortFood = new MyButton();
		btnSortFood.setFont(LABEL_CHILD_PANEL);
		btnSortFood.setText("Sắp xếp");
		btnSortFood.setFocusPainted(false);
		btnSortFood.setColor(SEARCH_COLOR);
		btnSortFood.setColorOver(LEFT_COLOR);
		btnSortFood.setBorderColor(Color.black);
		btnSortFood.setBounds(comboSortFood.getX() - 78, tfsearchFood.getY(), 80, tfsearchFood.getHeight());
		btnSortFood.addActionListener(adminController);
		childPanel.add(btnSortFood);

		grbtnSortFood = new ButtonGroup();
		JRadioButton rdibtnUpFood = new JRadioButton("↑");
		rdibtnUpFood.setFont(ACCROSS_FONT);
		rdibtnUpFood.setBackground(RIGHT_COLOR);
		rdibtnUpFood.setFocusPainted(false);
		rdibtnUpFood.setBounds(comboSortFood.getX() + comboSortFood.getWidth() + 10, comboSortFood.getY(), 40,
				comboSortFood.getHeight());
		rdibtnUpFood.setSelected(true);
		grbtnSortFood.add(rdibtnUpFood);
		childPanel.add(rdibtnUpFood);

		JRadioButton rdibtnDownFood = new JRadioButton("↓");
		rdibtnDownFood.setFont(ACCROSS_FONT);
		rdibtnDownFood.setBackground(RIGHT_COLOR);
		rdibtnDownFood.setFocusPainted(false);
		rdibtnDownFood.setBounds(rdibtnUpFood.getX() + rdibtnUpFood.getWidth(), comboSortFood.getY(), 40,
				comboSortFood.getHeight());
		grbtnSortFood.add(rdibtnDownFood);
		childPanel.add(rdibtnDownFood);

		JRadioButton rdibtnNoneFood = new JRadioButton("Không");
		rdibtnNoneFood.setFont(LABEL_CHILD_PANEL);
		rdibtnNoneFood.setBackground(RIGHT_COLOR);
		rdibtnNoneFood.setFocusPainted(false);
		rdibtnNoneFood.setSelected(true);
		rdibtnNoneFood.setBounds(rdibtnDownFood.getX() + rdibtnDownFood.getWidth(), comboSortFood.getY(), 100,
				comboSortStudent.getHeight());
		grbtnSortFood.add(rdibtnNoneFood);
		childPanel.add(rdibtnNoneFood);

		MyButton btnAddFood = new MyButton();
		btnAddFood.setForeground(BUTTON_FG_COLOR);
		btnAddFood.setText("Thêm món ăn");
		btnAddFood.setRadius(20);
		btnAddFood.setFont(COLUMN_TABLE_FONT);
		btnAddFood.setFocusPainted(false);
		btnAddFood.setColorOver(BUTTON_OVER_COLOR);
		btnAddFood.setColor(BUTTON_COLOR);
		btnAddFood.setBorderColor(Color.BLACK);
		btnAddFood.setBounds(810, 664, 164, 30);
		btnAddFood.addActionListener(adminController);
		childPanel.add(btnAddFood);

		btnSeeMoreFood = new MyButton();
		btnSeeMoreFood.setText("Xem thêm");
		btnSeeMoreFood.setRadius(20);
		btnSeeMoreFood.setForeground(BUTTON_FG_COLOR);
		btnSeeMoreFood.setFont(COLUMN_TABLE_FONT);
		btnSeeMoreFood.setFocusPainted(false);
		btnSeeMoreFood.setColorOver(BUTTON_OVER_COLOR);
		btnSeeMoreFood.setColor(BUTTON_COLOR);
		btnSeeMoreFood.setBorderColor(Color.BLACK);
		btnSeeMoreFood.setBounds(52, 664, 127, 30);
		btnSeeMoreFood.addActionListener(adminController);
		childPanel.add(btnSeeMoreFood);

		btnSeeAllFood = new MyButton();
		btnSeeAllFood.setText("Xem tất cả");
		btnSeeAllFood.setRadius(20);
		btnSeeAllFood.setForeground(BUTTON_FG_COLOR);
		btnSeeAllFood.setFont(COLUMN_TABLE_FONT);
		btnSeeAllFood.setFocusPainted(false);
		btnSeeAllFood.setColorOver(BUTTON_OVER_COLOR);
		btnSeeAllFood.setColor(BUTTON_COLOR);
		btnSeeAllFood.setBorderColor(Color.BLACK);
		btnSeeAllFood.addActionListener(adminController);
		btnSeeAllFood.setBounds(189, 664, 127, 30);
		childPanel.add(btnSeeAllFood);

		amountOfFood = new JLabel();
		amountOfFood.setFont(LABEL_CHILD_PANEL);
		amountOfFood.setForeground(FOREGROUND);
		amountOfFood.setBounds(780, scrollFood.getY() + scrollFood.getHeight(), 280, 30);
		childPanel.add(amountOfFood);

		parentPanel.add(childPanel, "listFood");
	}

	public void addRowsEatingHistory(List<String> ids, boolean isDeleteall) {
		if (isDeleteall)
			deleteAllRowOf(tableListEHis);
		DefaultTableModel model = (DefaultTableModel) tableListEHis.getModel();
		int count = model.getRowCount() + 1;
		for (String id : ids) {
			int ma = Integer.parseInt(id);
			EatingHistory ehis = adminController.getEatingHistoryById(ma);
			String mainFood = "";
			String subFood = "";
			List<Food> foods = adminController.getFoodsOfMenu(ehis.getMenu_id());
			for (Food food : foods) {
				if (food.getCategory())
					mainFood += food.getName() + ", ";
				else
					subFood = food.getName();
			}
			if (mainFood.length() > 2) {
				mainFood = mainFood.substring(0, mainFood.length() - 2);
			}
			model.addRow(new Object[] { count++, ma, ehis.getMenu_id(), mainFood, subFood, ehis.getEating_day() });
		}
	}

	public void addRowsCreateEHis(List<EatingHistory> eatingHistories) {
		deleteAllRowOf(tableListEHis);
		DefaultTableModel model = (DefaultTableModel) tableListEHis.getModel();
		int count = 1;
		for (EatingHistory ehis : eatingHistories) {
			String mainFood = "";
			String subFood = "";
			List<Food> foods = adminController.getFoodsOfMenu(ehis.getMenu_id());
			for (Food food : foods) {
				if (food.getCategory())
					mainFood += food.getName() + ", ";
				else
					subFood = food.getName();
			}
			model.addRow(new Object[] { count++, "", ehis.getMenu_id(), mainFood, subFood, ehis.getEating_day() });
		}
	}

	public List<EatingHistory> getListEHis() {
		List<EatingHistory> result = new ArrayList<EatingHistory>();
		DefaultTableModel model = (DefaultTableModel) tableListEHis.getModel();
		int count = model.getRowCount();
		for (int i = 0; i < count; i++) {
			EatingHistory ehis = new EatingHistory();
			String ehis_id = model.getValueAt(i, 1) + "";
			int id = 0;
			if (ehis_id.length() != 0)
				id = Integer.parseInt(ehis_id);
			ehis.setEatingHistory_id(id);
			int menu_id = Integer.parseInt(model.getValueAt(i, 2) + "");
			int bFee_id = ((CBBItem) comboBoxEHis.getSelectedItem()).getValue();
			ehis.setBoardingFee_id(bFee_id);
			ehis.setMenu_id(menu_id);
			String date = model.getValueAt(i, 5) + "";
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8));
			ehis.setEating_day(Date.valueOf(LocalDate.of(year, month, day)));
			result.add(ehis);
		}
		return result;
	}

	public void setTextButtonEHis(String edit, String create) {
		btnSaveEHis.setText(edit);
		btnCancelEHis.setText(create);
	}

	public void setEnableButtonEHis(boolean enableEdit, boolean enableCreate) {
		if (enableEdit) {
			panelEatingHistory.add(btnSaveEHis);
			panelEatingHistory.revalidate();
			panelEatingHistory.repaint();
		} else {
			panelEatingHistory.remove(btnSaveEHis);
			panelEatingHistory.revalidate();
			panelEatingHistory.repaint();
		}

		if (enableCreate) {
			btnCancelEHis.setEnabled(true);
			panelEatingHistory.add(btnCancelEHis);
			panelEatingHistory.revalidate();
			panelEatingHistory.repaint();
		} else {
			panelEatingHistory.remove(btnCancelEHis);
			panelEatingHistory.revalidate();
			panelEatingHistory.repaint();
		}
	}

	public void setEditEHis() {
		boolean[] editableColumnsListEHis = { false, false, false, false, false, false, true };
		NonEditableTableModel modelListEHis = new NonEditableTableModel(new Object[][] {},
				new String[] { "STT", "Mã ngày ăn", "Mã TĐ", "Bữa trưa", "Bữa phụ", "Ngày ăn", "" },
				editableColumnsListEHis);
		tableListEHis.setModel(modelListEHis);
		tableListEHis.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListEHis.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableListEHis.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableListEHis.getColumnModel().getColumn(3).setPreferredWidth(300);
		tableListEHis.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableListEHis.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableListEHis.setSelectionBackground(TABLEHEADER_COLOR);
		tableListEHis.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		TableController tableTCLEHis = new TableController(tableListEHis, this, "EatingHistory");
		tableListEHis.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
		tableListEHis.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(tableTCLEHis));
	}

	public void setNonEditEHis() {
		boolean[] editableColumnsListEHis = { false, false, false, false, false, false };
		NonEditableTableModel modelListEHis = new NonEditableTableModel(new Object[][] {},
				new String[] { "STT", "Mã ngày ăn", "Mã TĐ", "Bữa trưa", "Bữa phụ", "Ngày ăn" },
				editableColumnsListEHis);
		tableListEHis.setModel(modelListEHis);
		tableListEHis.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListEHis.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableListEHis.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableListEHis.getColumnModel().getColumn(3).setPreferredWidth(300);
		tableListEHis.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableListEHis.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableListEHis.setSelectionBackground(TABLEHEADER_COLOR);
		tableListEHis.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}

	public void manageEatingHistory(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		JLabel lbTitle = new JLabel("LỊCH SỬ NGÀY ĂN");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbTitle.setBounds(PADDING, PADDING - 10, parentPanel.getWidth() - 2 * PADDING, 40);
		lbTitle.setForeground(FOREGROUND);
		childPanel.add(lbTitle);

		tfsearchEHis = new JTextField();
		tfsearchEHis.setBounds(636, 136, 200, 30);
		tfsearchEHis.setFont(ROW_TABLE_FONT);
		tfsearchEHis.setBackground(SEARCH_COLOR);
		tfsearchEHis.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		childPanel.add(tfsearchEHis);

		MyButton btnSearchEHis = new MyButton();
		btnSearchEHis.setFont(LABEL_CHILD_PANEL);
		btnSearchEHis.setText("Tìm kiếm");
		btnSearchEHis.setFocusPainted(false);
		btnSearchEHis.setColor(SEARCH_COLOR);
		btnSearchEHis.setColorOver(LEFT_COLOR);
		btnSearchEHis.setBorderColor(Color.black);
		btnSearchEHis.addActionListener(adminController);
		btnSearchEHis.setBounds(538, 136, 100, tfsearchEHis.getHeight());
		childPanel.add(btnSearchEHis);

		searchEHis = new JComboBox<String>();
		searchEHis.addItem("Mã ngày ăn");
		searchEHis.addItem("Mã thực đơn");
		searchEHis.addItem("Ngày ăn");
		searchEHis.setFont(ROW_TABLE_FONT);
		searchEHis.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		searchEHis.setFocusable(true);
		searchEHis.setBounds(836, 136, 140, tfsearchEHis.getHeight());
		searchEHis.setBackground(SEARCH_COLOR);
		childPanel.add(searchEHis);

		boolean[] editableColumnsListEHis = { false, false, false, false, false, false };
		NonEditableTableModel modelListEHis = new NonEditableTableModel(new Object[][] {},
				new String[] { "STT", "Mã ngày ăn", "Mã TĐ", "Bữa trưa", "Bữa phụ", "Ngày ăn" },
				editableColumnsListEHis);

		tableListEHis = new MyTable();
		tableListEHis.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tableListEHis.setRowHeight(30);
		tableListEHis.setModel(modelListEHis);
		tableListEHis.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListEHis.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableListEHis.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableListEHis.getColumnModel().getColumn(3).setPreferredWidth(300);
		tableListEHis.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableListEHis.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableListEHis.setSelectionBackground(TABLEHEADER_COLOR);
		tableListEHis.setGridColor(Color.black);

		JTableHeader headerFood = tableListEHis.getTableHeader();
		headerFood.setFont(COLUMN_TABLE_FONT);
		headerFood.setLayout(null);
		headerFood.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerFood.setBackground(TABLEHEADER_COLOR);
		JScrollPane scrollEHis = new JScrollPane(tableListEHis);
		scrollEHis.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
		scrollEHis.setBounds(30, 176, 964, 445);
		childPanel.add(scrollEHis);

		JLabel lblListEHis = new JLabel("Danh sách ngày ăn:");
		lblListEHis.setFont(LABEL_CHILD_PANEL);
		lblListEHis.setForeground(FOREGROUND);
		lblListEHis.setBounds(43, 139, 155, 23);
		childPanel.add(lblListEHis);

		comboBoxEHis = new JComboBox<CBBItem>();
		List<CBBItem> bfs = BoardingFee.getIdAndName();
		for (CBBItem cbb : bfs) {
			comboBoxEHis.addItem(cbb);
		}
		comboBoxEHis.setBounds(457, 80, 192, 26);
		comboBoxEHis.addActionListener(adminController);
		comboBoxEHis.setBackground(SEARCH_COLOR);
		comboBoxEHis.setForeground(FOREGROUND);
		comboBoxEHis.addActionListener(adminController);
		comboBoxEHis.setFont(LABEL_CHILD_PANEL);
		childPanel.add(comboBoxEHis);

		JLabel lblTime = new JLabel("Thời gian:");
		lblTime.setForeground(FOREGROUND);
		lblTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblTime.setBounds(351, 80, 96, 23);
		childPanel.add(lblTime);

		JPanel seperator1 = new JPanel();
		seperator1.setBackground(FOREGROUND);
		seperator1.setBounds(67, 116, 898, 3);
		childPanel.add(seperator1);

		JPanel seperator2 = new JPanel();
		seperator2.setBackground(FOREGROUND);
		seperator2.setBounds(67, 643, 898, 3);
		childPanel.add(seperator2);

		btnSaveEHis = new MyButton();
		btnSaveEHis.setText("Chỉnh sửa");
		btnSaveEHis.setRadius(20);
		btnSaveEHis.setForeground(Color.WHITE);
		btnSaveEHis.setFont(COLUMN_TABLE_FONT);
		btnSaveEHis.setFocusPainted(false);
		btnSaveEHis.setColorOver(BUTTON_OVER_COLOR);
		btnSaveEHis.setColorClick(BUTTON_CLICK_COLOR);
		btnSaveEHis.setColor(BUTTON_COLOR);
		btnSaveEHis.setBorderColor(Color.BLACK);
		btnSaveEHis.addActionListener(adminController);
		btnSaveEHis.setBounds(593, 667, 192, 30);

		btnCancelEHis = new MyButton();
		btnCancelEHis.setText("Phân thực đơn");
		btnCancelEHis.setRadius(20);
		btnCancelEHis.setForeground(Color.WHITE);
		btnCancelEHis.setFont(COLUMN_TABLE_FONT);
		btnCancelEHis.setFocusPainted(false);
		btnCancelEHis.setColorOver(BUTTON_OVER_COLOR);
		btnCancelEHis.setColorClick(BUTTON_CLICK_COLOR);
		btnCancelEHis.setColor(BUTTON_COLOR);
		btnCancelEHis.setBorderColor(Color.BLACK);
		btnCancelEHis.setEnabled(false);
		btnCancelEHis.addActionListener(adminController);
		btnCancelEHis.setBounds(794, 667, 200, 30);
		childPanel.add(btnCancelEHis);

		parentPanel.add(childPanel, "eatingHistory");
	}

	public void setEditableBFee(boolean editable) {
		tfAmount.setEditable(editable);
		tfDayFrom.setEditable(editable);
		tfMonthFrom.setEditable(editable);
		tfYearFrom.setEditable(editable);
		tfDayTo.setEditable(editable);
		tfMonthTo.setEditable(editable);
		lblYearTo.setEditable(editable);
		btnSeeMoreInvoice.setEnabled(!editable);
		btnSeeAllIn.setEnabled(!editable);
	}

	public void setTfPrinted(String str) {
		this.tfInvoice.setText(str);
	}

	public int getTfPrinted() {
		return Integer.parseInt(this.tfInvoice.getText());
	}

	public void setBoardingFee(BoardingFee bf) {
		if (bf != null) {
			tfBFee_id.setText(bf.getBoardingFee_id() + "");
			tfBFee_id.setBackground(SEARCH_COLOR);
			tfAmount.setText((bf.getMainCosts() + bf.getSubCosts()) + "");
			tfAmount.setBackground(SEARCH_COLOR);
			LocalDate start = bf.getStart_day().toLocalDate();
			tfDayFrom.setText(start.getDayOfMonth() + "");
			tfDayFrom.setBackground(SEARCH_COLOR);
			tfMonthFrom.setText(start.getMonthValue() + "");
			tfMonthFrom.setBackground(SEARCH_COLOR);
			tfYearFrom.setText(start.getYear() + "");
			tfYearFrom.setBackground(SEARCH_COLOR);
			LocalDate end = bf.getEnd_day().toLocalDate();
			tfDayTo.setText(end.getDayOfMonth() + "");
			tfDayTo.setBackground(SEARCH_COLOR);
			tfMonthTo.setText(end.getMonthValue() + "");
			tfMonthTo.setBackground(SEARCH_COLOR);
			lblYearTo.setText(end.getYear() + "");
			lblYearTo.setBackground(SEARCH_COLOR);

			long totalMoney = adminController.getTotalMoneyOfBoardingFeeId(bf.getBoardingFee_id()) / 1000;
			tfTotalAMount.setText(totalMoney + "");
			long payedMoney = adminController.getPayedMoneyOfBoardingFeeId(bf.getBoardingFee_id()) / 1000;
			tfTotalPayed.setText(payedMoney + "");
			tfStdTotal.setText(bf.getInvoice_ids().size() + "");
			int stdpayed = adminController.getPayedStudentOfBoardingFee(bf.getBoardingFee_id());
			tfStdPayed.setText(stdpayed + "");
			int nonPrinted = adminController.getSizeOfPaidInvoiceOfBoaringFee(bf.getBoardingFee_id());
			tfInvoice.setText(nonPrinted + "");
			setTfPrinted(nonPrinted + "");
		}
	}

	public int getBoardingFeeid() {
		int bFee_id = ((CBBItem) comboBoxBFee.getSelectedItem()).getValue();
		return bFee_id;
	}

	public void addRowsInvoice(List<Integer> ids, boolean isDeleteAll) {
		if (isDeleteAll)
			deleteAllRowOf(tableInvoice);
		DefaultTableModel model = (DefaultTableModel) tableInvoice.getModel();
		int count = model.getRowCount() + 1;
		for (int id : ids) {
			Invoice iv = adminController.getInvoiceById(id);
			Student std = adminController.getStudentById(iv.getStudent_id());
			String bdc = (std.getBoardingClass_id() == 0) ? "Không có"
					: adminController.getNameBoardingClass(std.getBoardingClass_id());
			String status = "Chưa nộp";
			if (iv.getStatusPayment() == 2)
				status = "Chưa in";
			else if (iv.getStatusPayment() == 3)
				status = "Đã in";
			model.addRow(new Object[] { count++, iv.getInvoice_id(), iv.getStudent_id(), std.getName(), bdc,
					iv.getPayment_date(), iv.getMoney(), status });
		}
		setEditableBFee(false);
	}

	public void manageBoardingFee(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		JLabel lbTitle = new JLabel("QUẢN LÝ THU TIỀN");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbTitle.setBounds(PADDING, PADDING - 10, parentPanel.getWidth() - 2 * PADDING, 40);
		lbTitle.setForeground(FOREGROUND);
		childPanel.add(lbTitle);

		boolean[] editableColumnsListInvoice = { false, false, false, false, false, false, false, false, true };
		NonEditableTableModel modelListInvoice = new NonEditableTableModel(new Object[][] {}, new String[] { "STT",
				"Mã hóa đơn", "Mã HS", "Họ và tên", "Lớp", "Ngày nộp", "Số tiền nộp", "Trạng thái", "" },
				editableColumnsListInvoice);

		tableInvoice = new MyTable();
		tableInvoice.setFont(ROW_TABLE_FONT);
		tableInvoice.setRowHeight(30);
		tableInvoice.setModel(modelListInvoice);
		tableInvoice.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableInvoice.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableInvoice.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableInvoice.getColumnModel().getColumn(3).setPreferredWidth(175);
		tableInvoice.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableInvoice.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableInvoice.getColumnModel().getColumn(6).setPreferredWidth(70);
		tableInvoice.setSelectionBackground(TABLEHEADER_COLOR);
		tableInvoice.setGridColor(new Color(0, 0, 0));
		tableInvoice.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		TableTeacherCTL tableInvoiceCTL = new TableTeacherCTL("Invoice", (DefaultTableModel) tableInvoice.getModel());
		tableInvoice.getColumnModel().getColumn(8).setCellRenderer(new TableActionTcCellRender());
		tableInvoice.getColumnModel().getColumn(8).setCellEditor(new TableActionTcCellEditor(tableInvoiceCTL));

		JTableHeader headerFood = tableInvoice.getTableHeader();
		headerFood.setFont(COLUMN_TABLE_FONT);
		headerFood.setLayout(null);
		headerFood.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerFood.setBackground(TABLEHEADER_COLOR);

		JScrollPane scrollPane = new JScrollPane(tableInvoice);
		scrollPane.setBounds(30, 288, 964, 354);
		childPanel.add(scrollPane);

		tfsearchInvoice = new JTextField();
		tfsearchInvoice.setBounds(636, 248, 200, 30);
		tfsearchInvoice.setFont(ROW_TABLE_FONT);
		tfsearchInvoice.setBackground(SEARCH_COLOR);
		tfsearchInvoice.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		childPanel.add(tfsearchInvoice);

		btnSearchInvoice = new MyButton();
		btnSearchInvoice.setFont(LABEL_CHILD_PANEL);
		btnSearchInvoice.setText("Tìm kiếm");
		btnSearchInvoice.setFocusPainted(false);
		btnSearchInvoice.setColor(SEARCH_COLOR);
		btnSearchInvoice.setColorOver(LEFT_COLOR);
		btnSearchInvoice.setBorderColor(Color.black);
		btnSearchInvoice.addActionListener(adminController);
		btnSearchInvoice.setBounds(tfsearchInvoice.getX() - 98, tfsearchInvoice.getY(), 100,
				tfsearchInvoice.getHeight());
		childPanel.add(btnSearchInvoice);

		searchInvoice = new JComboBox<String>();
		searchInvoice.addItem("Mã hóa đơn");
		searchInvoice.addItem("Mã học sinh");
		searchInvoice.addItem("Tên học sinh");
		searchInvoice.addItem("Lớp");
		searchInvoice.addItem("Trạng thái");
		searchInvoice.setFont(ROW_TABLE_FONT);
		searchInvoice.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		searchInvoice.setFocusable(true);
		searchInvoice.setBounds(tfsearchInvoice.getX() + tfsearchInvoice.getWidth(), tfsearchInvoice.getY(), 140,
				tfsearchInvoice.getHeight());
		searchInvoice.setBackground(SEARCH_COLOR);
		childPanel.add(searchInvoice);

		comboBoxBFee = new JComboBox<CBBItem>();
		List<CBBItem> bfs = BoardingFee.getIdAndName();
		for (CBBItem cbb : bfs) {
			comboBoxBFee.addItem(cbb);
		}
		comboBoxBFee.setFont(ROW_TABLE_FONT);
		comboBoxBFee.setBackground(SEARCH_COLOR);
		comboBoxBFee.setForeground(FOREGROUND);
		comboBoxBFee.addActionListener(adminController);
		comboBoxBFee.setBounds(469, 80, 210, 30);
		childPanel.add(comboBoxBFee);

		JLabel lblListInvoice = new JLabel("Lịch sử thu tiền:");
		lblListInvoice.setFont(LABEL_CHILD_PANEL);
		lblListInvoice.setBounds(30, 248, 180, 30);
		childPanel.add(lblListInvoice);

		JPanel sepertor = new JPanel();
		sepertor.setBackground(BUTTON_COLOR);
		sepertor.setBounds(30, 239, 964, 3);
		childPanel.add(sepertor);

		JLabel lblDTT = new JLabel("Đợt thu tiền:");
		lblDTT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDTT.setFont(LABEL_CHILD_PANEL);
		lblDTT.setBounds(338, 80, 115, 30);
		childPanel.add(lblDTT);

		PanelRound panelInfoBFee = new PanelRound();
		panelInfoBFee.setBorderWidth(2);
		panelInfoBFee.setBground(SEARCH_COLOR);
		panelInfoBFee.setRadius(20);
		panelInfoBFee.setBounds(30, 120, 469, 109);
		childPanel.add(panelInfoBFee);
		panelInfoBFee.setLayout(null);

		JLabel lblInfo = new JLabel("Thông tin");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(LABEL_CHILD_PANEL);
		lblInfo.setBounds(10, 10, 449, 21);
		panelInfoBFee.add(lblInfo);

		JLabel lblFrom = new JLabel("Từ:");
		lblFrom.setBounds(10, 69, 76, 30);
		panelInfoBFee.add(lblFrom);
		lblFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFrom.setFont(LABEL_CHILD_PANEL);

		JLabel lblTo = new JLabel("Đến:");
		lblTo.setBounds(246, 69, 52, 30);
		panelInfoBFee.add(lblTo);
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setFont(LABEL_CHILD_PANEL);

		JLabel lblAmount = new JLabel("Đầy đủ:");
		lblAmount.setBounds(150, 41, 80, 30);
		panelInfoBFee.add(lblAmount);
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setFont(LABEL_CHILD_PANEL);

		JLabel lblId = new JLabel("Mã ĐTT:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(LABEL_CHILD_PANEL);
		lblId.setBounds(10, 41, 76, 30);
		panelInfoBFee.add(lblId);

		tfBFee_id = new MyTextField();
		tfBFee_id.setBounds(96, 41, 60, 27);
		tfBFee_id.setHorizontalAlignment(SwingConstants.CENTER);
		tfBFee_id.setEditable(false);
		tfBFee_id.setFont(ROW_TABLE_FONT);
		tfBFee_id.setBackground(SEARCH_COLOR);
		panelInfoBFee.add(tfBFee_id);

		tfAmount = new MyTextField();
		tfAmount.setFont(ROW_TABLE_FONT);
		tfAmount.setHorizontalAlignment(SwingConstants.CENTER);
		tfAmount.setBounds(228, 45, 76, 27);
		panelInfoBFee.add(tfAmount);

		JLabel lblUnit = new JLabel("đồng/ngày");
		lblUnit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnit.setFont(LABEL_CHILD_PANEL);
		lblUnit.setBounds(300, 41, 89, 30);
		panelInfoBFee.add(lblUnit);

		MyButton btnDetail = new MyButton();
		btnDetail.setText("Chi tiết");
		btnDetail.setBorderColor(Color.black);
		btnDetail.setColor(BUTTON_COLOR);
		btnDetail.setRadius(15);
		btnDetail.setColorOver(BUTTON_OVER_COLOR);
		btnDetail.setForeground(BUTTON_FG_COLOR);
		btnDetail.setColorClick(BUTTON_CLICK_COLOR);
		btnDetail.setFocusPainted(false);
		btnDetail.addActionListener(adminController);
		btnDetail.setBounds(395, 41, 65, 30);
		panelInfoBFee.add(btnDetail);

		tfDayFrom = new MyTextField();
		tfDayFrom.setFont(ROW_TABLE_FONT);
		tfDayFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDayFrom.setBounds(96, 72, 24, 27);
		panelInfoBFee.add(tfDayFrom);

		JLabel seperator1 = new JLabel("/");
		seperator1.setHorizontalAlignment(SwingConstants.CENTER);
		seperator1.setFont(SEPERATOR_FONT);
		seperator1.setBounds(120, 70, 17, 30);
		panelInfoBFee.add(seperator1);

		tfMonthFrom = new MyTextField();
		tfMonthFrom.setFont(ROW_TABLE_FONT);
		tfMonthFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		tfMonthFrom.setBounds(135, 73, 24, 27);
		panelInfoBFee.add(tfMonthFrom);

		JLabel seperator2 = new JLabel("/");
		seperator2.setHorizontalAlignment(SwingConstants.CENTER);
		seperator2.setFont(SEPERATOR_FONT);
		seperator2.setBounds(162, 69, 17, 30);
		panelInfoBFee.add(seperator2);

		tfYearFrom = new MyTextField();
		tfYearFrom.setFont(ROW_TABLE_FONT);
		tfYearFrom.setBounds(180, 73, 50, 27);
		tfYearFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInfoBFee.add(tfYearFrom);

		tfDayTo = new MyTextField();
		tfDayTo.setFont(ROW_TABLE_FONT);
		tfDayTo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDayTo.setBounds(308, 72, 24, 27);
		panelInfoBFee.add(tfDayTo);

		JLabel seperator3 = new JLabel("/");
		seperator3.setHorizontalAlignment(SwingConstants.CENTER);
		seperator3.setFont(SEPERATOR_FONT);
		seperator3.setBounds(332, 70, 17, 30);
		panelInfoBFee.add(seperator3);

		tfMonthTo = new MyTextField();
		tfMonthTo.setFont(ROW_TABLE_FONT);
		tfMonthTo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfMonthTo.setBounds(347, 73, 24, 27);
		panelInfoBFee.add(tfMonthTo);

		JLabel seperator4 = new JLabel("/");
		seperator4.setHorizontalAlignment(SwingConstants.CENTER);
		seperator4.setFont(SEPERATOR_FONT);
		seperator4.setBounds(374, 69, 17, 30);
		panelInfoBFee.add(seperator4);

		lblYearTo = new MyTextField();
		lblYearTo.setFont(ROW_TABLE_FONT);
		lblYearTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYearTo.setBounds(389, 73, 50, 27);
		panelInfoBFee.add(lblYearTo);

		PanelRound panelBFSitua = new PanelRound();
		panelBFSitua.setBorderWidth(2);
		panelBFSitua.setLayout(null);
		panelBFSitua.setRadius(20);
		panelBFSitua.setBground(TABLEHEADER_COLOR);
		panelBFSitua.setBounds(525, 120, 469, 109);
		childPanel.add(panelBFSitua);

		JLabel lblSitua = new JLabel("Tình hình");
		lblSitua.setHorizontalAlignment(SwingConstants.CENTER);
		lblSitua.setFont(LABEL_CHILD_PANEL);
		lblSitua.setBounds(10, 10, 449, 21);
		panelBFSitua.add(lblSitua);

		JLabel lblInvoice = new JLabel("Số hóa đơn chưa in:");
		lblInvoice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInvoice.setFont(LABEL_CHILD_PANEL);
		lblInvoice.setBounds(275, 69, 159, 30);
		panelBFSitua.add(lblInvoice);

		JLabel lblAmountStd = new JLabel("Số học sinh đóng tiền:");
		lblAmountStd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmountStd.setFont(LABEL_CHILD_PANEL);
		lblAmountStd.setBounds(10, 69, 179, 30);
		panelBFSitua.add(lblAmountStd);

		JLabel lblTotalAmount = new JLabel("Số tiền đã thu:");
		lblTotalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalAmount.setFont(LABEL_CHILD_PANEL);
		lblTotalAmount.setBounds(10, 41, 179, 30);
		panelBFSitua.add(lblTotalAmount);

		JLabel sepertor5 = new JLabel("/");
		sepertor5.setHorizontalAlignment(SwingConstants.CENTER);
		sepertor5.setFont(SEPERATOR_FONT);
		sepertor5.setBounds(265, 40, 17, 30);
		panelBFSitua.add(sepertor5);

		tfTotalAMount = new MyTextField();
		tfTotalAMount.setFont(ROW_TABLE_FONT);
		tfTotalAMount.setHorizontalAlignment(SwingConstants.CENTER);
		tfTotalAMount.setBackground(TABLEHEADER_COLOR);
		tfTotalAMount.setBounds(284, 41, 75, 27);
		panelBFSitua.add(tfTotalAMount);

		tfTotalPayed = new MyTextField();
		tfTotalPayed.setFont(ROW_TABLE_FONT);
		tfTotalPayed.setHorizontalAlignment(SwingConstants.CENTER);
		tfTotalPayed.setBackground(TABLEHEADER_COLOR);
		tfTotalPayed.setBounds(199, 41, 63, 27);
		panelBFSitua.add(tfTotalPayed);

		JLabel lblUnit2 = new JLabel("ngàn đồng");
		lblUnit2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnit2.setFont(LABEL_CHILD_PANEL);
		lblUnit2.setBounds(357, 41, 89, 30);
		panelBFSitua.add(lblUnit2);

		tfStdPayed = new MyTextField();
		tfStdPayed.setFont(ROW_TABLE_FONT);
		tfStdPayed.setHorizontalAlignment(SwingConstants.CENTER);
		tfStdPayed.setBackground(TABLEHEADER_COLOR);
		tfStdPayed.setBounds(199, 71, 30, 27);
		panelBFSitua.add(tfStdPayed);

		JLabel seperator6 = new JLabel("/");
		seperator6.setHorizontalAlignment(SwingConstants.CENTER);
		seperator6.setFont(SEPERATOR_FONT);
		seperator6.setBounds(223, 69, 17, 30);
		panelBFSitua.add(seperator6);

		tfStdTotal = new MyTextField();
		tfStdTotal.setFont(ROW_TABLE_FONT);
		tfStdTotal.setHorizontalAlignment(SwingConstants.CENTER);
		tfStdTotal.setBackground(TABLEHEADER_COLOR);
		tfStdTotal.setBounds(238, 72, 30, 27);
		panelBFSitua.add(tfStdTotal);

		tfInvoice = new MyTextField();
		tfInvoice.setFont(ROW_TABLE_FONT);
		tfInvoice.setHorizontalAlignment(SwingConstants.CENTER);
		tfInvoice.setBackground(TABLEHEADER_COLOR);
		tfInvoice.setBounds(435, 73, 24, 27);
		panelBFSitua.add(tfInvoice);

		btnSeeMoreInvoice = new MyButton();
		btnSeeMoreInvoice.setText("Xem thêm");
		btnSeeMoreInvoice.setRadius(20);
		btnSeeMoreInvoice.setForeground(BUTTON_FG_COLOR);
		btnSeeMoreInvoice.setFont(COLUMN_TABLE_FONT);
		btnSeeMoreInvoice.setFocusPainted(false);
		btnSeeMoreInvoice.setColorOver(BUTTON_OVER_COLOR);
		btnSeeMoreInvoice.setColorClick(BUTTON_CLICK_COLOR);
		btnSeeMoreInvoice.setColor(BUTTON_COLOR);
		btnSeeMoreInvoice.setBorderColor(Color.BLACK);
		btnSeeMoreInvoice.setBounds(30, 668, 153, 30);
		btnSeeMoreInvoice.addActionListener(adminController);
		childPanel.add(btnSeeMoreInvoice);

		btnSeeAllIn = new MyButton();
		btnSeeAllIn.setText("Xem tất cả");
		btnSeeAllIn.setRadius(20);
		btnSeeAllIn.setForeground(BUTTON_FG_COLOR);
		btnSeeAllIn.setFont(COLUMN_TABLE_FONT);
		btnSeeAllIn.setFocusPainted(false);
		btnSeeAllIn.setColorOver(BUTTON_OVER_COLOR);
		btnSeeAllIn.setColorClick(BUTTON_CLICK_COLOR);
		btnSeeAllIn.setColor(BUTTON_COLOR);
		btnSeeAllIn.setBorderColor(Color.BLACK);
		btnSeeAllIn.setBounds(194, 668, 180, 30);
		btnSeeAllIn.addActionListener(adminController);
		childPanel.add(btnSeeAllIn);

		btnAddBFee = new MyButton();
		btnAddBFee.setText("Tạo đợt thu tiền");
		btnAddBFee.setRadius(20);
		btnAddBFee.setForeground(BUTTON_FG_COLOR);
		btnAddBFee.setFont(COLUMN_TABLE_FONT);
		btnAddBFee.setFocusPainted(false);
		btnAddBFee.setColorOver(BUTTON_OVER_COLOR);
		btnAddBFee.setColorClick(BUTTON_CLICK_COLOR);
		btnAddBFee.setColor(BUTTON_COLOR);
		btnAddBFee.setBorderColor(Color.BLACK);
		btnAddBFee.setBounds(814, 668, 180, 30);
		btnAddBFee.addActionListener(adminController);
		childPanel.add(btnAddBFee);

		btnPrint = new MyButton();
		btnPrint.setText("In hóa đơn");
		btnPrint.setRadius(20);
		btnPrint.setForeground(BUTTON_FG_COLOR);
		btnPrint.setFont(COLUMN_TABLE_FONT);
		btnPrint.setFocusPainted(false);
		btnPrint.setColorOver(BUTTON_OVER_COLOR);
		btnPrint.setColorClick(BUTTON_CLICK_COLOR);
		btnPrint.setColor(BUTTON_COLOR);
		btnPrint.setBorderColor(Color.BLACK);
		btnPrint.setBounds(639, 668, 165, 30);
		btnPrint.addActionListener(adminController);
		childPanel.add(btnPrint);

		btnSeeList = new MyButton();
		btnSeeList.setText("Danh sách theo lớp");
		btnSeeList.setRadius(20);
		btnSeeList.setForeground(BUTTON_FG_COLOR);
		btnSeeList.setFont(COLUMN_TABLE_FONT);
		btnSeeList.setFocusPainted(false);
		btnSeeList.setColorOver(BUTTON_OVER_COLOR);
		btnSeeList.setColorClick(BUTTON_CLICK_COLOR);
		btnSeeList.setColor(BUTTON_COLOR);
		btnSeeList.setBorderColor(Color.BLACK);
		btnSeeList.setBounds(382, 668, 250, 30);
		btnSeeList.addActionListener(adminController);
		childPanel.add(btnSeeList);

		tfTotalAMount.setEditable(false);
		tfTotalPayed.setEditable(false);
		tfStdPayed.setEditable(false);
		tfStdTotal.setEditable(false);
		tfInvoice.setEditable(false);

		parentPanel.add(childPanel, "listBoardingFee");
	}

	public void addRowsAccount(List<String> accounts, boolean isDeleteAll) {
		if (isDeleteAll)
			deleteAllRowOf(tableListAccount);
		DefaultTableModel model = (DefaultTableModel) tableListAccount.getModel();
		int count = model.getRowCount() + 1;
		for (String username : accounts) {
			User user = adminController.getUserByUsername(username);
			String position = user.getPosition();
			String name = "ADMIN";
			String id = "ADMIN";
			if (!position.equals("Admin")) {
				name = adminController.getNameUser(position, username);
				id = adminController.getUserId(position, username);
			}
			String password = "";
			for (int i = 0; i < user.getPassword().length(); i++)
				password += "*";
			model.addRow(new Object[] { count++, username, password, position, id, name, user.getLastLogin() });
		}
		amountOfAc.setText("Tổng cộng: " + adminController.getSizeOfUser() + " tài khoản");
	}

	public void manageAccount(PanelRound parentPanel, PanelRound childPanel) {
		childPanel.setBground(RIGHT_COLOR);
		childPanel.setRadius(40);
		childPanel.setBorderWidth(2);
		childPanel.setBorderColor(Color.BLACK);
		childPanel.setLayout(null);

		JLabel lbTitle = new JLabel("QUẢN LÝ TÀI KHOẢN");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lbTitle.setBounds(PADDING, PADDING - 10, parentPanel.getWidth() - 2 * PADDING, 40);
		lbTitle.setForeground(FOREGROUND);
		childPanel.add(lbTitle);

		boolean[] editableColumnsListAccount = { false, false, false, false, false, false, false, true };
		NonEditableTableModel modelListAccount = new NonEditableTableModel(new Object[][] {},
				new String[] { "STT", "Tài khoản", "Mật khẩu", "Vị trí", "Mã", "Tên", "Lần cuối đăng nhập", "" },
				editableColumnsListAccount);

		tableListAccount = new MyTable();
		tableListAccount.setSelectionBackground(TABLEHEADER_COLOR);
		tableListAccount.setGridColor(new Color(0, 0, 0));
		tableListAccount.setRowHeight(30);
		tableListAccount.setFont(ROW_TABLE_FONT);
		tableListAccount.setModel(modelListAccount);
		tableListAccount.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableListAccount.getColumnModel().getColumn(1).setPreferredWidth(75);
		tableListAccount.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableListAccount.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableListAccount.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableListAccount.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableListAccount.getColumnModel().getColumn(6).setPreferredWidth(60);
		tableListAccount.getColumnModel().getColumn(7).setPreferredWidth(60);

		tableListAccount.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableAccountController = new TableController(tableListAccount, this, "User");
		tableListAccount.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
		tableListAccount.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(tableAccountController));

		JTableHeader headerAccount = tableListAccount.getTableHeader();
		headerAccount.setFont(COLUMN_TABLE_FONT);
		headerAccount.setLayout(null);
		headerAccount.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerAccount.setBackground(TABLEHEADER_COLOR);

		JScrollPane scrollAccount = new JScrollPane(tableListAccount);
		scrollAccount.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollAccount.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollAccount.setBounds(30, 120, 964, 500);
		childPanel.add(scrollAccount);

		tfsearchAccount = new JTextField();
		tfsearchAccount.setBounds(640, 80, 200, 30);
		tfsearchAccount.setFont(ROW_TABLE_FONT);
		tfsearchAccount.setBackground(SEARCH_COLOR);
		tfsearchAccount.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		childPanel.add(tfsearchAccount);

		btnSearchAccount = new MyButton();
		btnSearchAccount.setFont(LABEL_CHILD_PANEL);
		btnSearchAccount.setText("Tìm kiếm");
		btnSearchAccount.setFocusPainted(false);
		btnSearchAccount.setColor(SEARCH_COLOR);
		btnSearchAccount.setColorOver(LEFT_COLOR);
		btnSearchAccount.setBorderColor(Color.black);
		btnSearchAccount.addActionListener(adminController);
		btnSearchAccount.setBounds(tfsearchStudent.getX() - 98, tfsearchStudent.getY(), 100,
				tfsearchStudent.getHeight());
		childPanel.add(btnSearchAccount);

		searchAccount = new JComboBox<String>();
		searchAccount.addItem("Tài khoản");
		searchAccount.setFont(ROW_TABLE_FONT);
		searchAccount.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		searchAccount.setFocusable(true);
		searchAccount.addActionListener(adminController);
		searchAccount.setBounds(tfsearchAccount.getX() + tfsearchAccount.getWidth(), tfsearchAccount.getY(), 140,
				tfsearchAccount.getHeight());
		searchAccount.setBackground(SEARCH_COLOR);
		childPanel.add(searchAccount);

		comboSortAccount = new JComboBox<String>();
		comboSortAccount.addItem("Tài khoản");
		comboSortAccount.addItem("Lần cuối đăng nhập");
		comboSortAccount.setBackground(SEARCH_COLOR);
		comboSortAccount.setFont(ROW_TABLE_FONT);
		comboSortAccount.setBorder(searchstudents.getBorder());
		comboSortAccount.setBounds(124, 80, 180, 30);
		childPanel.add(comboSortAccount);

		MyButton btnSortAccount = new MyButton();
		btnSortAccount.setFont(LABEL_CHILD_PANEL);
		btnSortAccount.setText("Sắp xếp");
		btnSortAccount.setFocusPainted(false);
		btnSortAccount.setColor(SEARCH_COLOR);
		btnSortAccount.setColorOver(LEFT_COLOR);
		btnSortAccount.setBorderColor(Color.black);
		btnSortAccount.setBounds(comboSortAccount.getX() - 78, tfsearchAccount.getY(), 80, tfsearchAccount.getHeight());
		btnSortAccount.addActionListener(adminController);
		childPanel.add(btnSortAccount);

		grbtnSortAccount = new ButtonGroup();
		JRadioButton rdibtnUpAccount = new JRadioButton("↑");
		rdibtnUpAccount.setFont(new Font("Times New Roman", Font.BOLD, 25));
		rdibtnUpAccount.setBackground(RIGHT_COLOR);
		rdibtnUpAccount.setFocusPainted(false);
		rdibtnUpAccount.setBounds(comboSortAccount.getX() + comboSortAccount.getWidth() + 10, comboSortAccount.getY(),
				40, comboSortAccount.getHeight());
		rdibtnUpAccount.setSelected(true);
		grbtnSortAccount.add(rdibtnUpAccount);
		childPanel.add(rdibtnUpAccount);

		JRadioButton rdibtnDownAccount = new JRadioButton("↓");
		rdibtnDownAccount.setFont(new Font("Times New Roman", Font.BOLD, 25));
		rdibtnDownAccount.setBackground(RIGHT_COLOR);
		rdibtnDownAccount.setFocusPainted(false);
		rdibtnDownAccount.setBounds(rdibtnUpAccount.getX() + rdibtnUpAccount.getWidth(), comboSortAccount.getY(), 40,
				comboSortAccount.getHeight());
		grbtnSortAccount.add(rdibtnDownAccount);
		childPanel.add(rdibtnDownAccount);

		JRadioButton rdibtnNoneAccount = new JRadioButton("Không");
		rdibtnNoneAccount.setFont(LABEL_CHILD_PANEL);
		rdibtnNoneAccount.setBackground(RIGHT_COLOR);
		rdibtnNoneAccount.setFocusPainted(false);
		rdibtnNoneAccount.setSelected(true);
		rdibtnNoneAccount.setBounds(rdibtnDownAccount.getX() + rdibtnDownAccount.getWidth(), comboSortAccount.getY(),
				100, comboSortStudent.getHeight());
		grbtnSortAccount.add(rdibtnNoneAccount);
		childPanel.add(rdibtnNoneAccount);

		MyButton btnAddAccount = new MyButton();
		btnAddAccount.setForeground(BUTTON_FG_COLOR);
		btnAddAccount.setText("Thay đổi mật khẩu mặc định");
		btnAddAccount.setRadius(20);
		btnAddAccount.setFont(COLUMN_TABLE_FONT);
		btnAddAccount.setFocusPainted(false);
		btnAddAccount.setColorOver(BUTTON_OVER_COLOR);
		btnAddAccount.setColor(BUTTON_COLOR);
		btnAddAccount.setBorderColor(Color.BLACK);
		btnAddAccount.setBounds(710, 664, 264, 30);
		btnAddAccount.addActionListener(adminController);
		childPanel.add(btnAddAccount);

		btnSeeMoreAc = new MyButton();
		btnSeeMoreAc.setText("Xem thêm");
		btnSeeMoreAc.setRadius(20);
		btnSeeMoreAc.setForeground(BUTTON_FG_COLOR);
		btnSeeMoreAc.setFont(COLUMN_TABLE_FONT);
		btnSeeMoreAc.setFocusPainted(false);
		btnSeeMoreAc.setColorOver(BUTTON_OVER_COLOR);
		btnSeeMoreAc.setColor(BUTTON_COLOR);
		btnSeeMoreAc.setBorderColor(Color.BLACK);
		btnSeeMoreAc.setBounds(52, 664, 127, 30);
		btnSeeMoreAc.addActionListener(adminController);
		childPanel.add(btnSeeMoreAc);

		btnSeeAllAc = new MyButton();
		btnSeeAllAc.setText("Xem tất cả");
		btnSeeAllAc.setRadius(20);
		btnSeeAllAc.setForeground(BUTTON_FG_COLOR);
		btnSeeAllAc.setFont(COLUMN_TABLE_FONT);
		btnSeeAllAc.setFocusPainted(false);
		btnSeeAllAc.setColorOver(BUTTON_OVER_COLOR);
		btnSeeAllAc.setColor(BUTTON_COLOR);
		btnSeeAllAc.setBorderColor(Color.BLACK);
		btnSeeAllAc.addActionListener(adminController);
		btnSeeAllAc.setBounds(189, 664, 127, 30);
		childPanel.add(btnSeeAllAc);

		amountOfAc = new JLabel();
		amountOfAc.setFont(LABEL_CHILD_PANEL);
		amountOfAc.setForeground(FOREGROUND);
		amountOfAc.setBounds(800, scrollAccount.getY() + scrollAccount.getHeight(), 320, 30);
		amountOfAc.setText("Tổng cộng: " + 0 + " tài khoản đang hoạt động");
		childPanel.add(amountOfAc);

		parentPanel.add(childPanel, "listAccount");
	}

	public void setCardRight(String name) {
		card_right.show(panel_right, name);
	}

	private void deleteAllRowOf(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
	}

	public JTextField getTfSearchOf(String object) {
		JTextField result = tfsearchStudent;
		switch (object) {
		case "Student":
			result = tfsearchStudent;
			break;
		case "Parents":
			result = tfsearchParents;
			break;
		case "Teacher":
			result = tfsearchTeacher;
			break;
		case "BoardingClass":
			result = tfsearchClass;
			break;
		case "Food":
			result = tfsearchFood;
			break;
		case "Invoice":
			result = tfsearchInvoice;
			break;
		case "Menu":
			result = tfsearchMenu;
			break;
		case "EatingHistory":
			result = tfsearchEHis;
			break;
		case "User":
			result = tfsearchAccount;
			break;
		}
		return result;
	}

	public String getTextSearchOf(String object) {
		String result = getTfSearchOf(object).getText();
		return result.trim();
	}

	public JComboBox<String> getSeletecdComboBoxOf(String object, boolean isSort) {
		JComboBox<String> cb = searchstudents;
		switch (object) {
		case "Student":
			if (isSort)
				cb = comboSortStudent;
			else
				cb = searchstudents;
			break;
		case "Parents":
			if (isSort)
				cb = comboSortParents;
			else
				cb = searchParents;
			break;
		case "Teacher":
			if (isSort)
				cb = comboSortTeacher;
			else
				cb = searchTeacher;
			break;
		case "BoardingClass":
			if (isSort)
				cb = comboSortClass;
			else
				cb = searchClass;
			break;
		case "Food":
			if (isSort)
				cb = comboSortFood;
			else
				cb = searchFood;
			break;
		case "Invoice":
			cb = searchInvoice;
			break;
		case "Menu":
			cb = searchMenus;
			break;
		case "EatingHistory":
			cb = searchEHis;
			break;
		case "User":
			if (isSort)
				cb = comboSortAccount;
			else
				cb = searchAccount;
			break;
		}
		return cb;
	}

	public String getColumnSearch(String object) {
		JComboBox<String> cb = getSeletecdComboBoxOf(object, false);
		String column = cb.getSelectedItem() + "";
		String kind = "";
		switch (column) {
		case "Mã học sinh":
			kind = "student_id";
			break;
		case "Họ và tên":
		case "Tên lớp":
		case "Tên món ăn":
			kind = "name";
			break;
		case "Địa chỉ":
			kind = "address";
			break;
		case "Năm sinh":
			kind = "YEAR(dateOfBirth)";
			break;
		case "Mã phụ huynh":
			kind = "parents_id";
			break;
		case "Mã giáo viên":
			kind = "teacher_id";
			break;
		case "Số điện thoại":
			kind = "phoneNumber";
			break;
		case "LBT":
		case "Lớp quản lí":
		case "Lớp":
			kind = "LBT";
			break;
		case "Mã lớp học":
			kind = "boardingClass_id";
			break;
		case "Phòng":
			kind = "room";
			break;
		case "Số chỗ ngủ":
			kind = "numberofbed";
			break;
		case "Mã món ăn":
			kind = "food_id";
			break;
		case "Loại":
			kind = "category";
			break;
		case "Mã hóa đơn":
			kind = "invoice_id";
			break;
		case "Tên học sinh":
			kind = "THS";
			break;
		case "Trạng thái":
			kind = "statusPayment";
			break;
		case "Ngày ăn":
			kind = "DAY(eating_day)";
			break;
		case "Mã ngày ăn":
			kind = "eatingHistory_id";
			break;
		case "Tài khoản":
			kind = "username";
			break;
		case "Mã thực đơn":
			kind = "menu_id";
			break;
		case "Món chính":
			kind = "MC";
			break;
		case "Món phụ":
			kind = "MP";
			break;
		}
		return kind;
	}

	public ButtonGroup getBtnGroup(String object) {
		ButtonGroup btnGroup = grbtnSortStudent;
		switch (object) {
		case "Student":
			btnGroup = grbtnSortStudent;
			break;
		case "Parents":
			btnGroup = grbtnSortParents;
			break;
		case "Teacher":
			btnGroup = grbtnSortTeacher;
			break;
		case "BoardingClass":
			btnGroup = grbtnSortClass;
			break;
		case "Food":
			btnGroup = grbtnSortFood;
			break;
		case "User":
			btnGroup = grbtnSortAccount;
			break;
		}
		return btnGroup;
	}

	public void setNoneSortOf(String object) {
		ButtonGroup btnGroup = getBtnGroup(object);
		Enumeration<AbstractButton> radios = btnGroup.getElements();
		while (radios.hasMoreElements()) {
			JRadioButton tmp = (JRadioButton) radios.nextElement();
			if (tmp.isSelected()) {
				tmp.setSelected(false);
			}
			if (tmp.getText().equals("Không"))
				tmp.setSelected(true);
		}
	}

	public byte getDirectionSortOf(String object) {
		byte direction = -1;
		ButtonGroup btnGroup = getBtnGroup(object);
		Enumeration<AbstractButton> radios = btnGroup.getElements();
		while (radios.hasMoreElements()) {
			JRadioButton tmp = (JRadioButton) radios.nextElement();
			if (tmp.isSelected()) {
				String dr = tmp.getText();
				if (dr.equals("↑"))
					direction = 1;
				else if (dr.equals("↓"))
					direction = 2;
				else if (dr.equals("Không"))
					direction = 3;
				break;
			}
		}
		return direction;
	}

	public String getColumnSort(String object) {
		JComboBox<String> cbSort = getSeletecdComboBoxOf(object, true);
		String selection = cbSort.getSelectedItem() + "";
		String column = "";
		switch (selection) {
		case "Mã học sinh":
			column = "student_id";
			break;
		case "Họ và tên":
		case "Tên lớp":
		case "Tên món ăn":
			column = "name";
			break;
		case "Năm sinh":
			column = "YEAR(dateOfBirth)";
			break;
		case "Mã phụ huynh":
			column = "parents_id";
			break;
		case "Mã giáo viên":
			column = "teacher_id";
			break;
		case "Mã lớp học":
			column = "boardingClass_id";
			break;
		case "Phòng":
			column = "room";
			break;
		case "Số chỗ ngủ":
			column = "numberofbed";
			break;
		case "Số học sinh":
			column = "SHS";
			break;
		case "Loại":
			column = "category";
			break;
		case "Mã món ăn":
			column = "food_id";
			break;
		case "Số thực đơn có mặt":
			column = "STDCM";
			break;
		case "Tài khoản":
			column = "username";
			break;
		case "Lần cuối đăng nhập":
			column = "lastLogin";
			break;
		}
		return column;
	}

	public boolean isCritical() {
		return this.critical;
	}

	public void setCritical(boolean critical) {
		this.critical = critical;
	}

	public static void main(String[] args) {
		new AdminScreen();
	}
}