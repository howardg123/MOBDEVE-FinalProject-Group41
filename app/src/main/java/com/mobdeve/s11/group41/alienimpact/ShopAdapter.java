package com.mobdeve.s11.group41.alienimpact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopViewHolder> {

    private ArrayList<WeaponModel> weapons;
    private ArrayList<BuffModel> buffs;
    private String mode;

    public ShopAdapter(ArrayList<WeaponModel> model, int ignore) {this.weapons = model; this.mode="weapon";}
    public ShopAdapter(ArrayList<BuffModel> model) {this.buffs = model; this.mode="buff";}


    @NonNull
    @NotNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View iv = inf.inflate(R.layout.shop_items_template, parent, false);
        ShopViewHolder vh = new ShopViewHolder(iv);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ShopViewHolder holder, int position) {
        if (mode.equals("weapon")) {
            holder.setIvPicture(weapons.get(position).getCurrentImage());
            holder.setTvQuantity(weapons.get(position).getAmount());
            holder.setTvOnePrice(weapons.get(position).getCost()); //to be edited to match scaling
            holder.setTvTenPrice(weapons.get(position).getCost() * 10); //to be edited to match scaling
            holder.setTvName(weapons.get(position).getCurrentName());
        } else {
            holder.setIvPicture(buffs.get(position).getImage());
            holder.setTvQuantity(buffs.get(position).getAmount());
            holder.setTvOnePrice(buffs.get(position).getCost()); //to be edited to match scaling
            holder.setTvTenPrice(buffs.get(position).getCost() * 10); //to be edited to match scaling
            holder.setTvName(buffs.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        if (mode.equals("weapon"))
            return this.weapons.size();
        else
            return this.buffs.size();
    }
}
