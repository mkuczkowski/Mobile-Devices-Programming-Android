package com.maciej.project1_memo;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MemoThumbnail {
    private ImageView thumbnail;
    private Bitmap assignedPicture;
    private boolean isClicked;

    public void setClicked(boolean clicked) { isClicked = clicked; }
    public boolean isClicked() { return isClicked; }

    public void setAssignedPicture(Bitmap assignedPicture) {
        this.assignedPicture = assignedPicture;
    }
    public Bitmap getAssignedPicture() { return assignedPicture; }

    public ImageView getThumbnail() { return thumbnail; }

    public MemoThumbnail(ImageView thumbnail) {
        this.thumbnail = thumbnail;
        assignedPicture = null;
        isClicked = false;
    }

    protected void fadeOutAndHideImage() {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                thumbnail.setVisibility(View.INVISIBLE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });
        thumbnail.startAnimation(fadeOut);
    }

    protected void restoreQuestionMark() {
        Animation restoreQMark = new AlphaAnimation(1, 0);
        restoreQMark.setInterpolator(new AccelerateInterpolator());
        restoreQMark.setDuration(900);
        restoreQMark.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                thumbnail.setImageResource(R.drawable.questionmark);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });
        thumbnail.startAnimation(restoreQMark);
    }

    protected void revealImage() { thumbnail.setImageBitmap(assignedPicture); }
}