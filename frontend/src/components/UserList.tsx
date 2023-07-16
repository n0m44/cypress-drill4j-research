import { useEffect, useState } from "react";
import { DefaultService, UserDto } from "../generated";
import { UserItem } from "./UserItem";

export const UserList = () => {
    const [users, setUsers] = useState<Array<UserDto>>([])

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await DefaultService.getUsers();
                setUsers(response);
            } catch (error) {
                console.log(error);
            }
        };
        fetchUsers();
    }, []);

    return (
        <div>
            <h1 style={{ textAlign: 'center' }}>Users</h1>
            {users.map((user: UserDto) => (
                <UserItem user={user} key={user.id} />
            ))}
        </div>
    )
}