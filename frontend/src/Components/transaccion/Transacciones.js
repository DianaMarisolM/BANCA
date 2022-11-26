import axios from 'axios'
import "./Transacciones.css"
import { useState, useEffect } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
let URI = 'http://129.213.23.13:8080/bancasergio/api/v1/transaccion/list/'



const Transacciones = (props) => {
    let headers = props.headers
    const { id } = useParams()
    const navigate = useNavigate();
    const [Transacciones, setTransacciones] = useState([]);
    useEffect(() => {
        getTransacciones()
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);
    //procedimineto para mostrar todos los blogs


    const getTransacciones = async () => {
        try {
            const res = await axios({
                method: "GET",
                url: URI + id,
                headers: headers
            });
            console.log(res.data);
            setTransacciones(res.data)
        } catch (error) {
            navigate("/noFound")
        }
    }



    return (
        <div>
            {" "}
            <div className='container'>
                <div className='row'>
                    <div className='col'>
                        {props.user != null ? (<Link to="/createCuenta" className='btn btn-outline-primary mt-2 mb-2'><i className="fas fa-plus"></i></Link>) : ""} Relación de Transacciones cuenta {id}
                        <table className='table'>
                            <thead className='table-primary'>
                                <tr>
                                    <th>ID</th>
                                    <th>Fecha</th>
                                    <th>valor</th>
                                    <th>Tipo</th>
                                </tr>
                            </thead>
                            <tbody>

                                {Transacciones.map((transaccion) => (

                                    <tr key={transaccion.id}>
                                        <td> {transaccion.id} </td>
                                        <td> {transaccion.fechaTransaccion.substring(0, 10)} </td>
                                        <td className="izquierda" > {new Intl.NumberFormat('en-US', {
                                            style: 'currency',
                                            currency: 'USD'
                                        }).format(transaccion.valorTransaccion)} </td>
                                        <td ><i className="fa-regular fa-money-bill-1" /> {transaccion.tipoTransaccion} </td>

                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        <Link to={`/cuentas`} className='btn btn-outline-success'> Regresar</Link>
                    </div>
                </div>
            </div>
        </div>
    );
};
export default Transacciones;
