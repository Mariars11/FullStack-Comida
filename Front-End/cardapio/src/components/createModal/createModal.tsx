import { useEffect, useState } from "react";
import { useFoodDataMutate } from "../../hooks/useFoodDataMutate";
import { FoodData } from "../../Interface/FoodData";
import "./modal.css";

interface InputProps {
    label: string,
    value: string | number,
    updateValue(value: any): void
}

const Input = ({label, value, updateValue}: InputProps)  => {
    return (
        <>
            <label>{label}</label>
            <input value={value} onChange={ e => updateValue(e.target.value)}></input>
        </>
    )

} 
interface ModalProps{
    closeModal() : void;
}
export function CreateModal({closeModal} : ModalProps) {
    const [title, setTitle] = useState("");
    const [price, setPrice] = useState(0);
    const [image, setImage] = useState("");
    const {mutate, isSuccess, isLoading} = useFoodDataMutate();

    const submit = () => {
        const foodData: FoodData = {
            title,
            price,
            image
        };
        mutate(foodData);
    }
    useEffect(() => {
        if(isSuccess){
            closeModal();
        } 
    }, [isSuccess])
    
    return(
        <div className="modal-overlay">
            <div className="modal-body">
            <h2>Cadastre um novo item no cardápio</h2>
                <form className="input-container">
                    <Input label="title" value={title} updateValue={setTitle}/>
                    <Input label="price" value={price} updateValue={setPrice}/>
                    <Input label="image" value={image} updateValue={setImage}/>
                </form>
                <button onClick={submit} className="btn-secondary">
                    {isLoading ? "Postando..." : "Postar"}
                </button>
            </div>
        </div>
    )
}