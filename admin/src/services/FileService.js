import {request, requestLocal} from "./RequestService";

export async function uploadPhoto(data) {
    let obj = new FormData();
    obj.append("file", data);

    return await requestLocal({
        method: 'POST',
        url: "/v1/file/upload",
        body: obj,
    })
}

export async function getPhoto(id) {

    let obj = await requestLocal({
        method: 'GET',
        // headers: {
        //     responseType: 'blob'
        // },
        url: "/v1/file/" + id
    });

    let blob = new Blob(
        [obj.data],
        { type: obj.headers['content-type'] }
    )

    return URL.createObjectURL(blob);
}