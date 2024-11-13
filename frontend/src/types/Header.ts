import {Dispatch, SetStateAction} from "react";

export type HeaderProps = {
    username: Username;
    setUsername: Dispatch<SetStateAction<Username>>
}

export type Username = {
    id: string,
    username: string,
    role: string,
    avatarUrl: string
}

export const defaultUsername = {
    id: '',
    username: "",
    role: "",
    avatarUrl: ''
}