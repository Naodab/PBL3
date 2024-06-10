package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import controller.FoodDetailCTL;
import custom.MyButton;
import custom.MyTextField;
import model.Food;

import javax.swing.JRadioButton;
import javax.swing.JPanel;

public class FoodDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 350;
	private final int HEIGHT = 220;

	private final Color BACKGROUND = new Color(0, 45, 97);
	private final Color RIGHT_COLOR = new Color(242, 224, 191);
	private final Color COLOR1 = new Color(244, 203, 198);
	private final Color LABEL_COLOR = new Color(0, 0, 0);

	private final Font LABEL_FONT = new Font("Times New Roman", Font.PLAIN, 20);
	private final Font TEXT_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18);
	private final Font BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 14);
	private final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 30);

	private FoodDetailCTL fdc;
	private Food food;
	private boolean isNew;
	private boolean isEditable;
	private MyTextField textName;
	private ButtonGroup btn;
	private JRadioButton rdbtnPh;
	private JRadioButton rbtnMain;
	private JLabel lblID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FoodDetail dialog = new FoodDetail(1, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	public FoodDetail(int food_id, boolean isEditable) {
		fdc = new FoodDetailCTL(this);
		isNew = false;
		food = fdc.selectFoodById(food_id);
		this.isEditable = isEditable;
		initComponents();
	}

	public FoodDetail() {
		fdc = new FoodDetailCTL(this);
		this.isEditable = true;
		this.isNew = true;
		initComponents();
	}

	public void initComponents() {
		setModal(true);
		setUndecorated(true);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BACKGROUND);
		
		MyButton btnCancel = new MyButton();
		btnCancel.setFocusPainted(false);
		btnCancel.setText("Đóng");
		btnCancel.setRadius(20);
		btnCancel.setColor(COLOR1);
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setBorderColor(LABEL_COLOR);
		btnCancel.setForeground(LABEL_COLOR);
		btnCancel.setColorOver(new Color(222, 215, 165));
		btnCancel.setSize(97, 30);
		btnCancel.setLocation(243, 176);
		btnCancel.addActionListener(fdc);
		getContentPane().add(btnCancel);
		
		JLabel lblName = new JLabel("Tên:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setForeground(COLOR1);
		lblName.setFont(LABEL_FONT);
		lblName.setBounds(10, 80, 64, 30);
		getContentPane().add(lblName);

		textName = 	new MyTextField();
		textName.setLineColor(COLOR1);
		textName.setHorizontalAlignment(SwingConstants.LEFT);
		textName.setForeground(RIGHT_COLOR);
		textName.setFont(TEXT_FONT);
		textName.setBackground(BACKGROUND);
		textName.setBounds(84, 80, 221, 30);
		getContentPane().add(textName);
		
		JLabel lblLoi = new JLabel("Loại:");
		lblLoi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoi.setForeground(new Color(244, 203, 198));
		lblLoi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblLoi.setBounds(10, 123, 64, 30);
		getContentPane().add(lblLoi);
		
		btn = new ButtonGroup();
		
		rbtnMain = new JRadioButton("Chính");
		rbtnMain.setBackground(BACKGROUND);
		rbtnMain.setForeground(COLOR1);
		rbtnMain.setFont(TEXT_FONT);
		rbtnMain.setFocusPainted(false);
		rbtnMain.setBounds(84, 130, 82, 21);
		btn.add(rbtnMain);
		getContentPane().add(rbtnMain);
		
		rdbtnPh = new JRadioButton("Phụ");
		rdbtnPh.setForeground(COLOR1);
		rdbtnPh.setFont(TEXT_FONT);
		rdbtnPh.setFocusPainted(false);
		rdbtnPh.setBackground(BACKGROUND);
		rdbtnPh.setBounds(183, 130, 82, 21);
		btn.add(rdbtnPh);
		getContentPane().add(rdbtnPh);
		
		MyButton btnSave = new MyButton();
		btnSave.setText("Lưu");
		btnSave.setRadius(20);
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(BUTTON_FONT);
		btnSave.setFocusPainted(false);
		btnSave.setColorOver(new Color(222, 215, 165));
		btnSave.setColor(COLOR1);
		btnSave.setBorderColor(Color.BLACK);
		btnSave.setBounds(136, 176, 97, 30);
		btnSave.addActionListener(fdc);
		
		lblID = new JLabel("Món ăn " + fdc.getNewIdFood());
		lblID.setBorder(new MatteBorder(0, 0, 2, 0, COLOR1));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setForeground(RIGHT_COLOR);
		lblID.setFont(TITLE_FONT);
		lblID.setBounds(10, 10, 330, 47);
		getContentPane().add(lblID);
		
		JPanel panel = new JPanel();
		panel.setBackground(COLOR1);
		panel.setBounds(10, 163, 330, 3);
		getContentPane().add(panel);
		if (!isEditable) {
			textName.setEditable(false);
			textName.setForeground(RIGHT_COLOR);
			rbtnMain.setEnabled(false);
			rdbtnPh.setEnabled(false);
		}
		if (!isNew) {
			this.initFood();
		}
		if (isEditable)
			getContentPane().add(btnSave);
		setVisible(true);
	}
	
	public void initFood() {
		textName.setText(food.getName());
		if (food.getCategory()) 
			rbtnMain.setSelected(true);
		else 
			rdbtnPh.setSelected(true);
		lblID.setText("Món ăn " + food.getFood_id());
	}
	
	public boolean checkBeforeSave() {
		boolean result = true;
		if (textName.getText().length() == 0) {
			textName.setBackground(Color.orange);
			result = false;
		} else if (!rbtnMain.isSelected() && ! rdbtnPh.isSelected())
			result = false;
		return result;
	}
	
	public Food getFood() {
		String id = lblID.getText().trim();
		int pos = id.lastIndexOf(' ');
		food = new Food();
		food.setFood_id(Integer.parseInt(id.substring(pos + 1)));
		food.setName(textName.getText());
		food.setCategory(rbtnMain.isSelected());
		return food;
	}
	
	public boolean getIsNew() {
		return this.isNew;
	}
}
