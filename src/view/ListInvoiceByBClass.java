package view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ListInvoicesByBClassCTL;
import model.BoardingFee;
import model.CBBItem;
import model.Invoice;
import model.Student;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import custom.MyTable;
import custom.NonEditableTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import custom.MyButton;

public class ListInvoiceByBClass extends JDialog {

	private static final long serialVersionUID = 1L;
	private final Color BACKGROUND = new Color(0, 45, 97);
	private final Color RIGHT_COLOR = new Color(242, 224, 191);
	private final Color COLOR1 = new Color(244, 203, 198);

	private final Font LABEL_FONT = new Font("Times New Roman", Font.PLAIN, 20);
	private final Font TEXT_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18);
	private final Font BUTTON_FONT = new Font("Times New Roman", Font.BOLD, 16);
	private final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 30);
	private ListInvoicesByBClassCTL libbcc;
	public JComboBox<CBBItem> cbClass;
	private MyTable table;
	private int boardingFee_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListInvoiceByBClass dialog = new ListInvoiceByBClass(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListInvoiceByBClass() {
		initComponents();
	}

	public ListInvoiceByBClass(int boardingFee_id) {
		this.boardingFee_id = boardingFee_id;
		initComponents();
	}

	public int getBoardingFee_id() {
		return boardingFee_id;
	}

	public void addRowsInvoice(List<Invoice> invoices) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		int count = model.getRowCount() + 1;
		BoardingFee bf = libbcc.getBoardingFeeById(boardingFee_id);
		long mainCosts = bf.getMainCosts();
		long subCosts = bf.getSubCosts();
		int numberDays = bf.getNumberDays();
		for (Invoice iv : invoices) {
			Student std = libbcc.getStudentById(iv.getStudent_id());
			long subCost = (std.isSubMeal()) ? subCosts : 0;
			String name = libbcc.getNameStudentById(iv.getStudent_id());
			model.addRow(new Object[] { count++, iv.getStudent_id(), name, iv.getReturnMoney(), mainCosts, subCost,
					numberDays, iv.getMoney() });
		}
	}

	public void initComponents() {
		setModal(true);
		setUndecorated(true);
		setSize(900, 800);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BACKGROUND);
		libbcc = new ListInvoicesByBClassCTL(this);
		int month = (boardingFee_id + 8) % 12;
		if (month == 0)
			month = 12;
		
		table = new MyTable();
		JLabel lblDanhSchPhiu = new JLabel("DANH SÁCH PHIẾU THU THÁNG " + month);
		lblDanhSchPhiu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSchPhiu.setForeground(new Color(244, 203, 198));
		lblDanhSchPhiu.setFont(TITLE_FONT);
		lblDanhSchPhiu.setBounds(10, 10, 880, 45);
		getContentPane().add(lblDanhSchPhiu);

		JLabel lblClass = new JLabel("Lớp:");
		lblClass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClass.setForeground(COLOR1);
		lblClass.setFont(LABEL_FONT);
		lblClass.setBounds(298, 67, 52, 30);
		getContentPane().add(lblClass);

		cbClass = new JComboBox<CBBItem>();
		List<CBBItem> cbbs = libbcc.getBoardingClass_idAndName();
		for (CBBItem cbb : cbbs) {
			if (cbb.getValue() != 0)
				cbClass.addItem(cbb);
		}
		cbClass.setFont(TEXT_FONT);
		cbClass.setForeground(BACKGROUND);
		cbClass.setBackground(COLOR1);
		cbClass.setBounds(360, 67, 153, 28);
		cbClass.addActionListener(libbcc);
		cbClass.setSelectedIndex(0);
		getContentPane().add(cbClass);

		boolean[] nonEdit = {false, false, false, false, false, false, false, false};
		NonEditableTableModel nonTable = new NonEditableTableModel(new Object[][] {}, new String[] { "STT", "Mã HS", "Họ và tên",
				"Chi phí dư", "Buổi chính", "Buổi phụ", "Số ngày học", "Số tiền nộp" }, nonEdit);
		table.setFont(LABEL_FONT);
		table.setRowHeight(30);
		table.setModel(nonTable);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.setSelectionBackground(RIGHT_COLOR);
		table.setGridColor(Color.black);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 107, 858, 624);
		getContentPane().add(scrollPane);

		MyButton btnCancel = new MyButton();
		btnCancel.setText("Đóng");
		btnCancel.setRadius(15);
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setFocusPainted(false);
		btnCancel.setColorOver(new Color(242, 224, 191));
		btnCancel.setColor(new Color(244, 203, 198));
		btnCancel.setBorderColor(Color.BLACK);
		btnCancel.setBounds(769, 749, 103, 30);
		btnCancel.addActionListener(libbcc);
		getContentPane().add(btnCancel);
		
		MyButton mbtnXutVnBng = new MyButton();
		mbtnXutVnBng.setText("Xuất văn bảng");
		mbtnXutVnBng.setRadius(15);
		mbtnXutVnBng.setForeground(Color.BLACK);
		mbtnXutVnBng.setFont(new Font("Times New Roman", Font.BOLD, 16));
		mbtnXutVnBng.setFocusPainted(false);
		mbtnXutVnBng.setColorOver(new Color(242, 224, 191));
		mbtnXutVnBng.setColor(new Color(244, 203, 198));
		mbtnXutVnBng.setBorderColor(Color.BLACK);
		mbtnXutVnBng.setBounds(672, 65, 191, 30);
		mbtnXutVnBng.addActionListener(libbcc);
		getContentPane().add(mbtnXutVnBng);

		JTableHeader headerFood = table.getTableHeader();
		headerFood.setFont(TEXT_FONT);
		headerFood.setAlignmentX(JTableHeader.CENTER_ALIGNMENT);
		headerFood.setBackground(RIGHT_COLOR);
		
		if (cbClass.getItemCount() > 0) {
			cbClass.setSelectedIndex(0);
		}
		
		this.setVisible(true);
	}
}
