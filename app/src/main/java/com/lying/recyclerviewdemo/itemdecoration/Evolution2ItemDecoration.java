package com.lying.recyclerviewdemo.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 分割线 进化版 2.0 ：在每项左侧绘制圆和线
 * 目前用到的还是 getItemOffsets 和 onDraw
 */
public class Evolution2ItemDecoration extends RecyclerView.ItemDecoration {

    public static final String TAG = Evolution2ItemDecoration.class.getSimpleName();

    //横线分割线高度
    private float dividerHeight;
    //纵向分割宽度
    private float dividerWidth;
    //圆心 X 坐标
    private float nodeRadiusX;
    //圆心 Y 坐标
    private float nodeRadiusY;
    //圆半径
    private float nodeRadius = 10;
    //纵向分割线宽度
    private float verticalLineWidth = 5;

    private Paint paint;

    public Evolution2ItemDecoration(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect,view,parent,state);

        if(parent.getChildAdapterPosition(view) != 0){
            outRect.top = 2;
        }

        outRect.left = 100;
        dividerWidth = 100;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);

            if(index == 0){
                dividerHeight = view.getTop();
            }else{
                dividerHeight = 2;
                //为每个子项画横线
                float dividerLeft = parent.getPaddingLeft() + dividerWidth;
                float dividerTop = view.getTop() - dividerHeight;
                float dividerRight = parent.getWidth() - parent.getPaddingRight();
                float dividerBottom = view.getTop();
                c.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,paint);
            }
            nodeRadiusY = view.getBottom() - (view.getBottom() - view.getTop())/2;
            Log.i(TAG,"nodeRadiusY = " + nodeRadiusY);
            nodeRadiusX = dividerWidth/2;
            //画竖线；上部分
            float upLeft = parent.getPaddingLeft() + nodeRadiusX - verticalLineWidth/2;
            float upTop = view.getTop() - dividerHeight;
            float upRight = parent.getPaddingLeft() + nodeRadiusX + verticalLineWidth/2;
            float upBottom = nodeRadiusY;
            c.drawRect(upLeft,upTop,upRight,upBottom,paint);
            //画竖线：下部分
            float downLeft = parent.getPaddingLeft() + nodeRadiusX - verticalLineWidth/2;
            float downTop = nodeRadiusY;
            float downRight = parent.getPaddingLeft() + nodeRadiusX + verticalLineWidth/2;
            float downBottom = view.getBottom();
            c.drawRect(downLeft,downTop,downRight,downBottom,paint);
            //画圆
            c.drawCircle(nodeRadiusX,nodeRadiusY,nodeRadius,paint);
        }
    }
}
