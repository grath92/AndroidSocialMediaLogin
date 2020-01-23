package com.gkrath.androidsocialmedialogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.material.snackbar.Snackbar;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.models.Tweet;

import org.json.JSONException;
import org.json.JSONObject;

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

    private ViewGroup parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initVal();
    }

    private void initVal() {

        parent = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

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
        if (graphResponse != null){
            try {
                JSONObject jsonObject = graphResponse.getJSONObject();
                String name = jsonObject.getString("name");
                String email = jsonObject.getString("email");
                String id = jsonObject.getString("id");
                String profileImg = "http://graph.facebook.com/" + id + "/picture?type=large";
            } catch (JSONException e) {
                showSnackBar(parent, e.getMessage(), true);
            }
        }else {
            showSnackBar(parent, error, true);
        }
    }

    @Override
    public void OnGSignInSuccess(GoogleSignInAccount googleSignInAccount) {
        hideProgressBar();
        if (googleSignInAccount != null){
            String email = googleSignInAccount.getEmail();
            String displayName = googleSignInAccount.getDisplayName();
            String firstName = googleSignInAccount.getGivenName();
            String lastName = googleSignInAccount.getFamilyName();
            Uri picUrl = googleSignInAccount.getPhotoUrl();
            String id = googleSignInAccount.getId();
            String token = googleSignInAccount.getIdToken();
        }else {
            showSnackBar(parent, "No Profile Details Found", true);
        }
    }

    @Override
    public void OnGSignInError(String error) {
        hideProgressBar();
        showSnackBar(parent, error, true);
    }

    @Override
    public void OnTwitterSignInComplete(TwitterHelper.UserDetails userDetails, String error) {
        hideProgressBar();
        if (userDetails != null){
            String userName = userDetails.getUserName();
            long userId = userDetails.getUserId();
            String token = userDetails.getToken();
            String secret = userDetails.getSecret();
            String userEmail = userDetails.getUserEmail();
        }else {
            showSnackBar(parent, error, true);
        }
    }

    @Override
    public void OnTweetPostComplete(Result<Tweet> result, String error) {
        hideProgressBar();
        if (result != null){

        }else {
            showSnackBar(parent, error, true);
        }
    }

    @Override
    public void onAPICallStarted() {

    }

    @Override
    public void OnLinkedInSignInSuccess(String response) {
        hideProgressBar();
        if (response != null){
            try {
                JSONObject object = new JSONObject(response);
                String firstName = object.has("firstName") ? object.getString("firstName") : "";
                String lastName = object.has("lastName") ? object.getString("lastName") : "";
                String emailAddress = object.has("emailAddress") ? object.getString("emailAddress") : "";

            } catch (JSONException e) {
                showSnackBar(parent, e.getMessage(), true);
            }
        }else {
            showSnackBar(parent, "No Profile Details Found", true);
        }
    }

    @Override
    public void onLinkedInSignError(String error) {
        hideProgressBar();
        showSnackBar(parent, error, true);
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

    private void showSnackBar(ViewGroup view, String mssg, Boolean isLong){
        Snackbar snackbar = Snackbar.make(view, mssg, isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT );
        View mView = snackbar.getView();
        mView.setBackgroundColor(getResources().getColor(R.color.colorSnackbar));
        TextView mTextView = mView.findViewById(R.id.snackbar_text);
        mTextView.setTextColor(getResources().getColor(R.color.black));
        // Version Check
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        snackbar.show();
    }
}
