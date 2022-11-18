import { Link } from "react-router-dom";
import Menu from "./Menu";
const Clientes = () => {
  return (
    <div>
      <Menu />
      <p>
        <i className="fa fa-user" aria-hidden="true"></i>Clientes
      </p>
      <Link to={"/"}>Regresar</Link>
    </div>
  );
};

export default Clientes;
