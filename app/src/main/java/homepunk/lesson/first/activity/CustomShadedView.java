package homepunk.lesson.first.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.RelativeLayout;

public class CustomShadedView extends View {
    final int height, width;
    int lineStartY, rightX, leftX, bottomY, lineEndY;
    int koefUp, koefDown;
    int alpha;
    int red, green, blue;
    Paint paint;
    Path path;

    public CustomShadedView(Context context, int viewWidth, int viewHeight) {
        super(context);
        width = RelativeLayout.LayoutParams.MATCH_PARENT;
        height = viewHeight;
        leftX = 0;
        rightX = viewWidth;
        bottomY = height;
        lineStartY = height * 7 / 11;
        lineEndY = height / 3;
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.moveTo(leftX, lineStartY);
        path.lineTo(rightX, lineEndY);
        path.lineTo(rightX, bottomY);
        path.lineTo(leftX, bottomY);
        path.close();

        paint.setColor(Color.rgb(red, green, blue));
        paint.setAlpha(alpha);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, paint);
    }

    public int getFabLeftMargin(int fabWidth) {
        int a1 = lineStartY - lineEndY;
        int b1 = rightX - leftX;
        int c1 = leftX * lineEndY - rightX * lineStartY;
        int a2 = 0;
        int b2 = leftX - rightX;
        int c2 = (rightX * bottomY - leftX * bottomY) * 7 / 18;
        return (c2 * b1 - c1 * b2) / (a1 * b2 - a2 * b1) - fabWidth;
    }

    public int getFabTopMargin(int fabWidth, int koefUp) {
        return (height * koefUp / koefDown - fabWidth);
    }

    public void setKoef(int koefUp, int koefDown) {
        this.koefUp = koefUp;
        this.koefDown = koefDown;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}