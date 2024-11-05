import {ChangeEvent, FormEvent, useState} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {BucketListStatus, NewBucketListType} from "../types/BucketList.ts";

type ListModalProps = {
    show: boolean,
    handleClose: () => void,
    setHasChanged: (value: (prev: boolean) => boolean) => void;
}

const ListModal = ({show, handleClose, setHasChanged}: ListModalProps) => {
    const [name, setName] = useState("");
    const [country, setCountry] = useState("");
    const [status, setStatus] = useState<BucketListStatus>(BucketListStatus.NOT_VISITED);

    const handleStatusChange = (event: ChangeEvent<HTMLInputElement>) => {
        setStatus(event.target.value as BucketListStatus);
    };

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        const newBucketList: NewBucketListType = {
            name: name,
            country: country,
            status: status
        }

        axios.post("/api/bucket-lists", newBucketList)
            .then(() => {
                console.log(newBucketList);
                setName("");
                setCountry("");
                setStatus(BucketListStatus.NOT_VISITED);
                setHasChanged((state: boolean) => !state);
            })
            .catch((error: AxiosError) => console.log(error));

        handleClose();
    }

    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Add a new destination</Modal.Title>
            </Modal.Header>
            <Form onSubmit={handleSubmit}>
                <Modal.Body>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Name</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            autoFocus
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput2">
                        <Form.Label>Country</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            value={country}
                            onChange={(e) => setCountry(e.target.value)}
                        />
                    </Form.Group>
                    <div className="form-checkbox">
                        <Form.Check
                            inline
                            type="radio"
                            id="not-visited"
                            label="Not visited"
                            name="group1"
                            value={BucketListStatus.NOT_VISITED}
                            checked={status === BucketListStatus.NOT_VISITED}
                            onChange={handleStatusChange}
                        />
                        <Form.Check
                            inline
                            className="mx-2"
                            type="radio"
                            id="visited"
                            label="Visited"
                            name="group1"
                            value={BucketListStatus.VISITED}
                            checked={status === BucketListStatus.VISITED}
                            onChange={handleStatusChange}
                        />
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
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

export default ListModal;