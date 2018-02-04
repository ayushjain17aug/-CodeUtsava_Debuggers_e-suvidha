package codeutsava.app.codeutsava.com.codeutsava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.helper.SessionManager;

public class CheckinActivity extends AppCompatActivity {

    public Button enter;
    public Button leave;
    public Button logout;
    public TextView textView;
    SessionManager sessionManager;
    private Spinner spinner1, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enter = (Button) findViewById(R.id.enter);
        leave = (Button) findViewById(R.id.leave);
        logout = (Button) findViewById(R.id.logout);
        textView = (TextView) findViewById(R.id.text);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Entered");
            }
        });

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Left");
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(getApplicationContext());
                sessionManager.setLogin(false);
                Intent intent = new Intent(CheckinActivity.this, StartActivity.class);
                startActivity(intent);
            }

        });

        addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }

    public void addItemsOnSpinner2() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("Swachh Bharat Toilet");
        list.add("Sulabh Toilet");
        list.add("Durlabh Toilet");

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list1 = new ArrayList<String>();
        list1.add("Toilet 1");
        list1.add("1Toilet 2");
        list1.add("Toilet 2");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {


    }
}
