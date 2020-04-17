package com.qa.main;
import java.sql.SQLException;

public class Runner {
	
	public static void main(String[] args)throws SQLException {
		
		Ims me = new Ims();
		
		System.out.println(me.tester());
	}

}
