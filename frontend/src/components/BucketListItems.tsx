import {Button, Card, Col, Row} from "react-bootstrap";
import {
    BucketListItemsProps,
    BucketListItemStatus,
    BucketListItemType
} from "../types/BucketList.ts";

const BucketListItems = ({bucketList, setModalOpen, setBucketListItem}: BucketListItemsProps) => {

    const handleEdit = (item: BucketListItemType) => {
        setBucketListItem(item);
        setModalOpen(prev => !prev);
    }

    return (
        <Row className="mt-3">
            {bucketList.map((item) => (
                <Col key={item.id} sm={12} md={6} lg={3} className="mb-4">
                    <Card className="bucket-card shadow-sm">
                        <Card.Body>
                            <Card.Title className="text-start fw-bold">
                                {item.name}
                            </Card.Title>
                            <Card.Subtitle className="mb-2 text-muted text-start">{item.country}</Card.Subtitle>
                            <Card.Text className="text-start">
                                <span
                                    className={`badge ${item.status === BucketListItemStatus.VISITED ? 'bg-success' : 'bg-warning'}`}>
                                {item.status}
                                </span>
                            </Card.Text>
                            <div className="d-flex justify-content-end gap-2">
                                <Button variant="primary" className="btn-sm" onClick={() => handleEdit(item)}>
                                    Edit
                                </Button>
                                <Button variant="danger" className="btn-sm">
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

export default BucketListItems;