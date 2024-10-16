import './card.css'
interface CardProps {
  id: number;
  player1: string;
  player2: string;
  choice1: string;
  choice2: string;
  winner: string;
  date: Date;
}

export function Card({ id, player1, player2, choice1, choice2, winner, date }: CardProps) {
  return (
    <div className="card mb-3 custom-card">
      <div className="card-body">
        <h5 className="card-title text-center font-weight-bold">
          Partida {id}
        </h5>
        <div className="row">
          <div className="col text-center">
            <p><strong> Jogador 1: </strong>
              {player1}
            </p>
            <p><strong>Escolha: </strong>{choice1}</p>
          </div>

          <div className="col text-center">
            <p><strong> Jogador 2: </strong>{player2}</p>
            <p><strong>Escolha: </strong>{choice2}</p>
          </div>
        </div>
        <p><strong>Jogador Vencedor: </strong>{winner}</p>
        <p><strong>Data e Hora da Partida:</strong> {date.toLocaleString('pt-BR', { dateStyle: 'short', timeStyle: 'short' })}</p>
      </div>
    </div>
  );
}