package pt.ipbeja.aula04;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AwfulListActivity extends AppCompatActivity {



    private LinearLayout list;
    private EditText itemCountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awful_list);

        list = findViewById(R.id.list);
        itemCountEditText = findViewById(R.id.item_count);

    }

    public void onFillListClicked(View view) {

        list.removeAllViews();

        long start = System.currentTimeMillis();

        int count = 0;
        if(!itemCountEditText.getText().toString().isEmpty()) {
            count = Integer.parseInt(itemCountEditText.getText().toString());
        }
        for (int i = 0; i < count; i++) {

            TextView tv = new TextView(this);
            tv.setTextSize(32);
            tv.setText("item " + i);
            list.addView(tv);

        }

        long lapsedTime = System.currentTimeMillis() - start;


        Toast.makeText(this, lapsedTime + "ms", Toast.LENGTH_LONG).show();


    }
}
