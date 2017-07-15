package cheng.drawpattle.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cheng on 2017/7/15.
 */

public class MyDrawView extends View {

    private List<Point> points= new ArrayList<>();

    public MyDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        super.setBackgroundColor(Color.WHITE);
        super.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Point point = new Point((int) (motionEvent.getX()), (int) (motionEvent.getY()));
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    points = new ArrayList<>();
                    points.add(point);
                } else if (action == MotionEvent.ACTION_UP) {
                    points.add(point);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    points.add(point);
                    MyDrawView.this.postInvalidate();
                }
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        int size = points.size();
        if (size > 1) {
            Iterator<Point> iterator = points.iterator();
            Point firstPoint = null;
            Point lastPoint = null;
            while (iterator.hasNext()) {
                if (firstPoint == null) {
                    firstPoint = iterator.next();
                } else {
                    if (lastPoint != null) {
                        firstPoint = lastPoint;
                    }
                    lastPoint = iterator.next();
                    canvas.drawLine(firstPoint.x, firstPoint.y, lastPoint.x
                            , lastPoint.y, paint);
                }
            }
        }

        super.onDraw(canvas);
    }
}
