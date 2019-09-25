package pt.ipbeja.aula02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Não podemos fazer os findViewById logo aqui! As Views ainda não existem, só depois de #setContentView
    private EditText textField;
    private TextView counterLabel;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) Encontrar as Views definidas no layout (só podemos fazer isto depois de #setContentView)

        this.textField = findViewById(R.id.text_field);
        this.counterLabel = findViewById(R.id.counter);

        Button incBtn = findViewById(R.id.increment_btn);
        Button decBtn = findViewById(R.id.decrement_btn);
        Button launchBtn = findViewById(R.id.launch);

        // 2) Atribuir OnClickListeners aos Buttons
        incBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quando o utilizador carrega no botão, incrementamos o contador
                counter++;
                // E actualizamos a TextView
                updateLabel();
            }
        });

        decBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                updateLabel();
            }
        });


        launchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quando o utilizador carrega no botão, vamos lançar a SecondActivity

                // Criamos um Intent (uma mensagem ao sistema operativo) a dizer esta Activity quer lançar a SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // TODO Sugestão: Enviar o texto da EditText e o valor do counter para a SecondActivity
                String text = textField.getText().toString();

                // Quando o Intent estiver configurado, podemos lançar a SecondActivity com o intent
                startActivity(intent);
            }
        });
    }

    private void updateLabel() {
        counterLabel.setText(counter + ""); // Não passar int a TextView#setText! Reservado a identificadores de recursos
    }
}
