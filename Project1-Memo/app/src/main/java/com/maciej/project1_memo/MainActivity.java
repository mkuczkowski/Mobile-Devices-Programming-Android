package com.maciej.project1_memo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int numberOfAssignedPhotos = 0;
    private GameBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameBoard = new GameBoard(this);
    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, numberOfAssignedPhotos);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap capturedImage = (Bitmap) (extras.get("data"));
                assignCapturedPictures(capturedImage);
                checkIfGameCanBeStarted();
            }
        }
    }

    private void assignCapturedPictures(Bitmap capturedImage) {
        gameBoard.setCameraPictures(capturedImage, numberOfAssignedPhotos);
        gameBoard.setCameraPictures(capturedImage, numberOfAssignedPhotos+1);
        numberOfAssignedPhotos = numberOfAssignedPhotos + 2;
    }

    private void checkIfGameCanBeStarted() {
        if(allImagesAreSet()) {
            gameBoard.shuffleThumbnails();
            gameBoard.assignPicturesToThumbnails();
            numberOfAssignedPhotos = 0;
        }
    }

    private boolean allImagesAreSet() {
        for (Bitmap takenPhoto : gameBoard.getCameraPictures()) {
            if (takenPhoto == null)
                return false;
        }
        return true;
    }

    public void restartGame(View view) {
        gameBoard.restoreAllQuestionMarks();
        gameBoard.shuffleThumbnails();
        gameBoard.assignPicturesToThumbnails();
        Toast.makeText(MainActivity.this, "Thumbnails shuffled - game restarted", Toast.LENGTH_SHORT).show();
    }
}