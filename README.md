# Hustle4Work

# The project is an Android application aimed at facilitating job search and recruitment processes. 
It includes features such as user authentication (login and signup), job feed, profile management, password reset, and submission of seeker forms. 
The application utilizes various components such as activities, fragments, RecyclerView, navigation drawer, and API calls to provide a seamless user experience. 
Additionally, it incorporates internet connectivity checks and toast messages to enhance usability.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Code Explanations](#code-explanations)
  - [LoginActivity.java](#loginactivityjava)
  - [MainActivity.java](#mainactivityjava)
  - [ResetPassword.java](#resetpasswordjava)
  - [SeekerForm.java](#seekerformjava)
  - [SignUpActivity.java](#signupactivityjava)
  - [SplashActivity.java](#splashactivityjava)

## Installation

To get started with the Hustle4Work Android application, follow these steps:

1. **Clone the Repository:**
   Clone the repository to your local machine using the following command:
   ```sh
   git clone https://github.com/Rutvik-Gandhi/Hustle4Work-Final-Capstone-Project-.git

2. Open the Project:
Open the project in Android Studio. Navigate to File -> Open and select the project's root directory.

3. Build the Project:
Allow Android Studio to build the project. This may take a few minutes as it resolves dependencies and sets up the project.

4. Configure API Endpoints:
Open the WebApiCall class and set up the necessary API endpoints and keys. Ensure you have the correct URLs for the authentication, job feed, password reset, and seeker form APIs.

5. Run the Application:
Connect an Android device to your computer or start an emulator. Click the Run button in Android Studio to compile and install the application on the device.

## Usage
Once the application is installed, follow these steps to use the Hustle4Work app:

1. Launch the Application:
Open the Hustle4Work app on your device. You will be greeted with the splash screen.

2. Login or Sign Up:

If you are a new user, click on the Sign Up link and fill out the registration form. Choose whether you are a job seeker or an employer, and provide the necessary details.
If you are an existing user, enter your username and password and click Login.
3. Reset Password:
If you have forgotten your password, click on the Forgot Password link. Enter your registered email to receive an OTP, then use the OTP to reset your password.

4. Navigate the App:

Job Feed: View the latest job postings by navigating to the Job Feed section from the navigation drawer.
Profile Management: Update your profile information by navigating to the Profile section.
About: Learn more about the application in the About section.
Logout: Log out of your account by selecting the Logout option.
Submit Seeker Form:
If you are a job seeker, fill out the seeker form with your education, skills, and experience details. Validate the input fields and submit the form to apply for jobs.

5. Check Connectivity:
The application will notify you with toast messages if there is no internet connection when attempting to perform actions like login, reset password, or form submission.

By following these steps, you will be able to effectively use the Hustle4Work application to search for jobs or recruit candidates.
   

## LoginActivity.java
The LoginActivity.java file is responsible for handling user login functionality in the application. Here's a breakdown of its key functionalities:

It initializes UI elements such as EditText fields for username and password, TextViews for sign-up and forgot password, and a Button for login.
It includes a method validateFields() to validate the input fields (username and password) entered by the user.
The validateFields() method checks if the username and password are not empty and then checks for internet connectivity using the CheckInternetConnection class.
If internet connectivity is available, it calls the WebApiCall class to perform the login operation.
It displays a toast message if no internet connection is available.

## MainActivity.java
The MainActivity.java file serves as the main activity of the application and manages the navigation and fragments within the app. Here's a summary of its functionalities:

It sets up a navigation drawer with options such as "Job Feed," "Profile," "About," and "Logout."
It initializes RecyclerView for the navigation drawer and sets up a click listener to handle navigation item clicks.
It loads fragments based on the selected navigation item, such as "JobsFeed," "ProfileFragment," and "About."
It contains a method prepareAlbums() to populate the navigation drawer items with icons and labels.

## ResetPassword.java
The ResetPassword.java file handles the password reset functionality for the application. Key functionalities include:

It initializes EditText fields for OTP, new password, and confirm password.
It includes a method validateField() to validate the input fields (OTP, new password, and confirm password) entered by the user.
The validateField() method checks if the fields are not empty, if the passwords match, and then calls the callResetApi() method.
It displays a toast message if no internet connection is available and handles the reset password API call.

## SeekerForm.java
The SeekerForm.java file manages the seeker form submission in the application. Key functionalities include:

It initializes EditText fields for various user details such as education, skills, and experience.
It includes a method validateFields() to validate all the input fields entered by the user.
The validateFields() method checks if all required fields are filled and then calls the callSeekerApi() method.
It displays a toast message if no internet connection is available and handles the seeker form submission API call.

## SignUpActivity.java
The SignUpActivity.java file handles user registration functionality in the application. Here's a breakdown of its key functionalities:

It initializes EditText fields for username, email, password, phone number, and address.
It includes a method validateFields() to validate all the input fields entered by the user.
The validateFields() method checks if all required fields are filled, passwords match, and a user type (employer or job seeker) is selected.
It displays a toast message if any validation fails and handles the user registration API call.

## SplashActivity.java
The SplashActivity.java file represents the splash screen activity displayed when the application is launched. Here's a summary of its functionalities:

It displays a splash screen for a predefined duration using a Handler with a delay.
Based on the user's login status (stored in CSPreferences), it redirects to either the MainActivity or LoginActivity.
It checks the login status using CSPreferences.getBoolean() and starts the appropriate activity accordingly.
