package es.ucm.fdi.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATA_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Rutinas.db";
    public static final String TABLE_RUTINAS = "t_rutinas";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE , null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_RUTINAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ejercicio TEXT NOT NULL," +
                "repeticiones TEXT NOT NULL," +
                "peso TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
