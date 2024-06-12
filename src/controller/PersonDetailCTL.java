package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import dao.BoardingClassDAO;
import dao.GlobalDAO;
import dao.ParentsDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import dao.UserDAO;
import model.BoardingClass;
import model.Parents;
import model.Student;
import model.Teacher;
import model.User;
import view.AbsenceDetail;
import view.PersonDetail;

public class PersonDetailCTL implements ActionListener {
//	private final static String[] ERRORS = new String[] { "Vui lòng nhập tên.", "Họ và tên không hợp lệ.",
//			"Vui lòng nhập ngày sinh", "Ngày sinh không hợp lệ", "Vui lòng nhập tháng sinh", "Tháng sinh không hợp lệ",
//			"Vui lòng nhập năm sinh", "Năm sinh không hợp lệ", "Ngày, tháng, năm sinh không hợp lệ",
//			"Vui lòng chọn giới tính", "Vui lòng nhập địa chỉ", "Vui lòng chọn mã phụ huynh",
//			"Vui lòng chọn mã lớp học", "Vui lòng nhập cân nặng", "Cân nặng không hợp lệ", "Vui lòng nhập chiều cao",
//			"Chiều cao không hợp lệ", "Vui lòng chọn ăn phụ hay không", "Vui lòng nhập email",
//			"Vui lòng nhập số điện thoại", "Vui lòng chọn ảnh thẻ" };
	private PersonDetail personDetail;
	private File avatar;

	public PersonDetailCTL(PersonDetail personDetail) {
		this.personDetail = personDetail;
	}

	public Parents selectParentsById(int parents_id) {
		return ParentsDAO.getInstance().selectById(parents_id);
	}

	public Teacher selectTeacherById(int teacher_id) {
		return TeacherDAO.getInstance().selectById(teacher_id);
	}

	public Student selectStudentById(int student_id) {
		return StudentDAO.getInstance().selectById(student_id);
	}

	public int getAmountAbsence(int student_id) {
		return GlobalDAO.getInstance().getSizeOf("absence", "student_id=\'" + student_id + "\'");
	}
	
	public int getNewIdOf(String object) {
		return GlobalDAO.getInstance().getAuto_IncrementOf(object);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Đóng")) {
			this.personDetail.dispose();
		} else if (src.equals("Lưu")) {
			String error = this.personDetail.check();
			if (error.equals("")) {
				String object = this.personDetail.getObject();
				if (this.personDetail.getIsNew()) {
					if (avatar != null) {
						String path = System.getProperty("user.dir") + "/avatar/" + object + "/"
								+ this.personDetail.getId() + ".jpg";
						File ava = new File(path);
						if (ava.exists())
							ava.delete();
						try {
							Files.copy(avatar.toPath(), ava.toPath());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}

					if (!object.equals("Student") && personDetail.getIsNew()) {
						User user = UserDAO.getInstance().selectByUserName(this.personDetail.getPhoneNumber());
						if (user == null) {
							int result = JOptionPane.showConfirmDialog(this.personDetail,
									"Bạn có muốn tạo tài khoản không?");
							if (result == JOptionPane.YES_OPTION) {
								user = new User();
								user.setUsername(this.personDetail.getPhoneNumber());
								user.setAvtive(true);
								user.setPosition(object);
								user.setLastLogin(Date.valueOf(LocalDate.now()));
								UserDAO.getInstance().insert(user);
								this.personDetail.dispose();
							} else if (result == JOptionPane.NO_OPTION) {
								this.personDetail.dispose();
							}
						} else {
							this.personDetail.dispose();
						}
					} else {
						this.personDetail.dispose();
					}
					if (object.equals("Student")) {
						Student std = this.personDetail.getStudent();
						StudentDAO.getInstance().insert(std);
					} else if (object.equals("Parents")) {
						Parents parents = this.personDetail.getParents();
						ParentsDAO.getInstance().insert(parents);
					} else if (object.equals("Teacher")) {
						Teacher teacher = this.personDetail.getTeacher();
						TeacherDAO.getInstance().insert(teacher);
					}
					JOptionPane.showMessageDialog(this.personDetail,
							"Tạo thành công! Vui lòng nhấn \"Xem thêm\" để làm mới!");
				} else {
					if (avatar != null) {
						String path = System.getProperty("user.dir") + "/avatar/" + object + "/"
								+ this.personDetail.getId() + ".jpg";
						File ava = new File(path);
						if (ava.exists())
							ava.delete();
						try {
							Files.copy(avatar.toPath(), ava.toPath());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}

					if (object.equals("Student")) {
						Student std = this.personDetail.getStudent();
						StudentDAO.getInstance().update(std);
					} else if (object.equals("Parents")) {
						ParentsDAO.getInstance().update(this.personDetail.getParents());
					} else if (object.equals("Teacher")) {
						Teacher teacher = this.personDetail.getTeacher();
						TeacherDAO.getInstance().update(teacher);
					}
					JOptionPane.showMessageDialog(this.personDetail,
							"Cập nhật thành công! Vui lòng nhấn \"Xem thêm\" để làm mới!");
					this.personDetail.dispose();
				}
			}
		} else if (src.equals("+") || src.equals("")) {
			if (this.personDetail.getIsEditable()) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(this.personDetail);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					String name = file.getName();
					if (!name.endsWith(".jpg")) {
						JOptionPane.showMessageDialog(this.personDetail, "Vui lòng chọn ảnh định dạng jpg!");
					} else {
						this.avatar = file;
						this.personDetail.setAvatar(file.getPath());
					}
				}
			}
		} else if (src.equals("Chi tiết")) {
			new AbsenceDetail(Integer.parseInt(this.personDetail.getId()));
		}
	}

	public BoardingClass getBoardingClassByID(int boardingClass_id) {
		return BoardingClassDAO.getInstance().selectById(boardingClass_id);
	}
}