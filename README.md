# Trello_Clone
Project Overview
  Trello Clone is an open-source project aimed at replicating the core features of Trello, a popular project management tool owned by Atlassian. This clone provides users with the ability to create Trello profiles, manage tasks, collaborate on boards, and share profiles with others. The app includes essential features to help users organize their projects across multiple boards for purposes like education or business.

Key Features:
  Splash Screen with App Icon: Displays the custom app launcher icon on the splash screen.
  Sign-In and Sign-Up Screens: Allows users to register or log in to their Trello profile.
  Home Screen: Users can create and manage Trello profiles and share them with others.
Setup Instructions
Requirements:
  Platform: Android Studio
  Languages/Frameworks: Kotlin, Jetpack Compose (and/or XML)
  Tools: Git, Android SDK
Installation Steps:
  Clone the Repository:
    Copy code
    git clone https://github.com/yourusername/trello-clone.git
  Open the Project in Android Studio:
    Launch Android Studio and select "Open an Existing Project."
    Navigate to the folder where the project is cloned and open it.
  Build the Project:
    Ensure all dependencies are installed.
    Sync the project with Gradle files.
  Run the App:
    Connect an Android device or use an emulator to run the app.
    Click on "Run" or use the command:
    Copy code
    ./gradlew assembleDebug
    
Screens Designed and Their Purpose
  Splash Screen:  
    Displays the custom app launcher icon as a brief loading screen before directing users to either the sign-in or sign-up screens.
  Sign-In Screen:
    Enables existing users to sign in with their credentials and access their Trello profile.
    Offers the option to reset forgotten passwords.
  Sign-Up Screen:  
    Allows new users to register by entering their account details.
    After successfully signing up, users are redirected to the home screen to set up their Trello profile.
  Home Screen: 
    The main interface where users can create and manage their Trello profiles.
    Users can share their profiles with others and start collaborating on various boards and tasks.

Technical Challenges Faced
  Color Scheme Selection:
    One of the initial challenges was selecting a consistent and visually appealing color palette that aligns with the app’s theme while maintaining a good user experience across various screens.
  Home Page Alignment:
    Achieving the desired layout and alignment on the Home screen proved difficult, especially when handling different screen sizes and orientations.

Future Development Plans
  Board and Task Management: Add functionality to create and manage boards, lists, and tasks, allowing users to track project progress more efficiently.
  Collaboration Features: Implement features for sharing boards, assigning tasks, and commenting, enabling seamless collaboration among users.
  Improved User Experience: Focus on improving the app's performance and responsiveness, including optimizing the UI for different screen resolutions.  
  Authentication and Security: Enhance user authentication mechanisms and ensure secure data handling for user accounts and profiles.
