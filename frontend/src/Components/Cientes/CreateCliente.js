import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import swal from "sweetalert";
const URI = "http://129.213.23.13:8080/bancasergio/api/v1/cliente/create";



const CreateCliente = (props) => {
    let headers =props.headers
    const [nombre, setNombre] = useState("");
    const [apellido, setApellido] = useState("");
    const [userName, setUserName] = useState("");
    const [password, setPassWord] = useState("");
    let roles = document.getElementsByClassName("role");

    const navigate = useNavigate();

    const Save = async () => {


        let listaRoles = [];
        for (const rol of roles) {
            if (rol.checked) {
                listaRoles.push(rol.value);
            }
        }


        try {
            const insertCliente = await axios({
                method: "POST",
                url: URI,
                data: {
                    nombre: nombre,
                    apellido: apellido,
                    userName: userName,
                    password: password,
                    role: listaRoles,
                },
                headers: headers,
            });
            if (insertCliente.status === 201) {
                navigate("/clientes");
            } else {
                swal(
                    "Error",
                    insertCliente.data.errors[0].type +
                    " " +
                    insertCliente.data.errors[0].message,
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


    return (
        <div>

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

                    <div className="mb-3">
                        <div>
                            {" "}
                            <label className="form-label">Roles</label>
                        </div>
                        <div>
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
                    </div>
                    <button type="submit" className="btn btn-outline-primary">
                        Guardar
                    </button>
                </form>
            </div>
        </div>
    );
};

export default CreateCliente;
