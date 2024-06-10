package custom;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.PanelMenuCTL;
import dao.FoodDAO;
import dao.MenuDAO;
import model.Food;
import model.Menu;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;

public class PanelMenu extends PanelRound {
	private final Color BACKGROUND = new Color(244, 203, 198);
	private final Color BUTTON_COLOR1 = new Color(0, 45, 97);
	private final Color BUTTON_OVER_COLOR1 = new Color(24, 89, 158);
	private final Color BUTTON_COLOR2 = Color.WHITE;
	private final Color BUTTON_OVER_COLOR2 = new Color(230, 230, 230);
	private final int WIDTH = 230;
//	private final int HEIGHT = 300;
	private final int PADDING = 10;
	private final int LINE_HEIGHT = 20;
	private final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 23);
	private final Font MEAL_FONT = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);
	private final Font FOOD_FONT = new Font("Times New Roman", Font.PLAIN, 19);
	private static final long serialVersionUID = 1L;

	private Menu menu;
	private List<Food> mainFood;
	private Food subFood;
	private PanelMenuCTL pmctl;
	private boolean isEditable;
	
	private JTextField lblmainfood5;
	private JTextField lblmainfood1;
	private JTextField lblmainfood2;
	private JTextField lblmainfood3;
	private JTextField lblmainfood4;
	private JTextField lblsubfood1;
	private JLabel lblTitle;
	private MyButton btnEdit_1;
	private MyButton btnEdit;

	/**
	 * Create the panel.
	 * WIDTH = 230, HEIGHT = 300 is the best
	 */
	public PanelMenu() {
		this.isEditable = true;
		initComponents();
	}

	public PanelMenu(int menu_id, boolean isEditable) {
		this.menu = MenuDAO.getInstance().selectById(menu_id);
		this.isEditable = isEditable;
		initFoods();
		initComponents();
	}

	public void initFoods() {
		List<Integer> food_ids = this.menu.getFood_ids();
		mainFood = new ArrayList<Food>();
		subFood = new Food();
		for (int food_id : food_ids) {
			Food food = FoodDAO.getInstance().selectById(food_id);
			if (food.getCategory())
				mainFood.add(food);
			else
				subFood = food;
		}
	}

	public void initComponents() {
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setRadius(20);
		this.setBground(BACKGROUND);
		this.setBorderWidth(2);
		this.setBorderColor(Color.black);
		setLayout(null);
		
		pmctl = new PanelMenuCTL(this);

		lblTitle = new JLabel("TITLE");
		if (this.menu != null)
			lblTitle.setText("Thực đơn " + menu.getMenu_id());
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(TITLE_FONT);
		lblTitle.setBounds(10, 10, 210, 30);
		add(lblTitle);

		JLabel lblLunch = new JLabel("Bữa trưa:");
		lblLunch.setHorizontalAlignment(SwingConstants.LEFT);
		lblLunch.setFont(MEAL_FONT);
		lblLunch.setBounds(PADDING, lblTitle.getY() + lblTitle.getHeight(), 166, LINE_HEIGHT);
		add(lblLunch);

		lblmainfood1 = new JTextField("");
		lblmainfood1.setBorder(null);
		lblmainfood1.setHorizontalAlignment(SwingConstants.LEFT);
		lblmainfood1.setBackground(BACKGROUND);
		lblmainfood1.setFont(FOOD_FONT);
		lblmainfood1.setBounds(60, 65, 120, LINE_HEIGHT);
		add(lblmainfood1);

		lblmainfood2 = new JTextField("");
		lblmainfood2.setBorder(null);
		lblmainfood2.setHorizontalAlignment(SwingConstants.LEFT);
		lblmainfood2.setFont(FOOD_FONT);
		lblmainfood2.setBackground(BACKGROUND);
		lblmainfood2.setSize(lblmainfood1.getSize());
		lblmainfood2.setLocation(lblmainfood1.getX(), lblmainfood1.getY() + lblmainfood1.getHeight());
		add(lblmainfood2);

		lblmainfood3 = new JTextField("");
		lblmainfood3.setBorder(null);
		lblmainfood3.setHorizontalAlignment(SwingConstants.LEFT);
		lblmainfood3.setFont(FOOD_FONT);
		lblmainfood3.setBackground(BACKGROUND);
		lblmainfood3.setSize(lblmainfood2.getSize());
		lblmainfood3.setLocation(lblmainfood2.getX(), lblmainfood2.getY() + lblmainfood2.getHeight());
		add(lblmainfood3);

		lblmainfood4 = new JTextField("");
		lblmainfood4.setBorder(null);
		lblmainfood4.setHorizontalAlignment(SwingConstants.LEFT);
		lblmainfood4.setFont(FOOD_FONT);
		lblmainfood4.setBackground(BACKGROUND);
		lblmainfood4.setSize(lblmainfood3.getSize());
		lblmainfood4.setLocation(lblmainfood3.getX(), lblmainfood3.getY() + lblmainfood3.getHeight());
		add(lblmainfood4);

		lblmainfood5 = new JTextField("");
		lblmainfood5.setBorder(null);
		lblmainfood5.setHorizontalAlignment(SwingConstants.LEFT);
		lblmainfood5.setFont(FOOD_FONT);
		lblmainfood5.setBackground(BACKGROUND);
		lblmainfood5.setSize(lblmainfood4.getSize());
		lblmainfood5.setLocation(lblmainfood4.getX(), lblmainfood4.getY() + lblmainfood4.getHeight());
		add(lblmainfood5);

		JLabel lblSubMeal = new JLabel("Bữa phụ:");
		lblSubMeal.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubMeal.setFont(MEAL_FONT);
		lblSubMeal.setBounds(lblLunch.getX(), lblmainfood5.getY() + lblmainfood5.getHeight(), 166, 30);
		add(lblSubMeal);

		lblsubfood1 = new JTextField("");
		lblsubfood1.setBorder(null);
		lblsubfood1.setHorizontalAlignment(SwingConstants.LEFT);
		lblsubfood1.setFont(FOOD_FONT);
		lblsubfood1.setBackground(BACKGROUND);
		lblsubfood1.setSize(lblmainfood5.getSize());
		lblsubfood1.setLocation(60, 195);
		add(lblsubfood1);

		lblmainfood1.setEditable(false);
		lblmainfood2.setEditable(false);
		lblmainfood3.setEditable(false);
		lblmainfood4.setEditable(false);
		lblmainfood5.setEditable(false);
		lblsubfood1.setEditable(false);

		btnEdit = new MyButton();
		btnEdit.setForeground(BUTTON_COLOR2);
		btnEdit.setText("Tắt hoạt động");
		btnEdit.setRadius(10);
		btnEdit.setColorOver(BUTTON_OVER_COLOR1);
		btnEdit.setColor(BUTTON_COLOR1);
		btnEdit.setFont(FOOD_FONT);
		btnEdit.setBorderColor(BUTTON_COLOR2);
		btnEdit.setFocusPainted(false);
		btnEdit.addActionListener(pmctl);
		btnEdit.setBounds(10, 260, WIDTH - 2 * PADDING, 30);

		btnEdit_1 = new MyButton();
		btnEdit_1.setForeground(BUTTON_COLOR1);
		btnEdit_1.setText("Chỉnh sửa");
		btnEdit_1.setRadius(10);
		btnEdit_1.setColorOver(BUTTON_OVER_COLOR2);
		btnEdit_1.setColor(BUTTON_COLOR2);
		btnEdit_1.setFont(FOOD_FONT);
		btnEdit_1.setFocusPainted(false);
		btnEdit_1.setBorderColor(BUTTON_COLOR1);
		btnEdit_1.addActionListener(pmctl);
		btnEdit_1.setBounds(10, 225, WIDTH - 2 * PADDING, 30);
		
		if (this.menu != null) {
			fillTextField();
			if (!this.menu.isActive()) {
				btnEdit.setText("Bật hoạt động");
			}
		}
		
		if (isEditable) {
			add(btnEdit);
			add(btnEdit_1);
		}
	}
	
	public void resetComboBoxes() {
		lblmainfood1.setText("");
		lblmainfood2.setText("");
		lblmainfood3.setText("");
		lblmainfood4.setText("");
		lblmainfood5.setText("");
		lblsubfood1.setText("");
	}
	
	public void fillTextField() {
		int size = mainFood.size();
		if (size > 0) {
			lblmainfood1.setText(mainFood.get(0).getName());
		}
		if (size > 1) {
			lblmainfood2.setText(mainFood.get(1).getName());
		}
		if (size > 2) {
			lblmainfood3.setText(mainFood.get(2).getName());
		}
		if (size > 3) {
			lblmainfood4.setText(mainFood.get(3).getName());
		}
		if (size > 4) {
			lblmainfood5.setText(mainFood.get(4).getName());
		}
		if (subFood != null) {
			lblsubfood1.setText(subFood.getName());
		}
	}
	
	public int getMenuID() {
		return this.menu.getMenu_id();
	}
	
	public void setTitle(String title) {
		this.lblTitle.setText(title);
	}
	
	public Menu getMenu() {
		return this.menu;
	}
	
	public void setMenu(int menu_id) {
		if (menu_id == 0) {
			resetComboBoxes();
			return;
		}
		this.menu = MenuDAO.getInstance().selectById(menu_id);
		initFoods();
		fillTextField();
	}
	
	public void setActiveButton(boolean active) {
		if (!active)
			btnEdit.setText("Bật hoạt động");
		else
			btnEdit.setText("Tắt hoạt động");
	}
}
