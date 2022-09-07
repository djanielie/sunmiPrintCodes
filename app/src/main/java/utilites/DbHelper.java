package utilites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "dbCodes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("create Table codeQr(codeToPrint varchar(10) primary key not null,numberPrinted integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
        Db.execSQL("Drop table if exists codeQr");
    }

    public Boolean insertCode(String codeInsert, int value) {
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CodeToPrint", codeInsert);
        contentValues.put("numberPrinted", value);
        long result = Db.insert("codeQr", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateCode(String codeInsert, int value) {
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CodeToPrint", codeInsert);
        contentValues.put("numberPrinted", value);
        Cursor cursor = Db.rawQuery("Select * from codeQR where codeToPrint=?", new String[]{codeInsert});
        if (cursor.getCount() > 0) {
            long result = Db.insert("codeQr", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletetCode(String codeInsert) {
        SQLiteDatabase Db = this.getWritableDatabase();
        Cursor cursor = Db.rawQuery("select * from codeQr where codeToPrint=?", new String[]{codeInsert});
        if (cursor.getCount() > 0) {
            long result = Db.delete("codeQr", "codeToPrint=?", new String[]{codeInsert});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletetnullsCode(String codeInsert) {
        SQLiteDatabase Db = this.getWritableDatabase();
        Cursor cursor = Db.rawQuery("select * from codeQr where numberPrinted=?", new String[]{"1"});
        Db.execSQL("delete from codeQr where codeToPrint=?");
        if (cursor.getCount() > 0) {
            System.out.println("the cursor is not empty");
            long result = Db.delete("codeQr", "numberPrinted=?", new String[]{"1"});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }

        else if(cursor.getCount()==0) {
            System.out.println("the cursor is empty");
            long result = Db.delete("codeQr", "codeToPrint=?", null);
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }
        return false;
    }

    public Cursor getData() {
        SQLiteDatabase Db = this.getWritableDatabase();
        Cursor cursor = Db.rawQuery("select * from codeQr", null);
        return cursor;
    }
}
