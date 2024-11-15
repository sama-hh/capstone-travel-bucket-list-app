import {Container, Row} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {useEffect, useState} from "react";
import {DashboardType} from "../types/Dashboard.ts";
import DashboardCard from "../components/DashboardCard.tsx";
import DashboardItineraryCard from "../components/DashboardItineraryCard.tsx";

const Dashboard = () => {
    const [data, setData] = useState<DashboardType>({
        totalDestinations: 0,
        visitedDestinations: 0,
        totalItineraries: 0,
        lastCreatedItinerary: null,
    });
    const [loading, setLoading] = useState(false)

    const fetchData = () => {
        setLoading(true);

        axios.get("/api/dashboard/statistics")
            .then((response) => {
                setData(response.data);
                setLoading(false);
            })
            .catch((error: AxiosError) => {
                console.error(error);
            });
    }

    useEffect(() => {
        fetchData();
    }, [])

    return (
        <Container className="custom-container">
            <Row className="mt-3">
                <DashboardCard loading={loading} title="Total Destinations"
                               statistics={data.totalDestinations}/>
                <DashboardCard loading={loading} title="Visited Destinations"
                               statistics={data.visitedDestinations}/>
            </Row>
            <Row>
                <DashboardCard loading={loading} title="Total Itineraries"
                               statistics={data.totalItineraries}/>
                <DashboardItineraryCard loading={loading} itinerary={data.lastCreatedItinerary} />
            </Row>
        </Container>
    );
};

export default Dashboard;