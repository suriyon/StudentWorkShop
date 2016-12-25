package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import model.Branch;
import model.Student;
import util.MySQLHelper;

public class StudentDAO {
	public boolean insert(Student student){
		boolean result = false;
		String sql = "insert into student(studentId, studentName, branchId) values(?, ?, ?)";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(3, student.getBranchId());
			ps.setString(2, student.getStudentName());
			ps.setString(1, student.getStudentId());
			
			int row = ps.executeUpdate();
			if(row > 0){
				result = true;
			}
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean update(Student student) {
		boolean result = false;
		String sql = "update student set studentName = ? where studentId = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);			
			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getStudentId());
			
			int row = ps.executeUpdate();
			if(row > 0){
				result = true;
			}
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public Vector selectAll(){
		Vector students = new Vector();
		String sql = "select s.studentId, s.studentName, b.branchName from branch as b, student as s "
				+ " where b.branchId = s.branchId";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();
			while(rs.next()){
				Vector student = new Vector();
				for(int i=1; i<=column; i++){
					student.add(rs.getString(i));
				}
				students.add(student);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}
	public Vector selectByName(String studentName) {
		Vector students = new Vector();
		String sql = "select s.studentId, s.studentName, b.branchName from branch as b, student as s "
				+ " where b.branchId = s.branchId and s.studentName like ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, "%" + studentName + "%");
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();
			while(rs.next()){
				Vector student = new Vector();
				for(int i=1; i<=column; i++){
					student.add(rs.getString(i));
				}
				students.add(student);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}
}
