import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import swal from "sweetalert";
const URI = "http://localhost:8081/api/v1/cuenta/create";
const URIC = 'http://localhost:8081/api/v1/cliente/list'


const CreateCuenta = (props) => {
  let headers =props.headers
  const [fecha, setFecha] = useState("");
  const [cliente, setCliente] = useState("");
  const [saldo, setSaldo] = useState("");
  const [clientes, setClientes] = useState([]);


  const navigate = useNavigate();

  const Save = async () => {

    try {
      const insertCuenta = await axios({
        method: "POST",
        url: URI,
        data: {
          fechaApertura: fecha,
          saldoCuenta: saldo,
          cliente: {
            idCliente: cliente
          }
        },
        headers: headers,
      });
      if (insertCuenta.status === 201) {
        navigate("/cuentas");
      } else {
        swal(
          "Error",
          insertCuenta.data.errors[0].type +
          " " +
          insertCuenta.data.errors[0].message,
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


  useEffect(() => {
    getClientes()
  // eslint-disable-next-line react-hooks/exhaustive-deps
  },[])

  const getClientes = async () => {

    try {
      const res = await axios({
        method: "GET",
        url: URIC,
        headers: headers
      });
      setClientes(res.data)
    } catch (error) {
      navigate("/noFound")
    }
  }

  return (
    <div>
      <div className="container col-5">
        <h3>Create Cuenta</h3>
        <form onSubmit={Save}>
          <div className="mb-3">
            <label className="form-label">Fecha</label>
            <input
              value={fecha}
              onChange={(e) => setFecha(e.target.value)}
              type="date"
              className="form-control"
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Usuario</label>
            <select
              value={cliente}
              onChange={(e) => setCliente(e.target.value)}
              type="text"
              className="form-control"
            >
              {clientes.map((cliente) => (
                <option value={cliente.idCliente}>{cliente.nombre +" "+cliente.apellido}</option>
              ))}
            </select>
          </div>
          <div className="mb-3">
            <label className="form-label">Saldo</label>
            <input
              value={saldo}
              onChange={(e) => setSaldo(e.target.value)}
              type="number"
              className="form-control"
            />
          </div>

          <button type="submit" className="btn btn-outline-primary">
            Guardar
          </button>
        </form>
      </div>
    </div>
  );
};

export default CreateCuenta;
