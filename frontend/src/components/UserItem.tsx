import React from 'react';
import { UserDto, DefaultService } from "../generated"

export const UserItem: React.FC<{ user: UserDto }> = ({ user }) => {

  return (
    <div>
      <div>
        <strong>{user.id} {user.login}</strong>
        <div>
          {user.surname} {user.name}
        </div>
      </div>
      <div>
        <button onClick={() => DefaultService.deleteUser(user.id!)}>Delete</button>
      </div>
    </div>
  );
};
