import { Card } from '../../components/card/card';
import { useMatchData } from '../../hooks/useMatchData';
import { useDeleteMatch } from '../../hooks/useDeleteMatch';
import './History.css';
import { useState } from 'react';

const History: React.FC = () => {
  const { data } = useMatchData();
  const { mutate: deleteMatch } = useDeleteMatch();

  const [searchTerm, setSearchTerm] = useState('');
  const [resultFilter, setResultFilter] = useState('');

  const handleDelete = (id: number) => {
    deleteMatch(id);
  };

  const filteredData = data?.filter((matchData) => {
    const matchesSearch =
      matchData.player1.toLowerCase().includes(searchTerm.toLowerCase())

    const matchesResult = resultFilter === '' ||
      (resultFilter === 'vitoria' && matchData.winner.toLowerCase() !== 'computer' && matchData.winner.toLowerCase() !== 'draw') ||
      (resultFilter === 'derrota' && matchData.winner.toLowerCase() == 'computer' && matchData.winner.toLowerCase() !== 'draw') ||
      (resultFilter === 'empate' && matchData.winner.toLowerCase() === 'draw');

    return matchesSearch && matchesResult;
  })

  .sort((a, b) => b.id - a.id);
  
  return (
    <div className="container">
      <h1 className="text-center" id="title">Histórico de Partidas Jokenpo</h1>
      <div className="row mb-3">
        <div className="col-md-6">
          <input
            type="text"
            className="form-control"
            placeholder="Pesquisar por jogador"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
        <div className="col-md-6">
          <select
            className="form-control"
            value={resultFilter}
            onChange={(e) => setResultFilter(e.target.value)}
          >
            <option value="">Todos</option>
            <option value="vitoria">Vitória</option>
            <option value="derrota">Derrota</option>
            <option value="empate">Empate</option>
          </select>
        </div>
      </div>

      <div className="row">
        {filteredData?.map((matchData) => (
          <div className="col-md-4 mb-3" key={matchData.id}>
            <Card
              id={matchData.id}
              player1={matchData.player1}
              player2={matchData.player2}
              choice1={matchData.choice1}
              choice2={matchData.choice2}
              winner={matchData.winner}
              date={new Date(matchData.date)}
              onDelete={() => handleDelete(matchData.id)}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default History;