import {ChangeEvent, FormEvent} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {defaultItineraryItem, ItineraryModalProps} from "../types/Itinerary.tsx";

const ItineraryModal = ({show, handleClose, setHasChanged, itinerary, setItinerary}: ItineraryModalProps) => {
    const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setItinerary({...itinerary, [name]: value});
    };

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();

        const apiUrl = itinerary.id ? `/api/itineraries/${itinerary.id}` : '/api/itineraries';
        const apiMethod = itinerary.id ? 'put' : 'post';

        axios[apiMethod](apiUrl, itinerary)
            .then(() => {
                console.log(itinerary);
                setHasChanged((state: boolean) => !state);
            })
            .catch((error: AxiosError) => console.log(error))
            .finally(() => {
                setItinerary(defaultItineraryItem);
            });

        handleClose();
    }

    const handleCancel = () => {
        setItinerary(defaultItineraryItem);
        handleClose();
    }

    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header>
                <Modal.Title>{itinerary.id ? 'Edit an itinerary' : 'Create a new itinerary'}</Modal.Title>
            </Modal.Header>
            <Form onSubmit={handleSubmit}>
                <Modal.Body>
                    <Form.Group className="mb-3" controlId="formName">
                        <Form.Label>Name</Form.Label>
                        <Form.Control
                            type="text"
                            name="name"
                            value={itinerary.name}
                            onChange={handleInputChange}
                            required
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formStartDate">
                        <Form.Label>Start Date</Form.Label>
                        <Form.Control
                            type="datetime-local"
                            name="startDate"
                            value={itinerary.startDate}
                            onChange={handleInputChange}
                            required
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formEndDate">
                        <Form.Label>End Date</Form.Label>
                        <Form.Control
                            type="datetime-local"
                            name="endDate"
                            value={itinerary.endDate}
                            onChange={handleInputChange}
                            required
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formEstimatedCost">
                        <Form.Label>Estimated Cost ( € )</Form.Label>
                        <Form.Control
                            type="number"
                            name="estimatedCost"
                            value={itinerary.estimatedCost}
                            onChange={handleInputChange}
                            required
                        />
                    </Form.Group>

                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => handleCancel()}>
                        Cancel
                    </Button>
                    <Button variant="primary" type="submit">
                        Save
                    </Button>
                </Modal.Footer>
            </Form>

        </Modal>
    )

}

export default ItineraryModal;