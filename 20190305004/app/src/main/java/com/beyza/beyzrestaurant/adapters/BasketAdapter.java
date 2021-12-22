package com.beyza.beyzrestaurant.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.beyza.beyzrestaurant.R;
import com.beyza.beyzrestaurant.activities.BasketActivity;
import com.beyza.beyzrestaurant.databases.BasketDao;
import com.beyza.beyzrestaurant.databases.DatabaseHelper2;
import com.beyza.beyzrestaurant.models.Basket;
import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.CardViewDesignThingsHolder>{
    private Context context;
    private ArrayList<Basket> basketArrayList = new ArrayList<>();
    private DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(context);

    public BasketAdapter(Context context, ArrayList<Basket> basketArrayList) {
        this.context = context;
        this.basketArrayList = basketArrayList;
    }

    @NonNull
    @Override
    public CardViewDesignThingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_meal, parent, false);
        return new CardViewDesignThingsHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardViewDesignThingsHolder holder, @SuppressLint("RecyclerView") int position) {
        Basket basket = basketArrayList.get(position);

        holder.textViewMealAmount.setText("1x ");
        holder.textViewMealName.setText(basket.getMealname());

        holder.imageViewMealAdd.setVisibility(View.INVISIBLE);

        holder.imageViewMealRemove.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                databaseHelper2 = new DatabaseHelper2(context);
                new BasketDao().deleteMealFromBasket(databaseHelper2, basket.getId());

                basketArrayList.remove(basket);
                BasketActivity.totalprice-=basket.getPrice();
                BasketActivity.textViewBasketPrice.setText(String.valueOf(BasketActivity.totalprice));
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return basketArrayList.size();
    }

    public static class CardViewDesignThingsHolder extends RecyclerView.ViewHolder {
        private CardView cardViewMeal = null;
        private TextView textViewMealName, textViewMealAmount = null;
        private ImageView imageViewMealAdd, imageViewMealRemove = null;

        public CardViewDesignThingsHolder(@NonNull View itemView) {
            super(itemView);
            cardViewMeal = itemView.findViewById(R.id.cardViewMeal);
            textViewMealName = itemView.findViewById(R.id.textViewMealName);
            textViewMealAmount = itemView.findViewById(R.id.textViewMealAmount);
            imageViewMealAdd = itemView.findViewById(R.id.imageViewMealAdd);
            imageViewMealRemove = itemView.findViewById(R.id.imageViewMealRemove);
        }
    }
}
