package com.vadinei.dm1.modulo4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vadinei.dm1.modulo4.adapter.ContatoAdapter;
import com.vadinei.dm1.modulo4.dao.ContadoDAO;
import com.vadinei.dm1.modulo4.model.Contato;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityResultLauncher<Intent> launcher;

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

        final Button btInserirContatoMain = findViewById(R.id.btInserirContatoMain);
        btInserirContatoMain.setOnClickListener(this);
        final Button btLimparContatoMain = findViewById(R.id.btLimparContatoMain);
        btLimparContatoMain.setOnClickListener(this);

        final ContadoDAO contadoDAO = new ContadoDAO(this);
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        listarContatos(contadoDAO);
                    }
                }
        );
        listarContatos(contadoDAO);
    }

    @Override
    public void onClick(View view) {
        final ContadoDAO contadoDAO = new ContadoDAO(this);
        if (view.getId() == R.id.btLimparContatoMain) {
            limparContatos(contadoDAO);
        } else if (view.getId() == R.id.btInserirContatoMain) {
            final Intent telaContatoEdit = new Intent(MainActivity.this, ContatoEditActivity.class);
            launcher.launch(telaContatoEdit);
        }
    }

    private void limparContatos(final ContadoDAO contadoDAO) {
        contadoDAO.limpar();
        listarContatos(contadoDAO);
        Toast.makeText(MainActivity.this, "Todos os contatos foram removidos com sucesso!", Toast.LENGTH_LONG).show();
    }

    public void listarContatos(final ContadoDAO contadoDAO) {
        final List<Contato> contatos = contadoDAO.listar();
        final ContatoAdapter contatoAdapter = new ContatoAdapter(contatos, this);
        final ListView lvContatos = findViewById(R.id.lvContatos);
        lvContatos.setAdapter(contatoAdapter);
    }
}