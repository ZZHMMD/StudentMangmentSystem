package com.zzh.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.zzh.utils.JdbcUtils;

public class AddStudent {
	
	public static void main(String args[]) throws SQLException{
		Connection conn = JdbcUtils.getConnection();
		
		String []  maj = {"�������","��ͨ����","��������","Ӣ��רҵ"};
		for(int i=0;i<100;i++){
			PreparedStatement pstmt = conn.prepareStatement("insert into student(id,name,sex,location,indate,major,classname) values(?,?,?,?,?,?,?)");
			if(i<10){
			pstmt.setString(1,"20152110"+i );
			}else{
				pstmt.setString(1,"2015211"+i);
			}
			
			pstmt.setString(2, "����"+i);
			pstmt.setString(3, "��");
			pstmt.setString(4, "�ϲ�"+i);
			pstmt.setDate(5, new Date(i));
			pstmt.setString(6, maj[(int) (Math.random()*4)]);
			pstmt.setString(7,(int)(Math.random()*11+1)+"��");
			
			pstmt.executeUpdate();
		}
	}

}
