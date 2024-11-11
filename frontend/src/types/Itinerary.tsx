import {Dispatch, SetStateAction} from "react";

export type Destination = {
    destinationId: string,
    destinationName: string,
    arrivalTime: string,
    departureTime: string
}

export type ItineraryType = {
    id?: string,
    name: string,
    destinations: Destination[],
    startDate: string,
    endDate: string,
    estimatedCost: number,
    createdDate: string,
    lastModifiedDate: string
}

export const defaultItineraryItem = {
    name: '',
    destinations: [],
    startDate: '',
    endDate: '',
    estimatedCost: 0,
    createdDate: '',
    lastModifiedDate: '',
};

export type ItinerariesListProps = {
    itineraries: ItineraryType[],
    setModalOpen: (value: (prev: boolean) => boolean) => void;
    setItinerary: Dispatch<SetStateAction<ItineraryType>>,
    setHasChanged: (value: (prev: boolean) => boolean) => void;
}

export type ItineraryModalProps = {
    show: boolean,
    handleClose: () => void,
    setHasChanged: (value: (prev: boolean) => boolean) => void;
    itinerary: ItineraryType,
    setItinerary: Dispatch<SetStateAction<ItineraryType>>
}