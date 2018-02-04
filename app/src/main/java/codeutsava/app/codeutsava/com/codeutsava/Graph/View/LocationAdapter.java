package codeutsava.app.codeutsava.com.codeutsava.Graph.View;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.Graph.Model.Data.Location;
import codeutsava.app.codeutsava.com.codeutsava.R;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    private List<Location> locations;
    private Context context;

    public LocationAdapter(List<Location> locations, Context context) {
        this.locations = locations;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Location location = locations.get(position);
        holder.name.setText(location.getName());
        holder.address.setText(location.getAddress());
        Picasso.with(context).load(location.getImage()).into(holder.image, new com.squareup.picasso.Callback() {

            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Log.d("abhi", "Error in image loading");
                holder.progressBar.setVisibility(View.GONE);
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, GraphChoiceActivity.class);
                i.putExtra("id", location.getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, address;
        ImageView image;
        ProgressBar progressBar;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}
