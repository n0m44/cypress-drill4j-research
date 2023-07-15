import React from "react";
import { DefaultService } from "../generated";

export const RegistrationForm = () => {
    return (
        <form>
            <input type="text"/>
            <input type="password"/>
            <button
                onClick={(event) => {
                    event.preventDefault()
                    DefaultService.registration({login: 'login', password: 'password'})
                }}
            >
                Registration
            </button>
        </form>
    )
}