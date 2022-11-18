import { Link } from "react-router-dom";
const Menu = () => {
  //logica de negocio componente
  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">
            Principal
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              <li className="nav-item">
                <Link className="navbar-brand" to="/Clientes">
                  Clientes
                </Link>
              </li>
              <li className="nav-item">
                <Link className="navbar-brand" to="/Cuentas">
                  Cuentas
                </Link>
              </li>
              <li className="nav-item">
                <Link className="navbar-brand" to="/Loguear">
                  Login
                </Link>
              </li>
              <li className="nav-item">
                <Link className="navbar-brand" to="/Salir">
                  Logout
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div>Cuerpo</div>
    </div>
  );
};

export default Menu;
