package com.mobdeve.s11.group41.alienimpact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopViewHolder> {

    private ArrayList<WeaponModel> weapons;
    private ArrayList<BuffModel> buffs;
    private String mode;
    private MyDatabaseHelper myDB;
    private Context context;

    public ShopAdapter(ArrayList<WeaponModel> model, MyDatabaseHelper myDB, Context context, int ignore) {
        this.weapons = model;
        this.mode="weapon";
        this.myDB = myDB;
        this.context = context;
    }
    public ShopAdapter(ArrayList<BuffModel> model, MyDatabaseHelper myDB, Context context) {
        this.buffs = model;
        this.mode="buff";
        this.myDB = myDB;
        this.context = context;
    }


    @NonNull
    @NotNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View iv = inf.inflate(R.layout.shop_items_template, parent, false);
        ShopViewHolder vh = new ShopViewHolder(iv);

        if (mode.equals("weapon")) {
            //buy one button functionality for weapons
            vh.getIbBuyOne().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item_name = weapons.get(vh.getBindingAdapterPosition()).getBaseName();
                    int item_level = 0;
                    int item_cost = 0;
                    switch (item_name) {
                        case "Fists":
                            item_level = myDB.getWeapon0();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getCurrentCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon0(1);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 1 hand-type weapon for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Stick":
                            item_level = myDB.getWeapon1();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getCurrentCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon1(1);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 1 stick-type weapon for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Blowgun":
                            item_level = myDB.getWeapon2();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getCurrentCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon2(1);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 1 projectile-type weapon for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Trebuchet":
                            item_level = myDB.getWeapon3();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getCurrentCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon3(1);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 1 artillery-type weapon for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            item_level = myDB.getWeapon4();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getCurrentCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon4(1);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 1 vehicle-type weapon for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                    ShopAdapter.this.notifyItemChanged(vh.getBindingAdapterPosition());
                }
            });

            //buy ten button functionality for weapons
            vh.getIbBuyTen().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item_name = weapons.get(vh.getBindingAdapterPosition()).getBaseName();
                    int item_level = 0;
                    int item_cost = 0;
                    switch (item_name) {
                        case "Fists":
                            item_level = myDB.getWeapon0();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getBuyTenCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon0(10);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 10 hand-type weapons for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Stick":
                            item_level = myDB.getWeapon1();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getBuyTenCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon1(10);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 10 stick-type weapons for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Blowgun":
                            item_level = myDB.getWeapon2();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getBuyTenCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon2(10);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 10 projectile-type weapons for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Trebuchet":
                            item_level = myDB.getWeapon3();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getBuyTenCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon3(10);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 10 artillery-type weapons for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            item_level = myDB.getWeapon4();
                            item_cost = weapons.get(vh.getBindingAdapterPosition()).getBuyTenCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateWeapon4(10);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 10 vehicle-type weapon for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                    ShopAdapter.this.notifyItemChanged(vh.getBindingAdapterPosition());
                }
            });
        } else {
            //buy one button functionality for buffs
            vh.getIbBuyOne().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item_name = buffs.get(vh.getBindingAdapterPosition()).getName();
                    int item_level = 0;
                    int item_cost = 0;
                    switch (item_name) {
                        case "Tap":
                            item_level = myDB.getTapLevel();
                            item_cost = buffs.get(vh.getBindingAdapterPosition()).getCurrentCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateTapLevel(1);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 1 tap level for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Hold":
                            item_level = myDB.getHoldLevel();
                            item_cost = buffs.get(vh.getBindingAdapterPosition()).getCurrentCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateHoldLevel(1);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 1 hold level for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            item_level = myDB.getSwipeLevel();
                            item_cost = buffs.get(vh.getBindingAdapterPosition()).getCurrentCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateSwipeLevel(1);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 1 swipe level for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                    ShopAdapter.this.notifyItemChanged(vh.getBindingAdapterPosition());
                }
            });

            //buy ten button functionality for weapons
            vh.getIbBuyTen().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item_name = buffs.get(vh.getBindingAdapterPosition()).getName();
                    int item_level = 0;
                    int item_cost = 0;
                    switch (item_name) {
                        case "Tap":
                            item_level = myDB.getTapLevel();
                            item_cost = buffs.get(vh.getBindingAdapterPosition()).getBuyTenCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateTapLevel(10);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 10 tap levels for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Hold":
                            item_level = myDB.getHoldLevel();
                            item_cost = buffs.get(vh.getBindingAdapterPosition()).getBuyTenCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateHoldLevel(10);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 10 hold levels for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            item_level = myDB.getSwipeLevel();
                            item_cost = buffs.get(vh.getBindingAdapterPosition()).getBuyTenCost(item_level);
                            if (myDB.getScrap() >= item_cost) {
                                myDB.updateSwipeLevel(10);
                                myDB.removeScrap(item_cost);
                                myDB.updateScrapSpent(item_cost);
                                Toast.makeText(context, "Purchased 10 swipe levels for " + item_cost + " scrap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Not enough scrap.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                    ShopAdapter.this.notifyItemChanged(vh.getBindingAdapterPosition());
                }
            });
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ShopViewHolder holder, int position) {
        if (mode.equals("weapon")) {
            int wep_level = 0;
            switch (weapons.get(position).getBaseName()) {
                case "Fists":
                    wep_level = myDB.getWeapon0();
                    break;
                case "Stick":
                    wep_level = myDB.getWeapon1();
                    break;
                case "Blowgun":
                    wep_level = myDB.getWeapon2();
                    break;
                case "Trebuchet":
                    wep_level = myDB.getWeapon3();
                    break;
                default:
                    wep_level = myDB.getWeapon4();
                    break;
            }
            holder.setIvPicture(weapons.get(position).getCurrentImage(wep_level));
            holder.setTvQuantity(wep_level);
            holder.setTvOnePrice(weapons.get(position).getCurrentCost(wep_level));
            holder.setTvTenPrice(weapons.get(position).getBuyTenCost(wep_level));
            holder.setTvName(weapons.get(position).getCurrentName(wep_level));
        } else {
            int buff_level = 0;
            switch (buffs.get(position).getName()) {
                case "Tap":
                    buff_level = myDB.getTapLevel();
                    break;
                case "Hold":
                    buff_level = myDB.getHoldLevel();
                    break;
                default:
                    buff_level = myDB.getSwipeLevel();
                    break;
            }
            holder.setIvPicture(buffs.get(position).getImage());
            holder.setTvQuantity(buff_level);
            holder.setTvOnePrice(buffs.get(position).getCurrentCost(buff_level));
            holder.setTvTenPrice(buffs.get(position).getBuyTenCost(buff_level));
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
