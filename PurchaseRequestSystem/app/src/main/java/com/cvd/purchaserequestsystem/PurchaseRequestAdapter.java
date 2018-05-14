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

public class PurchaseRequestAdapter extends RecyclerView.Adapter<PurchaseRequestAdapter.PurchaseViewHolder> {

    private Context mContext;
    private List<PurchaseRequest> prList;
    private OnClickListener listener;

    public interface OnClickListener {
        void onItemCLick(int position);
    }
    public  void setOnClickListener(OnClickListener listen){
        listener = listen;
    }

    public PurchaseRequestAdapter(Context context, List<PurchaseRequest> prList) {
        this.mContext = context;
        this.prList = prList;
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_layout,parent,false);
         return new PurchaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
        PurchaseRequest pr = prList.get(position);

        String Description = pr.getDescription();
        String Justification = pr.getJustification();
        String Status = pr.getStatus();
        double Total = pr.getTotal();
        String UserName = pr.getUserName();
        String FirstName = pr.getFirstName();
        String LastName = pr.getLastName();
        String PhoneNumber = pr.getPhoneNumber();
        String Email = pr.getEmail();
        String formattedTotal = NumberFormat.getCurrencyInstance().format(Total);



        holder.textViewTotal.setText("Total: " + formattedTotal);
        holder.textViewStatus.setText("Status: " + Status);
        holder.textViewJustification.setText("Justification: " +Justification);
        holder.textViewDescription.setText("Description: " +Description);
        holder.textViewUserName.setText("User Name: " +UserName);
        holder.textViewFirstName.setText("First Name: " +FirstName);
        holder.textViewLastName.setText("Last Name: " +LastName);
        holder.textViewPhoneNumber.setText("Phone Number: " +PhoneNumber);
        holder.textViewEmail.setText("Email: " +Email);


    }

    @Override
    public int getItemCount() {
        return prList.size();
    }

    class PurchaseViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDescription, textViewJustification, textViewStatus, textViewTotal, textViewUserName,textViewFirstName,textViewLastName,textViewPhoneNumber,textViewEmail;

        public PurchaseViewHolder(View itemView) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.description);
            textViewJustification = itemView.findViewById(R.id.justification);
            textViewStatus = itemView.findViewById(R.id.status);
            textViewTotal = itemView.findViewById(R.id.total);
            textViewUserName = itemView.findViewById(R.id.UserName);
            textViewFirstName = itemView.findViewById(R.id.FirstName);
            textViewLastName = itemView.findViewById(R.id.LastName);
            textViewPhoneNumber = itemView.findViewById(R.id.PhoneNumber);
            textViewEmail = itemView.findViewById(R.id.Email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemCLick(position);
                        }
                    }
                }
            });

        }
    }
}
