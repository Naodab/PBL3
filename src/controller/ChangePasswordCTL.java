package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.UserDAO;
import model.User;
import view.ChangePassword;

public class ChangePasswordCTL implements ActionListener {
	private ChangePassword cp;

	public ChangePasswordCTL(ChangePassword cp) {
		this.cp = cp;
	}
	
	public User selectByUsername(String username) {
		return UserDAO.getInstance().selectByUserName(username);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Lưu")) {
			if (cp.checkBeforeSaving()) {
				int result = JOptionPane.showConfirmDialog(cp, "Bạn đã chắc chắn muốn đổi mật khẩu?");
				if (result == JOptionPane.YES_OPTION) {
					UserDAO.getInstance().updatePassword(cp.getUser(), cp.txtNewPass.getText());
					JOptionPane.showMessageDialog(cp, "Bạn đã thay đổi mật khẩu thành công");
					this.cp.dispose();
				}
			}
		} else if (src.equals("Đóng")) {
			this.cp.dispose();
		}
	}

}
