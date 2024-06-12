package model;

import java.util.ArrayList;
import java.util.List;

import dao.BoardingClassDAO;
import dao.GlobalDAO;

public class BoardingClass {
	private int boardingClass_id;
	private String name;
	private int numberOfBed;
	private String room;
	private int teacher_id;
	private List<Integer> student_ids;

	public BoardingClass() {
		super();
	}

	public BoardingClass(int boardingClass_id, String name, int numberOfBed, String room, int teacher_id,
			List<Integer> student_ids) {
		super();
		this.boardingClass_id = boardingClass_id;
		this.name = name;
		this.numberOfBed = numberOfBed;
		this.room = room;
		this.teacher_id = teacher_id;
		this.student_ids = student_ids;
	}

	public int getBoardingClass_id() {
		return boardingClass_id;
	}

	public void setBoardingClass_id(int boardingClass_id) {
		this.boardingClass_id = boardingClass_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfBed() {
		return numberOfBed;
	}

	public void setNumberOfBed(int numberOfBed) {
		this.numberOfBed = numberOfBed;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public List<Integer> getStudent_ids() {
		return student_ids;
	}

	public void setStudent_ids(List<Integer> student_ids) {
		this.student_ids = student_ids;
	}

	public static List<CBBItem> getBoardingClass_idAndName() {
		List<CBBItem> result = new ArrayList<CBBItem>();
		List<BoardingClass> bdcs = BoardingClassDAO.getInstance().selectAll();
		for (BoardingClass bdc : bdcs) {
			result.add(new CBBItem(bdc.getBoardingClass_id(), bdc.getBoardingClass_id() + ". " + bdc.getName()));
		}
		return result;
	}
	
	public static List<CBBItem> getIdAndNameNonTeacher() {
		List<CBBItem> result = new ArrayList<CBBItem>();
		List<String> allIds = GlobalDAO.getInstance().search("boardingClass", "boardingClass_id", null, 0);
		List<String> boardingClass_ids = GlobalDAO.getInstance().search("teacher", "boardingClass_id", null, 0);
		for (String id : allIds) {
			if (!boardingClass_ids.contains(id)) {
				String name = GlobalDAO.getInstance().search("boardingClass", "name", "boardingClass_id=\'" + id + "\'", 1).get(0);
				result.add(new CBBItem(Integer.parseInt(id), name));
			}
		}
		return result;
	}
	
	public static List<CBBItem> getIdAndNameVacancy() {
		List<CBBItem> result = new ArrayList<CBBItem>();
		List<BoardingClass> allBc = BoardingClassDAO.getInstance().selectAll();
		for (BoardingClass bc : allBc) {
			if (bc.getNumberOfBed() > bc.getStudent_ids().size()) {
				result.add(new CBBItem(bc.getBoardingClass_id(), bc.getName()));
			}
		}
		return result;
	}
}
