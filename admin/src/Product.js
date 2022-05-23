import React, {Component} from 'react';
import {Button, Checkbox, Col, Image, Popconfirm, Row, Table, Tag} from "antd";
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
            <Row style={{width: "100%"}}>
                <Col offset={1} style={{marginTop: "10px", marginBottom: "10px"}}>
                    <span><Tag color="blue">L</Tag> : 2 </span>
                </Col>
                <Col offset={1} style={{margin: "10px auto"}}>
                    <span><Tag color="blue">XL</Tag> : 2 </span>
                </Col>
                <Col offset={1} style={{margin: "10px auto"}}>
                    <span><Tag color="blue">2XL</Tag> : 2 </span>
                </Col>
                <Col offset={1} style={{margin: "10px auto"}}>
                    <span><Tag color="blue">3XL</Tag> : 2 </span>
                </Col>
                <Col offset={1} style={{margin: "10px auto"}}>
                    <span><Tag color="blue">4XL</Tag> : 2 </span>
                </Col>
                <Col offset={1} style={{margin: "10px auto"}}>
                    <span><Tag color="blue">5XL</Tag> : 2 </span>
                </Col>

            </Row>
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
                <Table bordered pagination={false} dataSource={dataSource} columns={columns}/>
            </>
        );
    }
}

Product.propTypes = {};

export default Product;