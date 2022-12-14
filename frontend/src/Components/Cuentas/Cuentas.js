import axios from 'axios'
import { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import swal from "sweetalert"
let URI = ''
const URID = 'http://129.213.23.13:8080/bancasergio/api/v1/cuenta/delete/'


const Cuenta = (props) => {
    let headers =props.headers
    if (props.user == null) {
        URI = 'http://129.213.23.13:8080/bancasergio/api/v1/cuenta/list/cliente/' + sessionStorage.getItem("id")
    }else{
        URI = 'http://129.213.23.13:8080/bancasergio/api/v1/cuenta/list'
    }
    const navigate = useNavigate();
    const [Cuenta, setCuenta] = useState([]);
    useEffect(() => {
        getCuentas()
    // eslint-disable-next-line react-hooks/exhaustive-deps
    },[]);
    //procedimineto para mostrar todos los blogs


    const getCuentas = async () => {
        try {
            const res = await axios({
                method: "GET",
                url: URI,
                headers: headers
            });
            setCuenta(res.data)
        } catch (error) {
            navigate("/noFound")
        }
    }


    //procedimineto para eliminar un blog
    const deleteCuenta = async (id) => {
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
                        getCuentas()
                    });

                } else {
                    swal("El registro no se borró");
                }
            });

    }

    return (
        <div>
            {" "}
            <div className='container'>
                <div className='row'>
                    <div className='col'>
                        {props.user != null ? (<Link to="/createCuenta" className='btn btn-outline-primary mt-2 mb-2'><i className="fas fa-plus"></i></Link>) : ""} Administración de cuentas
                        <table className='table'>
                            <thead className='table-primary'>
                                <tr>
                                    <th>Fecha Creacion</th>
                                    <th>Nombre</th>
                                    <th>Apellido</th>
                                    <th>Saldo</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>

                                {Cuenta.map((cuenta) => (
                                    <tr key={cuenta.id}>
                                        <td> {cuenta.fechaCreaccion.substring(0, 10)} </td>
                                        <td> {cuenta.nombre} </td>
                                        <td> {cuenta.apellido} </td>
                                        <td> {cuenta.saldo} </td>
                                        <td>
                                            {props.user != null ? (<Link to={`/editCuenta/${cuenta.id}`} className='btn btn-outline-info'><i className="fas fa-edit"></i></Link>) : (<Link to={`/transaccion/${cuenta.id}/D`} className='btn btn-outline-success'><i className="fa-regular fa-money-bill-1 "> D</i></Link>)}&nbsp;
                                            {props.user != null ? (<button onClick={() => deleteCuenta(cuenta.id)} className='btn btn-outline-danger'><i className="fas fa-trash-alt"></i></button>) : (<Link to={`/transaccion/${cuenta.id}/R`} className='btn btn-outline-danger'><i className="fa-regular fa-money-bill-1 " > R</i></Link>)}&nbsp;
                                            {props.user != null ? ("") : (<Link to={`/transacciones/${cuenta.id}`} className='btn btn-outline-warning'><i className="fa-regular fa-money-bill-1 " > T</i></Link>)}
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
export default Cuenta;
