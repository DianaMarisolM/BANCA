import Menu from "../Menu/Menu";
import axios from 'axios'
import { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import swal from "sweetalert"
const URI = 'http://localhost:8081/api/v1/cliente/create'

let headers = {
    user: sessionStorage.getItem("user"),
    key: sessionStorage.getItem("key")
};

const CreateCliente = () => {
    const [nombre, setNombre] = useState("");
    const [apellido, setApellido] = useState("");
    const [userName, setUserName] = useState("");
    const [password, setPassWord] = useState("");
    const [roles, setRoles] = useState([]);
    const navigate = useNavigate();

    const Save = async (e) => {
        e.preventDefault();
        try {
            const insertCliente = await axios({
                method: "POST",
                url: URI,
                data: {
                    nombre: nombre,
                    apellido: apellido,
                    userName: userName,
                    password: password
                },
                headers: headers
            });
            if (insertCliente.status === 201) {
                navigate("/clientes");
            } else {
                swal("Error", insertCliente.data.errors[0].type + " " + insertCliente.data.errors[0].message, "error")
            }

        } catch (error) {
            swal("Error", JSON.parse(error.request.response).errors[0].message, "error")
        }
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

export default CreateCliente;