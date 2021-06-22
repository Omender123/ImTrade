package com.trade.imtrade;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.trade.imtrade.Fragment.Home_Fragment;
import com.trade.imtrade.Fragment.Near_By_Me;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    NavController navController;
    DrawerLayout drawer;
    NavigationView navigationView;
    AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton ;
    CoordinatorLayout coordinatorLayout;
    ImageView img_discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        floatingActionButton = findViewById(R.id.fab);
        coordinatorLayout =(CoordinatorLayout) findViewById(R.id.coordinator);
        img_discount = (ImageView) findViewById(R.id.img_discount);

        setSupportActionBar(toolbar);
        changeStatusBarColor();
        SetBottomBarNavigationView();




    //   NavigationUI.setupWithNavController(navigationView, navController);



        floatingActionButton.setOnClickListener(this);

    }

    private void SetBottomBarNavigationView() {
        drawer = findViewById(R.id.drawer);

       navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        navController = Navigation.findNavController(this, R.id.main);
        appBarConfiguration = new AppBarConfiguration.Builder(new int[]{R.id.home_Fragment, R.id.Wish_List, R.id.explorer, R.id.profile})
                .setDrawerLayout(drawer)
                .build();
      NavigationUI.setupWithNavController(navigationView, navController);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
       // NavigationUI.setupWithNavController(toolbar,navController,drawer);



      //  toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icon_menu));
        //toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
       NavigationUI.setupWithNavController(bottomNavigationView, navController);

       navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
           @Override
           public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
               if(destination.getId() == R.id.My_address || destination.getId() == R.id.address ) {
                   img_discount.setVisibility(View.GONE);
                   coordinatorLayout.setVisibility(View.GONE);
               } else {
                  img_discount.setVisibility(View.VISIBLE);
                  coordinatorLayout.setVisibility(View.VISIBLE);
               }
           }
       });

    }


    @Override
    public boolean onSupportNavigateUp() {
       // toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icon_menu));
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
          //  toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icon_menu));
        } else {
            super.onBackPressed();
          //  toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icon_menu));
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.fab:
                navController.navigate(R.id.fab);
              ///navController.popBackStack();
                break;
        }
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item,navController)||super.onOptionsItemSelected(item);
    }
}
