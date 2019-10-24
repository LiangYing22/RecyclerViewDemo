package com.lying.recyclerviewdemo.fenzu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FenzuDecoration extends RecyclerView.ItemDecoration {

    public static final String TAG = FenzuDecoration.class.getSimpleName();

    private Context context;
    private FenzuCallBack fenzuCallBack;
    Paint paint ;


    private float headerHeight = 30;
    private float dividerHeight = 2;

    public FenzuDecoration(Context context, FenzuCallBack fenzuCallBack){
        this.context = context;
        this.fenzuCallBack = fenzuCallBack;
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect,view,parent,state);
        if(fenzuCallBack != null){
            int index = parent.getChildAdapterPosition(view);
            Fenzu fenzu = fenzuCallBack.getFenzu(index);
            if(null != fenzu && fenzu.getPosition() == 0){
                outRect.top = (int)headerHeight;
            }else{
                outRect.top = (int)dividerHeight;
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        int childeCount = parent.getChildCount();
        for(int i = 0; i < childeCount; i++){
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
            if(fenzuCallBack != null){
                Fenzu fenzu = fenzuCallBack.getFenzu(index);
                if(fenzu != null && fenzu.getPosition() == 0){
                    paint.setColor(Color.parseColor("#000000"));
                    float left = parent.getPaddingLeft();
                    float top = view.getTop() - headerHeight;
                    float right = parent.getWidth() - parent.getPaddingRight();
                    float bottom = view.getTop();
                    canvas.drawRect(left,top,right,bottom,paint);
                    TextPaint textPaint = new TextPaint();
                    textPaint.setAntiAlias(true);
                    textPaint.setTextSize(40);
                    textPaint.setColor(Color.parseColor("#ffaa22"));
                    canvas.drawText(fenzu.getId()+"",left+15, top+30, textPaint);
                }else{
                    Log.i(TAG,"非首项绘制");
                    paint.setColor(Color.parseColor("#ffaa22"));
                    float left = parent.getPaddingLeft();
                    float top = view.getTop() - dividerHeight;
                    float right = parent.getWidth() - parent.getPaddingRight();
                    float bottom = view.getTop();
                    canvas.drawRect(left,top,right,bottom,paint);
                }
            }
        }
    }

    interface FenzuCallBack {
        Fenzu getFenzu(int position);
    }
}
