import './App.css';
import Login from './Components/Login/Login';
import Principal from './Components/Principal/Principal';
import Cliente from './Components/Cientes/Cliente';
import CreateCliente from './Components/Cientes/CreateCliente';
import UpdateCliente from './Components/Cientes/UpdateCliente';
import { BrowserRouter, Route, Routes } from "react-router-dom"
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/principal" element={<Principal />} />
          <Route path="/clientes" element={<Cliente />} />
          <Route path="/createCliente" element={<CreateCliente />} />
          <Route path="/editCliente/:id" element={<UpdateCliente />} />

          
        </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
