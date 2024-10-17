import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import History from '../pages/History';
import Game from '../pages/Game';

const AppRoutes: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Game />} />
        <Route path="/history" element={<History />} />
      </Routes>
    </Router>
  );
}

export default AppRoutes;
