package programmingsolutions.tafebuddy;


import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import custom_tabs.CustomTabsHelper;
import rss_classes.ReadRSS;




public class MainPage extends AppCompatActivity implements View.OnClickListener, CustomTabActivityHelper.ConnectionCallback, NavigationView.OnNavigationItemSelectedListener, SharedPreferences.OnSharedPreferenceChangeListener {

    //creating the recycler view to handle the rss feed
    RecyclerView recyclerView ;


    //setting up the custom tab helper class
    private CustomTabActivityHelper customTabActivityHelper;

    //this is for Firebase analysiation
    private FirebaseAnalytics mFirebaseAnalytics;


    //Urls
    static final String COURSE_SCHEDULE = "https://my.tafesa.edu.au/PROD/bwskfshd.P_CrseSchd";
    static final String COUNSELLING_BOOKING = "http://itstudies.simplybook.me/index/about";
    static final String FAQ = "https://www.tafensw.edu.au/about-tafensw/enterprise-bargaining/faq";
    static final String VIDEO = "https://tafesaedu.sharepoint.com/portals/hub/_layouts/15/PointPublishing.aspx?app=video&p=h";
    static final String ONENOTE = "https://www.onenote.com/notebooks?session=f3dc3e95-ea68-4957-b69f-b028f7593d2e&auth=2";
    static final String ONEDRIVE = "https://tafesaedu-my.sharepoint.com/personal/timothy_finn_student_tafesa_edu_au/Documents/Forms/All.aspx";
    static final String ACCOUNT = "https://my.tafesa.edu.au/PROD/bwsksphs.P_ViewStatement";
    static final String USERDETAILS = "https://my.tafesa.edu.au/PROD/twbkwbis.P_GenMenu?name=bmenu.P_MainMnu#pageName=bmenu--P_GenMnu___UID1&pageReferrerId=&pageDepth=2&options=false";
    static final String EMAIL = "https://outlook.office.com/owa/?realm=student.tafesa.edu.au&exsvurl=1&delegatedOrg=tafesaedu.onmicrosoft.com&ll-cc=1033&modurl=0";
    static final String COURSE_INFORMATION = "https://www.tafensw.edu.au/courses/tafe-nsw-course-search";


    //prefrences Strings
    public static final String KEY_PREF_MAP_PREFRENCE = "mapPreference";
    public static final String KEY_PREF_RSS_FEED_PREFERENCE = "RSS_Feed_Settings";
    public static final String KEY_USER_NAME_PREFERENCE = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        // Obtain the FirebaseAnalytics instance.
        //this will want access to the Wake Lock permission no way to get arnound it
        //will have to see if battery drain is a problem with it!!
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        customTabActivityHelper = new CustomTabActivityHelper();
        //lets the helper know that we want this class to be used.
        customTabActivityHelper.setConnectionCallback(this);

        //loading prefrences for the main page
        loadPrefrences();

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


        //this will lock the screen to portrait view and display it in fullscreen mode.f
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //setting up the toolbar layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainpage_toobar);
        setSupportActionBar(toolbar);

        //creating a navigation drawer.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //creating the action bar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //assigning the buttons
        LinearLayout btnAgenda= (LinearLayout) findViewById(R.id.btnAgenda);
        LinearLayout btnMap = (LinearLayout) findViewById(R.id.btnCampuses);
        LinearLayout btnBookCounselling = (LinearLayout) findViewById(R.id.btnBookCounselling);
        LinearLayout btnCalendar = (LinearLayout) findViewById(R.id.btnMyAccount);
        LinearLayout btnVideos = (LinearLayout) findViewById(R.id.btnEmail);
        LinearLayout btnFAQPage = (LinearLayout) findViewById(R.id.btnFAQ);

        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //debugging
                Toast.makeText(MainPage.this, "Agenda Clicked", Toast.LENGTH_SHORT).show();

                //parseing the string into a uri
                Uri uri = Uri.parse(COURSE_SCHEDULE);
                openCustomTab(uri);

