package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Branch;
import model.Faculty;
import util.MySQLHelper;

public class BranchDAO {
	public String getBranchId(){
		String branchId = "";
		String sql = "select branchId from branch order by branchId desc limit 1";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String tmp = rs.getString(1);
				tmp = tmp.substring(1);
				int num = Integer.parseInt(tmp);
				num++;
				
				if(num<10){
					branchId = "B000" + num;
				}else if(num<100){
					branchId = "B00" + num;
				}else if(num<1000){
					branchId = "B0" + num;
				}else {
					branchId = "B" + num;
				}
			}else{
				branchId = "B0001";
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branchId;
	}
	
	public boolean insert(Branch branch){
		boolean result = false;
		String sql = "insert into branch(branchId, branchName, facultyId) values(?, ?, ?)";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(3, branch.getFacultyId());
			ps.setString(2, branch.getBranchName());
			ps.setString(1, branch.getBranchId());
			
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
		Vector branches = new Vector();
		String sql = "select b.branchId, b.branchName, f.facultyName from branch as b, faculty as f "
				+ " where b.facultyId = f.facultyId";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();
			while(rs.next()){
				Vector branch = new Vector();
				for(int i=1; i<=column; i++){
					branch.add(rs.getString(i));
				}
				branches.add(branch);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branches;
	}

	public boolean update(Branch branch) {
		boolean result = false;
		String sql = "update branch set branchName = ? where branchId = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);			
			ps.setString(1, branch.getBranchName());
			ps.setString(2, branch.getBranchId());
			
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

	public Vector selectByName(String branchName) {
		Vector branches = new Vector();
		String sql = "select b.branchId, b.branchName, f.facultyName from branch as b, faculty as f "
				+ " where b.branchName like ? and b.facultyId = f.facultyId";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, "%" + branchName + "%");
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();
			while(rs.next()){
				Vector branch = new Vector();
				for(int i=1; i<=column; i++){
					branch.add(rs.getString(i));
				}
				branches.add(branch);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branches;
	}
	public List<Branch> select(String facultyId){
		List<Branch> branches = new ArrayList<Branch>();
		String sql = "select branchId, branchName from branch where facultyId = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, facultyId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Branch branch = new Branch();
				branch.setBranchId(rs.getString(1));
				branch.setBranchName(rs.getString(2));
				
				branches.add(branch);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branches;
	}
}
