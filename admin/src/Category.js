import React, {useEffect, useState} from 'react';
import {Button, Checkbox, Image, Table, message, Popconfirm} from "antd";
import {EditOutlined} from "@ant-design/icons";
import {categoryList, changeStatusCategory, createCategory, updateCategory} from "./services/CategoryService";
import CategoryModal from "./components/CategoryModal";
import {Link} from "react-router-dom";
// import {
//
// } from '@ant-design/icons';

const dataSource = [
    {
        key: '1',
        name: 'Finka',

        status: <Checkbox checked={false}/>,
        action: <Button><EditOutlined/></Button>
    },
    {
        key: '1',
        name: 'Shim',
        image: <Image
            width={200}
            src="https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png"
        />,
        status: <Checkbox checked={true}/>,
        action: <Button><EditOutlined/></Button>
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
        align: 'center'
    },
    {
        title: 'Actions',
        dataIndex: 'action',
        key: 'action',
        width: '8%',
    },
];


const Category = ({currentUser}) => {

    const [list, setList] = useState([])
    const [current, setCurrent] = useState({})
    const [creatModalIsOpen, setCreatModalIsOpen] = useState(false)
    const [updateModalIsOpen, setUpdateModalIsOpen] = useState(false)

    useEffect(() => {
        getList({page: 0, size: 10})
    }, [])

    const getList = async (params) => {
        let res = await categoryList(params);
        if (res.success) {
            let list = res.data.content.map((item) => getItem(item))
            setList(list);
        }
    }

    const getItem = (item) => {
        return {
            key: item.id,
            id: item.id,
            name: item.name,
            status: <Popconfirm placement="topLeft" title={"Statusni o`zgartirmoqchimisiz ?"}
                                onConfirm={async () => {
                                    let res = await changeStatusCategory(item.id, {status: !item.status});
                                    if (res.success) {
                                        message.info("Status o`zgartirildi")
                                        getList({page: 0, size: 10})
                                    }
                                    else message.error(res.data.message)
                                }} okText="Ha" cancelText="Yo'q">
                <Checkbox checked={item.status}/>
            </Popconfirm>,
            action: <Button onClick={() => {
                setCurrent(item);
                setUpdateModalIsOpen(true)
            }
            }><EditOutlined/></Button>
        }
    }
    const create = async (e) => {
        let res = await createCategory(e);
        if (res.success) {
            getList({page: 0, size: 10})
            message.info("Yaratildi");
            setCreatModalIsOpen(false);
        } else message.error(res.data.message);
    }

    const update = async (e) => {
        e.id = current.id;
        let res = await updateCategory(e);
        if (res.success) {
            getList({page: 0, size: 10})
            message.info("Taxrirlandi!");
            setUpdateModalIsOpen(false);
        } else message.error(res.data.message);
    }

    return (
        <>
            <Button onClick={() => setCreatModalIsOpen(true)} type="primary">Qo'shish</Button>
            <div style={{marginBottom: '1rem'}}/>
            <Table bordered dataSource={list} columns={columns}/>

            <CategoryModal
                isOpen={creatModalIsOpen}
                handleOk={create}
                handleCancel={() => setCreatModalIsOpen(false)}/>

            <CategoryModal
                isOpen={updateModalIsOpen}
                handleOk={update}
                data={current}
                handleCancel={() => setUpdateModalIsOpen(false)}/>

        </>);
}

Category.propTypes = {};

export default Category;