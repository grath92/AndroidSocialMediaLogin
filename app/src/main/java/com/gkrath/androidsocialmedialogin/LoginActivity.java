package com.gkrath.androidsocialmedialogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.gkrath.androidsocialmedialogin.helper.FacebookHelper;
import com.gkrath.androidsocialmedialogin.helper.GoogleSignInHelper;
import com.gkrath.androidsocialmedialogin.helper.LinkedInSignInHelper;
import com.gkrath.androidsocialmedialogin.helper.TwitterHelper;
import com.gkrath.androidsocialmedialogin.utils.KeyHashGenerator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.models.Tweet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements FacebookHelper.OnFbSignInListener,
        GoogleSignInHelper.OnGoogleSignInListener, TwitterHelper.OnTwitterSignInListener, LinkedInSignInHelper.OnLinkedInSignInListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final String URL = "https://github.com/grath92/AndroidSocialMediaLogin";

    private FacebookHelper fbConnectHelper;
    private GoogleSignInHelper googleSignInHelper;
    private TwitterHelper twitterHelper;
    private LinkedInSignInHelper linkedInSignInHelper;

    private boolean isFbSignInReq = false;
    private boolean isGpSignInReq = false;
    private boolean isTwitterSignInReq = false;
    private boolean isLinkedInSignInReq = false;
    private boolean isInstagramSignInReq = false;
    private boolean isYahooSignInReq = false;
    private boolean isGithubSignInReq = false;

    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initVal();
    }

    private void initVal() {
        KeyHashGenerator.generateKey(LoginActivity.this);

        FacebookSdk.sdkInitialize(getApplicationContext());
        fbConnectHelper = new FacebookHelper(this, this);
        googleSignInHelper = new GoogleSignInHelper(this, this);
        twitterHelper = new TwitterHelper(this, this);
        linkedInSignInHelper = new LinkedInSignInHelper(this, this);
    }

    @OnClick(R.id.fb_sign_in_button)
    public void onFacebookSignInButtonClick(){
        showProgressBar();
        isFbSignInReq = true;
        fbConnectHelper.connect();
    }

    @OnClick(R.id.g_sign_in_button)
    public void onGooglePlusSignInButtonClick(){
        showProgressBar();
        isGpSignInReq = true;
        googleSignInHelper.connect();
        googleSignInHelper.signIn();
    }

    @OnClick(R.id.twitter_sign_in_button)
    public void onTwitterSignInButtonClick(){
        showProgressBar();
        isTwitterSignInReq = true;
        twitterHelper.connect();
    }

    @OnClick(R.id.linked_in_sign_in_button)
    public void onLinkedinSignInButtonClick(){
        showProgressBar();
        isLinkedInSignInReq = true;
        linkedInSignInHelper.connect();
        linkedInSignInHelper.signIn();
    }

    @OnClick(R.id.instagram_sign_in_button)
    public void onInstagramSignInButtonClick(){
        showProgressBar();
        isInstagramSignInReq = true;
    }

    @OnClick(R.id.yahoo_sign_in_button)
    public void onYahooSignInButtonClick(){
        showProgressBar();
        isYahooSignInReq = true;
    }

    @OnClick(R.id.github_sign_in_button)
    public void onGitHubSignInButtonClick(){
        showProgressBar();
        isGithubSignInReq = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (isFbSignInReq){
            isFbSignInReq = false;
            fbConnectHelper.onActivityResult(requestCode, resultCode, data);
        }else if (isGpSignInReq){
            isGpSignInReq = false;
            googleSignInHelper.onActivityResult(requestCode, resultCode, data);
        }else if (isTwitterSignInReq){
            isTwitterSignInReq = false;
            twitterHelper.onActivityResult(requestCode, resultCode, data);
        }else if (isLinkedInSignInReq){
            isLinkedInSignInReq = false;
            linkedInSignInHelper.onActivityResult(requestCode, resultCode, data);
        }else if (isInstagramSignInReq){
            isInstagramSignInReq = false;

        }else if (isYahooSignInReq){
            isYahooSignInReq = false;

        }else if (isGithubSignInReq){
            isGithubSignInReq = false;
        }
    }

    @Override
    public void OnFbSignInComplete(GraphResponse graphResponse, String error) {
        hideProgressBar();
    }

    @Override
    public void OnGSignInSuccess(GoogleSignInAccount googleSignInAccount) {
        hideProgressBar();
    }

    @Override
    public void OnGSignInError(String error) {
        hideProgressBar();
    }

    @Override
    public void OnTwitterSignInComplete(TwitterHelper.UserDetails userDetails, String error) {
        hideProgressBar();
    }

    @Override
    public void OnTweetPostComplete(Result<Tweet> result, String error) {
        hideProgressBar();
    }

    @Override
    public void onAPICallStarted() {
        hideProgressBar();
    }

    @Override
    public void OnLinkedInSignInSuccess(String response) {
        hideProgressBar();
    }

    @Override
    public void onLinkedInSignError(String error) {
        hideProgressBar();
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    public void gotoHomeScreen(){
        Intent homeScreenIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeScreenIntent);
        finish();
    }
}
