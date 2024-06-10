package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.FoodDAO;
import dao.GlobalDAO;
import model.Food;
import view.FoodDetail;

public class FoodDetailCTL implements ActionListener {
	private FoodDetail fd;

	public FoodDetailCTL(FoodDetail fd) {
		this.fd = fd;
	}
	
	public Food selectFoodById(int food_id) {
		return FoodDAO.getInstance().selectById(food_id);
	}
	
	public int getNewIdFood() {
		return GlobalDAO.getInstance().getAuto_IncrementOf("food");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Đóng")) {
			this.fd.dispose();
		} else if (src.equals("Lưu")) {
			if (this.fd.checkBeforeSave()) {
				Food food = this.fd.getFood();
				if (this.fd.getIsNew()) {
					FoodDAO.getInstance().insert(food);
					JOptionPane.showMessageDialog(fd, "Thêm thành công! Vui lòng nhấn \"Xem thêm\" để làm mới!");
				} else {
					FoodDAO.getInstance().update(food);
					JOptionPane.showMessageDialog(fd, "Cập nhật thành công! Vui lòng nhấn \"Xem thêm\" để làm mới!");
				}
				this.fd.dispose();
			} else {
				JOptionPane.showMessageDialog(this.fd, "Thông tin lưu không phù hợp, vui lòng kiểm tra lại.");
			}
		}
	}

}
