import {Col, Row} from "react-bootstrap";
import {
    BucketListItemsProps,
    BucketListItemType
} from "../types/BucketList.ts";
import axios, {AxiosError} from "axios";
import BucketListItemCard from "./BucketListItemCard.tsx";

const BucketListItems = ({bucketList, setModalOpen, setBucketListItem, setHasChanged}: BucketListItemsProps) => {

    const handleEdit = (item: BucketListItemType) => {
        setBucketListItem(item);
        setModalOpen(prev => !prev);
    }

    const handleDelete = (item: BucketListItemType) => {
        axios.delete(`api/bucket-lists/${item.id}`)
            .then(() => {
                setHasChanged((state: boolean) => !state);
            })
            .catch((error: AxiosError) => {
                console.log(error)
            });
    }

    return (
        <Row className="mt-3">
            {bucketList.map((item) => (
                <Col key={item.id} sm={12} md={6} lg={3} className="mb-4">
                    <BucketListItemCard item={item} onEdit={handleEdit} onDelete={handleDelete}/>
                </Col>
            ))}
        </Row>
    );
};

export default BucketListItems;