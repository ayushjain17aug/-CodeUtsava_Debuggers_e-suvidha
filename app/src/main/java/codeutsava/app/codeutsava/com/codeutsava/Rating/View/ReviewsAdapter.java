package codeutsava.app.codeutsava.com.codeutsava.Rating.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.R;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    private List<String> reviews;

    public ReviewsAdapter(List<String> reviews) {
        this.reviews = reviews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String review = reviews.get(position);
        holder.review.setText(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public final TextView review;

        public MyViewHolder(View itemView) {
            super(itemView);
            review = (TextView) itemView.findViewById(R.id.review);
        }
    }
}
