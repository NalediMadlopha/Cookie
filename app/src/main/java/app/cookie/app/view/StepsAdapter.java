package app.cookie.app.view;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cookie.app.R;

import java.util.List;

import app.cookie.app.model.Step;

import static app.cookie.app.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app.stringdef.CookieConstants.KEY.RECIPE_NAME;
import static app.cookie.app.stringdef.CookieConstants.KEY.STEP_ID;

class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private final int recipeId;
    private final String recipeName;
    private final List<Step> steps;

    public StepsAdapter(int recipeId, String recipeName, List<Step> steps) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.steps = steps;
    }

    @Override
    public StepsAdapter.StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.StepViewHolder holder, int position) {
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

        public StepViewHolder(View itemView) {
            super(itemView);
            stepTextView = itemView.findViewById(R.id.step_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, StepDetailsActivity.class);
            intent.putExtra(RECIPE_NAME, recipeName);
            intent.putExtra(RECIPE_ID, recipeId);
            intent.putExtra(STEP_ID, step.getId());
            context.startActivity(intent);
        }
    }
}
