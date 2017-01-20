package sf22_2014.android_projekat_sf22_2014.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sf22_2014.android_projekat_sf22_2014.R;

public class RestaurantInfoFragment extends Fragment implements OnMapReadyCallback, LocationListener,
                GoogleMap.OnMarkerClickListener {

    private TextView title, description, days, address, startHour, endHour, site,
            siteStatic, facebook, faceBookstatic, features, drink, delivery;
    private Button button, buttonSMS, buttonEmail;
    private ImageView openImage, closedImage, mainImage;
    private String naslov;

    private Date date;
    private Date startCompare;
    private Date endCompare;
    private int start;
    private int end;
    private String restaurantAddress;
    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private LocationManager locationManager;

    final String inputFormat = "HH:mm";
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat);

    private Marker restaurantMarker;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurant_info_layout, container, false);

        title = (TextView) view.findViewById(R.id.res_info_title);
        description = (TextView) view.findViewById(R.id.res_info_description);
        days = (TextView) view.findViewById(R.id.res_info_days);
        address = (TextView) view.findViewById(R.id.res_info_address);
        site = (TextView) view.findViewById(R.id.res_info_site);
        siteStatic = (TextView) view.findViewById(R.id.site_static);
        faceBookstatic = (TextView) view.findViewById(R.id.facebook_static);
        facebook = (TextView) view.findViewById(R.id.res_info_facebook);
        features = (TextView) view.findViewById(R.id.features);
        drink = (TextView) view.findViewById(R.id.drink);
        delivery = (TextView) view.findViewById(R.id.delivery);
        startHour = (TextView) view.findViewById(R.id.res_info_startHour);
        endHour = (TextView) view.findViewById(R.id.res_info_endHour);
        button = (Button) view.findViewById(R.id.call);
        buttonSMS = (Button) view.findViewById(R.id.sms);
        buttonEmail = (Button) view.findViewById(R.id.email);
        openImage = (ImageView) view.findViewById(R.id.res_info_open);
        closedImage = (ImageView) view.findViewById(R.id.res_info_closed);
        mainImage = (ImageView) view.findViewById(R.id.res_info_imageView);

        naslov = getActivity().getIntent().getStringExtra("res_title");
        String opis = getActivity().getIntent().getStringExtra("res_description");
        String url = getActivity().getIntent().getStringExtra("res_site");
        start = getActivity().getIntent().getIntExtra("res_start_hour", 0);
        end = getActivity().getIntent().getIntExtra("res_end_hour", 0);
        final String phone = getActivity().getIntent().getStringExtra("res_phone");
        final String email = getActivity().getIntent().getStringExtra("res_email");
        restaurantAddress = getActivity().getIntent().getStringExtra("res_address");
        String image_path = getActivity().getIntent().getStringExtra("res_image");

        title.setText(naslov);
        description.setText(opis);
        site.setText(url);
        startHour.setText("from: " + String.valueOf(start) + "h");
        endHour.setText("to: " + String.valueOf(end) + "h");
        address.setText(restaurantAddress);

        File file = new File(image_path);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        mainImage.setImageBitmap(bitmap);

        mMapView = (MapView) view.findViewById(R.id.google_map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + phone));
                startActivity(i);
            }
        });

        buttonSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.putExtra("sms_body", "I would like to order: ");
                i.putExtra("address", phone);
                i.setData(Uri.parse("smsto:" + phone));
/*
               i.setType("vnd.android-dir/mms-sms");
*/
                startActivity(i);
            }
        });

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);

                i.setType("plain/text");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                i.putExtra(Intent.EXTRA_SUBJECT, "Order");
                startActivity(i);
            }
        });


        checkTime(start, end);

        setFont();
        return view;
    }

    public void setFont() {
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "steelfish rg.ttf");
        title.setTypeface(font);
        description.setTypeface(font);
        days.setTypeface(font);
        address.setTypeface(font);
        site.setTypeface(font);
        siteStatic.setTypeface(font);
        faceBookstatic.setTypeface(font);
        facebook.setTypeface(font);
        features.setTypeface(font);
        drink.setTypeface(font);
        delivery.setTypeface(font);
        startHour.setTypeface(font);
        endHour.setTypeface(font);
    }

    public void checkTime(int start, int end) {

        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR);
        int minute = now.get(Calendar.MINUTE);

        date = parseDate(hour + ":" + minute);
        int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        /*Date startint = new Date(start);
        Date endInt = new Date(end);
        startCompare = startint;
        endCompare = endInt;
*/
        if (start > hours && end > hours) {
            closedImage.setVisibility(View.VISIBLE);
            openImage.setVisibility(View.GONE);

            buttonSMS.setEnabled(false);
            buttonEmail.setEnabled(false);
            button.setEnabled(false);
        } else {
            closedImage.setVisibility(View.GONE);
            openImage.setVisibility(View.VISIBLE);

            buttonSMS.setEnabled(true);
            buttonEmail.setEnabled(true);
            button.setEnabled(true);
        }
    }

    private Date parseDate(String date) {
        try {
            return inputParser.parse(date);
        } catch (ParseException e) {
            return new Date(0);
        }
    }

    private Date parseDateInteger(int date) {
        try {
            return inputParser.parse(String.valueOf(date));
        } catch (ParseException e) {
            return new Date(0);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        Geocoder gc = new Geocoder(getContext());
        try {
            List<Address> list = gc.getFromLocationName(restaurantAddress, 1);
            Address add = list.get(0);
            String locality = add.getAddressLine(0);
            double lat = add.getLatitude();
            double lng = add.getLongitude();
            LatLng latLng = new LatLng(lat, lng);

            restaurantMarker = mGoogleMap.addMarker(new MarkerOptions().position(latLng).title(naslov));
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

        } catch (IOException e) {
            e.printStackTrace();
        }

        mGoogleMap.setOnMarkerClickListener(this);
    }

    @Override
    public void onLocationChanged(Location location) {


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
