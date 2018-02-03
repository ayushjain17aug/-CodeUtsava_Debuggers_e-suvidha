package codeutsava.app.codeutsava.com.codeutsava.Maps.Presenter;

import android.content.Context;

import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionData;
import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.PositionProvider;
import codeutsava.app.codeutsava.com.codeutsava.Maps.PositionCallback;
import codeutsava.app.codeutsava.com.codeutsava.Maps.View.PositionsView;
import codeutsava.app.codeutsava.com.codeutsava.R;

public class PositionPresenterImpl  implements PositionPresenter {

    private PositionProvider provider;
    private PositionsView view;
    private Context context;

    public PositionPresenterImpl(PositionProvider provider, PositionsView view, Context context) {
        this.context = context;
        this.provider = provider;
        this.view = view;
    }

    @Override
    public void getPosition(Double lat, Double lon) {
        view.showProgressBar(true);
        provider.getPosition(lat, lon, new PositionCallback() {
            @Override
            public void onSuccess(PositionData body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    view.showPosition(body.getData());
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
}

