import {useLocation} from "react-router-dom";

const ItineraryDetails = () => {
    const location = useLocation();
    const {item} = location.state || {};

    return (
        <div>
            {item.name}
        </div>
    );
};

export default ItineraryDetails;