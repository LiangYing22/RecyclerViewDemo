package com.lying.recyclerviewdemo.nianxingfenzu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lying.recyclerviewdemo.fenzu.Fenzu;

public class NianxingFenzuItemDecoration extends RecyclerView.ItemDecoration  {

    private static final String TAG = NianxingFenzuItemDecoration.class.getSimpleName();

    private NianxingFenzuCallBack nianxingFenzuCallBack;
    private Paint paint;
    private TextPaint textPaint;

    private float headerHeight = 30;
    private float dividerHeight = 2;

    public NianxingFenzuItemDecoration(NianxingFenzuCallBack nianxingFenzuCallBack){
        this.nianxingFenzuCallBack = nianxingFenzuCallBack;
        paint = new Paint();
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(40);
        textPaint.setColor(Color.parseColor("#ffaa22"));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect,view,parent,state);
        if(nianxingFenzuCallBack != null){
            int index = parent.getChildAdapterPosition(view);
            Fenzu fenzu = nianxingFenzuCallBack.getFenzu(index);
            if(null != fenzu && fenzu.getPosition() == 0){
                outRect.top = (int)headerHeight;
            }else{
                outRect.top = (int)dividerHeight;
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int childeCount = parent.getChildCount();

        if(nianxingFenzuCallBack == null)
            return;

        for(int i = 0; i < childeCount; i++){
            View view = parent.getChildAt(i);

            //说明：i 的含义是屏幕上可见 View 的位置，最大值是屏幕显示的个数。
            //说明：index 是每个 View 真实的位置，不管显不显示在屏幕上，对应数据 List 中的位置。
            int index = parent.getChildAdapterPosition(view);

            Fenzu fenzu = nianxingFenzuCallBack.getFenzu(index);

            // headView 的左右两边的横坐标
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            // i = 0，表示是屏幕上第一个可见的，需要绘制 headView
            if(i == 0){
                int top = parent.getPaddingTop();
                //让 header 跟着最后一个 view 消失
                if(fenzu.getPosition() == 4){
                    int suggestTop = view.getBottom() - (int)headerHeight;
                    // view 的 getBottom（底部）减去 header 的高度 与 top 相等时，表明最后一个 view 底部与 header 底部平齐。
                    // 此时可以动态改变 top 的值了。实现最后一个 view 时，header 跟随 view走，形成推动效果。
                    if(suggestTop <= top){
                        top = suggestTop;
                    }
                }
                int bottom = top + (int)headerHeight;
                drawHeaderRect(c,fenzu,left,top,right,bottom);
            }else{
                // 不是屏幕上第一个可见的，只有组内第一个
                if(fenzu.getPosition() == 0){
                    int top = view.getTop() - (int)headerHeight;
                    int bottom = view.getTop();
                    drawHeaderRect(c,fenzu,left,top,right,bottom);
                }
            }
        }

    }

    private void drawHeaderRect(Canvas c, Fenzu fenzu, int left, int top, int right, int bottom) {
        //绘制Header
        c.drawRect(left,top,right,bottom,paint);

        float titleX =  (float) left + 13;
        float titleY =  (float) top + 30;
        //绘制Title
        c.drawText(fenzu.getId()+"",titleX,titleY,textPaint);
    }


    interface NianxingFenzuCallBack {
        Fenzu getFenzu(int position);
    }
}
