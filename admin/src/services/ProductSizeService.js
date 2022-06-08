import {requestLocal} from "./RequestService";

const basePath = "/v1/product/size"

export async function listProductSizeService(params) {
    return await requestLocal({
        method: 'GET',
        url: basePath,
        params: params,
    })

}

export async function createProductSizeService(data) {
    console.log(data);
    return await requestLocal({
        method: 'POST',
        url: basePath,
        body: data,
    })

}


export async function updateProductSizeService(data) {
    return await requestLocal({
        method: 'PUT',
        url: basePath + '/' + data.id,
        body: data,
    })

}
//
// export async function changeStatusProductSizeService(id, param) {
//     return await requestLocal({
//         method: 'PATCH',
//         url: basePath + '/status/' + id,
//         params: param,
//     })
//
// }