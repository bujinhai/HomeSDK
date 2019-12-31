package com.jinshu.homelibrary.recyclerview.baseadapter;

public interface SMultiItemTypeSupport<T>
{
	int getLayoutId(int itemType);

	int getItemViewType(int position, T t);
}