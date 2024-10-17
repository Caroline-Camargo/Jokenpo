import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/header/Header';
import Play from './pages/Game'; 
import History from './pages/History'; 

const App: React.FC = () => {
  return (
    <Router>
      <Header /> 
      <Routes>
        <Route path="/" element={<Play />} />
        <Route path="/history" element={<History />} />
      </Routes>
    </Router>
  );
}

export default App;
