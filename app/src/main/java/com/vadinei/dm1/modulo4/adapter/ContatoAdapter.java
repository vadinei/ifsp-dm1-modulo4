package com.vadinei.dm1.modulo4.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vadinei.dm1.modulo4.R;
import com.vadinei.dm1.modulo4.model.Contato;

import java.util.List;

public class ContatoAdapter extends BaseAdapter {
    private final List<Contato> contatos;
    private final Activity activity;

    public ContatoAdapter(List<Contato> contatos, Activity activity) {
        this.contatos = contatos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = activity.getLayoutInflater().inflate(R.layout.lista_contato, parent, false);
        final Contato contato = contatos.get(position);
        final TextView tvNomeConttoLista = view.findViewById(R.id.tvNomeContatoLista);
        tvNomeConttoLista.setText(contato.getNome());
        return view;
    }
}
