/* eslint-disable jsx-a11y/alt-text */
import axios from "axios";
import { useState, useEffect } from "react";
const URI = "http://localhost:8080/listar";

const DemoArchivo = () => {
  const [demoArchivo, setDemoArchivo] = useState([]);

  const lista = async () => {
    try {
      const demo = await axios({
        method: "GET",
        url: URI,
      });
      setDemoArchivo(demo.data);
    } catch (error) {}
  };

  useEffect(() => {
    lista();
  }, []);

  return (
    <>
      <table className="table">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>descripcion</th>
            <th>cantidad</th>
            <th>valor</th>
            <th>Imagen</th>
          </tr>
        </thead>
        <tbody>
          {demoArchivo.map((producto) =>( 
            <tr>
              <td>{producto.nombre}</td>
              <td>{producto.descripcion}</td>
              <td>{producto.cantidad}</td>
              <td>{producto.valor}</td>
              <td><img src={producto.url}></img></td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default DemoArchivo;
