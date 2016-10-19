package com.example.sqlitetestone.dbSqlite;

import java.util.ArrayList;
import java.util.List;

import com.example.sqlitetestone.entity.userlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

	private SQLiteDatabase db;
	private DBSQLiteHelper dbHelper;
	
	public DBManager(Context context) {
		dbHelper = new DBSQLiteHelper(context,"sqlTest.db",1);  
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);  
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里  
        db = dbHelper.getWritableDatabase();  
	}
	
	
	/**
	 * 数据库操作的添加数据方法
	 * @param userloginList
	 * @return boolean
	 */
	public boolean add(List<userlogin> userloginList){
		db.beginTransaction();  //开始事务
		try {
			for(userlogin ul:userloginList){
				//db.execSQL("insert into userlogin(username,password,loginNum,loginDatetime) values(?,?,?,?)",new Object[]{ul.getUsername(),ul.getPassword(),ul.getLoginNUm(),ul.getLoginDatetime()});
				if(queryISHaveUsername(ul)){
					
				}else{
					db.execSQL("insert into userlogin values(?,?,?,?,?)",new Object[]{null,ul.getUsername(),ul.getPassword(),ul.getLoginNUm(),ul.getLoginDatetime()});
				}
			}
			db.setTransactionSuccessful();  //设置事务成功完成
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			
			return false;
		} finally{
			db.endTransaction();    //结束事务
			System.out.println("add method...");
		}
	}
	
	/**
	 * 用户登录验证
	 * @param uLogin
	 * @return boolean
	 */
	public boolean checkLogin(userlogin uLogin){
		String sql="select * from userlogin where username=? and password=?";
		Cursor c=db.rawQuery(sql, new String[]{uLogin.getUsername(),uLogin.getPassword()});
		if(c.moveToNext()){
			System.out.println("登录验证成功...");
			c.close();
			return true;
		}else{
			System.out.println("登录验证失败...");
			c.close();
			return false;
		}
	}
	
	/**
	 * 查询是否已存在某个用户数据（用户名）
	 * @param udata
	 * @return 有数据返回真；否则假
	 */
	public boolean queryISHaveUsername(userlogin udata){
		String sql="select * from userlogin where username=?";
		Cursor c=db.rawQuery(sql, new String[]{udata.getUsername()});
		//c.getCount()
		if(c.moveToNext()){
			c.close();
			return true;
		}else{
			c.close();
			return false;
		}
	}
	
	/**
	 * 记录登录成功的状态
	 * 用户登录时刷新登录次数和登录时间
	 * @param ulog
	 * @return
	 */
	public userlogin updateUserloginLog(userlogin ulog){
		int inum=0;
		Cursor c=db.rawQuery("select * from userlogin where username=?", new String[]{ulog.getUsername()});
		if(c.getCount()==1){
			c.moveToNext();
			inum=c.getInt(c.getColumnIndex("loginNum"));
		}
		ContentValues cv = new ContentValues();
		
		cv.put("loginNum", inum+1);
		cv.put("loginDatetime", ulog.getLoginDatetime());
		db.update("userlogin", cv, "username = ?", new String[]{ulog.getUsername()});
	
		userlogin returnLog=new userlogin();
		returnLog.setUsername(ulog.getUsername());
		returnLog.setLoginNUm(inum+1);
		returnLog.setLoginDatetime(ulog.getLoginDatetime());
		
		return returnLog;
	}
	
	/**
	 * @return 记录集：用户登录表所有数据
	 */
	public List<userlogin> queryAllForUserlogin(){
		ArrayList<userlogin> ul = new ArrayList<userlogin>();
		Cursor c=db.rawQuery("SELECT * FROM userlogin", null);
		while(c.moveToNext()){
			userlogin udata=new userlogin();
			udata.set_id(c.getInt(c.getColumnIndex("_id")));
			udata.setUsername(c.getString(c.getColumnIndex("username")));
			udata.setPassword(c.getString(c.getColumnIndex("password")));
			udata.setLoginNUm(c.getInt(c.getColumnIndex("loginNum")));
			udata.setLoginDatetime(c.getString(c.getColumnIndex("loginDatetime")));
			ul.add(udata);
		}
		c.close();
		return ul;
	}
	
	/** 
     * close database 
     */  
    public void closeDB() {  
        db.close();
        System.out.println("关闭当前数据库（操作）...");
    }  
	
}
