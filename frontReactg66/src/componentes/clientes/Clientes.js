import Menu from "../menu/Menu";
import axios from "axios";
import swal from "sweetalert";

import { useState, useEffect } from "react";

import { Link } from "react-router-dom";
const URI = "http://localhost:8081/api/v1/cliente/list";
const URID ="http://localhost:8081/api/v1/cliente/delete/"
const Clientes = () => {
  const [Cliente, setCliente] = useState([]);
  let headers = {
    user: sessionStorage.getItem("user"),
    key: sessionStorage.getItem("key"),
  };

  useEffect(() => {
    getClientes();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const getClientes = async () => {
    console.log(headers);
    try {
      const res = await axios({
        method: "GET",
        url: URI,
        headers: headers,
      });
      setCliente(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  const deleteCliente = async (id) => {
    swal({
        title: "Eliminar Registro",
        text: "Esta seguro de eliminar el registro",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then(async (willDelete) => {
            if (willDelete) {
                const res = await axios({
                    method: "DELETE",
                    url: URID + id,
                    headers: headers
                });
                console.log(res);
                swal("El registro se borró satisfactoriamente", {
                    icon: "success",
                }).then((value) => {
                    getClientes()
                });

            } else {
                swal("El registro no se borró");
            }
        });

}

  return (
    <>
      <Menu />
      <div>
        {" "}
        <div className="container">
          <div className="row">
            <div className="col">
              <Link
                to="/createCliente"
                className="btn btn-outline-primary mt-2 mb-2"
              >
                <i className="fas fa-plus"></i>
              </Link>
              Administración de Usuarios
              <table className="table">
                <thead className="table-primary">
                  <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>UserName</th>
                    <th>Roles</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {Cliente.map((cliente) => (
                    <tr key={cliente.idCliente}>
                      <td> {cliente.nombre} </td>
                      <td> {cliente.apellido} </td>
                      <td> {cliente.userName} </td>
                      <td>
                        {" "}
                        {cliente.roles.map(
                          (role) => role.nombre.replace("ROLE_", "") + " "
                        )}{" "}
                      </td>
                      <td>
                        <Link
                          to={`/editCliente/${cliente.idCliente}`}
                          className="btn btn-outline-info"
                        >
                          <i className="fas fa-edit"></i>
                        </Link>
                        &nbsp;
                        {cliente.userName !== sessionStorage.getItem("user") ? (
                          <button
                            onClick={() => deleteCliente(cliente.idCliente)}
                            className="btn btn-outline-danger"
                          >
                            <i className="fas fa-trash-alt"></i>
                          </button>
                        ) : (
                          ""
                        )}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Clientes;
