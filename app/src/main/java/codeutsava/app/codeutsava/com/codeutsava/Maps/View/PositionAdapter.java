package codeutsava.app.codeutsava.com.codeutsava.Maps.View;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import codeutsava.app.codeutsava.com.codeutsava.Maps.Model.Data.PositionInfo;
import codeutsava.app.codeutsava.com.codeutsava.R;
import codeutsava.app.codeutsava.com.codeutsava.Rating.View.RatingReviewActivity;

public class PositionAdapter extends
        RecyclerView.Adapter<PositionAdapter.ViewHolder> {

    private static ClickListener clickListener;
    private List<PositionInfo> positionInfos;
    private Context context;
    private int selectedTheme;
    private Drawable emojiForCircle = null;
    private Drawable backgroundCircle = null;
    private int upperCardSectionColor = 0;

    public PositionAdapter(List<PositionInfo> styles,
                           Context context, ClickListener cardClickListener, int selectedTheme) {
        this.context = context;
        this.positionInfos = styles;
        this.selectedTheme = selectedTheme;
        clickListener = cardClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int singleRvCardToUse = R.layout.single_location_map_view_rv_card;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(singleRvCardToUse, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return positionInfos.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder card, final int position) {

        final PositionInfo positionInfo = positionInfos.get(position);
        card.moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RatingReviewActivity.class);
                intent.putExtra("latitude", "" + positionInfos.get(position).getLatitude());
                intent.putExtra("longitude", "" + positionInfos.get(position).getLongitude());
                context.startActivity(intent);
            }
        });
        card.nameTextView.setText(positionInfo.getName());
        card.addressTextView.setText(positionInfo.getAddress());
        BigDecimal n = new BigDecimal(positionInfo.getRating());
        n = n.setScale(2, BigDecimal.ROUND_CEILING);
        card.rating.setText(n.toString());
        card.hoursTextView.setText(positionInfo.getHours());

        if (positionInfo.getFlagm().equals("1") && positionInfo.getFlagf().equals("1")) {
            emojiForCircle = ResourcesCompat.getDrawable(context.getResources(), R.drawable.male_female, null);
            backgroundCircle = ResourcesCompat.getDrawable(context.getResources(), R.drawable.white_circle, null);
            setColors(R.color.colorPrimary_gray, R.color.white, R.color.white, R.color.cardHourAndPhoneTextColor_gray,
                    R.color.cardHourAndPhoneTextColor_gray, R.color.cardHourAndPhoneTextColor_gray,
                    R.color.cardHourAndPhoneTextColor_gray, R.color.white, R.color.white);
            setAlphas(card, .41f, .48f, 100f, .48f);
        } else if (positionInfo.getFlagf().equals("1")) {
            emojiForCircle = ResourcesCompat.getDrawable(context.getResources(), R.drawable.female, null);
            backgroundCircle = ResourcesCompat.getDrawable(context.getResources(), R.drawable.white_circle, null);
            setColors(R.color.colorPrimary_gray, R.color.white, R.color.white, R.color.cardHourAndPhoneTextColor_gray,
                    R.color.cardHourAndPhoneTextColor_gray, R.color.cardHourAndPhoneTextColor_gray,
                    R.color.cardHourAndPhoneTextColor_gray, R.color.white, R.color.white);
            setAlphas(card, .41f, .48f, 100f, .48f);
        } else {
            emojiForCircle = ResourcesCompat.getDrawable(context.getResources(), R.drawable.male_logo, null);
            backgroundCircle = ResourcesCompat.getDrawable(context.getResources(), R.drawable.white_circle, null);
            setColors(R.color.colorPrimary_gray, R.color.white, R.color.white, R.color.cardHourAndPhoneTextColor_gray,
                    R.color.cardHourAndPhoneTextColor_gray, R.color.cardHourAndPhoneTextColor_gray,
                    R.color.cardHourAndPhoneTextColor_gray, R.color.white, R.color.white);
            setAlphas(card, .41f, .48f, 100f, .48f);
        }

        card.emojiImageView.setImageDrawable(emojiForCircle);
        card.constraintUpperColorSection.setBackgroundColor(upperCardSectionColor);
        card.backgroundCircleImageView.setImageDrawable(backgroundCircle);
    }

    private void setColors(int colorForUpperCard, int colorForName, int colorForAddress,
                           int colorForHours, int colorForHoursHeader, int colorForPhoneNum,
                           int colorForPhoneHeader, int colorForDistanceNum, int colorForMilesAbbreviation) {
        upperCardSectionColor = ResourcesCompat.getColor(context.getResources(), colorForUpperCard, null);
    }

    private void setAlphas(ViewHolder card, float addressAlpha, float hoursHeaderAlpha, float hoursNumAlpha,
                           float ratingAlpha) {
        card.addressTextView.setAlpha(addressAlpha);
        card.hoursHeaderTextView.setAlpha(hoursHeaderAlpha);
        card.hoursTextView.setAlpha(hoursNumAlpha);
        card.rating.setAlpha(ratingAlpha);

    }

    public interface ClickListener {
        void onItemClick(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView addressTextView;
        TextView hoursTextView;
        TextView rating;
        TextView hoursHeaderTextView;
        TextView moreInfo;
        ConstraintLayout constraintUpperColorSection;
        CardView cardView;
        ImageView backgroundCircleImageView;
        ImageView emojiImageView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.location_name);
            addressTextView = (TextView) itemView.findViewById(R.id.location_description);
            hoursTextView = (TextView) itemView.findViewById(R.id.location_hours);
            rating = (TextView) itemView.findViewById(R.id.location_rating);
            moreInfo = (TextView) itemView.findViewById(R.id.moreInfo);
            backgroundCircleImageView = (ImageView) itemView.findViewById(R.id.background_circle);
            emojiImageView = (ImageView) itemView.findViewById(R.id.emoji);
            constraintUpperColorSection = (ConstraintLayout) itemView.findViewById(R.id.constraint_upper_color);
            hoursHeaderTextView = (TextView) itemView.findViewById(R.id.hours_header);
            cardView = (CardView) itemView.findViewById(R.id.map_view_location_card);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(getLayoutPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
        }

        public interface ClickListener {
            void onItemClick(int position);
        }
    }
}
