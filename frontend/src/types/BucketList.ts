export enum BucketListStatus {
    VISITED = 'VISITED',
    NOT_VISITED = 'NOT_VISITED',
}

export type BucketListType = {
    id: string,
    name: string,
    country: string,
    status: BucketListStatus
}

export type NewBucketListType = {
    name: string,
    country: string,
    status: BucketListStatus
}
