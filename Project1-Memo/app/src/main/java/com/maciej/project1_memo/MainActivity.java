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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_FIRST_IMAGE_CAPTURE = 1;
    static final int REQUEST_SECOND_IMAGE_CAPTURE = 2;
    boolean[] isClicked = {false, false, false, false};
    ImageView[] availableThumbnails;
    Bitmap[] takenPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        availableThumbnails = new ImageView[4];
        takenPhotos = new Bitmap[2];
        availableThumbnails[0] = findViewById(R.id.thmb1);
        availableThumbnails[1] = findViewById(R.id.thmb2);
        availableThumbnails[2] = findViewById(R.id.thmb3);
        availableThumbnails[3] = findViewById(R.id.thmb4);
        shuffleArray(availableThumbnails);
        setupListeners(availableThumbnails);
    }

    public boolean UserPickedTwoImgs(boolean[] arr) {
        int loopHelper = 0;
        for (boolean arrayItem : arr) {
            if(arrayItem)
                loopHelper++;
        }
        if(loopHelper >= 2)
            return true;
        return false;
    }

    public void checkForMatch() {
        if(isClicked[0] && isClicked[1]) {
            Toast.makeText(MainActivity.this, "Match!", Toast.LENGTH_LONG).show();
            availableThumbnails[0].setVisibility(View.INVISIBLE);
            availableThumbnails[1].setVisibility(View.INVISIBLE);
        }
        if(isClicked[2] && isClicked[3]) {
            Toast.makeText(MainActivity.this, "Match!", Toast.LENGTH_LONG).show();
            availableThumbnails[2].setVisibility(View.INVISIBLE);
            availableThumbnails[3].setVisibility(View.INVISIBLE);
        }
    }

    public void setupListeners(final ImageView[] arr) {
        for (final ImageView imgView : arr) {
            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == availableThumbnails[0]) {
                        imgView.setImageBitmap(takenPhotos[0]);
                        isClicked[0] = true;
                    } else if (v == availableThumbnails[1]) {
                        imgView.setImageBitmap(takenPhotos[0]);
                        isClicked[1] = true;
                    } else if (v == availableThumbnails[2]) {
                        imgView.setImageBitmap(takenPhotos[1]);
                        isClicked[2] = true;
                    } else if (v == availableThumbnails[3]) {
                        imgView.setImageBitmap(takenPhotos[1]);
                        isClicked[3] = true;
                    }
                    checkForMatch();
                    if(UserPickedTwoImgs(isClicked)) {
                        for (final ImageView img : arr) {
                            img.setImageResource(R.drawable.questionmark);
                        }
                        Toast.makeText(MainActivity.this, "Try again!", Toast.LENGTH_LONG).show();
                        isClicked[0] = false;
                        isClicked[1] = false;
                        isClicked[2] = false;
                        isClicked[3] = false;
                    }
                }
            });
        }
    }
    public void dispatchTakeFirstPictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_FIRST_IMAGE_CAPTURE);
        }
    }
    public void dispatchTakeSecondPictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_SECOND_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_FIRST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            takenPhotos[0] = (Bitmap)(extras.get("data"));
        } else if(requestCode == REQUEST_SECOND_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            takenPhotos[1] = (Bitmap)(extras.get("data"));
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