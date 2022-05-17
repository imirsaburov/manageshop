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
