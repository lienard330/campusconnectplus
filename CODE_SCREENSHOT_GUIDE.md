# CampusConnectPlus - Code Screenshot Guide (With Explanation)

Use this file to know exactly what code to screenshot for your presentation/report, and what to explain for each screenshot.

---

## Screenshot Rules (So your slides look clean)

- Screenshot only the **important 15-30 lines** (avoid full file).
- Zoom to make text readable (at least 125%-150%).
- Add a small label under each image: **"What this code does"**.
- Keep each explanation short: **2-4 sentences max**.
- Prefer one concept per screenshot.

---

## 1) App Entry + Navigation

### File
`app/src/main/java/com/campusconnectplus/MainActivity.kt`

### What to screenshot
- `setContent { ... }`
- `NavHost(...)`
- Start destination and key routes (student/admin flow)

### Explanation to say
"This is the app entry point. `MainActivity` sets up Compose UI and navigation.  
`NavHost` controls which screen is shown based on user flow, so this is where student and admin routes are connected."

---

## 2) Architecture / Dependency Wiring

### File
`app/src/main/java/com/campusconnectplus/core/di/AppContainer.kt`

### What to screenshot
- Repository and service creation/provision
- Any central dependency object definitions

### Explanation to say
"This is the central wiring for app dependencies.  
Instead of creating objects in every screen, we provide shared repositories/services from one place.  
This keeps the architecture clean and easier to maintain."

---

## 3) Service Locator / Dependency Access

### File
`app/src/main/java/com/campusconnectplus/core/di/ServiceLocator.kt`

### What to screenshot
- The part where dependencies are exposed or retrieved

### Explanation to say
"This file gives feature modules access to shared dependencies.  
It reduces repeated setup code and keeps object lifecycles consistent across screens."

---

## 4) Database Configuration (Room)

### File
`app/src/main/java/com/campusconnectplus/data/local/db/AppDatabase.kt`

### What to screenshot
- `@Database(...)` annotation
- Database class declaration
- DAO abstract functions

### Explanation to say
"This defines the Room database.  
It declares which entities/tables exist and which DAOs can access them.  
This is the core of local offline storage."

---

## 5) Entity (Table Model)

### File
`app/src/main/java/com/campusconnectplus/data/local/entity/UserEntity.kt`

### What to screenshot
- `@Entity` class
- Primary key and important columns

### Explanation to say
"This entity represents one database table.  
Each field becomes a column in Room.  
This structure lets us persist user data locally."

---

## 6) DAO (Database Operations)

### File
`app/src/main/java/com/campusconnectplus/data/local/dao/UserDao.kt`

### What to screenshot
- `@Dao` interface
- `@Query`, `@Insert/@Upsert`, `@Delete` methods
- Any `Flow` return type

### Explanation to say
"DAO is where database operations are defined.  
Queries and write operations are centralized here.  
Using `Flow` allows UI to react automatically when data changes."

---

## 7) Repository Pattern

### Files
- `app/src/main/java/com/campusconnectplus/data/repository/UserRepository.kt`
- `app/src/main/java/com/campusconnectplus/data/local/repository/RoomUserRepository.kt`

### What to screenshot
- Repository interface methods
- Room repository implementation methods

### Explanation to say
"Repository is the bridge between ViewModel and data source.  
ViewModel calls repository methods without needing database details.  
This makes it easy to add API/Firebase later without rewriting UI logic."

---

## 8) ViewModel (Business Logic + State)

### File
`app/src/main/java/com/campusconnectplus/feature_student/events/StudentEventsViewModel.kt`

### What to screenshot
- `StateFlow` state declaration
- One action handler function (e.g., load/filter/favorite)
- Repository call inside ViewModel

### Explanation to say
"ViewModel is the logic layer for the screen.  
It receives UI actions, calls repository functions, and exposes state to the UI.  
This keeps Composables simple and focused on display."

---

## 9) Compose Screen (UI Rendering)

