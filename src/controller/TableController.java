package controller;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import custom.MyTable;
import dao.BoardingClassDAO;
import dao.EatingHistoryDAO;
import dao.FoodDAO;
import dao.GlobalDAO;
import dao.MenuDAO;
import dao.ParentsDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import dao.UserDAO;
import model.BoardingClass;
import model.EatingHistory;
import model.Food;
import model.Parents;
import model.Teacher;
import model.Menu;
import view.AdminScreen;
import view.DetailBoardingClass;
import view.EatingHistoryDetail;
import view.FoodDetail;
import view.InvoiceView;
import view.MenuDetail;
import view.PersonDetail;
import view.UserDetail;

public class TableController implements TableActionEvent {
	private DefaultTableModel model;
	private MyTable table;
	private AdminScreen ad;
	private String object;

	public TableController(MyTable table, AdminScreen ad, String object) {
		this.table = table;
		this.model = (DefaultTableModel) table.getModel();
		this.ad = ad;
		this.object = object;
	}

	public void setDefaultTable(DefaultTableModel model) {
		this.model = model;
	}

	@Override
	public void onEdit(int row) {
		table.clearSelection();
		int id = 0;
		if (!object.equals("User") && !object.equals("EatingHistory"))
			id = Integer.parseInt(model.getValueAt(row, 1) + "");
		if (object.equals("BoardingClass")) {
			new DetailBoardingClass(id, true);
		} else if (object.equals("Food")) {
			Food food = FoodDAO.getInstance().selectById(id);
			if (food.getMenu_ids().size() > 0) {
				JOptionPane.showMessageDialog(ad, "Không thể cập nhật món ăn!");
			} else {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new FoodDetail(id, true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		} else if (object.equals("User")) {
			String username = model.getValueAt(row, 1) + "";
			new UserDetail(username, true);
		} else if (object.equals("EatingHistory")) {
			int menu_id = Integer.parseInt(model.getValueAt(row, 2) + "");
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				EatingHistoryDetail ehisd = new EatingHistoryDetail(menu_id, true);
				menu_id = ehisd.getMenuID();
				model.setValueAt(menu_id, row, 2);
				Menu menu = MenuDAO.getInstance().selectById(menu_id);
				String mainFood = "";
				String subFood = "";
				for (int food_id : menu.getFood_ids()) {
					Food food = FoodDAO.getInstance().selectById(food_id);
					if (food.getCategory())
						mainFood += food.getName() + ", ";
					else
						subFood = food.getName();
				}
				model.setValueAt(mainFood, row, 3);
				model.setValueAt(subFood, row, 4);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		} else if (object.equals("Menu")) {
			Menu menu = MenuDAO.getInstance().selectById(id);
			if (menu.getEatingHistory_ids() != null) {
				boolean checkMenu = true;
				LocalDate now = LocalDate.now();
				for (int eHis_id : menu.getEatingHistory_ids()) {
					EatingHistory ehis = EatingHistoryDAO.getInstance().selectById(eHis_id);
					if (now.compareTo(ehis.getEating_day().toLocalDate()) >= 0) {
						checkMenu = false;
						break;
					}
				}
				if (checkMenu) {
					new MenuDetail(id, true, true);
				} else {
					new MenuDetail(id, false, true);
				}
			} else
				new MenuDetail(id, true, true);
		} else {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new PersonDetail(id, object, true);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
		this.ad.adminController.doAddMore();
	}

	@Override
	public void onDelete(int row) {
		if (table.isEditing()) {
			table.getCellEditor().stopCellEditing();
		}
		table.clearSelection();
		int returnVal = JOptionPane.showConfirmDialog(this.ad, "Bạn chắc chứ?");
		if (returnVal == JOptionPane.YES_OPTION) {
			int id = 0;
			if (!object.equals("User") && !object.equals("EatingHistory"))
				id = Integer.parseInt(model.getValueAt(row, 1) + "");
			if (object.equals("User"))
				JOptionPane.showMessageDialog(ad, "Chỉ có thể khóa tài khoản!");
			boolean check = false;
			if (object.equals("Student")) {
				check = StudentDAO.getInstance().deleteByID(id);
			} else if (object.equals("Parents")) {
				int result = JOptionPane.showConfirmDialog(this.ad, "Bạn có muốn xóa các con của phụ huynh này không?");
				Parents tmp = ParentsDAO.getInstance().selectById(id);
				if (result == JOptionPane.YES_OPTION) {
					List<Integer> childs = tmp.getStudent_id();
					for (int child : childs)
						StudentDAO.getInstance().deleteByID(child);
				} else if (result == JOptionPane.NO_OPTION) {
					List<Integer> student_ids = StudentDAO.getInstance().selectByParents_id(id);
					for (Integer student_id : student_ids)
						check = GlobalDAO.getInstance().updateNULLForeignKey("student", "parents_id",
								"student_id=\'" + student_id + "\'");
				}
				check = ParentsDAO.getInstance().deleteByID(id);
				UserDAO.getInstance().delete(tmp.getPhoneNumber());
			} else if (object.equals("Teacher")) {
				Teacher tc = TeacherDAO.getInstance().selectById(id);
				UserDAO.getInstance().delete(tc.getPhoneNumber());
				check = TeacherDAO.getInstance().deleteById(id);
			} else if (object.equals("BoardingClass")) {
				BoardingClass bc = BoardingClassDAO.getInstance().selectById(id);
				check = BoardingClassDAO.getInstance().delete(bc);
			} else if (object.equals("EatingHistory")) {
				check = true;
			} else if (object.equals("Food")) {
				Food food = FoodDAO.getInstance().selectById(id);
				if (food.getMenu_ids().size() > 0) {
					JOptionPane.showMessageDialog(ad, "Không thể xóa món ăn!");
					check = false;
				} else
					check = FoodDAO.getInstance().delete(food);
			} else if (object.equals("Menu")) {
				Menu menu = MenuDAO.getInstance().selectById(id);
				if (menu.getEatingHistory_ids().size() > 0) {
					JOptionPane.showMessageDialog(ad, "Không thể xóa thực đơn này vì thực đơn đã được sử dụng!");
				} else {
					check = MenuDAO.getInstance().delete(menu);
				}
			}
			if (check) {
				String path = System.getProperty("user.dir") + "/avatar/" + object + "/" + id + ".jpg";
				File file = new File(path);
				if (file.exists()) {
					file.delete();
				}
				model.removeRow(row);
			}
			String str = (check ? "Xóa thành công!" : "Xóa không thành công!");
			JOptionPane.showMessageDialog(this.ad, str);
		}
	}

	@Override
	public void onView(int row) {
		int id = 0;
		if (!object.equals("User") && !object.equals("EatingHistory"))
			id = Integer.parseInt(model.getValueAt(row, 1) + "");
		if (object.equals("BoardingClass")) {
			new DetailBoardingClass(id, false);
		} else if (object.equals("Food")) {
			new FoodDetail(id, false);
		} else if (object.equals("Menu")) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new MenuDetail(id, false, false);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		} else if (object.equals("Invoice")) {
			new InvoiceView(id);
		} else if (object.equals("User")) {
			String username = model.getValueAt(row, 1) + "";
			new UserDetail(username, false);
		} else if (object.equals("EatingHistory")) {
			JOptionPane.showMessageDialog(ad, "Mọi thông tin đã được hiển thị");
		} else {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new PersonDetail(id, object, false);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
	}

}
