package codeutsava.app.codeutsava.com.codeutsava.Graph.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.RetrofitGraphProvider;
import codeutsava.app.codeutsava.com.codeutsava.Graph.Presenter.GraphPresenterImp;
import codeutsava.app.codeutsava.com.codeutsava.R;

public class GraphChoiceActivity extends AppCompatActivity implements GraphView {

    private Calendar myCalendar;
    private TextView editText, editText1;
    private String startDate, endDate, id;
    private Button ug, rrg;
    private GraphPresenterImp presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_choice);
        editText = (TextView) findViewById(R.id.date1);
        editText1 = (TextView) findViewById(R.id.date2);

        ug = (Button) findViewById(R.id.usageGraph);
        rrg = (Button) findViewById(R.id.reviewRating);
        presenter = new GraphPresenterImp(this, new RetrofitGraphProvider(), this);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
        }


        rrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getRRG(id);
            }
        });

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1();
            }
        };


        editText1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(GraphChoiceActivity.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(GraphChoiceActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        ug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getUG(id, startDate, endDate);
            }
        });

    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(myCalendar.getTime()));
        startDate = sdf.format(myCalendar.getTime());

    }

    private void updateLabel1() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText1.setText(sdf.format(myCalendar.getTime()));
        endDate = sdf.format(myCalendar.getTime());
    }


    @Override
    public void getShowGraph(String url) {
        Intent intent = new Intent(this, GraphActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}