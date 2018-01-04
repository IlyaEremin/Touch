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
    private int x2;
    private int y2 = 1200;
    private int dy;
    private int dy2;
    private int speed = 10;

    public IlyasView(Context context) {
        super(context);
    }

    public IlyasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        dy = speed;                                                                                  // скорость движения

        moveCircle = new Runnable() {

            public void run() {
                move1();
                runAnimation();
                invalidate();
                move2();
                borders();
                borders2();
                borders3();
                move3();
                move4();
                move5();

            }

        };
        runAnimation();

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                x = getMeasuredWidth() / 2;

                if (Build.VERSION.SDK_INT < 16) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

            }

        });

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                x2 = getMeasuredWidth() / 2;

                if (Build.VERSION.SDK_INT < 16) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

            }

        });

    }

    public void borders() {                                                                          // координаты остановки внизу
        if (y > 1000) {
            dy = 0;
        }
    }

    public void move1() {                                                                            // движение вниз
        y = y - dy;
    }

    public void move2() {
        if (y < 1000) {
            dy = -speed;
        }
    }

    public void borders2() {                                                                         // координаты остановки внизу
        if (y2 > 2330) {
            dy2 = 0;
        }
    }

    public void move3() {                                                                            // движение вниз
        y2 = y2 - dy2;
    }

    public void move4() {
        if (y > 1000) {
            dy2 = -speed;
        }
    }

//    public void move5() {
//        if (y2 == 2330) {
//            dy2++;
//        }
//    }
//    public void borders3() {                                                                         // координаты остановки внизу
//        if (y2 < 1200) {
//            dy2 = 0;
//        }
//    }


    private void runAnimation() {
        postDelayed(moveCircle, 10);
    }

    public IlyasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override                                                                                        //рисует по заданным координатам
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint1 = new Paint();
        paint1.setColor(Color.CYAN);
        canvas.drawCircle(x, y, 100, paint1);

        Paint paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        canvas.drawCircle(x2, y2, 100, paint2);
    }

    @Override                                                                                        //выводит на экран
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(moveCircle);
    }
}