package com.gkrath.androidsocialmedialogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gkrath.androidsocialmedialogin.helper.FacebookHelper;
import com.gkrath.androidsocialmedialogin.helper.GoogleSignInHelper;
import com.gkrath.androidsocialmedialogin.helper.LinkedInSignInHelper;
import com.gkrath.androidsocialmedialogin.helper.TwitterHelper;

import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    //--------------------------------Facebook LogIn--------------------------------------//
    private FacebookHelper fbConnectHelper;
    private Button fbSignInButton;
    private Button fbShareButton;
    //----------------------------------Google+ SignIn-----------------------------------//
    //Google plus sign-in button
    private GoogleSignInHelper googleSignInHelper;
    private Button gSignInButton;
    //-----------------------------------Twitter SignIn -----------------------------------//
    private TwitterHelper twitterHelper;
    private Button tSignInButton;
    //-----------------------------------LinkedIn SignIn-----------------------------------//
    private LinkedInSignInHelper linkedInSignInHelper;
    private Button lSignInButton;

    private TextView userName;
    private TextView email;
    private ProgressBar progressBar;
    private boolean isFbLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.fb_sign_in_button)
    public void onFacebookSignInButtonClick(){

    }

    @OnClick(R.id.g_sign_in_button)
    public void onGooglePlusSignInButtonClick(){

    }

    @OnClick(R.id.twitter_sign_in_button)
    public void onTwitterSignInButtonClick(){

    }

    @OnClick(R.id.linked_in_sign_in_button)
    public void onLinkedinSignInButtonClick(){

    }

    @OnClick(R.id.instagram_sign_in_button)
    public void onInstagramSignInButtonClick(){

    }

    @OnClick(R.id.yahoo_sign_in_button)
    public void onYahooSignInButtonClick(){

    }

    @OnClick(R.id.github_sign_in_button)
    public void onGitHubSignInButtonClick(){

    }

}
