package programmingsolutions.tafebuddy;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import CustomTabs.WebviewFallback;



public class MainPage extends AppCompatActivity implements View.OnClickListener, CustomTabActivityHelper.ConnectionCallback, NavigationView.OnNavigationItemSelectedListener {


    private CustomTabsClient mClient;
    //setting up the Imagebuttons for navigation.
    private ImageButton btnAgenda;
    private ImageButton btnMap;
    private ImageButton btnBookCounselling;
    private ImageButton btnCalendar;
    private ImageButton btnVideos;
    private ImageButton btnFAQPage;


    private CustomTabActivityHelper customTabActivityHelper;

    //Urls
    final String COURSE_SCHEDULE = "https://my.tafesa.edu.au/PROD/bwskfshd.P_CrseSchd";
    final String COUNSELLING_BOOKING = "http://itstudies.simplybook.me/index/about";
    final String FAQ = "https://www.tafensw.edu.au/about-tafensw/enterprise-bargaining/faq";
    final String CALENDER = "https://outlook.office.com/owa/?realm=student.tafesa.edu.au&exsvurl=1&ll-cc=1033&modurl=1&path=/calendar/view/Month";
    final String VIDEO = "https://tafesaedu.sharepoint.com/portals/hub/_layouts/15/PointPublishing.aspx?app=video&p=h";

    private View mMayLaunchUrlButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        //setting up the toolbar layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainpage_toobar);
        setSupportActionBar(toolbar);




        //creating a navigation drawer.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        customTabActivityHelper= new CustomTabActivityHelper();
        //lets the helper know that we want this class to be used.
        customTabActivityHelper.setConnectionCallback(this);



        mMayLaunchUrlButton = findViewById(R.id.btnVideos);
        mMayLaunchUrlButton.setEnabled(false);
        mMayLaunchUrlButton.setOnClickListener(this);



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
        final CustomTabsIntent.Builder intent = new CustomTabsIntent.Builder(customTabActivityHelper.getSession());

        //setting the toolbar color
        intent.setToolbarColor(Color.RED);

        //this will change the custom animations for custom tab using animatinos resource files.
        intent.setStartAnimations(MainPage.this, R.anim.slide_in_right, R.anim.slide_out_left);
        intent.setExitAnimations(MainPage.this, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);

        //setting the home button in the custom tab
        intent.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_arrow_back));

        //this will hide the URL bar when a user scrolls down the page.
        intent.enableUrlBarHiding();

        //this adds to the menu android default share.
        intent.addDefaultShareMenuItem();

        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //debugging
                Toast.makeText(MainPage.this, "Agenda Clicked", Toast.LENGTH_SHORT).show();

                //parseing the string into a uri
                Uri uri  = Uri.parse(COURSE_SCHEDULE);

                //sending the information to the helper to process
                 CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());

            }
        });


        btnBookCounselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "BookCounselling Clicked", Toast.LENGTH_SHORT).show();
                //parseing the string into a uri
                Uri uri  = Uri.parse(COUNSELLING_BOOKING);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());

            }
        });


        btnFAQPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "FAQPage Clicked", Toast.LENGTH_SHORT).show();
                //parseing the string into a uri
                Uri uri  = Uri.parse(FAQ);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());;
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Calendar Clicked", Toast.LENGTH_SHORT).show();
                //parseing the string into a uri
                Uri uri  = Uri.parse(CALENDER);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());
            }
        });

        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Videos Clicked", Toast.LENGTH_SHORT).show();
                //parseing the string into a uri
                Uri uri  = Uri.parse(VIDEO);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());
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
        mMayLaunchUrlButton.setEnabled(true);

    }

    @Override
    public void onCustomTabsDisconnected() {
        mMayLaunchUrlButton.setEnabled(false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
