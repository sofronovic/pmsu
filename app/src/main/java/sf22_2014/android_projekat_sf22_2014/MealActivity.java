package sf22_2014.android_projekat_sf22_2014;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MealActivity extends AppCompatActivity {

    private TextView name, ready, description, price, priceStatic;
    private ImageView image;
    private Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        name = (TextView) findViewById(R.id.meal_info_name);
        description = (TextView) findViewById(R.id.meal_info_description);
        price = (TextView) findViewById(R.id.meal_info_price);
        ready = (TextView) findViewById(R.id.meal_info_ready);
        priceStatic = (TextView) findViewById(R.id.meal_info_price_static);

        String mealName = getIntent().getStringExtra("meal_name");
        String mealDesc = getIntent().getStringExtra("meal_desc");
        double mealPrice = getIntent().getDoubleExtra("meal_price", 0);

        name.setText(mealName);
        description.setText(mealDesc);
        price.setText(String.valueOf(mealPrice));

        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getAssets(), "steelfish rg.ttf");
        name.setTypeface(font);
        description.setTypeface(font);
        price.setTypeface(font);
        ready.setTypeface(font);
        priceStatic.setTypeface(font);
    }
}
