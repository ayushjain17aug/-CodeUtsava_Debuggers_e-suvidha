package codeutsava.app.codeutsava.com.codeutsava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import codeutsava.app.codeutsava.com.codeutsava.Graph.View.LocationActivity;
import codeutsava.app.codeutsava.com.codeutsava.Maps.View.MainActivity;
import codeutsava.app.codeutsava.com.codeutsava.helper.SessionManager;

public class StartActivity extends AppCompatActivity {

    public SessionManager sessionManager;
    private Button searchButton;
    private Button addButton;
    private Button account;
    private Button usage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        searchButton = (Button) findViewById(R.id.search_button);
        addButton = (Button) findViewById(R.id.add_button);
        account = (Button) findViewById(R.id.accountButton);
        usage = (Button) findViewById(R.id.usageButton);


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
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(getApplicationContext());
                if (sessionManager.isLoggedIn()) {
                    Intent intent = new Intent(StartActivity.this, CheckinActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        usage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });


        /*
        intent.putExtra("latitude",positionInfos.get(position).getLatitude());
        intent.putExtra("latitude",positionInfos.get(position).getLongitude());
        context.startActivity(intent);*/
    }

}
