# US Citizenship Test Prep 🇺🇸

An Android application designed to help applicants prepare for and pass the United States Naturalization Civics Test, practice with flashcards, review the N-400 interview, and track study progress toward American citizenship.

[![Android Build & APK Generation](https://github.com/jaygaw87/US-Citizen-Ship/actions/workflows/android-build.yml/badge.svg)](https://github.com/jaygaw87/US-Citizen-Ship/actions/workflows/android-build.yml)

---

## 📌 Project Overview

- **App Name**: US Citizenship Test Prep
- **Application ID**: `com.digipocketapps.uscitizenshiptest`
- **Platform**: Native Android
- **Programming Language**: Java
- **UI Architecture**: XML Layouts + Material Design 3 (M3)
- **Minimum SDK**: Android 7.0 (API Level 24)
- **Target SDK**: Android 16 (API Level 36)
- **Build System**: Gradle with Kotlin DSL (`build.gradle.kts`)

---

## 🚀 Current Development Stage: Stage 1 (Home Screen UI)

The current release represents **Stage 1 — Complete Home Screen UI & Layout Recreation**.

### ✅ Implemented in Stage 1
1. **Patriotic Header Section**:
   - Dark navy gradient background with glowing golden stars and laurel wreath.
   - High-resolution artwork overlay featuring the illuminated Statue of Liberty and the US Capitol building dome.
   - Header title `"US Citizenship"`, elevated red ribbon `"TEST PREP"`, and subtitle `"Civics • Interview • Practice"`.
   - Top navigation menu button (`btnMenu`) and notification bell button with active alert indicator (`btnNotifications`).
   - Flowing silk American flag ribbon wave separator.
2. **Civics Test Version Selector**:
   - Responsive toggle buttons for **2025 Civics Test** (with `"NEW"` badge) and **2008 Civics Test**.
   - Fully interactive selection states: deep navy background, checkmark, and dynamic progress statistics recalculation.
3. **Six Main Feature Cards**:
   - Two-column grid with rounded corners (18dp), elevation, ripple touch feedback, and custom vector icons:
     - **Study Questions** (`cardStudyQuestions`)
     - **Practice Test** (`cardPracticeTest`)
     - **Flashcards** (`cardFlashcards`)
     - **Interview Prep** (`cardInterviewPrep`)
     - **My Progress** (`cardMyProgress`)
     - **Saved Questions** (`cardSavedQuestions`)
4. **Study Progress Section**:
   - Rounded progress card with Material 3 Circular Progress Indicator, percentage label, mastered questions counter, and Linear Progress Indicator.
   - Live synchronization with selected test version (72% for 2025 test; 85% for 2008 test).
5. **Fixed Bottom Navigation Bar**:
   - 5 navigation tabs: **Home** (active), **Study**, **Practice**, **Progress**, **Settings**.
   - Interactive tab selection highlighting with underlying decorative flag wave watermark.

### ⚠️ Functional Scope & Placeholders (Stage 2 Roadmap)
The following features are currently visual UI representations and are scheduled for implementation in Stage 2:
- ❌ **USCIS Civics Question Database**: Full 100/128 official civics questions database with state-specific answers (Governor, Senators, Representatives).
- ❌ **Interactive Quiz Engine**: 20-question randomized mock exams with passing score (60%) calculation.
- ❌ **Flashcard Flipping Engine**: Swipeable 3D flashcards with "Know" vs "Study Again" sorting.
- ❌ **N-400 Interview & Audio Engine**: Spoken English comprehension and interview audio recordings.
- ❌ **Persistent Data Storage**: Local Room database or cloud sync for tracking historical test scores over time.

---

## 🛠️ Building the Project in Android Studio

### Prerequisites
- **Android Studio**: Ladybug (2024.2.1) or later recommended.
- **JDK**: Java Development Kit 17 or higher.
- **Android SDK**: Build Tools 36.0.0, Platform SDK 36, and Platform SDK 24.

### Build Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/jaygaw87/US-Citizen-Ship.git
   ```
2. Open Android Studio and select **Open**, then select the cloned project folder.
3. Allow Gradle to sync dependencies automatically.
4. Build the debug APK using the terminal:
   ```bash
   # On Linux / macOS
   ./gradlew clean assembleDebug

   # On Windows
   gradlew.bat clean assembleDebug
   ```
5. The generated APK will be placed at:
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

---

## 📦 Downloading the APK from GitHub Actions

Every commit and pull request to the `main` branch automatically triggers the Android Build workflow to compile a clean APK:

1. Open the repository on GitHub: [https://github.com/jaygaw87/US-Citizen-Ship](https://github.com/jaygaw87/US-Citizen-Ship)
2. Click on the **Actions** tab at the top.
3. Select the latest workflow run (titled **Android Build & APK Generation**).
4. Scroll down to the **Artifacts** section at the bottom of the summary page.
5. Click on **US-Citizenship-Test-Debug-APK** to download the ZIP file containing `app-debug.apk`.

---

## 📱 Installing on a Physical Android Phone

1. **Transfer the APK to your phone**:
   - Send the `app-debug.apk` file to your phone via USB cable, Google Drive, email attachment, or local file sharing.
2. **Enable Unknown App Installation**:
   - When tapping the downloaded `.apk` file, Android may display a security prompt: *"For your security, your phone is not allowed to install unknown apps from this source."*
   - Tap **Settings** in the prompt and toggle **Allow from this source** to ON.
3. **Install and Launch**:
   - Tap **Install**.
   - Tap **Open** once installation completes.
4. **Via ADB (Command Line)**:
   - If your phone is connected to your computer with USB Debugging enabled:
     ```bash
     adb install -r app/build/outputs/apk/debug/app-debug.apk
     ```

---

## 📄 License

This project is prepared for educational and naturalization test preparation purposes. Official civics questions and answers are public domain materials provided by the United States Citizenship and Immigration Services (USCIS).
