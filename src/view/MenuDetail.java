package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;

import controller.MenuDetailCTL;
import custom.MyButton;
import model.CBBItem;
import model.Food;
import model.Menu;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MenuDetail extends JDialog {
	private final Color BACKGROUND = new Color(0, 45, 97);
	private final Color FOREGROUND = new Color(244, 203, 198);
	
	private final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 26);
	private final Font MEAL_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23);
	private final Font FOOD_FONT = new Font("Times New Roman", Font.BOLD, 20);
	private final Font BUTTON_FONT = new Font("Times New Roman", Font.BOLD, 18);
	
	private static final long serialVersionUID = 1L;
	
	private Menu menu;
	private boolean isNew;
	private boolean isEditable;
	private boolean editableActive;
	private JLabel title;
	private MenuDetailCTL mectl;
	
	private JComboBox<CBBItem> comboBox1;
	private JComboBox<CBBItem> comboBox2;
	private JComboBox<CBBItem> comboBox3;
	private JComboBox<CBBItem> comboBox4;
	private JComboBox<CBBItem> comboBox5;
	private JComboBox<CBBItem> comboBox6;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;

	public static void main(String[] args) {
		new MenuDetail();
	}
	
	public MenuDetail() {
		mectl = new MenuDetailCTL(this);
		menu = null;
		this.isNew = true;
		this.isEditable = true;
		initComponents();
	}
	
	public MenuDetail(int menu_id, boolean isEditable, boolean editableActive) {
		mectl = new MenuDetailCTL(this);
		this.menu = mectl.selectMenuById(menu_id);
		this.editableActive = editableActive;
		this.isNew = false;
		this.isEditable = isEditable;
		initComponents();
	}
	
	public void initComponents() {
		setModal(true);
		setUndecorated(true);
		setSize(349, 480);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(BACKGROUND);

		title = new JLabel("THỰC ĐƠN " + mectl.selectNewIdFood());
		title.setBorder(new MatteBorder(0, 0, 2, 0, FOREGROUND));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(TITLE_FONT);
		title.setForeground(FOREGROUND);
		title.setBounds(10, 10, 329, 41);
		getContentPane().add(title);

		JLabel lblMainFood = new JLabel("Bữa trưa:");
		lblMainFood.setHorizontalAlignment(SwingConstants.LEFT);
		lblMainFood.setForeground(FOREGROUND);
		lblMainFood.setFont(MEAL_FONT);
		lblMainFood.setBounds(20, 51, 293, 31);
		getContentPane().add(lblMainFood);

		JLabel mainFood1 = new JLabel("1.");
		mainFood1.setHorizontalAlignment(SwingConstants.CENTER);
		mainFood1.setForeground(FOREGROUND);
		mainFood1.setFont(FOOD_FONT);
		mainFood1.setBounds(69, 92, 37, 31);
		getContentPane().add(mainFood1);

		comboBox1 = new JComboBox<CBBItem>();
		comboBox1.addItem(new CBBItem(0, "Không có"));
		comboBox1.setFont(FOOD_FONT);
		comboBox1.setBackground(FOREGROUND);
		comboBox1.setForeground(BACKGROUND);
		comboBox1.setBounds(116, 92, 193, 28);
		comboBox1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(comboBox1);

		comboBox2 = new JComboBox<CBBItem>();
		comboBox2.addItem(new CBBItem(0, "Không có"));
		comboBox2.setFont(FOOD_FONT);
		comboBox2.setBackground(FOREGROUND);
		comboBox2.setForeground(BACKGROUND);
		comboBox2.setBounds(116, 130, 193, 28);
		comboBox2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(comboBox2);

		JLabel mainFood2 = new JLabel("2.");
		mainFood2.setHorizontalAlignment(SwingConstants.CENTER);
		mainFood2.setForeground(FOREGROUND);
		mainFood2.setFont(FOOD_FONT);
		mainFood2.setBounds(69, 130, 37, 31);
		getContentPane().add(mainFood2);

		comboBox3 = new JComboBox<CBBItem>();
		comboBox3.addItem(new CBBItem(0, "Không có"));
		comboBox3.setFont(FOOD_FONT);
		comboBox3.setBackground(FOREGROUND);
		comboBox3.setForeground(BACKGROUND);
		comboBox3.setBounds(116, 168, 193, 28);
		comboBox3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(comboBox3);

		JLabel mainFood3 = new JLabel("3.");
		mainFood3.setHorizontalAlignment(SwingConstants.CENTER);
		mainFood3.setForeground(FOREGROUND);
		mainFood3.setFont(FOOD_FONT);
		mainFood3.setBounds(69, 168, 37, 31);
		getContentPane().add(mainFood3);

		comboBox4 = new JComboBox<CBBItem>();
		comboBox4.addItem(new CBBItem(0, "Không có"));
		comboBox4.setFont(FOOD_FONT);
		comboBox4.setBackground(FOREGROUND);
		comboBox4.setForeground(BACKGROUND);
		comboBox4.setBounds(116, 206, 193, 28);
		comboBox4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(comboBox4);

		JLabel mainFood4 = new JLabel("4.");
		mainFood4.setHorizontalAlignment(SwingConstants.CENTER);
		mainFood4.setForeground(FOREGROUND);
		mainFood4.setFont(FOOD_FONT);
		mainFood4.setBounds(69, 206, 37, 31);
		getContentPane().add(mainFood4);

		comboBox5 = new JComboBox<CBBItem>();
		comboBox5.addItem(new CBBItem(0, "Không có"));
		comboBox5.setBounds(116, 244, 193, 28);
		comboBox5.setFont(FOOD_FONT);
		comboBox5.setBackground(FOREGROUND);
		comboBox5.setForeground(BACKGROUND);
		comboBox5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(comboBox5);

		JLabel mainFood5 = new JLabel("5.");
		mainFood5.setHorizontalAlignment(SwingConstants.CENTER);
		mainFood5.setForeground(FOREGROUND);
		mainFood5.setFont(FOOD_FONT);
		mainFood5.setBounds(69, 244, 37, 31);
		getContentPane().add(mainFood5);

		List<CBBItem> cbbs = Food.getIdAndName(true);
		for (CBBItem cbb : cbbs) {
			comboBox1.addItem(cbb);
			comboBox2.addItem(cbb);
			comboBox3.addItem(cbb);
			comboBox4.addItem(cbb);
			comboBox5.addItem(cbb);
		}

		JLabel lblSubMeal = new JLabel("Bữa phụ:");
		lblSubMeal.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubMeal.setForeground(FOREGROUND);
		lblSubMeal.setFont(MEAL_FONT);
		lblSubMeal.setBounds(20, 285, 293, 31);
		getContentPane().add(lblSubMeal);

		comboBox6 = new JComboBox<CBBItem>();
		comboBox6.addItem(new CBBItem(0, "Không có"));
		comboBox6.setFont(FOOD_FONT);
		comboBox6.setBackground(FOREGROUND);
		comboBox6.setForeground(BACKGROUND);
		comboBox6.setBounds(116, 315, 193, 28);
		comboBox6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(comboBox6);
		List<CBBItem> cbbsubs = Food.getIdAndName(false);
		for (CBBItem cbb : cbbsubs) {
			comboBox6.addItem(cbb);
		}
		
		MyButton btnCancel = new MyButton();
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setRadius(10);
		btnCancel.setForeground(BACKGROUND);
		btnCancel.setBorderColor(new Color(0, 0, 0));
		btnCancel.setColorOver(new Color(230, 230, 230));
		btnCancel.setText("Hủy");
		btnCancel.setFocusPainted(false);
		btnCancel.setBounds(221, 439, 118, 31);
		getContentPane().add(btnCancel);

		MyButton btnSave = new MyButton();
		btnSave.setText("Lưu");
		btnSave.setFont(BUTTON_FONT);
		btnSave.setColor(FOREGROUND);
		btnSave.setForeground(BACKGROUND);
		btnSave.setFocusPainted(false);
		btnSave.setRadius(10);
		btnSave.setColorOver(new Color(237, 225, 222));
		btnSave.setBorderColor(Color.BLACK);
		btnSave.setBounds(10, 439, 210, 31);
		
		JPanel separator = new JPanel();
		separator.setBackground(FOREGROUND);
		separator.setBounds(10, 427, 329, 3);
		getContentPane().add(separator);
		
		JLabel lblcThmVo = new JLabel("Được thêm vào tháng sau:");
		lblcThmVo.setHorizontalAlignment(SwingConstants.LEFT);
		lblcThmVo.setForeground(new Color(244, 203, 198));
		lblcThmVo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		lblcThmVo.setBounds(20, 353, 293, 31);
		getContentPane().add(lblcThmVo);
		
		ButtonGroup btng = new ButtonGroup();
		rdbtnYes = new JRadioButton("Có");
		rdbtnYes.setFont(MEAL_FONT);
		rdbtnYes.setForeground(FOREGROUND);
		rdbtnYes.setBackground(BACKGROUND);
		rdbtnYes.setFocusPainted(false);
		rdbtnYes.setBounds(101, 390, 103, 31);
		btng.add(rdbtnYes);
		getContentPane().add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("Không");
		rdbtnNo.setFont(MEAL_FONT);
		rdbtnNo.setForeground(FOREGROUND);
		rdbtnNo.setBackground(BACKGROUND);
		rdbtnNo.setFocusPainted(false);
		rdbtnNo.setBounds(206, 390, 103, 31);
		btng.add(rdbtnNo);
		getContentPane().add(rdbtnNo);
		
		btnCancel.addActionListener(mectl);
		btnSave.addActionListener(mectl);
		
		if (!isNew)
			setComboBox();
		if (!isEditable) {
			comboBox1.setEnabled(false);
			comboBox2.setEnabled(false);
			comboBox3.setEnabled(false);
			comboBox4.setEnabled(false);
			comboBox5.setEnabled(false);
			comboBox6.setEnabled(false);
		}
		if (isEditable || editableActive) 
			getContentPane().add(btnSave);
		setVisible(true);
	}
	
	public void setComboBox() {
		title.setText("THỰC ĐƠN " + this.menu.getMenu_id());
		List<Food> foods = mectl.selectFoodsOfMenu(menu.getMenu_id());
		int count = 1;
		for (Food food : foods) {
			int id = food.getFood_id();
			if (food.getCategory()) {
				if (count == 1)
					comboBox1.setSelectedItem(new CBBItem(id, ""));
				else if (count == 2)
					comboBox2.setSelectedItem(new CBBItem(id, ""));
				else if (count == 3)
					comboBox3.setSelectedItem(new CBBItem(id, ""));
				else if (count == 4)
					comboBox4.setSelectedItem(new CBBItem(id, ""));
				else
					comboBox5.setSelectedItem(new CBBItem(id, ""));
				count++;
			} else {
				comboBox6.setSelectedItem(new CBBItem(id, ""));
			}
		}
		if (this.menu.isActive()) {
			this.rdbtnYes.setSelected(true);
			if (!editableActive)
				rdbtnNo.setEnabled(false);
		} else {
			this.rdbtnNo.setSelected(true);
			if (!editableActive)
				rdbtnYes.setEnabled(false);
		}
	}
	
	public boolean checkBeforeSave() {
		int[] selections = new int[] {
			((CBBItem) comboBox1.getSelectedItem()).getValue(),
			((CBBItem) comboBox2.getSelectedItem()).getValue(),
			((CBBItem) comboBox3.getSelectedItem()).getValue(),
			((CBBItem) comboBox4.getSelectedItem()).getValue(),
			((CBBItem) comboBox5.getSelectedItem()).getValue()
		};
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 5; j++) {
				if (selections[i] == selections[j])
					return false;
			}
		}
		return true;
	}
	
	private int getFoodID(JComboBox<CBBItem> cbb) {
		CBBItem food = (CBBItem) cbb.getSelectedItem();
		int dot = food.getValue();
		return dot;
	}
	
	public Menu getMenu() {
		if (menu == null) {
			this.menu = new Menu();
			this.menu.setMenu_id(mectl.selectNewIdFood());
		}
		List<Integer> food_ids = new ArrayList<Integer>();
		int food_id = getFoodID(comboBox1);
		if (food_id != 0) {
			food_ids.add(food_id);
		}
		food_id = getFoodID(comboBox2);
		if (food_id != 0) {
			food_ids.add(food_id);
		}
		food_id = getFoodID(comboBox3);
		if (food_id != 0) {
			food_ids.add(food_id);
		}
		food_id = getFoodID(comboBox4);
		if (food_id != 0) {
			food_ids.add(food_id);
		}
		food_id = getFoodID(comboBox5);
		if (food_id != 0) {
			food_ids.add(food_id);
		}
		food_id = getFoodID(comboBox6);
		if (food_id != 0) {
			food_ids.add(food_id);
		}
		menu.setFood_ids(food_ids);
		menu.setActive(this.rdbtnYes.isSelected());
		return menu;
	}
	
	public boolean getIsNew() {
		return this.isNew;
	}
	
	public boolean isEditableActive() {
		return this.editableActive;
	}
}
