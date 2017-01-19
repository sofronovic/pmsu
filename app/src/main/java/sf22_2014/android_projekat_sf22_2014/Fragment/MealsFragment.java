package sf22_2014.android_projekat_sf22_2014.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sf22_2014.android_projekat_sf22_2014.Adapter.MealAdapter;
import sf22_2014.android_projekat_sf22_2014.Database.MySQLiteHelper;
import sf22_2014.android_projekat_sf22_2014.Model.Meal;
import sf22_2014.android_projekat_sf22_2014.R;

public class MealsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textView;
    private List<Meal> mealList;
    private MealAdapter mealAdapter;
    private MySQLiteHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meals_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMeals);
        dbHelper = MySQLiteHelper.getInstance(getContext().getApplicationContext());

        mealAdapter = new MealAdapter(getContext(), dbHelper.getAllMeal());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mealAdapter);

/*
        prepareMeals();
*/
        return view;
    }

   /* private void prepareMeals() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meal1);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.meal5);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.meal4);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.meal7);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.meal6);

        Meal m1 = new Meal(1, "Pljeca", "Sa rostilja", 250, bitmap);
        Meal m2 = new Meal(1, "Index", "Masan sendvic", 450, bitmap2);
        Meal m3 = new Meal(1, "Burger", "Prtinin burger", 700, bitmap3);
        Meal m4 = new Meal(1, "Pljeca", "Sa rostilja", 250, bitmap4);
        Meal m5 = new Meal(1, "Index", "Masan sendvic", 450, bitmap1);
        Meal m6 = new Meal(1, "Burger", "Prtinin burger", 700, bitmap3);
        Meal m8 = new Meal(1, "Pljeca", "Sa rostilja", 250, bitmap2);
        Meal m7 = new Meal(1, "Index", "Masan sendvic", 450, bitmap4);
        Meal m9 = new Meal(1, "Burger", "Prtinin burger", 700, bitmap);
        mealList.add(m1);
        mealList.add(m2);
        mealList.add(m3);
        mealList.add(m4);
        mealList.add(m5);
        mealList.add(m6);
        mealList.add(m7);
        mealList.add(m8);
        mealList.add(m9);

        mealAdapter.notifyDataSetChanged();
    }*/
}
