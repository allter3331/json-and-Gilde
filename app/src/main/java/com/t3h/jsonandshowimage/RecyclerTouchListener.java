package com.t3h.jsonandshowimage;

import android.content.Context;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    public static interface ClickListener {
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    private ClickListener   clicklistener;

    //Đối tượng để phát hiện ra onLongPress trên phần tử
    //clicklistener.onLongClick
    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

        this.clicklistener=clicklistener;

        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && clicklistener!=null){
                    clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        //Khi bắt đầu Touch trên RecyclerView thì tìm View biểu diễn phàn tử
        //ở vị trí đó và xử lý Touch để biết Click, LongCLick
        View child=rv.findChildViewUnder(e.getX(),e.getY());

        if (child instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup)child;

            for(int _numChildren = viewGroup.getChildCount() - 1; _numChildren >=0; --_numChildren)
            {
                View _child = viewGroup.getChildAt(_numChildren);
                Rect _bounds = new Rect();
                _child.getHitRect(_bounds);
                if (_bounds.contains((int)e.getX() - viewGroup.getLeft(), (int)e.getY() - viewGroup.getTop()))  {
                    if (_child instanceof Button)
                        return false;

                }
            }

        }

        if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
            clicklistener.onClick(child,rv.getChildAdapterPosition(child));
        }

        return false;

    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
