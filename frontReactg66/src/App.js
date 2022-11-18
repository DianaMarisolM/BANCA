
import './App.css';
import Menu from './components/menu/Menu';
import Cuerpo from './components/menu/Cuerpo';
const App = () => {
  return (
   <div>
    <Menu brayan="Brayan" ciudad="Medellin" />
    <Cuerpo />
    <Cuerpo />
    <Cuerpo />
   </div>
  );
}

export default App;
