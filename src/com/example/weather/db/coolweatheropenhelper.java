package com.example.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class coolweatheropenhelper extends SQLiteOpenHelper {
	
	public coolweatheropenhelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 *  Province表建表语句
	 */
	public static final String CREATE_PROVINCE = "create table Province ("
				+ "id integer primary key autoincrement, " 
				+ "province_name text, "
				+ "province_code text)";
	/**
	 *  City表建表语句
	 */
	public static final String CREATE_CITY = "create table City ("
				+ "id integer primary key autoincrement, " 
				+ "city_name text, " 
				+ "city_code text, " 
				+ "province_id integer)";
	/**
	 *  County表建表语句
	 */
	public static final String CREATE_COUNTY = "create table County ("
				+ "id integer primary key autoincrement, " 
				+ "county_name text, " 
				+ "county_code text, " 
				+ "city_id integer)";
	private static final String context = null;
	private static final String name = null;
	private static final String factory = null;
	private static final String version = null;

	public void coolWeatherOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);  // 创建Province表
		db.execSQL(CREATE_CITY);  // 创建City表
		db.execSQL(CREATE_COUNTY);  // 创建Country表
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
