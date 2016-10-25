package programmingsolutions.tafebuddy;


import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;

import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import android.widget.ImageButton;

import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.analytics.FirebaseAnalytics;

import rss_classes.ReadRSS;


public class MainPage extends AppCompatActivity implements View.OnClickListener, CustomTabActivityHelper.ConnectionCallback, NavigationView.OnNavigationItemSelectedListener {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        // Obtain the FirebaseAnalytics instance.
        //this will want access to the Wake Lock permission no way to get arnound it
        //will have to see if battery drain is a problem with it!!
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

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

        customTabActivityHelper = new CustomTabActivityHelper();
        //lets the helper know that we want this class to be used.
        customTabActivityHelper.setConnectionCallback(this);
        customTabActivityHelper.getSession();

        //link the recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //initializing the rss feed
        ReadRSS readRSS = new ReadRSS(this, recyclerView);
        // Start download RSS task
        readRSS.execute();
        // Debug the thread name
        Log.d("RSS", Thread.currentThread().getName());

    }


    public void logEvent(String name, Bundle params) {

    }

    //housekeeping freeing up space or binding to the service.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        customTabActivityHelper.setConnectionCallback(null);
    }

    //bimds the custom tab to this activity
    @Override
    protected void onStart() {
        super.onStart();
        customTabActivityHelper.bindCustomTabsService(this);

        //creating a custom tab and making customizing the animations and toolbar.
        final CustomTabsIntent.Builder intent = new CustomTabsIntent.Builder(customTabActivityHelper.getSession());
        final CustomTabsIntent.Builder intentBlue = new CustomTabsIntent.Builder(customTabActivityHelper.getSession());
        //setting the toolbar color also allowing the tab to show the title of the wabpage.
        intent.setToolbarColor(Color.RED).setShowTitle(true);
        intentBlue.setToolbarColor(Color.BLUE).setShowTitle(true);

        //this will change the custom animations for custom tab using animatinos resource files.
        intent.setStartAnimations(MainPage.this, R.anim.slide_in_right, R.anim.slide_out_left);
        intent.setExitAnimations(MainPage.this, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        intentBlue.setStartAnimations(MainPage.this, R.anim.slide_in_right, R.anim.slide_out_left);
        intentBlue.setExitAnimations(MainPage.this, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);

        //setting the home button in the custom tab
        intent.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_back));
        intentBlue.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_back));
        //this will hide the URL bar when a user scrolls down the page.
        intent.enableUrlBarHiding();
        intentBlue.enableUrlBarHiding();

        //this adds to the menu android default share.
        intent.addDefaultShareMenuItem();
        intentBlue.addDefaultShareMenuItem();
        // prepareActionButton(intent);
        //   prepareActionButton(intentBlue);

        //assigning the buttons
        ImageButton btnAgenda = (ImageButton) findViewById(R.id.btnAgenda);
        ImageButton btnMap = (ImageButton) findViewById(R.id.btnMap);
        ImageButton btnBookCounselling = (ImageButton) findViewById(R.id.btnBookCounselling);
        ImageButton btnCalendar = (ImageButton) findViewById(R.id.btnAccount);
        ImageButton btnVideos = (ImageButton) findViewById(R.id.btnEmail);
        ImageButton btnFAQPage = (ImageButton) findViewById(R.id.btnFAQPage);

        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //debugging
                Toast.makeText(MainPage.this, "Agenda Clicked", Toast.LENGTH_SHORT).show();
                //parseing the string into a uri
                Uri uri = Uri.parse(COURSE_SCHEDULE);
                //sending the information to the helper to process
                CustomTabActivityHelper.openCustomTab(MainPage.this, intent.build(), uri, new WebviewFallback());

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
                CustomTabActivityHelper.openCustomTab(MainPage.this, intent.build(), uri, new WebviewFallback());

            }
        });

        btnFAQPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "FAQPage Clicked", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse(FAQ);
                CustomTabActivityHelper.openCustomTab(MainPage.this, intentBlue.build(), uri, new WebviewFallback());
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Account Clicked", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse(ACCOUNT);
                CustomTabActivityHelper.openCustomTab(MainPage.this, intent.build(), uri, new WebviewFallback());
            }
        });

        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Email Clicked", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse(EMAIL);
                CustomTabActivityHelper.openCustomTab(MainPage.this, intentBlue.build(), uri, new WebviewFallback());

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
        final CustomTabsIntent.Builder intent = new CustomTabsIntent.Builder(customTabActivityHelper.getSession()).setShowTitle(true);

        //setting the toolbar color
        intent.setToolbarColor(Color.RED);

        //this will change the custom animations for custom tab using animatinos resource files.
        intent.setStartAnimations(MainPage.this, R.anim.slide_in_right, R.anim.slide_out_left);
        intent.setExitAnimations(MainPage.this, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);

        //setting the home button in the custom tab
        intent.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_back));

        //this will hide the URL bar when a user scrolls down the page.
        intent.enableUrlBarHiding();

        //this adds to the menu android default share.
        intent.addDefaultShareMenuItem();
        //prepareActionButton(intent);

        if (id == R.id.nav_courses) {
            Uri uri = Uri.parse(COURSE_INFORMATION);
            CustomTabActivityHelper.openCustomTab(MainPage.this, intent.build(), uri, new WebviewFallback());

        } else if (id == R.id.nav_videos) {
            Uri uri = Uri.parse(VIDEO);
            CustomTabActivityHelper.openCustomTab(MainPage.this, intent.build(), uri, new WebviewFallback());
        } else if (id == R.id.nav_onenote) {
            Uri uri = Uri.parse(ONENOTE);
            CustomTabActivityHelper.openCustomTab(MainPage.this, intent.build(), uri, new WebviewFallback());
        } else if (id == R.id.nav_onedrive) {
            Uri uri = Uri.parse(ONEDRIVE);
            CustomTabActivityHelper.openCustomTab(MainPage.this, intent.build(), uri, new WebviewFallback());
        } else if (id == R.id.nav_tasks) {
            Uri uri = Uri.parse(USERDETAILS);
            CustomTabActivityHelper.openCustomTab(MainPage.this, intent.build(), uri, new WebviewFallback());
        } else if (id == R.id.nav_files) {
            Uri uri = Uri.parse(ACCOUNT);
            CustomTabActivityHelper.openCustomTab(MainPage.this, intent.build(), uri, new WebviewFallback());
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /* private void prepareActionButton(CustomTabsIntent.Builder intent) {
        // An example intent that sends an email.
        Intent actionIntent = new Intent(Intent.ACTION_SEND);
        actionIntent.setType("*/
       /* actionIntent.putExtra(Intent.EXTRA_EMAIL, "example@example.com");
        actionIntent.putExtra(Intent.EXTRA_SUBJECT, "Tafe IT Studies Application");
        PendingIntent pi = PendingIntent.getActivity(this, 0, actionIntent, 0);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_ic_action_email);
        intent.setActionButton(icon, "send email", pi, true);*/



}

