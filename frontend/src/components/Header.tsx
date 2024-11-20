import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Link, useLocation, useNavigate} from "react-router-dom";
import {Button} from "react-bootstrap";
import Logo from "../../public/travel.svg"
import {defaultUsername, HeaderProps} from "../types/Header.ts";
import axios, {AxiosError} from "axios";

const Header = ({username, setUsername}: HeaderProps) => {
    const navigate = useNavigate();
    const location = useLocation();

    const isActive = (path: string) => location.pathname === path;

    function logout() {
        axios.post("/api/auth/logout")
            .then(() => {
                setUsername(defaultUsername);
                navigate("/login");
            })
            .catch((error: AxiosError) => {
                console.log(error)
            })
    }

    return <>
        <Navbar className="mb-3 navbar">
            <Container className="w-100 custom-container py-3">
                <div className="header-container">
                    <div className="header-container__nav">
                        <Nav.Link as={Link} to="/dashboard">
                            <img src={Logo} alt="" className="logo"/>
                        </Nav.Link>
                        <Nav className="me-auto mx-3">
                            <Nav.Link as={Link} to="/dashboard" className={isActive('/dashboard') ? 'active' : ''}>Home</Nav.Link>
                            <Nav.Link as={Link} to="/bucket-list" className={isActive('/bucket-list') ? 'active' : ''}>Travel Wishlist</Nav.Link>
                            <Nav.Link as={Link} to="/travel-plan" className={isActive('/travel-plan') ? 'active' : ''}>Travel Plans</Nav.Link>
                        </Nav>
                    </div>
                    <div className="d-flex">
                        {username.id !== '' ?
                            <div className="header-user">
                                <span className="mx-2">Welcome, {username.username}</span>
                                <Button className="primary-button" onClick={logout}>Logout</Button>
                            </div> :
                            <Button className="primary-button" onClick={() => navigate("/login")}>Log in</Button>}
                    </div>
                </div>
            </Container>
        </Navbar>
    </>
}

export default Header;