import { Card } from '../components/card/card';
import { useMatchData } from '../hooks/useMatchData';
import './History.css';

const History: React.FC = () => {
  const { data } = useMatchData();
  
  return (
    <div className="container">
      <h1 className="text-center" id='title'>Histórico de Partidas Jokenpo</h1>
      <div className="row">
        {data?.map(matchData => (
          <div className="col-md-4 mb-3" key={matchData.id}>
            <Card 
              id={matchData.id} 
              player1={matchData.player1} 
              player2={matchData.player2} 
              choice1={matchData.choice1} 
              choice2={matchData.choice2} 
              winner={matchData.winner} 
              date={new Date(matchData.date)}
            />
          </div>
        ))}
      </div>
    </div>
  );
}

export default History;
