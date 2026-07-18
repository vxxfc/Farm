# Farm Manager

A complete, original Android app for managing a poultry/livestock farm — flocks, egg production,
feeding, health records, income & expenses, reminders, and reports. All features are unlocked;
there's no paywall, subscription, or licensing gate anywhere in this codebase.

## Tech stack
- **Kotlin** + **Jetpack Compose** (Material 3) for the UI
- **Room** for local persistence (SQLite under the hood)
- **Navigation Compose** for the bottom-nav + screen graph
- **ViewModel + Kotlin Flow** for state management (MVVM)
- **KSP** for Room's annotation processing

## Features
- **Dashboard** — live totals for bird count, monthly eggs, income, expenses, and feed cost
- **Flocks** — add/view/delete flocks (name, breed, quantity, acquisition cost & date)
- **Egg Production** — log daily collection + breakage per flock
- **Feed Management** — log feed type, quantity (kg), and cost per flock
- **Health Records** — vaccinations, medications, disease events, checkups per flock
- **Income & Expenses** — categorized transaction ledger
- **Reminders** — simple todo-style reminders with completion toggle
- **Reports** — aggregated totals (eggs, feed, income/expense, net profit)

## Project structure
```
app/src/main/java/com/farmmanager/app/
├── data/
│   ├── entity/        Room entities (Flock, EggRecord, FeedRecord, HealthRecord, TransactionEntity, Reminder, Note)
│   ├── dao/            Room DAOs
│   ├── repository/     FarmRepository — single source of truth used by all ViewModels
│   └── AppDatabase.kt  Room database + Converters
├── ui/
│   ├── dashboard/       Home screen + ViewModel
│   ├── flock/           Flock list/add + ViewModel
│   ├── egg/             Egg records + ViewModel
│   ├── feed/            Feed records + ViewModel
│   ├── health/          Health records + ViewModel
│   ├── transaction/     Income/expense ledger + ViewModel
│   ├── reminder/        Reminders + ViewModel
│   ├── reports/         Aggregated reports screen
│   ├── more/            Hub screen for Feed/Health/Transactions/Reminders
│   ├── navigation/      Bottom nav + NavHost wiring
│   └── theme/           Compose Material 3 theme (colors, type)
├── util/                DateUtils, generic ViewModelFactory
├── FarmApp.kt           Application class, owns the Room DB + repository singleton
└── MainActivity.kt      Entry point
```

## How to open and run it
1. Install **Android Studio** (Koala/2024.1 or newer recommended).
2. Open this folder (`FarmManager/`) as a project — Android Studio will detect the Gradle
   project automatically via `settings.gradle.kts`.
3. Let Gradle sync (it will pull dependencies for Compose, Room, Navigation, etc.).
4. Run on an emulator or physical device (minSdk 24 / Android 7.0+).

No API keys, backend, or account sign-in is required — all data is stored locally in a Room/SQLite
database on the device.

## Extending it
Everything is modular by feature package, so adding a new module (e.g. "Notes", which already has
an entity/DAO stubbed out but no UI yet) follows the same pattern as the others:
1. Add a `ViewModel` that exposes a `StateFlow` from the repository.
2. Add a Composable screen with a list + FAB-triggered add dialog.
3. Wire it into `AppNavigation.kt` (or into `MoreScreen.kt` if it's a secondary feature).
