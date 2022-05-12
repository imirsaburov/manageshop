import axios from "axios";
import {API_URL, LOGIN_BASIC} from "./Constants";

export function request(data) {

    let headers = data.headers;
    let params = data.params;

    if (!headers)
        headers = {};

    if (!params)
        params = {};


    const accessToken = localStorage.getItem("access_token") || "";
    if (headers["Authorization"] == null && accessToken) {
        headers["Authorization"] = "Bearer " + accessToken;
    }

    let response={};

    return axios.request({
        method: data.method,
        url: data.url,
        params: params,
        headers: headers,
        data: data.body
    }).then(res => {
        return {
            success: true,
            data: res.data,
            statusCode: res.status,
            statusText: res.statusText
        }
    }).catch(err => {
            let res = err.response;
            return {
                success: false,
                data: res.data,
                statusCode: res.status,
                statusText: res.statusText
            }
        }
    )
}

export async function login(data) {
    data.grant_type = 'password';
    let res = await request({
        params: data,
        method: 'POST',
        url: API_URL + "/oauth/token",
        headers: {
            "Authorization": "Basic " + LOGIN_BASIC
        }
    })

    if (res.success) {
        localStorage.setItem("access_token", res.data.access_token)
    }

    return res;
}

export async function userMe() {
    return request({
        method: 'GET',
        url: API_URL + "/v1/user/me",
        params: {},
        headers: {}
    })

}

