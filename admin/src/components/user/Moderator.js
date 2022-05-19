import React, {useEffect, useState} from 'react';
import {Button, Checkbox, Col, Image, message, Popconfirm, Row, Table} from "antd";
import {EditOutlined, KeyOutlined} from "@ant-design/icons";
import {
    changeModeratorPassword,
    changePassword,
    changeStatusModerator,
    createModerator,
    updateModerator
} from "../../services/ModeratorService";
import {filter} from "../../utils";
import {listModerator} from "../../services/ModeratorService";
import ProductFilter from "../../ProductFilter";
import ModeratorFilter from "./ModeratorFilter";
import ModeratorModal from "./ModeratorModal";
import ModeratorChangePasswordModal from "./ModeratorChangePasswordModal";

const columns = [
    {
        title: '№',
        dataIndex: 'i',
        key: 'i',
        width: '2%',
        render: (text, record, index) => index + 1
    },

    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
        width: '2%',
    },
    {
        title: 'Ismi',
        dataIndex: 'firstName',
        key: 'firstName',
    },
    {
        title: 'Familya',
        dataIndex: 'lastName',
        key: 'lastName',
    },
    {
        title: 'Login',
        dataIndex: 'username',
        key: 'username',
    },
    {
        title: 'Status',
        dataIndex: 'enabled',
        key: 'enabled',
        width: '2%',
        align: 'center'
    },
    {
        title: 'Taxrirlash',
        dataIndex: 'action',
        key: 'action',
        width: '10%',
        align: 'center'
    },
];


const User = ({currentUser}) => {

    const [list, setList] = useState([])
    const [current, setCurrent] = useState({})
    const [creatModalIsOpen, setCreatModalIsOpen] = useState(false)
    const [updateModalIsOpen, setUpdateModalIsOpen] = useState(false)
    const [passwordModalIsOpen, setPasswordModalIsOpen] = useState(false)

    useEffect(() => {
        getList({page: 0, size: 10})
    }, [])

    const getList = async (params) => {
        let res = await listModerator(params);
        if (res.success) {
            let list = res.data.content.map((item) => getItem(item))
            setList(list);
        }
    }

    const getItem = (item) => {
        return {
            key: item.id,
            id: item.id,
            lastName: item.lastName,
            firstName: item.firstName,
            username: item.username,
            enabled: <Popconfirm placement="topLeft" title={"Statusni o`zgartirmoqchimisiz ?"}
                                 onConfirm={async () => {
                                     let res = await changeStatusModerator(item.id, {status: !item.enabled});
                                     if (res.success) {
                                         message.info("Status o`zgartirildi")
                                         getList({page: 0, size: 10})
                                     } else message.error(res.data.message)
                                 }} okText="Ha" cancelText="Yo'q">
                <Checkbox checked={item.enabled}/>
            </Popconfirm>,
            action: <>
                <Button onClick={() => {
                    setCurrent(item);
                    setUpdateModalIsOpen(true)
                }
                }><EditOutlined/>
                </Button>
                <Button onClick={() => {
                    setCurrent(item);
                    setPasswordModalIsOpen(true)
                }
                }><KeyOutlined/></Button></>
        }
    }
    const create = async (e) => {
        let res = await createModerator(e);
        if (res.success) {
            getList({page: 0, size: 10})
            message.info("Yaratildi");
            setCreatModalIsOpen(false);
            setCurrent({})
        } else message.error(res.data.message);
    }

    const update = async (e) => {
        e.id = current.id;
        let res = await updateModerator(e);
        if (res.success) {
            getList({page: 0, size: 10})
            message.info("Taxrirlandi!");
            setUpdateModalIsOpen(false);
            setCurrent({})
        } else message.error(res.data.message);
    }

    const changePassword = async (e) => {
        e.id = current.id;
        let res = await changeModeratorPassword(e);
        if (res.success) {
            getList({page: 0, size: 10})
            message.info("Taxrirlandi!");
            setPasswordModalIsOpen(false);
            setCurrent(null)
        } else message.error(res.data.message);
    }

    return (
        <>
            <Row>
                <Col md={1}>
                    <Button onClick={() => setCreatModalIsOpen(true)} type="primary">Qo'shish</Button>
                </Col>


            </Row>
            <ModeratorFilter
                onFinished={e => {
                    getList({...filter(e), page: 0, size: 10})
                }}
            />

            <div style={{marginBottom: '1rem'}}/>
            <Table bordered dataSource={list} columns={columns}/>

            {creatModalIsOpen && <ModeratorModal
                isOpen={creatModalIsOpen}
                handleOk={create}
                handleCancel={() => {
                    setCreatModalIsOpen(false)
                    setCurrent(null)
                }}/>}

            {updateModalIsOpen && <ModeratorModal
                isOpen={updateModalIsOpen}
                handleOk={update}
                data={current}
                handleCancel={() => {
                    setUpdateModalIsOpen(false);
                    setCurrent(null)
                }}/>}
            {passwordModalIsOpen && <ModeratorChangePasswordModal
                isOpen={passwordModalIsOpen}
                handleOk={changePassword}
                handleCancel={() => {
                    setPasswordModalIsOpen(false);
                    setCurrent(null)
                }}/>}

        </>);
}

export default User;