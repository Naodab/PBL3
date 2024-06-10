package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import dao.FoodDAO;
import dao.GlobalDAO;
import dao.MenuDAO;
import model.Food;
import model.Menu;
import view.EatingHistoryDetail;

public class EHisDetailCTL implements ActionListener {
	private EatingHistoryDetail ehisd;

	public EHisDetailCTL(EatingHistoryDetail ehisd) {
		this.ehisd = ehisd;
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
	
	public List<String> selectListMenu_idActive() {
		return GlobalDAO.getInstance().search("menu", "menu_id", "active is TRUE", 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Hủy")) {
			ehisd.dispose();
		} else if (src.equals("Lưu")) {
			String selection = ehisd.comboMenuID.getSelectedItem() + "";
			int menu_id = Integer.parseInt(selection);
			ehisd.setMenuID(menu_id);
			this.ehisd.dispose();
		} else if (e.getSource() == ehisd.comboMenuID) {
			String selection = ehisd.comboMenuID.getSelectedItem() + "";
			int menu_id = Integer.parseInt(selection);
			this.ehisd.setComboBox(menu_id);
		}
	}

}
