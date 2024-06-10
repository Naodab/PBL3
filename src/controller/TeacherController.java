package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.AbsenceDAO;
import dao.BoardingClassDAO;
import dao.BoardingFeeDAO;
import dao.EatingHistoryDAO;
import dao.FoodDAO;
import dao.GlobalDAO;
import dao.InvoiceDAO;
import dao.MenuDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import model.Absence;
import model.BoardingClass;
import model.BoardingFee;
import model.CBBItem;
import model.EatingHistory;
import model.Food;
import model.Invoice;
import model.Menu;
import model.Student;
import model.Teacher;
import view.ChangePassword;
import view.TeacherScreen;

public class TeacherController implements ActionListener {

	public TeacherScreen teacherController;
	private int feeID;

	public TeacherController(TeacherScreen tcs) {
		this.teacherController = tcs;
	}

	public Teacher selectTeacherById(int teacher_id) {
		return TeacherDAO.getInstance().selectById(teacher_id);
	}

	public Menu selectMenuById(int menu_id) {
		return MenuDAO.getInstance().selectById(menu_id);
	}

	public BoardingClass selectBoardingClassById(int boardingClass_id) {
		return BoardingClassDAO.getInstance().selectById(boardingClass_id);
	}

	public BoardingFee selectBoardingFeeById(int boardingFee_id) {
		return BoardingFeeDAO.getInstance().selectById(boardingFee_id);
	}

	public Invoice selectInvoiceByBfeeIdAndStd_id(int boardingFee_id, int student_id) {
		return InvoiceDAO.getInstance().selectByBoardingFeeIdandStudentID(boardingFee_id, student_id);
	}

	public List<EatingHistory> selectListEhisByBFeeid(int boardingFee_id) {
		List<EatingHistory> result = new ArrayList<EatingHistory>();
		List<Integer> ids = EatingHistoryDAO.getInstance().selectByBoardingFee_id(boardingFee_id);
		for (int id : ids) {
			result.add(EatingHistoryDAO.getInstance().selectById(id));
		}
		return result;
	}

	public int getSizeOfBoardingFee() {
		return GlobalDAO.getInstance().getSizeOf("BoardingFee", "");
	}

	public List<Student> getListStudentOfBoardingClass_id(int boaringClass_id) {
		return StudentDAO.getInstance().selectByBoardingClass_id2(boaringClass_id);
	}

	public int getAmountStudentOfBoardingClass_id(int boardingClass_id) {
		return GlobalDAO.getInstance().getSizeOf("Student", "boardingClass_id=" + boardingClass_id);
	}

	public String getNameParentsById(int parents_id) {
		String result = "Không có";
		List<String> ids = GlobalDAO.getInstance().search("parents", "name", "parents_id=\'" + parents_id + "\'", 1);
		if (ids.size() != 0)
			result = ids.get(0);
		return result;
	}

