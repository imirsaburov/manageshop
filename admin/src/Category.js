import React, {Component} from 'react';
import {Button, Checkbox, Image, Table} from "antd";
// import {
//
// } from '@ant-design/icons';

const dataSource = [
    {
        key: '1',
        name: 'Finka',

        status: <Checkbox checked={false}/>,
        action: <Button>Taxrirlash</Button>
    },
    {
        key: '1',
        name: 'Shim',
        image: <Image
            width={200}
            src="https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png"
        />,
        status: <Checkbox checked={true}/>,
        action: <Button>Taxrirlash</Button>
    },
];

const columns = [
    {
        title: '№',
        dataIndex: 'i',
        key: 'i',
        width: '2%',
        render: (text, record, index) => index + 1
    },

    {
        title: 'Nomi',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Status',
        dataIndex: 'status',
        key: 'status',
        width: '2%',
    },
    {
        title: 'Actions',
        dataIndex: 'action',
        key: 'action',
    },
];


class Category extends Component {
    render() {
        return (
            <>
                <Table bordered dataSource={dataSource} columns={columns}/></>
        );
    }
}

Category.propTypes = {};

export default Category;