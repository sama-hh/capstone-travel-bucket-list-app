import {Button, Card, Col, Row} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {ItinerariesListProps, ItineraryType} from "../types/Itinerary.tsx";
import {useNavigate} from "react-router-dom";

const ItinerariesList = ({itineraries, setModalOpen, setItinerary, setHasChanged}: ItinerariesListProps) => {
    const navigate = useNavigate();

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

    const formatDate = (timestamp: string) => {
        return new Date(timestamp).toLocaleString('en-US', {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
        })
    }

    const handleItinerary = (item: ItineraryType) => {
        navigate(`/itineraries/${item.id}`, {state: {item}});
    }

    return (
        <Row className="mt-3">
            {itineraries.map((item) => (
                <Col key={item.id} sm={12} md={6} lg={4} className="mb-4">
                    <Card className="shadow-sm">
                        <Card.Body>
                            <Card.Title className="text-start fw-bold card-name" onClick={() => handleItinerary(item)}>
                                {item.name}
                            </Card.Title>
                            <Card.Subtitle className="mb-2 text-muted text-start">
                                From {formatDate(item.startDate)} to {formatDate(item.endDate)}
                            </Card.Subtitle>
                            <Card.Subtitle className="mb-2 text-muted text-start">
                                Estimated cost: {item.estimatedCost}€
                            </Card.Subtitle>
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