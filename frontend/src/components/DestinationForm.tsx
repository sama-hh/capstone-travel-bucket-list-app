import {Button, Col, Form, Row} from "react-bootstrap";
import {ChangeEvent, FormEvent, useState} from "react";
import axios, {AxiosError} from "axios";
import {DestinationFormProps} from "../types/Itinerary.tsx";
import {generateShortId} from "../service/generateId.ts";

const DestinationForm = ({item, setHasChanged}: DestinationFormProps) => {
    const [newDestination, setNewDestination] = useState({
        destinationId: '',
        destinationName: '',
        arrivalTime: '',
        departureTime: ''
    });

    const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setNewDestination({...newDestination, [name]: value});
    };

    const handleAddDestination = (e: FormEvent) => {
        e.preventDefault();
        const updatedItinerary = {...item, destinations: [...item.destinations, {...newDestination, destinationId: generateShortId()}]}

        axios.put(`/api/itineraries/${item.id}`, updatedItinerary)
            .then(() => {
                setHasChanged((state: boolean) => !state);
            })
            .catch((error: AxiosError) => console.log(error))
            .finally(() => {
                setNewDestination({destinationId: '', destinationName: '', arrivalTime: '', departureTime: ''});
            });

    };

    return (
        <Col>
            <h5 className="text-start mb-3">Add New Destination</h5>
            <Form onSubmit={handleAddDestination}>
                <Row className="align-items-center">
                    <Col style={{flex: 0.4}}>
                        <Form.Group controlId="destinationName">
                            <Form.Control
                                type="text"
                                name="destinationName"
                                value={newDestination.destinationName}
                                onChange={handleInputChange}
                                placeholder="Destination Name"
                                required
                            />
                        </Form.Group>
                    </Col>
                    <Col style={{flex: 0.25}}>
                        <Form.Group controlId="arrivalTime">
                            <Form.Control
                                type="datetime-local"
                                name="arrivalTime"
                                value={newDestination.arrivalTime}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                    </Col>
                    <Col style={{flex: 0.25}}>
                        <Form.Group controlId="departureTime">
                            <Form.Control
                                type="datetime-local"
                                name="departureTime"
                                value={newDestination.departureTime}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                    </Col>
                    <Col xs="auto" style={{flex: 0.10}}>
                        <Button className="w-100" variant="primary" type="submit">
                            Add
                        </Button>
                    </Col>
                </Row>
            </Form>
        </Col>

    );
};

export default DestinationForm;