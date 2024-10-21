import axios from "axios";
import { MatchDataPost } from "../interface/MatchData";
import { useMutation } from "@tanstack/react-query";
import { MatchData } from "../interface/MatchData";

const API_URL = 'http://localhost:8080';

const saveMatchData = async (newMatch: MatchDataPost): Promise<MatchData> => {
    const response = await axios.post(`${API_URL}/jokenpo/play`, newMatch);
    return response.data; 
}

export function useSaveMatchData() {
    const mutation = useMutation({
        mutationFn: saveMatchData,
        onSuccess: (data: MatchData) => {
            console.log('Dados salvos com sucesso!');
        },
        onError: (error) => {
            if (axios.isAxiosError(error) && error.response) {
                console.error('Erro ao salvar os dados:', error.response.data);
                alert(`${error.response.data}`); 
            } else {
                console.error('Erro inesperado:', error);
            }
        }
    });

    return mutation;
}
