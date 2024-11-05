import {Button, Container} from "react-bootstrap";
import {useEffect, useState} from "react";
import axios, {AxiosError} from "axios";
import {BucketListType} from "../types/BucketList.ts";
import BucketItemList from "../components/BucketListItems.tsx";

const BucketList = () => {
    const [bucketList, setBucketList] = useState<BucketListType[]>([]);

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
    }, [])

    return (
        <Container className="custom-container">
            <div className="mt-4 bucket-list_header">
                <h4>My Bucket List</h4>
                <Button>Add</Button>
            </div>
            <BucketItemList bucketList={bucketList}/>
        </Container>
    );
};

export default BucketList;