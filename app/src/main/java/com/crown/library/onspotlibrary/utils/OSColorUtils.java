package com.crown.library.onspotlibrary.utils;

import android.content.Context;
import android.graphics.Color;

import com.crown.library.onspotlibrary.R;

import java.util.Random;

public class OSColorUtils {
    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public static int getDefinedRandomColor(Context context) {
        int[] colors = context.getResources().getIntArray(R.array.colorMix);
        return colors[new Random().nextInt(colors.length)];
    }
}
