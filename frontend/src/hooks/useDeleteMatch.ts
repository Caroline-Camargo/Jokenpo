import axios from 'axios';
import { useMutation, useQueryClient } from '@tanstack/react-query';

const API_URL = 'http://localhost:8080';

const deleteMatch = async (id: number): Promise<void> => {
  return axios.delete(`${API_URL}/jokenpo/match/${id}`);
};

export function useDeleteMatch() {
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: deleteMatch,
    onSuccess: () => {
        console.log('Partida deletada com sucesso!');
        queryClient.invalidateQueries({ queryKey: ['match-data'] });
    },
    onError: (error: any) => {
      console.error('Erro ao deletar a partida:', error);
    },
  });

  return mutation;
}
