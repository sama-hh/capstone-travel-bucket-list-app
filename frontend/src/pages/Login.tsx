import {Container, Button, Card, Row, Col} from 'react-bootstrap';

const Login = () => {
    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin
        window.open(host + '/oauth2/authorization/github', '_self')
    }

    return (
        <Container className="custom-container">
            <Row className="login-row">
                <Col md={6} lg={4}>
                    <Card>
                        <Card.Body>
                            <Card.Title className="text-center mb-4">Login</Card.Title>
                            <Button variant="secondary" onClick={login} className="w-100">
                                Login with Github
                            </Button>
                            {/*</Form>*/}
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default Login;
