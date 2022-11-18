import { Link } from "react-router-dom";
import Menu from "./Menu";
const Salir = () => {
  return (
    <div>
      <Menu />
      <p>Salir</p>
      <Link to={"/"}>Regresar</Link>
    </div>
  );
};

export default Salir;
