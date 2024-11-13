import {useLocation} from "react-router-dom";
import {Container} from "react-bootstrap";
import DestinationForm from "../components/DestinationForm.tsx";
import Destination from "../components/Destination.tsx";
import {useEffect, useState} from "react";
import axios, {AxiosError} from "axios";
import {defaultItineraryItem} from "../types/Itinerary.tsx";

const ItineraryDetails = () => {
    const [hasChanged, setHasChanged] = useState<boolean>(false);
    const [itinerary, setItinerary] = useState(defaultItineraryItem);
    const location = useLocation();
    const {item} = location.state || {};

    const getItinerary = () => {
        axios.get(`/api/itineraries/${item.id}`)
            .then(response => {
                setItinerary(response.data);
                // console.log(itinerary);
            })
            .catch((error: AxiosError) => {
                console.log(error)
            });
    }

    useEffect(() => {
        getItinerary();
    }, [hasChanged]);

    return (
        <Container className="custom-container mt-3">
            <DestinationForm item={itinerary} setHasChanged={setHasChanged}/>
            <Destination item={itinerary} setHasChanged={setHasChanged}/>
        </Container>
    );
};

export default ItineraryDetails;