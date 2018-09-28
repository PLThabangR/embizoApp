package embizo.com;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private WebView mywebView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpToolbar();
        setupNavigationDrawerMenu();

    }


    private void setUpToolbar(){

        toolbar = findViewById(R.id.app_bar);
        toolbar.setTitle("Home Screen");
    }


private void setupViewPager(){
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());

       // adapter.addFragment(new FragmentOne());


}

class SectionPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        public SectionPagerAdapter(FragmentManager fm){

            super(fm);

        }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment){
            mFragmentList.add(fragment);
    }
}



    private void setupNavigationDrawerMenu(){

        NavigationView navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle  drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    public void openMap(){
        startActivity(new Intent(HomeActivity.this, MapsActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       String itemName =(String)menuItem.getTitle();

       menuItem.setCheckable(true);
       //menuItem.setChecked(true);



       closeDrawer();

       switch(menuItem.getItemId()){

           case R.id.nav_wifi:
             Toast.makeText(HomeActivity.this, itemName+" clicked", Toast.LENGTH_SHORT).show();

               break;
           case R.id.nav_lte:
               Toast.makeText(HomeActivity.this, itemName+" clicked", Toast.LENGTH_SHORT).show();

               break;
           case R.id.nav_fibre:
               Toast.makeText(HomeActivity.this, itemName+" clicked", Toast.LENGTH_SHORT).show();
               break;
           case R.id.nav_adsl:
               Toast.makeText(HomeActivity.this, itemName+" clicked", Toast.LENGTH_SHORT).show();
               break;


       }



        return false;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(Gravity.START);

    }

    private void showDrawer(){
        drawerLayout.openDrawer(Gravity.START);
    }

    public void onBackPressed(){

    if(drawerLayout.isDrawerOpen(Gravity.START))
        closeDrawer();
    else
        super.onBackPressed();

    }
}
