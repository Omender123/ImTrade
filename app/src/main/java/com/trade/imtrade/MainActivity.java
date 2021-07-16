package com.trade.imtrade;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;

import java.io.File;


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
    Boolean backhome =false;
    RelativeLayout relative,img_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        floatingActionButton = findViewById(R.id.fab);
        coordinatorLayout =(CoordinatorLayout) findViewById(R.id.coordinator);
        img_discount = (ImageView) findViewById(R.id.img_discount);
        img_cart = (RelativeLayout) findViewById(R.id.relative_cart);
        relative = (RelativeLayout) findViewById(R.id.relative);

        setSupportActionBar(toolbar);
        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        changeStatusBarColor();
        SetBottomBarNavigationView();




    //   NavigationUI.setupWithNavController(navigationView, navController);



        floatingActionButton.setOnClickListener(this);

        moreNavigationOptions();
    }



    private void SetBottomBarNavigationView() {
        drawer = findViewById(R.id.drawer);

       navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        navController = Navigation.findNavController(this, R.id.main);
        appBarConfiguration = new AppBarConfiguration.Builder(new int[]{R.id.home_Fragment, R.id.Wish_List, R.id.explorer, R.id.profile,R.id.nav_Referral,R.id.product_Details})
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
               if(destination.getId() == R.id.My_address || destination.getId() == R.id.address || destination.getId() == R.id.update_profile ) {
                   img_discount.setVisibility(View.GONE);
                   coordinatorLayout.setVisibility(View.GONE);
               }else if (destination.getId()==R.id.Search_nearBy || destination.getId()==R.id.categories || destination.getId()==R.id.product_Fragemet || destination.getId()==R.id.filter_Fragment ){
                   backhome =true;
                   coordinatorLayout.setVisibility(View.GONE);
               }
               else {
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
          }
        /*else {
            if (bottomNavigationView.getSelectedItemId()==R.id.home_Fragment){
                if (backhome ==true ){
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    backhome =false;
                }else{
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.app_name))
                            .setContentText("Are you sure you want to close App?")
                            .setCancelText("Cancel")
                            .setConfirmText("Exit")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent a = new Intent(Intent.ACTION_MAIN);
                                    a.addCategory(Intent.CATEGORY_HOME);
                                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(a);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        finishAffinity();
                                    }
                                    finish();
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            })
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    //  sDialog.cancel();
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();

                }
              }*/
        else {
                super.onBackPressed();
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
                backhome =true;
              ///navController.popBackStack();

                break;
        }
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item,navController)||super.onOptionsItemSelected(item);
    }

    private void moreNavigationOptions() {
        Menu menu = navigationView.getMenu();

        MenuItem logout = menu.findItem(R.id.nav_logout);

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                LogoutAlertBox();
                return true;
            }
        });

      /*  MenuItem share = menu.findItem(R.id.nav_Share_us);

        share.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                AppUtils.shareApp(MainActivity.this);
                return true;
            }
        });*/
    }

    private void LogoutAlertBox() {
        //Logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        // set title
        alertDialogBuilder.setTitle(getResources().getString(R.string.app_name));

        // set dialog message
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher_round);
        alertDialogBuilder
                .setMessage("Are you sure to Logout !!!!!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                        clearApplicationData();
                        Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();


        // show it
        alertDialog.show();


    }
    @SuppressLint("LongLogTag")
    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    Log.i("EEEEEERRRRRRROOOOOOORRRR", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

}
