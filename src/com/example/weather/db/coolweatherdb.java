package com.example.weather.db;

import java.util.ArrayList;
import java.util.List;

import com.example.weather.model.city;
import com.example.weather.model.county;
import com.example.weather.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class coolweatherdb {

	/**
	 * ���ݿ���
	 */
	public static final String DB_NAME = "cool_weather";

	/**
	 * ���ݿ�汾
	 */
	public static final int VERSION = 1;

	private static coolweatherdb coolWeatherDB;

	private SQLiteDatabase db;

	/**
	 * ���췽��˽�л�
	 */
	private coolweatherdb(Context context) {
		coolweatheropenhelper dbhelper = new coolweatheropenhelper(context,
				DB_NAME, null, VERSION);
		db = dbhelper.getWritableDatabase();
	}

	/**
	 * ��ȡCoolWeatherDB��ʵ��
	 */
	public synchronized static coolweatherdb getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new coolweatherdb(context);
		}
		return coolWeatherDB;
	}

	/**
	 * ��Provinceʵ���洢�����ݿ�
	 */
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}

	/**
	 * �����ݿ��ȡȫ�����е�ʡ����Ϣ
	 */
	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db
				.query("Province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor
						.getColumnIndex("province_code")));
				list.add(province);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * ��Cityʵ���洢�����ݿ�
	 */
	public void saveCity(city city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getcityName());
			values.put("city_code", city.getcityCode());
			values.put("province_id", city.getprovinceId());
			db.insert("City", null, values);
		}
	}

	/**
	 * �����ݿ��ȡĳʡ�����еĳ�����Ϣ
	 */
	public List<city> loadCities(int provinceId) {
		List<city> list = new ArrayList<city>();
		Cursor cursor = db.query("City", null, "province_id = ?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				city city = new city();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setcityName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				city.setcityCode(cursor.getString(cursor
						.getColumnIndex("city_code")));
				city.setprovinceId(provinceId);
				list.add(city);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * ��Countryʵ���洢�����ݿ�
	 */
	public void saveCounty(county county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getcountyName());
			values.put("county_code", county.getcountyCode());
			values.put("city_id", county.getcityId());
			db.insert("County", null, values);
		}
	}

	/**
	 * �����ݿ��ȡĳ���������е�����Ϣ
	 */
	public List<county> loadCounties(int cityId) {
		List<county> list = new ArrayList<county>();
		Cursor cursor = db.query("County", null, "city_id = ?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				county county = new county();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setcountyName(cursor.getString(cursor
						.getColumnIndex("county_name")));
				county.setcountyCode(cursor.getString(cursor
						.getColumnIndex("county_code")));
				county.setcityId(cityId);
				list.add(county);
			} while (cursor.moveToNext());
		}
		return list;
	}

}
