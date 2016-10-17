package programmingsolutions.tafebuddy;

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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;


public class MainPage extends AppCompatActivity implements View.OnClickListener, CustomTabActivityHelper.ConnectionCallback, NavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_URL = "extra.url";

    //setting up the Imagebuttons for navigation.
    private ImageButton btnAgenda;
    private ImageButton btnMap;
    private ImageButton btnBookCounselling;
    private ImageButton btnCalendar;
    private ImageButton btnVideos;
    private ImageButton btnFAQPage;
    //setting up the custom tab helper class
    private CustomTabActivityHelper customTabActivityHelper;
    //Urls
    final String COURSE_SCHEDULE = "https://my.tafesa.edu.au/PROD/bwskfshd.P_CrseSchd";
    final String COUNSELLING_BOOKING = "http://itstudies.simplybook.me/index/about";
    final String FAQ = "https://www.tafensw.edu.au/about-tafensw/enterprise-bargaining/faq";
    final String CALENDER = "https://outlook.office.com/owa/?realm=student.tafesa.edu.au&exsvurl=1&ll-cc=1033&modurl=1&path=/calendar/view/Month";
    final String VIDEO = "https://tafesaedu.sharepoint.com/portals/hub/_layouts/15/PointPublishing.aspx?app=video&p=h";
    final String ONENOTE = "https://www.onenote.com/notebooks?session=f3dc3e95-ea68-4957-b69f-b028f7593d2e&auth=2";
    final String ONEDRIVE= "https://tafesaedu-my.sharepoint.com/personal/timothy_finn_student_tafesa_edu_au/Documents/Forms/All.aspx";
    final String ACCOUNT= "https://my.tafesa.edu.au/PROD/bwsksphs.P_ViewStatement";
    final String USERDETAILS="https://my.tafesa.edu.au/PROD/twbkwbis.P_GenMenu?name=bmenu.P_MainMnu#pageName=bmenu--P_GenMnu___UID1&pageReferrerId=&pageDepth=2&options=false";
    final String EMAIL="https://outlook.office.com/owa/?realm=student.tafesa.edu.au&exsvurl=1&delegatedOrg=tafesaedu.onmicrosoft.com&ll-cc=1033&modurl=0";
    final String ACCOUNT_MAIN_PAGE = "https://my.tafesa.edu.au/PROD/twbkwbis.P_GenMenu?name=bmenu.P_MainMnu#pageName=bmenu--P_StuMainMnu___UID0&pageReferrerId=&pageDepth=2&options=false";
    final String FILES= "http://netstorage.tafesa.edu.au/SitePages/Home.aspx";
    final String COURSE_INFORMATION= "https://www.tafensw.edu.au/courses/tafe-nsw-course-search";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);

        //setting up the toolbar layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainpage_toobar);
        setSupportActionBar(toolbar);

        //creating a navigation drawer.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //creating the action bar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        customTabActivityHelper= new CustomTabActivityHelper();
        //lets the helper know that we want this class to be used.
        customTabActivityHelper.setConnectionCallback(this);
        customTabActivityHelper.getSession();


        //assigning the buttons
        btnAgenda= (ImageButton) findViewById(R.id.btnAgenda);
        btnMap = (ImageButton) findViewById(R.id.btnMap);
        btnBookCounselling = (ImageButton) findViewById(R.id.btnBookCounselling);
        btnCalendar = (ImageButton) findViewById(R.id.btnAccount);
        btnVideos = (ImageButton) findViewById(R.id.btnEmail);
        btnFAQPage = (ImageButton) findViewById(R.id.btnFAQPage);

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
        intent.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_arrow_back));
        intentBlue.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_arrow_back));
        //this will hide the URL bar when a user scrolls down the page.
        intent.enableUrlBarHiding();
        intentBlue.enableUrlBarHiding();

        //this adds to the menu android default share.
        intent.addDefaultShareMenuItem();
        intentBlue.addDefaultShareMenuItem();
       // prepareActionButton(intent);
     //   prepareActionButton(intentBlue);

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
                Uri uri  = Uri.parse(COUNSELLING_BOOKING);
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());

            }
        });

         btnFAQPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "FAQPage Clicked", Toast.LENGTH_SHORT).show();
                Uri uri  = Uri.parse(FAQ);
                CustomTabActivityHelper.openCustomTab(MainPage.this,intentBlue.build(),uri,new WebviewFallback());;
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Account Clicked", Toast.LENGTH_SHORT).show();
                Uri uri  = Uri.parse(ACCOUNT);
                CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());
            }
        });

        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Email Clicked", Toast.LENGTH_SHORT).show();
                Uri uri=Uri.parse(EMAIL);
                CustomTabActivityHelper.openCustomTab(MainPage.this,intentBlue.build(),uri,new WebviewFallback());

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
    //bimds the custom tab to this activity
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
        intent.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_arrow_back));

        //this will hide the URL bar when a user scrolls down the page.
        intent.enableUrlBarHiding();

        //this adds to the menu android default share.
        intent.addDefaultShareMenuItem();
        //prepareActionButton(intent);

        if(id == R.id.nav_courses){
            Uri uri = Uri.parse(COURSE_INFORMATION);
            CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());

        }

        else if(id== R.id.nav_videos){
            Uri uri  = Uri.parse(VIDEO);
            CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());
        }
        else if(id==R.id.nav_onenote){
            Uri uri = Uri.parse(ONENOTE);
            CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());
        }
        else if(id==R.id.nav_onedrive){
            Uri uri = Uri.parse(ONEDRIVE);
            CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());
        }
        else if(id==R.id.nav_tasks){
            Uri uri = Uri.parse(USERDETAILS);
            CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());
        }
        else  if(id==R.id.nav_files){
            Uri uri = Uri.parse(ACCOUNT);
            CustomTabActivityHelper.openCustomTab(MainPage.this,intent.build(),uri,new WebviewFallback());
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

