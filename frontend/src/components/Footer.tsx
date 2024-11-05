import {Col, Container, Row} from "react-bootstrap";
import {Link} from "react-router-dom";
import Nav from "react-bootstrap/Nav";

const Footer = () => {
    return (<>
            <footer className="bg-light text-dark mt-auto py-3">
                <Container className="custom-container">
                    <Row className="text-start">
                        <Col md={4}>
                            <h5>About Us</h5>
                            <p>Hello</p>
                        </Col>
                        <Col md={4}>
                            <h5>Links</h5>
                            <ul className="list-unstyled">
                                <li><Nav.Link as={Link} to="/dashboard">Home</Nav.Link></li>
                            </ul>
                        </Col>
                        <Col md={4}>
                            <h5>Contact Us</h5>
                            <p>Email: info@example.com</p>
                        </Col>
                    </Row>
                </Container>
            </footer>
        </>

    )
}

export default Footer;