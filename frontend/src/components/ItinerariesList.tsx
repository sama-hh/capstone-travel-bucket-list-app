import {Button, Card, Col, Row} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {ItinerariesListProps, ItineraryType} from "../types/Itinerary.tsx";

const ItinerariesList = ({itineraries, setModalOpen, setItinerary, setHasChanged}: ItinerariesListProps) => {

    const handleEdit = (item: ItineraryType) => {
        setItinerary(item);
        setModalOpen(prev => !prev);
    }

    const handleDelete = (item: ItineraryType) => {
        axios.delete(`api/itineraries/${item.id}`)
            .then(() => {
                setHasChanged((state: boolean) => !state);
            })
            .catch((error: AxiosError) => {
                console.log(error)
            });
    }

    return (
        <Row className="mt-3">
            {itineraries.map((item) => (
                <Col key={item.id} sm={12} md={6} lg={3} className="mb-4">
                    <Card className="bucket-card shadow-sm">
                        <Card.Body>
                            <Card.Title className="text-start fw-bold">
                                {item.name}
                            </Card.Title>
                            {/*<Card.Subtitle className="mb-2 text-muted text-start">{item.country}</Card.Subtitle>*/}
                            <div className="d-flex justify-content-end gap-2">
                                <Button variant="primary" className="btn-sm" onClick={() => handleEdit(item)}>
                                    Edit
                                </Button>
                                <Button variant="danger" className="btn-sm" onClick={() => handleDelete(item)}>
                                    Remove
                                </Button>
                            </div>
                        </Card.Body>
                    </Card>
                </Col>
            ))}
        </Row>
    );
};

export default ItinerariesList;