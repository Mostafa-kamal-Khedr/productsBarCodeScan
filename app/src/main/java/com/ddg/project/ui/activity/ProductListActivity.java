package com.ddg.project.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ddg.project.R;
import com.ddg.project.adapter.ProductListAdapter;
import com.ddg.project.db.Products;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends AppCompatActivity {

    @BindView(R.id.rcy_products)
    RecyclerView rcyProducts;
    private ProductListAdapter productListAdapter;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        inflateLayOut();
        setRecycler();

    }

    private void inflateLayOut() {
        ButterKnife.bind(this);
        category = getIntent().getStringExtra("category");
        Toolbar customToolbar = findViewById(R.id.custom_toolbar);

        setSupportActionBar(customToolbar);
        TextView appBarTitle = findViewById(R.id.appbar_title);

        appBarTitle.setText(category);
    }

    private void setRecycler() {
        Products products = new Products();
        rcyProducts.setLayoutManager(new GridLayoutManager(ProductListActivity.this, 2));


        if (category.equals("Makeup")) {
            productListAdapter = new ProductListAdapter(products.getMakeupList(), ProductListActivity.this);

        } else if (category.equals("Perfume")) {
            productListAdapter = new ProductListAdapter(products.getPerfumeList(), ProductListActivity.this);

        } else if (category.equals("Skin Care")) {
            productListAdapter = new ProductListAdapter(products.getMakeupList(), ProductListActivity.this);

        } else if (category.equals("Hair")) {
            productListAdapter = new ProductListAdapter(products.getMakeupList(), ProductListActivity.this);

        }
        rcyProducts.setAdapter(productListAdapter);

    }
}
