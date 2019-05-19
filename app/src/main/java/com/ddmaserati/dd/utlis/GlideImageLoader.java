package com.ddmaserati.dd.utlis;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ddmaserati.dd.R;

/**
 * dec:  glide
 * Created by ddmaserati
 * on 2019/4/10.
 */
public class GlideImageLoader {

//  private static     RequestOptions requestOptions = new RequestOptions()
//              .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round)
//              .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

      public static void load(Context context, String url, ImageView imageView)
      {
         // Glide.with(context).load(url).apply(requestOptions).into(imageView);
          Glide.with(context).load(url).into(imageView);

      }

      public static void lowMemory(Activity activity)
      {
          Glide.get(activity).clearMemory();
      }

      public static void trimMemory(Activity activity,int level)
      {
          Glide.get(activity).trimMemory(level);
      }

}
