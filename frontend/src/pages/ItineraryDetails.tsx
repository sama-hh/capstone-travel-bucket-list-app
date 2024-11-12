import {useLocation} from "react-router-dom";
import {useEffect} from "react";
import {Container} from "react-bootstrap";
import DestinationForm from "../components/DestinationForm.tsx";
import Destination from "../components/Destination.tsx";

const ItineraryDetails = () => {
    const location = useLocation();
    const {item} = location.state || {};

    useEffect(() => {
        console.log(item)
    }, []);
    return (
        <Container className="custom-container mt-3">
            <DestinationForm/>
            <Destination item={item}/>
        </Container>
    );
};

export default ItineraryDetails;