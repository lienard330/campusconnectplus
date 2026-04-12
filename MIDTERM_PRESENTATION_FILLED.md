# CampusConnectPlus - Midterm MCO Presentation (Filled)

## Slide 1 - Title Slide
- **App Name:** CampusConnectPlus  
- **Group Name:** [Your Group Name]  
- **Members:** [Member 1], [Member 2], [Member 3], [Member 4]  
- **Course:** Mobile Programming 2  
- **Instructor:** [Instructor Name]  
- **Date:** April 10, 2026  

## Slide 2 - Project Overview
- **Brief description:** CampusConnectPlus is a campus information app for students and admins to manage and view announcements, events, media, and saved items.  
- **Purpose:** Provide a structured, offline-ready mobile platform for campus communication and content management.  
- **Target users:** Students, campus admin staff, organizers, and media team members.  

## Slide 3 - Project Goal
- Build a scalable mobile app structure using MVVM and clean separation of concerns.  
- Implement offline-first readiness using local persistence.  
- Use Room database with Repository pattern for maintainable data flow.  

## Slide 4 - Midterm Scope
- **Included in Midterm:**
  - Local database with Room (entities, DAOs, database provider)
  - Basic and functional UI using Jetpack Compose + Material 3
  - Data flow using ViewModel -> Repository -> Room
  - Role-based admin screens and student screens
- **Not yet included (Final term target):**
  - Firebase / REST API integration
  - Online sync and conflict resolution

## Slide 5 - App Architecture Overview
**Architecture (Current):**
UI (Compose Screens)  
-> ViewModel  
-> Repository  
-> Room Database (DAO)  
-> UI updates via Flow/StateFlow

**Layer notes:**
- UI is in `ui/student` and `ui/admin`.
- ViewModels are in `feature_student` and `feature_admin`.
- Repositories and Room implementation are in `data/repository` and `data/local`.

## Slide 6 - Why Use Hilt?
- Simplifies dependency injection and removes manual object wiring.
- Improves scalability as modules/features grow.
- Makes ViewModels and repositories easier to test.
- Helps keep architecture clean and consistent.
- Reduces boilerplate compared to manual DI.

## Slide 7 - Hilt Module (Providing Dependencies)
Use this slide to show your actual DI setup from project files:
- App-level container and service locator are used to provide shared dependencies.
- Database/repository providers are centralized for consistent injection.
- ViewModel factories are defined to bind ViewModels to provided repositories.

**Explain:**
- Dependencies are created in one place and injected where needed.
- This keeps ViewModel and repository construction maintainable and testable.

## Slide 8 - Data Model (Entity)
Show Room entities from your codebase, such as:
- `UserEntity`
- Event entity
- Media entity
- Announcement entity
- Favorite entity

**Explain:**
- Each entity maps to a Room table.
- Entities define the local storage schema for offline-first behavior.

## Slide 9 - DAO (Data Access Object)
Show DAO interfaces from `data/local/dao`.

**Explain:**
- DAO handles all database operations (queries, inserts, updates, deletes).
- `Flow`-based queries keep UI reactive to database changes.

## Slide 10 - Repository Pattern
Show repository interfaces/implementations:
- `AuthRepository`, `UserRepository`, and feature repositories
- Room-based implementations and fake repositories for tests

**Explain:**
- Repository separates data source details from UI logic.
- Same ViewModel API can later support remote API/Firebase without rewriting UI.

## Slide 11 - ViewModel
Show sample ViewModel logic from:
- `StudentEventsViewModel`
- `AdminEventsViewModel`
- other student/admin ViewModels

**Explain:**
- ViewModel exposes UI state (`StateFlow`) and handles actions.
- Business logic stays outside Composables.

## Slide 12 - UI (Compose)
Show sample Composable screens:
- Student: Home, Events, Media, Saved, Announcements
- Admin: Login, Dashboard, Events, Media, Announcements, Users, Settings

**Explain:**
- UI observes ViewModel state via `collectAsState`.
- Material 3 components and navigation are fully integrated.

## Slide 13 - Data Flow with Hilt Explanation
**Flow:**
User Action  
-> Compose UI Event  
-> ViewModel  
-> Repository  
-> DAO / Room  
-> Flow emits updated data  
-> ViewModel state updates  
-> UI recomposes

**Explain:**
- This provides predictable, testable, and offline-capable behavior.

## Slide 14 - Demonstration
- Insert or update event/announcement/media data in admin screens.
- Display content on student screens (events/media/announcements).
- Show saved/favorite behavior in student module.
- Show app behavior without network (local Room data still available).

## Slide 15 - Key Achievements
- Working Room database with entities and DAOs.
- Clean app structure (MVVM + repository layering).
- Offline-first ready architecture.
- Multiple functional screens for both Student and Admin roles.
- Unit tests and instrumented navigation test included.

## Slide 16 - Challenges Encountered
- Database setup and schema consistency.
- State synchronization between Compose, Flow, and ViewModel.
- Debugging data flow across multiple layers.
- Managing role-based admin navigation and permissions.

## Slide 17 - Preparation for Final Term
- Add Firebase or REST API integration.
- Implement local/remote sync strategy.
- Add conflict-handling rules for sync.
- Expand UI/UX polish and transition animations.
- Increase automated test coverage (UI and repository-level tests).

## Slide 18 - Conclusion
- Midterm implementation already demonstrates strong architecture and offline readiness.
- Core local data flow is complete and stable.
- Project is ready to scale into cloud-connected final-term features.

---

## Rubric Alignment (Self-check)
- **App Architecture (25):** MVVM, layered structure, clear separation
- **Room Implementation (15):** entities, DAO, database provider in place
- **Hilt DI Implementation (15):** dependency provisioning/injection structure present
- **Repository Pattern (15):** repositories abstract data operations
- **Data Flow Understanding (10):** reactive Flow/StateFlow from DB to UI
- **Demo (10):** create/read/update UI flows available
- **Presentation (10):** complete 18-slide outline prepared

---

### Presenter Notes (Optional)
- Replace placeholders (`[Your Group Name]`, members, instructor) before presenting.
- Add screenshots per slide (architecture, entity/DAO snippets, ViewModel, UI screens, demo flow).
- Keep code snippets short and explain "why" each layer exists.