	public List<Food> selectFoodsOfMenu(int menu_id) {
		List<Food> result = new ArrayList<Food>();
		Menu menu = MenuDAO.getInstance().selectById(menu_id);
		List<Integer> listFoods = menu.getFood_ids();
		for (int id : listFoods) {
			result.add(FoodDAO.getInstance().selectById(id));
		}
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == teacherController.logOut) {
			int result = JOptionPane.showConfirmDialog(teacherController, "Bạn chắc chắn muốn đăng xuất?", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION)
				teacherController.dispose();
		} else if (e.getActionCommand().equals("Thay đổi mật khẩu")) {
			new ChangePassword(teacherController.getUserName());
		} else if (e.getSource() == teacherController.button1) {
			teacherController.isEditable = false;
			teacherController.setCardRight("teacherInformation");
		} else if (e.getSource() == teacherController.buttonEditInforTeacher) {
			teacherController.buttonSaveInforTeacher.setVisible(true);
			teacherController.isEditable = true;
			teacherController.txtEmail.setEditable(true);
			teacherController.txtEmail.setLineColor(Color.RED);
			teacherController.txtAddress.setEditable(true);
			teacherController.txtAddress.setLineColor(Color.RED);
			teacherController.txtPhoneNumber.setEditable(true);
			teacherController.txtPhoneNumber.setLineColor(Color.RED);
		} else if (e.getSource() == teacherController.buttonSaveInforTeacher) {
			teacherController.teacher.setEmail(teacherController.txtEmail.getText());
			teacherController.teacher.setPhoneNumber(teacherController.txtPhoneNumber.getText());
			teacherController.teacher.setAddress(teacherController.txtAddress.getText());
			int result = JOptionPane.showConfirmDialog(teacherController, "Bạn chắc chắn muốn lưu thông tin?",
					"Thông báo", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				TeacherDAO.getInstance().update(teacherController.teacher);
				JOptionPane.showMessageDialog(teacherController, "Đã lưu thông tin thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				teacherController.buttonSaveInforTeacher.setVisible(false);
				teacherController.txtEmail.setEditable(false);
				teacherController.txtEmail.setLineColor(Color.BLACK);
				teacherController.txtAddress.setEditable(false);
				teacherController.txtAddress.setLineColor(Color.BLACK);
				teacherController.txtPhoneNumber.setEditable(false);
				teacherController.txtPhoneNumber.setLineColor(Color.BLACK);
			}
		} else if (e.getSource() == teacherController.button2) {
			teacherController.setCardRight("paymentHistory");
		} else if (e.getSource() == teacherController.jcbbPayment) {
			int value = ((CBBItem) teacherController.jcbbPayment.getSelectedItem()).getValue();
			if (value != 0) {
				feeID = value;
				teacherController.dataPayment.setRowCount(0);
				teacherController.viewPayment(feeID);
				teacherController.buttonSavePayment.setVisible(true);
			} else {
				teacherController.dataPayment.setRowCount(0);
				teacherController.buttonSavePayment.setVisible(false);
			}
		} else if (e.getSource() == teacherController.buttonSavePayment) {
			List<Integer> listCheckStatus = teacherController.listStatus;
			boolean check = true;
			for (int i = 0; i < teacherController.dataPayment.getRowCount(); i++) {
				String str = teacherController.dataPayment.getValueAt(i, 4).toString();
				if (!str.equals("Chưa nộp") && !str.equals("Đã nộp") && !str.equals("Đã in")) {
					System.out.println(teacherController.dataPayment.getValueAt(i, 4).toString());
					JOptionPane.showMessageDialog(teacherController,
							"Thông tin " + "'" + teacherController.dataPayment.getValueAt(i, 4).toString()
									+ "' tại dòng " + (i + 1) + " không hợp lệ!"
									+ "\nBạn chỉ được nhập 'Chưa nộp' hoặc 'Đã nộp'!");
					check = false;
					break;
				}
			}
			if (check) {
				int result = JOptionPane.showConfirmDialog(teacherController, "Bạn chắc chắn muốn lưu thông tin?",
						"Thông báo", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					for (int i = 0; i < teacherController.dataPayment.getRowCount(); i++) {
						if (teacherController.dataPayment.getValueAt(i, 4).toString().equals("Đã nộp")
								&& (listCheckStatus.get(i) == 1 || listCheckStatus.get(i) == 0)) {
							Invoice temp = new Invoice();
							temp.setInvoice_id(
									Integer.parseInt(teacherController.dataPayment.getValueAt(i, 3).toString()));
							temp.setStudent_id(
									Integer.parseInt(teacherController.dataPayment.getValueAt(i, 1).toString()));
							temp.setPayment_date(teacherController.paymentDay);
							temp.setBoardingFee_id(feeID);
							if (teacherController.dataPayment.getValueAt(i, 4).toString().equals("Đã nộp"))
								temp.setStatusPayment((byte) 2);
							boolean execute = InvoiceDAO.getInstance().updateById(temp);
							if (!execute)
								JOptionPane.showMessageDialog(teacherController,
										"Cập nhật thông tin tại dòng thứ " + i + " thất bại!");
						}
					}
					JOptionPane.showMessageDialog(teacherController,
							"Cập nhật thành công các trạng thái nộp tiền của học sinh!");
					teacherController.dataPayment.setRowCount(0);
					teacherController.viewPayment(feeID);
				}
			}
		} else if (e.getSource() == teacherController.jcbbEating) {
			String s = teacherController.jcbbEating.getSelectedItem().toString();
			int id = -1;
			if (s.length() > 0)
				id = Integer.parseInt(teacherController.jcbbEating.getSelectedItem().toString().split(" ")[0]);
			else
				id = 0;
			if (id > 0) {
				teacherController.dataFood.setRowCount(0);
				teacherController.viewFood(id);
			} else {
				teacherController.dataFood.setRowCount(0);
			}
		} else if (e.getSource() == teacherController.button3) {
			teacherController.setCardRight("studentInformation");
		} else if (e.getSource() == teacherController.button4) {
			teacherController.setCardRight("foodHistory");
		} else if (e.getSource() == teacherController.button5) {
			teacherController.setCardRight("monitorAbsence");
		} else if (e.getSource() == teacherController.buttonAbsence) {
			teacherController.setCardRight("monitorAbsence");
		} else if (e.getSource() == teacherController.buttonPhysical) {
			teacherController.setCardRight("monitorPhysical");
		} else if (e.getSource() == teacherController.buttonSaveAbsence) {
			boolean check = false, empty = true;
			for (int i = 0; i < teacherController.dataStudent2.getRowCount(); i++) {
				if (teacherController.dataStudent2.getValueAt(i, 3) != null) {
					int absenceStatus = Integer.parseInt(teacherController.dataStudent2.getValueAt(i, 3) + "");
					if (absenceStatus != 1 && absenceStatus != 0) {
						JOptionPane.showMessageDialog(teacherController,
								"Nhập (1) khi vắng hoặc nhập (0) khi không vắng!");
						check = false;
						break;
					} else {
						check = true;
						empty = false;
					}
				}
			}
			if (check) {
				int result = JOptionPane.showConfirmDialog(teacherController, "Bạn chắc chắn muốn lưu thay đổi?",
						"Thông báo", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					for (int i = 0; i < teacherController.dataStudent2.getRowCount(); i++) {
						if (teacherController.dataStudent2.getValueAt(i, 3) != null) {
							int absenceStatus = Integer.parseInt(teacherController.dataStudent2.getValueAt(i, 3) + "");
							int studentID = Integer.parseInt(teacherController.dataStudent2.getValueAt(i, 1) + "");
							if (absenceStatus != 1 && absenceStatus != 0) {
								JOptionPane.showMessageDialog(teacherController,
										"Nhập (1) khi vắng hoặc nhập (0) khi không vắng!");
								break;
							}
							if (absenceStatus == 1) {
								if (GlobalDAO.getInstance().getSizeOf("absence", "absence_day = "
										+ teacherController.absenceDay + " and student_id = " + studentID) == 0) {
									Absence absence = new Absence(0, teacherController.absenceDay, studentID);
									AbsenceDAO.getInstance().insert(absence);
								}
							}
						}
					}
					JOptionPane.showMessageDialog(teacherController, "Đã cập nhật ngày vắng thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (empty)
				JOptionPane.showMessageDialog(teacherController, "Các trường dữ liệu cần phải cập nhật còn trống!",
						"Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == teacherController.buttonSavePhysical) {
			boolean check = true;
			for (int i = 0; i < teacherController.dataStudent3.getRowCount(); i++) {
				if (teacherController.dataStudent3.getValueAt(i, 5) != null
						&& teacherController.dataStudent3.getValueAt(i, 6) != null) {
					Student t = new Student();
					try {
						t.setStudent_id(Integer.parseInt(teacherController.dataStudent3.getValueAt(i, 1) + ""));
						t.setHeight(Double.parseDouble(teacherController.dataStudent3.getValueAt(i, 5) + ""));
						t.setWeight(Double.parseDouble(teacherController.dataStudent3.getValueAt(i, 6) + ""));
						if (t.getHeight() < 0 || t.getWeight() < 0) {
							JOptionPane.showMessageDialog(teacherController, "Dữ liệu chỉnh sửa chưa phù hợp");
							check = false;
						} else {
							int result = JOptionPane.showConfirmDialog(teacherController,
									"Bạn chắc chắn muốn lưu thay đổi?", "Thông báo", JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION)
								StudentDAO.getInstance().update(t);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(teacherController, "Dữ liệu chỉnh sửa chưa phù hợp");
						check = false;
					}
				} else if (teacherController.dataStudent3.getValueAt(i, 5) != null
						&& teacherController.dataStudent3.getValueAt(i, 6) == null) { // 6 : weight
					Student t = new Student();
					try {
						t.setStudent_id(Integer.parseInt(teacherController.dataStudent3.getValueAt(i, 1) + ""));
						t.setHeight(Double.parseDouble(teacherController.dataStudent3.getValueAt(i, 5) + ""));
						t.setWeight(Double.parseDouble(teacherController.dataStudent3.getValueAt(i, 4) + ""));
						if (t.getHeight() < 0) {
							JOptionPane.showMessageDialog(teacherController, "Dữ liệu chỉnh sửa chưa phù hợp");
							check = false;
						} else {
							int result = JOptionPane.showConfirmDialog(teacherController,
									"Bạn chắc chắn muốn lưu thay đổi?", "Thông báo", JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION)
								StudentDAO.getInstance().update(t);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(teacherController, "Dữ liệu chỉnh sửa chưa phù hợp");
						check = false;
					}
				} else if (teacherController.dataStudent3.getValueAt(i, 5) == null
						&& teacherController.dataStudent3.getValueAt(i, 6) != null) {
					Student t = new Student();
					try {
						t.setStudent_id(Integer.parseInt(teacherController.dataStudent3.getValueAt(i, 1) + ""));
						t.setWeight(Double.parseDouble(teacherController.dataStudent3.getValueAt(i, 6) + ""));
						t.setHeight(Double.parseDouble(teacherController.dataStudent3.getValueAt(i, 3) + ""));
						if (t.getWeight() < 0) {
							JOptionPane.showMessageDialog(teacherController, "Dữ liệu chỉnh sửa chưa phù hợp");
							check = false;
						} else {
							int result = JOptionPane.showConfirmDialog(teacherController,
									"Bạn chắc chắn muốn lưu thay đổi?", "Thông báo", JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION)
								StudentDAO.getInstance().update(t);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(teacherController, "Dữ liệu chỉnh sửa chưa phù hợp");
						check = false;
					}
				}
			}
			if (check) {
				JOptionPane.showMessageDialog(teacherController, "Cập nhật thành công chiều cao và cân nặng!");
				teacherController.dataStudent3.setRowCount(0);
				teacherController.viewStudentPhysical();
			}
		}
	}
}
