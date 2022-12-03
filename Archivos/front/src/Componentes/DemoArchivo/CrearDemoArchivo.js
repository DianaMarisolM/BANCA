/* eslint-disable jsx-a11y/alt-text */
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
const URI = "http://localhost:8080/create";

const CrearDemoArchivo = () => {
  const [nombre, setNombre] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [cantidad, setCantidad] = useState("");
  const [file, setFile] = useState("");
  const [valor, setValor] = useState("");
  const navigate= useNavigate()

  const guardar = async (e) => {
    e.preventDefault();
    try {
      let data = new FormData(document.querySelector("form"));
      const enviar = await axios({
        method: "POST",
        url: URI,
        data: data,
      });
      navigate("/demoarchivo")
    } catch (error) {}
  };
  return (
    <>
      <form onSubmit={guardar}>
        <div className="mb-3">
          <label className="form-label">Nombre</label>
          <input
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
            name="nombre"
            type="text"
            className="form-control"
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Descripcion</label>
          <input
            value={descripcion}
            onChange={(e) => setDescripcion(e.target.value)}
            type="text"
            name="descripcion"
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Valor</label>
          <input
            value={cantidad}
            onChange={(e) => setCantidad(e.target.value)}
            type="number"
            name="cantidad"
            className="form-control"
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Cantidad</label>
          <input
            value={valor}
            onChange={(e) => setValor(e.target.value)}
            type="number"
            name="valor"
            className="form-control"
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Imagen</label>
          <input
            required
            type="file"
            onChange={(e) => setFile(URL.createObjectURL(e.target.files[0]))}
            className="form-control"
            name="file"
          />
          <br />
          <img  src={file} />
        </div>
        <button type="submit" className="btn btn-outline-primary">
          Guardar
        </button>
      </form>
    </>
  );
};

export default CrearDemoArchivo;
