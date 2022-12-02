import "./style.css";
import "../Productos/Producto.css";
import axios from "axios";
import { getApiUrl } from "../config/apiConfig";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";


const URIP = getApiUrl("producto/list");

const Cart = () => {
  const navigate = useNavigate();
  const [Producto, setProducto] = useState([]);
  useEffect(() => {
    getProductos();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const getProductos = async () => {
    try {
      const res = await axios({
        method: "GET",
        url: URIP,
      });
      setProducto(res.data);
    } catch (error) {
      navigate("/noFound");
    }
  };

  return (
    <>
      <div className="container">
        {" "}
        <div className="row">
          {Producto.map((Producto) => (
            <div className="col-md-4 product-men mt-5">
              <div className="men-pro-item simpleCart_shelfItem">
                <div className="men-thumb-item text-center">
                  <img src={Producto.url} alt="" className="imageCart" />
                  <div className="men-cart-pro">
                    <div className="inner-men-cart-pro">
                      <Link to="/cart" className="link-product-add-cart">
                        Ver
                      </Link>
                    </div>
                  </div>
                </div>
                <div className="item-info-product text-center border-top mt-4">
                  <h4 className="pt-1">
                    <Link to="/cart">{Producto.nombre}</Link>
                  </h4>
                  <div className="info-product-price my-2">
                    <span className="item_price">$ { new Intl.NumberFormat('en-es', { maximumSignificantDigits: 3 }).format(Producto.valor )}</span>
                    <del>{ new Intl.NumberFormat('en-es', { maximumSignificantDigits: 3 }).format(Math.round(Producto.valor * 1.2)) }</del>
                  </div>
                  <div className="snipcart-details top_brand_home_details item_add single-item hvr-outline-out">
                    <form action="#" method="post">
                      <fieldset>
                        <input
                          type="submit"
                          name="submit"
                          value="Agregar al carro"
                          className="button btn"
                        />
                      </fieldset>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default Cart;
