package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dao.BoardingFeeDAO;
import dao.GlobalDAO;
import dao.InvoiceDAO;
import dao.StudentDAO;
import model.BoardingClass;
import model.BoardingFee;
import model.CBBItem;
import model.Invoice;
import model.Student;
import view.ListInvoiceByBClass;

public class ListInvoicesByBClassCTL implements ActionListener {
	ListInvoiceByBClass libb;

	public ListInvoicesByBClassCTL(ListInvoiceByBClass libb) {
		this.libb = libb;
	}

	public BoardingFee getBoardingFeeById(int boardingFee_id) {
		return BoardingFeeDAO.getInstance().selectById(boardingFee_id);
	}

	public Student getStudentById(int student_id) {
		return StudentDAO.getInstance().selectById(student_id);
	}

	public List<CBBItem> getBoardingClass_idAndName() {
		return BoardingClass.getBoardingClass_idAndName();
	}

	public String getNameStudentById(int student_id) {
		String result = "Không có";
		List<String> ids = GlobalDAO.getInstance().search("student", "name", "student_id=\'" + student_id + "\'", 1);
		if (ids.size() != 0)
			result = ids.get(0);
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Đóng"))
			libb.dispose();
		else if (e.getSource() == libb.cbClass) {
			List<Invoice> invoices = new ArrayList<Invoice>();
			int bClass_id = ((CBBItem) libb.cbClass.getSelectedItem()).getValue();
			int bFee_id = libb.getBoardingFee_id();
			List<Integer> student_ids = StudentDAO.getInstance().selectByBoardingClass_id(bClass_id);
			for (int id : student_ids) {
				Invoice iv = InvoiceDAO.getInstance().selectByBoardingFeeIdandStudentID(bFee_id, id);
				if (iv != null)
					invoices.add(iv);
			}
			this.libb.addRowsInvoice(invoices);
		} else if (src.equals("Xuất văn bảng")) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnal = jfc.showSaveDialog(libb);
				if (returnal == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					WordTableExam.makeListClass(libb.getBoardingFee_id(), file.getPath());
					JOptionPane.showMessageDialog(libb, "Lưu thành công!");
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}

		}
	}

}
