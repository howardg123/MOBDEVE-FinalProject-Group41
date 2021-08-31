package com.mobdeve.s11.group41.alienimpact;

import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class PetShopViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivPicture;
    private TextView tvName;
    private TextView tvEffect;

    public PetShopViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        this.ivPicture = itemView.findViewById(R.id.ivPetIcon);
        this.tvName = itemView.findViewById(R.id.tvPetName);
        this.tvEffect = itemView.findViewById(R.id.tvPetEffect);
    }

    public void setIvPicture (int pic) {this.ivPicture.setImageResource(pic);}
    public void setTvName (String name) {this.tvName.setText(name);}
    public void setTvEffect (String effect, int cost) {
        String txt = effect + "; COST: " + Integer.toString(cost);
        this.tvEffect.setText(txt);
    }
}
