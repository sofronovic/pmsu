package sf22_2014.android_projekat_sf22_2014.Fragment;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sf22_2014.android_projekat_sf22_2014.Adapter.RestaurantAdapter;
import sf22_2014.android_projekat_sf22_2014.Model.Restaurant;
import sf22_2014.android_projekat_sf22_2014.R;



public class RestaurantsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textView;
    private RestaurantAdapter adapter;
    private List<Restaurant> restaurantList;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.restaurants_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        restaurantList = new ArrayList<>();
        adapter = new RestaurantAdapter(getContext(), restaurantList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        /*recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));*/
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

//        textView = (TextView) view.findViewById(R.id.card_title);
//        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "minimal.otf");
//        textView.setTypeface(font);

        prepareRestaurants();

        return view;

    }

    private void prepareRestaurants(){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.res1);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.res2);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.res3);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.res4);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.res5);
        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.res6);

        Restaurant test = new Restaurant(1, "Test", "Test Opis", bitmap, "Test Site", 7, 24, "0653552001", "sofronovicn@gmail.com");
        restaurantList.add(test);
        Restaurant r = new Restaurant(1, "Chinese food house", "Kineska hrana", bitmap1, "asdasd", 9, 24, "0653552001", "sofronovicn@gmail.com");
        Restaurant r1 = new Restaurant(2, "Palermo Indjija", "Brza hrana", bitmap2, 07, 24, "asdasda");
        Restaurant r2 = new Restaurant(3, "Fast Food Petica", "Kod Spome sa rostilja", bitmap3);
        Restaurant r3 = new Restaurant(4, "Fornetii", "Kevine fornete", bitmap4);
        Restaurant r4 = new Restaurant(4, "Micko's burek", "Burek i peciva", bitmap5);

        restaurantList.add(r);
        restaurantList.add(r1);
        restaurantList.add(r2);
        restaurantList.add(r3);
        restaurantList.add(r4);
        adapter.notifyDataSetChanged();
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}