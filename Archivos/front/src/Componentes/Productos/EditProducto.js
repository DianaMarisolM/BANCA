/* eslint-disable jsx-a11y/alt-text */
import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import swal from "sweetalert";
import { getApiUrl } from "../config/apiConfig";
const URI = getApiUrl("producto/list/");
const URIU = getApiUrl("producto/update/");

const EditProducto = () => {
  const [nombre, setNombre] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [valor, setValor] = useState("");
  const [cantidad, setCantidad] = useState("");
  const [file, setFile] = useState(null);
  const navigate = useNavigate();
  const { id } = useParams();
  const Save = async (e) => {
    e.preventDefault();
    try {
      let data = new FormData(document.querySelector("form"));

      const updateProducto = await axios({
        method: "PUT",
        url: URIU + id,
        data: data,
      });
      if (updateProducto.status === 201) {
        navigate("/");
      } else {
        swal(
          "Error",
          updateProducto.data.errors[0].type +
            " " +
            updateProducto.data.errors[0].message,
          "error"
        );
      }
    } catch (error) {
      console.log(error.response.data.message);
      swal("Error", error.response.data.message, "error");
    }
  };

  useEffect(() => {
    getProductoById();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const getProductoById = async () => {
    const res = await axios({
      method: "GET",
      url: URI + id,
    });
    setNombre(res.data.nombre);
    setDescripcion(res.data.descripcion);
    setValor(res.data.valor);
    setCantidad(res.data.cantidad);
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

export default EditProducto;