### File
`app/src/main/java/com/campusconnectplus/ui/student/StudentEventsScreen.kt`

### What to screenshot
- `@Composable` function header
- `collectAsState(...)`
- List rendering (`LazyColumn`) or empty/loading states

### Explanation to say
"This Composable renders the events screen.  
It collects state from ViewModel and redraws automatically when state changes.  
So UI stays synced with data flow."

---

## 10) Admin Feature CRUD Screen

### File
`app/src/main/java/com/campusconnectplus/ui/admin/AdminEventsScreen.kt`

### What to screenshot
- Add/Edit/Delete UI handlers
- Dialog/form handling and submit action

### Explanation to say
"This admin screen provides content management operations.  
User actions trigger ViewModel methods for create/update/delete.  
Changes are persisted to Room and reflected in related screens."

---

## 11) Reusable UI Scaffold

### File
`app/src/main/java/com/campusconnectplus/core/ui/components/StudentScaffold.kt`

### What to screenshot
- Scaffold + top/bottom bar structure
- Slot/content area

### Explanation to say
"This reusable scaffold keeps layout consistent across student screens.  
It avoids repeating the same navigation and structure code in multiple files."

---

## 12) Custom UI/Canvas Component (Optional but good for points)

### File
`app/src/main/java/com/campusconnectplus/core/ui/components/StatRingCanvas.kt`

### What to screenshot
- The Canvas drawing logic

### Explanation to say
"This component uses custom drawing for data visualization.  
It improves UI quality and shows advanced Compose capability beyond basic text/list components."

---

## 13) Security Utility

### File
`app/src/main/java/com/campusconnectplus/core/security/PasswordHasher.kt`

### What to screenshot
- Hashing function

### Explanation to say
"This utility handles password hashing before storage/checking.  
It adds a security layer by avoiding plain-text credential handling."

---

## 14) Test Evidence (Very important for credibility)

### Files
- `app/src/test/java/com/campusconnectplus/feature_student/events/StudentEventsViewModelTest.kt`
- `app/src/test/java/com/campusconnectplus/feature_admin/events/AdminEventsViewModelTest.kt`
- `app/src/androidTest/java/com/campusconnectplus/MainActivityNavigationTest.kt`

### What to screenshot
- Test method names
- `given/when/then` style assertions
- Navigation assertion in instrumented test

### Explanation to say
"These tests verify key logic and navigation behavior.  
Unit tests validate ViewModel state transitions, while instrumented tests validate app navigation on device/emulator."

---

## Best 10 Screenshots Set (If instructor wants only a few)

If you can only include 10 code screenshots, use these:
1. `MainActivity.kt` (NavHost)
2. `AppContainer.kt` (dependency wiring)
3. `AppDatabase.kt` (`@Database`)
4. `UserEntity.kt` (`@Entity`)
5. `UserDao.kt` (`@Dao`)
6. `UserRepository.kt` (interface)
7. `RoomUserRepository.kt` (implementation)
8. `StudentEventsViewModel.kt` (state + logic)
9. `StudentEventsScreen.kt` (Compose + `collectAsState`)
10. `StudentEventsViewModelTest.kt` (test proof)

---

## Suggested Slide Mapping

- **Architecture slide:** #1, #2, #3  
- **Room slide:** #4, #5, #6  
- **Repository slide:** #7  
- **ViewModel + Data Flow slide:** #8 + #9  
- **UI/Feature slide:** #10 + #11 + #12  
- **Security/utility slide:** #13  
- **Testing slide:** #14  

---

## Quick Caption Template (Copy per screenshot)

**Code Area:** [File + function/class name]  
**Purpose:** [What this block does]  
**Why Important:** [How it supports architecture/offline/data flow/testing]  
**Rubric Match:** [Architecture / Room / Repository / Data Flow / Demo / Presentation]

---

Use this guide while taking screenshots so every image directly supports a rubric criterion.
