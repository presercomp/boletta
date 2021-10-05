package com.presercomp.boletta.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import androidx.annotation.Nullable;

public class User extends DBHelper {
    private Context context;
    private SQLiteDatabase db;
    private String name;
    private String mail;
    private String pass;
    private long id_user;

    public User(@Nullable Context c){
        super(c);
        this.context = context;
    }

    /**
     * Prepara los datos para su operacion
     * @param nombre Nombre del usuario
     * @param clave Clave del usuario
     * @param correo Correo del usuario
     */
    public void add(String nombre, String clave, String correo){
        this.name = nombre;
        this.mail = correo;
        this.pass = clave;
    }

    /**
     * Crea un nuevo usuario a partir de los datos añadidos
     * @return id del nuevo usuario
     */
    public long create(){
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", this.name);
        values.put("pass", this.pass);
        values.put("mail", this.mail);
        try{
            this.id_user = db.insert(super.TABLE_USERS, null, values);
            return this.id_user;
        } catch (SQLiteException ex){
            System.out.println(ex.getMessage());
            return 0;
        }

    }


    public boolean login(String mail, String pass){
        this.db = this.getReadableDatabase();
        String query = "SELECT count(*) FROM "+super.TABLE_USERS;
        query += " WHERE mail = '"+mail+"' AND pass = '"+pass+"';";
        try{
            Cursor cursor = this.db.rawQuery(query, null);
            System.out.println(cursor.getColumnCount());
            System.out.println(cursor.getCount());
            if(cursor.getColumnCount() == 0){
                return false;
            } else {
                cursor.moveToFirst();
                int count = cursor.getInt(0);
                cursor.close();
                return count > 0;
            }
        }catch(SQLiteException ex){
            return false;
        }

    }
    /**
     * Edita un usuario a partir de los datos añadidos
     * @return
     */
    public long edit(){
        ContentValues values = new ContentValues();
        values.put("name", this.name);
        values.put("pass", this.pass);
        values.put("mail", this.mail);
        return db.update(super.TABLE_USERS, values, "id_user = ?", new String[]{String.valueOf(this.id_user)});
    }

    /*
    public void getAll(){
        Cursor cursor = db.rawQuery("SELECT id_user, mail, pass, name FROM "+super.TABLE_USERS, null);
        while(cursor.moveToNext()) {
            long _id_user = cursor.getLong(0);
            String _mail = cursor.getString(1);
            String _pass = cursor.getString(2);
            String _name = cursor.getString(3);
            User u_result = new User(this.context);
            u_result.add(_name, _pass, _mail);
            this.users[this.users.length] = u_result;
        }
        cursor.close();
    }
    */
}
