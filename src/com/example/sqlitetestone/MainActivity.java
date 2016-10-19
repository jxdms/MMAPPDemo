package com.example.sqlitetestone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.sqlitetestone.dbSqlite.DBManager;
import com.example.sqlitetestone.dbSqlite.DBSQLiteHelper;
import com.example.sqlitetestone.entity.userlogin;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private DBManager dbm;
	
	private SQLiteDatabase db;
	private DBSQLiteHelper dbHelper;
	
	private Button btnLogin,btnReset;
	private EditText etUsername,etPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//����DBManager�ࣺ���ݿ����ĳ��÷���
		dbm=new DBManager(this);
		
		//SQLite���ݿ�Ĵ������ʼ��
		//dbHelper=new DBSQLiteHelper(this,"sqlTest.db",1);
		//db=dbHelper.getReadableDatabase();
		
		initDbData();
		
		//�ؼ���������ע��
		initWidget();
		//�����¼�������
		addClickListener();
		
	}
	
	//���ݿ⣺��ʼ������
	private void initDbData(){
		//db=dbHelper.getReadableDatabase();
		/*String strName="u1";
		String strPwd="123456";
		String strNum="0";
		String strDatetime="";
		String sql="insert into userlogin(username,password,loginNum,loginDatetime) values('"+strName+"','"+strPwd+"','"+strNum+"','"+strDatetime+"')";
		db.execSQL(sql);*/
		
		ArrayList<userlogin> uloginArr=new ArrayList<userlogin>();
		userlogin u1=new userlogin("u1","123456",0,"");
		uloginArr.add(u1);
		uloginArr.add(new userlogin("u2","123456",0,""));
		
		dbm.add(uloginArr);
	}
	
	//�ؼ���ע��
	private void initWidget(){
		btnLogin=(Button) this.findViewById(R.id.btnLogin);
		btnReset=(Button) this.findViewById(R.id.btnReset);
		
		etUsername=(EditText) this.findViewById(R.id.etUsername);
		etPassword=(EditText) this.findViewById(R.id.etPassword);
		
	}
	
	// �����������������¼�����ز���
	private void addClickListener(){
		// ���ڲ����������ʵ����
		btnOnClickListener btncol=new btnOnClickListener();
		
		//����ť�ؼ����¼�
		btnLogin.setOnClickListener(btncol);
		btnReset.setOnClickListener(btncol);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (db != null && db.isOpen())
		{
			db.close();
		}
	}

	// �ڲ���
	private class btnOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.btnLogin:
				
				String strUname=etUsername.getText().toString().trim();
				String strPwd=etPassword.getText().toString().trim();
				
				userlogin inputUser=new userlogin();
				inputUser.setUsername(strUname);
				inputUser.setPassword(strPwd);
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");       
				Date curDate = new Date(System.currentTimeMillis());//��ȡ��ǰʱ��       
				String str = formatter.format(curDate);
				
				if(dbm.checkLogin(inputUser)){
					inputUser.setLoginDatetime(str);
					dbm.updateUserloginLog(inputUser);
				}
				
				   
				
				//String sql="select * from userlogin where username='"+strUname+"' and password='"+strPwd+"'";
				/*String sql="select * from userlogin where username=? and password=?";
				Cursor c=db.rawQuery(sql, new String[]{strUname,strPwd});
				if(c.moveToNext()){
					System.out.println("��¼�ɹ�������");
				}else{
					System.out.println("��¼ʧ�ܡ�����");
				}
				c.close();*/
				
				//db=dbHelper.getReadableDatabase();
				/*
				String sql="select * from userlogin where username=?";
				Cursor c=db.rawQuery(sql, new String[]{strUname});
				while(c.moveToNext()){
					String pwd= c.getString(c.getColumnIndex("password"));
					if(pwd.equals(strPwd)){
						System.out.println("��¼�ɹ�������");
						break;
					}else{
						System.out.println(pwd+"==="+strPwd);
					}
				}
				*/
				
				
				break;
			case R.id.btnReset:
				//���ݳ�ʼ����������������
				etUsername.setText("");
				etPassword.setText("");
				break;
			default:
				break;
			}
			
		}		
		
	}
}
