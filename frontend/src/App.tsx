import './styles/App.css'
import Header from "./components/Header.tsx";
import Footer from "./components/Footer.tsx";
import {Navigate, Route, Routes, useNavigate} from "react-router-dom";
import Dashboard from "./pages/Dashboard.tsx";
import BucketList from "./pages/BucketList.tsx";
import Itinerary from "./pages/Itinerary.tsx";
import ItineraryDetails from "./pages/ItineraryDetails.tsx";
import Login from "./pages/Login.tsx";
import ProtectedRoute from "./components/Protected.route.tsx";
import {useEffect, useState} from "react";
import axios from "axios";
import {defaultUsername, Username} from "./types/Header.ts";

function App() {
    const [username, setUsername] = useState<Username>(defaultUsername);
    const navigate = useNavigate();

    const loadUser = () => {
        axios.get('/api/auth/me')
            .then(response => {
                setUsername(response.data);
                navigate("/");
            })
            .catch((error) => {
                console.log(error);
            });
    };

    useEffect(() => {
        loadUser();
    }, []);

    return (
        <>
            <div className="d-flex flex-column min-vh-100">
                <Header username={username} setUsername={setUsername}/>
                <Routes>
                    <Route path="/" element={<Navigate to="/dashboard" replace/>}/>
                    <Route path="/login" element={<Login/>}/>

                    <Route element={<ProtectedRoute username={username}/>}>
                        <Route path="/dashboard" element={<Dashboard/>}/>
                        <Route path="/bucket-list" element={<BucketList/>}/>
                        <Route path="/itineraries" element={<Itinerary/>}/>
                        <Route path="/itineraries/:id" element={<ItineraryDetails/>}/>
                    </Route>

                </Routes>
                <Footer/>
            </div>
        </>
    )
}

export default App
