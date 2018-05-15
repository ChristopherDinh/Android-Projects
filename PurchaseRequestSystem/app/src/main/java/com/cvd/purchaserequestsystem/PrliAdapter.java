package com.cvd.purchaserequestsystem;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class PrliAdapter extends RecyclerView.Adapter<PrliAdapter.PrliHolder> {

    private Context mContext;
    private ArrayList<Prli> prliList;
    private ArrayList<Prli> prliList2;
    public static int primaryId;

    public PrliAdapter(Context context, ArrayList<Prli> prliList) {
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
        int id = pr.getPrliID();



        String formattedPrice = NumberFormat.getCurrencyInstance().format(price);
        String formattedLineTotal = NumberFormat.getCurrencyInstance().format(lineTotal);

        if(prliList.size() != 0) {
            holder.textViewProduct.setText("Product: " + prliList.get(position).getProduct());
            holder.textViewPrice.setText("Price: " + formattedPrice);
            holder.textViewQuantity.setText("Quantity: " + quantity);
            holder.textViewLineTotal.setText("Line Total: " + formattedLineTotal);
            if (id == primaryId) {
                holder.layout.setVisibility(View.VISIBLE);
                holder.textViewProduct.setVisibility(View.VISIBLE);
                holder.textViewPrice.setVisibility(View.VISIBLE);
                holder.textViewQuantity.setVisibility(View.VISIBLE);
                holder.textViewLineTotal.setVisibility(View.VISIBLE);
            } else {
                holder.layout.setVisibility(View.GONE);
                holder.textViewProduct.setVisibility(View.INVISIBLE);
                holder.textViewPrice.setVisibility(View.INVISIBLE);
                holder.textViewQuantity.setVisibility(View.INVISIBLE);
                holder.textViewLineTotal.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return prliList.size();
    }

    class PrliHolder extends RecyclerView.ViewHolder {

        TextView textViewProduct, textViewPrice, textViewQuantity, textViewLineTotal;
        CardView layout;
        public PrliHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.card_view);
            textViewProduct = itemView.findViewById(R.id.Product);
            textViewPrice = itemView.findViewById(R.id.Price);
            textViewQuantity = itemView.findViewById(R.id.Quantity);
            textViewLineTotal = itemView.findViewById(R.id.LineTotal);

        }

    }

}