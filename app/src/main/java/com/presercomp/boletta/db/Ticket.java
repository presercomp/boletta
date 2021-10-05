package com.presercomp.boletta.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import androidx.annotation.Nullable;

public class Ticket  extends DBHelper{
    private Context context;
    private SQLiteDatabase db;
    private String date;
    private long folio;
    private long id_user;
    private long amount;
    private int active;

    public Ticket(@Nullable Context c){
        super(c);
        this.context = context;
    }

    /**
     * Prepara los datos para su operacion
     * @param date Fecha de la operación
     * @param id_user Identificador del usuario
     * @param amount Monto de la boleta
     */
    public void add(String date, int id_user, int amount){
        this.date = date;
        this.id_user = id_user;
        this.amount = amount;
    }

    /**
     * Crea un nuevo usuario a partir de los datos añadidos
     * @return id del nuevo usuario
     */
    public long create(){
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", this.date);
        values.put("id_user", this.id_user);
        values.put("amount", this.amount);
        try{
            this.folio = db.insert(super.TABLE_TICKETS, null, values);
            return this.folio;
        } catch (SQLiteException ex){
            System.out.println(ex.getMessage());
            return 0;
        }

    }

    public long edit(long folio){
        this.folio = folio;
        ContentValues values = new ContentValues();
        values.put("date", this.date);
        values.put("id_user", this.id_user);
        values.put("amount", this.amount);
        return db.update(super.TABLE_TICKETS, values, "folio = ?", new String[]{String.valueOf(this.folio)});
    }

}
