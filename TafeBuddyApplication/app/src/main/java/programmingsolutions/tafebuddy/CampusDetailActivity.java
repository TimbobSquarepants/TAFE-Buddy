package programmingsolutions.tafebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import campus_map_classes.CampusDetailFragment;

/**
 * An activity representing a single Campus detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link CampusListActivity}.
 */
public class CampusDetailActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // s avedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the FragmentsAPI guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            android.app.FragmentManager fm = getFragmentManager();

            Bundle arguments = new Bundle();
            arguments.putString(CampusDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(CampusDetailFragment.ARG_ITEM_ID));
            CampusDetailFragment fragment = new CampusDetailFragment();
            fragment.setArguments(arguments);
            fm.beginTransaction()
                    .replace(R.id.campus_detail_container, fragment)
                    .commit();


        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, CampusListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onStart(){
        super.onStart();


    }

    public void onDestroy(){
        super.onDestroy();
    }
    public void onPause(){
        super.onPause();

    }
    public void onResume(){
        super.onResume();

    }
    public void onStop(){
        super.onStop();
    }
}
