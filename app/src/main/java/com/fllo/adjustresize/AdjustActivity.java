package com.fllo.adjustresize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

/**
 * Create (an illusion) to move a footer button with the SoftKeyboard:
 *
 * Use a custom ViewGroup to catch when the SKB shows up and trigger,
 * by an interface, the button animations.
 */
public class AdjustActivity extends AppCompatActivity {

    private Button footerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust);

        footerButton = (Button) findViewById(R.id.footer_button);

        AdjustViewGroupHandler containerView = (AdjustViewGroupHandler) findViewById(R.id.container_view);
        containerView.setKeyboardStateListener(
                new AdjustViewGroupHandler.onKeyboardStateChange() {
            @Override
            public void onKeyboardShow() {
                setAnimationUp();
            }

            @Override
            public void onKeyboardHide() {
                setAnimationDown();
            }
        });
    }

    private void setAnimationUp() {
        footerButton.setVisibility(View.GONE);

        Animation a1 = new TranslateAnimation(0, 0, footerButton.getHeight() * 4, -20);
        a1.setDuration(250);
        a1.setFillAfter(true);

        final Animation a2 = new TranslateAnimation(0, 0, -20, 0);
        a2.setDuration(320);
        a2.setFillAfter(true);

        a1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                footerButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                footerButton.startAnimation(a2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        footerButton.startAnimation(a1);
    }

    private void setAnimationDown() {
        Animation b1 = new TranslateAnimation(0, 0, -(footerButton.getHeight() * 4), 30);
        b1.setDuration(200);
        b1.setFillAfter(true);

        final Animation b2 = new TranslateAnimation(0, 0, 30, 0);
        b2.setDuration(320);
        b2.setFillAfter(true);

        b1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                footerButton.startAnimation(b2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        footerButton.startAnimation(b1);
    }
}
