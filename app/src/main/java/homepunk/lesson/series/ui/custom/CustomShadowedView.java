package homepunk.lesson.series.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import homepunk.lesson.series.utils.ScreenUtils;

public class CustomShadowedView extends View {
    private final int height, width;
    private double lineAngleCoef = 0.5, xOffsetCoef = 1;
    private int alpha = 1;

    private Paint paint = new Paint();
    private Path path = new Path();

    public CustomShadowedView(Context context) {
        super(context);
        width = ScreenUtils.getDisplayContentWidth(context);
        height = ScreenUtils.getDisplayContentHeight(context);
    }

    public CustomShadowedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        width = ScreenUtils.getDisplayContentWidth(context);
        height = ScreenUtils.getDisplayContentHeight(context);
    }

    public CustomShadowedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        width = ScreenUtils.getDisplayContentWidth(context);
        height = ScreenUtils.getDisplayContentHeight(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawFigure();

        paint.setAlpha(alpha);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, paint);
    }

    private void drawFigure(){
        int leftX = 0, rightX = width;
        int bottomEndX = height;
        float lineStartY = (float) ( (height * getLineAngleCoef()));
        float lineEndY = (float) (height * (1 - getLineAngleCoef()));
        if (xOffsetCoef != 1){
            lineStartY *= xOffsetCoef;
            lineEndY *= xOffsetCoef;
        }

        path.moveTo(leftX, lineStartY);
        path.lineTo(rightX, lineEndY);
        path.lineTo(rightX, bottomEndX);
        path.lineTo(leftX, bottomEndX);
        path.close();
    }

    public void setTransparentAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setColor(int red, int green, int blue) {
        paint.setColor(Color.rgb(red, green, blue));
    }

    public double getLineAngleCoef() {
        return lineAngleCoef;
    }

    public void setLineAngleCoef(double lineAngleCoef) {
        this.lineAngleCoef = lineAngleCoef;
    }

    public double getXOffsetCoef() {
        return xOffsetCoef;
    }

    public void setxOffsetCoef(double xOffsetCoef) {
        this.xOffsetCoef = xOffsetCoef;
    }
}