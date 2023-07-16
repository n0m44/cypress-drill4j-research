import React from 'react';
import './styles/App.css';
import { LoginRegistrationForm } from './components/LoginRegistrationForm';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { UserList } from './components/UserList';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={
          <LoginRegistrationForm 
            actinTitle='Sign up'
            alternativeMsg='Do you already have an account?'
            alternativeAction='Sign in'
            alternativePath='/login'
          />}
        />
        <Route path='/registration' element={
          <LoginRegistrationForm 
            actinTitle='Sign up'
            alternativeMsg='Do you already have an account?'
            alternativeAction='Sign in'
            alternativePath='/login'
          />} 
        />
        <Route path='/login' element={
          <LoginRegistrationForm 
            actinTitle='Sign in'
            alternativeMsg="Don't have an account yet?"
            alternativeAction='Sign up'
            alternativePath='/registration'
          />} 
        />
        {/* list users */}
        <Route path='/users' element={<UserList />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
