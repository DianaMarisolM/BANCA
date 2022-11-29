import "./App.css";
import Producto from "./Componentes/Productos/Productos";
import { Route, Routes, HashRouter } from "react-router-dom";
import CreateProducto from "./Componentes/Productos/CreateProducto";
import EditProducto from "./Componentes/Productos/EditProducto";
function App() {
  return (
    <div className="App">
      <HashRouter>
        <Routes>
          <Route exact path="/" element={<Producto />} />
          <Route exact path="/createProducto" element={<CreateProducto/>} />
          <Route exact path="/editProducto/:id" element={<EditProducto  />} />
        </Routes>
      </HashRouter>
    </div>
  );
}

export default App;
