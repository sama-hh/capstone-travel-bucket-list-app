import {ItineraryType} from "./Itinerary.tsx";

export type DashboardCardProps = {
    title: string,
    statistics?: number,
    loading: boolean
}

export type DashboardItineraryCardProps = {
    itinerary?: ItineraryType | null,
    loading: boolean
}

export type DashboardType = {
    totalDestinations: number,
    visitedDestinations: number,
    totalItineraries: number
    lastCreatedItinerary: ItineraryType | null,
}