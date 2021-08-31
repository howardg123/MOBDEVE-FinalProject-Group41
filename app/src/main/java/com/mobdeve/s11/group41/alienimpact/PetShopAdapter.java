package com.mobdeve.s11.group41.alienimpact;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PetShopAdapter extends RecyclerView.Adapter<PetShopViewHolder> {

    private ArrayList<PetModel> pets;

    public PetShopAdapter(ArrayList<PetModel> pets) {
        this.pets = pets;
    }

    @NonNull
    @NotNull
    @Override
    public PetShopViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View iv = inf.inflate(R.layout.shop_pets_template, parent, false);
        PetShopViewHolder vh = new PetShopViewHolder(iv);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PetShopViewHolder holder, int position) {
        holder.setIvPicture(pets.get(position).getImage());
        holder.setTvEffect(pets.get(position).getEffect(), pets.get(position).getCost());
        holder.setTvName(pets.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.pets.size();
    }
}
