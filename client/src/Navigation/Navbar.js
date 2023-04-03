import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';

function MyNavbar() {
  return (
    <>
      <Navbar bg="light">
        <Container>
          <Navbar.Brand href="#home">Brand link</Navbar.Brand>
        </Container>
      </Navbar>
    </>
  );
}

export default MyNavbar;
