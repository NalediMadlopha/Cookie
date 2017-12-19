package app.cookie.app.view.stepdetails;


import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cookie.app.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import app.cookie.app.model.Recipe;
import app.cookie.app.model.Step;
import app.cookie.app.viewmodel.StepDetailsViewModel;

public class StepDetailsFragment extends Fragment implements StepDetailsFragmentView {

    private final int FIRST_STEP_ID = 0;
    private final int STEP_ID_INCREMENT = 1;
    private static final String TAG = StepDetailsFragment.class.getSimpleName();
    private TextView stepShortDescriptionTextView;
    private TextView stepDescriptionTextView;
    private Button previousStepButton;
    private Button nextStepButton;
    private View viewSpace;
    private StepDetailsViewModel viewModel;
    private Recipe recipe;
    private Step step;
    private ProgressBar progressBar;
    private LinearLayout mainlayout;
    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView exoPlayerView;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;
    private boolean isLandspace;

    public StepDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        progressBar = view.findViewById(R.id.step_details_progress_bar);
        mainlayout = view.findViewById(R.id.step_details_main_layout);
        exoPlayerView = view.findViewById(R.id.exoPlayerView);
        stepShortDescriptionTextView = view.findViewById(R.id.step_short_description);
        stepDescriptionTextView = view.findViewById(R.id.step_description);
        previousStepButton = view.findViewById(R.id.previous_step_button);
        nextStepButton = view.findViewById(R.id.next_step_button);
        viewSpace = view.findViewById(R.id.view_space);
        isLandspace = getResources().getBoolean(R.bool.isLandscape);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(StepDetailsViewModel.class);
        viewModel.getRecipe().observe(this, recipe -> {
            this.recipe = recipe;
            updateUi();
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @Override
    public void displayPreviousStepButton() {
        previousStepButton.setVisibility(View.VISIBLE);
        previousStepButton.setOnClickListener(PreviousStepButtonClickListener);
    }

    @Override
    public void hidePreviousStepButton() {
        previousStepButton.setVisibility(View.GONE);
    }

    @Override
    public void displayNextStepButton() {
        nextStepButton.setVisibility(View.VISIBLE);
        nextStepButton.setOnClickListener(NextStepButtonClickListener);
    }

    @Override
    public void hideNextStepButton() {
        nextStepButton.setVisibility(View.GONE);
    }

    private void updateUi() {
        step = recipe.getSteps().get(viewModel.getStepId());
        if (!step.getVideoURL().isEmpty()) {
            initializePlayer(Uri.parse(step.getVideoURL()));
        } else {
            exoPlayerView.setVisibility(View.GONE);
        }

        if (!isLandspace) {
            stepShortDescriptionTextView.setText(step.getShortDescription());
            stepDescriptionTextView.setText(step.getDescription());
            progressBar.setVisibility(View.GONE);
            mainlayout.setVisibility(View.VISIBLE);
            setupButtons();
        } else {
            hideSystemUi();
        }
    }

    private void setupButtons() {
        if (!getResources().getBoolean(R.bool.isTablet)) {
            if (viewModel.getStepId() != FIRST_STEP_ID) {
                displayPreviousStepButton();
            } else {
                hidePreviousStepButton();
            }

            if ((viewModel.getStepId() + STEP_ID_INCREMENT) != recipe.getSteps().size()) {
                displayNextStepButton();
            } else {
                hideNextStepButton();
            }

            if (nextStepButton.getVisibility() == View.VISIBLE && previousStepButton.getVisibility() == View.VISIBLE) {
                viewSpace.setVisibility(View.VISIBLE);
            } else {
                viewSpace.setVisibility(View.GONE);
            }
        }
    }

    View.OnClickListener PreviousStepButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.setStepId(step.getId() - 1);
            updateStepDetails();
        }
    };

    View.OnClickListener NextStepButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.setStepId(step.getId() + 1);
            updateStepDetails();
        }
    };

    public void updateStepDetails() {
        StepDetailsFragment fragment = new StepDetailsFragment();

        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.step_details_container, fragment)
                .commit();
    }

    private void initializePlayer(Uri uri) {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        exoPlayerView.setPlayer(exoPlayer);

        exoPlayer.setPlayWhenReady(playWhenReady);
        exoPlayer.seekTo(currentWindow, playbackPosition);

        MediaSource mediaSource = buildMediaSource(uri);
        exoPlayer.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory(getString(R.string.app_name)),
                new DefaultExtractorsFactory(), null, null);
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            playbackPosition = exoPlayer.getCurrentPosition();
            currentWindow = exoPlayer.getCurrentWindowIndex();
            playWhenReady = exoPlayer.getPlayWhenReady();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        exoPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}