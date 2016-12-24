package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
