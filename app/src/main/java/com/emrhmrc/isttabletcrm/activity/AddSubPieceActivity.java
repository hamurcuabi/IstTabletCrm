package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.emrhmrc.isttabletcrm.R;

public class AddSubPieceActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout lnr_main, lnr_sub;
    private Animation anim_down, anim_down_2, anim_up, anim_up_2;
    private ConstraintLayout cons_main, cons_sub;
    private boolean ismain_shown, issub_shown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_piece);
        init();
    }

    private void init() {
        ismain_shown = false;
        issub_shown = false;
        lnr_main = findViewById(R.id.lnr_main);
        lnr_sub = findViewById(R.id.lnr_sub);
        cons_main = findViewById(R.id.cons_main);
        cons_sub = findViewById(R.id.cons_sub);
        anim_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        anim_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        anim_up_2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        anim_down_2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        cons_main.setOnClickListener(this);
        cons_sub.setOnClickListener(this);
        anim_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == anim_up)
                    lnr_main.setVisibility(View.GONE);
                else if (animation == anim_up_2) lnr_sub.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.cons_main:
                AnimateDownMain();
                break;
            case R.id.cons_sub:
                AnimateDownSub();
                break;
        }
    }

    private void AnimateDownMain() {
        if (!ismain_shown) {
            lnr_main.setVisibility(View.VISIBLE);
            lnr_main.startAnimation(anim_down);
            ismain_shown = true;

            lnr_sub.startAnimation(anim_up_2);
            issub_shown = false;

        } else {
            lnr_main.startAnimation(anim_up);
            ismain_shown = false;
        }


    }

    private void AnimateDownSub() {
        if (!issub_shown) {
            lnr_sub.setVisibility(View.VISIBLE);
            lnr_sub.startAnimation(anim_down_2);
            issub_shown = true;
            lnr_main.startAnimation(anim_up);
            ismain_shown = false;
        } else {
            lnr_sub.startAnimation(anim_up_2);
            issub_shown = false;
        }

    }
}
