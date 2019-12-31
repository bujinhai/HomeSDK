package com.jinshu.homelibrary.recyclerview.irc;

import android.view.View;

public interface OnItemClickListener<T>
{
    void onItemClick(View view, T t, int position);
}