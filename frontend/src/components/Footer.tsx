import {Col, Container, Row} from "react-bootstrap";

const Footer = () => {
    return (<>
            <footer className="bg-light text-dark mt-auto py-3">
                <Container className="custom-container">
                    <Row className="text-start">
                        <Col md={4}>
                            <h5>About Us</h5>
                            <p>Plan your travels with ease.</p>
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