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
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
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
import com.trade.imtrade.Lunch_screen.Splash_Screen;
import com.trade.imtrade.MainActivity;
import com.trade.imtrade.Model.ResponseModel.LoginResponse;
import com.trade.imtrade.Model.ResponseModel.SocialLoginResponse;
import com.trade.imtrade.Model.request.LoginBody;
import com.trade.imtrade.Model.request.SocialLoginBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
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
    AccessTokenTracker accessTokenTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        // Passing Login in Facebook SDK.
        FacebookSdk.sdkInitialize(Login.this);
        view = binding.getRoot();
        context = Login.this;
        presenter = new LoginPresenter(this);
        dialog = AppUtils.hideShowProgress(context);

        binding.cardLogin.setOnClickListener(this);
        binding.textSignUp1.setOnClickListener(this);
        binding.forgot.setOnClickListener(this);
        binding.Skip.setOnClickListener(this);

       // AppUtils.StatusbarColor(this);

        initview();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.card_Login:

                doLogin();
                break;

            case R.id.text_sign_up1:
                startActivity(new Intent(getApplicationContext(), Referral_Code_Srceen.class));
                break;
            case R.id.forgot:
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
                break;

            case R.id.Skip:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                MyPreferences.getInstance(getApplicationContext()).putBoolean(PrefConf.LOGINCHECK,false);
                break;
        }
    }

    private void doLogin() {
        String Email = binding.email.getText().toString();
        String password = binding.password.getText().toString();
        if (Email.isEmpty()) {
            binding.email.requestFocus();
            Snacky.builder()
                    .setActivity(Login.this)
                    .setText("Please enter Email !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();

        } else if (password.isEmpty()) {
            binding.password.requestFocus();
            Snacky.builder()
                    .setActivity(Login.this)
                    .setText("Please enter Password !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        } else {
           // AppUtils.FullScreen(this);

            LoginBody user = new LoginBody(Email, password);
            presenter.loginUser(user);
        }

    }

    @Override
    public void showHideLoginProgress(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
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
        if (message.equalsIgnoreCase("ok")) {
            User_Data user_data = new User_Data(response.getResult().getId(),
                    response.getResult().getEmail(),
                    response.getToken(),
                    response.getResult().getMyReferalcode(),null,null);
            SharedPrefManager.getInstance(this).SetLoginData(user_data);
            MyPreferences.getInstance(getApplicationContext()).putBoolean(PrefConf.LOGINCHECK,true);
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();

            Toast.makeText(context, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onSocialLoginSuccess(SocialLoginResponse response, String message) {
        if (message.equalsIgnoreCase("ok")) {
            User_Data user_data = new User_Data(response.getResult().getId(),
                    response.getResult().getEmail(),
                    response.getToken(),
                    response.getResult().getMyReferalcode(),null,null);
            SharedPrefManager.getInstance(this).SetLoginData(user_data);
            MyPreferences.getInstance(getApplicationContext()).putBoolean(PrefConf.LOGINCHECK,true);
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();

            Toast.makeText(context, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
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
        //AppUtils.FullScreen(this);
        AppEventsLogger.activateApp(Login.this);
    }

    private void initview() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();

        binding.loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

// Checking the Access Token.
        if (AccessToken.getCurrentAccessToken() != null) {

            getUserProfile(AccessToken.getCurrentAccessToken());

            // If already login in then show the Toast.
          /*  Sneaker.with(Login.this)
                    .setTitle("Already logged in")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();
*/
        } else {

            // If not login in then show the Toast.
           /* Sneaker.with(Login.this)
                    .setTitle("User not logged in")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();*/


        }

        binding.loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //  mAccessToken = loginResult.getAccessToken();
                getUserProfile(loginResult.getAccessToken());

                //  Toast.makeText(Login.this, "mAccessToken" +loginResult.getAccessToken(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {

                //   Toast.makeText(Login.this, "Cancelled", Toast.LENGTH_SHORT).show();
                Sneaker.with(Login.this)
                        .setTitle("Cancelled")
                        .setMessage("")
                        .setCornerRadius(4)
                        .setDuration(1500)
                        .sneakError();

            }


            @Override
            public void onError(FacebookException error) {
                Log.d("coder", "" + error);
                Sneaker.with(Login.this)
                        .setTitle(error.getMessage())
                        .setMessage("")
                        .setCornerRadius(4)
                        .setDuration(1500)
                        .sneakError();

                //   Toast.makeText(Login.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        });


        // Detect user is login or not. If logout then clear the TextView and delete all the user info from TextView.
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
                if (accessToken2 == null) {

                    // Clear the TextView after logout.
                    Sneaker.with(Login.this)
                            .setTitle("all Data are Removed")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakSuccess();


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
            String personName = "", personGivenName = "", personFamilyName = "", personEmail = "", personId = "", personMobile = "";
            Uri personPhoto = null;
            if (acct != null) {
                personName = acct.getDisplayName();
                personGivenName = acct.getGivenName();
                personFamilyName = acct.getFamilyName();
                personEmail = acct.getEmail();
                personId = acct.getId();
                personPhoto = acct.getPhotoUrl();


                SocialLoginBody socialLoginBody = new SocialLoginBody(personName, personEmail, true, false, personId);
                presenter.SocialLogin(socialLoginBody);
            }

            //  Intent in = new Intent(LoginAcitivity.this, MainActivity.class);

            //startActivity(in);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            Log.d("coder", "" + e.getLocalizedMessage());

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
                        try {

                            if (response.getJSONObject().getString("email").isEmpty()) {

                            } else {
                                SocialLoginBody socialLoginBody = new SocialLoginBody(response.getJSONObject().getString("name"),
                                        response.getJSONObject().getString("email")
                                        , false, true, response.getJSONObject().getString("id"));
                                presenter.SocialLogin(socialLoginBody);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Your FB account is registered on phone number...Please try other available options to Register!", Toast.LENGTH_SHORT).show();
                            //  Snackbar.make(view, "Your FB account is registered on phone number...Please try other available options to Register!", Snackbar.LENGTH_LONG).show();

                        }

                    }
                });

        Bundle parameters = new Bundle();

        parameters.putString("fields", "id,name,email,last_name,first_name");
        request.setParameters(parameters);
        request.executeAsync();

    }

    void snack(String msg) {

        Snackbar.make(view, "" + msg, Snackbar.LENGTH_SHORT)
                .show();

    }


    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(Login.this);


    }
}