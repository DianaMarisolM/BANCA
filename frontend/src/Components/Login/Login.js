import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import swal from 'sweetalert';

import "./Login.css"

const URI = "http://localhost:8081/api/v1/cliente/login";
const Login = () => {
    const [User, setUser] = useState("");
    const [PassWord, setPassWord] = useState("");
    const alerta = document.getElementById("alerta")
    const navigate = useNavigate();
    const Login = async (e) => {
        e.preventDefault();
        try {
            const Login = await axios({
                method: "POST",
                url: URI,
                headers: {
                    user: User,
                    key: PassWord,
                },
            });
            sessionStorage.setItem("key", Login.data.password)
            sessionStorage.setItem("user", Login.data.user)
            sessionStorage.setItem("id", Login.data.id)
            sessionStorage.setItem("Rol", JSON.stringify(Login.data.roles))
            swal("Acceso Autorizado para usuario " + Login.data.user, "Presione el botón", "success")
            .then((value)=>{
                navigate("/principal")
            });


        } catch (error) {
            console.log(JSON.parse(error.request.response));
            alerta.innerHTML = JSON.parse(error.request.response).errors[0].message
            setTimeout(function () {
                alerta.style.display = "none"
            }, 2000);
            alerta.style.display = "block"

        }

    };

    return (
        <div className="container col-3">
            <form onSubmit={Login}>
                <div className="form-outline mb-3">
                    <label className="form-label" >
                        User Name{" "}
                    </label>{" "}
                    <input
                        value={User}
                        onChange={(e) => setUser(e.target.value)}
                        type="text"
                        id="user"
                        className="form-control"
                    />
                </div>{" "}
                <div className="form-outline mb-3">
                    <label className="form-label" >
                        Password{" "}
                    </label>{" "}
                    <input
                        value={PassWord}
                        onChange={(e) => setPassWord(e.target.value)}
                        type="password"
                        id="password"
                        className="form-control"
                    />
                </div>{" "}
                <div className="alert alert-danger mostrar" role="alert" id="alerta">
                    A simple danger alert—check it out!
                </div>{" "}
                <button type="submit" className="btn btn-outline-primary btn-block mb-4 ">
                <i className="fa fa-sign-in" aria-hidden="true"></i> Sign In{" "}
                </button>{" "}
            </form>{" "}
        </div>
    );
};

export default Login;
