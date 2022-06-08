import React from "react";
import {Button, Table} from "antd";

const columns = [

    {
        title: 'O`lcham',
        dataIndex: 'sizeName',
        key: 'sizeName',
        // width: '8%',
    },


    {
        title: 'Qolgan soni',
        dataIndex: 'count',
        key: 'count',
        // width: '8%',
    },
    {
        title: 'Sotilgan soni',
        dataIndex: 'soldCount',
        key: 'soldCount',
        // width: '8%',
    },

    {
        title: 'Taxrirlash',
        dataIndex: 'action',
        align: 'center',
        key: 'action',
        width: '15%',
    },
];


export const ProductSizeComponent = ({itemList,product, editOpenFunction}) => {
    return <>
        <Table size={'small'} pagination={false} columns={columns} dataSource={itemList.map(e => {
            return {
                id: e.id,
                sizeName: e.sizeName,
                count: e.count,
                soldCount: e.soldCount,
                productId: e.productId,
                action: <Button onClick={() => editOpenFunction(product,e)} size={'small'}>Taxrirlash</Button>
            }
        })}/>
    </>
}