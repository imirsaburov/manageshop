import {requestLocal} from "./RequestService";

const basePath = "/v1/size"

export async function listSize(params) {
    return await requestLocal({
        method: 'GET',
        url: basePath,
        params: params,
    })

}

export async function createSize(data) {
    return await requestLocal({
        method: 'POST',
        url: basePath,
        body: data,
    })

}


export async function updateSize(data) {
    return await requestLocal({
        method: 'PUT',
        url: basePath + '/' + data.id,
        body: data,
    })

}

export async function changeStatusSize(id, param) {
    return await requestLocal({
        method: 'PATCH',
        url: basePath + '/status/' + id,
        params: param,
    })

}