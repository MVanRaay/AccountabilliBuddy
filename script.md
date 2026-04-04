# AccountabiliBuddy – Demo Script

## Introduction

Hi, today I’ll be demonstrating AccountabiliBuddy, a web application designed to help users track personal goals and stay accountable through a social friends system.

The core idea behind the application is that users can create and manage goals, track their progress over time, and connect with friends to view each other’s progress and stay motivated.

From a technical perspective, the application is built using Spring Boot on the backend, with Thymeleaf for server-side rendering on the frontend, and uses JPA for database persistence. Authentication is handled securely through Google OAuth.

---

## Architecture Overview

Before jumping into the demo, I’ll briefly explain the architecture.

The application follows a standard MVC pattern. The controller layer handles incoming HTTP requests and routes them to the appropriate views. The service layer contains the business logic, including validation and processing of user data. The repository layer uses Spring Data JPA to interact with the database.

When a user performs an action, such as creating a goal, the request flows from the controller to the service, then to the repository for persistence, and the result is returned back to the view.

Security is handled through Google OAuth, and all data in the application is scoped to the authenticated user, ensuring users can only access their own goals and their confirmed friends.

---

## Authentication Flow

I’ll start by showing the login flow.

When a user accesses the application, they are redirected to sign in using their Google account. This ensures secure authentication without managing passwords directly.

For first-time users, after signing in, they are redirected to a registration page where some fields are automatically pre-filled using their Google account information. Once they complete registration, they are taken to the dashboard.

---

## Dashboard Overview

After logging in, the user lands on the dashboard.

This page displays all of the user’s active goals. Each goal shows key information such as the title, target date, completion frequency, and progress indicators.

Users can also navigate to create new goals, view existing goals in detail, or access the friends system from here.

---

## Creating a Goal

Next, I’ll demonstrate creating a goal.

I navigate to the create goal page and fill in the required fields, including the goal title, timeframe, and number of completions per timeframe. Depending on the timeframe, an end date may also be required.

The application performs validation to ensure that all required fields are correctly filled out.

Once I submit the form, the goal is saved to the database and immediately appears on the dashboard.

---

## Viewing and Editing a Goal

Now I’ll open an existing goal.

This page displays all of the goal’s details, including its timeframe, completion requirements, and associated dates.

The same page also allows editing. The fields are pre-populated with the current values, and I can update any of them. After saving, the changes are persisted and reflected immediately.

---

## Deleting a Goal

From the same page, I can also delete a goal.

When I delete the goal, it is permanently removed from the database and no longer appears on the dashboard. This action is irreversible in the current implementation.

---

## Tracking Progress

Back on the dashboard, users can track their progress directly.

Each goal includes completion indicators based on its defined timeframe. Users can toggle these checkboxes to mark progress, and the updates are saved automatically.

This allows users to consistently track their progress without navigating away from the main page.

---

## Friends System

Now I’ll move on to the friends system.

Users can navigate to the friends page to view a list of their confirmed friends.

They can search for other users in the system and add them as friends. Once added, those users appear in the friends list.

Selecting a friend allows the user to view that friend’s goals. This creates a shared accountability system where users can monitor each other’s progress.

Users also have the ability to remove friends, which will remove them from their list and revoke access to their data.

---

## Wrap-Up

To summarize, AccountabiliBuddy allows users to securely log in, create and manage goals, track their progress over time, and stay accountable through a social friends system.

All features implemented align with the original project scope, and the application demonstrates a clean separation of concerns through its MVC architecture.

Future improvements would focus on refining the user interface and expanding social features.

Thank you.