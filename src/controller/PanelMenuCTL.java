package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import custom.PanelMenu;
import dao.MenuDAO;
import model.Menu;
import view.MenuDetail;

public class PanelMenuCTL implements ActionListener {
	PanelMenu pm;

	public PanelMenuCTL(PanelMenu pm) {
		this.pm = pm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if(src.equals("Chỉnh sửa")) {
			new MenuDetail(this.pm.getMenuID(), false, false);
		} else if (src.equals("Tắt hoạt động")) {
			Menu menu = this.pm.getMenu();
			menu.setActive(false);
			MenuDAO.getInstance().updateActive(menu.getMenu_id(), false);
			this.pm.setActiveButton(false);
		} else if (src.equals("Bật hoạt động")) {
			Menu menu = this.pm.getMenu();
			menu.setActive(true);
			MenuDAO.getInstance().updateActive(menu.getMenu_id(), true);
			this.pm.setActiveButton(true);
		}
	}

}
