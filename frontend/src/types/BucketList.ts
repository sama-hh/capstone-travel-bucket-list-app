import {Dispatch, SetStateAction} from "react";

export enum BucketListItemStatus {
    VISITED = 'VISITED',
    NOT_VISITED = 'NOT_VISITED',
}

export type BucketListItemType = {
    id?: string,
    name: string,
    country: string,
    status: BucketListItemStatus
}

export const defaultBucketListItem = {
    name: '',
    country: '',
    status: BucketListItemStatus.NOT_VISITED
};

export type BucketListItemsProps = {
    bucketList: BucketListItemType[],
    setModalOpen: (value: (prev: boolean) => boolean) => void;
    setBucketListItem: Dispatch<SetStateAction<BucketListItemType>>,
    setHasChanged: (value: (prev: boolean) => boolean) => void;
}

export type ListModalProps = {
    show: boolean,
    handleClose: () => void,
    setHasChanged: (value: (prev: boolean) => boolean) => void;
    bucketListItem: BucketListItemType,
    setBucketListItem: Dispatch<SetStateAction<BucketListItemType>>
}