package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.FoodDAO;
import dao.GlobalDAO;
import dao.MenuDAO;
import model.Food;
import model.Menu;
import view.MenuDetail;

public class MenuDetailCTL implements ActionListener{
	private MenuDetail me;
	
	public MenuDetailCTL(MenuDetail me) {
		this.me = me;
	}
	
	public List<Food> selectFoodsOfMenu(int menu_id) {
		List<Food> result = new ArrayList<Food>();
		Menu menu = MenuDAO.getInstance().selectById(menu_id);
		List<Integer> listFoods = menu.getFood_ids();
		for (int id : listFoods) {
			result.add(FoodDAO.getInstance().selectById(id));
		}
		return result;
	}
	
	public int selectNewIdFood() {
		return GlobalDAO.getInstance().getAuto_IncrementOf("food");
	}
	
	public Menu selectMenuById(int menu_id) {
		return MenuDAO.getInstance().selectById(menu_id);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Hủy"))
			this.me.dispose();
		else if (e.getActionCommand().equals("Lưu")) {
			String str = "Bạn có muốn lưu không?";
			if (!this.me.checkBeforeSave()) {
				str = "Có những món ăn trùng nhau, bạn có muốn lưu không?";
			}
			int returnal = JOptionPane.showConfirmDialog(this.me, str);
			if (returnal == JOptionPane.YES_OPTION) {
				Menu menu = this.me.getMenu();
				if (this.me.getIsNew()) {
					MenuDAO.getInstance().insert(menu);
				} else {
					if (!this.me.isEditableActive()) {
						MenuDAO.getInstance().update(menu);
					} else
						MenuDAO.getInstance().updateActive(menu.getMenu_id(), me.isActive());
				}
				JOptionPane.showMessageDialog(me, "Lưu thành công! Nhấn \"Xem thêm\" để làm mới dữ liệu!");
				this.me.dispose();
			}
		}
	}

}
