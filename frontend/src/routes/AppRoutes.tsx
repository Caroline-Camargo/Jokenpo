import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import History from '../pages/history/History';
import Game from '../pages/game/Game';

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
