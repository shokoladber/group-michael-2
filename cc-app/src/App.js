import {LoginSignup} from './Components/common/';
import { Header } from './Components/common/header';
import {Landing} from './Components/common/landing';
import {Navbar} from './Components/common/navbar';


function App() {
  return (
    <div>
      <Landing/>
      <Navbar/>
      <Header/>
      <LoginSignup/>
    </div>
  );
}

export default App;
