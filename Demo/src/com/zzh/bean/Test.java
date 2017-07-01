package com.zzh.bean;

import java.util.Iterator;
import java.util.List;

import com.zzh.dao.DemoDao;
import com.zzh.dao.impl.DemoDaoImpl;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DemoDao dao = new DemoDaoImpl();
		
		//dao.insertGraduateDate("201521108");
		
		/*List<Xueji> list = dao.insertXuejiDate();
		
		Iterator it = list.iterator();
		
		while(it.hasNext()){
			Xueji xueji = (Xueji)it.next();
			
			System.out.println(xueji);
		}*/

	}

}
