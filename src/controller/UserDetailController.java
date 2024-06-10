package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.GlobalDAO;
import dao.UserDAO;
import model.User;
import view.UserDetail;

public class UserDetailController implements ActionListener {
	private UserDetail ud;

	public UserDetailController(UserDetail ud) {
		this.ud = ud;
	}

	public String selectUserId(String object, String username) {
		return GlobalDAO.getInstance().search(object, object + "_id", "phoneNumber=\'" + username + "\'", 1).get(0);
	}
	
	public User selectUserByUsername(String username) {
		return UserDAO.getInstance().selectByUserName(username);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Đóng")) {
			if (ud.getIsEditable()) {
				int returnal = JOptionPane.showConfirmDialog(ud, "Những thay đổi sẽ không được lưu!");
				if (returnal == JOptionPane.YES_OPTION)
					this.ud.dispose();
			} else
				this.ud.dispose();
		} else if (src.equals("Lưu")) {
			User user = ud.getUser();
			UserDAO.getInstance().update(user);
			JOptionPane.showMessageDialog(ud, "Lưu thành công!");
			this.ud.dispose();
		}
	}

}
