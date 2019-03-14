package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class GlideBindingAdapters {


    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView view, int id) {

        Context context = view.getContext();
        int imageUrl;
        switch (id) {
            case 1:
                imageUrl = R.drawable.ic_bakim;
                break;
            case 2:
                imageUrl = R.drawable.ic_ariza;
                break;
            case 3:
                imageUrl = R.drawable.ic_modern;
                break;
            case 4:
                imageUrl = R.drawable.ic_yedek;
                break;
            case 5:
                imageUrl = R.drawable.ic_yedek;
                break;
            case 7:
                imageUrl = R.drawable.ic_yedek;
                break;
            default:
                imageUrl = R.drawable.ic_bakim;
                break;
        }
        view.setImageResource(imageUrl);
      /*  RequestOptions option = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(option)
                .load(imageUrl)
                .into(view);*/
    }

    @BindingAdapter("linearBackground")
    public static void setImageResource(LinearLayout view, int id) {

        Context context = view.getContext();
        int imageUrl;
        switch (id) {
            case 1:
                imageUrl = R.drawable.isemri_bakim;
                break;
            case 2:
                imageUrl = R.drawable.isemri_ariza;
                break;
            case 3:
                imageUrl = R.drawable.isemri_modern;
                break;
            case 4:
                imageUrl = R.drawable.isemri_yedek;
                break;
            case 5:
                imageUrl = R.drawable.isemri_yedek;
                break;
            default:
                imageUrl = R.drawable.isemri_bakim;
                break;
        }
        view.setBackground(context.getDrawable(imageUrl));
    }

    @BindingAdapter("statusColor")
    public static void setStatusColor(ImageView view, int id) {

        Context context = view.getContext();
        int imageUrl;
        switch (id) {

            case 0:
                imageUrl = R.drawable.ic_az;
                break;
            case 1:
                imageUrl = R.drawable.ic_orta;
                break;
            case 2:
                imageUrl = R.drawable.ic_yuksek;
                break;
            default:
                imageUrl = R.drawable.ic_az;
                break;
        }
        view.setBackground(context.getDrawable(imageUrl));
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView view, String imageUrl) {
        Context context = view.getContext();
        RequestOptions option = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(option)
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter("imageResourceBitmap")
    public static void setImageResourceBitmap(ImageView view, Bitmap bitmap) {
        Context context = view.getContext();
        RequestOptions option = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(option)
                .load(bitmap)
                .into(view);
    }

    @BindingAdapter("imageResourceBase64")
    public static void setImageResourceBase64(ImageView view, String base64) {
        Context context = view.getContext();
        RequestOptions option = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        if (StringUtil.isNull(base64)) {
            final byte[] decodedBytes = Base64.decode(base64, Base64.DEFAULT);
            // Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes
            // .length);

            Glide.with(context)
                    .setDefaultRequestOptions(option)
                    .load(decodedBytes)
                    .into(view);
        } else {
            Glide.with(context)
                    .setDefaultRequestOptions(option)
                    .load("")
                    .into(view);
        }
    }
}
