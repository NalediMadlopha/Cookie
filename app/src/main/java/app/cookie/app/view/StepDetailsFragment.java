package app.cookie.app.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cookie.app.R;

import app.cookie.app.model.Step;
import app.cookie.app.viewmodel.StepDetailsFragmentViewModel;

public class StepDetailsFragment extends Fragment implements StepDetailsFragmentView {

    private StepDetailsFragmentViewModel viewModel;
    private TextView stepShortDescriptionTextView;
    private TextView stepDescriptionTextView;
    private ProgressBar progressBar;
    private Button previousStepButton;
    private Button nextStepButton;
    private String recipeName;

    public StepDetailsFragment() {
        // Required empty public constructor
    }

    public static StepDetailsFragment newInstance() {
        return new StepDetailsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new StepDetailsFragmentViewModel(getContext(), this, getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.step_details_progress_bar);
        stepShortDescriptionTextView = (TextView) view.findViewById(R.id.step_short_description);
        stepDescriptionTextView = (TextView) view.findViewById(R.id.step_description);
//        previousStepButton = (Button) view.findViewById(R.id.previous_step_button);
//        nextStepButton = (Button) view.findViewById(R.id.next_step_button);
//
//        previousStepButton.setOnClickListener(PreviousStepButtonClickListener);
//        nextStepButton.setOnClickListener(NextStepButtonClickListener);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    //    View.OnClickListener PreviousStepButtonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            viewModel.previousStepButtonClicked();
//        }
//    };
//
//    View.OnClickListener NextStepButtonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            viewModel.nextStepButtonClicked();
//        }
//    };
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        viewModel.fetchStepDetails();
//    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayStepDetails(Step step) {
        stepShortDescriptionTextView.setText(step.getShortDescription());
        stepDescriptionTextView.setText(step.getDescription());
    }


//    @Override
//    public void displayPreviousStepButton() {
//        previousStepButton.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hidePreviousStepButton() {
//        previousStepButton.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void displayNextStepButton() {
//        nextStepButton.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hideNextStepButton() {
//        nextStepButton.setVisibility(View.GONE);
//    }



//    @Override
//    public void updateStepDetails(int stepId, int recipeId) {
//        Bundle bundle = new Bundle();
//        bundle.putInt(STEP_ID, stepId);
//        bundle.putInt(RECIPE_ID, recipeId);
//        bundle.putString(RECIPE_NAME, recipeName);
//
//        StepDetailsFragment fragment = StepDetailsFragment.newInstance();
//        fragment.setArguments(bundle);
//
//        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.fragment_step_details_container, fragment)
//                .commit();
//    }

    private class MyMediaSession extends MediaSessionCompat.Callback {

        @Override
        public void onPlay() {
            super.onPlay();
        }

        @Override
        public void onPause() {
            super.onPause();
        }

        @Override
        public void onSkipToPrevious() {
            super.onSkipToPrevious();
        }

        @Override
        public void onSkipToNext() {
            super.onSkipToNext();
        }
    }
}