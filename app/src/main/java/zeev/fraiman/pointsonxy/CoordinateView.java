package zeev.fraiman.pointsonxy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class CoordinateView extends View {

    private Paint paint;
    private List<Point> points;

    public CoordinateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        points = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int originX = width / 2;
        int originY = height / 2;

        // Axis
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        canvas.drawLine(0, originY, width, originY, paint); // Ось X
        canvas.drawLine(originX, 0, originX, height, paint); // Ось Y

        // Draw points
        for (Point p : points) {
            float x = originX + p.getX() * 10;
            float y = originY - p.getY() * 10;
            canvas.drawCircle(x, y, 5, paint);
            paint.setTextSize(30);
            canvas.drawText(p.getName() + "(" + p.getX() + "," + p.getY() + ")", x + 10, y, paint);
        }
    }

    public void addPoint(Point point) {
        points.add(point);
        invalidate();
    }

    public void clearPoints() {
        points.clear();
        invalidate();
    }
}
