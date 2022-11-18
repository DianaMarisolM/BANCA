
import './App.css';
import Menu from './components/menu/Menu';
import Clientes from './components/menu/Clientes';
import Cuentas from './components/menu/Cuentas';
import Login from './components/menu/Login';
import Salir from './components/menu/Salir';

import { BrowserRouter, Route, Routes, Link, Router } from "react-router-dom"

const App = () => {
  return (
    <div className="container">     

        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Menu />} />
            <Route path="/Loguear" element={<Login />} />
            <Route path="/Cuentas" element={<Cuentas />} />
            <Route path="/Clientes" element={<Clientes />} />
            <Route path="/Salir" element={<Salir />} />
          </Routes>
        </BrowserRouter>
      </div>

   
  );
}

export default App;
