/* eslint-disable jsx-a11y/img-redundant-alt */
const LonginHtml = ({user,mkey,Login,setUser,setKey}) => {
    return ( 
        <section className="vh-100">
      <div className="container-fluid h-custom">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col-md-9 col-lg-6 col-xl-5">
            <img
              src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
              className="img-fluid"
              alt="Sample image"
            />
          </div>
          <div className="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
            <form onSubmit={Login}>
              <div className="form-outline mb-4">
                <input
                  value={user}
                  onChange={(e) => setUser(e.target.value)}
                  type="text"
                  id="form3Example3"
                  className="form-control form-control-lg"
                  placeholder="Enter a valid email address"
                />
                <label className="form-label" htmlFor="form3Example3">
                  UserName
                </label>
              </div>
              <div className="form-outline mb-3">
                <input
                  value={mkey}
                  onChange={(e) => setKey(e.target.value)}
                  type="password"
                  id="form3Example4"
                  className="form-control form-control-lg"
                  placeholder="Enter password"
                />
                <label className="form-label" htmlFor="form3Example4">
                  Password
                </label>
              </div>

              <div className="text-center text-lg-start mt-4 pt-2">
                <button
                  type="submit"
                  className="btn btn-outline-primary btn-block mb-4 "
                >
                  <i className="fa fa-sign-in" aria-hidden="true"></i> Sign In{" "}
                </button>{" "}
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
     );
}
 
export default LonginHtml;