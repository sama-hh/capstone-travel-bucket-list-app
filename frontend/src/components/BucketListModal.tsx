import {ChangeEvent, FormEvent} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {
    BucketListItemStatus,
    defaultBucketListItem,
    ListModalProps
} from "../types/BucketList.ts";

const ListModal = ({show, handleClose, setHasChanged, bucketListItem, setBucketListItem}: ListModalProps) => {
    const handleStatusChange = (event: ChangeEvent<HTMLInputElement>) => {
        setBucketListItem({...bucketListItem, status: event.target.value as BucketListItemStatus})
    };

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();

        const apiUrl = bucketListItem.id ? `/api/bucket-lists/${bucketListItem.id}` : '/api/bucket-lists';
        const apiMethod = bucketListItem.id ? 'put' : 'post';

        axios[apiMethod](apiUrl, bucketListItem)
            .then(() => {
                console.log(bucketListItem);
                setBucketListItem(defaultBucketListItem);
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
                            value={bucketListItem.name}
                            onChange={(e) => setBucketListItem({...bucketListItem, name: e.target.value})}
                            autoFocus
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput2">
                        <Form.Label>Country</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            value={bucketListItem.country}
                            onChange={(e) => setBucketListItem({...bucketListItem, country: e.target.value})}
                        />
                    </Form.Group>
                    <div className="form-checkbox">
                        <Form.Check
                            inline
                            type="radio"
                            id="not-visited"
                            label="Not visited"
                            name="group1"
                            value={BucketListItemStatus.NOT_VISITED}
                            checked={bucketListItem.status === BucketListItemStatus.NOT_VISITED}
                            onChange={handleStatusChange}
                        />
                        <Form.Check
                            inline
                            className="mx-2"
                            type="radio"
                            id="visited"
                            label="Visited"
                            name="group1"
                            value={BucketListItemStatus.VISITED}
                            checked={bucketListItem.status === BucketListItemStatus.VISITED}
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