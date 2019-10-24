package com.lying.recyclerviewdemo.itemdecoration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lying.recyclerviewdemo.R;

/**
 *  升级使用：使用 onDrawOver 绘制角标。
 */
public class Evolution3ItemDecoration extends RecyclerView.ItemDecoration {

    public static final String TAG = Evolution3ItemDecoration.class.getSimpleName();

    //横向分割线的高度
    private float dividerHeight = 2;
    private Paint paint;
    private Bitmap bitmap;

    public Evolution3ItemDecoration(Context context){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.qiansan);
    }

    /**
     * 主要作用：制造分割线，并为 onDraw 方法计算坐标提供方便。(因为提供了 dividerHeight)
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect,view,parent,state);

        if(parent.getChildAdapterPosition(view) != 0){
            outRect.top = 2;
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            //根据 view 获取的位置是真实地，是对应数据的位置，是唯一的。
            int index = parent.getChildAdapterPosition(view);
            if(index != 0){
                float dividerLeft = parent.getPaddingLeft();
                float dividerTop = view.getTop() - dividerHeight;
                float dividerRight = parent.getWidth() - parent.getPaddingRight();
                float dividerBottom = view.getTop();
                canvas.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,paint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
            LinearLayout layout = (LinearLayout)view;
            TextView tvId = (TextView) layout.findViewById(R.id.tv_id);
            float bLeft = tvId.getWidth()/2 - bitmap.getWidth()/2;
            int index = parent.getChildAdapterPosition(view);
            if(index < 3){
                c.drawBitmap(bitmap,bLeft,view.getTop(),paint);
            }else{
                break;
            }
        }
    }
}
