package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
