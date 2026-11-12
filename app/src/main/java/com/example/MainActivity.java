package com.example;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;

/**
 * MainActivity for US Citizenship Test Prep.
 * Implements interactive Phase 1 Home Screen with patriotic header,
 * Civics test version switcher (2025 vs 2008), 6 feature cards,
 * dynamic study progress indicator, and bottom navigation.
 */
public class MainActivity extends AppCompatActivity {

    // Header Controls
    private ImageButton btnMenu;
    private ImageButton btnNotifications;

    // Version Selector Controls
    private MaterialCardView btnTest2025;
    private MaterialCardView btnTest2008;
    private ImageView ivCheck2025;
    private ImageView ivCheck2008;
    private TextView tvTitle2025;
    private TextView tvTitle2008;

    // Main Feature Cards
    private MaterialCardView cardStudyQuestions;
    private MaterialCardView cardPracticeTest;
    private MaterialCardView cardFlashcards;
    private MaterialCardView cardInterviewPrep;
    private MaterialCardView cardMyProgress;
    private MaterialCardView cardSavedQuestions;

    // Progress Section Controls
    private CircularProgressIndicator progressCircular;
    private LinearProgressIndicator progressHorizontal;
    private TextView tvProgressPercent;
    private TextView tvMasteredQuestions;
    private ImageButton btnProgressDetails;
    private MaterialCardView cardStudyProgressContainer;

    // Bottom Navigation Controls
    private View navHome;
    private View navStudy;
    private View navPractice;
    private View navProgress;
    private View navSettings;

    private ImageView ivNavHome, ivNavStudy, ivNavPractice, ivNavProgress, ivNavSettings;
    private TextView tvNavHome, tvNavStudy, tvNavPractice, tvNavProgress, tvNavSettings;

    // State Tracking
    private boolean is2025Selected = true;
    private int currentNavIndex = 0; // 0=Home, 1=Study, 2=Practice, 3=Progress, 4=Settings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Apply edge-to-edge window insets cleanly
        setupWindowInsets();

        // Initialize UI component references
        initViews();

        // Setup click listeners and interactive behaviors
        setupListeners();

