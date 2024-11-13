import {Badge, Button, Card, Col, ListGroup, Row} from "react-bootstrap";
import {DestinationFormProps} from "../types/Itinerary.tsx";
import {dateFormat, timeFormat} from "../service/dateFormat.ts";
import axios, {AxiosError} from "axios";

const Destination = ({item, setHasChanged}: DestinationFormProps) => {
    const handleRemoveDestination = (index: number) => {
        const updatedDestinations = item.destinations.filter((_, i) => i !== index);
        const updatedItinerary = {...item, destinations: updatedDestinations};

        axios.put(`/api/itineraries/${item.id}`, updatedItinerary)
            .then(() => {
                setHasChanged((state: boolean) => !state);
            })
            .catch((error: AxiosError) => console.log(error));
    };
    return (
        <Row>
            <Col className="mt-4">
                <Card className="text-start">
                    <Card.Header as="h5">{item.name}</Card.Header>
                    <Card.Body>
                        <p><strong>Start Date:</strong> {dateFormat(item.startDate)}</p>
                        <p><strong>End Date:</strong> {dateFormat(item.endDate)}</p>
                        <p><strong>Estimated Cost:</strong> {item.estimatedCost}€</p>
                        <Card.Subtitle className="mt-3 mb-2 text-secondary">Destinations</Card.Subtitle>
                        <ListGroup variant="flush">
                            {item.destinations.length ? (
                                item.destinations.map((destination, index) => (
                                    <ListGroup.Item key={index} className="destination-list">
                                        <div className="destination-list__info">
                                            <Badge bg="info" className="me-2 badge"/>
                                            <div>
                                                <p>{destination.destinationName}</p>
                                                <p>
                                                    <strong>From:</strong> {dateFormat(destination.departureTime)} at {timeFormat(destination.departureTime)}
                                                </p>
                                                <p>
                                                    <strong>To:</strong> {dateFormat(destination.arrivalTime)} at {timeFormat(destination.arrivalTime)}
                                                </p>
                                            </div>
                                        </div>
                                        <Button variant="danger" size="sm" className="ms-2"
                                                onClick={() => handleRemoveDestination(index)}
                                        >
                                            &times;
                                        </Button>
                                    </ListGroup.Item>
                                ))
                            ) : (
                                <ListGroup.Item>No destinations listed</ListGroup.Item>
                            )}
                        </ListGroup>
                    </Card.Body>
                </Card>
                <div className="text-start mt-3 text-secondary">
                    <p>Last updated: {dateFormat(item.lastModifiedDate)} at {timeFormat(item.lastModifiedDate)}</p>
                </div>
            </Col>
        </Row>
    );
};

export default Destination;