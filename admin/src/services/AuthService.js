import {LOGIN_BASIC} from "../Constants";
import {request, requestLocal} from "./RequestService";


export async function login(data) {
    data.grant_type = 'password';
    let res = await requestLocal({
        params: data,
        method: 'POST',
        url: "/oauth/token",
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
    return requestLocal({
        method: 'GET',
        url: "/v1/auth/user/me",
        params: {},
        headers: {}
    })

}

