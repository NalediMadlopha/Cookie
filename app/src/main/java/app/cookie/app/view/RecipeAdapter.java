package app.cookie.app.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cookie.app.R;

import java.util.List;

import app.cookie.app.model.Recipe;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeCardViewHolder> {

    private final List<Recipe> recipeList;
    private Context context;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    class RecipeCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView recipeImageView;
        TextView recipeNameTextView;

        RecipeCardViewHolder(View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipe_name_text_view);
            recipeImageView = itemView.findViewById(R.id.recipe_image_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public RecipeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);

        return new RecipeCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeCardViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

        holder.recipeNameTextView.setText(recipe.getName());

        int drawableResourceId;
        if (TextUtils.isEmpty(recipe.getImage())) {
            switch (recipe.getId()) {
                case 1:
                    drawableResourceId = R.drawable.nutella_pie;
                    break;
                case 2:
                    drawableResourceId = R.drawable.brownies;
                    break;
                case 3:
                    drawableResourceId = R.drawable.yellow_cake;
                    break;
                case 4:
                    drawableResourceId = R.drawable.cheese_cake;
                    break;
                default:
                    drawableResourceId = R.drawable.default_card_background;
            }

            Glide.with(context)
                    .load(drawableResourceId)
                    .into(holder.recipeImageView);
        } else {
            // TODO: 10/12/17 Convert the recipe image string to a drawable
            Glide.with(context)
                    .load(recipe.getImage())
                    .into(holder.recipeImageView);
        }


    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
