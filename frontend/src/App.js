import './App.css';
import Login from './Components/Login/Login';
import Principal from './Components/Principal/Principal';
import NoFound from './Components/NoFound';
import Menu from './Components/Menu/Menu';
import { Route, Routes,HashRouter } from "react-router-dom"
function App() {
  
  return (
    <div className="App">
      <HashRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/principal" element={<Principal />} />
          <Route path="/clientes" element={<Menu ruta="clientes"  />} />
          <Route path="/createCliente" element={<Menu ruta="createCliente" />} />
          <Route path="/editCliente/:id" element={<Menu ruta="updateCliente" />} />

          <Route path="/cuentas" element={<Menu ruta="cuentas"  />} />
          <Route path="/createCuenta" element={<Menu ruta="createCuenta"  />} />
          <Route path="/editCuenta/:id" element={<Menu ruta="updateCuenta" />} />

          <Route path="/transaccion/:id/:tipo" element={<Menu ruta="transaccion"  />} />
          <Route path="/transacciones/:id" element={<Menu ruta="transacciones"  />} />

          <Route path="/noFound" element={<NoFound />} />

          
        </Routes>
      </HashRouter>

    </div>
  );
}

export default App;
