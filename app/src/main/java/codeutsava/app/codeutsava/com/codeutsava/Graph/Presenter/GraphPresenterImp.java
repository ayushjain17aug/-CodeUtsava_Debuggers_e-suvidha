package codeutsava.app.codeutsava.com.codeutsava.Graph.Presenter;

import android.content.Context;

import codeutsava.app.codeutsava.com.codeutsava.Graph.GraphCallback;
import codeutsava.app.codeutsava.com.codeutsava.Graph.LocationCallback;
import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data.LocationData;
import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.GraphProvider;
import codeutsava.app.codeutsava.com.codeutsava.Graph.View.GraphView;
import codeutsava.app.codeutsava.com.codeutsava.Graph.View.LocationView;
import codeutsava.app.codeutsava.com.codeutsava.R;

/**
 * Created by Abhi on 04-Feb-18.
 */

public class GraphPresenterImp implements GraphPresenter {

    private LocationView view;
    private GraphView view1;
    private GraphProvider provider;
    private Context context;

    public GraphPresenterImp(LocationView view, GraphProvider provider, Context context) {
        this.view = view;
        this.provider = provider;
        this.context = context;
    }

    public GraphPresenterImp(GraphView view, GraphProvider provider, Context context) {
        this.view1 = view;
        this.provider = provider;
        this.context = context;
    }


    @Override
    public void getLocations(final int count) {
        view.showProgressBar(true);
        provider.getLocations(count, new LocationCallback() {
            @Override
            public void onSuccess(LocationData body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    view.showLocations(body.getLocationList());
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
    public void getUG(String id, String startDate, String endDate) {
        provider.getUsageGraph(id, startDate, endDate, new GraphCallback() {
            @Override
            public void onSuccess(String url) {
                view1.getShowGraph(url);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void getRRG(String id) {
        provider.getReviewRatingGraph(id, new GraphCallback() {
            @Override
            public void onSuccess(String url) {
                view1.getShowGraph(url);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
