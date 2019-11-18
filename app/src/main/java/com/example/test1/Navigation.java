package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Navigation extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private int SIGN_IN_REQUEST_CODE = 2;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String name;
    private TextView textView,textView1;
    private ImageView imageView;
    private RatingBar ratingBar;
    private Button button;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        int Img[] = {R.drawable.first,R.drawable.second,R.drawable.third,R.drawable.fourth,R.drawable.six,R.drawable.seven,R.drawable.eight};
        viewFlipper = (ViewFlipper)findViewById(R.id.flipper);
        viewFlipper.setAutoStart(true);

        for(int image1: Img){
            image(image1);
        }

        ratingBar = (RatingBar)findViewById(R.id.rating_star);
        ratingBar.setRating(1.5f);

        button = (Button)findViewById(R.id.share_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Your star rate is "+1.5;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Rating");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        getSupportActionBar().setTitle("Home                                  TP"+5);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
            data();
        } else {
            data();
        }

        dl = (DrawerLayout)findViewById(R.id.activity_navigation);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.rate:
                        dl.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(),Rate.class));
                        break;
                    case R.id.suggestion:
                        dl.closeDrawer(GravityCompat.START);
                        String subject = "Suggestion";
                        String[] mail = {"ce18bo24@iittp.ac.in"};
                        String message = "Write here";

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setData(Uri.parse("mailto"));
                        intent.putExtra(Intent.EXTRA_EMAIL,mail);
                        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                        intent.putExtra(Intent.EXTRA_TEXT,message);

                        intent.setType("message/rfc822");
                        startActivity(intent);
                        break;
                    case R.id.point:
                        dl.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(),Points.class));break;
                    case R.id.logout:
                        AuthUI.getInstance().signOut(Navigation.this)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Navigation.this,
                                                    "You have been signed out.",
                                                    Toast.LENGTH_LONG)
                                                    .show();

                                            // Close activity
                                            finish();
                                        }
                                    });

                        break;
                    case R.id.chatapp:
                        dl.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(),Chatapp.class));
                        break;
                    case R.id.news:
                        dl.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(),News.class));
                        break;
                    default:
                        return true;
                }
                dl.closeDrawer(GravityCompat.START);
                return true;

            }
        });
        data();
    }

    public void image(int n){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(n);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    public void data(){
        NavigationView mView = ( NavigationView ) findViewById( R.id.nv );
        View hView =  mView.getHeaderView(0);
        textView1 = (TextView)hView.findViewById(R.id.text_View1);
        imageView = (ImageView)hView.findViewById(R.id.imageView1);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            if(user.getDisplayName() != null){
                textView1.setText(user.getDisplayName());}
            if(user.getPhotoUrl() != null){
                Glide.with(this).load(user.getPhotoUrl().toString()).into(imageView);

            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
           data();

            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();
                // Close the app
                finish();
            }
        }
    }

}
