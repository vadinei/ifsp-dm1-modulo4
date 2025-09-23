package com.vadinei.dm1.modulo4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vadinei.dm1.modulo4.dao.ContadoDAO;
import com.vadinei.dm1.modulo4.model.Contato;

public class ContatoEditActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contato_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final Button btInserirContatoEdit = findViewById(R.id.btInserirContatoEdit);
        final Button btVoltarContatoEdit = findViewById(R.id.btVoltarContatoEdit);
        btInserirContatoEdit.setOnClickListener(this);
        btVoltarContatoEdit.setOnClickListener(this);
        final EditText etNomeContatoEdit = findViewById(R.id.etNomeContatoEdit);
        etNomeContatoEdit.requestFocus();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btVoltarContatoEdit) {
            finish();
        } else if (view.getId() == R.id.btInserirContatoEdit) {
            final EditText etNomeContatoEdit = findViewById(R.id.etNomeContatoEdit);
            final String nomeContato = etNomeContatoEdit.getText().toString();
            if (!nomeContato.isEmpty()) {
                final ContadoDAO contadoDAO = new ContadoDAO(this);
                final Contato contato = new Contato(0, nomeContato);
                contadoDAO.inserir(contato);
                final Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(ContatoEditActivity.this, "Informe o nome do contato", Toast.LENGTH_LONG).show();
                etNomeContatoEdit.requestFocus();
            }
        }
    }
}