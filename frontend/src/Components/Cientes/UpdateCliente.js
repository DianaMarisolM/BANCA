import axios from 'axios'
import { useState, useEffect } from 'react'
import {  useNavigate, useParams } from 'react-router-dom'
import swal from "sweetalert"
const URI = 'http://localhost:8081/api/v1/cliente/list/'
const URIU = 'http://localhost:8081/api/v1/cliente/update/'



const UpdateCliente =() => {
    let headers = {
        user: sessionStorage.getItem("user"),
        key: sessionStorage.getItem("key")
    };
    const [nombre, setNombre] = useState("");
    const [apellido, setApellido] = useState("");
    const [userName, setUserName] = useState("");
    const [password, setPassWord] = useState("");
    let roles = document.getElementsByClassName("role");
    const { id } = useParams()
    const navigate = useNavigate();

    const Save = async (e) => {
        e.preventDefault();
        let listaRoles = [];
        for (const rol of roles) {
            if (rol.checked) {
                listaRoles.push(rol.value);
            }
        }
        try {
            const updateCliente = await axios({
                method: "PUT",
                url: URIU + id,
                data: {
                    nombre: nombre,
                    apellido: apellido,
                    userName: userName,
                    password: password,
                    role:listaRoles
                },
                headers: headers
            });
            if (updateCliente.status === 200) {
                navigate("/clientes");
            } else {
                swal("Error", updateCliente.data.errors[0].type + " " + updateCliente.data.errors[0].message, "error")
            }

        } catch (error) {
            //console.log(error);
            swal("Error", JSON.parse(error.request.response).errors[0].message, "error")
        }
    }

    useEffect(() => {
        getUserById()
    }, [])

    const getUserById = async () => {

        const res = await axios({
            method: "GET",
            url: URI + id,
            headers: headers
        });
        setNombre(res.data.nombre)
        setApellido(res.data.apellido)
        setUserName(res.data.userName)
        setPassWord(res.data.password)
        for (const rol of res.data.roles) {
            for (const check of roles) {
                if(rol.nombre.replaceAll("ROLE_","").substring(0,3)===check.value.toUpperCase().substring(0,3)){
                    check.checked=true
                }
            }
        }
        
    }
    return (
        <div>
            <div className="container col-5">
                <h3>Update Cliente</h3>
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
                    <div className="mb-3">
                        <div>
                            {" "}
                            <label className="form-label">Roles</label>
                        </div>
                        <input
                            className="form-check-input role"
                            type="checkbox"
                            value="user"
                        />
                        <label className="form-check-label">User</label>
                        {"  "}
                        <input
                            className="form-check-input role"
                            type="checkbox"
                            value="admin"
                        />
                        <label className="form-check-label">Admin</label>
                        {"  "}

                        <input
                            className="form-check-input role"
                            type="checkbox"
                            value="mod"
                        />
                        <label className="form-check-label">mod</label>
                        {"  "}
                        <input
                            className="form-check-input role"
                            type="checkbox"
                            value="cash"
                        />
                        <label className="form-check-label">cash</label>
                        {"  "}
                    </div>
                    <button type="submit" className="btn btn-primary">
                        Guardar
                    </button>
                </form>
            </div></div>
    );
}

export default UpdateCliente;