export interface MatchData {
    id: number,
    player1: string,
    player2: string,
    choice1: string,
    choice2: string,
    winner: string,
    date: Date
}

export interface MatchDataPost {
    player1: string,
    choice1: string,
}

export interface MatchDataResult {
    winner: string
}