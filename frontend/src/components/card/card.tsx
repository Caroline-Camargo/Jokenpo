import './card.css';

interface CardProps {
  id: number;
  player1: string;
  player2: string;
  choice1: string;
  choice2: string;
  winner: string;
  date: Date;
  onDelete: (id: number) => void; 
}

export function Card({ id, player1, player2, choice1, choice2, winner, date, onDelete }: CardProps) {
  return (
    <div className="card mb-3">
      <div className="card-body position-relative custom-card">
        <button className="delete-btn" onClick={() => onDelete(id)}>
          🗑️
        </button>
        <h5 className="card-title text-center font-weight-bold">
          Partida {id}
        </h5>
        <div className="row">
          <div className="col text-center">
            <p><strong> Jogador 1:</strong><br/>{player1}</p>
            <p><strong>Escolha: </strong>{choice1}</p>
          </div>
          <div className="col text-center">
            <p><strong> Jogador 2: </strong><br/>{player2}</p>
            <p><strong>Escolha: </strong>{choice2}</p>
          </div>
        </div>
        <p><strong>Jogador Vencedor: </strong>{winner}</p>
        <p><strong>Data e Hora da Partida:</strong> {date.toLocaleString('pt-BR', { dateStyle: 'short', timeStyle: 'short' })}</p>
      </div>
    </div>
  );
}
