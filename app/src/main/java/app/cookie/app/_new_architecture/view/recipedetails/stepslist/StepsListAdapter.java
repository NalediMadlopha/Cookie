package app.cookie.app._new_architecture.view.recipedetails.stepslist;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cookie.app.R;

import java.util.List;

import app.cookie.app._new_architecture.model.Step;
import app.cookie.app._new_architecture.view.recipedetails.RecipeDetailsActivity;
import app.cookie.app._new_architecture.view.stepdetails.StepDetailsActivity;
import app.cookie.app._new_architecture.view.stepdetails.StepDetailsFragment;

import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_NAME;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.STEP_ID;

class StepsListAdapter extends RecyclerView.Adapter<StepsListAdapter.StepViewHolder> {

    private final int recipeId;
    private final String recipeName;
    private final List<Step> steps;

    StepsListAdapter(int recipeId, String recipeName, List<Step> steps) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.steps = steps;
    }

    @Override
    public StepsListAdapter.StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsListAdapter.StepViewHolder holder, int position) {
        holder.step = steps.get(position);
        holder.stepTextView.setText(steps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Step step;
        TextView stepTextView;

        StepViewHolder(View itemView) {
            super(itemView);
            stepTextView = itemView.findViewById(R.id.step_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            boolean isTablet = context.getResources().getBoolean(R.bool.isTablet);

            if (!isTablet) {
                Intent intent = new Intent(context, StepDetailsActivity.class);
                intent.putExtra(RECIPE_ID, recipeId);
                intent.putExtra(RECIPE_NAME, recipeName);
                intent.putExtra(STEP_ID, step.getId());
                context.startActivity(intent);
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt(STEP_ID, step.getId());

                StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
                stepDetailsFragment.setArguments(bundle);

                FragmentManager fragmentManager = ((RecipeDetailsActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.step_details_container, stepDetailsFragment)
                        .commit();
            }

        }
    }
}
