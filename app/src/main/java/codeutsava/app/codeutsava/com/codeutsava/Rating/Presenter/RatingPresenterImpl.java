package codeutsava.app.codeutsava.com.codeutsava.Rating.Presenter;

import android.content.Context;

import codeutsava.app.codeutsava.com.codeutsava.R;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.PostRatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.RatingData;
import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.RatingProvider;
import codeutsava.app.codeutsava.com.codeutsava.Rating.PostRatingCallback;
import codeutsava.app.codeutsava.com.codeutsava.Rating.RatingCallback;
import codeutsava.app.codeutsava.com.codeutsava.Rating.View.RatingView;

public class RatingPresenterImpl implements RatingPresenter {

    private RatingProvider provider;
    private RatingView view;
    private Context context;

    public RatingPresenterImpl(RatingProvider provider, RatingView view, Context context) {
        this.context = context;
        this.provider = provider;
        this.view = view;
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
    public void postRating(String lt, String lng, float overall, float hygiene, float infra, float safety, String review, String feedback) {
        view.showProgressBar(true);
        provider.postRating(lt, lng, overall, hygiene, infra, safety, review, feedback, new PostRatingCallback() {
            @Override
            public void onSuccess(PostRatingData body) {
                view.showProgressBar(false);
                view.showMessage(body.getMessage());
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showMessage(context.getString(R.string.Connection_error));
            }
        });
    }
}
