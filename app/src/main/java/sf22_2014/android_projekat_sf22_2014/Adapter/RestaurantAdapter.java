package sf22_2014.android_projekat_sf22_2014.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sf22_2014.android_projekat_sf22_2014.Fragment.MapFragment;
import sf22_2014.android_projekat_sf22_2014.Fragment.RestaurantsFragment;
import sf22_2014.android_projekat_sf22_2014.Interface.RecyclerItemClickListener;
import sf22_2014.android_projekat_sf22_2014.Model.Restaurant;
import sf22_2014.android_projekat_sf22_2014.R;
import sf22_2014.android_projekat_sf22_2014.RestaurantActivity;

import static sf22_2014.android_projekat_sf22_2014.R.id.recyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    private Context mContext;
    private List<Restaurant> restaurantList;


    public RestaurantAdapter(Context mContext, List<Restaurant> restaurantList){
        this.mContext = mContext;
        this.restaurantList = restaurantList;
    }

    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_card, parent, false);

        return new MyViewHolder(itemView, mContext, restaurantList);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.title.setText(restaurant.getName());
        holder.description.setText(restaurant.getDescription());
        holder.imageView.setImageBitmap(restaurant.getSmallPhoto());

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        public TextView title, description;
        public ImageView imageView;
        List<Restaurant> restaurantList;
        Context mContext;

        public MyViewHolder(View itemView, Context mContext, List<Restaurant> restaurantList) {
            super(itemView);
            this.restaurantList = restaurantList;
            this.mContext = mContext;

            itemView.setOnClickListener(this); //za svaki klik poziva se onClick metoda

            title = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_description);
            imageView = (ImageView) itemView.findViewById(R.id.thumbnail);

            Typeface font = Typeface.createFromAsset(mContext.getAssets(), "steelfish rg.ttf");
            title.setTypeface(font);
            description.setTypeface(font);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Restaurant restaurant = this.restaurantList.get(position);
            Intent intent = new Intent(this.mContext, RestaurantActivity.class);
            intent.putExtra("res_id", restaurant.getId());
            intent.putExtra("res_title", restaurant.getName());
            intent.putExtra("res_description", restaurant.getDescription());
            intent.putExtra("res_site", restaurant.getUrl());
            intent.putExtra("res_start_hour", restaurant.getStartHour());
            intent.putExtra("res_end_hour", restaurant.getEndHour());
            intent.putExtra("res_phone", restaurant.getPhone());
            intent.putExtra("res_email", restaurant.getEmail());
            this.mContext.startActivity(intent);
            Log.d("RestaurantAdapter", "OnClick Recycler View");

    }

    }
}
