package model;

public class Student {
	private String studentId;
	private String studentName;
	private String branchId;
	public Student() {
		super();
	}
	public Student(String studentId, String studentName, String branchId) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.branchId = branchId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
