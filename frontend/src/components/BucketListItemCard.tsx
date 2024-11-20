import {Button, Card} from "react-bootstrap";
import {BucketListItemCardProps, BucketListItemStatus} from "../types/BucketList.ts";

const BucketListItemCard = ({item, onEdit, onDelete}: BucketListItemCardProps) => {
    return (
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
                    <Button variant="primary" className="btn-sm" onClick={() => onEdit(item)}>
                        Edit
                    </Button>
                    <Button variant="danger" className="btn-sm" onClick={() => onDelete(item)}>
                        Remove
                    </Button>
                </div>
            </Card.Body>
        </Card>
    );
};

export default BucketListItemCard;