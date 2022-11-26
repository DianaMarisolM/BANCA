/* eslint-disable jsx-a11y/img-redundant-alt */
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import swal from "sweetalert";
import LonginHtml from "./LoginHtml";
import "./Login.css";
import axios from "axios";
const URI = "http://localhost:8080/api/v1/cliente/login";
const Login = () => {
  const [key, setKey] = useState("");
  const [user, setUser] = useState("");
  const navigate = useNavigate();
  const Login = async (e) => {
    e.preventDefault();
    try {
      const Login = await axios({
        method: "POST",
        url: URI,
        headers: {
          user: user,
          key: key,
        },
      });
      sessionStorage.setItem("key", Login.data.password)
      sessionStorage.setItem("user", Login.data.user)
      sessionStorage.setItem("id", Login.data.id)
      sessionStorage.setItem("Rol", JSON.stringify(Login.data.roles))
      swal(
        "Acceso Autorizado para usuario " + Login.data.user,
        "Presione el botón",
        "success"
      ).then((value) => {
        navigate("/menu");
      });
    } catch (error) {

        if(error.message==="Network Error"){
            swal(
                "Acceso no autorizado Error: Servidor No Disponible " ,
                "Presione el botón",
                "error"
              );
        }
      swal(
        "Acceso no autorizado Error: " + JSON.parse(error.request.response).errors[0].message,
        "Presione el botón",
        "error"
      );
    }
  };

  return (
    <LonginHtml mkey={key} user={user} setKey={setKey} Login={Login} setUser={setUser} />
  );
};

export default Login;
