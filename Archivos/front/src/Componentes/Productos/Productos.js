/* eslint-disable jsx-a11y/alt-text */
import axios from "axios";
import { getApiUrl } from "../config/apiConfig";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import swal from "sweetalert";
import "./Producto.css";

const URIP = getApiUrl("producto/list");
const URID = getApiUrl("producto/delete/")
const Producto = (props) => {
  console.log(URIP);
  let headers = props.headers;
  const navigate = useNavigate();
  const [Producto, setProducto] = useState([]);
  useEffect(() => {
    getProductos();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);


  const getProductos = async () => {
    try {
      const res = await axios({
        method: "GET",
        url: URIP,
        headers: headers,
      });
      setProducto(res.data);
    } catch (error) {
      navigate("/noFound")
    }
  };
 
  const deleteProducto = async (id) => {
    swal({
      title: "Eliminar Registro",
      text: "Esta seguro de eliminar el registro",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then(async (willDelete) => {
      if (willDelete) {
        const res = await axios({
          method: "DELETE",
          url: URID + id,
          headers: headers,
        });
        console.log(res);
        swal("El registro se borró satisfactoriamente", {
          icon: "success",
        }).then((value) => {
          getProductos();
        });
      } else {
        swal("El registro no se borró");
      }
    });
  };

  return (
    <div>
      {" "}
      <div className="container">
        <div className="row">
          <div className="col">
            <Link
              to="/createProducto"
              className="btn btn-outline-primary mt-2 mb-2"
            >
              <i className="fas fa-plus"></i>
            </Link>
            Administración de Productos
            <table className="table">
              <thead className="table-primary">
                <tr>
                  <th>Nombre</th>
                  <th>Descripcion</th>
                  <th>Cantidad</th>
                  <th>Valor</th>
                  <th>Imagen</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {Producto.map((Producto) => (
                  <tr key={Producto.id}>
                    <td className="alinear"> {Producto.nombre} </td>
                    <td className="alinear"> {Producto.descripcion} </td>
                    <td className="alinear"> {Producto.cantidad} </td>
                    <td className="alinear"> {Producto.valor} </td>
                    <td className="alinear">
                      <img src={Producto.url} className="image"></img>
                    </td>
                    <td className="alinear">
                      <Link
                        to={`/editProducto/${Producto.id}`}
                        className="btn btn-outline-info"
                      >
                        <i className="fas fa-edit"></i>
                      </Link>
                      &nbsp;
                      <button
                        onClick={() => deleteProducto(Producto.id)}
                        className="btn btn-outline-danger"
                      >
                        <i className="fas fa-trash-alt"></i>
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Producto;
