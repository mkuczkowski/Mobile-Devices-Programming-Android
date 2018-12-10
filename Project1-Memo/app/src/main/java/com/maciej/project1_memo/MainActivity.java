package com.maciej.project1_memo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    boolean[] isClicked = {false, false, false, false, false, false};
    ImageView[] availableThumbnails;
    Bitmap[] takenPhotos;
    static int takenImgsCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        availableThumbnails = new ImageView[6];
        takenPhotos = new Bitmap[3];
        availableThumbnails[0] = findViewById(R.id.thmb1);
        availableThumbnails[1] = findViewById(R.id.thmb2);
        availableThumbnails[2] = findViewById(R.id.thmb3);
        availableThumbnails[3] = findViewById(R.id.thmb4);
        availableThumbnails[4] = findViewById(R.id.thmb5);
        availableThumbnails[5] = findViewById(R.id.thmb6);
        shuffleArray(availableThumbnails);
        setupListeners(availableThumbnails);
    }

    public boolean userPickedTwoImgs(boolean[] arr) {
        int clickedImgs = 0;
        for (boolean arrayItem : arr) {
            if(arrayItem)
                clickedImgs++;
        }
        return clickedImgs >= 2;
    }

    public boolean isMatch() {
        for(int i = 0; i < 5; i+=2) {
            if(isClicked[i] && isClicked[i+1]) {
                Toast.makeText(MainActivity.this, "Match!", Toast.LENGTH_SHORT).show();
                fadeOutAndHideImage(availableThumbnails[i]);
                fadeOutAndHideImage(availableThumbnails[i+1]);
                isClicked[i] = false;
                isClicked[i+1] = false;
                return true;
            }
        }
        return false;
    }

    public void fadeOutAndHideImage(final ImageView img) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1500);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                img.setVisibility(View.INVISIBLE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });
        img.startAnimation(fadeOut);
    }

    public void restoreQuestionMarks(final ImageView img) {
        Animation restoreQMark = new AlphaAnimation(1, 0);
        restoreQMark.setInterpolator(new AccelerateInterpolator());
        restoreQMark.setDuration(1000);
        restoreQMark.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                img.setImageResource(R.drawable.questionmark);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });
        img.startAnimation(restoreQMark);
    }

    public void setupListeners(final ImageView[] arr) {
        for (final ImageView imgView : arr) {
            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(takenPhotos[0] == null || takenPhotos[1] == null || takenPhotos[2] == null) {
                        Toast.makeText(MainActivity.this, "Take photos first!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for(int i = 0, j = 0; i <= 5; i++) {
                        if(i % 2 == 0 && i != 0) j++;
                        if (v == availableThumbnails[i]) {
                            imgView.setImageBitmap(takenPhotos[j]);
                            isClicked[i] = true;
                        }
                    }
                    if(!isMatch()) {
                        if(userPickedTwoImgs(isClicked)) {
                            int i = 0;
                            for (final ImageView img : arr) {
                                if(isClicked[i++])
                                    restoreQuestionMarks(img);
                            }
                            Toast.makeText(MainActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                            Arrays.fill(isClicked, false);
                        }
                    }
                }
            });
        }
    }
    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, ++takenImgsCounter);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null : "Received data is null";
            takenPhotos[takenImgsCounter-1] = (Bitmap)(extras.get("data"));
        }
    }
    public void shuffleArray(ImageView[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            ImageView a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
}