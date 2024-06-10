package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import dao.GlobalDAO;
import dao.UserDAO;
import model.User;
import view.LogIn;

public class LogInCTL implements ActionListener {
	private User user;
	private LogIn logIn;

	public LogInCTL(LogIn logIn) {
		this.user = new User();
		this.logIn = logIn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Đăng nhập")) {
			this.user = UserDAO.getInstance().selectByUserName(this.logIn.getUsername());
			if (user == null) {
				this.logIn.setWarning("TÀI KHOẢN HOẶC MẬT KHẢU KHÔNG HỢP LỆ!");
			} else if (!this.user.getPassword().equals(this.logIn.getPassword())
					|| !user.getUsername().equals(this.logIn.getUsername())) {
				this.logIn.setWarning("TÀI KHOẢN HOẶC MẬT KHẢU KHÔNG HỢP LỆ!");
			} else if (!user.isAvtive()) {
				this.logIn.setWarning("TÀI KHOẢN ĐÃ BỊ KHÓA!");
			} else {
				user.setLastLogin(Date.valueOf(LocalDate.now()));
				UserDAO.getInstance().update(user);
				if (user.getPosition().equals("Admin"))
					this.logIn.end(3, 2);
				else {
					String id = GlobalDAO.getInstance().search(user.getPosition(), user.getPosition() + "_id",
							"phoneNumber=\'" + user.getUsername() + "\'", 1).get(0);
					if (user.getPosition().equals("Parents"))
						this.logIn.end(1, Integer.parseInt(id));
					else if (user.getPosition().equals("Teacher")) {
						this.logIn.end(2, Integer.parseInt(id));
					}
				}
			}
		}
	}

}