package homepunk.lesson.first.view.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerClickListener implements RecyclerView.OnItemTouchListener {

    private final GestureDetector mGestureDetector;
    private final GestureListener mGestureListener;

    private final OnItemMotionEventListener mListener;

    public RecyclerClickListener(Context context, OnItemMotionEventListener listener) {
        mListener = listener;
        mGestureListener = new GestureListener();

        mGestureDetector = new GestureDetector(context, mGestureListener);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {

        mGestureListener.setRecyclerView(view);

        mGestureDetector.onTouchEvent(e);

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private RecyclerView mRecyclerView;

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View view = findView(e);
            if (validate(view)) {
                int position = findPosition(view);
                mListener.onItemClick(view, position);
            }
            return true;
        }


        @Override
        public void onLongPress(MotionEvent e) {
            View view = findView(e);
            if (validate(view)) {
                int position = findPosition(view);
                mListener.onItemLongClick(view, position);
            }
        }

        private boolean validate(View view) {
            if (view != null && mListener != null) {
                return true;
            } else {
                return false;
            }
        }

        private View findView(MotionEvent e) {
            View childView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            return childView;
        }

        private int findPosition(View view) {
            return mRecyclerView.getChildPosition(view);
        }

        public void setRecyclerView(RecyclerView mRecyclerView) {
            this.mRecyclerView = mRecyclerView;
        }
    }

    public interface OnItemMotionEventListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    public static class SimpleItemMotionEventListener implements OnItemMotionEventListener{

        @Override
        public void onItemClick(View view, int position) {

        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    }
}


