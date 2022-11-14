import Menu from "../Menu/Menu";
import axios from 'axios'
import { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import swal from "sweetalert"
const URI = 'http://localhost:8081/api/v1/cuenta/list'
const URID = 'http://localhost:8081/api/v1/cuenta/delete/'
let headers = {
    user: sessionStorage.getItem("user"),
    key: sessionStorage.getItem("key")
};
const Cuenta = () => {

    const navigate = useNavigate();
    const [Cuenta, setCuenta] = useState([]);
    useEffect(() => {
        getCuentas()
    }, []);
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
            <Menu />
            <div className='container'>
                <div className='row'>
                    <div className='col'>
                        <Link to="/createCuenta" className='btn btn-primary mt-2 mb-2'><i className="fas fa-plus"></i></Link> Administración de cuentas
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
                                        <td> {cuenta.fechaCreaccion.substring(0,10)} </td>
                                        <td> {cuenta.nombre} </td>
                                        <td> {cuenta.apellido} </td>
                                        <td> {cuenta.saldo} </td>
                                        <td>
                                            <Link to={`/editCuenta/${cuenta.id}`} className='btn btn-info'><i className="fas fa-edit"></i></Link>&nbsp;
                                            {cuenta.userName !== sessionStorage.getItem("user") ? (
                                                <button onClick={() => deleteCuenta(cuenta.id)} className='btn btn-danger'><i className="fas fa-trash-alt"></i></button>) : ""}
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
