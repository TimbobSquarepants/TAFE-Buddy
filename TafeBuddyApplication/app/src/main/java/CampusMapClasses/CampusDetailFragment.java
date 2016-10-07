package CampusMapClasses;


import android.Manifest;
import android.app.Activity;


import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import programmingsolutions.tafebuddy.CampusDetailActivity;
import programmingsolutions.tafebuddy.CampusListActivity;
import programmingsolutions.tafebuddy.R;
import CampusMapClasses.Campus.CampusContent;


/**
 * A fragment representing a single Campus detail screen.
 * This fragment is either contained in a {@link CampusListActivity}
 * in two-pane mode (on tablets) or a {@link CampusDetailActivity}
 * on handsets.
 */
public class CampusDetailFragment extends android.app.Fragment implements OnMapReadyCallback {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private CampusContent.Campus mItem;

    //google map object
    private GoogleMap mMap;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CampusDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = CampusContent.CAMPUS_MAP.get(getArguments().getString(ARG_ITEM_ID));


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.campusName);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.campus_detail, container, false);


        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        fragment.getMapAsync(this);
    }

    //this will setup the map for use


    //this is where the map is created and where we are specifying what map to load.
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setIndoorEnabled(true);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);

        //Adding Markers and moving the camera to the location
        //we can also specify the zoom amount.

        if(mItem.campusName == "Adelaide City Campus"){

            LatLng adelaideCBDCampus = new LatLng(-34.924225, 138.595189);
            mMap.addMarker(new MarkerOptions().position(adelaideCBDCampus).title("TAFESA Adelaide City Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.924225,138.595189),17 ));
        }
        if(mItem.campusName == "Tonsley Campus"){

            LatLng tonsleyCampus = new LatLng(-35.000809, 138.572425);
            mMap.addMarker(new MarkerOptions().position(tonsleyCampus).title("TAFESA Tonsley Campus Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-35.000809, 138.572425),17));
        }
        if(mItem.campusName == "Urrbrae Campus"){

            LatLng urrbraeCampus = new LatLng(-34.966858, 138.625295);
            mMap.addMarker(new MarkerOptions().position(urrbraeCampus).title("TAFESA Urrbrae Campus Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.966858, 138.625295),17));
        }
        if(mItem.campusName == "Morphetville Campus"){

            LatLng morphetvilleCampus = new LatLng(-34.973050, 138.546031);
            mMap.addMarker(new MarkerOptions().position(morphetvilleCampus).title("TAFESA Morphetville Campus Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.973050, 138.546031),17));
        }
        if(mItem.campusName == "Regency Campus"){

            LatLng regencyCampus = new LatLng(-34.873101, 138.567916);
            mMap.addMarker(new MarkerOptions().position(regencyCampus).title("TAFESA Regency Campus Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.873101, 138.567916),17));
        }
        if(mItem.campusName == "Port Adelaide Campus"){

            LatLng portAdelaideCampus = new LatLng(-34.843949,138.500524);
            mMap.addMarker(new MarkerOptions().position(portAdelaideCampus).title("TAFESA Port Adelaide Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.843949,138.500524),17));
        }
        if(mItem.campusName == "Tea Tree Gully Campus"){

            LatLng teaTreeGully = new LatLng(-34.832280,138.696305);
            mMap.addMarker(new MarkerOptions().position(teaTreeGully).title("TAFESA Tea Tree Gully Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.832280,138.696305),17));
        }
        if(mItem.campusName == "Giles Plains Campus"){

            LatLng gilesPlainsCampus = new LatLng(-34.851530, 138.652238);
            mMap.addMarker(new MarkerOptions().position(gilesPlainsCampus).title("TAFESA Giles Plains Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.851530, 138.652238),17));
        }
        if(mItem.campusName == "Parafield Campus"){

            LatLng parafieldCampus = new LatLng(-34.788660, 138.633438);
            mMap.addMarker(new MarkerOptions().position(parafieldCampus).title("TAFESA Parafield Campus Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.788660, 138.633438),17));
        }
        if(mItem.campusName == "Elizabeth Campus"){

            LatLng elizabethCampus = new LatLng(-34.712957, 138.671950);
            mMap.addMarker(new MarkerOptions().position(elizabethCampus).title("TAFESA Elizabeth Campus Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.712957, 138.671950),17));
        }
        if(mItem.campusName == "Gawler Campus"){

            LatLng gawlerCampus = new LatLng(-34.597199, 138.750434);
            mMap.addMarker(new MarkerOptions().position(gawlerCampus).title("TAFESA Gawler Campus Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34.597199, 138.750434),17));
        }
        if(mItem.campusName == "Noarlunga Campus"){

            LatLng noarlungaCampus = new LatLng(-35.140019, 138.497695);
            mMap.addMarker(new MarkerOptions().position(noarlungaCampus).title("TAFESA Noarlunga Campus Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-35.140019, 138.497695),17));
        }
        if(mItem.campusName == "Mount Barker Campus"){

            LatLng mountBarkerCampus = new LatLng(-35.069198, 138.855127);
            mMap.addMarker(new MarkerOptions().position(mountBarkerCampus).title("TAFESA Mount Barker Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-35.069198, 138.855127),17));
        }

    }
}
