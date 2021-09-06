package com.mobdeve.s11.group41.alienimpact;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class PetShopViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivPicture;
    private TextView tvName;
    private TextView tvEffect;
    private ImageButton ibPetBuy;
    private Button btnEquip;

    public PetShopViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        this.ivPicture = itemView.findViewById(R.id.ivPetIcon);
        this.tvName = itemView.findViewById(R.id.tvPetName);
        this.tvEffect = itemView.findViewById(R.id.tvPetEffect);
        this.ibPetBuy = itemView.findViewById(R.id.ibBuyPet);
        this.btnEquip = itemView.findViewById(R.id.btnPetEquip);
    }

    public void setIvPicture (int pic) {this.ivPicture.setImageResource(pic);}
    public void setTvName (String name) {this.tvName.setText(name);}
    public void setTvEffect (String effect, int cost) {
        String txt = effect + "; COST: " + Integer.toString(cost);
        this.tvEffect.setText(txt);
    }
    public ImageButton getIbPetBuy () {return this.ibPetBuy;}
    public void setBtnEquip (String text) {this.btnEquip.setText(text);}
    public Button getBtnEquip () {return this.btnEquip;}
}
