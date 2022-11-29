/* eslint-disable jsx-a11y/alt-text */
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import swal from "sweetalert";
import { getApiUrl } from "../config/apiConfig";
const URI = getApiUrl("producto/create");

const UpdateProducto = () => {
  const [nombre, setNombre] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [valor, setValor] = useState("");
  const [cantidad, setCantidad] = useState("");
  const [file, setFile] = useState(null);
  const navigate = useNavigate();

  const Save = async (e) => {
    e.preventDefault();
    try {
      let data = new FormData(document.querySelector("form"));

      const insertProducto = await axios({
        method: "POST",
        url: URI,
        data: data,
      });
      if (insertProducto.status === 201) {
        navigate("/");
      } else {
        swal(
          "Error",
          insertProducto.data.errors[0].type +
            " " +
            insertProducto.data.errors[0].message,
          "error"
        );
      }
    } catch (error) {
      swal(
        "Error",
        JSON.parse(error.request.response).errors[0].message,
        "error"
      );
    }
  };

  return (
    <div>
      <div className="container col-5">
        <h3>Update Producto</h3>
        <form onSubmit={Save}>
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
            <img className="image" src={file} />
          </div>
          <button type="submit" className="btn btn-outline-primary">
            Guardar
          </button>
        </form>
      </div>
    </div>
  );
};

export default UpdateProducto;
