import Hijo from "./Hijo";

import { useState,useEffect } from "react";

const Padre = () => {
const [valor, setValor] = useState(0);
  const suma=()=>{
    setValor(valor+1)
  }
  const resta=()=>{
    setValor(valor-1)
  }

  useEffect(() => {
    setValor(valor+1)
  // eslint-disable-next-line react-hooks/exhaustive-deps
  },[]);

  return (
    <p>
      Padre
      <Hijo mensaje="Hola todos" suma={suma}  resta={resta} />
      valor = {valor}
    </p>
  );
};

export default Padre;
