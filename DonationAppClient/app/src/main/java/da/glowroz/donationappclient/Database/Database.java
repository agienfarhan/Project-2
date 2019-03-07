package da.glowroz.donationappclient.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import da.glowroz.donationappclient.Model.Transaksi;

/**
 * Created by GlowRoz on 31/12/2017.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME="sqlitetransaksi";
    private static final int DB_VER=1;

    public Database(Context context) {
        super(context, DB_NAME,null, DB_VER);
    }

    public List<Transaksi> getDonasi()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Nama_Donasi","Id_Donasi","Nominal_Donasi"};
        String sqlTable="TransaksiDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Transaksi> result = new ArrayList<>();
        if(c.moveToFirst())
        {
            do{
                result.add(new Transaksi(c.getString(c.getColumnIndex("Id_Donasi")),
                        c.getString(c.getColumnIndex("Nama_Donasi")),
                        c.getString(c.getColumnIndex("Nominal_Donasi"))
                ));
            }while(c.moveToNext());
        }
        return result;

    }

    public void addToDonasi(Transaksi transaksi)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO TransaksiDetail(Id_Donasi,Nama_Donasi,Nominal_Donasi) VALUES ('%s','%s','%s');",
                transaksi.getId_Donasi(),
                transaksi.getNama_Donasi(),
                transaksi.getNominal_Donasi()
                );
        db.execSQL(query);
    }

    public void cleanDonasi()
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM TransaksiDetail");
        db.execSQL(query);
    }



}
