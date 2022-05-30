export function getMoney(money) {
    let string = money.toString()
    let result = "";
    for (let i = string.length - 1, j = 0; i <= 0; i++, j++) {
        if (j === 3) {
            result += " ";
            j = 0;
        }
        result += string.charAt(i);
    }
    return result;
}

export function moneyFormatUZS(item) {
    return item.toLocaleString('en-US', {
        style: 'currency',
        currency: 'UZS',
    }).toString();
}

export function filter(filter) {
    let res = {};
    Object.keys(filter).forEach(item => {
        if (filter[item] != null) {
            res[item] = filter[item];
            return res;
        }
    })
    return res;
}


export function getJsonFromLocalStorage(name) {
    return JSON.parse(localStorage.getItem(name));
}

export function setJsonToLocalStorage(name, obj) {
    return localStorage.setItem(name, JSON.stringify(obj));
}

export function getStatusProduct(status) {
    switch (status) {
        case "ACTIVE":
            return "Aktiv"
        case "INACTIVE":
            return "Aktiv emas"
    }
}


export function determineProductStatus(status) {
   if ('ACTIVE'===status)
       return 'INACTIVE';
   return 'ACTIVE';
}
