package programmingsolutions.tafebuddy;


import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import CustomTabs.WebviewFallback;
import programmingsolutions.tafebuddy.CampusMasterFlow.CampusData.CampusListActivity;


public class MainPage extends AppCompatActivity implements View.OnClickListener, CustomTabActivityHelper.ConnectionCallback {

    //setting up the Imagebuttons for navigation.
    private ImageButton btnAgenda;
    private ImageButton btnMap;
    private ImageButton btnBookCounselling;
    private ImageButton btnCalendar;
    private ImageButton btnVideos;
    private ImageButton btnFAQPage;

    private CustomTabActivityHelper customTabActivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        customTabActivityHelper= new CustomTabActivityHelper();
        //lets the helper know that we want this class to be used.
        customTabActivityHelper.setConnectionCallback(this);

        //Urls
        final String COURSE_SCHEDULE = "https://my.tafesa.edu.au/PROD/bwskfshd.P_CrseSchd";
        final String COUNSELLING_BOOKING = "http://itstudies.simplybook.me/index/about";
        final String FAQ = "https://www.tafensw.edu.au/about-tafensw/enterprise-bargaining/faq";
        final String CALENDER = "https://outlook.office.com/owa/?realm=student.tafesa.edu.au&exsvurl=1&ll-cc=1033&modurl=1&path=/calendar/view/Month";
        final String VIDEO = "https://tafesaedu.sharepoint.com/portals/hub/_layouts/15/PointPublishing.aspx?app=video&p=h";

        //getting the urls ready in a bundle so they can passed to maylaunch method
        Bundle campusBundle = new Bundle();
        campusBundle.putString("CourseScehdule",COURSE_SCHEDULE);
        campusBundle.putString("Counselling",COUNSELLING_BOOKING);
        campusBundle.putString("FAQ",FAQ);
        campusBundle.putString("Calender",CALENDER);
        campusBundle.putString("Video",VIDEO);

        //sending the URLs that we want to be preloaded.

        customTabActivityHelper.mayLaunchUrl(null,campusBundle,null);



        //assigning the buttons

        btnAgenda= (ImageButton) findViewById(R.id.btnAgenda);
        btnMap = (ImageButton) findViewById(R.id.btnMap);
        btnBookCounselling = (ImageButton) findViewById(R.id.btnBookCounselling);
        btnCalendar = (ImageButton) findViewById(R.id.btnCalendar);
        btnVideos = (ImageButton) findViewById(R.id.btnVideos);
        btnFAQPage = (ImageButton) findViewById(R.id.btnFAQPage);

        //setting click events added a toast message for debugging purposes.

        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //debugging
                Toast.makeText(MainPage.this, "Agenda Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder(customTabActivityHelper.getSession()).build();
                //parseing the string into a uri
                Uri uri  = Uri.parse(COURSE_SCHEDULE);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent,uri,new WebviewFallback());

            }
        });


        btnBookCounselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "BookCounselling Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder(customTabActivityHelper.getSession()).build();
                //parseing the string into a uri
                Uri uri  = Uri.parse(COUNSELLING_BOOKING);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent,uri,new WebviewFallback());

            }
        });


        btnFAQPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "FAQPage Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder(customTabActivityHelper.getSession()).build();
                //parseing the string into a uri
                Uri uri  = Uri.parse(FAQ);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent,uri,new WebviewFallback());;
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Calendar Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder(customTabActivityHelper.getSession()).build();
                //parseing the string into a uri
                Uri uri  = Uri.parse(CALENDER);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent,uri,new WebviewFallback());
            }
        });

        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Videos Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder(customTabActivityHelper.getSession()).build();
                //parseing the string into a uri
                Uri uri  = Uri.parse(VIDEO);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent,uri,new WebviewFallback());
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Map Clicked", Toast.LENGTH_SHORT).show();
               Intent mapIntent = new Intent(MainPage.this, CampusListActivity.class);
                startActivity(mapIntent);
            }
        });


    }

    //housekeeping freeing up space or binding to the service.

    @Override
    protected void onDestroy() {
        super.onDestroy();
        customTabActivityHelper.setConnectionCallback(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        customTabActivityHelper.bindCustomTabsService(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        customTabActivityHelper.unbindCustomTabsService(this);

    }

    //if we wanted to change how the buttons work or if we couldnt connect to the custom tab we can
    //create code to stop people from accessing the buttons
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCustomTabsConnected() {

    }

    @Override
    public void onCustomTabsDisconnected() {

    }
}
