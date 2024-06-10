package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import dao.AbsenceDAO;
import dao.GlobalDAO;
import model.Absence;
import view.AbsenceDetail;

public class AbsenceCTL implements ActionListener {
	private AbsenceDetail abd;

	public List<Absence> selectAbsenceByStudentId(int student_id) {
		List<Absence> result = new ArrayList<Absence>();
		List<Integer> ids = AbsenceDAO.getInstance().selectByStudentId(student_id);
		for (int id : ids) {
			result.add(AbsenceDAO.getInstance().selectById(id));
		}
		return result;
	}

	public String nameStudent(int student_id) {
		return GlobalDAO.getInstance().search("student", "name", "student_id=\'" + student_id + "\'", 1).get(0);
	}

	public int getAmountAbsence(int student_id) {
		return GlobalDAO.getInstance().getSizeOf("absence", "student_id=\'" + student_id + "\'");
	}

	public AbsenceCTL(AbsenceDetail abd) {
		this.abd = abd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Trở lại")) {
			this.abd.dispose();
		}
	}

}
