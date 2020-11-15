package com.example.faixa_etaria;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spnFaixaEtaria;
    Button cadastrar, limpar;
    EditText etNome;
    TextView tvMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnFaixaEtaria = (Spinner) findViewById(R.id.spnFaixaEtaria);
        cadastrar = (Button)findViewById(R.id.btnCadastrar);
        limpar = (Button)findViewById(R.id.btnLimpar);

        final Pessoa pessoa = new Pessoa();

        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spnLista, android.R.layout.simple_spinner_item);
        spnFaixaEtaria.setAdapter(adapter);

        cadastrar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                pessoa.setNome(etNome.getText().toString());
                pessoa.setFaixaEtaria(spnFaixaEtaria.getSelectedItem().toString());
                if (etNome.length()==0)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Informe seu nome completo!",Toast.LENGTH_LONG);
                    toast.show();
                }
                {try {
                    ContentValues dados = new ContentValues();
                    dados.put("nome", pessoa.getNome());
                    dados.put("faixa_etaria", pessoa.getFaixaEtaria());
                    DBAdapter.getConexao(getApplicationContext()).insert("pessoas", null, dados);
                    DBAdapter.close();
                    Toast.makeText(getApplicationContext(), "Pessoa cadastradada: " + pessoa.getNome() + " | " + pessoa.getFaixaEtaria(), Toast.LENGTH_SHORT).show();
                    etNome.requestFocus();
                } catch (Exception exception) {
                    Toast.makeText(getApplicationContext(), "Algo deu errado! :(", Toast.LENGTH_SHORT).show();
                    exception.printStackTrace();
                    }
                }
            }
        });

        limpar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                etNome.setText("");
                spnFaixaEtaria.setSelection(0);
            }
        });
    }
}