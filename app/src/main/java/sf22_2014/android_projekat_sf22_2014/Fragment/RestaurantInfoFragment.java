package sf22_2014.android_projekat_sf22_2014.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import android.icu.text.DateFormat;

import java.text.SimpleDateFormat;

import android.icu.util.TimeZone;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import sf22_2014.android_projekat_sf22_2014.R;

public class RestaurantInfoFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private TextView title, description, days, address, startHour, endHour, site,
            siteStatic, facebook, faceBookstatic, features, drink, delivery;
    private Button button, buttonSMS, buttonEmail;
    private ImageView openImage, closedImage;

    private Date date;
    private Date startCompare;
    private Date endCompare;
    private int start;
    private int end;

    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private LocationManager locationManager;

    final String inputFormat = "HH:mm";
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat);

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


        String naslov = getActivity().getIntent().getStringExtra("res_title");
        String opis = getActivity().getIntent().getStringExtra("res_description");
        String url = getActivity().getIntent().getStringExtra("res_site");
        start = getActivity().getIntent().getIntExtra("res_start_hour", 0);
        end = getActivity().getIntent().getIntExtra("res_end_hour", 0);
        final String phone = getActivity().getIntent().getStringExtra("res_phone");
        final String email = getActivity().getIntent().getStringExtra("res_email");

        title.setText(naslov);
        description.setText(opis);
        site.setText(url);
        startHour.setText("from: " + String.valueOf(start) + "h");
        endHour.setText("to: " + String.valueOf(end) + "h");

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
                i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                startActivity(i);
            }
        });


        checkTime(start, end);

        setFont();
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
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
    public void onLocationChanged(Location location) {

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mGoogleMap.animateCamera(cameraUpdate);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(this);
        }
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
}