                //testing how firebase analytics works have to wait 24 hours to view it on
                // the console.
                Bundle analytics = new Bundle();
                analytics.putString(FirebaseAnalytics.Param.ITEM_NAME, "AgendaCustomTab");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, analytics);
            }
        });
        btnBookCounselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "BookCounselling Clicked", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse(COUNSELLING_BOOKING);
                openCustomTab(uri);

            }
        });

        btnFAQPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "FAQPage Clicked", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse(FAQ);
                openCustomTab(uri);
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Account Clicked", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse(ACCOUNT);
                openCustomTab(uri);
            }
        });

        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Email Clicked", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse(EMAIL);
                openCustomTab(uri);

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

    //this handles the click events on the navigaion menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_courses) {
            Uri uri = Uri.parse(COURSE_INFORMATION);
            openCustomTab(uri);
        } else if (id == R.id.nav_videos) {
            Uri uri = Uri.parse(VIDEO);
            openCustomTab(uri);
        } else if (id == R.id.nav_onenote) {
            Uri uri = Uri.parse(ONENOTE);
            openCustomTab(uri);
        } else if (id == R.id.nav_onedrive) {
            Uri uri = Uri.parse(ONEDRIVE);
            openCustomTab(uri);
        } else if (id == R.id.nav_tasks) {
            Uri uri = Uri.parse(USERDETAILS);
            openCustomTab(uri);
        } else if (id == R.id.nav_files) {
            Uri uri = Uri.parse(ACCOUNT);
            openCustomTab(uri);
        } else if (id == R.id.nav_settings){
            Intent settingsIntent = new Intent(MainPage.this, SettingsActivity.class);
            startActivity(settingsIntent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Method handling all of the Custom tab customization.
    private void openCustomTab(Uri uri){



        //creating a custom tab and making customizing the animations and toolbar.
        final CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabActivityHelper.getSession());

        //setting the toolbar color also allowing the tab to show the title of the wabpage.
        builder.setToolbarColor(Color.RED).setShowTitle(true);

        //this will change the custom animations for custom tab using animatinos resource files.
        builder.setStartAnimations(MainPage.this, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(MainPage.this, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);

        //setting the home button in the custom tab
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_back));

        //this will hide the URL bar when a user scrolls down the page.
        builder.enableUrlBarHiding();

        //this adds to the menu android default share.
        builder.addDefaultShareMenuItem();

        //this should add actions to the custom tab pages
        prepareActionButton(builder);

        CustomTabsHelper.getPackageNameToUse(this);
        //this will check to see what browser is availible to handle custom tab.
        CustomTabsIntent customTabsIntent = builder.build();
        CustomTabsHelper.addKeepAliveExtra(getBaseContext(), customTabsIntent.intent);

        //sending the information to the helper to process
        CustomTabActivityHelper.openCustomTab(MainPage.this, builder.build(), uri, new WebviewFallback());
    }


    //preparing the action button for custom tabs sits in the action bar.
    private void prepareActionButton(CustomTabsIntent.Builder builder) {
        // An example intent that sends an email.
        Intent actionIntent = new Intent(Intent.ACTION_SEND);
        actionIntent.setType("*/*");
        actionIntent.putExtra(Intent.EXTRA_EMAIL, "example@example.com");
        actionIntent.putExtra(Intent.EXTRA_SUBJECT, "example");
        PendingIntent pi = PendingIntent.getActivity(this, 0, actionIntent, 0);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_ic_action_email);
        builder.setActionButton(icon, "send email", pi, true);
    }

    //this is a listner that checks to see what prefrences have changed dont know if we really need it
    //tho since we can check it in the methods when we call the prefrences on the oncreate methods.
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }


    //this is where you can set the prefrences for the app call from the onCreate Method as having it
    //in the onStart method makes it load the RSS feed every Time it loads
    private void loadPrefrences(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isRssFeedEnabled = sharedPreferences.getBoolean(KEY_PREF_RSS_FEED_PREFERENCE,true);
        if(isRssFeedEnabled){

            try {
                //link the recycler view
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                //initializing the rss feed
                ReadRSS readRSS = new ReadRSS(this, recyclerView);
                // Start download RSS task
                readRSS.execute();
                // Debug the thread name
                Log.d("RSS", Thread.currentThread().getName());
            }catch (Exception e){
                System.out.println("Error in retireving RSS Feed");
            }

        }

        TextView userName = (TextView) findViewById(R.id.txtViewName);

        String user_name = sharedPreferences.getString("user_name","");

        userName.setText(user_name);

    }



    }

