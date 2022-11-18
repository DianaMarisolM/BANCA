import { Link } from "react-router-dom";
import Menu from "./Menu";
const Cuentas = () => {
  return (
    <div>
      <Menu />
      <p>Cuentas</p>
      <Link to={"/"}>Regresar</Link>
    </div>
  );
};

export default Cuentas;
