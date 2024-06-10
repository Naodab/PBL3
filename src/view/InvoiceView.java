package view;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import controller.InvoiceViewCTL;
import custom.MyButton;
import custom.MyTextField;
import model.BoardingFee;
import model.Invoice;
import model.Student;

public class InvoiceView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final Color FOREGROUND = new Color(244, 203, 198);
	private final Color BACKGROUND = new Color(0, 45, 97);
	private final Color TF_FOREGROUND = new Color(242, 224, 191);
	private final Font LB_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18);
	private final Font TF_FONT = new Font("Times New Roman", Font.BOLD, 18);

	private MyTextField tfStudent;
	private Invoice invoice;
	private MyTextField tfParents;
	private MyTextField tfFee;
	private MyTextField tfClass;
	private MyTextField tfTeacher;
	private MyTextField tfMain;
	private MyTextField tfPay;
	private MyTextField tfDayPay;
	private MyTextField tfMonthPay;
	private MyTextField tfYearPay;
	private MyTextField tfNumberDays;
	private MyTextField tfRetrun;
	private MyTextField tfSub;
	private InvoiceViewCTL ivvctl;

	public static void main(String[] args) {
		try {
			InvoiceView dialog = new InvoiceView(1200);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InvoiceView(int invoice_id) {
		ivvctl = new InvoiceViewCTL(this);
		this.invoice = ivvctl.getInvoiceById(invoice_id);
		initComponents();
	}

	public InvoiceView() {
		ivvctl = new InvoiceViewCTL(this);
		initComponents();
	}

	public void initInfo() {
		Student student = ivvctl.getStudentById(invoice.getStudent_id());
		tfStudent.setText(student.getName());
		tfClass.setText(ivvctl.getNameBoardingClassById(student.getBoardingClass_id()));
		tfTeacher.setText(ivvctl.getNameTeacherByBoardingClass_id(student.getBoardingClass_id()));
		tfParents.setText(ivvctl.getNameParentsById(student.getParents_id()));
		BoardingFee bfee = ivvctl.getBoardingFeeById(invoice.getBoardingFee_id());
		LocalDate local = bfee.getStart_day().toLocalDate();
		tfFee.setText("Tháng " + local.getMonthValue() + ", năm " + local.getYear());
		local = invoice.getPayment_date().toLocalDate();
		tfDayPay.setText(local.getDayOfMonth() + "");
		tfMonthPay.setText(local.getMonthValue() + "");
		tfYearPay.setText(local.getYear() + "");
		tfMain.setText(bfee.getMainCosts() + "");
		tfSub.setText((student.isSubMeal())? (bfee.getSubCosts() + "") : "0");
		tfNumberDays.setText(bfee.getNumberDays() + "");
		tfRetrun.setText(invoice.getReturnMoney() + "");
		tfPay.setText(invoice.getMoney() + "");
	}

	public void initComponents() {
		setModal(true);
		setUndecorated(true);
		setSize(550, 570);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(BACKGROUND);
		contentPanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblSchool = new JLabel("TRƯỜNG TIỂU HỌC SỐ I HÒA PHƯỚC");
		lblSchool.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSchool.setForeground(FOREGROUND);
		lblSchool.setBounds(10, 10, 511, 32);
		contentPanel.add(lblSchool);

		JLabel lblAddress = new JLabel(
				"Địa chỉ: tổ 4, thôn Miếu Bông, xã Hòa Phước, huyện Hòa Vang, thành phố Đà Nẵng");
		lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAddress.setForeground(FOREGROUND);
		lblAddress.setBounds(10, 39, 522, 32);
		contentPanel.add(lblAddress);

		JLabel title = new JLabel("PHIẾU THU HỌC PHÍ");
		title.setBorder(new MatteBorder(3, 0, 0, 0, FOREGROUND));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 24));
		title.setForeground(FOREGROUND);
		title.setBounds(10, 70, 530, 65);
		contentPanel.add(title);

		JLabel lblStudent = new JLabel("Học sinh:");
		lblStudent.setForeground(FOREGROUND);
		lblStudent.setFont(LB_FONT);
		lblStudent.setBounds(10, 145, 81, 32);
		contentPanel.add(lblStudent);

		JLabel lblParents = new JLabel("Phụ huynh:");
		lblParents.setForeground(FOREGROUND);
		lblParents.setFont(LB_FONT);
		lblParents.setBounds(10, 183, 101, 32);
		contentPanel.add(lblParents);

		JLabel lblClass = new JLabel("Lớp:");
		lblClass.setForeground(new Color(244, 203, 198));
		lblClass.setFont(LB_FONT);
		lblClass.setBounds(385, 145, 48, 32);
		contentPanel.add(lblClass);

		JLabel lblFee = new JLabel("Đợt thu tiền:");
		lblFee.setForeground(FOREGROUND);
		lblFee.setFont(LB_FONT);
		lblFee.setBounds(10, 265, 101, 32);
		contentPanel.add(lblFee);

		tfStudent = new MyTextField();
		tfStudent.setEditable(false);
		tfStudent.setHorizontalAlignment(SwingConstants.CENTER);
		tfStudent.setFont(TF_FONT);
		tfStudent.setBounds(101, 145, 236, 28);
		tfStudent.setLineColor(FOREGROUND);
		tfStudent.setBackground(BACKGROUND);
		tfStudent.setForeground(TF_FOREGROUND);
		contentPanel.add(tfStudent);
		tfStudent.setColumns(10);

		tfParents = new MyTextField();
		tfParents.setEditable(false);
		tfParents.setHorizontalAlignment(SwingConstants.CENTER);
		tfParents.setLineColor(FOREGROUND);
		tfParents.setForeground(TF_FOREGROUND);
		tfParents.setColumns(10);
		tfParents.setBackground(BACKGROUND);
		tfParents.setFont(TF_FONT);
		tfParents.setBounds(111, 183, 421, 28);
		contentPanel.add(tfParents);

		tfFee = new MyTextField();
		tfFee.setEditable(false);
		tfFee.setHorizontalAlignment(SwingConstants.CENTER);
		tfFee.setLineColor(FOREGROUND);
		tfFee.setForeground(TF_FOREGROUND);
		tfFee.setColumns(10);
		tfFee.setFont(TF_FONT);
		tfFee.setBackground(BACKGROUND);
		tfFee.setBounds(121, 265, 163, 28);
		contentPanel.add(tfFee);

		tfClass = new MyTextField();
		tfClass.setEditable(false);
		tfClass.setHorizontalAlignment(SwingConstants.CENTER);
		tfClass.setLineColor(FOREGROUND);
		tfClass.setForeground(TF_FOREGROUND);
		tfClass.setFont(TF_FONT);
		tfClass.setColumns(10);
		tfClass.setBackground(BACKGROUND);
		tfClass.setBounds(421, 145, 111, 28);
		contentPanel.add(tfClass);

		JLabel lblTeacher = new JLabel("Giáo viên:");
		lblTeacher.setForeground(FOREGROUND);
		lblTeacher.setFont(LB_FONT);
		lblTeacher.setBounds(10, 225, 81, 32);
		contentPanel.add(lblTeacher);

		tfTeacher = new MyTextField();
		tfTeacher.setEditable(false);
		tfTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		tfTeacher.setLineColor(FOREGROUND);
		tfTeacher.setForeground(TF_FOREGROUND);
		tfTeacher.setFont(TF_FONT);
		tfTeacher.setColumns(10);
		tfTeacher.setBackground(BACKGROUND);
		tfTeacher.setBounds(110, 225, 422, 28);
		contentPanel.add(tfTeacher);

		JLabel lblAmount = new JLabel("Chi phí buổi trưa:");
		lblAmount.setForeground(FOREGROUND);
		lblAmount.setFont(LB_FONT);
		lblAmount.setBounds(10, 307, 147, 32);
		contentPanel.add(lblAmount);

		JLabel lblNgnng = new JLabel("đồng");
		lblNgnng.setForeground(FOREGROUND);
		lblNgnng.setFont(LB_FONT);
		lblNgnng.setBounds(266, 306, 43, 32);
		contentPanel.add(lblNgnng);

		tfMain = new MyTextField();
		tfMain.setEditable(false);
		tfMain.setHorizontalAlignment(SwingConstants.RIGHT);
		tfMain.setLineColor(FOREGROUND);
		tfMain.setForeground(TF_FOREGROUND);
		tfMain.setFont(TF_FONT);
		tfMain.setColumns(10);
		tfMain.setBackground(BACKGROUND);
		tfMain.setBounds(158, 303, 98, 28);
		contentPanel.add(tfMain);

		JLabel lblSTin = new JLabel("Số tiền nộp:");
		lblSTin.setForeground(FOREGROUND);
		lblSTin.setFont(LB_FONT);
		lblSTin.setBounds(10, 451, 128, 32);
		contentPanel.add(lblSTin);

		tfPay = new MyTextField();
		tfPay.setEditable(false);
		tfPay.setLineColor(FOREGROUND);
		tfPay.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPay.setForeground(TF_FOREGROUND);
		tfPay.setFont(TF_FONT);
		tfPay.setBackground(BACKGROUND);
		tfPay.setBounds(158, 454, 98, 28);
		contentPanel.add(tfPay);

		JLabel lblNgnng_1 = new JLabel("đồng");
		lblNgnng_1.setForeground(FOREGROUND);
		lblNgnng_1.setFont(LB_FONT);
		lblNgnng_1.setBounds(266, 451, 43, 32);
		contentPanel.add(lblNgnng_1);

		JLabel lblNgyNp = new JLabel("Ngày nộp:");
		lblNgyNp.setForeground(FOREGROUND);
		lblNgyNp.setFont(LB_FONT);
		lblNgyNp.setBounds(294, 265, 86, 32);
		contentPanel.add(lblNgyNp);

		tfDayPay = new MyTextField();
		tfDayPay.setEditable(false);
		tfDayPay.setLocation(385, 268);
		tfDayPay.setSize(25, 28);
		tfDayPay.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDayPay.setLineColor(FOREGROUND);
		tfDayPay.setForeground(TF_FOREGROUND);
		tfDayPay.setFont(TF_FONT);
		tfDayPay.setBackground(BACKGROUND);
		contentPanel.add(tfDayPay);

		JLabel seperator = new JLabel("/");
		seperator.setHorizontalAlignment(SwingConstants.CENTER);
		seperator.setForeground(FOREGROUND);
		seperator.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 41));
		seperator.setBounds(405, 268, 31, 40);
		contentPanel.add(seperator);

		tfMonthPay = new MyTextField();
		tfMonthPay.setEditable(false);
		tfMonthPay.setHorizontalAlignment(SwingConstants.RIGHT);
		tfMonthPay.setLineColor(FOREGROUND);
		tfMonthPay.setForeground(TF_FOREGROUND);
		tfMonthPay.setFont(TF_FONT);
		tfMonthPay.setBackground(BACKGROUND);
		tfMonthPay.setBounds(432, 269, 25, 28);
		contentPanel.add(tfMonthPay);

		JLabel seperator_1 = new JLabel("/");
		seperator_1.setHorizontalAlignment(SwingConstants.CENTER);
		seperator_1.setForeground(FOREGROUND);
		seperator_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 41));
		seperator_1.setBounds(452, 268, 31, 40);
		contentPanel.add(seperator_1);

		tfYearPay = new MyTextField();
		tfYearPay.setEditable(false);
		tfYearPay.setHorizontalAlignment(SwingConstants.RIGHT);
		tfYearPay.setLineColor(FOREGROUND);
		tfYearPay.setForeground(TF_FOREGROUND);
		tfYearPay.setFont(TF_FONT);
		tfYearPay.setBackground(BACKGROUND);
		tfYearPay.setBounds(481, 269, 50, 28);
		contentPanel.add(tfYearPay);

		MyButton btnCancel = new MyButton();
		btnCancel.setText("Đóng");
		btnCancel.setRadius(10);
		btnCancel.setColor(FOREGROUND);
		btnCancel.setForeground(BACKGROUND);
		btnCancel.setFont(LB_FONT);
		btnCancel.setBorderColor(Color.black);
		btnCancel.addActionListener(ivvctl);
		btnCancel.setBounds(420, 516, 101, 28);
		contentPanel.add(btnCancel);

		JLabel lblChiPhBui = new JLabel("Chi phí buổi phụ:");
		lblChiPhBui.setForeground(FOREGROUND);
		lblChiPhBui.setFont(LB_FONT);
		lblChiPhBui.setBounds(10, 345, 147, 32);
		contentPanel.add(lblChiPhBui);

		tfSub = new MyTextField();
		tfSub.setEditable(false);
		tfSub.setLineColor(FOREGROUND);
		tfSub.setHorizontalAlignment(SwingConstants.RIGHT);
		tfSub.setForeground(TF_FOREGROUND);
		tfSub.setFont(TF_FONT);
		tfSub.setColumns(10);
		tfSub.setBackground(BACKGROUND);
		tfSub.setBounds(158, 341, 98, 28);
		contentPanel.add(tfSub);

		JLabel lblNgnng_2 = new JLabel("đồng");
		lblNgnng_2.setForeground(FOREGROUND);
		lblNgnng_2.setFont(LB_FONT);
		lblNgnng_2.setBounds(266, 344, 43, 32);
		contentPanel.add(lblNgnng_2);

		JLabel lblSNgyHc = new JLabel("Số ngày học:");
		lblSNgyHc.setForeground(FOREGROUND);
		lblSNgyHc.setFont(LB_FONT);
		lblSNgyHc.setBounds(10, 376, 147, 32);
		contentPanel.add(lblSNgyHc);

		tfNumberDays = new MyTextField();
		tfNumberDays.setEditable(false);
		tfNumberDays.setHorizontalAlignment(SwingConstants.RIGHT);
		tfNumberDays.setLineColor(FOREGROUND);
		tfNumberDays.setForeground(TF_FOREGROUND);
		tfNumberDays.setFont(TF_FONT);
		tfNumberDays.setBackground(BACKGROUND);
		tfNumberDays.setBounds(158, 372, 98, 28);
		contentPanel.add(tfNumberDays);

		JLabel lblNgy = new JLabel("ngày");
		lblNgy.setForeground(FOREGROUND);
		lblNgy.setFont(LB_FONT);
		lblNgy.setBounds(266, 375, 43, 32);
		contentPanel.add(lblNgy);

		JLabel lblChiPhD = new JLabel("Chi phí dư:");
		lblChiPhD.setForeground(FOREGROUND);
		lblChiPhD.setFont(LB_FONT);
		lblChiPhD.setBounds(10, 414, 147, 32);
		contentPanel.add(lblChiPhD);

		tfRetrun = new MyTextField();
		tfRetrun.setEditable(false);
		tfRetrun.setHorizontalAlignment(SwingConstants.RIGHT);
		tfRetrun.setLineColor(FOREGROUND);
		tfRetrun.setForeground(TF_FOREGROUND);
		tfRetrun.setFont(TF_FONT);
		tfRetrun.setBackground(BACKGROUND);
		tfRetrun.setBounds(158, 410, 98, 28);
		contentPanel.add(tfRetrun);

		JLabel lblNgnng_2_1 = new JLabel("đồng");
		lblNgnng_2_1.setForeground(FOREGROUND);
		lblNgnng_2_1.setFont(LB_FONT);
		lblNgnng_2_1.setBounds(266, 413, 43, 32);
		contentPanel.add(lblNgnng_2_1);
		if (invoice != null)
			initInfo();
		setVisible(true);
	}
}
