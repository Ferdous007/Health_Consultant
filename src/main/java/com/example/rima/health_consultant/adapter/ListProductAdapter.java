package com.example.rima.health_consultant.adapter;
import com.example.rima.health_consultant.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rima.health_consultant.model.Product;

import java.util.List;

/**
 * Created by SHARMI on 28/12/2015.
 */
public class ListProductAdapter extends BaseAdapter {
    private Context mContext;
    private List<Product>mProductList;

    public ListProductAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_listview, null);
        TextView tvPhysicalproblem = (TextView)v.findViewById(R.id.tv_product_physicalproblem);
        tvPhysicalproblem.setText(mProductList.get(position).getPhysicalproblem());
        return v;
    }
}
