package com.example.faixa_etaria;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBAdapter
{
    private static final String DATABASE_NAME = "contatos.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper DBHelper;
    private static SQLiteDatabase db = null;
    public static SQLiteDatabase getConexao(Context context)
    {
        DBHelper = new DatabaseHelper(context);
        db = DBHelper.getWritableDatabase();
        return db;
    }
    public static void close()
    {
        db = null;
        DBHelper.close();
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            criarTabela(db);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
        private void criarTabela(SQLiteDatabase db)
        {
            String criarTabelaContatos = "CREATE TABLE IF NOT EXISTS contatos( "
                    + "id_pessoa INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, "
                    + "faixa_Etaria TEXT NOT NULL);";
            db.execSQL(criarTabelaContatos);
        }
    }
}
