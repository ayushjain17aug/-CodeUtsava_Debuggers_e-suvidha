package codeutsava.app.codeutsava.com.codeutsava.Rating.Presenter;

import android.content.Context;
import android.os.Handler;

import codeutsava.app.codeutsava.com.codeutsava.R;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.PostRatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.RatingProvider;
import codeutsava.app.codeutsava.com.codeutsava.Rating.PostRatingCallback;
import codeutsava.app.codeutsava.com.codeutsava.Rating.RatingCallback;
import codeutsava.app.codeutsava.com.codeutsava.Rating.View.PostRatingView;
import codeutsava.app.codeutsava.com.codeutsava.Rating.View.RatingView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RatingPresenterImpl implements RatingPresenter {

    private RatingProvider provider;
    private RatingView view;
    private PostRatingView view1;
    private Context context;

    public RatingPresenterImpl(RatingProvider provider, RatingView view, Context context) {
        this.context = context;
        this.provider = provider;
        this.view = view;
    }

    public RatingPresenterImpl(RatingProvider provider, PostRatingView view, Context context) {
        this.context = context;
        this.provider = provider;
        this.view1 = view;
    }

    @Override
    public void getRating(String lt, String lng) {
        view.showProgressBar(true);
        provider.getRating(lt, lng, new RatingCallback() {
            @Override
            public void onSuccess(RatingData body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    view.showRatings(body.getData());
                } else
                    view.showMessage(body.getMessage());
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showMessage(context.getString(R.string.Connection_error));
            }
        });
    }

    @Override
    public void postRating(String lt, String lng, float overall, float hygiene, float infra,
                           float safety, String review, String feedback, MultipartBody.Part file, RequestBody name) {
        view1.showProgressBar(true);
        provider.postRating(lt, lng, overall, hygiene, infra, safety, review, feedback, file, name, new PostRatingCallback() {
            @Override
            public void onSuccess(final PostRatingData body) {
                view1.showProgressBar(false);
                view1.showMessage(body.getMessage());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view1.showResult(body);
                    }
                }, 2500);
            }

            @Override
            public void onFailure() {
                view1.showProgressBar(false);
                view1.showMessage(context.getString(R.string.Connection_error));
            }
        });
    }
}
