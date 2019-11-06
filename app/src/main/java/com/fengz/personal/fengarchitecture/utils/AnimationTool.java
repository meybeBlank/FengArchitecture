package com.fengz.personal.fengarchitecture.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class AnimationTool {

    /**
     * 任务完成动画
     */
    public static void finishTask(Activity context, View startView) {
        if (context == null) {
            return;
        }
        int[] startLocation = new int[2];
        startView.getLocationInWindow(startLocation);

        final ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(getBitmapFromViewDraw(startView));
        final ViewGroup mAnimLayout = (ViewGroup) context.getWindow().getDecorView().findViewById(android.R.id.content);
        mAnimLayout.addView(imageView);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = startLocation[0];
        lp.topMargin = startLocation[1];
        imageView.setLayoutParams(lp);

        // 计算位移
        int endX = UITool.getScreenWidth(context) - startLocation[0] - startView.getWidth();
        int endY = UITool.getScreenHeight(context) - startLocation[1];

        long duration = 1000;
        ObjectAnimator translateAnimationX = ObjectAnimator.ofFloat(imageView, "translationX", 0, endX);
        translateAnimationX.setDuration(duration);

        ObjectAnimator translateAnimationY = ObjectAnimator.ofFloat(imageView, "translationY", 0, endY);
        translateAnimationY.setDuration(duration);
        translateAnimationY.setInterpolator(new AnticipateInterpolator(1.0f));

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1, 0.1f);
        scaleX.setDuration(duration);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1, 0.1f);
        scaleY.setDuration(duration);
        scaleY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mAnimLayout != null) {
                    mAnimLayout.removeView(imageView); //动画结束后移除动画图片
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();//设置动画播放顺序
        animatorSet.play(translateAnimationX).with(translateAnimationY).with(scaleX).with(scaleY);
        animatorSet.start();
    }

    private static Bitmap getBitmapFromViewDraw(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
