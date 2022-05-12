import axios from "axios";
import {API_URL} from "../Constants";

export function request(data) {

    let headers = data.headers;
    let params = data.params;

    if (!headers)
        headers = {};

    if (!params)
        params = {};

    headers["Accept-Language"] = 'uz';


    const accessToken = localStorage.getItem("access_token") || "";
    if (headers["Authorization"] == null && accessToken) {
        headers["Authorization"] = "Bearer " + accessToken;
    }

    let response = {};

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

export function requestLocal(data) {
    data.url = API_URL + data.url;
    return request(data);
}