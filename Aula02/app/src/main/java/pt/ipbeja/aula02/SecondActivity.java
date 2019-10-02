package pt.ipbeja.aula02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static String COUNTER_KEY = "counter";
    public static String TEXT_KEY = "text";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView numberLabel = findViewById(R.id.number);
        TextView textLabel = findViewById(R.id.text_field);

        Intent intent = getIntent();
        int counter = intent.getIntExtra(COUNTER_KEY, 0);
        String text = intent.getStringExtra(TEXT_KEY);

        numberLabel.setText(counter + "");
        textLabel.setText(text);


    }
}
