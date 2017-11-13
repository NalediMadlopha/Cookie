package app.cookie.app._new_architecture.view.recipeslist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cookie.app.R;

import java.util.List;

import app.cookie.app._new_architecture.model.Recipe;
import app.cookie.app._new_architecture.view.recipedetails.RecipeDetailsActivity;

import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_NAME;


public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeCardViewHolder> {

    private final List<Recipe> recipeList;
    private Context context;

    RecipeListAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @Override
    public RecipeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);
        return new RecipeCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeCardViewHolder holder, int position) {
        holder.recipe = recipeList.get(position);
        holder.recipeNameTextView.setText(holder.recipe.getName());

        int drawableResourceId;
        if (TextUtils.isEmpty(holder.recipe.getImage())) {
            switch (holder.recipe.getId()) {
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
                    .load(holder.recipe.getImage())
                    .into(holder.recipeImageView);
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Recipe recipe;
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
            Intent intent = new Intent(view.getContext(), RecipeDetailsActivity.class);
            intent.putExtra(RECIPE_ID, recipe.getId());
            intent.putExtra(RECIPE_NAME, recipe.getName());
            view.getContext().startActivity(intent);
        }
    }
}
