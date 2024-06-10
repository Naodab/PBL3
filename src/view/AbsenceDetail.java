package view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import custom.MyButton;
import custom.MyTable;
import custom.MyTextField;
import model.Absence;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.AbsenceCTL;

public class AbsenceDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 700;
	private final int HEIGHT = 400;
	private final int PADDING = 20;

	private final Color BACKGROUND = new Color(0, 45, 97);
	private final Color RIGHT_COLOR = new Color(242, 224, 191);
	private final Color COLOR1 = new Color(244, 203, 198);
	private final Color LABEL_COLOR = new Color(0, 0, 0);

	private final Font LABEL_FONT = new Font("Times New Roman", Font.PLAIN, 20);
	private final Font TEXT_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18);
	private final Font BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 14);

	private int student_id;
	private MyTable table;
	private MyTextField textAbsence;
	private AbsenceCTL abctl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AbsenceDetail dialog = new AbsenceDetail(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AbsenceDetail(int student_id) {
		abctl = new AbsenceCTL(this);
		this.student_id = student_id;
		initComponents();
	}

	public void initComponents() {
		setAlwaysOnTop(true);
		setUndecorated(true);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BACKGROUND);
		
		table = new MyTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"STT", "Tên học sinh", "Ngày vắng"
			}
		));
		table.setSelectionBackground(RIGHT_COLOR);
		table.setGridColor(Color.black);
		table.setRowHeight(30);
		table.setFont(LABEL_FONT);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		String name = abctl.nameStudent(student_id);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int row = 1;
		List<Absence> ids = abctl.selectAbsenceByStudentId(student_id);
		for (Absence ab : ids) {
			model.addRow(new Object[] {row++, name, ab.getDayOfAbsence()});
		}
		
		JTableHeader headerStudent = table.getTableHeader();
		headerStudent.setFont(TEXT_FONT);
		headerStudent.setLayout(null);
		headerStudent.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerStudent.setBackground(RIGHT_COLOR);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 63, 660, 277);
		getContentPane().add(scrollPane);
		
		MyButton btnCancel = new MyButton();
		btnCancel.setFocusPainted(false);
		btnCancel.setText("Trở lại");
		btnCancel.setRadius(20);
		btnCancel.setColor(COLOR1);
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setBorderColor(LABEL_COLOR);
		btnCancel.setForeground(LABEL_COLOR);
		btnCancel.setColorOver(new Color(222, 215, 165));
		btnCancel.setSize(120, 30);
		btnCancel.setLocation(WIDTH - PADDING - btnCancel.getWidth(), HEIGHT - PADDING - btnCancel.getHeight());
		btnCancel.addActionListener(abctl);
		getContentPane().add(btnCancel);
		
		JLabel lblSBuiVng = new JLabel("Số buổi vắng:");
		lblSBuiVng.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSBuiVng.setForeground(COLOR1);
		lblSBuiVng.setFont(LABEL_FONT);
		lblSBuiVng.setBounds(500, 20, 130, 30);
		getContentPane().add(lblSBuiVng);

		textAbsence = new MyTextField();
		textAbsence.setLineColor(COLOR1);
		textAbsence.setHorizontalAlignment(SwingConstants.CENTER);
		textAbsence.setForeground(COLOR1);
		textAbsence.setFont(TEXT_FONT);
		textAbsence.setBackground(BACKGROUND);
		textAbsence.setBounds(lblSBuiVng.getX() + lblSBuiVng.getWidth(), lblSBuiVng.getY(), 44, 30);
		textAbsence.setEnabled(false);
		getContentPane().add(textAbsence);
		int absence = abctl.getAmountAbsence(student_id);
		textAbsence.setText(absence + "");
		
		setVisible(true);
	}
}
