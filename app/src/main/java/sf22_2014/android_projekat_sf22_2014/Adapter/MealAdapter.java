package sf22_2014.android_projekat_sf22_2014.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sf22_2014.android_projekat_sf22_2014.Model.Meal;
import sf22_2014.android_projekat_sf22_2014.R;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolderMeal> {

    private Context mContext;
    private List<Meal> mealList;

    public MealAdapter(Context mContext, List<Meal> mealList){
        this.mContext = mContext;
        this.mealList = mealList;
    }

    @Override
    public MealAdapter.MyViewHolderMeal onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.meal_card, parent, false);

        return new MyViewHolderMeal(view, mContext, mealList);
    }

    @Override
    public void onBindViewHolder(MealAdapter.MyViewHolderMeal holder, int position) {
        Meal meal = mealList.get(position);
        holder.title.setText(meal.getName());
        holder.description.setText(meal.getDescription());
        holder.imageView.setImageBitmap(meal.getPhoto());

        holder.price.setText(String.valueOf(meal.getPrice()));

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class MyViewHolderMeal extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title, description , price, priceStatic;
        public ImageView imageView;
        public Context mContext;
        public List<Meal> mealList;

        public MyViewHolderMeal(View itemView, Context mContext, List<Meal> mealList) {
            super(itemView);
            this.mealList = mealList;
            this.mContext = mContext;

            itemView.setOnClickListener(this);

            title = (TextView) itemView.findViewById(R.id.titleMealCardView);
            description = (TextView) itemView.findViewById(R.id.descriptionMealCardView);
            price = (TextView) itemView.findViewById(R.id.priceMealCardView);
            imageView = (ImageView) itemView.findViewById(R.id.mealCardImageView);
/*
            priceStatic =(TextView) itemView.findViewById(R.id.priceStatic);
*/
            Typeface font = Typeface.createFromAsset(mContext.getAssets(), "steelfish rg.ttf");
            title.setTypeface(font);
            description.setTypeface(font);
            price.setTypeface(font);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Meal meal = this.mealList.get(position);
        }
    }
}