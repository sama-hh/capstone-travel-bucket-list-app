import {Button, Container} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {useEffect, useState} from "react";
import ItineraryModal from "../components/ItineraryModal.tsx";
import {defaultItineraryItem, ItineraryType} from "../types/Itinerary.tsx";
import ItinerariesList from "../components/ItinerariesList.tsx";

const Itinerary = () => {
    const [itineraries, setItineraries] = useState<ItineraryType[]>([]);
    const [isModalOpen, setModalOpen] = useState<boolean>(false);
    const [hasChanged, setHasChanged] = useState<boolean>(false);
    const [itinerary, setItinerary] = useState<ItineraryType>(defaultItineraryItem);

    const getItineraries = () => {
        axios.get("/api/itineraries")
            .then(response => {
                setItineraries(response.data);
            })
            .catch((error: AxiosError) => {
                console.log(error)
            });
    }

    useEffect(() => {
        getItineraries();
    }, [hasChanged]);

    return (
        <>
            <Container className="custom-container">
                <div className="mt-4 bucket-list_header">
                    <h4>Travel plans</h4>
                    <Button onClick={() => setModalOpen(prev => !prev)}>Add</Button>
                </div>
                <ItinerariesList itineraries={itineraries} setModalOpen={setModalOpen}
                                 setItinerary={setItinerary} setHasChanged={setHasChanged}/>
            </Container>

            <ItineraryModal
                show={isModalOpen}
                setHasChanged={setHasChanged}
                handleClose={() => setModalOpen(false)}
                itinerary={itinerary}
                setItinerary={setItinerary}
            />

        </>
    );
};

export default Itinerary;