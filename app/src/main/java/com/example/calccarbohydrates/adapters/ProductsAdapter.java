package com.example.calccarbohydrates.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.model.Products;

import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Context mContext;
    private List<Products> products;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public ProductsAdapter(List<Products> products, Context context) {
        this.products = products;
        this.mContext = context;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       holder.name.setText(String.format(mContext.getString(R.string.product) + " " + products.get(position).getName() + " " + mContext.getString(R.string.have) + " "));
       holder.carbohydrates.setText(String.format(products.get(position).getCarbohydrates() + " " + mContext.getString(R.string.carbohydrate)));
    }

    @Override
    public int getItemCount() {
        if(products != null){
            return products.size();
        }
        return 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView carbohydrates;

        private   ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_product);
            carbohydrates = (TextView) itemView.findViewById(R.id.name_carbohydrates);
//            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

//    public void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
