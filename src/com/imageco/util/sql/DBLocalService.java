package com.imageco.util.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class DBLocalService ...
 *
 * @author Administrator
 *         Created on 11-10-22
 */
public class DBLocalService extends SQLiteOpenHelper {

    /**
     * Field DB_VERSION
     */
    private static final int DB_VERSION = 1;//版本
    /**
     * Field DB_NAME
     */
    private static final String DB_NAME = "storage_sqlite.db";//数据库名称
    /**
     * Field TABLE_CONTACTS
     */
    public static final String TABLE_CONTACTS = "myDemo_contacts";

    /**
     * Constructor DBLocalService creates a new DBLocalService instance.
     *
     * @param context of type Context
     */
    public DBLocalService(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 当数据库被创建时执行
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        String sql = "create table[" + TABLE_CONTACTS + "](" +
                "[_id] integer autoinc primary key," +
                "[name] varchar(20)," +
                "[tel] varchar(50)" +
                ")";
        db.execSQL(sql);
        System.out.println("Create table contacts success");
//		//插入一条初始化数据
//		ContentValues values=new ContentValues();
//		values.put("name","美赞成");
//		values.put("tel","13511111111");
//		db.insert(TABLE_CONTACTS,null, values);
    }

    /**
     * 更新数据库版本的函数
     *
     * @param db         数据库操作对象
     * @param oldVersion 数据库旧版本号
     * @param newVersion 数据库新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新数据库的操作代码
    }

    /**
     * 向数据库插入表数据
     *
     * @param tableName  表名
     * @param columnHack 列名
     * @param values     列记录集
     */
    public void insert(String tableName, String columnHack, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();//数据库可写
        db.insert(tableName, columnHack, values);
    }

    /**
     * 执行更新SQL语句
     *
     * @param sql
     */
    public void exeSQL(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();//数据库可写
        db.execSQL(sql);
    }

    /**
     * 执行更新SQL语句
     *
     * @param sql  update Table set column=? where column=?
     * @param args Object[] args=new Object[]{"text",int,float}
     */
    public void exeSQL(String sql, Object[] args) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql, args);
    }

    /**
     * 查询语句
     *
     * @param sql  select * from Table where column=? and column=? ...
     * @param args 列条件的值String []{"value1","value2",...}
     * @return
     */
    public Cursor querySQL(String sql, String[] args) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, args);
        return cursor;
    }

    /**
     * Method deleteItem ...
     *
     * @param tableName of type String
     * @param name      of type String
     */
    /*
    * 删除一个条数据
    */
    public void deleteItem(String tableName, String name) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(tableName, "name =" + "\"" + name + "\"", null);
        } catch (SQLException e) {

        }
    }

}
