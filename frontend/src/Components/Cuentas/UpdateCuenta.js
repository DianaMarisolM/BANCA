import Menu from "../Menu/Menu";
import axios from 'axios'
import { useState, useEffect } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import swal from "sweetalert"
const URI = 'http://localhost:8081/api/v1/cuenta/list/'
const URIU = 'http://localhost:8081/api/v1/cuenta/update/'
const URIC = 'http://localhost:8081/api/v1/cliente/list'

let headers = {
    user: sessionStorage.getItem("user"),
    key: sessionStorage.getItem("key")
};

const UpdateCuentas = () => {
    const [fecha, setFecha] = useState("");
    const [cliente, setCliente] = useState("");
    const [saldo, setSaldo] = useState("");
    let roles = document.getElementsByClassName("role");
    const { id } = useParams()
    const navigate = useNavigate();
    const [clientes, setClientes] = useState([]);

    const Save = async (e) => {
        e.preventDefault();

        try {
            const updateCliente = await axios({
                method: "PUT",
                url: URIU + id,
                data: {
                    fechaApertura: fecha,
                    saldoCuenta: saldo,
                    cliente: {
                        idCliente: cliente
                    }
                },
                headers: headers
            });
            if (updateCliente.status === 200) {
                navigate("/cuentas");
            } else {
                swal("Error", updateCliente.data.errors[0].type + " " + updateCliente.data.errors[0].message, "error")
            }

        } catch (error) {
            //console.log(error);
            swal("Error", JSON.parse(error.request.response).errors[0].message, "error")
        }
    }

    useEffect(() => {
        getCuentaById()
    }, [])

    const getCuentaById = async () => {

       try {
        const res = await axios({
            method: "GET",
            url: URI + id,
            headers: headers
        });
        console.log(res.data.fechaCreaccion);
        setFecha(res.data.fechaCreaccion)
        setSaldo(res.data.saldo)
        setCliente(res.data.idCliente)
       } catch (error) {
         console.log(error);
       }

    }


    useEffect(() => {
        getClientes()
    }, [])

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
        <div><Menu />
            <div className="container col-5">
                <h3>Update Cliente</h3>
                <form onSubmit={Save}>
          <div className="mb-3">
            <label className="form-label">Fecha</label>
            <input
              value={fecha.substring(0,10)}
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

          <button type="submit" className="btn btn-primary">
            Guardar
          </button>
        </form>
            </div></div>
    );
}

export default UpdateCuentas;