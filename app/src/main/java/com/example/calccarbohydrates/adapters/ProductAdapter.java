package com.example.calccarbohydrates.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.calccarbohydrates.R;
import com.example.calccarbohydrates.model.Product;
import com.example.calccarbohydrates.ui.products.ProductViewModel;
import com.example.calccarbohydrates.ui.products.UpdateProductFragment;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context mContext;
    private List<Product> mProducts;
    private ItemClickListener mClickListener;
    private ProductViewModel mProductsViewModel;

    public ProductAdapter(List<Product> products, Context context) {
        this.mProducts = products;
        this.mContext = context;
    }

    public void setProducts(List<Product> products, ProductViewModel productsViewModel) {
        this.mProducts = products;
        this.mProductsViewModel = productsViewModel;
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
       holder.name.setText(String.format(" " + mProducts.get(position).getName() + " " + mContext.getString(R.string.have) + " "));
       holder.carbohydrates.setText(String.format(mProducts.get(position).getCarbohydrates() + " " + mContext.getString(R.string.carbohydrate)));

       holder.edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Product product = new Product(mProducts.get(position).getId(),mProducts.get(position).getName(),mProducts.get(position).getCarbohydrates());

               AppCompatActivity activity = (AppCompatActivity) v.getContext();
               activity.getSupportFragmentManager()
                       .beginTransaction().replace(R.id.fragment_container, UpdateProductFragment.newInstance(product))
                       .addToBackStack(null)
                       .commit();
           }
       });
       holder.delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mProductsViewModel.delete(mProducts.get(holder.getAdapterPosition()));
               int position = holder.getAdapterPosition();
               mProducts.remove(position);
               notifyItemRemoved(position);
               notifyItemRangeChanged(position,mProducts.size());
           }
       });
    }

    @Override
    public int getItemCount() {
        if(mProducts != null){
            return mProducts.size();
        }
        return 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView carbohydrates;
        ImageView edit, delete;

        private   ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_product);
            carbohydrates = (TextView) itemView.findViewById(R.id.name_carbohydrates);
            edit = (ImageView) itemView.findViewById(R.id.imageView_edit);
            delete = (ImageView) itemView.findViewById(R.id.imageView_delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setFilter(List<Product> products){
        mProducts = new ArrayList<>();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
