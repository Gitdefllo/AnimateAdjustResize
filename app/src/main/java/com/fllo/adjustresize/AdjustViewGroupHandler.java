package com.fllo.adjustresize;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

public class AdjustViewGroupHandler extends RelativeLayout {

    private boolean isKeyboardShown;
    private onKeyboardStateChange listener;

    public AdjustViewGroupHandler(Context context) {
        super(context);
    }

    public AdjustViewGroupHandler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustViewGroupHandler(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setKeyboardStateListener(onKeyboardStateChange listener) {
        this.listener = listener;
    }

    // Callbacks
    public interface onKeyboardStateChange {
        void onKeyboardShow();
        void onKeyboardHide();
    }

    @Override
    public boolean dispatchKeyEventPreIme(@NonNull KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // Keyboard is hiding
            if (isKeyboardShown) {
                isKeyboardShown = false;
                listener.onKeyboardHide();
            }
        }
        return super.dispatchKeyEventPreIme(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int proposedHeight = MeasureSpec.getSize(heightMeasureSpec);
        final int actualHeight = getHeight();
        if (actualHeight > proposedHeight) {
            // Keyboard is showing
            if (!isKeyboardShown) {
                isKeyboardShown = true;
                listener.onKeyboardShow();
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
