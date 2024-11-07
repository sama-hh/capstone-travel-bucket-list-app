export type Destinations = {
    totalDestinations: number,
    visitedDestinations: number,
}

export type DashboardCardProps = {
    title: string,
    destinationCount?: number,
    loading: boolean
}