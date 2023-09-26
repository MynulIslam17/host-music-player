package com.noyon.musicplayerwithlistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.MissingFormatArgumentException;

public class music extends AppCompatActivity {

    ListView lView;
    TextView tvCoverTitle;
    ImageView listCover;

    Animation anim;


    public static String coverTitle;

    public static Bitmap bitmap;

    public static ArrayList<HashMap<String,String>> mainArrayList= new ArrayList<>();
    HashMap<String,String> map;

    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        //finding id
        lView=findViewById(R.id.lView);
        tvCoverTitle=findViewById(R.id.tvCoverTitle);
         tvCoverTitle.setText(coverTitle);
         listCover=findViewById(R.id.listCover);


         //assign an animaton
         anim = AnimationUtils.loadAnimation(music.this,R.anim.rotate);


         //set BitMap
        if(bitmap!=null){
            listCover.setImageBitmap(bitmap);
        }



        MyAdapter adapter = new MyAdapter();
        lView.setAdapter(adapter);




    }


    //============================create adapter class======================

    public class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return mainArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            //layout inflate

            LayoutInflater layInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView= layInflater.inflate(R.layout.item_song,viewGroup,false);

            //finding id of item_song
            TextView tvSongName= myView.findViewById(R.id.tvSongName);
            TextView tvSongDes=myView.findViewById(R.id.tvSongDes);
            TextView tvDuration= myView.findViewById(R.id.tvDuration);
            ImageView itemImg= myView.findViewById(R.id.itemImg);
            ImageView imgPlay = myView.findViewById(R.id.imgPlay);
            LinearLayout layItem =myView.findViewById(R.id.layItem);

            //get value from arralylist

             HashMap<String,String> map=mainArrayList.get(i);

             //get value from hashmap
             String musicName= map.get("songName");
             String songCover=map.get("songCover");
             String songUrl= map.get("songUrl");
             String duration= map.get("duration");
             String description= map.get("des");


             //display all value
             tvSongName.setText(musicName);
             tvDuration.setText(duration);
             tvSongDes.setText(description);

              //display song img
            Picasso.get().
                    load(songCover)
                    .into(itemImg);


           //set click event on song
             layItem.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                     //change play pause img

                     //when first time click
                     if(imgPlay.getTag()!=null && imgPlay.getTag().toString().contains("notPlaying")){

              // handle prvious song layout
                         for(int x=0 ; x<lView.getChildCount(); x++){
                             View newView= lView.getChildAt(x);
                             //now find id through newview
                             ImageView newItemImg= newView.findViewById(R.id.itemImg);
                             ImageView newImgPlay= newView.findViewById(R.id.imgPlay);

                             //now apply changes
                             newItemImg.clearAnimation();
                             newImgPlay.setImageResource(R.drawable.play);
                             newImgPlay.setTag("notPlaying");

                         }








                         //set rotate animation on itemimg
                           itemImg.startAnimation(anim);


                         imgPlay.setImageResource(R.drawable.pause);
                         imgPlay.setTag("playing");


                         //music play part
                         if(mp!=null){
                             mp.release();

                         }

                         mp=new MediaPlayer();

                         try {
                             mp.setDataSource(songUrl);
                             mp.prepare();
                             mp.start();
                         } catch (IOException e) {
                             throw new RuntimeException(e);
                         }

                         //when the song end
                         mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                             @Override
                             public void onCompletion(MediaPlayer mediaPlayer) {

                                 //stop animatoon
                                 itemImg.clearAnimation();

                                 //chamge in tag and  img
                                 imgPlay.setImageResource(R.drawable.play);
                                 imgPlay.setTag("notPlaying");



                             }
                         });




                     }
                     //for the 2nd time click in the same song
                     else {
                   //stop rotate anim
                         itemImg.clearAnimation();

                         imgPlay.setImageResource(R.drawable.play);
                         imgPlay.setTag("notPlaying");

                         if(mp!=null){
                             mp.release();
                         }

                     }




                 }


             });






            return myView;
        }
    }

    //===================================================== back press methld ============

    @Override
    public void onBackPressed() {
     //  super.onBackPressed();

       Dialog box=  new Dialog(music.this);
         box.setContentView(R.layout.cstm_dialog);
         box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         box.show();

         //find ids

        CardView btnYes= box.findViewById(R.id.btnYes);
        CardView btnNo= box.findViewById(R.id.btnNo);

        //click event
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                box.dismiss();
                if(mp!=null){
                    mp.release();
                }
                finish();

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                box.dismiss();
            }
        });



    }


    //=================back press method end=================================






}