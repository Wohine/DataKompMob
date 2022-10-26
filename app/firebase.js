// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyC7YmGbFAYmbcy0rEGl5pgGxORcQDLyuzo",
  authDomain: "datakompkotlin.firebaseapp.com",
  databaseURL: "https://datakompkotlin-default-rtdb.europe-west1.firebasedatabase.app",
  projectId: "datakompkotlin",
  storageBucket: "datakompkotlin.appspot.com",
  messagingSenderId: "220991526486",
  appId: "1:220991526486:web:8019de4ecdddd9cf64cdf7",
  measurementId: "G-N1F5H9HNPG"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);