import {requestLocal} from "./RequestService";

const basePath = "/v1/user/moderator"

export async function listModerator(params) {
    return await requestLocal({
        method: 'GET',
        url: basePath,
        params: params,
    })

}

export async function createModerator(data) {
    return await requestLocal({
        method: 'POST',
        url: basePath,
        body: data,
    })

}


export async function updateModerator(data) {
    return await requestLocal({
        method: 'PUT',
        url: basePath + '/' + data.id,
        body: data,
    })

}

export async function changeStatusModerator(id, param) {
    return await requestLocal({
        method: 'PATCH',
        url: basePath + '/enabled/' + id,
        params: param,
    })

}


export async function changeModeratorPassword(data) {
    return await requestLocal({
        method: 'PATCH',
        url: basePath + '/password/' + data.id,
        body: data,
    })

}