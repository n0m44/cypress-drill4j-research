import HTTPStatusCode from '../statusesCodes';

const loginEndpoint = {
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

export default loginEndpoint;
