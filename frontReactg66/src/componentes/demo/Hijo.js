const Hijo = ({ mensaje, suma,resta }) => {
  return (
    <>
      <p>mensaje= {mensaje} desde padre</p>
      <button onClick={suma}>Sumar</button>
      <button onClick={resta}>restar</button>
    </>
  );
};

export default Hijo;
