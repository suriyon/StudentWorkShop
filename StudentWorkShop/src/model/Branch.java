package model;

public class Branch {
	private String branchId;
	private String branchName;
	private String facultyId;
	public Branch() {
		super();
	}
	public Branch(String branchId, String branchName, String facultyId) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.facultyId = facultyId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}
}
