import ReactDOM from 'react-dom';
import React from 'react';
import './index.css';
import App from './App';
import UserSignupPage from  './allPages/UserSignUpPage';
import reportWebVitals from './reportWebVitals';
import LoginPage from './allPages/LoginPage';

ReactDOM.render(<LoginPage />, document.getElementById('root'));

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();