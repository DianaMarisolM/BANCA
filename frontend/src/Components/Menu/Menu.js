import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
const Menu = () => {
    let User
    const navigate = useNavigate();
    JSON.parse(sessionStorage.getItem("Rol")).forEach(element => {
        if(element.nombre==="ROLE_ADMIN")
        {
            User= "ROLE_ADMIN"
        }
    });

    const salir =()=>{
        sessionStorage.removeItem("Rol")
        sessionStorage.removeItem("user")
        sessionStorage.removeItem("key")
        navigate("/")
    }
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    {User==='ROLE_ADMIN' ? (
                        <Link to="/clientes" className="navbar-brand">
                            <i className="fa fa-user" aria-hidden="true"></i> Clientes
                        </Link>) : ("")}
                    <Link to="/cuentas" className="navbar-brand">
                        <i className="fa-solid fa-money-bill"></i> Cuentas
                    </Link>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link className="nav-link active" aria-current="page" href="#">
                                    {" "}
                                </Link>
                            </li>
                        </ul>
                        <form className="d-flex">
                            <button className="btn btn-outline-success" type="button" onClick={salir}>
                                Logout
                            </button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>
    );
};
export default Menu;
