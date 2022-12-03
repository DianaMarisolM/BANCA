import "./App.css";
import Producto from "./Componentes/Productos/Productos";
import { Route, Routes, HashRouter } from "react-router-dom";
import CreateProducto from "./Componentes/Productos/CreateProducto";
import EditProducto from "./Componentes/Productos/EditProducto";
import Cart from "./Componentes/Cart/Cart";
import Menu from "./Componentes/Navbar/Menu";
import DemoArchivo from "./Componentes/DemoArchivo/DemoArchivo";
import CrearDemoArchivo from "./Componentes/DemoArchivo/CrearDemoArchivo";

function App() {
  return (
    <div className="App">
      <HashRouter>
        <Menu></Menu>
        <Routes>
          <Route exact path="/" element={<Producto />} />
          <Route exact path="/createProducto" element={<CreateProducto/>} />
          <Route exact path="/editProducto/:id" element={<EditProducto  />} />
          <Route exact path="/cart" element={<Cart  />} />
          <Route exact path="/demoarchivo" element={<DemoArchivo  />} />
          <Route exact path="/creardemoarchivo" element={<CrearDemoArchivo  />} />
        </Routes>
      </HashRouter>
    </div>
  );
}

export default App;
