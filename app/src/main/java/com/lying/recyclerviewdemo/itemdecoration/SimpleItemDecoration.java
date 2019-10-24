package com.lying.recyclerviewdemo.itemdecoration;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 简单的分割线：设置每个 item 的 outRect.top = 1，每个item的上方偏移一个像素。
 * 偏移一个像素，会露出背景，所以这个分割线颜色取决于背景颜色
 * outRect 等同于 margin 属性，是 item 的偏移区域
 */
public class SimpleItemDecoration  extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect,view,parent,state);

        //第一个 item 不设置
        if(parent.getChildAdapterPosition(view) != 0){
            outRect.top = 1;
        }
    }
}
