import axios from "axios"

export const signUpAPI = (body) => {
    return axios.post('/api/1.0/users', body);
}

export const login = creds => {
    return axios.post('/api/1.0/auth', {}, { auth: creds });
};

export const sharePosts = (post,postLink) => {
    return axios.post('/api/1.0/posts', post, postLink)
};

export const getPosts = () => {
    return axios.get('/api/1.0/posts');
}
