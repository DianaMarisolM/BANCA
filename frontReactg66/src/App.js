import "./App.css";
import Login from "./componentes/Login/Login";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Menu from "./componentes/menu/Menu";
import Padre from "./componentes/demo/Padre";
import Clientes from "./componentes/clientes/Clientes";
import CreateCliente from "./componentes/clientes/CrearCliente";
import UpdateCliente from "./componentes/clientes/UpdateCliente";

const App = () => {
  return (
    <>
      <div className="container">
        {" "}
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/menu" element={<Menu />} />
            <Route path="/Padre" element={<Padre />} />
            <Route path="/clientes" element={<Clientes/>} />
            <Route path="/createCliente" element={<CreateCliente/>} />
            <Route path="/editCliente/:id" element={<UpdateCliente />} />
            
          </Routes>
        </BrowserRouter>
      </div>
    </>
  );
};

export default App;
