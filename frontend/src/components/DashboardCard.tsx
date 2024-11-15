import {Card, Col} from "react-bootstrap";
import {DashboardCardProps} from "../types/Dashboard.ts";
import Skeleton from 'react-loading-skeleton'

const DashboardCard = ({title, statistics, loading}: DashboardCardProps) => {
    return (
        <Col sm={12} md={6} lg={6} className="mb-4">
            {loading ? (
                <Skeleton height={100} borderRadius={8} baseColor="#F8F8F8" />
            ) : (
                <Card className="dashboard-card shadow-sm">
                    <Card.Body>
                        <Card.Title className="text-start fw-bold">
                            {title}
                        </Card.Title>
                        <Card.Text className="text-start">{statistics}</Card.Text>
                    </Card.Body>
                </Card>)}
        </Col>
    );
};

export default DashboardCard;