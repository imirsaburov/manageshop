import React, {useEffect, useState} from 'react';
import {Button, Checkbox, Col, message, Popconfirm, Row, Table} from "antd";
import {EditOutlined, KeyOutlined} from "@ant-design/icons";
import {
    changeModeratorPassword,
    changeStatusModerator,
    createModerator,
    listModerator,
    updateModerator
} from "../../services/ModeratorService";
import {filter, getJsonFromLocalStorage, setJsonToLocalStorage} from "../../utils";
import ModeratorFilter from "./ModeratorFilter";
import ModeratorModal from "./ModeratorModal";
import ModeratorChangePasswordModal from "./ModeratorChangePasswordModal";
import {PAGE_SIZE} from "../../Constants";
import CustomPagenation from "../custom/CustomPagenation";

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
    const [totalElements, setTotalElements] = useState(0);
    const [updateModalIsOpen, setUpdateModalIsOpen] = useState(false)
    const [passwordModalIsOpen, setPasswordModalIsOpen] = useState(false)

    useEffect(() => {
        getList(getPage())
    }, [])

    const getList = async (page) => {
        let params = {page: page, ...getFilterProperties()}
        params.size = PAGE_SIZE;
        let res = await listModerator(params);
        if (res.success) {
            setTotalElements(res.data.totalElements)
            setPage(res.data.number)
            let list = res.data.content.map((item) => getItem(item))
            setList(list);
        }
    }
    const onChangePage = page => {
        setPage(page);
        getList(page);
    };

    const getFilterProperties = () => {
        return getJsonFromLocalStorage("moderatorFilter");
    }

    const setFilterProperties = (obj) => {
        setJsonToLocalStorage("moderatorFilter", filter(obj));
    }

    const getPage = () => {
        return getJsonFromLocalStorage("moderatorPage");
    }

    const setPage = (page) => {
        setJsonToLocalStorage("moderatorPage", page);
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
                                         getList(getPage())
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
            getList(getPage())
            message.info("Yaratildi");
            setCreatModalIsOpen(false);
            setCurrent({})
        } else message.error(res.data.message);
    }

    const update = async (e) => {
        e.id = current.id;
        let res = await updateModerator(e);
        if (res.success) {
            getList(getPage())
            message.info("Taxrirlandi!");
            setUpdateModalIsOpen(false);
            setCurrent({})
        } else message.error(res.data.message);
    }

    const changePassword = async (e) => {
        e.id = current.id;
        let res = await changeModeratorPassword(e);
        if (res.success) {
            getList(getPage())
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
                params={getFilterProperties()}
                onFinished={e => {
                    setFilterProperties(e);
                    setPage(0)
                    getList(getPage());
                }}
            />

            <div style={{marginBottom: '1rem'}}/>
            <Table pagination={false} bordered dataSource={list} columns={columns}/>
            <CustomPagenation current={getPage()} totalItems={totalElements} onChange={onChangePage}/>
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