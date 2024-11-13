import {Navigate, Outlet} from 'react-router-dom';
import {Username} from "../types/Header.ts";

interface ProtectedRouteProps {
    username: Username;
}

const ProtectedRoute = ({username}: ProtectedRouteProps) => {
    if (!username.id) {
        return <Navigate to="/login" replace/>;
    }
    return <Outlet/>;
};

export default ProtectedRoute;
