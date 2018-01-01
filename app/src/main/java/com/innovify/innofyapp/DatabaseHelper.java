package com.innovify.innofyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "innovify.sqlite";

	// Planned Route table name
	private static final String TABLE_USERLOCATION = "Tbl_UserLocation";

	// Planned Route Table Columns names
	private static final String KEY_ID = "unique_id";
	private static final String KEY_LAT = "lat";
	private static final String KEY_LON = "lon";
	private static final String TIME_STAMP = "arrival_time";


	Context context;
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PLANNED_ROUTE_TABLE = "CREATE TABLE " + TABLE_USERLOCATION + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
				+ KEY_LAT + " TEXT,"
				+ KEY_LON + " TEXT,"
				+ TIME_STAMP + " TEXT )";
		db.execSQL(CREATE_PLANNED_ROUTE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERLOCATION);
		// Create tables again
		onCreate(db);
	}

	/**
	 * This method will add new location to local database
	 * @param location to be added
	 * @return counts of number of row affected
	 */
	public int addUserLocation(LocationBean location) {
		SQLiteDatabase db = this.getWritableDatabase();
		int val = 0;
		ContentValues values = new ContentValues();
		values.put(KEY_ID, location.getId());
		values.put(KEY_LAT, location.getLat());
		values.put(KEY_LON, location.getLon());
		values.put(TIME_STAMP, location.getTimeStamp());

		val = (int) db.insert(TABLE_USERLOCATION, null, values);
		db.close(); // Closing database connection
		return val;
	}

	/**
	 * This method will be used to get location stored in the database
	 * @return
	 */
	public ArrayList<LocationBean> getAllPlannedRoute() {
		ArrayList<LocationBean> locationList = new ArrayList<LocationBean>();
		// Select All Query

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_USERLOCATION, new String[] {KEY_ID, KEY_LAT
				, KEY_LON, TIME_STAMP}, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				LocationBean locationBean = new LocationBean();
				locationBean.setId(String.valueOf(cursor.getInt(0)));
				locationBean.setLat(cursor.getDouble(1));
				locationBean.setLon(cursor.getDouble(2));
				locationBean.setTimeStamp(cursor.getLong(3));
				locationList.add(locationBean);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return locationList;
	}
}