package com.touch.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;


public class IlyasView extends View {

    private Runnable moveCircle;
    private int x;
    private int y;
    private int dy;
    private int bottomBorder;
    private int speed = 10;
    private int radius;

    public IlyasView(Context context) {
        super(context);
    }

    public IlyasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dy = speed; // скорость движения

        moveCircle = new Runnable() {

            public void run() {
                move1();
                runAnimation();
                invalidate();
                borderu();
                move2();

                borders();
                move3();
            }
        };
        runAnimation();

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                x = getMeasuredWidth() / 2;
                y = getMeasuredHeight() / 2;
                radius = getMeasuredWidth() / 10;
                bottomBorder = getMeasuredHeight() - radius;
                speed = getMeasuredHeight() / 100;

                if (Build.VERSION.SDK_INT < 16) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

            }

        });
    }

    public void borders() {  // координаты остановки внизу
        if (y > bottomBorder) {
            dy = 0;
        }
    }

    public void move3() {
        if (y > bottomBorder) {
            dy = speed;
        }
    }

    public void borderu() {  // координаты остановки на верху
        if (y < radius) {
            dy = 0;
        }
    }

    public void move1() { // движение вверх
        y = y - dy;
    }

    public void move2() { // движение вниз
        if (y < radius) {
            dy = -speed;
        }
    }

    private void runAnimation() {
        postDelayed(moveCircle, 10);

    }

    public IlyasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override                 //рисует по заданным координатам
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        canvas.drawCircle(x, y, radius, paint);

    }

    @Override               //выводит на экран
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(moveCircle);
    }
}