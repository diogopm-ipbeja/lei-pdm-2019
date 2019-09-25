package pt.ipbeja.aula02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView numberLabel = findViewById(R.id.number);
        TextView textLabel = findViewById(R.id.text_field);

        // TODO Recolher os valores enviados no Intent e colocá-los nas TextViews


    }
}
