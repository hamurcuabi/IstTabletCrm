package com.emrhmrc.isttabletcrm.util;

import android.content.Context;
import android.graphics.Typeface;

public class TextFont {

    public static Typeface robotoBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
    }

    public static Typeface robotoMedium(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "Roboto-Medium.ttf");
    }

    public static Typeface robotoRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
    }

    public static Typeface sourceSansProBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "SourceSansPro-Bold.ttf");
    }
    public static Typeface sourceSansProRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "SourceSansPro-Regular.ttf");
    }
}