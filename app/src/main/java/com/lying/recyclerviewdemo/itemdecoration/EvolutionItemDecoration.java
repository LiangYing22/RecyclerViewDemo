package com.lying.recyclerviewdemo.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 分割线 进化版1.0 ：自定义分割线的颜色
 * 改进化版的分割线只是一条线 + 自定义线的颜色
 */
public class EvolutionItemDecoration extends RecyclerView.ItemDecoration {

    public static final String TAG = EvolutionItemDecoration.class.getSimpleName();

    //设置的分割线高度
    private float dividerHeight;

    //画笔
    private Paint paint;

    public EvolutionItemDecoration(){
        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
    }

    /**
     * 主要作用：制造分割线，并为 onDraw 方法计算坐标提供方便。(因为提供了 dividerHeight)
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect,view,parent,state);

        if(parent.getChildAdapterPosition(view) != 0){
            outRect.top = 2;
            dividerHeight = 2;
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state){
        super.onDraw(canvas,parent,state);

        //获取的 childCount 含义为：一个屏幕显示的数量，并不是真正数据的数量
        //本例中 childCount 有时等于18，有时等于 19。因为滑动的状态不同，一个屏幕显示的数量不同。子项显示一点点也会计算在 childCount 中。
        int childCount = parent.getChildCount();
        Log.i(TAG,"childCount = " + childCount);

        for(int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
            //根据 view 获取的位置是真实地，是对应数据的位置，是唯一的。
            int index = parent.getChildAdapterPosition(view);
            Log.i(TAG, "i = " + i + "index = " + index);

            if(index == 0){
                continue;
            }
            //用自己相对 RecyclerView 的 Top 值 减去 分割线的高度，得到分割线的左上角的纵坐标。
            float dividerTop = view.getTop() - dividerHeight;
            //获取 RecyclerView 除去左 padding 的值，得到分割线的左上角的横坐标。
            //注意：不能用 view.getPaddingLeft() 去获取，因为Padding 值是在 RecyclerView 中设置的。
            float dividerLeft = parent.getPaddingLeft();
            float dividerBottom = view.getTop();
            float dividerRight = parent.getWidth() - parent.getPaddingRight();

            canvas.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, paint);
        }
    }
}
