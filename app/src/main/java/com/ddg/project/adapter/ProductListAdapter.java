package com.ddg.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ddg.project.R;
import com.ddg.project.helper.ImageDialog;
import com.ddg.project.model.ProductsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mostafa kamal khedr on 09,July,2019
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<ProductsModel> productsModelList;
    private Context context;

    public ProductListAdapter(List<ProductsModel> productsModelList, Context context) {
        this.productsModelList = productsModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int productImage = productsModelList.get(position).getProductImage();
        final String productLink = productsModelList.get(position).getProductLink();
        String productName = productsModelList.get(position).getProductName();

        Glide.with(context).load(productImage).apply(ImageDialog.getRequestOption())
                .into(viewHolder.ivProductList);
        viewHolder.tvProductList.setText(productName);

        viewHolder.recyclerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(productLink)));

            }
        });


    }


    @Override
    public int getItemCount() {
        return productsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_productList)
        ImageView ivProductList;
        @BindView(R.id.tv_productList)
        TextView tvProductList;
        @BindView(R.id.rl_recyclerContainer)
        RelativeLayout recyclerContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
