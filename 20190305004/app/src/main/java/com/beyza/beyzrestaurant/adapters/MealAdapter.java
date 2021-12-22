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
import com.beyza.beyzrestaurant.databases.BasketDao;
import com.beyza.beyzrestaurant.databases.DatabaseHelper2;
import com.beyza.beyzrestaurant.models.Meal;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.CardViewDesignThingsHolder>{
    private Context context;
    private ArrayList<Meal> mealArrayList = new ArrayList<>();
    public static ArrayList<String> inMyBasketArrayList = new ArrayList<>();
    private DatabaseHelper2 databaseHelper2 = null;
    private BasketDao basketDao = new BasketDao();


    public MealAdapter(Context context, ArrayList<Meal> mealArrayList) {
        this.context = context;
        this.mealArrayList = mealArrayList;
    }

    @NonNull
    @Override
    public CardViewDesignThingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_meal, parent, false);
        return new CardViewDesignThingsHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardViewDesignThingsHolder holder, int position) {
        Meal meal = mealArrayList.get(position);

        holder.textViewMealName.setText(meal.getMealname());
        holder.textViewMealPrice.setText(" ("+meal.getPrice()+") ₺");

        holder.imageViewMealAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                databaseHelper2 = new DatabaseHelper2(context);

                basketDao.addBasket(databaseHelper2, holder.textViewMealName.getText().toString(), meal.getPrice());

                notifyDataSetChanged();

                Snackbar.make(view, "1x "+holder.textViewMealName.getText().toString()+" has been succesfully added to your basket.", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(context.getResources().getColor(R.color.white))
                        .setTextColor(context.getResources().getColor(R.color.background))
                        .show();

            }
        });

        holder.imageViewMealRemove.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return mealArrayList.size();
    }

    public static class CardViewDesignThingsHolder extends RecyclerView.ViewHolder {
        private CardView cardViewMeal = null;
        private TextView textViewMealName, textViewMealPrice = null;
        private ImageView imageViewMealAdd, imageViewMealRemove = null;

        public CardViewDesignThingsHolder(@NonNull View itemView) {
            super(itemView);
            cardViewMeal = itemView.findViewById(R.id.cardViewMeal);
            textViewMealName = itemView.findViewById(R.id.textViewMealName);
            textViewMealPrice = itemView.findViewById(R.id.textViewMealPrice);
            imageViewMealAdd = itemView.findViewById(R.id.imageViewMealAdd);
            imageViewMealRemove = itemView.findViewById(R.id.imageViewMealRemove);
        }
    }

}