        // Initialize default UI state
        updateVersionSelection(true);
        updateNavigationState(0);
    }

    private void setupWindowInsets() {
        View rootLayout = findViewById(R.id.rootLayout);
        if (rootLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                // Ensure header doesn't get clipped by status bar
                View header = findViewById(R.id.headerContainer);
                if (header != null) {
                    header.setPadding(0, systemBars.top, 0, 0);
                }
                // Ensure bottom navigation stays elevated above navigation bar
                View bottomNav = findViewById(R.id.bottomNavContainer);
                if (bottomNav != null) {
                    bottomNav.setPadding(0, 0, 0, systemBars.bottom);
                }
                return insets;
            });
        }
    }

    private void initViews() {
        // Header
        btnMenu = findViewById(R.id.btnMenu);
        btnNotifications = findViewById(R.id.btnNotifications);

        // Version Selector
        btnTest2025 = findViewById(R.id.btnTest2025);
        btnTest2008 = findViewById(R.id.btnTest2008);
        ivCheck2025 = findViewById(R.id.ivCheck2025);
        ivCheck2008 = findViewById(R.id.ivCheck2008);
        tvTitle2025 = findViewById(R.id.tvTitle2025);
        tvTitle2008 = findViewById(R.id.tvTitle2008);

        // 6 Main Cards
        cardStudyQuestions = findViewById(R.id.cardStudyQuestions);
        cardPracticeTest = findViewById(R.id.cardPracticeTest);
        cardFlashcards = findViewById(R.id.cardFlashcards);
        cardInterviewPrep = findViewById(R.id.cardInterviewPrep);
        cardMyProgress = findViewById(R.id.cardMyProgress);
        cardSavedQuestions = findViewById(R.id.cardSavedQuestions);

        // Progress Section
        progressCircular = findViewById(R.id.progressCircular);
        progressHorizontal = findViewById(R.id.progressHorizontal);
        tvProgressPercent = findViewById(R.id.tvProgressPercent);
        tvMasteredQuestions = findViewById(R.id.tvMasteredQuestions);
        btnProgressDetails = findViewById(R.id.btnProgressDetails);
        cardStudyProgressContainer = findViewById(R.id.cardStudyProgressContainer);

        // Bottom Navigation
        navHome = findViewById(R.id.navHome);
        navStudy = findViewById(R.id.navStudy);
        navPractice = findViewById(R.id.navPractice);
        navProgress = findViewById(R.id.navProgress);
        navSettings = findViewById(R.id.navSettings);

        ivNavHome = findViewById(R.id.ivNavHome);
        ivNavStudy = findViewById(R.id.ivNavStudy);
        ivNavPractice = findViewById(R.id.ivNavPractice);
        ivNavProgress = findViewById(R.id.ivNavProgress);
        ivNavSettings = findViewById(R.id.ivNavSettings);

        tvNavHome = findViewById(R.id.tvNavHome);
        tvNavStudy = findViewById(R.id.tvNavStudy);
        tvNavPractice = findViewById(R.id.tvNavPractice);
        tvNavProgress = findViewById(R.id.tvNavProgress);
        tvNavSettings = findViewById(R.id.tvNavSettings);
    }

    private void setupListeners() {
        // Header Actions
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Navigation Drawer & Topics", Toast.LENGTH_SHORT).show()
            );
        }

        if (btnNotifications != null) {
            btnNotifications.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "USCIS Updates: No new announcements", Toast.LENGTH_SHORT).show()
            );
        }

        // Version Selector Toggles
        if (btnTest2025 != null) {
            btnTest2025.setOnClickListener(v -> updateVersionSelection(true));
        }

        if (btnTest2008 != null) {
            btnTest2008.setOnClickListener(v -> updateVersionSelection(false));
        }

        // Six Main Feature Cards
        if (cardStudyQuestions != null) {
            cardStudyQuestions.setOnClickListener(v -> {
                String version = is2025Selected ? "2025 Test" : "2008 Test";
                Toast.makeText(MainActivity.this, "Opening Study Questions (" + version + ")", Toast.LENGTH_SHORT).show();
            });
        }

        if (cardPracticeTest != null) {
            cardPracticeTest.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Starting 20-Question Practice Test", Toast.LENGTH_SHORT).show()
            );
        }

        if (cardFlashcards != null) {
            cardFlashcards.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Opening Civics Flashcards", Toast.LENGTH_SHORT).show()
            );
        }

        if (cardInterviewPrep != null) {
            cardInterviewPrep.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Opening N-400 & Interview Preparation", Toast.LENGTH_SHORT).show()
            );
        }

        if (cardMyProgress != null) {
            cardMyProgress.setOnClickListener(v -> {
                updateNavigationState(3); // Switch tab to Progress
                Toast.makeText(MainActivity.this, "Viewing Study Analytics & Mastery", Toast.LENGTH_SHORT).show();
            });
        }

        if (cardSavedQuestions != null) {
            cardSavedQuestions.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Opening Saved & Starred Questions", Toast.LENGTH_SHORT).show()
            );
        }

        // Progress Section Details
        View.OnClickListener progressClickListener = v ->
            Toast.makeText(MainActivity.this, "Progress Details: 58 mastered, 14 in review, 8 unread", Toast.LENGTH_SHORT).show();

        if (btnProgressDetails != null) {
            btnProgressDetails.setOnClickListener(progressClickListener);
        }
        if (cardStudyProgressContainer != null) {
            cardStudyProgressContainer.setOnClickListener(progressClickListener);
        }

        // Bottom Navigation Tabs
        if (navHome != null) navHome.setOnClickListener(v -> updateNavigationState(0));
        if (navStudy != null) navStudy.setOnClickListener(v -> updateNavigationState(1));
        if (navPractice != null) navPractice.setOnClickListener(v -> updateNavigationState(2));
        if (navProgress != null) navProgress.setOnClickListener(v -> updateNavigationState(3));
        if (navSettings != null) navSettings.setOnClickListener(v -> updateNavigationState(4));
    }

    /**
     * Toggles between 2025 Civics Test and 2008 Civics Test
     */
    private void updateVersionSelection(boolean select2025) {
        this.is2025Selected = select2025;

        int selectedBgColor = ContextCompat.getColor(this, R.color.navy_selected);
        int unselectedBgColor = ContextCompat.getColor(this, R.color.selector_unselected_bg);
        int selectedStrokeColor = 0xFF1A3B73;
        int unselectedStrokeColor = 0xFFD0DFEE;

        int selectedTextColor = ContextCompat.getColor(this, R.color.white);
        int unselectedTextColor = ContextCompat.getColor(this, R.color.selector_unselected_text);

        if (select2025) {
            // 2025 is Selected
            btnTest2025.setCardBackgroundColor(ColorStateList.valueOf(selectedBgColor));
            btnTest2025.setStrokeColor(ColorStateList.valueOf(selectedStrokeColor));
            btnTest2025.setCardElevation(dpToPx(2));
            ivCheck2025.setImageResource(R.drawable.ic_check_circle);
            tvTitle2025.setTextColor(selectedTextColor);

            // 2008 is Unselected
            btnTest2008.setCardBackgroundColor(ColorStateList.valueOf(unselectedBgColor));
            btnTest2008.setStrokeColor(ColorStateList.valueOf(unselectedStrokeColor));
            btnTest2008.setCardElevation(0);
            ivCheck2008.setImageResource(R.drawable.ic_circle_outline);
            tvTitle2008.setTextColor(unselectedTextColor);

            // Update Progress Card for 2025 syllabus
            tvProgressPercent.setText(getString(R.string.progress_percent_sample));
            tvMasteredQuestions.setText(getString(R.string.progress_mastered_sample));
            progressCircular.setProgressCompat(72, true);
            progressHorizontal.setProgressCompat(72, true);
        } else {
            // 2008 is Selected
            btnTest2008.setCardBackgroundColor(ColorStateList.valueOf(selectedBgColor));
            btnTest2008.setStrokeColor(ColorStateList.valueOf(selectedStrokeColor));
            btnTest2008.setCardElevation(dpToPx(2));
            ivCheck2008.setImageResource(R.drawable.ic_check_circle);
            tvTitle2008.setTextColor(selectedTextColor);

            // 2025 is Unselected
            btnTest2025.setCardBackgroundColor(ColorStateList.valueOf(unselectedBgColor));
            btnTest2025.setStrokeColor(ColorStateList.valueOf(unselectedStrokeColor));
            btnTest2025.setCardElevation(0);
            ivCheck2025.setImageResource(R.drawable.ic_circle_outline);
            tvTitle2025.setTextColor(unselectedTextColor);

            // Update Progress Card for 2008 syllabus
            tvProgressPercent.setText(getString(R.string.progress_percent_2008_sample));
            tvMasteredQuestions.setText(getString(R.string.progress_mastered_2008_sample));
            progressCircular.setProgressCompat(85, true);
            progressHorizontal.setProgressCompat(85, true);
        }
    }

    /**
     * Updates bottom navigation item highlighting
     */
    private void updateNavigationState(int selectedIndex) {
        this.currentNavIndex = selectedIndex;

        int activeColor = ContextCompat.getColor(this, R.color.nav_active);
        int inactiveColor = ContextCompat.getColor(this, R.color.nav_inactive);

        ImageView[] icons = {ivNavHome, ivNavStudy, ivNavPractice, ivNavProgress, ivNavSettings};
        TextView[] texts = {tvNavHome, tvNavStudy, tvNavPractice, tvNavProgress, tvNavSettings};
        String[] titles = {"Home", "Study", "Practice", "Progress", "Settings"};

        for (int i = 0; i < icons.length; i++) {
            boolean isActive = (i == selectedIndex);
            int color = isActive ? activeColor : inactiveColor;

            if (icons[i] != null) {
                icons[i].setImageTintList(ColorStateList.valueOf(color));
            }
            if (texts[i] != null) {
                texts[i].setTextColor(color);
                texts[i].setTypeface(null, isActive ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
            }
        }

        if (selectedIndex != 0) {
            Toast.makeText(this, titles[selectedIndex] + " tab selected", Toast.LENGTH_SHORT).show();
        }
    }

    private float dpToPx(int dp) {
        return dp * getResources().getDisplayMetrics().density;
    }
}
