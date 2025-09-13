package com.vadinei.dm1.modulo4;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vadinei.dm1.modulo4.adapter.ContatoAdapter;
import com.vadinei.dm1.modulo4.dao.ContadoDAO;
import com.vadinei.dm1.modulo4.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadContatos();
    }

    private void loadContatos() {
        final ContadoDAO contadoDAO = new ContadoDAO(this);
        // final List<Contato> contatos = contadoDAO.listar();
        final List<Contato> contatos = fakeContatos();
        final ContatoAdapter contatoAdapter = new ContatoAdapter(contatos, this);
        final ListView lvContatos = findViewById(R.id.lvContatos);
        lvContatos.setAdapter(contatoAdapter);
    }

    private List<Contato> fakeContatos() {
        final Contato c1 = new Contato(1, "Contato 01");
        final Contato c2 = new Contato(1, "Contato 02");
        final Contato c3 = new Contato(1, "Contato 03");
        final List<Contato> contatos = new ArrayList<>();
        contatos.add(c1);
        contatos.add(c2);
        contatos.add(c3);
        return contatos;
    }
}