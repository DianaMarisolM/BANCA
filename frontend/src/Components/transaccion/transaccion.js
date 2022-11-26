import axios from "axios";
import { useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import swal from "sweetalert";
const URI = "http://129.213.23.13:8080/bancasergio/api/v1/transaccion/create"


const Deposito = (props) => {
  let headers = props.headers
  const { id, tipo } = useParams()
  const [valorTransaccion, setValorTransaccion] = useState("");



  const navigate = useNavigate();

  const Save = async (e) => {
    e.preventDefault();

    try {
      const Transaccion = await axios({
        method: "POST",
        url: URI,
        data: {
          cuenta: {
            id: id
          },
          valorTransaccion: valorTransaccion,
          tipoTransaccion: tipo
        },
        headers: headers,
      });
      if (Transaccion.status === 201) {

        swal((tipo === "D" ? ("Deposito") : ("Retiro")) + " Realizado", "Presione el botón", "success")
          .then((value) => {
            navigate("/cuentas")
          });


      } else {
        swal(
          "Error",
          Transaccion.data.errors[0].type +
          " " +
          Transaccion.data.errors[0].message,
          "error"
        );
      }
    } catch (error) {
      swal("Error Retiro ",
        JSON.parse(error.request.response).message,
        "error");

    }
  };


  return (
    <div>
      <div className="container col-5">
        <h3>Consignar a cuenta </h3>
        <form onSubmit={Save}>
          <div className="mb-3">
            <label className="form-label">Valor {tipo === "D" ? ("Deposito") : ("Retiro")}</label>
            <input
              value={valorTransaccion}
              onChange={(e) => setValorTransaccion(e.target.value)}
              type="number"
              className="form-control"
            />
          </div>

          <button type="submit" className="btn btn-outline-primary">
            Realizar {tipo === "D" ? ("Deposito") : ("Retiro")}
          </button>{" "}
          <Link to={`/cuentas`} className='btn btn-outline-success'> Regresar</Link>
        </form>
      </div>
    </div>
  );
};

export default Deposito;
