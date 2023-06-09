package com.example.th2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "data.db";
    private static final int DB_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE cat (id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql.append(" diaChi TEXT, dichVu TEXT, dienTich DOUBLE, gia DOUBLE, soNguoi INTEGER)");
        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Cat> getAll () {
        List<Cat> results = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor rs = database.query("cat", null, null, null, null, null, null);
        while (rs != null && rs.moveToNext()) {
            results.add(new Cat(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5)));
        }
        return results;
    }

    public List<Cat> search (String key) {
        List<Cat> results = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor rs = database.rawQuery("SELECT * FROM cat WHERE diaChi LIKE ?", new String[]{String.valueOf("%"+key+"%")});
        while (rs != null && rs.moveToNext()) {
            results.add(new Cat(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5)));
        }
        return results;
    }

    public Cat getOne (int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor rs = database.rawQuery("SELECT * FROM cat WHERE id = ?", new String[]{String.valueOf(id)});
        return new Cat(rs.getString(0), rs.getString(1), rs.getDouble(2), rs.getDouble(3), rs.getInt(4));
    }

    public Long add (Cat cat) {
        ContentValues values = new ContentValues();
        values.put("diaChi", cat.getDiaChi());
        values.put("dichVu", cat.getDichVu());
        values.put("dienTich", cat.getDienTich());
        values.put("gia", cat.getGia());
        values.put("soNguoi", cat.getSoNguoi());
        SQLiteDatabase database = getWritableDatabase();
        return database.insert("cat", null, values);
    }

    public int update (Cat cat) {
        ContentValues values = new ContentValues();
        values.put("diaChi", cat.getDiaChi());
        values.put("dichVu", cat.getDichVu());
        values.put("dienTich", cat.getDienTich());
        values.put("gia", cat.getGia());
        values.put("soNguoi", cat.getSoNguoi());
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(cat.getId())};
        return database.update("cat", values, whereClause, whereArgs);
    }

    public int delete (int id) {
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        return database.delete("cat", whereClause, whereArgs);
    }
}
