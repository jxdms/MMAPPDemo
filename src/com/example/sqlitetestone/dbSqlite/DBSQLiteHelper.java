package com.example.sqlitetestone.dbSqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSQLiteHelper extends SQLiteOpenHelper {
	//类的默认入口函数：构造函数
	
	//构造函数：四个参数
	public DBSQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	//构造函数：三个参数
	public DBSQLiteHelper(Context context,String name,int version){
		//CursorFactory设置为null,使用默认值
		super(context,name,null, version);
	}

	//实现方法:是一个回调方法
	//连接数据库的时候，如果数据库文件不存在
	//数据库第一次被创建时onCreate会被调用 
	//只调用一次
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//创建数据库的语句
		System.out.println("Creating Database......");
		//构造建表语句
		String CreateTableSql="create table if not EXISTS userlogin("
					+"_id integer primary key autoincrement,"
					+"username varchar,"
					+"password varchar,"
					+"loginNum interger,"
					+"loginDatetime text)";
		db.execSQL(CreateTableSql);

		//初始化参数 ContentValues
        ContentValues cv = new ContentValues();

        cv.put("username","uu");
        cv.put("password", "12345");
        //返回id long型  如果不成功返回-1
        //1-表名
        //2-空列的默认值
        //3-字段和值的key/value集合
        Long l = db.insert("userlogin", null, cv);
	}

	//升级数据库
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
