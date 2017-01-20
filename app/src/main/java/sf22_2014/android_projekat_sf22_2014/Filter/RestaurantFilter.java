package sf22_2014.android_projekat_sf22_2014.Filter;


import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import sf22_2014.android_projekat_sf22_2014.Adapter.RestaurantAdapter;
import sf22_2014.android_projekat_sf22_2014.Model.Restaurant;

public class RestaurantFilter extends Filter {

    private List<Restaurant> restaurantList;
    private List<Restaurant> filteredRestaurantList;
    private RestaurantAdapter restaurantAdapter;

    public RestaurantFilter(List<Restaurant> restaurantList,
                            RestaurantAdapter restaurantAdapter) {
        this.restaurantList = restaurantList;
        this.filteredRestaurantList = new ArrayList<>();
        this.restaurantAdapter = restaurantAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        filteredRestaurantList.clear();
        final FilterResults results = new FilterResults();

        for (Restaurant i : restaurantList){
            if(i.getName().toLowerCase().trim().contains(charSequence)){
                filteredRestaurantList.add(i);
            }
        }

        results.values = filteredRestaurantList;
        results.count = filteredRestaurantList.size();

        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        restaurantAdapter.setList(filteredRestaurantList);
        restaurantAdapter.notifyDataSetChanged();
    }

}
