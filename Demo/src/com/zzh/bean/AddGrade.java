package com.zzh.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zzh.utils.JdbcUtils;

public class AddGrade {
	public static void main(String args[]) throws SQLException{
		Connection conn = JdbcUtils.getConnection();
		for(int i=0;i<50;i++){
			PreparedStatement pstmt = conn.prepareStatement("insert into grade(id,studyyear,studyseason,math,english,javatest,studentid) values(?,?,?,?,?,?,?)");
			if(i<10){
				pstmt.setString(7,"20152110"+i );
				//pstmt.setString(1, "20150"+i);
				}else{
					pstmt.setString(7,"2015211"+i);
					//pstmt.setString(1, "2015"+i);
				}
			//pstmt.setString(1, "2015"+i+50);
			int n = 50+i;
			pstmt.setString(1, "2015"+n);
			pstmt.setDate(2, new Date(i));
			pstmt.setString(3, "об");
			pstmt.setDouble(4, Math.random()*100);
			pstmt.setDouble(5, Math.random()*100);
			pstmt.setDouble(6, Math.random()*100);
			
			pstmt.executeUpdate();
		}
	}
}
