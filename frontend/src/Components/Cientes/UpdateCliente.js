import Menu from "../Menu/Menu";
import axios from 'axios'
import { useState, useEffect } from 'react'
import { Link, useNavigate,useParams } from 'react-router-dom'
import swal from "sweetalert"
const URI = 'http://localhost:8081/api/v1/cliente/list/'
const URIU = 'http://localhost:8081/api/v1/cliente/update/'

let headers = {
    user: sessionStorage.getItem("user"),
    key: sessionStorage.getItem("key")
};

const UpdateCliente = () => {
    const [nombre, setNombre] = useState("");
    const [apellido, setApellido] = useState("");
    const [userName, setUserName] = useState("");
    const [password, setPassWord] = useState("");
    const [roles, setRoles] = useState([]);
    const {id} = useParams()
    const navigate = useNavigate();

    const Save = async (e) => {
        e.preventDefault();
        try {
            const updateCliente = await axios({
                method: "PUT",
                url: URIU+id,
                data: {
                    nombre: nombre,
                    apellido: apellido,
                    userName: userName,
                    password: password
                },
                headers: headers
            });
            if (updateCliente.status === 200) {
                navigate("/clientes");
            } else {
                swal("Error", updateCliente.data.errors[0].type + " " + updateCliente.data.errors[0].message, "error")
            }

        } catch (error) {
            console.log(error);
           // swal("Error", JSON.parse(error.request.response).errors[0].message, "error")
        }
    }

    useEffect( ()=>{
        getUserById()
    },[])

    const getUserById = async () => {

        const res =  await axios({
            method: "GET",
            url: URI+id,
            headers: headers 
          });
        setNombre(res.data.nombre)
        setApellido(res.data.apellido)
        setUserName(res.data.userName)
        setPassWord(res.data.password)
    }
    return (
        <div><Menu />
            <div className="container col-5">
                <h3>Create Cliente</h3>
                <form onSubmit={Save}>
                    <div className="mb-3">
                        <label className="form-label">Nombre</label>
                        <input
                            value={nombre}
                            onChange={(e) => setNombre(e.target.value)}
                            type="text"
                            className="form-control"
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Apellido</label>
                        <input
                            value={apellido}
                            onChange={(e) => setApellido(e.target.value)}
                            type="text"
                            className="form-control"
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">UserName</label>
                        <input
                            value={userName}
                            onChange={(e) => setUserName(e.target.value)}
                            type="text"
                            className="form-control"
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">PassWord</label>
                        <input
                            value={password}
                            onChange={(e) => setPassWord(e.target.value)}
                            type="password"
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

export default UpdateCliente;