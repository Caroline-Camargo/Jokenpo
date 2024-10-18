import React, { useState } from 'react';
import { useSaveMatchData } from '../hooks/useSaveMatchData'; 
import './Game.css';

const Game: React.FC = () => {
  const [name, setName] = useState('');
  const [playerChoice, setPlayerChoice] = useState<string | null>(null);
  const [result, setResult] = useState<string | null>(null);

  const { mutate, isError, error, isSuccess } = useSaveMatchData();

  const handleChoice = (choice: string) => {
    setPlayerChoice(choice);

    mutate(
      { player1: name, choice1: choice }, 
      {
        onSuccess: (data) => {
          if (typeof data === 'string' && data === 'draw') {
            setResult('Empate!');
          }
          else {
            if (typeof data === 'string' && data === 'Computer') {
              setResult('Você perdeu, o vencedor foi o computador');
            }
            else {
              setResult('Parabéns, Você Ganhou!')
            }
          }  
        },
        onError: (error) => {
          console.error('Erro ao salvar a partida:', error);
        }
      }
    );
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center" id='title'>Jogo de Jokenpo</h1>

      <div className="row justify-content-center mb-4">
        <div className="col-md-6 text-center">
          <div className="form-group">
            <label htmlFor="name">Digite seu nome:</label>
            <input
              autoComplete='name'
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

      {isError && (
        <div className="row justify-content-center mt-4">
          <div className="col-md-6 text-center">
            <p>Ocorreu um erro ao salvar a partida</p>
          </div>
        </div>
      )}

      {isSuccess && result && (
        <div className="row justify-content-center mt-4">
          <div className="col-md-6 text-center">
            <h4>{result}</h4>
          </div>
        </div>
      )}
    </div>
  );
};

export default Game;
