import React, {useEffect, useState} from 'react';
import {Button, Checkbox, Col, Image, message, Popconfirm, Row, Table} from "antd";
import {EditOutlined} from "@ant-design/icons";
import {changeStatusCategory, createCategory, listCategory, updateCategory} from "../../services/CategoryService";
import CategoryModal from "./CategoryModal";
import CategoryFilter from "./CategoryFilter";
import {filter, getJsonFromLocalStorage, setJsonToLocalStorage} from "../../utils";
import {PAGE_SIZE} from "../../Constants";
import {listModerator} from "../../services/ModeratorService";
import CustomPagenation from "../custom/CustomPagenation";
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
        title: 'Taxrirlash',
        dataIndex: 'action',
        key: 'action',
        align: 'center',
        width: '8%',
    },
];


const Category = ({currentUser}) => {

    const [list, setList] = useState([])
    const [current, setCurrent] = useState({})
    const [creatModalIsOpen, setCreatModalIsOpen] = useState(false)
    const [updateModalIsOpen, setUpdateModalIsOpen] = useState(false)
    const [totalElements, setTotalElements] = useState(0);

    useEffect(() => {
        getList(getPage())
    }, [])

    const getList = async (page) => {
        let params = {page: page, ...getFilterProperties()}
        params.size = PAGE_SIZE;
        let res = await listCategory(params);
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
        return getJsonFromLocalStorage("categoryFilter");
    }

    const setFilterProperties = (obj) => {
        setJsonToLocalStorage("categoryFilter", filter(obj));
    }

    const getPage = () => {
        return getJsonFromLocalStorage("categoryPage");
    }

    const setPage = (page) => {
        setJsonToLocalStorage("categoryPage", page);
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
                                        getList(getPage())
                                    } else message.error(res.data.message)
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
            getList(getPage())
            message.info("Yaratildi");
            setCreatModalIsOpen(false);
            setCurrent({})
        } else message.error(res.data.message);
    }

    const update = async (e) => {
        e.id = current.id;
        let res = await updateCategory(e);
        if (res.success) {
            getList(getPage())
            message.info("Taxrirlandi!");
            setUpdateModalIsOpen(false);
            setCurrent({})
        } else message.error(res.data.message);
    }

    return (
        <>
            <Row>
                <Col md={1}>
                    <Button onClick={() => setCreatModalIsOpen(true)} type="primary">Qo'shish</Button>
                </Col>


            </Row>
            <CategoryFilter
                params={getFilterProperties()}
                onFinished={e => {
                    setFilterProperties(e);
                    setPage(0)
                    getList(getPage());
                }}
            />

            <div style={{marginBottom: '1rem'}}/>
            <Table bordered dataSource={list} columns={columns} pagination={false}/>
            <CustomPagenation current={getPage()} totalItems={totalElements} onChange={onChangePage}/>
            {creatModalIsOpen && <CategoryModal
                isOpen={creatModalIsOpen}
                handleOk={create}
                handleCancel={() => {
                    setCreatModalIsOpen(false)
                    setCurrent(null)
                }}/>}

            {updateModalIsOpen && <CategoryModal
                isOpen={updateModalIsOpen}
                handleOk={update}
                data={current}
                handleCancel={() => {
                    setUpdateModalIsOpen(false);
                    setCurrent(null)
                }}/>}
        </>);
}

Category.propTypes = {};

export default Category;