import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Link} from "react-router-dom";
import {Button} from "react-bootstrap";
import Logo from "../../public/travel.svg"

const Header = () => {
    return <>
        <Navbar className="mb-3 navbar">
            <Container className="w-100 custom-container py-2">
                <div className="header-container">
                    <div className="header-container__nav">
                        <Navbar.Brand href="/dashboard" className="p-0">
                            <img src={Logo} alt="" className="logo"/>
                        </Navbar.Brand>
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/dashboard">Home</Nav.Link>
                            <Nav.Link as={Link} to="/bucket-list">Travel Wishlist</Nav.Link>
                            <Nav.Link as={Link} to="/itineraries">Itineraries</Nav.Link>
                        </Nav>
                    </div>
                    <div className="d-flex">
                        <Button className="primary-button">Logout</Button>
                    </div>
                </div>
            </Container>
        </Navbar>
    </>
}

export default Header;