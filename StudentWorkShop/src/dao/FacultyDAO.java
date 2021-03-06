package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Faculty;
import util.MySQLHelper;

public class FacultyDAO {
	public String getFacultyId(){
		String facultyId = "";
		String sql = "select facultyId from faculty order by facultyId desc limit 1";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String tmp = rs.getString(1);
				tmp = tmp.substring(1);
				int num = Integer.parseInt(tmp);
				num++;
				
				if(num<10){
					facultyId = "F000" + num;
				}else if(num<100){
					facultyId = "F00" + num;
				}else if(num<1000){
					facultyId = "F0" + num;
				}else {
					facultyId = "F" + num;
				}
			}else{
				facultyId = "F0001";
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return facultyId;
	}
	public boolean insert(Faculty faculty){
		boolean result = false;
		String sql = "insert into faculty(facultyId, facultyName) values(?, ?)";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, faculty.getFacultyId());
			ps.setString(2, faculty.getFacultyName());
			
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
	public boolean update(Faculty faculty){
		boolean result = false;
		String sql = "update faculty set facultyName = ? where facultyId = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(2, faculty.getFacultyId());
			ps.setString(1, faculty.getFacultyName());
			
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
		Vector faculties = new Vector();
		String sql = "select * from faculty";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData  rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();
			while(rs.next()){
				Vector faculty = new Vector();
				for(int i=1; i<=column; i++){
					faculty.add(rs.getString(i));
				}
				faculties.add(faculty);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return faculties;
	}
	
	public List<Faculty> select(){
		List<Faculty> faculties = new ArrayList<Faculty>();
		String sql = "select * from faculty";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Faculty faculty = new Faculty();
				faculty.setFacultyId(rs.getString(1));
				faculty.setFacultyName(rs.getString(2));
				
				faculties.add(faculty);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return faculties;
	}
	
	public Vector selectByName(String facultyName){
		Vector faculties = new Vector();
		String sql = "select * from faculty where facultyName like ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, "%" + facultyName + "%");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData  rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();
			while(rs.next()){
				Vector faculty = new Vector();
				for(int i=1; i<=column; i++){
					faculty.add(rs.getString(i));
				}
				faculties.add(faculty);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return faculties;
	}
	public String getFacultyName(String branchName){
		String facultyName = "";
		String sql = "select f.facultyName from faculty as f, branch as b "
				+ "where b.branchName = ? "
				+ "and b.facultyId = f.facultyId";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, branchName);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				facultyName = rs.getString(1);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return facultyName;
	}
}







