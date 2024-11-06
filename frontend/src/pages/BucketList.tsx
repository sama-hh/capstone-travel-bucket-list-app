import {Button, Container} from "react-bootstrap";
import {useEffect, useState} from "react";
import axios, {AxiosError} from "axios";
import {
    BucketListItemType,
    defaultBucketListItem
} from "../types/BucketList.ts";
import BucketItemList from "../components/BucketListItems.tsx";
import BucketListModal from "../components/BucketListModal.tsx";

const BucketList = () => {
    const [bucketList, setBucketList] = useState<BucketListItemType[]>([]);
    const [isModalOpen, setModalOpen] = useState<boolean>(false);
    const [hasChanged, setHasChanged] = useState<boolean>(false);
    const [bucketListItem, setBucketListItem] = useState<BucketListItemType>(defaultBucketListItem)

    const getBucketLists = () => {
        axios.get("/api/bucket-lists")
            .then(response => {
                setBucketList(response.data);
            })
            .catch((error: AxiosError) => {
                console.log(error)
            });
    }

    useEffect(() => {
        getBucketLists()
    }, [hasChanged])

    return (
        <>
            <Container className="custom-container">
                <div className="mt-4 bucket-list_header">
                    <h4>My Bucket List</h4>
                    <Button onClick={() => setModalOpen(prev => !prev)}>Add</Button>
                </div>
                <BucketItemList bucketList={bucketList} setModalOpen={setModalOpen}
                                setBucketListItem={setBucketListItem} setHasChanged={setHasChanged}/>
            </Container>

            <BucketListModal
                show={isModalOpen}
                setHasChanged={setHasChanged}
                handleClose={() => setModalOpen(false)}
                bucketListItem={bucketListItem}
                setBucketListItem={setBucketListItem}
            />
        </>
    );
};

export default BucketList;