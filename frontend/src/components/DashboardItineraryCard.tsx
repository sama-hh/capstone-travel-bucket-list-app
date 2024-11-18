import {Card, Col} from "react-bootstrap";
import {DashboardItineraryCardProps} from "../types/Dashboard.ts";
import Skeleton from 'react-loading-skeleton'

const DashboardItineraryCard = ({itinerary, loading}: DashboardItineraryCardProps) => {
    return (
        <Col sm={12} md={6} lg={6} className="mb-4">
            {loading ? (
                <Skeleton height={100} borderRadius={8} baseColor="#F8F8F8"/>
            ) : (
                <Card className="dashboard-card shadow-sm">
                    <Card.Body>
                        <Card.Title className="text-start fw-bold">
                            Last Added Travel Plan
                        </Card.Title>
                        <Card.Text className="text-start">{itinerary?.name}</Card.Text>
                    </Card.Body>
                </Card>)}
        </Col>
    );
};

export default DashboardItineraryCard;