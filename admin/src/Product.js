import React, {Component} from 'react';
import {Button, Checkbox, Image, Popconfirm, Table, Tag} from "antd";
import ProductFilter from "./ProductFilter";
import {moneyFormatUZS} from "./utils";
import {EditOutlined} from '@ant-design/icons';
// import {
//
// } from '@ant-design/icons';

const dataSource = [

    {
        key: '1',
        name: 'Loro piono shim',
        category: 'Shim',
        inputPrice: moneyFormatUZS('100000'),
        sellPrice: moneyFormatUZS('120000'),
        image: <Image
            width={100}
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTW-qwrd18SVecNJqPU-5aVgp6b-HSW9xfSjg&usqp=CAU"
        />,
        count: <>
            <span><Tag color="blue">S</Tag> : 2 </span>&emsp;&emsp;
            <span><Tag color="blue">S</Tag> : 2 </span><br/>
            <span><Tag color="blue">S</Tag> : 2 </span>&emsp;&emsp;
            <span><Tag color="blue">S</Tag> : 2 </span><br/>
            <span><Tag color="blue">S</Tag> : 2 </span>&emsp;&emsp;
            <span><Tag color="blue">S</Tag> : 2 </span><br/> <span><Tag color="blue">S</Tag> : 2 </span>&emsp;&emsp;
            <span><Tag color="blue">S</Tag> : 2 </span><br/>
        </>,
        status: <Popconfirm placement="topLeft" title={"Status o'zgarsinmi?"}
                            onConfirm={() => console.log()} okText="Ha" cancelText="Yo'q">
            <Checkbox checked={true}/>
        </Popconfirm>,
        action: <Button><EditOutlined/></Button>
    },
]


const columns = [
    {
        title: '№',
        dataIndex: 'i',
        key: 'i',
        width: '2%',
        render: (text, record, index) => index + 1
    },
    {
        title: 'Rasm',
        dataIndex: 'image',
        key: 'image',
        width: '8%',
    },
    {
        title: 'Nomi',
        dataIndex: 'name',
        key: 'name',
    },

    {
        title: 'Toifa',
        dataIndex: 'category',
        key: 'category',
        width: '8%',
    }, {
        title: 'Kelish narxi',
        dataIndex: 'inputPrice',
        key: 'inputPrice',
        width: '8%',
    },
    {
        title: 'Sotish narxi',
        dataIndex: 'sellPrice',
        key: 'sellPrice',
        width: '8%',
    }, {
        title: 'Soni',
        dataIndex: 'count',
        key: 'count',
        width: '12%',
    },
    {
        title: 'Status',
        dataIndex: 'status',
        key: 'status',
        align: 'center',
        width: '3%',
    },
    {
        title: 'Taxrirlash',
        dataIndex: 'action',
        align: 'center',
        key: 'action',
        width: '5%',
    },
];


class Product extends Component {
    render() {
        return (
            <>
                <ProductFilter/>
                <Table bordered dataSource={dataSource} columns={columns}/>
            </>
        );
    }
}

Product.propTypes = {};

export default Product;