package com.welson.gankio2.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideUtil {

    public static void loadImage(Context context, String url, ImageView imageView){
        Glide.with(context).asBitmap().load(url).into(imageView);
    }

    public static void loadImageGif(Context context, String url, ImageView imageView){
        Glide.with(context).asGif().load(url).into(imageView);
    }

}
