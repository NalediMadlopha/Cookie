package app.cookie.app.view;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cookie.app.R;

import java.util.List;

import app.cookie.app.model.Step;

class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private final List<Step> steps;

    public StepsAdapter(List<Step> steps) {
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
            Toast.makeText(view.getContext(), String.valueOf(step.getId()), Toast.LENGTH_SHORT).show();
        }
    }
}
