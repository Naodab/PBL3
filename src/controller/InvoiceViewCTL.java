package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dao.BoardingFeeDAO;
import dao.GlobalDAO;
import dao.InvoiceDAO;
import dao.StudentDAO;
import model.BoardingFee;
import model.Invoice;
import model.Student;
import view.InvoiceView;

public class InvoiceViewCTL implements ActionListener {
	public InvoiceView ivv;

	public InvoiceViewCTL(InvoiceView ivv) {
		this.ivv = ivv;
	}

	public Student getStudentById(int student_id) {
		return StudentDAO.getInstance().selectById(student_id);
	}

	public String getNameBoardingClassById(int boardingClass_id) {
		String result = "Không có";
		List<String> ids = GlobalDAO.getInstance().search("boardingClass", "name",
				"boardingClass_id=\'" + boardingClass_id + "\'", 1);
		if (ids.size() != 0)
			result = ids.get(0);
		return result;
	}

	public String getNameTeacherByBoardingClass_id(int boardingClass_id) {
		String result = "Không có";
		List<String> ids = GlobalDAO.getInstance().search("teacher", "name", "boardingClass_id=\'" + boardingClass_id + "\'", 1);
		if (ids.size() != 0)
			result = ids.get(0);
		return result;
	}
	
	public String getNameParentsById(int parents_id) {
		String result = "Không có";
		List<String> ids = GlobalDAO.getInstance().search("parents", "name", "parents_id=\'" + parents_id + "\'", 1);
		if (ids.size() != 0)
			result = ids.get(0);
		return result;
	}

	public BoardingFee getBoardingFeeById(int boardingFee_id) {
		return BoardingFeeDAO.getInstance().selectById(boardingFee_id);
	}

	public Invoice getInvoiceById(int invoice_id) {
		return InvoiceDAO.getInstance().selectById(invoice_id);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Đóng")) {
			ivv.dispose();
		}
	}

}
