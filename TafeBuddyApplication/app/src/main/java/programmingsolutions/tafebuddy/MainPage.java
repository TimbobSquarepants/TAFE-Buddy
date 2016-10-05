package programmingsolutions.tafebuddy;


import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import programmingsolutions.tafebuddy.CampusMasterFlow.CampusData.CampusListActivity;


public class MainPage extends AppCompatActivity {

    //setting up the Imagebuttons for navigation.
    private ImageButton btnAgenda;
    private ImageButton btnMap;
    private ImageButton btnBookCounselling;
    private ImageButton btnCalendar;
    private ImageButton btnVideos;
    private ImageButton btnFAQPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

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
                Toast.makeText(MainPage.this, "Agenda Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
                final String url = "https://my.tafesa.edu.au/PROD/bwskfshd.P_CrseSchd";
                intent.launchUrl(MainPage.this, Uri.parse(url));

            }
        });


        btnBookCounselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "BookCounselling Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
                final String url = "http://itstudies.simplybook.me/index/about";
                intent.launchUrl(MainPage.this, Uri.parse(url));

            }
        });


        btnFAQPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "FAQPage Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
                final String url = "https://www.tafensw.edu.au/about-tafensw/enterprise-bargaining/faq";
                intent.launchUrl(MainPage.this, Uri.parse(url));
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Calendar Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
                final String url = "https://outlook.office.com/owa/?realm=student.tafesa.edu.au&exsvurl=1&ll-cc=1033&modurl=1&path=/calendar/view/Month";
                intent.launchUrl(MainPage.this, Uri.parse(url));
            }
        });

        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Videos Clicked", Toast.LENGTH_SHORT).show();
                final CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
                final String url = "https://tafesaedu.sharepoint.com/portals/hub/_layouts/15/PointPublishing.aspx?app=video&p=h";
                intent.launchUrl(MainPage.this, Uri.parse(url));
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
}
