package codeutsava.app.codeutsava.com.codeutsava.Rating.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;
import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.R;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingsReviews;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.RetrofitRatingProvider;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Presenter.RatingPresenter;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Presenter.RatingPresenterImpl;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RatingReviewActivity extends AppCompatActivity implements RatingView {

    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    ReviewsAdapter adapter;
    private ProgressBar progressBar;
    private SliderLayout mDemoSlider;
    private RatingBar overall, hygiene, infra, safety;
    private RecyclerView recyclerView;
    private RatingPresenter presenter;
    private String lt = "", lng = "";
    private Uri uri;
    private MultipartBody.Part fileToUpload = null;
    private RequestBody filename = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lt = extras.getString("latitude");
            lng = extras.getString("longitude");
        }*/
        lt = "21.248471";
        lng = "81.579622";

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        overall = (RatingBar) findViewById(R.id.overall_score);
        hygiene = (RatingBar) findViewById(R.id.hygiene);
        infra = (RatingBar) findViewById(R.id.infra);
        safety = (RatingBar) findViewById(R.id.safety);


        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));


        presenter = new RatingPresenterImpl(new RetrofitRatingProvider(), this, this);
        presenter.getRating(lt, lng);

    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.rating_relative_layout), message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void showRatings(RatingsReviews data) {

        //Ratings
        overall.setRating(data.getOverallScore());
        hygiene.setRating(data.getHygiene());
        infra.setRating(data.getInfrastructure());
        safety.setRating(data.getSafety());

        //Images
        List<String> images = data.getImages();
        HashMap<String, String> url_maps = new HashMap<String, String>();
        int i = 0;
        for (String img : images)
            url_maps.put("img" + (i++), img);

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image(url_maps.get(name));
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //  mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);

        //Reviews
        adapter = new ReviewsAdapter(data.getReviews());
        recyclerView.setAdapter(adapter);
    }

    public void addRating(View v) {
        Intent intent = new Intent(this, PostRatingActivity.class);
        intent.putExtra("latitude", lt);
        intent.putExtra("longitude", lng);
        startActivity(intent);
    }
}