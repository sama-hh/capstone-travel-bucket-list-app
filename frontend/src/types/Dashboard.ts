export type Destinations = {
    totalDestinations: number,
    visitedDestinations: number,
}

export type Itineraries = {
    totalItineraries: number,
}

export type DashboardCardProps = {
    title: string,
    destinationCount?: number,
    loading: boolean
}

export type DashboardItineraryCardProps = {
    itinerariesCount?: Itineraries | null,
    loading: boolean
}

export type DashboardType = {
    destinationsInfo: Destinations | null;
    itinerariesInfo: Itineraries | null;
    loading: boolean;
}