package codeutsava.app.codeutsava.com.codeutsava.Rating.View;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;

import java.io.File;
import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.R;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.PostRatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.RetrofitRatingProvider;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Presenter.RatingPresenter;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Presenter.RatingPresenterImpl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;

public class PostRatingActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, PostRatingView {


    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    RatingBar Overall, Hygiene, Infra, Safety;
    EditText review, feedback;
    private ProgressBar progressBar;
    private SliderLayout mDemoSlider;
    private RatingBar overall, hygiene, infra, safety;
    private RecyclerView recyclerView;
    private RatingPresenter presenter;
    private String lt = "", lng = "";
    private Uri uri;
    private MultipartBody.Part fileToUpload = null;
    private RequestBody filename = null;
    private TextView img_name;
    private String revw, fdbk;
    private float overall1, hygiene1, infra1, safety1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_rating);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lt = extras.getString("latitude");
            lng = extras.getString("longitude");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Overall = (RatingBar) findViewById(R.id.overall_score);
        Hygiene = (RatingBar) findViewById(R.id.hygiene);
        Infra = (RatingBar) findViewById(R.id.infra);
        Safety = (RatingBar) findViewById(R.id.safety);
        Button selectUploadButton = (Button) findViewById(R.id.select_image);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar1);
        img_name = (TextView) findViewById(R.id.img_select);
        review = (EditText) findViewById(R.id.review);
        feedback = (EditText) findViewById(R.id.feedback);

        selectUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("abhi", "In the on Click");
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });
        presenter = new RatingPresenterImpl(new RetrofitRatingProvider(), this, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, PostRatingActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("abhi", "abhi");
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, PostRatingActivity.this);
                File file = new File(filePath);
                Log.d("abhi", "Filename " + file.getName());
                img_name.setText(file.getName());
                //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
                //presenter.postRating(lt,lng,overall1,hygiene1,infra1,safety1,revw,fdbk,fileToUpload,filename);
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (uri != null) {
            String filePath = getRealPathFromURIPath(uri, PostRatingActivity.this);
            Log.d("abhi", filePath);
            File file = new File(filePath);
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            img_name.setText(file.getName());
        }
    }

    public void onSubmit(View view) {
        revw = "";
        fdbk = "";
        if (review.getText() != null)
            revw = review.getText().toString().trim();
        if (feedback.getText() != null)
            fdbk = feedback.getText().toString().trim();

        overall1 = Overall.getRating();
        hygiene1 = Hygiene.getRating();
        infra1 = Infra.getRating();
        safety1 = Safety.getRating();

        // Log.d("abhi","In On Submit"+filename+fileToUpload.toString());
        presenter.postRating(lt, lng, overall1, hygiene1, infra1, safety1, revw, fdbk, fileToUpload, filename);
    }

    public void onCancel(View view) {
        Intent intent = new Intent(this, RatingReviewActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("abhi", "Permission has been denied");
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
        Snackbar.make(findViewById(R.id.rel_layout), message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void showResult(PostRatingData data) {
        Intent intent = new Intent(this, RatingReviewActivity.class);
        intent.putExtra("latitude", lt);
        intent.putExtra("longitude", lng);
        startActivity(intent);
        finish();
    }
}