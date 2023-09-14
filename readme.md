# Android Test demo App

This document provides an overview of the architecture of our Test demo app (for chip), highlighting the key components and libraries used.

##  Layers

Our Android app follows a clean architecture pattern, which separates the app into several distinct layers, each with its own responsibilities.

├── data
│ ├── api
│ └── repository
│  
├── domain
│ ├── model
│ └── usecase
│
├── presentation
│ ├── ui
│ └── viewmodel
│  
│── utils  
│── di
└── App.kt


### 1. Presentation Layer

- Responsible for the User Interface (UI).
- Utilizes Jetpack Compose for UI components.
- Contains ViewModels for managing UI-related data and interactions.
- Observes changes in the ViewModel and updates the UI accordingly.

### 2. Domain Layer

- Contains the business logic and use cases.
- Use Cases encapsulate the app's core functionality.
- Business logic is isolated from the presentation and data layers.

### 3. Data Layer

- Manages data sources and repositories.
- Uses Retrofit for network communication.
- Utilizes Gson for JSON serialization/deserialization.
- Repositories abstract data sources (remote and local).

## Libraries Used

Our Android app leverages several libraries to enhance its functionality and maintainability:

- **Jetpack Compose:** A modern Android UI toolkit for building native UIs with a declarative syntax.

- **Retrofit:** A popular HTTP client for making network requests and handling API interactions.

- **Gson:** A library for JSON serialization and deserialization.

- **Koin (Dependency Injection):** A lightweight dependency injection framework for managing app dependencies.

- **NetworkResponseAdapter:** This library provides a Kotlin Coroutines based Retrofit call adapter for wrapping your API responses in a NetworkResponse sealed type.

- **Mockk:** It's open source library for kotlin focused on making mocking.

## Getting Started

To build and run the app locally, follow these steps:

1. Open the project in Android Studio.
2. Build and run the app on an emulator or physical device.

