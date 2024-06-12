package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import controller.PersonDetailCTL;
import custom.MyButton;
import custom.MyTextField;
import custom.PanelRound;
import model.BoardingClass;
import model.CBBItem;
import model.Parents;
import model.Student;
import model.Teacher;

public class PersonDetail extends JFrame {
	private static final long serialVersionUID = 1L;

	private final int WIDTH = 700;
	private final int HEIGHT = 400;
	private final int PADDING = 20;
	private final int GAP_HEIGHT = 5;
	private final int GAP_WIDTH = 20;
	private final int HEIGHT_TEXT = 30;

	private final Color RIGHT_COLOR = new Color(242, 224, 191);
	private final Color LEFT_COLOR = new Color(244, 203, 198);
	private final Color NEED_EDIT_COLOR = new Color(255, 128, 0);
	private final Color ENABLE_TEXT = Color.black;
	private final Color COLOR1 = new Color(244, 203, 198);
	private final Color LABEL_COLOR = new Color(0, 0, 0);
	private final Color TEXT_COLOR = new Color(46, 81, 64);
	private final Color ERRORS = new Color(225, 0, 77);
	private final Color BUTTON_COLOR = new Color(0, 45, 97);

	private final Font LABEL_FONT = new Font("Times New Roman", Font.PLAIN, 20);
	private final Font TEXT_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18);
	private final Font BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 14);
	private final JPanel contentPanel = new JPanel();

	private String sourceImage;
	private String object;
	private boolean isNew;
	private boolean isEditable;
	private Student student;
	private Parents parents;
	private Teacher teacher;

	private ButtonGroup btnSex;
	private ButtonGroup btnSubMeal;

	private JRadioButton radioMale;
	private JRadioButton radioFemale;
	private JRadioButton radioYes;
	private JRadioButton radioNo;

	private MyTextField textName;
	private MyTextField textDay;
	private MyTextField textID;
	private MyTextField textMonth;
	private MyTextField textYear;
	private MyTextField textWeight;
	private MyTextField textHeight;
	private MyTextField textAddress;
	private MyTextField textEmail;
	private MyTextField textTel;
	private MyTextField textAbsence;

	private JComboBox<CBBItem> cbParentsID;
	private JComboBox<CBBItem> cbClassID;

	private JLabel lblAddress;
	private JLabel lbId;
	private JLabel lblIDPR;
	private JLabel lblClass;
	private JLabel lblWeight;
	private JLabel lblUnitWeight;
	private JLabel lblHeight;
	private JLabel lblUnitHeight;
	private JLabel lblSubMeal;
	private JLabel lblTel;
	private JLabel lblEmail;

	private PersonDetailCTL dtpc;
	private PanelRound panelUp;
	private PanelRound panelUnder;

	private JButton avatar;

	public PersonDetail(String object) {
		dtpc = new PersonDetailCTL(this);
		this.sourceImage = "";
		this.object = object;
		this.isNew = true;
		this.isEditable = true;
		initComponents();
	}

	public PersonDetail(int id, String object, boolean isEditable) {
		dtpc = new PersonDetailCTL(this);
		if (object.equals("Student")) {
			this.student = dtpc.selectStudentById(id);
		} else if (object.equals("Parents")) {
			this.parents = dtpc.selectParentsById(id);
		} else if (object.equals("Teacher")) {
			this.teacher = dtpc.selectTeacherById(id);
		}
		this.sourceImage = System.getProperty("user.dir") + "/avatar/" + object + "/" + id + ".jpg";
		this.object = object;
		this.isNew = false;
		this.isEditable = isEditable;
		initComponents();
	}

	public void initComponents() {
		setFocusableWindowState(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, WIDTH, HEIGHT);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setBackground(BUTTON_COLOR);
		contentPanel.setLayout(null);

		avatar = new JButton("+");
		avatar.setBackground(RIGHT_COLOR);
		avatar.setForeground(NEED_EDIT_COLOR);
		avatar.setOpaque(true);
		avatar.setFocusPainted(false);
		avatar.setFont(new Font("Berlin Sans FB", Font.BOLD, 60));
		avatar.setBounds(PADDING, PADDING, 120, 160);
		avatar.addActionListener(dtpc);
		if (!isNew) {
			File file = new File(sourceImage);
			if (file.exists()) {
				avatar.setText("");
				avatar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(sourceImage)
						.getScaledInstance(avatar.getWidth(), avatar.getHeight(), Image.SCALE_AREA_AVERAGING)));

			}
		}
		contentPanel.add(avatar);

		panelUp = new PanelRound();
		panelUp.setLocation(avatar.getX() + avatar.getWidth() + GAP_WIDTH, avatar.getY());
		panelUp.setSize(WIDTH - panelUp.getX() - PADDING, avatar.getHeight());
		panelUp.setRadius(20);
		panelUp.setBground(COLOR1);
		contentPanel.add(panelUp);
		panelUp.setLayout(null);

		lbId = new JLabel();
		lbId.setHorizontalAlignment(SwingConstants.RIGHT);
		lbId.setFont(LABEL_FONT);
		lbId.setBounds(0, PADDING, 130, HEIGHT_TEXT);
		panelUp.add(lbId);

		JLabel lblName = new JLabel("Họ và tên:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(LABEL_FONT);
		lblName.setForeground(LABEL_COLOR);
		lblName.setBounds(lbId.getX(), lbId.getY() + HEIGHT_TEXT + GAP_HEIGHT, lbId.getWidth(), HEIGHT_TEXT);
		panelUp.add(lblName);

		JLabel lblBirthday = new JLabel("Ngày sinh:");
		lblBirthday.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthday.setFont(LABEL_FONT);
		lblName.setForeground(LABEL_COLOR);
		lblBirthday.setBounds(lblName.getX(), lblName.getY() + HEIGHT_TEXT + GAP_HEIGHT, lbId.getWidth(), HEIGHT_TEXT);
		panelUp.add(lblBirthday);

		JLabel lblSex = new JLabel("Giới tính:");
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setFont(LABEL_FONT);
		lblSex.setForeground(LABEL_COLOR);
		lblSex.setBounds(lblName.getX(), lblBirthday.getY() + HEIGHT_TEXT + GAP_HEIGHT, lbId.getWidth(), HEIGHT_TEXT);
		panelUp.add(lblSex);

		textID = new MyTextField();
		textID.setForeground(TEXT_COLOR);
		textID.setLocation(lbId.getX() + lbId.getWidth() + GAP_WIDTH, lbId.getY());
		textID.setSize(panelUp.getWidth() - textID.getX() - PADDING, HEIGHT_TEXT);
		textID.setBackground(LEFT_COLOR);
		textID.setLineColor(ENABLE_TEXT);
		textID.setFont(TEXT_FONT);
		textID.setEditable(false);
		panelUp.add(textID);

		textName = new MyTextField();
		textName.setLineColor(NEED_EDIT_COLOR);
		textName.setForeground(TEXT_COLOR);
		textName.setFont(TEXT_FONT);
		textName.setBackground(LEFT_COLOR);
		textName.setBounds(textID.getX(), lblName.getY(), textID.getWidth(), HEIGHT_TEXT);
		panelUp.add(textName);

		textDay = new MyTextField();
		textDay.setHorizontalAlignment(SwingConstants.RIGHT);
		textDay.setLineColor(NEED_EDIT_COLOR);
		textDay.setForeground(TEXT_COLOR);
		textDay.setFont(TEXT_FONT);
		textDay.setBackground(LEFT_COLOR);
		textDay.setBounds(textID.getX(), lblBirthday.getY(), 22, HEIGHT_TEXT);
		panelUp.add(textDay);

		JLabel lblSeperate = new JLabel("/");
		lblSeperate.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeperate.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblSeperate.setBounds(textDay.getWidth() + textDay.getX(), textDay.getY(), 25, HEIGHT_TEXT);
		panelUp.add(lblSeperate);

		textMonth = new MyTextField();
		textMonth.setHorizontalAlignment(SwingConstants.RIGHT);
		textMonth.setLineColor(NEED_EDIT_COLOR);
		textMonth.setForeground(TEXT_COLOR);
		textMonth.setFont(TEXT_FONT);
		textMonth.setBackground(LEFT_COLOR);
		textMonth.setBounds(textDay.getX() + textDay.getWidth() + 10, lblBirthday.getY(), textDay.getWidth(),
				HEIGHT_TEXT);
		panelUp.add(textMonth);

		JLabel lblSeperate_1 = new JLabel("/");
		lblSeperate_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeperate_1.setFont(lblSeperate.getFont());
		lblSeperate_1.setBounds(textMonth.getWidth() + textMonth.getX(), textMonth.getY(), 25, HEIGHT_TEXT);
		panelUp.add(lblSeperate_1);

		textYear = new MyTextField();
		textYear.setLineColor(NEED_EDIT_COLOR);
		textYear.setForeground(TEXT_COLOR);
		textYear.setFont(TEXT_FONT);
		textYear.setBackground(LEFT_COLOR);
		textYear.setBounds(textMonth.getX() + textMonth.getWidth() + 10, lblBirthday.getY(), textDay.getWidth() * 2,
				HEIGHT_TEXT);
		panelUp.add(textYear);

		btnSex = new ButtonGroup();
		radioMale = new JRadioButton("Nam");
		btnSex.add(radioMale);
		radioMale.setBackground(COLOR1);
		radioMale.setForeground(TEXT_COLOR);
		radioMale.setFont(TEXT_FONT);
		radioMale.setFocusPainted(false);
		radioMale.setBounds(textID.getX(), lblSex.getY(), 80, HEIGHT_TEXT);
		panelUp.add(radioMale);

		radioFemale = new JRadioButton("Nữ");
		btnSex.add(radioFemale);
		radioFemale.setBackground(COLOR1);
		radioFemale.setForeground(TEXT_COLOR);
		radioFemale.setFont(TEXT_FONT);
		radioFemale.setFocusPainted(false);
		radioFemale.setBounds(radioMale.getX() + radioMale.getWidth(), lblSex.getY(), 100, HEIGHT_TEXT);
		panelUp.add(radioFemale);

		panelUnder = new PanelRound();
		panelUnder.setLocation(PADDING, avatar.getY() + avatar.getHeight() + PADDING);
		panelUnder.setRadius(20);
		panelUnder.setBground(COLOR1);
		panelUnder.setBorderWidth(2);
		panelUnder.setLayout(null);
		panelUnder.setSize(WIDTH - 2 * PADDING, HEIGHT - panelUnder.getY() - 3 * PADDING);
		contentPanel.add(panelUnder);

		lblAddress = new JLabel("Địa chỉ:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(LABEL_FONT);
		lblAddress.setForeground(LABEL_COLOR);
		lblAddress.setBounds(0, PADDING, 130, HEIGHT_TEXT);
		panelUnder.add(lblAddress);

		textAddress = new MyTextField();
		textAddress.setLineColor(NEED_EDIT_COLOR);
		textAddress.setForeground(TEXT_COLOR);
		textAddress.setFont(TEXT_FONT);
		textAddress.setBackground(LEFT_COLOR);
		textAddress.setLocation(lblAddress.getX() + lblAddress.getWidth() + 20, lblAddress.getY());
		textAddress.setSize(panelUnder.getWidth() - PADDING - textAddress.getX(), lblAddress.getHeight());
		panelUnder.add(textAddress);

		MyButton btnCancel = new MyButton();
		btnCancel.setFocusPainted(false);
		btnCancel.setText("Đóng");
		btnCancel.setRadius(20);
		btnCancel.setColor(COLOR1);
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setBorderColor(LABEL_COLOR);
		btnCancel.setForeground(LABEL_COLOR);
		btnCancel.setColorOver(new Color(222, 215, 165));
		btnCancel.setSize(120, HEIGHT_TEXT);
		btnCancel.setLocation(WIDTH - PADDING - btnCancel.getWidth(), HEIGHT - PADDING - btnCancel.getHeight());
		btnCancel.addActionListener(dtpc);
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
		btnOK.addActionListener(dtpc);
		btnOK.setBounds(btnCancel.getX() - btnCancel.getWidth() - 10, btnCancel.getY(), btnCancel.getWidth(),
				btnCancel.getHeight());
		if (isEditable) {
			contentPanel.add(btnOK);
		}

		if (object.equals("Student"))
			infoStudent();
		else if (object.equals("Parents"))
			infoParents();
		else if (object.equals("Teacher"))
			infoTeacher();
		setVisible(true);
	}

	public void infoStudent() {
		lbId.setText("Mã học sinh:");

		lblIDPR = new JLabel("Mã phụ huynh:");
		lblIDPR.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIDPR.setFont(LABEL_FONT);
		lblIDPR.setBackground(LABEL_COLOR);
		lblIDPR.setBounds(lblAddress.getX(), lblAddress.getY() + lblAddress.getHeight() + GAP_HEIGHT,
				lblAddress.getWidth(), lblAddress.getHeight());
		panelUnder.add(lblIDPR);

		cbParentsID = new JComboBox<CBBItem>();
		cbParentsID.addItem(new CBBItem(0, "Không có"));
		List<CBBItem> cbbs = Parents.getIdAndName();
		for (CBBItem cbb : cbbs) {
			if (cbb.getValue() != 0)
				cbParentsID.addItem(cbb);
		}
		cbParentsID.setFont(TEXT_FONT);
		cbParentsID.setBounds(textAddress.getX(), lblIDPR.getY(), 250, HEIGHT_TEXT);
		panelUnder.add(cbParentsID);

		lblClass = new JLabel("Mã lớp:");
		lblClass.setHorizontalAlignment(SwingConstants.LEFT);
		lblClass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblClass.setBackground(Color.BLACK);
		lblClass.setBounds(cbParentsID.getX() + cbParentsID.getWidth() + GAP_WIDTH, lblIDPR.getY(), 69,
				lblIDPR.getHeight());
		panelUnder.add(lblClass);

		cbClassID = new JComboBox<CBBItem>();
		cbClassID.addItem(new CBBItem(0, "Không có"));
		for (CBBItem cbb : BoardingClass.getIdAndNameVacancy()) {
			if (cbb.getValue() != 0)
				cbClassID.addItem(cbb);
		}
		cbClassID.setFont(TEXT_FONT);
		cbClassID.setBounds(lblClass.getX() + lblClass.getWidth() + GAP_WIDTH, lblClass.getY(), 100,
				cbParentsID.getHeight());
		panelUnder.add(cbClassID);

		lblWeight = new JLabel("Cân nặng:");
		lblWeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeight.setFont(LABEL_FONT);
		lblWeight.setBackground(LEFT_COLOR);
		lblWeight.setBounds(lblAddress.getX(), lblIDPR.getHeight() + lblIDPR.getY() + GAP_HEIGHT, lblAddress.getWidth(),
				lblAddress.getHeight());
		panelUnder.add(lblWeight);

		textWeight = new MyTextField();
		textWeight.setHorizontalAlignment(SwingConstants.RIGHT);
		textWeight.setLineColor(NEED_EDIT_COLOR);
		textWeight.setForeground(TEXT_COLOR);
		textWeight.setFont(TEXT_FONT);
		textWeight.setBackground(LEFT_COLOR);
		textWeight.setBounds(textAddress.getX(), lblWeight.getY(), 44, lblWeight.getHeight());
		panelUnder.add(textWeight);

		lblUnitWeight = new JLabel("kg");
		lblUnitWeight.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnitWeight.setFont(LABEL_FONT);
		lblUnitWeight.setBounds(textWeight.getX() + textWeight.getWidth(), textWeight.getY(), 20,
				textWeight.getHeight());
		panelUnder.add(lblUnitWeight);

		lblHeight = new JLabel("Chiều cao:");
		lblHeight.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeight.setFont(LABEL_FONT);
		lblHeight.setBackground(Color.BLACK);
		lblHeight.setBounds(lblUnitWeight.getX() + lblUnitWeight.getWidth() + GAP_WIDTH, lblUnitWeight.getY(), 91,
				lblUnitWeight.getHeight());
		panelUnder.add(lblHeight);

		textHeight = new MyTextField();
		textHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		textHeight.setLineColor(NEED_EDIT_COLOR);
		textHeight.setForeground(TEXT_COLOR);
		textHeight.setFont(TEXT_FONT);
		textHeight.setBackground(LEFT_COLOR);
		textHeight.setBounds(lblHeight.getX() + lblHeight.getWidth() + GAP_WIDTH, lblHeight.getY(),
				textWeight.getWidth(), lblHeight.getHeight());
		panelUnder.add(textHeight);

		lblUnitHeight = new JLabel("m");
		lblUnitHeight.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnitHeight.setFont(LABEL_FONT);
		lblUnitHeight.setBounds(textHeight.getX() + textHeight.getWidth(), textHeight.getY(), 20,
				textHeight.getHeight());
		panelUnder.add(lblUnitHeight);

		lblSubMeal = new JLabel("Ăn phụ:");
		lblSubMeal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubMeal.setFont(LABEL_FONT);
		lblSubMeal.setBackground(LEFT_COLOR);
		lblSubMeal.setBounds(lblUnitHeight.getX() + lblUnitHeight.getWidth() + GAP_WIDTH, lblUnitHeight.getY(), 70,
				lblUnitHeight.getHeight());
		panelUnder.add(lblSubMeal);

		btnSubMeal = new ButtonGroup();
		radioYes = new JRadioButton("Có");
		btnSubMeal.add(radioYes);
		radioYes.setBackground(COLOR1);
		radioYes.setForeground(TEXT_COLOR);
		radioYes.setFont(TEXT_FONT);
		radioYes.setFocusPainted(false);
		radioYes.setBounds(lblSubMeal.getX() + lblSubMeal.getWidth(), lblSubMeal.getY(), 50, lblSubMeal.getHeight());
		panelUnder.add(radioYes);

		radioNo = new JRadioButton("Không");
		btnSubMeal.add(radioNo);
		radioNo.setBackground(COLOR1);
		radioNo.setForeground(TEXT_COLOR);
		radioNo.setFont(TEXT_FONT);
		radioNo.setFocusPainted(false);
		radioNo.setBounds(radioYes.getX() + radioYes.getWidth() + 20, radioYes.getY(), 80, radioYes.getHeight());
		panelUnder.add(radioNo);

		if (!isNew) {
			JLabel lblSBuiVng = new JLabel("Số buổi vắng:");
			lblSBuiVng.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSBuiVng.setFont(LABEL_FONT);
			lblSBuiVng.setBounds(278, 90, 130, 30);
			panelUp.add(lblSBuiVng);

			textAbsence = new MyTextField();
			textAbsence.setLineColor(new Color(255, 128, 0));
			textAbsence.setHorizontalAlignment(SwingConstants.CENTER);
			textAbsence.setForeground(ENABLE_TEXT);
			textAbsence.setFont(TEXT_FONT);
			textAbsence.setBackground(COLOR1);
			textAbsence.setBounds(418, 90, 44, 30);
			textAbsence.setEnabled(false);
			panelUp.add(textAbsence);
			int absence = dtpc.getAmountAbsence(student.getStudent_id());
			textAbsence.setText(absence + "");

			MyButton btnDetail = new MyButton();
			btnDetail.addActionListener(dtpc);
			btnDetail.setText("Chi tiết");
			btnDetail.setRadius(20);
			btnDetail.setFont(BUTTON_FONT);
			btnDetail.setForeground(Color.BLACK);
			btnDetail.setFocusPainted(false);
			btnDetail.setColorOver(COLOR1);
			btnDetail.setColor(RIGHT_COLOR);
			btnDetail.setBorderColor(Color.BLACK);
			btnDetail.setBounds(410, 127, 90, 30);
			if (absence > 0)
				panelUp.add(btnDetail);

			textID.setText(student.getStudent_id() + "");
			textName.setText(student.getName());
			LocalDate date = student.getDateOfBirth().toLocalDate();
			textDay.setText(date.getDayOfMonth() + "");
			textMonth.setText(date.getMonthValue() + "");
			textYear.setText(date.getYear() + "");
			if (student.getSex()) {
				radioMale.setSelected(true);
				if (!isEditable)
					radioFemale.setEnabled(false);
			}
			else {
				radioFemale.setSelected(true);
				if (!isEditable)
					radioMale.setEnabled(false);
			}
			textAddress.setText(student.getAddress());
			cbParentsID.setSelectedItem(new CBBItem(student.getParents_id(), ""));
			cbClassID.setSelectedItem(new CBBItem(student.getBoardingClass_id(), ""));
			textWeight.setText(((float) Math.round(student.getWeight() * 100) / 100) + "");
			textHeight.setText(student.getHeight() + "");
			if (student.isSubMeal()) {
				radioYes.setSelected(true);
				if (!isEditable)
					radioNo.setEnabled(false);
			}
			else {
				radioNo.setSelected(true);
				if (!isEditable)
					radioYes.setEnabled(false);
			}
		} else {
			int lastId = dtpc.getNewIdOf("Student");
			textID.setText(lastId + "");
		}

		if (!isEditable) {
			textName.setLineColor(ENABLE_TEXT);
			textDay.setLineColor(ENABLE_TEXT);
			textMonth.setLineColor(ENABLE_TEXT);
			textYear.setLineColor(ENABLE_TEXT);
			textAddress.setLineColor(ENABLE_TEXT);
			textHeight.setLineColor(ENABLE_TEXT);
			textWeight.setLineColor(ENABLE_TEXT);

			textName.setEditable(false);
			textDay.setEditable(false);
			textMonth.setEditable(false);
			textYear.setEditable(false);
			textAddress.setEditable(false);
			textHeight.setEditable(false);
			textWeight.setEditable(false);
			cbClassID.setEnabled(false);
			cbClassID.setFont(TEXT_FONT);
			cbParentsID.setEnabled(false);
		}
	}

	public void infoParents() {
		lbId.setText("Mã phụ huynh:");

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(LABEL_FONT);
		lblEmail.setBounds(0, lblAddress.getY() + HEIGHT_TEXT + GAP_HEIGHT, lblAddress.getWidth(), HEIGHT_TEXT);
		panelUnder.add(lblEmail);

		textEmail = new MyTextField();
		textEmail.setLineColor(NEED_EDIT_COLOR);
		textEmail.setForeground(TEXT_COLOR);
		textEmail.setFont(TEXT_FONT);
		textEmail.setBackground(COLOR1);
		textEmail.setBounds(textAddress.getX(), lblEmail.getY(), textAddress.getWidth(), HEIGHT_TEXT);
		panelUnder.add(textEmail);

		lblTel = new JLabel("Số điện thoại:");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setForeground(Color.BLACK);
		lblTel.setFont(LABEL_FONT);
		lblTel.setBounds(0, lblEmail.getY() + HEIGHT_TEXT + GAP_HEIGHT, lblEmail.getWidth(), HEIGHT_TEXT);
		panelUnder.add(lblTel);

		textTel = new MyTextField();
		textTel.setLineColor(NEED_EDIT_COLOR);
		textTel.setForeground(TEXT_COLOR);
		textTel.setFont(TEXT_FONT);
		textTel.setBackground(COLOR1);
		textTel.setBounds(textAddress.getX(), lblTel.getY(), textAddress.getWidth(), HEIGHT_TEXT);
		panelUnder.add(textTel);

		if (!isNew) {
			textID.setText(parents.getParents_id() + "");
			textName.setText(parents.getName());
			LocalDate date = parents.getDateOfBirth().toLocalDate();
			textDay.setText(date.getDayOfMonth() + "");
			textMonth.setText(date.getMonthValue() + "");
			textYear.setText(date.getYear() + "");
			if (parents.getSex()) {
				radioMale.setSelected(true);
				if (!isEditable)
					radioFemale.setEnabled(false);
			}
			else {
				radioFemale.setSelected(true);
				if (!isEditable)
					radioMale.setEnabled(false);
			}
			textAddress.setText(parents.getAddress());
			textEmail.setText(parents.getEmail());
			textTel.setText(parents.getPhoneNumber());
		} else {
			int lastId = dtpc.getNewIdOf("Parents");
			textID.setText(lastId + "");
		}

		if (!isEditable) {
			textName.setLineColor(ENABLE_TEXT);
			textDay.setLineColor(ENABLE_TEXT);
			textMonth.setLineColor(ENABLE_TEXT);
			textYear.setLineColor(ENABLE_TEXT);
			textAddress.setLineColor(ENABLE_TEXT);
			textEmail.setLineColor(ENABLE_TEXT);
			textTel.setLineColor(ENABLE_TEXT);

			textName.setEditable(false);
			textDay.setEditable(false);
			textMonth.setEditable(false);
			textYear.setEditable(false);
			textAddress.setEditable(false);
			textEmail.setEditable(false);
			textTel.setEditable(false);
		}
	}

	public void infoTeacher() {
		lbId.setText("Mã giáo viên:");

		lblTel = new JLabel("Số điện thoại:");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setForeground(Color.BLACK);
		lblTel.setFont(LABEL_FONT);
		lblTel.setBounds(0, lblAddress.getY() + HEIGHT_TEXT + GAP_HEIGHT, lblAddress.getWidth(), HEIGHT_TEXT);
		panelUnder.add(lblTel);

		textTel = new MyTextField();
		textTel.setLineColor(NEED_EDIT_COLOR);
		textTel.setForeground(TEXT_COLOR);
		textTel.setFont(TEXT_FONT);
		textTel.setBackground(COLOR1);
		textTel.setBounds(textAddress.getX(), lblTel.getY(), 200, HEIGHT_TEXT);
		panelUnder.add(textTel);

		lblClass = new JLabel("Mã lớp:");
		lblClass.setHorizontalAlignment(SwingConstants.LEFT);
		lblClass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblClass.setBackground(Color.BLACK);
		lblClass.setBounds(textTel.getX() + textTel.getWidth() + GAP_WIDTH, textTel.getY(), 69, HEIGHT_TEXT);
		panelUnder.add(lblClass);

		cbClassID = new JComboBox<CBBItem>();
		cbClassID.addItem(new CBBItem(0, "Không có"));
		for (CBBItem cbb : BoardingClass.getIdAndNameNonTeacher()) {
			if (cbb.getValue() != 0)
				cbClassID.addItem(cbb);
		}
		if (teacher != null) {
			System.out.println("1");
			if (teacher.getBoardingClass_id() != 0) {
				BoardingClass bdc = dtpc.getBoardingClassByID(teacher.getBoardingClass_id());
				cbClassID.addItem(new CBBItem(bdc.getBoardingClass_id(), bdc.getName()));
			}
		}
		cbClassID.setFont(TEXT_FONT);
		cbClassID.setLocation(lblClass.getX() + lblClass.getWidth() + GAP_WIDTH, lblClass.getY());
		cbClassID.setSize(panelUnder.getWidth() - PADDING - cbClassID.getX(), HEIGHT_TEXT);
		panelUnder.add(cbClassID);

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(LABEL_FONT);
		lblEmail.setBounds(0, lblTel.getY() + HEIGHT_TEXT + GAP_HEIGHT, lblTel.getWidth(), HEIGHT_TEXT);
		panelUnder.add(lblEmail);

		textEmail = new MyTextField();
		textEmail.setLineColor(NEED_EDIT_COLOR);
		textEmail.setForeground(TEXT_COLOR);
		textEmail.setFont(TEXT_FONT);
		textEmail.setBackground(COLOR1);
		textEmail.setBounds(textAddress.getX(), lblEmail.getY(), textAddress.getWidth(), HEIGHT_TEXT);
		panelUnder.add(textEmail);

		if (!isNew) {
			textID.setText(teacher.getTeacher_id() + "");
			textName.setText(teacher.getName());
			LocalDate date = teacher.getDateOfBirth().toLocalDate();
			textDay.setText(date.getDayOfMonth() + "");
			textMonth.setText(date.getMonthValue() + "");
			textYear.setText(date.getYear() + "");
			if (teacher.getSex()) {
				radioMale.setSelected(true);
				if (!isEditable)
					radioFemale.setEnabled(false);
			}
			else {
				radioFemale.setSelected(true);
				if (!isEditable)
					radioMale.setEnabled(false);
			}
			cbClassID.setSelectedItem(new CBBItem(teacher.getBoardingClass_id(), ""));
			textAddress.setText(teacher.getAddress());
			textEmail.setText(teacher.getEmail());
			textTel.setText(teacher.getPhoneNumber());
		} else {
			int lastId = dtpc.getNewIdOf("Teacher");
			textID.setText(lastId + "");
		}

		if (!isEditable) {
			textName.setLineColor(ENABLE_TEXT);
			textDay.setLineColor(ENABLE_TEXT);
			textMonth.setLineColor(ENABLE_TEXT);
			textYear.setLineColor(ENABLE_TEXT);
			textAddress.setLineColor(ENABLE_TEXT);
			textEmail.setLineColor(ENABLE_TEXT);
			textTel.setLineColor(ENABLE_TEXT);
			cbClassID.setEnabled(false);
			cbClassID.setFont(TEXT_FONT);

			textName.setEditable(false);
			textDay.setEditable(false);
			textMonth.setEditable(false);
			textYear.setEditable(false);
			textAddress.setEditable(false);
			textEmail.setEditable(false);
			textTel.setEditable(false);
		}
	}

	public void setAvatar(String path) {
		avatar.setText("");
		avatar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(path).getScaledInstance(avatar.getWidth(),
				avatar.getHeight(), Image.SCALE_AREA_AVERAGING)));
	}

	public String getId() {
		return textID.getText();
	}

	public String getObject() {
		return this.object;
	}

	public boolean getIsEditable() {
		return this.isEditable;
	}

	public boolean getIsNew() {
		return this.isNew;
	}

	public Student getStudent() {
		int stundent_id = Integer.parseInt(textID.getText());
		String name = textName.getText();
		boolean sex = radioMale.isSelected();
		boolean subMeal = radioYes.isSelected();
		String address = textAddress.getText();
		float weight = Float.parseFloat(textWeight.getText());
		float height = Float.parseFloat(textHeight.getText());
		int day = Integer.parseInt(textDay.getText());
		int month = Integer.parseInt(textMonth.getText());
		int year = Integer.parseInt(textYear.getText());
		Date dayOfBirth = Date.valueOf(LocalDate.of(year, month, day));
		int parents_id = ((CBBItem) cbParentsID.getSelectedItem()).getValue();
		int boardingClass_id = ((CBBItem) cbClassID.getSelectedItem()).getValue();
		return new Student(name, dayOfBirth, address, sex, stundent_id, weight, height, parents_id, boardingClass_id,
				subMeal, null, null);
	}

	public Parents getParents() {
		int parents_id = Integer.parseInt(textID.getText());
		String name = textName.getText();
		boolean sex = radioMale.isSelected();
		String address = textAddress.getText();
		int day = Integer.parseInt(textDay.getText());
		int month = Integer.parseInt(textMonth.getText());
		int year = Integer.parseInt(textYear.getText());
		Date dayOfBirth = Date.valueOf(LocalDate.of(year, month, day));
		String email = textEmail.getText();
		String numberPhone = textTel.getText();
		return new Parents(name, dayOfBirth, address, sex, parents_id, numberPhone, email, null);
	}

	public Teacher getTeacher() {
		int teacher_id = Integer.parseInt(textID.getText());
		String name = textName.getText();
		boolean sex = radioMale.isSelected();
		String address = textAddress.getText();
		int day = Integer.parseInt(textDay.getText());
		int month = Integer.parseInt(textMonth.getText());
		int year = Integer.parseInt(textYear.getText());
		Date dayOfBirth = Date.valueOf(LocalDate.of(year, month, day));
		String email = textEmail.getText();
		String numberPhone = textTel.getText();
		int boardingClass_id = ((CBBItem) cbClassID.getSelectedItem()).getValue();
		return new Teacher(name, dayOfBirth, address, sex, teacher_id, numberPhone, email, boardingClass_id);
	}

	public static boolean hasSpecialCharacter(String str, String find) {
		Pattern pattern = Pattern.compile(find);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	public static boolean checkDate(int day, int month, int year) {
		if (((year < 1) || (month < 1) || (month > 12) || (day < 1) || (year > LocalDate.now().getYear())))
			return false;
		int[] dayOfMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (year % 4 == 0)
			dayOfMonth[2] = 29;
		return day <= dayOfMonth[month];
	}

	public String check() {
		String result = "";

		// NAME
		if (textName.getText().trim().equals("")) {
			result += "1 ";
			textName.setBackground(ERRORS);
		} else if (hasSpecialCharacter(textName.getText(), "[!@#%^.*()`~?/><,_+=|{}\\[\\]\'\"\\\\-]")) {
			result += "2 ";
			textName.setBackground(ERRORS);
		} else {
			textName.setBackground(COLOR1);
		}

		// DAY
		if (textDay.getText().equals("")) {
			result += "3 ";
			textDay.setBackground(ERRORS);
		} else if (!hasSpecialCharacter(textDay.getText(), "^[0-9]+$")) {
			result += "4 ";
			textDay.setBackground(ERRORS);
		} else {
			textDay.setBackground(COLOR1);
		}

		// MONTH
		if (textMonth.getText().equals("")) {
			result += "5 ";
			textMonth.setBackground(ERRORS);
		} else if (!hasSpecialCharacter(textMonth.getText(), "^[0-9]+$")) {
			result += "6 ";
			textMonth.setBackground(ERRORS);
		} else {
			textMonth.setBackground(COLOR1);
		}

		// YEAR
		if (textYear.getText().equals("")) {
			result += "7 ";
			textYear.setBackground(ERRORS);
		} else if (!hasSpecialCharacter(textYear.getText(), "^[0-9]+$")) {
			result += "8 ";
			textYear.setBackground(ERRORS);
		} else {
			textYear.setBackground(COLOR1);
		}

		// CHECKDATE
		if (!textDay.getText().equals("") && !textMonth.getText().equals("") && !textYear.getText().equals("")
				&& !checkDate(Integer.parseInt(textDay.getText()), Integer.parseInt(textMonth.getText()),
						Integer.parseInt(textYear.getText()))) {
			result += "9 ";
			textYear.setBackground(ERRORS);
			textMonth.setBackground(ERRORS);
			textDay.setBackground(ERRORS);
		}

		// SEX
		ButtonModel model = btnSex.getSelection();
		if (model == null) {
			result += "10 ";
			radioFemale.setBackground(ERRORS);
			radioMale.setBackground(ERRORS);
		} else {
			radioFemale.setBackground(COLOR1);
			radioMale.setBackground(COLOR1);
		}

		// ADDRESS
		if (textAddress.getText().equals("")) {
			result += "11 ";
			textAddress.setBackground(ERRORS);
		} else {
			textAddress.setBackground(COLOR1);
		}

		// STUDENT:
		if (object.equals("Student")) {
			// Weight
			long count = textWeight.getText().chars().filter(ch -> ch == '.').count();
			if (count > 1 || !hasSpecialCharacter(textWeight.getText(), "^[.0-9]+$")) {
				result += "14 ";
				textWeight.setBackground(ERRORS);
			} else {
				textWeight.setBackground(COLOR1);
			}

			// Height
			count = textHeight.getText().chars().filter(ch -> ch == '.').count();
			if (count > 1 || !hasSpecialCharacter(textHeight.getText(), "^[.0-9]+$")) {
				result += "15 ";
				textHeight.setBackground(ERRORS);
			} else {
				textHeight.setBackground(COLOR1);
			}

			// Submeal
			model = btnSubMeal.getSelection();
			if (model == null) {
				result += "16 ";
				radioYes.setBackground(ERRORS);
				radioNo.setBackground(ERRORS);
			} else {
				radioYes.setBackground(COLOR1);
				radioNo.setBackground(COLOR1);
			}
		} else if (object.equals("Parents")) {
			// TEL
			if (textTel.getText().trim().equals("")) {
				result += "18 ";
				textTel.setBackground(ERRORS);
			} else if (!hasSpecialCharacter(textTel.getText(), "^[0-9]+$") || textTel.getText().length() != 10) {
				result += "19 ";
				textTel.setBackground(ERRORS);
			} else {
				textTel.setBackground(COLOR1);
			}
		} else if (object.equals("Teacher")) {
			// GMAIL
			if (textEmail.getText().trim().equals("")) {
				result += "17 ";
				textEmail.setBackground(ERRORS);
			} else {
				textEmail.setBackground(COLOR1);
			}
			// TEL
			if (textTel.getText().equals("")) {
				result += "18 ";
				textTel.setBackground(ERRORS);
			} else if (!hasSpecialCharacter(textTel.getText(), "^[0-9]+$") || textTel.getText().length() != 10) {
				result += "19 ";
				textTel.setBackground(ERRORS);
			} else {
				textTel.setBackground(COLOR1);
			}
		}
		return result;
	}

	public String getPhoneNumber() {
		return this.textTel.getText();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new PersonDetail(1, "Student", true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
