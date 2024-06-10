package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.UserDAO;
import view.ChangeDefaultPassword;

public class ChangeDefaultPasswordCTL implements ActionListener {
	private ChangeDefaultPassword cp;

	public ChangeDefaultPasswordCTL(ChangeDefaultPassword cp) {
		this.cp = cp;
	}
	
	public String getDefaultPass() {
		return UserDAO.getInstance().getDefaultPassword();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Lưu")) {
			if (cp.checkBeforeSaving()) {
				boolean check = false;
				int result = JOptionPane.showConfirmDialog(cp, "Bạn đã chắc chắn muốn đổi mật khẩu?");
				if (result == JOptionPane.YES_OPTION) {
					String newPas = this.cp.txtNewPass.getText();
					check = UserDAO.getInstance().alterDefaultPassword(newPas);
				}
				if (check) {
					JOptionPane.showMessageDialog(cp, "Thay đổi thành công!");
					this.cp.dispose();
				}
			}
		} else if (src.equals("Đóng")) {
			this.cp.dispose();
		}
	}

}
