package codeutsava.app.codeutsava.com.codeutsava.Rating.View;

import codeutsava.app.codeutsava.com.codeutsava.Rating.Model.Data.PostRatingData;


public interface PostRatingView {
    void showProgressBar(boolean b);

    void showMessage(String message);

    void showResult(PostRatingData data);

}
