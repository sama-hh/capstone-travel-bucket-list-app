import {Card, Col, ListGroup, Row} from "react-bootstrap";
import {ItineraryType} from "../types/Itinerary.tsx";
import {dateFormat, timeFormat} from "./dateFormat.ts";

const Destination = ({item}: { item: ItineraryType }) => {
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
                            {/*{item.destinations.length ? (*/}
                            {/*    item.destinations.map((destination, index) => (*/}
                            {/*        <ListGroup.Item key={index}>*/}
                            {/*            <Badge bg="info" className="me-2">{index + 1}</Badge>*/}
                            {/*            {destination}*/}
                            {/*        </ListGroup.Item>*/}
                            {/*    ))*/}
                            {/*) : (*/}
                            {/*    <ListGroup.Item>No destinations listed</ListGroup.Item>*/}
                            {/*)}*/}
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