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

    public IlyasView(Context context) {
        super(context);
    }

    public IlyasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        x = 400;  // координаты старта
        y = 1500; // координаты старта
        dy = 5; // скорость движения

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

                x = getMeasuredWidth()/2;
                y = getMeasuredHeight()/2;

                if (Build.VERSION.SDK_INT < 16) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

            }
        });

    }

    public void borders() {  // координаты остановки внизу
        if (y > 1500) {
            dy = 0;
        }
    }

    public void move3() {
        if (y > 1500) {
            dy = 5;
        }
    }

    public void borderu() {  // координаты остановки на верху
        if (y < 100) {
            dy = 0;
        }
    }

    public void move1() { // движение вверх
        y = y - dy;
    }

    public void move2() { // движение вниз
        if (y < 100) {
            dy = -5;
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
        canvas.drawCircle(x, y, 100, paint);

    }

    @Override               //выводит на экран
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(moveCircle);
    }
}