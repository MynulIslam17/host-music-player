package com.noyon.musicplayerwithlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Screen extends AppCompatActivity {

    TextView appName;
    Animation anim;
    LottieAnimationView lotti;
    LinearLayout retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       //find id
           appName=findViewById(R.id.appName);
           lotti=findViewById(R.id.lotti);
           retry =findViewById(R.id.retry);



           anim= AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.alpha);

           appName.startAnimation(anim);



           //add  delayed

           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {

                   //if net available then lotti animation will show
                   // and after repeted 1 time loti listner will be call
                   if(isAvailable()){
                       lotti.setVisibility(View.VISIBLE);
                       lotti.setRepeatCount(0);

                   }


                 //if net not available then show the no net pop up
                   else{

                       retry.setVisibility(View.VISIBLE);


                   }







               }
           },2000);





           //retry lay click event

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAvailable()){
                    retry.setVisibility(View.GONE);
                    lotti.setVisibility(View.VISIBLE);
                    lotti.setRepeatCount(0);

                }
                else {
                    Toast.makeText(Splash_Screen.this,"NO Internet",Toast.LENGTH_SHORT).show();

                }

            }
        });






 // lotti listener
        lotti.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {

                Intent myIntent=new Intent(Splash_Screen.this,MainActivity.class);
                startActivity(myIntent);
                finish();


            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {


            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        });







    }


    //================== user defined method============

     public boolean isAvailable(){

         ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo netInfo= manager.getActiveNetworkInfo();

          return  (netInfo!=null && netInfo.isConnected());

     }



    //=================end================================





}