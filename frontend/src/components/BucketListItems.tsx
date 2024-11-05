import {Button, Card, Col, Row} from "react-bootstrap";
import {BucketListStatus, BucketListType} from "../types/BucketList.ts";

type BucketListItemsProps = {
    bucketList: BucketListType[]
}
const BucketListItems = ({bucketList}: BucketListItemsProps) => {
    return (
        <Row className="justify-content-center mt-3">
            {bucketList.map((item) => (
                <Col key={item.id} sm={12} md={6} lg={4} className="mb-4">
                    <Card className="bucket-card shadow-sm">
                        <Card.Body>
                            <Card.Title>
                                {item.name}
                            </Card.Title>
                            <Card.Subtitle className="mb-2 text-muted">{item.country}</Card.Subtitle>
                            <Card.Text>
                  <span className={`badge ${item.status === BucketListStatus.VISITED ? 'bg-success' : 'bg-warning'}`}>
                    {item.status}
                  </span>
                            </Card.Text>
                            <div className="d-flex justify-content-between">
                                <Button variant="primary" className="me-2">
                                    Edit
                                </Button>
                                <Button variant="danger" className="me-2">
                                    Remove
                                </Button>
                                <Button variant="success">
                                    Mark as Visited
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