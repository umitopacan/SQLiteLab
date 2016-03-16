package com.mis49m.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    //-- Database Version
    private static final int DATABASE_VERSION = 1;

    //-- Database Name
    private static final String DATABASE_NAME = "contactsDB";

    //-- Contacts table name
    private static final String TABLE_CONTACTS = "tblContacts";

    //-- Contacts Table Columns names
    private static final String COL_KEY_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_PHONE = "phoneNumber";

    /* Constructor */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* Create table on OnCreate()*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL Statement: CREATE TABLE tblContacts (id INTEGER PRIMARY KEY, name TEXT, phoneNumber TEXT))

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + COL_KEY_ID + " INTEGER PRIMARY KEY," + COL_NAME + " TEXT,"
                + COL_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //-- drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        //-- create tables again
        onCreate(db);
    }

    /* SQLiteDatabase.insert : Convenience method for inserting a row into the database. */
    public long addContact(Contact contact) {
        // SQL Statement: INSERT tblContacts (name,phoneNumber) VALUES ('a','1')
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to pass insert method
        ContentValues values = new ContentValues();
        values.put(COL_NAME, contact.getName());
        values.put(COL_PHONE, contact.getPhoneNumber());

        //-- insert new contact row
        long result = db.insert(
                TABLE_CONTACTS,
                null,
                values);

        //-- close database connection
        db.close();

        return result;
    }



}
