import axios, { AxiosResponse } from "axios"
import { MatchData } from "../interface/MatchData"
import { useQuery } from "@tanstack/react-query"

const API_URL = 'http://localhost:8080'

const fetchData = async (): Promise<AxiosResponse<MatchData[]>> => {
    const response = axios.get(API_URL + '/jokenpo/match')
    return response
}

export function useMatchData() {
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ['match-data'],
        retry: 2
    })

    return {
        ...query,
        data: query.data?.data
    }
}