package com.noyon.musicplayerwithlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridView gView;

    HashMap<String,String> map;
    ArrayList<HashMap<String,String>> list= new ArrayList<>();



    //music arraylist
    ArrayList<HashMap<String,String>>gymList= new ArrayList<>();
    ArrayList<HashMap<String,String>> sadList= new ArrayList<>();
    ArrayList<HashMap<String,String>> rapList=new ArrayList<>();
    ArrayList<HashMap<String,String>> bandList=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gView=findViewById(R.id.gView);

         addMusic();

        addDashbordInfo();

        MyAdapter adapter = new MyAdapter();
        gView.setAdapter(adapter);

    }


    //====================== adapter class=============


    private class MyAdapter extends BaseAdapter{

        LayoutInflater layInflater;


        @Override
        public int getCount() {
            return list.size();
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

            layInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView= layInflater.inflate(R.layout.item_dashbord,viewGroup,false);

            //finding id of itemDashboard
            TextView tvMusicType= myView.findViewById(R.id.tvMusicType);
            CardView itemCard= myView.findViewById(R.id.itemCard);
            ImageView dashImg=myView.findViewById(R.id.dashImg);

            //get value from arrylist

            HashMap<String,String> map= list.get(i);

            //get valuw from hashmap
            String musicType=map.get("musicType");
            String DashCover= map.get("DashCover");


            //display music type
           tvMusicType.setText(musicType);
           //display dashboard cover
            Picasso.get()
                    .load(DashCover)
                    .into(dashImg);


           //set random text color
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            tvMusicType.setTextColor(color);



            //set song type for music activity
             itemCard.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                     if(i==0){
                         //pass the arraylist to music activity arraylist
                         music.mainArrayList=gymList;

                         //assign value to the music activity cover title
                         music.coverTitle=musicType;

                          //get bitmap
                           Bitmap btMap= ((BitmapDrawable) dashImg.getDrawable()) .getBitmap();
                           //then pass the bitmap to music activitty bitMap
                           music.bitmap=btMap;

                         //change activity
                         Intent myIntent = new Intent(MainActivity.this,music.class);
                         startActivity(myIntent);


                     }

                     else if(i==1){
                         //pass the arraylist to music activity arraylist
                         music.mainArrayList=rapList;

                         //assign value to the music activity cover title
                         music.coverTitle=musicType;

                         Intent myIntent = new Intent(MainActivity.this,music.class);
                         startActivity(myIntent);
                     }


                     else if(i==2){
                         //pass the arraylist to music activity arraylist
                         music.mainArrayList=bandList;

                         //assign value to the music activity cover title
                         music.coverTitle=musicType;

                         //get bitmap
                         Bitmap btMap= ((BitmapDrawable) dashImg.getDrawable()) .getBitmap();
                         //assign the bitmap to music activity bitmap
                         music.bitmap=btMap;


                         Intent myIntent= new Intent(MainActivity.this,music.class);
                         startActivity(myIntent);


                     }

                     else if(i==3){

                         //pass the arraylist to music activity arraylist
                         music.mainArrayList=sadList;

                         //assign value to the music activity cover title
                         music.coverTitle=musicType;

                           //get bitmap
                          Bitmap btMap= ((BitmapDrawable) dashImg.getDrawable()) .getBitmap();
                          //assign bit map to music activity bitmap
                         music.bitmap=btMap;

                         Intent myIntent= new Intent(MainActivity.this,music.class);
                         startActivity(myIntent);


                     }



                 }
             });



            return myView;

           }
    }

    //============================end============

    //=============user defined method=======

    public void addDashbordInfo(){

        map=new HashMap<>();
        map.put("musicType","Gym Music");
        map.put("DashCover","https://c.saavncdn.com/711/Gym-Music-English-2018-20180607074451-500x500.jpg");
        list.add(map);


        map=new HashMap<>();
        map.put("musicType","Rap Songs");
        map.put("DashCover","https://c.saavncdn.com/687/Deshi-Hiphop-Vol-1-Pt-6-Unknown-2016-20210306144214-500x500.jpg");
        list.add(map);


        map=new HashMap<>();
        map.put("musicType","Band song");
        map.put("DashCover","https://www.udiscovermusic.com/wp-content/uploads/2015/10/Jesus-Of-Cool.jpg");
        list.add(map);


        map=new HashMap<>();
        map.put("musicType","Sad Songs");
        map.put("DashCover","https://i.pinimg.com/736x/48/32/0e/48320e5c5d030ea53bec8cf3baed1494.jpg");
        list.add(map);









    }

    //==user defined method end =============



    //=================== add music method======


    public void addMusic(){

        //gym music
        map=new HashMap<>();
        map.put("songName","Imagine Dragons - Thunder");
        map.put("songUrl","https://noy007.000webhostapp.com/music/gymsong/Imagine%20Dragons%20-%20Thunder.mp3");
        map.put("songCover","https://cdns-images.dzcdn.net/images/cover/247b228179aea3b083eef43522b78b45/350x350.jpg");
        map.put("duration","3:23");
        map.put("des","The singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        gymList.add(map);

        map=new HashMap<>();
        map.put("songName","Remember The Name ");
        map.put("songUrl","https://noy007.000webhostapp.com/music/gymsong/Remember%20The%20Name%20(Official%20Video)%20-%20Fort%20Minor.mp3");
        map.put("duration","3:48");
        map.put("des","The singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        map.put("songCover","https://a10.gaanacdn.com/gn_img/albums/default/size_l.jpg");
        gymList.add(map);


        map=new HashMap<>();
        map.put("songName"," Sia - Unstoppable ");
        map.put("songUrl","https://noy007.000webhostapp.com/music/gymsong/Sia%20-%20Unstoppable%20(Official%20Video%20-%20Live%20from%20the%20Nostalgic%20For%20The%20Present%20Tour).mp3");
        map.put("duration","3:46");
        map.put("des","The singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        map.put("songCover","https://i1.sndcdn.com/artworks-Hzptfi3lWduUb4nC-nWDFWA-t500x500.jpg");
        gymList.add(map);


        map=new HashMap<>();
        map.put("songName","  Lost Sky - Fearless pt. II ");
        map.put("songUrl","https://noy007.000webhostapp.com/music/gymsong/Lost%20Sky%20-%20Fearless%20pt.%20II%20(feat.%20Chris%20Linton)%20%5bMusic%20Video%20Edit%5d.mp3");
        map.put("duration","3:14");
        map.put("des","The singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        map.put("songCover","https://s.mr-jatt1.com/siteuploads/thumb/c/2743_5.jpg");
        gymList.add(map);


        //rap song
        map= new HashMap<>();
        map.put("songName","Badshah - SANAK ");
        map.put("duration","3:14");
        map.put("songUrl","https://noy007.000webhostapp.com/music/rap/Badshah%20-%20SANAK%20(Official%20Video)%20%203_00%20AM%20Sessions.mp3");
        map.put("des","The singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        map.put("songCover","https://s.mr-jatt1.com/siteuploads/thumb/c/3556_5.jpg");
        rapList.add(map);

        map= new HashMap<>();
        map.put("songName","Blue Eyes ");
        map.put("duration","3:14");
        map.put("songUrl","https://noy007.000webhostapp.com/music/rap/Blue%20Eyes%20Full%20Video%20Song%20Yo%20Yo%20Honey%20Singh%20%20Blockbuster%20Song%20Of%202013.mp3");
        map.put("des","The singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        map.put("songCover","https://c.saavncdn.com/279/Blue-Eyes-2013-500x500.jpg");
        rapList.add(map);


        map= new HashMap<>();
        map.put("songName","Eminem - Rap God");
        map.put("duration","6:09");
        map.put("songUrl","https://noy007.000webhostapp.com/music/rap/Eminem%20-%20Rap%20God%20(Explicit).mp3");
        map.put("des","The singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        map.put("songCover","https://nghype.com/wp-content/uploads/2023/07/image_2023-07-09_233718499.png");
        rapList.add(map);


        //sad song

        map= new HashMap<>();
        map.put("songName","Bekhayali ");
        map.put("songUrl","https://noy007.000webhostapp.com/music/sad%20song/Bekhayali%20Full%20Song%20%20Kabir%20Singh%20%20Shahid%20K,Kiara%20ASandeep%20Reddy%20Vanga%20%20Sachet-Parampara%20%20Irshad.mp3");
        map.put("duration","5:53");
        map.put("songCover","https://c.saavncdn.com/276/Bekhayali-Arijit-Singh-Version-Remix-Hindi-2020-20200513084101-500x500.jpg");
        map.put("des","he singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        sadList.add(map);


        map= new HashMap<>();
        map.put("songName","Full Song_ KHAIRIYAT");
        map.put("songUrl","https://noy007.000webhostapp.com/music/sad%20song/Full%20Song_%20KHAIRIYAT%20(BONUS%20TRACK)%20%20CHHICHHORE%20%20Sushant,%20Shraddha%20%20Pritam,%20Amitabh%20BArijit%20Singh.mp3");
        map.put("duration","3:58");
        map.put("songCover","https://c.saavncdn.com/194/Khairiyat-English-2022-20221006231402-500x500.jpg");
        map.put("des","he singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        sadList.add(map);



        map= new HashMap<>();
        map.put("songName","Tujhe Kitna Chahne Lage ");
        map.put("songUrl","https://noy007.000webhostapp.com/music/sad%20song/LYRICAL_%20Tujhe%20Kitna%20Chahne%20Lage%20%20Kabir%20Singh%20%20Mithoon%20Feat.%20Arijit%20Singh%20%20Shahid%20Kapoor,%20Kiara%20A.mp3");
        map.put("duration","4:52");
        map.put("songCover","https://c.saavncdn.com/763/Tujhe-Kitna-Chahne-Lage-From-Kabir-Singh--Hindi-2019-20190531075012-500x500.jpg");
        map.put("des","he singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        sadList.add(map);


        //band song

        map= new HashMap<>();
        map.put("songName","Dukkho Bilash  দখ বলশ  Artcell  Anushilon  Bangla New Song");
        map.put("songUrl","https://noy007.000webhostapp.com/music/band%20song/Dukkho%20Bilash%20%20%e0%a6%a6%e0%a6%96%20%e0%a6%ac%e0%a6%b2%e0%a6%b6%20%20Artcell%20%20Anushilon%20%20Bangla%20New%20Song%20%20Official%20Lyrical%20Video.mp3");
        map.put("duration","6:41");
        map.put("songCover","https://c.saavncdn.com/051/Artcell-Bengali-2020-20200225122921-500x500.jpg");
        map.put("des","he singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        bandList.add(map);

        map= new HashMap<>();
        map.put("songName"," Mayabee (ময়ব)-Blue Touch");
        map.put("songUrl","https://noy007.000webhostapp.com/music/band%20song/Mayabee%20(%e0%a6%ae%e0%a7%9f%e0%a6%ac)-Blue%20Touch%20%20(Official%20Music%20Video).mp3");
        map.put("duration","4:06");
        map.put("songCover","https://a10.gaanacdn.com/gn_img/albums/z8k3y13rxo/k3yd6RV1Kr/size_l.jpg");
        map.put("des","he singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        bandList.add(map);

        map= new HashMap<>();
        map.put("songName","GhorGari (ঘরগড) - Reuploaded - Train Poka - HIGHWAY");
        map.put("songUrl","https://noy007.000webhostapp.com/music/band%20song/GhorGari%20(%e0%a6%98%e0%a6%b0%e0%a6%97%e0%a6%a1)%20-%20Reuploaded%20-%20Train%20Poka%20-%20HIGHWAY.mp3");
        map.put("duration","6:22");
        map.put("songCover","https://c.saavncdn.com/173/Angel-English-2023-20230404165841-500x500.jpg");
        map.put("des","he singer tells the story of a young person who wants to achieve her dreams, without being influenced by others");
        bandList.add(map);


















    }


    //=====================end========



}