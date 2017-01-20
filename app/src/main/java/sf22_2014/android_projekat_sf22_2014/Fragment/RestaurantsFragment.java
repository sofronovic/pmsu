package sf22_2014.android_projekat_sf22_2014.Fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sf22_2014.android_projekat_sf22_2014.Adapter.RestaurantAdapter;
import sf22_2014.android_projekat_sf22_2014.Database.MySQLiteHelper;
import sf22_2014.android_projekat_sf22_2014.Filter.RestaurantFilter;
import sf22_2014.android_projekat_sf22_2014.Model.Restaurant;
import sf22_2014.android_projekat_sf22_2014.R;


public class RestaurantsFragment extends Fragment  {

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<Restaurant> restaurantList;
    private MySQLiteHelper dbHelper;
    SearchView searchView;
    RestaurantFilter restaurantFilter;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.restaurants_layout, container, false);
        searchView = (SearchView) view.findViewById(R.id.searchView);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        dbHelper = MySQLiteHelper.getInstance(getContext().getApplicationContext());

        adapter = new RestaurantAdapter(getContext(), dbHelper.getAllUser());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);

    try {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                   // adapter.filterList(query);
                    //return true;
                } catch (Exception ex) {
                    Log.e("OnQueryTextSubmit", "Exception: ", ex);
                    //return false;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    adapter.filterList(newText);
                   // return true;
                } catch (Exception ex) {
                    Log.e("OnQueryTextChange", "Exception: ", ex);
                    //return false;
                }
                return true;
            }
        });
    }catch (Exception e){
        Log.e("SearchViewLISTENER", "Exception: " , e);
    }


        return view;
    }

    /*private void prepareRestaurants() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.res1);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.res2);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.res3);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.res4);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.res5);
        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.res6);

*//*        Restaurant test = new Restaurant(1, "Test", "Test Opis", bitmap, "Test Site", 7, 24, "0653552001", "sofronovicn@gmail.com",
                createRestaurantAddress("Bulevar Mihajla Pupina", "21000", "Serbia"));*//*
  *//*      restaurantList.add(test);*//*
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
*/

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



 /*   public String createRestaurantAddress(String street, String postalCode, String country) {
        String address = new Address(Locale.getDefault());
        address.setFeatureName(street);
        address.setPostalCode(postalCode);
        address.setCountryName(country);
        return address;
    }
*/
}
