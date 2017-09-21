package com.touch.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class IlyasView extends View {

    private Runnable moveCircle;

    private int      x;
    private int      y;
    private int      dy;

    public IlyasView(Context context) {
        super(context);
    }

    public IlyasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        x = 500;
        y = 500;
        dy = 5;

        int borderu = 100;
        int borders = 1000;

        moveCircle = new Runnable() {
            @Override public void run() {
                move();
                runAnimation();
                invalidate();
                border();

            }

        };
        runAnimation();
    }

    public void border(){
        if (y == 100) {
           dy=0;
        }

    }

        public void move() {
        y = y-dy;

    }

    private void runAnimation() {
        postDelayed(moveCircle, 10);
    }

    public IlyasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        canvas.drawCircle(x, y, 100, paint);

    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(moveCircle);
    }
}
