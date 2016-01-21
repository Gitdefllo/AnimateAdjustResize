package com.fllo.adjustresize.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Class utilities
 */
public class AdjustUtils {
    /**
     * Conversion pixels to density independent pixels.
     *
     * @param (px) A value in px (pixels) unit
     * @param (context) Context to get resources and device specific display metrics
     * @return (float) A value to represent dp equivalent to px value
     */
    public static float pxToDp(float px, Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }
}
