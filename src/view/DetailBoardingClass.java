package view;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;

import custom.MyButton;
import custom.MyTable;
import custom.MyTextField;
import model.BoardingClass;
import model.Student;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.DetailClassCTL;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;

public class DetailBoardingClass extends JDialog {

	private static final long serialVersionUID = 1L;

	private final Color BACKGROUND = new Color(0, 45, 97);
	private final Color FOREGROUND = new Color(244, 203, 198);
	private final Color TF_FOREGROUND = new Color(242, 224, 191);
	private final Color LINE_TF_COLOR = Color.WHITE;

	private final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 30);
	private final Font LABEL_FONT = new Font("Times New Roman", Font.BOLD, 18);
	private final Font TF_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);

	private DetailClassCTL dbc;

	private MyTable table;
	public MyTextField tfTeacher;

	private BoardingClass boardingClass;
	private boolean isNew;
	private boolean isEditable;

	public JComboBox<String> comboBox;
	private JLabel lblClass_id;
	private MyTextField tfNumberBed;
	private JLabel lblSizeHs;
	private MyTextField tfNRoom;
	private MyTextField tfClass;

	public static void main(String[] args) {
		try {
			new DetailBoardingClass(1, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DetailBoardingClass() {
		dbc = new DetailClassCTL(this);
		this.isNew = true;
		this.isEditable = true;
		initComponents();
	}

	public DetailBoardingClass(int boardingClass_id, boolean isEditable) {
		dbc = new DetailClassCTL(this);
		boardingClass = dbc.selectBoardingClassById(boardingClass_id);
		this.isEditable = isEditable;
		this.isNew = false;
		initComponents();
	}

	public void initComponents() {
		setModal(true);
		setUndecorated(true);
		setSize(590, 570);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BACKGROUND);

		table = new MyTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setSelectionBackground(TF_FOREGROUND);
		table.setGridColor(Color.BLACK);
		table.setRowHeight(30);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã học sinh", "Họ và tên", "Phụ huynh" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JTableHeader headerClass = table.getTableHeader();
		headerClass.setFont(new Font("Times New Roman", Font.BOLD, 16));
		headerClass.setLayout(null);
		headerClass.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerClass.setBackground(TF_FOREGROUND);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 171, 566, 314);
		getContentPane().add(scrollPane);

		JLabel lblTeacher = new JLabel("Giáo viên phụ trách:");
		lblTeacher.setBounds(20, 82, 165, 25);
		lblTeacher.setForeground(FOREGROUND);
		lblTeacher.setFont(LABEL_FONT);
		getContentPane().add(lblTeacher);

		tfTeacher = new MyTextField();
		tfTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		tfTeacher.setBounds(198, 82, 204, 25);
		tfTeacher.setBackground(BACKGROUND);
		tfTeacher.setForeground(TF_FOREGROUND);
		tfTeacher.setLineColor(LINE_TF_COLOR);
		tfTeacher.setFont(TF_FONT);
		tfTeacher.setEditable(false);
		getContentPane().add(tfTeacher);
		tfTeacher.setColumns(10);

		JLabel lblM = new JLabel("Mã:");
		lblM.setForeground(FOREGROUND);
		lblM.setFont(LABEL_FONT);
		lblM.setBounds(420, 82, 48, 25);
		getContentPane().add(lblM);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(TF_FOREGROUND);
		comboBox.setFont(TF_FONT);
		comboBox.addActionListener(dbc);
		comboBox.setBounds(460, 82, 116, 25);
		getContentPane().add(comboBox);

		comboBox.addItem("Không có");
		List<String> teacher_ids = dbc.selecltTeacherIdNonClass();
		for (String id : teacher_ids) {
			if (!id.equals("0"))
				comboBox.addItem(id);
		}

		JLabel lblNumberBed = new JLabel("Số chỗ ngủ:");
		lblNumberBed.setForeground(FOREGROUND);
		lblNumberBed.setFont(LABEL_FONT);
		lblNumberBed.setBounds(20, 117, 103, 25);
		getContentPane().add(lblNumberBed);

		tfNumberBed = new MyTextField();
		tfNumberBed.setHorizontalAlignment(SwingConstants.CENTER);
		tfNumberBed.setLineColor(Color.WHITE);
		tfNumberBed.setForeground(TF_FOREGROUND);
		tfNumberBed.setFont(TF_FONT);
		tfNumberBed.setBackground(BACKGROUND);
		tfNumberBed.setBounds(121, 117, 48, 25);
		getContentPane().add(tfNumberBed);

		lblSizeHs = new JLabel("Tổng: 0 học sinh");
		lblSizeHs.setForeground(FOREGROUND);
		lblSizeHs.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblSizeHs.setBounds(433, 136, 143, 25);
		getContentPane().add(lblSizeHs);

		JLabel lblPhng = new JLabel("Phòng:");
		lblPhng.setForeground(FOREGROUND);
		lblPhng.setFont(LABEL_FONT);
		lblPhng.setBounds(208, 117, 68, 25);
		getContentPane().add(lblPhng);

		tfNRoom = new MyTextField();
		tfNRoom.setLineColor(LINE_TF_COLOR);
		tfNRoom.setHorizontalAlignment(SwingConstants.CENTER);
		tfNRoom.setForeground(TF_FOREGROUND);
		tfNRoom.setFont(TF_FONT);
		tfNRoom.setColumns(10);
		tfNRoom.setBackground(BACKGROUND);
		tfNRoom.setBounds(276, 117, 68, 25);
		getContentPane().add(tfNRoom);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		panel.setBounds(10, 509, 566, 4);
		getContentPane().add(panel);

		MyButton btnClose = new MyButton();
		btnClose.setRadius(8);
		btnClose.setBounds(460, 523, 103, 30);
		btnClose.setText("Đóng");
		btnClose.setColor(TF_FOREGROUND);
		btnClose.setForeground(BACKGROUND);
		btnClose.setFont(LABEL_FONT);
		btnClose.setColorOver(new Color(230, 230, 230));
		btnClose.setFocusPainted(false);
		btnClose.setBorderColor(Color.black);
		btnClose.addActionListener(dbc);
		getContentPane().add(btnClose);

		MyButton mbtnLu = new MyButton();
		mbtnLu.setText("Lưu");
		mbtnLu.setRadius(8);
		mbtnLu.setForeground(new Color(0, 45, 97));
		mbtnLu.setFont(LABEL_FONT);
		mbtnLu.setFocusPainted(false);
		mbtnLu.setColorOver(new Color(230, 230, 230));
		mbtnLu.setColor(new Color(242, 224, 191));
		mbtnLu.setBorderColor(Color.BLACK);
		mbtnLu.setBounds(347, 523, 103, 30);
		mbtnLu.addActionListener(dbc);

		JPanel header = new JPanel();
		header.setBackground(BACKGROUND);
		header.setBounds(10, 10, 566, 62);
		getContentPane().add(header);
		header.setLayout(null);

		lblClass_id = new JLabel("Mã lớp: " + dbc.getNewIdBoardingClass());
		lblClass_id.setBounds(473, 47, 93, 15);
		header.add(lblClass_id);
		lblClass_id.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblClass_id.setForeground(FOREGROUND);

		JLabel lblName = new JLabel("LỚP: ");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(TITLE_FONT);
		lblName.setForeground(new Color(255, 255, 0));
		lblName.setBounds(45, 0, 213, 62);
		header.add(lblName);

		tfClass = new MyTextField();
		tfClass.setHorizontalAlignment(SwingConstants.CENTER);
		tfClass.setBounds(268, 11, 140, 42);
		tfClass.setBackground(BACKGROUND);
		tfClass.setFont(TITLE_FONT);
		tfClass.setLineColor(TF_FOREGROUND);
		tfClass.setForeground(new Color(255, 255, 0));
		header.add(tfClass);

		if (boardingClass != null) {
			this.iniInforClass();
			getContentPane().add(mbtnLu);
		}

		if (isEditable || isNew) {
			getContentPane().add(mbtnLu);
		}

		if (!isEditable) {
			tfClass.setEditable(false);
			tfNRoom.setEditable(false);
			tfNumberBed.setEditable(false);
			comboBox.setEnabled(false);
		}
		setVisible(true);
	}

	public void iniInforClass() {
		this.tfClass.setText(this.boardingClass.getName());
		this.tfTeacher.setText("");
		if (boardingClass.getTeacher_id() == 0) {
			this.comboBox.setSelectedIndex(0);
			this.tfTeacher.setText("");
		} else {
			this.comboBox.addItem(boardingClass.getTeacher_id() + "");
			this.comboBox.setSelectedItem(this.boardingClass.getTeacher_id() + "");
			this.tfTeacher.setText(dbc.getNameTeacher(boardingClass.getTeacher_id()));
		}
		this.tfNRoom.setText(boardingClass.getRoom());
		this.tfNumberBed.setText(boardingClass.getNumberOfBed() + "");
		this.lblClass_id.setText("Mã lớp: " + boardingClass.getBoardingClass_id());
		this.lblSizeHs.setText("Tổng: " + this.boardingClass.getStudent_ids().size() + " học sinh");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int count = 1;
		List<Student> stds = dbc.selectStudentsByBoaringClassId(boardingClass.getBoardingClass_id());
		for (Student student : stds) {
			model.addRow(new Object[] { count++, student.getStudent_id(), student.getName(), dbc.getNameParents(student.getParents_id()) });
		}
	}

	public static boolean hasSpecialCharacter(String str, String find) {
		Pattern pattern = Pattern.compile(find);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	public boolean checkBeforeSave() {
		boolean result = true;
		if (tfClass.getText().trim().length() == 0) {
			tfClass.setBackground(Color.orange);
			result = false;
		} else if (tfNumberBed.getText().trim().length() == 0
				|| !hasSpecialCharacter(tfNumberBed.getText().trim(), "^[0-9]+$")) {
			tfNumberBed.setBackground(Color.orange);
			result = false;
		} else if (tfNRoom.getText().trim().length() == 0) {
			tfNumberBed.setBackground(Color.orange);
			result = false;
		}
		return result;
	}

	public BoardingClass getBoardingClass() {
		if (boardingClass == null)
			boardingClass = new BoardingClass();
		boardingClass.setName(tfClass.getText());
		boardingClass.setRoom(tfNRoom.getText());
		boardingClass.setNumberOfBed(Integer.parseInt(tfNumberBed.getText()));
		String selection = comboBox.getSelectedItem() + "";
		if (isNew)
			boardingClass.setBoardingClass_id(dbc.getLastIdBoardingClass());
		if (!selection.equals("Không có"))
			boardingClass.setTeacher_id(Integer.parseInt(selection));
		return boardingClass;
	}

	public boolean getIsNew() {
		return this.isNew;
	}

	public boolean getIsEditable() {
		return this.isEditable;
	}
}
