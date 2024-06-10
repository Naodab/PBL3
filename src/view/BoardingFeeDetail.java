package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import controller.BoardingFeeDetailCTL;
import custom.MyButton;
import custom.MyTextField;
import custom.PanelRound;
import model.BoardingFee;

public class BoardingFeeDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private final Color BACKGROUND = new Color(0, 45, 97);
	private final Color RIGHT_COLOR = new Color(242, 224, 191);
	private final Color COLOR1 = new Color(244, 203, 198);
	private final Color LABEL_COLOR = new Color(0, 0, 0);

	private final Font LABEL_FONT = new Font("Times New Roman", Font.PLAIN, 20);
	private final Font TEXT_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18);
	private final Font BUTTON_FONT = new Font("Times New Roman", Font.BOLD, 16);
	private final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 30);
	private final JPanel contentPanel = new JPanel();
	private MyTextField textMonthSt;
	private MyTextField textYearSt;
	private MyTextField textTime;
	private MyTextField textDaySt;

	private AdminScreen ads;
	private BoardingFee bfee;
	private boolean isNew;
	private boolean isEditable;
	private int id;
	private MyTextField textDayE;
	private MyTextField textMonthE;
	private MyTextField textYearE;
	private MyTextField textMain;
	private MyTextField textSub;
	private JLabel title;
	private BoardingFeeDetailCTL bfdc;

	public static void main(String[] args) {
		try {
			BoardingFeeDetail dialog = new BoardingFeeDetail(new AdminScreen());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BoardingFeeDetail(AdminScreen ads) {
		bfdc = new BoardingFeeDetailCTL(this, ads);
		isNew = true;
		isEditable = true;
		this.ads = ads;
		id = bfdc.selectNewIdBoardingFee();
		initComponents();
	}

	public BoardingFeeDetail(int boardingFee_id, boolean isEditable) {
		bfdc = new BoardingFeeDetailCTL(this, ads);
		isNew = false;
		this.isEditable = isEditable;
		id = boardingFee_id;
		bfee = bfdc.selectBoardingFeeById(boardingFee_id);
		initComponents();
	}

	public void initComponents() {
		setModal(true);
		setUndecorated(true);
		setSize(522, 370);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		contentPanel.setBackground(BACKGROUND);

		title = new JLabel("ĐỢT THU TIỀN " + id);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(TITLE_FONT);
		title.setForeground(COLOR1);
		title.setBounds(20, 21, 502, 45);
		contentPanel.add(title);

		PanelRound panel = new PanelRound();
		panel.setBorderWidth(3);
		panel.setBorderColor(Color.black);
		panel.setRadius(25);
		panel.setBground(COLOR1);
		panel.setBounds(20, 76, 481, 230);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblThiGian = new JLabel("Thời gian:");
		lblThiGian.setBounds(21, 40, 91, 30);
		panel.add(lblThiGian);
		lblThiGian.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThiGian.setForeground(BACKGROUND);
		lblThiGian.setFont(LABEL_FONT);

		textTime = new MyTextField();
		textTime.setBounds(122, 40, 214, 30);
		panel.add(textTime);
		textTime.setLineColor(Color.black);
		textTime.setHorizontalAlignment(SwingConstants.CENTER);
		textTime.setForeground(BACKGROUND);
		textTime.setFont(TEXT_FONT);
		textTime.setBackground(COLOR1);
		textTime.setEditable(false);

		JLabel lblStart = new JLabel("Bắt đầu:");
		lblStart.setBounds(21, 80, 91, 30);
		panel.add(lblStart);
		lblStart.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStart.setForeground(BACKGROUND);
		lblStart.setFont(LABEL_FONT);

		textDaySt = new MyTextField();
		textDaySt.setBounds(123, 80, 22, 30);
		panel.add(textDaySt);
		textDaySt.setHorizontalAlignment(SwingConstants.RIGHT);
		textDaySt.setLineColor(Color.black);
		textDaySt.setForeground(BACKGROUND);
		textDaySt.setFont(TEXT_FONT);
		textDaySt.setBackground(COLOR1);

		JLabel lblSeperate = new JLabel("/");
		lblSeperate.setBounds(145, 80, 25, 30);
		panel.add(lblSeperate);
		lblSeperate.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeperate.setForeground(BACKGROUND);
		lblSeperate.setFont(new Font("Times New Roman", Font.BOLD, 30));

		textMonthSt = new MyTextField();
		textMonthSt.setBounds(155, 80, 22, 30);
		panel.add(textMonthSt);
		textMonthSt.setHorizontalAlignment(SwingConstants.RIGHT);
		textMonthSt.setLineColor(Color.black);
		textMonthSt.setForeground(BACKGROUND);
		textMonthSt.setFont(TEXT_FONT);
		textMonthSt.setBackground(COLOR1);

		JLabel lblSeperate_1 = new JLabel("/");
		lblSeperate_1.setBounds(177, 80, 25, 30);
		panel.add(lblSeperate_1);
		lblSeperate_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeperate_1.setForeground(BACKGROUND);
		lblSeperate_1.setFont(lblSeperate.getFont());

		textYearSt = new MyTextField();
		textYearSt.setBounds(187, 80, 44, 30);
		panel.add(textYearSt);
		textYearSt.setLineColor(Color.black);
		textYearSt.setForeground(BACKGROUND);
		textYearSt.setFont(TEXT_FONT);
		textYearSt.setBackground(COLOR1);

		JLabel lblEnd = new JLabel("Kết thúc:");
		lblEnd.setBounds(241, 80, 91, 30);
		panel.add(lblEnd);
		lblEnd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnd.setForeground(BACKGROUND);
		lblEnd.setFont(LABEL_FONT);

		textDayE = new MyTextField();
		textDayE.setBounds(343, 80, 22, 30);
		panel.add(textDayE);
		textDayE.setHorizontalAlignment(SwingConstants.RIGHT);
		textDayE.setLineColor(Color.black);
		textDayE.setForeground(BACKGROUND);
		textDayE.setFont(TEXT_FONT);
		textDayE.setBackground(COLOR1);

		JLabel lblSeperate_2 = new JLabel("/");
		lblSeperate_2.setBounds(365, 80, 25, 30);
		panel.add(lblSeperate_2);
		lblSeperate_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeperate_2.setForeground(BACKGROUND);
		lblSeperate_2.setFont(new Font("Times New Roman", Font.BOLD, 30));

		textMonthE = new MyTextField();
		textMonthE.setBounds(375, 80, 22, 30);
		panel.add(textMonthE);
		textMonthE.setHorizontalAlignment(SwingConstants.RIGHT);
		textMonthE.setLineColor(Color.black);
		textMonthE.setForeground(BACKGROUND);
		textMonthE.setFont(TEXT_FONT);
		textMonthE.setBackground(COLOR1);

		JLabel lblSeperate_1_1 = new JLabel("/");
		lblSeperate_1_1.setBounds(397, 80, 25, 30);
		panel.add(lblSeperate_1_1);
		lblSeperate_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeperate_1_1.setForeground(BACKGROUND);
		lblSeperate_1_1.setFont(new Font("Times New Roman", Font.BOLD, 30));

		textYearE = new MyTextField();
		textYearE.setBounds(407, 80, 44, 30);
		panel.add(textYearE);
		textYearE.setLineColor(Color.black);
		textYearE.setForeground(BACKGROUND);
		textYearE.setFont(TEXT_FONT);
		textYearE.setBackground(COLOR1);

		JLabel lblTinBuiChnh = new JLabel("Chi phí buổi chính:");
		lblTinBuiChnh.setBounds(21, 120, 170, 30);
		panel.add(lblTinBuiChnh);
		lblTinBuiChnh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTinBuiChnh.setForeground(BACKGROUND);
		lblTinBuiChnh.setFont(LABEL_FONT);

		textMain = new MyTextField();
		textMain.setHorizontalAlignment(SwingConstants.CENTER);
		textMain.setBounds(197, 120, 108, 30);
		panel.add(textMain);
		textMain.setLineColor(Color.black);
		textMain.setForeground(BACKGROUND);
		textMain.setFont(TEXT_FONT);
		textMain.setBackground(COLOR1);

		JLabel lblChiPhBui = new JLabel("Chi phí buổi phụ:");
		lblChiPhBui.setBounds(21, 160, 170, 30);
		panel.add(lblChiPhBui);
		lblChiPhBui.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChiPhBui.setForeground(BACKGROUND);
		lblChiPhBui.setFont(LABEL_FONT);

		textSub = new MyTextField();
		textSub.setHorizontalAlignment(SwingConstants.CENTER);
		textSub.setBounds(197, 160, 108, 30);
		panel.add(textSub);
		textSub.setLineColor(new Color(244, 203, 198));
		textSub.setLineColor(Color.black);
		textSub.setForeground(BACKGROUND);
		textSub.setFont(TEXT_FONT);
		textSub.setBackground(COLOR1);

		JLabel lblMoney = new JLabel("đồng/ngày");
		lblMoney.setBounds(315, 120, 99, 30);
		panel.add(lblMoney);
		lblMoney.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoney.setForeground(BACKGROUND);
		lblMoney.setFont(LABEL_FONT);

		JLabel lblMoney_1 = new JLabel("đồng/ngày");
		lblMoney_1.setBounds(315, 160, 99, 30);
		panel.add(lblMoney_1);
		lblMoney_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoney_1.setForeground(BACKGROUND);
		lblMoney_1.setFont(LABEL_FONT);

		MyButton btnCancel = new MyButton();
		btnCancel.setFocusPainted(false);
		btnCancel.setText("Đóng");
		btnCancel.setRadius(15);
		btnCancel.setColor(COLOR1);
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setBorderColor(LABEL_COLOR);
		btnCancel.setForeground(LABEL_COLOR);
		btnCancel.setColorOver(RIGHT_COLOR);
		btnCancel.setBounds(377, 316, 103, 30);
		btnCancel.addActionListener(bfdc);
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
		btnOK.addActionListener(bfdc);
		btnOK.setBounds(btnCancel.getX() - btnCancel.getWidth() - 10, btnCancel.getY(), btnCancel.getWidth(),
				btnCancel.getHeight());

		if (isNew) {
			int last_id = bfdc.getLastIdOfBoardingFee();
			BoardingFee lastBfee = bfdc.selectBoardingFeeById(last_id);
			if (lastBfee == null) {
				LocalDate now = LocalDate.now();
				textTime.setText("Tháng " + now.getMonthValue() + " năm " + now.getYear());
			} else {
				LocalDate now = lastBfee.getStart_day().toLocalDate();
				int month = now.getMonthValue() + 1;
				int year = now.getYear();
				if (month == 13) {
					month = 1;
					year++;
				}
				textTime.setText("Tháng " + month + " năm " + year);
				textMonthSt.setText(month + "");
				textMonthE.setText(month + "");
				textYearSt.setText(year + "");
				textYearE.setText(year + "");
			}
		}

		if (isEditable) {
			contentPanel.add(btnOK);
		} else {
			textDaySt.setEditable(false);
			textMonthSt.setEditable(false);
			textYearSt.setEditable(false);
			textDayE.setEditable(false);
			textMonthE.setEditable(false);
			textYearE.setEditable(false);
			textMain.setEditable(false);
			textSub.setEditable(false);
		}
		if (!isNew) {
			initInfo();
		}
		this.setVisible(true);
	}

	public void initInfo() {
		LocalDate start = bfee.getStart_day().toLocalDate();
		textTime.setText("Tháng " + start.getMonthValue() + " năm " + start.getYear());
		textDaySt.setText(start.getDayOfMonth() + "");
		textMonthSt.setText(start.getMonthValue() + "");
		textYearSt.setText(start.getYear() + "");
		LocalDate end = bfee.getEnd_day().toLocalDate();
		textDayE.setText(end.getDayOfMonth() + "");
		textMonthE.setText(end.getMonthValue() + "");
		textYearE.setText(end.getYear() + "");
		textMain.setText(bfee.getMainCosts() + "");
		textSub.setText(bfee.getSubCosts() + "");
	}

	public long parseNumber(String str) {
		long result = -1;
		try {
			result = Long.parseLong(str);
		} catch (Exception e) {
			result = -1;
		}
		return result;
	}

	private boolean checkTextField(MyTextField test) {
		boolean result = true;
		long temp = parseNumber(test.getText());
		if (temp == -1) {
			result = false;
			test.setBackground(RIGHT_COLOR);
		} else {
			test.setBackground(COLOR1);
		}
		return result;
	}

	public boolean checkBeforeSave() {
		boolean result = true;
		if (!checkTextField(textDaySt))
			result = false;
		if (!checkTextField(textMonthSt))
			result = false;
		if (!checkTextField(textYearSt))
			result = false;
		if (!checkTextField(textDayE))
			result = false;
		if (!checkTextField(textMonthE))
			result = false;
		if (!checkTextField(textYearE))
			result = false;
		if (!checkTextField(textMain))
			result = false;
		if (!checkTextField(textSub))
			result = false;
		if (result) {
			int day = Integer.parseInt(textDaySt.getText());
			int month = Integer.parseInt(textMonthSt.getText());
			int year = Integer.parseInt(textYearSt.getText());
			boolean check = PersonDetail.checkDate(day, month, year);
			if (!check) {
				result = false;
				textDaySt.setBackground(RIGHT_COLOR);
				textMonthSt.setBackground(RIGHT_COLOR);
				textYearSt.setBackground(RIGHT_COLOR);
			} else {
				textDaySt.setBackground(COLOR1);
				textMonthSt.setBackground(COLOR1);
				textYearSt.setBackground(COLOR1);
			}
			int day2 = Integer.parseInt(textDayE.getText());
			int month2 = Integer.parseInt(textMonthE.getText());
			int year2 = Integer.parseInt(textYearE.getText());
			check = PersonDetail.checkDate(day2, month2, year2);

			if (!check) {
				result = false;
				textDayE.setBackground(RIGHT_COLOR);
				textMonthE.setBackground(RIGHT_COLOR);
				textYearE.setBackground(RIGHT_COLOR);
			} else {
				textDayE.setBackground(COLOR1);
				textMonthE.setBackground(COLOR1);
				textYearE.setBackground(COLOR1);
			}
			
			if (LocalDate.of(year, month, day).compareTo(LocalDate.of(year2, month2, day2)) > 0) {
				result = false;
				textDaySt.setBackground(RIGHT_COLOR);
				textMonthSt.setBackground(RIGHT_COLOR);
				textYearSt.setBackground(RIGHT_COLOR);
				textDayE.setBackground(RIGHT_COLOR);
				textMonthE.setBackground(RIGHT_COLOR);
				textYearE.setBackground(RIGHT_COLOR);
			}
		}
		return result;
	}

	public BoardingFee getBoardingFee() {
		if (isNew) {
			bfee = new BoardingFee();
			bfee.setBoardingFee_id(id);
		}

		int day = Integer.parseInt(textDaySt.getText());
		int month = Integer.parseInt(textMonthSt.getText());
		int year = Integer.parseInt(textYearSt.getText());
		LocalDate start = LocalDate.of(year, month, day);

		day = Integer.parseInt(textDayE.getText());
		month = Integer.parseInt(textMonthE.getText());
		year = Integer.parseInt(textYearE.getText());
		LocalDate end = LocalDate.of(year, month, day);

		long mainCosts = Long.parseLong(textMain.getText());
		long subCosts = Long.parseLong(textSub.getText());

		bfee.setStart_day(Date.valueOf(start));
		bfee.setEnd_day(Date.valueOf(end));
		bfee.setMainCosts(mainCosts);
		bfee.setSubCosts(subCosts);
		return bfee;
	}

	public boolean getIsNew() {
		return this.isNew;
	}

	public boolean getIsEditable() {
		return this.isEditable;
	}
}
