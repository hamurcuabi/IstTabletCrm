package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.emrhmrc.isttabletcrm.R;

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
                imageUrl = R.drawable.ic_modern;
                break;
            case 3:
                imageUrl = R.drawable.ic_ariza;
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
                imageUrl = R.drawable.isemri_modern;
                break;
            case 3:
                imageUrl = R.drawable.isemri_ariza;
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
}
