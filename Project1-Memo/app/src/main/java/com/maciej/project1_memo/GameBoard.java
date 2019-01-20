package com.maciej.project1_memo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import android.view.View;
import android.widget.ImageView;

import java.util.Objects;
import java.util.Random;

public class GameBoard implements View.OnClickListener {
    private Context context;
    private MemoThumbnail[] thumbnails;
    private Bitmap[] cameraPictures;

    public Context getContext() { return context; }

    public Bitmap[] getCameraPictures() { return cameraPictures; }

    public void setCameraPictures(Bitmap cameraPictures, int index) {
        this.cameraPictures[index] = cameraPictures;
    }

    public GameBoard(Context context) {
        this.context = context;
        cameraPictures = new Bitmap[6];
        thumbnails = new MemoThumbnail[6];
        thumbnails[0] = new MemoThumbnail((ImageView)((Activity)context).findViewById(R.id.thmb1));
        thumbnails[1] = new MemoThumbnail((ImageView)((Activity)context).findViewById(R.id.thmb2));
        thumbnails[2] = new MemoThumbnail((ImageView)((Activity)context).findViewById(R.id.thmb3));
        thumbnails[3] = new MemoThumbnail((ImageView)((Activity)context).findViewById(R.id.thmb4));
        thumbnails[4] = new MemoThumbnail((ImageView)((Activity)context).findViewById(R.id.thmb5));
        thumbnails[5] = new MemoThumbnail((ImageView)((Activity)context).findViewById(R.id.thmb6));
    }

    protected void assignPicturesToThumbnails() {
        for(int currentPosition = 0; currentPosition < thumbnails.length; currentPosition++) {
            thumbnails[currentPosition].setAssignedPicture(cameraPictures[currentPosition]);
            thumbnails[currentPosition].getThumbnail().setOnClickListener(this);
        }
    }

    public void shuffleThumbnails() {
        Random rand = new Random();
        for (int i = thumbnails.length - 1; i > 0; i--) {
            int newIndex = rand.nextInt(i + 1);
            MemoThumbnail thumbnailToShuffle = thumbnails[newIndex];
            thumbnails[newIndex] = thumbnails[i];
            thumbnails[i] = thumbnailToShuffle;
        }
    }

    private boolean userPickedTwoImages() {
        int numberOfClickedImages = 0;
        for (MemoThumbnail thumbnail : thumbnails) {
            if (thumbnail.isClicked())
                ++numberOfClickedImages;
        }
        return numberOfClickedImages == 2;
    }

    private boolean isMatch() {
        MemoThumbnail[] clickedImages = getCurrentlyClickedImages();
        Bitmap firstClickedImage = clickedImages[0].getAssignedPicture();
        Bitmap secondClickedImage = clickedImages[1].getAssignedPicture();
        return Objects.equals(firstClickedImage, secondClickedImage);
    }

    private MemoThumbnail[] getCurrentlyClickedImages() {
        MemoThumbnail[] clickedImages = new MemoThumbnail[2];
        int numberOfFoundImages = 0;
        for (MemoThumbnail thumbnail : thumbnails) {
            if (thumbnail.isClicked()) {
                clickedImages[numberOfFoundImages] = thumbnail;
                ++numberOfFoundImages;
            }
        }
        return clickedImages;
    }

    private void restoreQuestionMarksToClickedImages() {
        MemoThumbnail[] clickedImages = getCurrentlyClickedImages();
        for(MemoThumbnail thumbnail : clickedImages) {
            thumbnail.setClicked(false);
            thumbnail.restoreQuestionMark();
        }
    }

    private void hideMatchedImages() {
        MemoThumbnail[] clickedImages = getCurrentlyClickedImages();
        for(MemoThumbnail thumbnail : clickedImages) {
            thumbnail.setClicked(false);
            thumbnail.fadeOutAndHideImage();
        }
    }

    protected void restoreAllQuestionMarks() {
        for (MemoThumbnail thumbnail : thumbnails) {
            thumbnail.setClicked(false);
            thumbnail.restoreQuestionMark();
            thumbnail.getThumbnail().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        ImageView pressedButton = (ImageView) view;
        for (MemoThumbnail thumbnail : thumbnails) {
            if(thumbnail.getThumbnail().equals(pressedButton)) {
                if(thumbnail.isClicked()) {
                    thumbnail.restoreQuestionMark();
                    thumbnail.setClicked(false);
                } else {
                    thumbnail.revealImage();
                    thumbnail.setClicked(true);
                }
            }
        }
        if(userPickedTwoImages()) {
            if(isMatch()) {
                hideMatchedImages();
            } else {
                restoreQuestionMarksToClickedImages();
            }
        }
    }
}