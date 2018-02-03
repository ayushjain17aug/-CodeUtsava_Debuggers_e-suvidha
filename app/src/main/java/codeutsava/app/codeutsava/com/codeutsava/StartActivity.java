package codeutsava.app.codeutsava.com.codeutsava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import codeutsava.app.codeutsava.com.codeutsava.Maps.View.MainActivity;

public class StartActivity extends AppCompatActivity {

    private Button searchButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        searchButton = (Button) findViewById(R.id.search_button);
        addButton = (Button) findViewById(R.id.add_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, AddLocation.class);
                startActivity(intent);
            }
        });
        /*
        intent.putExtra("latitude",positionInfos.get(position).getLatitude());
        intent.putExtra("latitude",positionInfos.get(position).getLongitude());
        context.startActivity(intent);*/
    }

}
