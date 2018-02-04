package codeutsava.app.codeutsava.com.codeutsava.Graph.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data.Location;
import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.RetrofitGraphProvider;
import codeutsava.app.codeutsava.com.codeutsava.Graph.Presenter.GraphPresenterImp;
import codeutsava.app.codeutsava.com.codeutsava.R;

public class LocationActivity extends AppCompatActivity implements LocationView {

    RecyclerView recyclerView;
    private GraphPresenterImp presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.location_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        presenter = new GraphPresenterImp(this, new RetrofitGraphProvider(), this);
        presenter.getLocations(0);
    }

    @Override
    public void showProgressBar(boolean b) {

    }

    @Override
    public void showLocations(List<Location> locations) {
        LocationAdapter adapter = new LocationAdapter(locations, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
    }
}
