package sf22_2014.android_projekat_sf22_2014;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

public class MealActivity extends AppCompatActivity {

    private TextView name, ready, description, price, priceStatic, orderName;
    private ImageView image;
    private Button orderButton;
    private RelativeLayout orderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        name = (TextView) findViewById(R.id.meal_info_name);
        description = (TextView) findViewById(R.id.meal_info_description);
        price = (TextView) findViewById(R.id.meal_info_price);
        ready = (TextView) findViewById(R.id.meal_info_ready);
        priceStatic = (TextView) findViewById(R.id.meal_info_price_static);
        image = (ImageView) findViewById(R.id.meal_info_image);
        orderButton = (Button) findViewById(R.id.meal_info_order_button);

        final String mealName = getIntent().getStringExtra("meal_name");
        String mealDesc = getIntent().getStringExtra("meal_desc");
        final double mealPrice = getIntent().getDoubleExtra("meal_price", 0);
        String image_path = getIntent().getStringExtra("meal_image");

        name.setText(mealName);
        description.setText(mealDesc);
        price.setText(String.valueOf(mealPrice));

        File file = new File(image_path);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        image.setImageBitmap(bitmap);

        orderLayout = (RelativeLayout) findViewById(R.id.orderLayout);
        orderName = (TextView) findViewById(R.id.orderName);
        final TextView cooking = (TextView) findViewById(R.id.orderStatic);
        TextView MealStatic = (TextView) findViewById(R.id.MealStatic);
        TextView PriceStatic = (TextView) findViewById(R.id.PriceStatic);
        final TextView orderPrice = (TextView) findViewById(R.id.orderPrice);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        orderFood(mealName, mealPrice);
                        orderLayout.setVisibility(View.VISIBLE);
                        orderName.setText(mealName);
                        orderPrice.setText(String.valueOf(mealPrice));

                       // finish();
                    }
                }, 3000);

            }
        });

        setFont();
    }

    private void orderFood(final String mealName, final double mealPrice){

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
