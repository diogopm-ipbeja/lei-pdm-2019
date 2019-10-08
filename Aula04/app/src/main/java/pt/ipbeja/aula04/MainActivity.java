package pt.ipbeja.aula04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAwfulListClicked(View view) {
        startActivity(new Intent(this, AwfulListActivity.class));
    }

    public void onContactListClicked(View view) {
        startActivity(new Intent(this, ContactListActivity.class));
    }
}
