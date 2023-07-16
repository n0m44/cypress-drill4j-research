import { DefaultService } from "../generated";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export type LoginRegistrationFormProps = {
    actinTitle: string,
    alternativeMsg: string,
    alternativeAction: string,
    alternativePath: string
}

export const LoginRegistrationForm = (props: LoginRegistrationFormProps) => {
    const [login, setLogin] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate();

    const handleSubmit = () => {
        if (props.alternativePath === '/registration') {
            DefaultService.login({ login: login, password: password }).then((sessionId) => {
                console.log(sessionId)
                document.cookie = `sessionId=${sessionId};max-age=0;Path=/`
            })
        }
        else {
            DefaultService.registration({ login: login, password: password })
        }
    }

    const goToSignInPage = () => {
        navigate(props.alternativePath)
    }

    return (
        <div>
            <h2>{props.actinTitle}</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    id='login'
                    name='login'
                    value={login}
                    required
                    onChange={(event) => setLogin(event.target.value)}
                />
                <input
                    type="password"
                    id='password'
                    name='password'
                    value={password}
                    required
                    onChange={(event) => setPassword(event.target.value)}
                />
                <button>
                    {props.actinTitle}
                </button>
                <p>
                    {props.alternativeMsg}
                    <span onClick={goToSignInPage}>
                        {props.alternativeAction}
                    </span>
                </p>
            </form>
        </div>
    )
}