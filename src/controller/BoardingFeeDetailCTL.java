package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import dao.BoardingFeeDAO;
import dao.GlobalDAO;
import model.BoardingFee;
import model.CBBItem;
import view.AdminScreen;
import view.BoardingFeeDetail;

public class BoardingFeeDetailCTL implements ActionListener {
	private BoardingFeeDetail bfd;
	private AdminScreen ads;

	public BoardingFeeDetailCTL(BoardingFeeDetail bfd, AdminScreen ads) {
		this.bfd = bfd;
		this.ads = ads;
	}

	public BoardingFee selectBoardingFeeById(int boardingFee_id) {
		return BoardingFeeDAO.getInstance().selectById(boardingFee_id);
	}

	public int selectNewIdBoardingFee() {
		return GlobalDAO.getInstance().getAuto_IncrementOf("boardingFee");
	}

	public int getLastIdOfBoardingFee() {
		return GlobalDAO.getInstance().getLastIDOf("boardingFee");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Đóng")) {
			if (bfd.getIsEditable()) {
				int returnal = JOptionPane.showConfirmDialog(bfd, "Những thay đổi sẽ không được lưu?");
				if (returnal == JOptionPane.YES_OPTION)
					bfd.dispose();
			} else {
				bfd.dispose();
			}
		} else if (src.equals("Lưu")) {
			if (bfd.checkBeforeSave()) {
				BoardingFee bfee = bfd.getBoardingFee();
				boolean check = true;
//				boolean isNew = bfd.getIsNew();
//				boolean check = false;
//				if (isNew) {
//					int countDay = 0;
//					bfee.setNumberDays(countDay);
//					check = BoardingFeeDAO.getInstance().insert(bfee);
//				} else
//					check = BoardingFeeDAO.getInstance().update(bfee);
				if (check) {
					JOptionPane.showMessageDialog(bfd, "Phân thực đơn cho tháng mới để hoàn tất!");
					this.bfd.dispose();
					LocalDate date = bfee.getStart_day().toLocalDate();
					this.ads.comboBoxBFee.addItem(new CBBItem(bfee.getBoardingFee_id(),
							bfee.getBoardingFee_id() + ". Tháng " + date.getMonthValue() + " năm " + date.getYear()));
					this.ads.comboBoxEHis.addItem(new CBBItem(bfee.getBoardingFee_id(),
							bfee.getBoardingFee_id() + ". Tháng " + date.getMonthValue() + " năm " + date.getYear()));
					ads.setCardRight("eatingHistory");
					ads.comboBoxEHis.setSelectedIndex(ads.comboBoxEHis.getItemCount() - 1);
					ads.setEnableButtonEHis(false, true);
				} else
					JOptionPane.showMessageDialog(bfd, "Lưu không thành công, vui lòng thử lại");
			} else {
				JOptionPane.showMessageDialog(ads, "Vui lòng kiểm tra lại thông tin");
			}
		}
	}
}
