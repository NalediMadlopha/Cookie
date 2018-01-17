package com.cookie.app.view.stepdetails;


import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cookie.app.R;
import com.cookie.app.dependency.App;
import com.cookie.app.model.Recipe;
import com.cookie.app.model.Step;
import com.cookie.app.stringdef.CookieConstants;
import com.cookie.app.viewmodel.StepDetailsViewModel;
import com.google.android.exoplayer2.C;
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

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.cookie.app.Utils.Util.getBitmapFromURL;
import static com.cookie.app.stringdef.CookieConstants.KEY.PLAY_WHEN_READY_STATE;
import static com.cookie.app.stringdef.CookieConstants.KEY.RECIPE_ID;
import static com.cookie.app.stringdef.CookieConstants.KEY.SELECTED_POSITION;

public class StepDetailsFragment extends Fragment {

    private static int CURRENT_STEP;
    private static final int FIRST_STEP = 0;
    private StepDetailsViewModel viewModel;
    private Recipe recipe;
    private Step step;
    private boolean isPlayWhenReady;
    private int currentWindow;
    private long playbackPosition;
    private SimpleExoPlayer exoPlayer;
    private Uri videoUri;
    private String thumbnailURL;

    @Nullable
    @BindView(R.id.step_short_description)
    TextView stepShortDescriptionTextView;

    @Nullable
    @BindView(R.id.step_description)
    TextView stepDescriptionTextView;

    @Nullable
    @BindView(R.id.previous_step_button)
    Button previousStepButton;

    @Nullable
    @BindView(R.id.next_step_button)
    Button nextStepButton;

    @Nullable
    @BindView(R.id.view_space)
    View viewSpace;

    @Nullable
    @BindView(R.id.step_details_progress_bar)
    ProgressBar progressBar;

    @Nullable
    @BindView(R.id.step_details_main_layout)
    LinearLayout mainlayout;

    @BindView(R.id.exoPlayerView)
    SimpleExoPlayerView exoPlayerView;

    @Nullable
    @BindBool(R.bool.isLandscape)
    boolean isLandspace;

    public StepDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.appComponent().inject(this);
        playbackPosition = C.TIME_UNSET;

        if (savedInstanceState != null) {
            playbackPosition = savedInstanceState.getLong(SELECTED_POSITION, C.TIME_UNSET);
            isPlayWhenReady = savedInstanceState.getBoolean(PLAY_WHEN_READY_STATE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(StepDetailsViewModel.class);
        if (getArguments() != null) {
            CURRENT_STEP = getArguments().getInt(CookieConstants.KEY.STEP_ID);
            viewModel.init(getArguments().getInt(RECIPE_ID), CURRENT_STEP);
        }
        viewModel.getRecipe().observe(this, recipe -> {
            this.recipe = recipe;
            updateUi();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoUri != null) {
            initializePlayer(videoUri, thumbnailURL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            playbackPosition = exoPlayer.getCurrentPosition();
            isPlayWhenReady = exoPlayer.getPlayWhenReady();
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(PLAY_WHEN_READY_STATE, isPlayWhenReady);
        outState.putLong(SELECTED_POSITION, playbackPosition);
    }

    private void displayPreviousStepButton() {
        previousStepButton.setVisibility(View.VISIBLE);
        previousStepButton.setOnClickListener(previousStepButtonClickListener);
    }

    private void hidePreviousStepButton() {
        previousStepButton.setVisibility(View.GONE);
    }

    private void displayNextStepButton() {
        nextStepButton.setVisibility(View.VISIBLE);
        nextStepButton.setOnClickListener(nextStepButtonClickListener);
    }

    private void hideNextStepButton() {
        nextStepButton.setVisibility(View.GONE);
    }

    private void updateUi() {
        step = recipe.getSteps().get(CURRENT_STEP);

        if (!TextUtils.isEmpty(step.getVideoURL())) {
            videoUri  = Uri.parse(step.getVideoURL());
            thumbnailURL = step.getThumbnailURL();
            initializePlayer(videoUri, thumbnailURL);
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
            if (CURRENT_STEP == FIRST_STEP) {
                hidePreviousStepButton();
                displayNextStepButton();
            } else if (CURRENT_STEP == (recipe.getSteps().size() - 1)) {
                hideNextStepButton();
                displayPreviousStepButton();
            } else {
                displayNextStepButton();
                displayPreviousStepButton();
            }

            if (nextStepButton.getVisibility() == View.VISIBLE && previousStepButton.getVisibility() == View.VISIBLE) {
                viewSpace.setVisibility(View.VISIBLE);
            } else {
                viewSpace.setVisibility(View.GONE);
            }
        }
    }

    View.OnClickListener previousStepButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.setStepId(CURRENT_STEP--);
            updateStepDetails();
        }
    };

    View.OnClickListener nextStepButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.setStepId(CURRENT_STEP++);
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

    private void initializePlayer(Uri uri, String thumbnailURL) {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        exoPlayerView.setDefaultArtwork(getBitmapFromURL(thumbnailURL));
        exoPlayerView.setPlayer(exoPlayer);

        exoPlayer.setPlayWhenReady(isPlayWhenReady);
        if (playbackPosition != C.TIME_UNSET) exoPlayer.seekTo(playbackPosition);

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
            isPlayWhenReady = exoPlayer.getPlayWhenReady();
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