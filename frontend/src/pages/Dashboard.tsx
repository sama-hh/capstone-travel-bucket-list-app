import {Container, Row} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {useEffect, useState} from "react";
import {DashboardType} from "../types/Dashboard.ts";
import DashboardCard from "../components/DashboardCard.tsx";
import DashboardItineraryCard from "../components/DashboardItineraryCard.tsx";

const Dashboard = () => {
    const [data, setData] = useState<DashboardType>({
        destinationsInfo: null,
        itinerariesInfo: null,
        loading: true,
    });

    const fetchData = () => {
        setData(prev => ({...prev, loading: true}));

        Promise.all([
            axios.get("/api/dashboard/total-destinations"),
            axios.get("/api/dashboard/total-itineraries")
        ])
            .then(([destinationsResponse, itinerariesResponse]) => {
                setData({
                    destinationsInfo: destinationsResponse.data,
                    itinerariesInfo: itinerariesResponse.data,
                    loading: false,
                });
            })
            .catch((error: AxiosError) => {
                console.error(error);
                setData(prev => ({...prev, loading: false}));
            });
    }

    useEffect(() => {
        fetchData();
    }, [])

    return (
        <Container className="custom-container">
            <Row className="mt-3">
                <DashboardCard loading={data.loading} title="Total Destinations"
                               destinationCount={data.destinationsInfo?.totalDestinations}/>
                <DashboardCard loading={data.loading} title="Visited Destinations"
                               destinationCount={data.destinationsInfo?.visitedDestinations}/>
            </Row>
            <Row>
                <DashboardItineraryCard loading={data.loading} itinerariesCount={data.itinerariesInfo}/>
            </Row>
        </Container>
    );
};

export default Dashboard;