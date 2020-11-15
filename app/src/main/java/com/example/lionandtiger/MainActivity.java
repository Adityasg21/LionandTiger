package com.example.lionandtiger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    enum Player{
        ONE,TWO
    }

    Player CurrentPlayer=Player.ONE;

    Player[] playerChoices= new Player[9];
    int[][] winner ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
public void ImageViewisTapped(View imageView){

    ImageView ImageViewTapped=(ImageView)imageView;
    ImageViewTapped.setTranslationX(-2000);

    int tiTag=Integer.parseInt(ImageViewTapped.getTag().toString());

    playerChoices[tiTag]=CurrentPlayer;

    if(CurrentPlayer==Player.ONE){
        ImageViewTapped.setImageResource(R.drawable.tiger);
        CurrentPlayer=Player.TWO;
    }
    else {
        ImageViewTapped.setImageResource(R.drawable.lion);
        CurrentPlayer=Player.ONE;
    }
    Toast.makeText(this,ImageViewTapped.getTag().toString(),Toast.LENGTH_LONG).show();
    ImageViewTapped.animate().translationXBy(2000).alpha(1).rotation(36000).setDuration(1000);
    }


}
