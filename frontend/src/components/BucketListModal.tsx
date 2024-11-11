import {ChangeEvent, FormEvent} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {
    BucketListItemStatus, BucketListModalProps,
    defaultBucketListItem
} from "../types/BucketList.ts";

const BucketListModal = ({
                             show,
                             handleClose,
                             setHasChanged,
                             bucketListItem,
                             setBucketListItem
                         }: BucketListModalProps) => {
    const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setBucketListItem({...bucketListItem, [name]: value});
    };

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();

        const apiUrl = bucketListItem.id ? `/api/bucket-lists/${bucketListItem.id}` : '/api/bucket-lists';
        const apiMethod = bucketListItem.id ? 'put' : 'post';

        axios[apiMethod](apiUrl, bucketListItem)
            .then(() => {
                setHasChanged((state: boolean) => !state);
            })
            .catch((error: AxiosError) => console.log(error))
            .finally(() => {
                setBucketListItem(defaultBucketListItem);
            });

        handleClose();
    }

    const handleCancel = () => {
        setBucketListItem(defaultBucketListItem);
        handleClose();
    }

    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header>
                <Modal.Title>{bucketListItem.id ? 'Edit a destination' : 'Create a new destination'}</Modal.Title>
            </Modal.Header>
            <Form onSubmit={handleSubmit}>
                <Modal.Body>
                    <Form.Group className="mb-3" controlId="formName">
                        <Form.Label>Name</Form.Label>
                        <Form.Control
                            type="text"
                            name="name"
                            value={bucketListItem.name}
                            onChange={handleInputChange}
                            required
                            autoFocus
                        />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formCountry">
                        <Form.Label>Country</Form.Label>
                        <Form.Control
                            type="text"
                            name="country"
                            value={bucketListItem.country}
                            onChange={handleInputChange}
                            required
                        />
                    </Form.Group>
                    <div className="form-checkbox">
                        <Form.Check
                            inline
                            type="radio"
                            id="not-visited"
                            label="Not visited"
                            name="status"
                            value={BucketListItemStatus.NOT_VISITED}
                            checked={bucketListItem.status === BucketListItemStatus.NOT_VISITED}
                            onChange={handleInputChange}
                        />
                        <Form.Check
                            inline
                            className="mx-2"
                            type="radio"
                            id="visited"
                            label="Visited"
                            name="status"
                            value={BucketListItemStatus.VISITED}
                            checked={bucketListItem.status === BucketListItemStatus.VISITED}
                            onChange={handleInputChange}
                        />
                    </div>
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

export default BucketListModal;