package com.cvd.purchaserequestsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

public class PrliAdapter extends RecyclerView.Adapter<PrliAdapter.PrliHolder> {

    private Context mContext;
    private List<Prli> prliList;


    public PrliAdapter(Context context, List<Prli> prliList) {
        this.mContext = context;
        this.prliList = prliList;
    }

    @NonNull
    @Override
    public PrliHolder onCreateViewHolder(@NonNull ViewGroup detail, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.detail_list, detail, false);
        return new PrliHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrliHolder holder, int position) {
        Prli pr = prliList.get(position);


        String name = pr.getProduct();
        int quantity = pr.getQuantity();
        double price = pr.getPrice();
        double lineTotal = pr.getLineTotal();
        String formattedPrice = NumberFormat.getCurrencyInstance().format(price);
        String formattedLineTotal = NumberFormat.getCurrencyInstance().format(lineTotal);


        holder.textViewProduct.setText("Product: " + name);
        holder.textViewPrice.setText("Price: " + formattedPrice);
        holder.textViewQuantity.setText("Quantity: " + quantity);
        holder.textViewLineTotal.setText("Line Total: " + formattedLineTotal);
    }

    @Override
    public int getItemCount() {
        return prliList.size();
    }

    class PrliHolder extends RecyclerView.ViewHolder {

        TextView textViewProduct, textViewPrice, textViewQuantity, textViewLineTotal;

        public PrliHolder(View itemView) {
            super(itemView);
            textViewProduct = itemView.findViewById(R.id.Product);
            textViewPrice = itemView.findViewById(R.id.Price);
            textViewQuantity = itemView.findViewById(R.id.Quantity);
            textViewLineTotal = itemView.findViewById(R.id.LineTotal);

        }

    }
}