import axios, { AxiosPromise } from "axios"
import { FoodData } from "../Interface/FoodData";
import { useMutation, useQueryClient } from "@tanstack/react-query";

const url_api = "http://localhost:8080";
const postData = async (data: FoodData): AxiosPromise<any> => {
    const response = axios.post(url_api + "/food", data)
    return response;
}

export function useFoodDataMutate(){
    const queryClient = useQueryClient();
    const mutate = useMutation({
        mutationFn: postData,
        retry: 2,
        onSuccess: () => {
            queryClient.invalidateQueries(['food-data']) 
        }
    })

    return mutate;
}