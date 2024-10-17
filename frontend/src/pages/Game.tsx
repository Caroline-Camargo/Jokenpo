import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Game.css';

const Play: React.FC = () => {
  const [name, setName] = useState('');
  const [result, setResult] = useState('');

  const handleChoice = async (choice: string) => {
    if (!name) {
      setResult('Por favor, insira seu nome antes de jogar.');
      return;
    }

    try {
      const response = await axios.get(`http://localhost:8080/jokenpo/play/${name}/${choice}`);
      setResult(response.data);
    } catch (error) {
      setResult('Ocorreu um erro. Verifique sua escolha e tente novamente.');
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center" id='title'>Jogo de Jokenpo</h1>

      <div className="row justify-content-center mb-4">
        <div className="col-md-6 text-center">
          <div className="form-group">
            <label htmlFor="name">Digite seu nome:</label>
            <input
              type="text"
              id="name"
              className="form-control"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Seu nome"
            />
          </div>
        </div>
      </div>

      <div className="row justify-content-center">
        <div className="col-md-4 text-center choice-option">
          <span
            role="img"
            aria-label="Pedra"
            className="choice-icon"
            onClick={() => handleChoice('rock')}
          >
            ✊
          </span>
          <p>Pedra</p>
        </div>
        <div className="col-md-4 text-center choice-option">
          <span
            role="img"
            aria-label="Papel"
            className="choice-icon"
            onClick={() => handleChoice('paper')}
          >
            ✋
          </span>
          <p>Papel</p>
        </div>
        <div className="col-md-4 text-center choice-option">
          <span
            role="img"
            aria-label="Tesoura"
            className="choice-icon"
            onClick={() => handleChoice('scissors')}
          >
            ✌️
          </span>
          <p>Tesoura</p>
        </div>
      </div>

      {result && (
        <div className="row justify-content-center mt-4">
          <div className="col-md-6 text-center">
            <h4>Resultado: {result}</h4>
          </div>
        </div>
      )}
    </div>
  );
};

export default Play;
