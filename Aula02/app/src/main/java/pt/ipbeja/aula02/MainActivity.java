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
        this.textField = findViewById(R.id.text_field); // Utilizamos o id definido no layout (R.id.XPTO)
        this.counterLabel = findViewById(R.id.counter);

        Button incrementBtn = findViewById(R.id.increment_btn);
        Button decrementBtn = findViewById(R.id.decrement_btn);
        Button launchNextActivityBtn = findViewById(R.id.next_btn);

        // 2) Atribuir OnClickListeners aos Buttons
        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quando o utilizador carrega no botão, incrementamos o contador
                counter++;
                // E actualizamos a TextView
                updateLabel();
            }
        });

        decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                updateLabel();
            }
        });


        launchNextActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quando o utilizador carrega no botão, vamos lançar a SecondActivity

                // Criamos um Intent (uma mensagem) a dizer que esta Activity quer lançar a SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // TODO Sugestão: Ex 1.1) Tente enviar o texto da EditText e o valor do counter para a SecondActivity (ver parte 2 em SecondActivity) - Ver https://developer.android.com/training/basics/firstapp/starting-activity
                String text = textField.getText().toString();

                // Quando o Intent estiver configurado, podemos lançar a SecondActivity com o intent
                // O Android tratará de instanciar e exibir a SecondActivity
                startActivity(intent);
            }
        });
    }

    private void updateLabel() {
        counterLabel.setText(counter + ""); // Não passar int a TextView#setText! Reservado a identificadores de recursos
    }
}
