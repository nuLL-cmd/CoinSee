package com.automatodev.coinSee.view.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.automatodev.coinSee.R;
import com.automatodev.coinSee.controller.entity.CoinChildr;
import com.automatodev.coinSee.controller.service.ConvertDataService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.DataHandler> {
    private Activity context;
    private List<CoinChildr> coinChildrs;
    private OnItemClickListener listener;
    private ConvertDataService convertDataService;

    public void setCoinChildrs(List<CoinChildr> coinChildrs) {
        this.coinChildrs = coinChildrs;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onFavItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CoinAdapter(List<CoinChildr> coinChildrs, Activity context) {
        this.context = context;
        this.coinChildrs = coinChildrs;
        this.convertDataService = new ConvertDataService();
    }

    @NonNull
    @Override
    public CoinAdapter.DataHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_celula_main, parent, false);
        return new DataHandler(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinAdapter.DataHandler holder, int position) {
        CoinChildr coinChildr = coinChildrs.get(position);
        holder.txtCodeIn_layout.setText(coinChildr.getCodein());
        holder.txtCode_layout.setText(coinChildr.getCode());
        holder.txtName_layout.setText(coinChildr.getName());
        if (coinChildr.isFav())
            holder.imgFav_layout.setImageResource(R.drawable.ic_favorite_red_24dp);
        else
            holder.imgFav_layout.setImageResource(R.drawable.ic_favorite_border_32dp);
        holder.txtHigh_layout.setText(coinChildr.getHigh());
        holder.txtLow_layout.setText(coinChildr.getLow());
        holder.txtCoinValue_layout.setText(convertDataService.convertDecimal(coinChildr.getBid()));
        holder.txtDate_layout.setText(convertDataService.convertDate(coinChildr.getTimestamp().substring(0, 10)));
        holder.txtPercent_layout.setText(coinChildr.getPctChange() + "%");
        if (Double.parseDouble(coinChildr.getPctChange()) < 0)
            holder.txtPercent_layout.setTextColor(Color.RED);
        else holder.txtPercent_layout.setTextColor(Color.parseColor("#43A047"));
        Glide.with(context).load(coinChildr.getUlrPhoto()).transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imgCode_layout);
    }

    @Override
    public int getItemCount() {
        return coinChildrs.size();
    }

    public class DataHandler extends RecyclerView.ViewHolder {
        private TextView txtName_layout;
        private TextView txtCode_layout;
        private TextView txtCodeIn_layout;
        private ImageView imgFav_layout;
        private ImageView imgCode_layout;
        private TextView txtHigh_layout;
        private TextView txtLow_layout;
        private TextView txtCoinValue_layout;
        private TextView txtDate_layout;
        private TextView txtPercent_layout;

        public DataHandler(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            txtName_layout = itemView.findViewById(R.id.txtName_layout);
            txtCode_layout = itemView.findViewById(R.id.txtCode_layout);
            txtCodeIn_layout = itemView.findViewById(R.id.txtCodeIn_layout);
            imgFav_layout = itemView.findViewById(R.id.imgFav_layout);
            imgCode_layout = itemView.findViewById(R.id.imgCode_layout);
            txtHigh_layout = itemView.findViewById(R.id.txtHigh_layout);
            txtLow_layout = itemView.findViewById(R.id.txtLow_layout);
            txtCoinValue_layout = itemView.findViewById(R.id.txtCoinValue_layout);
            txtDate_layout = itemView.findViewById(R.id.txtDate_layout);
            txtPercent_layout = itemView.findViewById(R.id.txtPercent_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            listener.onItemClick(position);
                    }
                }
            });
            imgFav_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            listener.onFavItemClick(position);
                    }
                }
            });
        }
    }
}
