package homepunk.lesson.first.contollers.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.RelativeLayout;

public class CustomShadedView extends View {
    int startY, maxX, maxY, endY, height;
    int koefUp, koefDown;
    int startX = 0;
    int alpha;
    int red = 0, green = 0, blue = 0;
    Paint paint;
    Path path;

    public CustomShadedView(Context context, int viewWidth, int viewHeight) {
        super(context);
        paint = new Paint();
        path = new Path();
        maxX = viewWidth;
        maxY = height = viewHeight;
        endY = maxY / 3;
        startY = maxY * 7/11;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int height = maxY;
        final int width = RelativeLayout.LayoutParams.MATCH_PARENT;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.moveTo(startX, startY);
        path.lineTo(maxX, endY);
        path.lineTo(maxX, maxY);
        path.lineTo(startX, maxY);
        path.close();

        paint.setColor(Color.rgb(red, green, blue));
        paint.setAlpha(alpha);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, paint);
    }

    public int getFabLeftMargin(int fabWidth) {
        int a1 = startY - endY;
        int b1 = maxX - startX;
        int c1 = startX * endY - maxX * startY;
        int a2 = 0;
        int b2 = startX - maxX;
        int c2 = (maxX * maxY - startX * maxY) * 7 / 18;
        return (c2 * b1 - c1 * b2)/(a1 * b2 - a2 *b1) - fabWidth;
    }

    public int getFabTopMargin(int fabWidth, int koefUp){
        return (int) (height * koefUp/koefDown - fabWidth);
    }

    public void setKoef(int koefUp, int koefDown){
        this.koefUp = koefUp;
        this.koefDown = koefDown;
    }

    public void setAlpha(int alpha){
        this.alpha = alpha;
    }

    public void setColor(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}