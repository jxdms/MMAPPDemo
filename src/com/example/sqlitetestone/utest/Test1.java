package com.example.sqlitetestone.utest;

import java.util.ArrayList;

import org.junit.Test;

import com.example.sqlitetestone.dbSqlite.DBManager;
import com.example.sqlitetestone.entity.userlogin;

import android.test.AndroidTestCase;

public class Test1 extends AndroidTestCase {
	@Test
	public void test() {
		fail("Not yet implemented");
		
	}
	
	public void checkSQLiteAdd(){
		DBManager dbm=new DBManager(getContext());
		
		ArrayList<userlogin> uloginArr=new ArrayList<userlogin>();
		userlogin u1=new userlogin("u1","123456",0,"");
		uloginArr.add(u1);
		uloginArr.add(new userlogin("u2","123456",0,""));
		
		System.out.println("==="+dbm.add(uloginArr));
		
	}
	
}
