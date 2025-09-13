package com.vadinei.dm1.modulo4.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vadinei.dm1.modulo4.model.Contato;
import com.vadinei.dm1.modulo4.sqlite.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ContadoDAO {
    private final SQLiteDatabase db;

    public ContadoDAO(Context context) {
        final DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void inserir(Contato contato) {
        final ContentValues values = new ContentValues();
        values.put(DbHelper.TABLE_CONTATOS_COLUMN_NOME, contato.getNome());
        db.insert(DbHelper.TABLE_CONTATOS_NAME, null, values);
    }

    public List<Contato> listar() {
        final List<Contato> contatos = new ArrayList<>();
        final Cursor cursor = db.query(DbHelper.TABLE_CONTATOS_NAME,
                new String[]{DbHelper.TABLE_CONTATOS_COLUMN_ID, DbHelper.TABLE_CONTATOS_COLUMN_NOME},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final int id = cursor.getInt(0);
                final String nome = cursor.getString(1);
                final Contato contato = new Contato(id, nome);
                contatos.add(contato);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contatos;
    }
}
