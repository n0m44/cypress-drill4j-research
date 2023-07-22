import HTTPStatusCode from './statusesCodes';

export const registrationEndpoint = {
  method: 'POST',
  path: '/auth/registration',
  body: (login: string, password: string) => {
    return { login, password };
  },
  statuses: {
    success: HTTPStatusCode.success,
    conflict: HTTPStatusCode.conflict,
  },
} as const;

export const loginEndpoint = {
  method: 'POST',
  path: '/auth/login',
  body: (login: string, password: string) => {
    return { login, password };
  },
  statuses: {
    sucess: HTTPStatusCode.success,
    error: HTTPStatusCode.unauthorized,
  },
} as const;

export const usersListEndpoint = {
  method: 'GET',
  path: '/user',
  statuses: {
    success: HTTPStatusCode.success,
    forbidden: HTTPStatusCode.forbidden,
  },
} as const;
