package sf22_2014.android_projekat_sf22_2014.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sf22_2014.android_projekat_sf22_2014.Database.MySQLiteHelper;
import sf22_2014.android_projekat_sf22_2014.Filter.RestaurantFilter;
import sf22_2014.android_projekat_sf22_2014.Model.Restaurant;
import sf22_2014.android_projekat_sf22_2014.R;
import sf22_2014.android_projekat_sf22_2014.RestaurantActivity;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>  {

    //private Cursor cursor;
    private Context mContext;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private LayoutInflater inflater;
    MySQLiteHelper dbHelper;
    RestaurantFilter restaurantFilter;
    List<Restaurant> filteredRestaurantList;

    public RestaurantAdapter(Context mContext, List<Restaurant> restaurantList1) {
        this.mContext = mContext;
        this.restaurantList = restaurantList1;
        this.filteredRestaurantList = restaurantList1;
        inflater = LayoutInflater.from(mContext);
        restaurantFilter = new RestaurantFilter(restaurantList, this);
    }

    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.from(parent.getContext())
                .inflate(R.layout.restaurant_card, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView, mContext, restaurantList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.title.setText(restaurant.getName());
        holder.description.setText(restaurant.getDescription());

        File imgFile = new File(restaurant.getSmallPhoto());
        Bitmap bitmap1 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        holder.imageView.setImageBitmap(bitmap1);

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void setList(List<Restaurant> list){
        this.filteredRestaurantList = list;
    }

    public void filterList(String text){
        restaurantFilter.filter(text);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title, description;
        public ImageView imageView;
        List<Restaurant> restaurantList;
        Context mContext;

        public MyViewHolder(View itemView, Context mContext, List<Restaurant> restaurantList) {
            super(itemView);
            this.restaurantList = restaurantList;
            this.mContext = mContext;

            title = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_description);
            imageView = (ImageView) itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(this); //za svaki klik poziva se onClick metoda
            imageView.setOnClickListener(this);

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
            intent.putExtra("res_address", restaurant.getAddress());
            intent.putExtra("res_image", restaurant.getSmallPhoto());
            this.mContext.startActivity(intent);
            Log.d("RestaurantAdapter", "OnClick Recycler View");

        }

        public void setFilter(ArrayList<Restaurant> newList) {
            restaurantList = new ArrayList<>();
            restaurantList.addAll(newList);

            notifyDataSetChanged();
        }
    }

}
