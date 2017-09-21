package com.touch.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class IlyasView extends View {

    private static final String TAG = "IlyasView";

    private int x;
    private int y;

    public IlyasView(Context context) {
        super(context);
    }

    public IlyasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IlyasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        canvas.drawCircle(720, 1480, 100, paint);
    }

    public void move() {

        x++;
        y++;
    }




}
