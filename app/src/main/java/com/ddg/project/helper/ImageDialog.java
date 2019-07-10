package com.ddg.project.helper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ddg.project.R;


/**
 * Created by mostafa kamal khedr on 16,September,2018
 */
public class ImageDialog {


//    private static ProgressBar progressBar;



    public static RequestOptions getRewuestOption() {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .priority(Priority.HIGH)

                .skipMemoryCache(true);
        return options;
    }
    public static int getImage(String imageName,Context  context) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }

}
