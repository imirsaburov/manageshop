import {requestLocal} from "./RequestService";

const basePath = "/v1/product"

export async function listProduct(params) {
    return await requestLocal({
        method: 'GET',
        url: basePath,
        params: params,
    })

}

export async function createProduct(data) {
    return await requestLocal({
        method: 'POST',
        url: basePath,
        body: data,
    })

}


export async function updateProduct(data) {
    return await requestLocal({
        method: 'PUT',
        url: basePath + '/' + data.id,
        body: data,
    })

}

export async function changeStatusProduct(id, param) {
    return await requestLocal({
        method: 'PATCH',
        url: basePath + '/status/' + id,
        params: param,
    })

}