import {Container, Row} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {useEffect, useState} from "react";
import {Destinations} from "../types/Dashboard.ts";
import DashboardCard from "../components/DashboardCard.tsx";

const Dashboard = () => {
    const [destinationInfo, setDestinationsInfo] = useState<Destinations | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    const getDestinationsCount = () => {
        setLoading(true);
        axios.get("/api/dashboard/total-destinations")
            .then(response => {
                setDestinationsInfo(response.data);
            })
            .catch((error: AxiosError) => {
                console.log(error)
            })
            .finally(() => {
                setLoading(false);
            });
    }

    useEffect(() => {
        getDestinationsCount();
    }, [])

    return (
        <Container className="custom-container">
                <Row className="mt-3">
                    <DashboardCard loading={loading} title="Total Destinations" destinationCount={destinationInfo?.totalDestinations}/>
                    <DashboardCard loading={loading} title="Visited Destinations " destinationCount={destinationInfo?.visitedDestinations}/>
                </Row>
        </Container>
    );
};

export default Dashboard;