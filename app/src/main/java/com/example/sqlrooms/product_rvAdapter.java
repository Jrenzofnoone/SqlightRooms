package com.example.sqlrooms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlrooms.db.Product;

import java.util.ArrayList;
import java.util.List;

public class product_rvAdapter extends RecyclerView.Adapter<product_rvAdapter.ViewHolder> {

    private List<Product> products = new ArrayList<>();

    public void setProducts(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public product_rvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull product_rvAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.tv_name.setText(product.getProductName());
        holder.tv_price.setText(String.valueOf(product.getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
