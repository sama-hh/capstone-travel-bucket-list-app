import './styles/App.css'
import Header from "./components/Header.tsx";
import Footer from "./components/Footer.tsx";
import {Navigate, Route, Routes} from "react-router-dom";
import Dashboard from "./pages/Dashboard.tsx";
import BucketList from "./pages/BucketList.tsx";
import Itinerary from "./pages/Itinerary.tsx";
import ItineraryDetails from "./pages/ItineraryDetails.tsx";
import Login from "./pages/Login.tsx";

function App() {
    return (
        <>
            <div className="d-flex flex-column min-vh-100">
                <Header/>
                <Routes>
                    <Route path="/" element={<Navigate to="/dashboard" replace />} />
                    <Route path="/dashboard" element={<Dashboard/>}/>
                    <Route path="/bucket-list" element={<BucketList/>}/>
                    <Route path="/itineraries" element={<Itinerary/>}/>
                    <Route path="/itineraries/:id" element={<ItineraryDetails/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="*" element={<h1>404 - Page Not Found</h1>} />
                </Routes>
                <Footer/>
            </div>
        </>
    )
}

export default App
