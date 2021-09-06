package com.mobdeve.s11.group41.alienimpact;

import android.content.Context;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PetShopAdapter extends RecyclerView.Adapter<PetShopViewHolder> {

    private ArrayList<PetModel> pets;
    private MyDatabaseHelper myDB;
    private Context context;

    public PetShopAdapter(ArrayList<PetModel> pets, MyDatabaseHelper myDB, Context context) {
        this.pets = pets;
        this.myDB = myDB;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public PetShopViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View iv = inf.inflate(R.layout.shop_pets_template, parent, false);
        PetShopViewHolder vh = new PetShopViewHolder(iv);

        //equip button functionality for each pet
        vh.getBtnEquip().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pet_str = pets.get(vh.getBindingAdapterPosition()).getName();
                int pet_var = 0;
                switch (pet_str) {
                    case "Cat":
                        pet_var = 0;
                        break;
                    case "Monkey":
                        pet_var = 1;
                        break;
                    case "Dog":
                        pet_var = 2;
                        break;
                    default:
                        pet_var = 3;
                        break;
                }
                myDB.updatePetUsed(pet_var);
                Toast.makeText(context, "Pet set to " + pet_str + ".", Toast.LENGTH_SHORT).show();
                PetShopAdapter.this.notifyDataSetChanged();
            }
        });


        //buy button functionality for each pet
        vh.getIbPetBuy().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pet_str = pets.get(vh.getBindingAdapterPosition()).getName();
                int pet_cost = pets.get(vh.getBindingAdapterPosition()).getCost();
                switch (pet_str) {
                    case "Cat":
                        if (myDB.getPet0Bought() == 1) {
                            Toast.makeText(context, pet_str + " already bought.", Toast.LENGTH_SHORT).show();
                        } else if (myDB.getScrap() >=  pet_cost) {
                            myDB.updatePet0Bought();
                            if (myDB.getPet0Bought() == 1) {
                                myDB.removeScrap(pet_cost);
                                Toast.makeText(context, pet_str + " purchased for " + pet_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(context, "Error occurred during purchase.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "Monkey":
                        if (myDB.getPet1Bought() == 1) {
                            Toast.makeText(context, pet_str + " already bought.", Toast.LENGTH_SHORT).show();
                        } else if (myDB.getScrap() >=  pet_cost) {
                            myDB.updatePet1Bought();
                            if (myDB.getPet1Bought() == 1) {
                                myDB.removeScrap(pet_cost);
                                Toast.makeText(context, pet_str + " purchased for " + pet_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(context, "Error occurred during purchase.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "Dog":
                        if (myDB.getPet2Bought() == 1) {
                            Toast.makeText(context, pet_str + " already bought.", Toast.LENGTH_SHORT).show();
                        } else if (myDB.getScrap() >=  pet_cost) {
                            myDB.updatePet2Bought();
                            if (myDB.getPet2Bought() == 1) {
                                myDB.removeScrap(pet_cost);
                                Toast.makeText(context, pet_str + " purchased for " + pet_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(context, "Error occurred during purchase.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        if (myDB.getPet3Bought() == 1) {
                            Toast.makeText(context, pet_str + " already bought.", Toast.LENGTH_SHORT).show();
                        } else if (myDB.getScrap() >=  pet_cost) {
                            myDB.updatePet3Bought();
                            if (myDB.getPet3Bought() == 1) {
                                myDB.removeScrap(pet_cost);
                                Toast.makeText(context, pet_str + " purchased for " + pet_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(context, "Error occurred during purchase.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                PetShopAdapter.this.notifyDataSetChanged();
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PetShopViewHolder holder, int position) {
        holder.setIvPicture(pets.get(position).getImage());
        holder.setTvEffect(pets.get(position).getEffect(), pets.get(position).getCost());
        String pet_str = pets.get(position).getName();
        holder.setTvName(pet_str);
        holder.setBtnEquip("EQUIP");

        int pet_used = myDB.getPetUsed();
        //sets visibility of equip buttons for each pet
        switch (pet_str) {
            case "Cat":
                if (myDB.getPet0Bought() == 1) {
                    holder.getBtnEquip().setVisibility(View.VISIBLE);
                    if (pet_used == 0)
                        holder.setBtnEquip("EQUIPPED");
                }
                break;
            case "Monkey":
                if (myDB.getPet1Bought() == 1) {
                    holder.getBtnEquip().setVisibility(View.VISIBLE);
                    if (pet_used == 1)
                        holder.setBtnEquip("EQUIPPED");
                }
                break;
            case "Dog":
                if (myDB.getPet2Bought() == 1) {
                    holder.getBtnEquip().setVisibility(View.VISIBLE);
                    if (pet_used == 2)
                        holder.setBtnEquip("EQUIPPED");
                }
                break;
            default:
                if (myDB.getPet3Bought() == 1) {
                    holder.getBtnEquip().setVisibility(View.VISIBLE);
                    if (pet_used == 3)
                        holder.setBtnEquip("EQUIPPED");
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.pets.size();
    }
}
