package com.trade.imtrade.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Model.ResponseModel.LoginResponse;
import com.trade.imtrade.Model.request.LoginBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.ActivityLoginBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.utils.Validation;
import com.trade.imtrade.view_presenter.LoginPresenter;
import com.trade.imtrade.view_presenter.LoginPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import de.mateware.snacky.Snacky;


public class Login extends AppCompatActivity implements View.OnClickListener, LoginPresenter.LoginView {
ActivityLoginBinding binding;
    private Context context;
    private Dialog dialog;
    private LoginPresenter presenter;
    private View view;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;
    private CallbackManager callbackManager;
    private AccessToken mAccessToken;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);

        view = binding.getRoot();
        context = Login.this;
        presenter = new LoginPresenter(this);
        dialog = AppUtils.hideShowProgress(context);

        binding.cardLogin.setOnClickListener(this);
        binding.textSignUp1.setOnClickListener(this);

        AppUtils.FullScreen(this);

        initview();

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.card_Login:

                doLogin();
                break;

            case R.id.text_sign_up1:
                startActivity(new Intent(getApplicationContext(),Referral_Code_Srceen.class));
                break;
        }
    }

    private void doLogin() {
        String Email= binding.email.getText().toString();
        String password = binding.password.getText().toString();
        if(Email.isEmpty()){
            binding.email.requestFocus();
            Snacky.builder()
                    .setActivity(Login.this)
                    .setText("Please enter Email !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();

        }else if(password.isEmpty()){
            binding.password.requestFocus();
            Snacky.builder()
                    .setActivity(Login.this)
                    .setText("Please enter Password !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }
        else {
            AppUtils.FullScreen(this);

            LoginBody user = new LoginBody(Email,password);
            presenter.loginUser(user);
        }

    }

    @Override
    public void showHideLoginProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else{
            dialog.dismiss();
        }
    }

    @Override
    public void onLoginError(String message) {
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onLoginSuccess(LoginResponse response, String message) {
        if (message.equalsIgnoreCase("ok")){
            User_Data user_data = new User_Data(response.getResult().getId(),
                    response.getResult().getFirstName(),
                    response.getResult().getLastName(),
                    response.getResult().getEmail(),
                    response.getResult().getPhone(),
                    response.getResult().getCountryCode(),
                    response.getToken(),
                    response.getResult().getMyReferalcode());
           // SharedPrefManager.getInstance(this).SetLoginData(user_data);


            Sneaker.with(this)
                    .setTitle(response.getMessage())
                    .setMessage(response.getResult().getEmail())
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();
        }


    }

    @Override
    public void onLoginFailure(Throwable t) {
        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtils.FullScreen(this);
    }

    private void initview() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();

        binding.loginButton.setReadPermissions(Arrays.asList("email","user_birthday"));
        callbackManager = CallbackManager.Factory.create();



        binding.loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mAccessToken = loginResult.getAccessToken();
              //  getUserProfile(mAccessToken);

                // Toast.makeText(Login_signup.this, "mAccessToken" +mAccessToken, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {

                Toast.makeText(Login.this, "Cancelled", Toast.LENGTH_SHORT).show();


            }


            @Override
            public void onError(FacebookException error) {
                Log.d("coder",""+error);

                Toast.makeText(Login.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        });

        AccessTokenTracker tracker  = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                if (currentAccessToken==null){
                    Toast.makeText(context, "logout", Toast.LENGTH_SHORT).show();
                }else{
                    getUserProfile(currentAccessToken);
                }
            }
        };

    }

    public void go_to_google(View view) {
        signIn();
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            String personName = "", personGivenName = "", personFamilyName = "", personEmail = "", personId = "",personMobile="";
            Uri personPhoto = null;
            if (acct != null) {
                personName = acct.getDisplayName();
                personGivenName = acct.getGivenName();
                personFamilyName = acct.getFamilyName();
                personEmail = acct.getEmail();
                personId = acct.getId();
                personPhoto = acct.getPhotoUrl();


                Toast.makeText(this, personEmail+ "", Toast.LENGTH_SHORT).show();
            }

          //  Intent in = new Intent(LoginAcitivity.this, MainActivity.class);

            //startActivity(in);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            Log.d("coder",""+e.getLocalizedMessage());

            // updateUI(null);
        }
    }

    public void go_to_fb(View view) {
        binding.loginButton.performClick();
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("resp", object.toString());
                      /*  try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");

                            String id = object.getString("id");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";


                            String email = "";
                            try {
                                if (object.getString("email").equals("")) {
                                    Log.d("exception", "onCompleted: ");
                                } else {
                                    email = object.getString("email");
                                    Toast.makeText(context, ""+email, Toast.LENGTH_SHORT).show();
                                  //  social_login("" + id, "" + email, "" + first_name + " " + last_name, "" + image_url);

                                }

                            }catch (Exception e){
                                Snackbar.make(view, "Your FB account is registered on phone number...Please try other available options to Register!", BaseTransientBottomBar.LENGTH_LONG).show();

                            }






                        } catch (JSONException e) {
                            e.printStackTrace();
                            LoginManager.getInstance().logOut();
                            //  Toast.makeText(Login_signup.this, "exception" +e, Toast.LENGTH_SHORT).show();
                            snack("Your FB account doesn't  belong to any email");

                            Log.d("exception", "onCompleted: " + e);
                        }*/

                        if (object!=null){
                            try {
                                String email = object.getString("email");
                                String id = object.getString("id");

                                Toast.makeText(context, ""+email, Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                LoginManager.getInstance().logOut();
                                //  Toast.makeText(Login_signup.this, "exception" +e, Toast.LENGTH_SHORT).show();
                                snack("Your FB account doesn't  belong to any email");

                            }
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    void snack(String msg) {

        Snackbar.make(view, "" + msg, Snackbar.LENGTH_SHORT)
                .show();

    }
}
