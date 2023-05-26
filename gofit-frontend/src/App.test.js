import { render, screen } from '@testing-library/react';
import Registracija from './components/RegisterComponent';
import Login from './components/LoginComp';
import Home from './components/First';
import AdminRecipe from './components/Admin/AdminRecepies';
import AdminTraining from './components/Admin/AdminTrainings';
import AdminExercise from './components/Admin/AdminExercises';
import AdminDashboard from './components/Admin/AdminDashboard';


import { BrowserRouter as Router } from 'react-router-dom';

        test('renders react component', async () => {
            render(
                    <Router>
                    <Registracija />
                    </Router>

            );

    expect(screen.getByText("Create an account")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Name")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Last name")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Email address")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Password")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Username")).toBeInTheDocument();
    expect(screen.getByText("Already have an account?")).toBeInTheDocument();
});


test('test login component', async () => {
    render(
        <Router>
            <Login />
        </Router>

    );

    expect(screen.getByText("Sign into your account")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Email")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Password")).toBeInTheDocument();
    expect(screen.getByText("Dont have an account?")).toBeInTheDocument();

});


test('test admin recipes component', async () => {
    render(
        <Router>
            <AdminRecipe />
        </Router>

    );

    expect(screen.getByText("Recipes")).toBeInTheDocument();
    expect(screen.getByText("Subject")).toBeInTheDocument();
    expect(screen.getByText("Description")).toBeInTheDocument();
    expect(screen.getByText("Image")).toBeInTheDocument();

});

test('test admin dashboard component', async () => {
    render(
        <Router>
            <AdminDashboard />
        </Router>

    );

    expect(screen.getByText("Manage data")).toBeInTheDocument();
    expect(screen.getByText("Training")).toBeInTheDocument();
    expect(screen.getByText("Exercises")).toBeInTheDocument();
    expect(screen.getByText("Users")).toBeInTheDocument();
    expect(screen.getByText("Diet plan recipes")).toBeInTheDocument();
});
















