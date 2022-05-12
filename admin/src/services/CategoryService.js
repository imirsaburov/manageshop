import {requestLocal} from "./RequestService";

const basePath = "/v1/category"

export async function categoryList(params) {
    return await requestLocal({
        method: 'GET',
        url: basePath,
        params: params,
    })

}

export async function createCategory(data) {
    return await requestLocal({
        method: 'POST',
        url: basePath,
        body: data,
    })

}


export async function updateCategory(data) {
    return await requestLocal({
        method: 'PUT',
        url: basePath + '/' + data.id,
        body: data,
    })

}

export async function changeStatusCategory(id, param) {
    return await requestLocal({
        method: 'PATCH',
        url: basePath + '/status/' + id,
        params: param,
    })

}