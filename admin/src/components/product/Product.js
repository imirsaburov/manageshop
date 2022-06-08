import React, {useEffect, useState} from 'react';
import {Button, Checkbox, Col, Image, message, Popconfirm, Row, Table, Tag} from "antd";
import ProductFilter from "./ProductFilter";
import {
    determineProductStatus,
    filter,
    getJsonFromLocalStorage,
    getStatusProduct,
    moneyFormatUZS,
    setJsonToLocalStorage
} from "../../utils";
import {EditOutlined} from '@ant-design/icons';
import {FILE_URL, PAGE_SIZE} from "../../Constants";
import {changeStatusCategory, createCategory, listCategory, updateCategory} from "../../services/CategoryService";
import CustomPagenation from "../custom/CustomPagenation";
import ProductModal from "./ProductModal";
import {changeStatusProduct, createProduct, listProduct, updateProduct} from "../../services/ProductService";
import ProductSizeModal from "./productSize/ProductSizeModal";
import {listSize} from "../../services/SizeService";
import {ProductSizeComponent} from "./productSize/ProductSizeComponent";
import {createProductSizeService, updateProductSizeService} from "../../services/ProductSizeService";
// import {
//
// } from '@ant-design/icons';


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
        dataIndex: 'title',
        key: 'title',
        width: '15%',
    },

    {
        title: 'Toifa',
        dataIndex: 'category',
        key: 'category',
        width: '8%',
    }, {
        title: 'Kelish narxi',
        dataIndex: 'incomingPrice',
        key: 'incomingPrice',
        width: '8%',
    },
    {
        title: 'Sotish narxi',
        dataIndex: 'sellPrice',
        key: 'sellPrice',
        width: '8%',
    },
    {
        title: 'Min sotish narxi',
        dataIndex: 'minSellPrice',
        key: 'minSellPrice',
        width: '8%',
    }, {
        title: 'Soni',
        dataIndex: 'count',
        key: 'count',
        width: '20%',
        render(text, record) {
            return {
                props: {
                    style: {}
                },
                children: <div>{text}</div>
            };
        },
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
        width: '10%',
    },
];


const Product = ({currentUser}) => {


    const [list, setList] = useState([])
    const [selectProductList, setSelectProductList] = useState([])
    const [selectSizeList, setSelectSizeList] = useState([])
    const [current, setCurrent] = useState({})
    const [currentProductSize, setCurrentProductSize] = useState({})
    const [categoryList, setCategoryList] = useState([])
    const [creatModalIsOpen, setCreatModalIsOpen] = useState(false)
    const [updateModalIsOpen, setUpdateModalIsOpen] = useState(false)
    const [creatProductSizeModalIsOpen, setCreatProductSizeModalIsOpen] = useState(false)
    const [updateProductSizeModalIsOpen, setUpdateProductSizeModalIsOpen] = useState(false)
    const [totalElements, setTotalElements] = useState(0);
    const [imageId, setImageId] = useState(null);

    useEffect(() => {
        getList(getPage())
        getCategory();
    }, [])

    const getList = async (page) => {
        let params = {page: page, ...getFilterProperties()}
        params.size = PAGE_SIZE;
        let res = await listProduct(params);
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
        return getJsonFromLocalStorage("productFilter");
    }

    const setFilterProperties = (obj) => {
        setJsonToLocalStorage("productFilter", filter(obj));
    }

    const getPage = () => {
        return getJsonFromLocalStorage("productPage");
    }

    const setPage = (page) => {
        setJsonToLocalStorage("productPage", page);
    }

    const openEditProductSizeModal = (product, item) => {
        setUpdateProductSizeModalIsOpen(true);
        setCurrent(product);
        setCurrentProductSize(item);
        getSizeListForSelect();
    }

    const getItem = (item) => {
        return {
            key: item.id,
            id: item.id,
            image: <Image src={FILE_URL + item.imageId}/>,
            title: item.title,
            category: item.categoryName,
            categoryId: item.categoryId,
            sellPrice: item.sellPrice,
            minSellPrice: item.minSellPrice,
            incomingPrice: item.incomingPrice,
            count: <ProductSizeComponent product={item} editOpenFunction={openEditProductSizeModal} editFunction={updateProductSize}
                                         itemList={item.productSizeList}/>,
            status: getStatusProduct(item.status),
            action: <>
                {/*<Button.Group size={"small"}>*/}
                <Popconfirm placement="topLeft" title={"Statusni o`zgartirmoqchimisiz ?"}
                            onConfirm={async () => {
                                let res = await changeStatusProduct(item.id, {status: determineProductStatus(item.status)});
                                if (res.success) {
                                    message.info("Status o`zgartirildi")
                                    getList(getPage())
                                } else message.error(res.data.message)
                            }} okText="Ha" cancelText="Yo'q">
                    <Button size={"small"}>Statusni o`zgartrish</Button>
                </Popconfirm>


                <Button size={"small"} onClick={() => {
                    setCurrent(item);
                    setImageId(item.imageId)
                    setUpdateModalIsOpen(true)
                }
                }>Tahrirlash</Button>

                <Button size={"small"} onClick={() => {
                    setCurrent(item);
                    getSizeListForSelect();
                    setCreatProductSizeModalIsOpen(true)
                }
                }>O`lcham qo`shish</Button>
                {/*</Button.Group>*/}

            </>

        }
    }

    const create = async (e) => {
        e.imageId = imageId;
        let res = await createProduct(e);
        if (res.success) {
            getList(getPage())
            message.info("Yaratildi");
            setImageId(null)
            setCreatModalIsOpen(false);
            setCurrent({})
        } else message.error(res.data.message);
    }

    const update = async (e) => {
        e.id = current.id;
        e.imageId = imageId;
        let res = await updateProduct(e);
        if (res.success) {
            getList(getPage())
            message.info("Taxrirlandi!");
            setImageId(null)
            setUpdateModalIsOpen(false);
            setCurrent({})
        } else message.error(res.data.message);
    }

    const createProductSize = async (e) => {
        e.productId = current.id;
        let res = await createProductSizeService(e);
        if (res.success) {
            getList(getPage())
            message.info("Yaratildi");
            setCreatProductSizeModalIsOpen(false);
            setCurrent({})
        } else message.error(res.data.message);
    }

    const updateProductSize = async (e) => {
        e.productId = current.id;
        e.id = currentProductSize.id;
        let res = await updateProductSizeService(e);
        if (res.success) {
            getList(getPage())
            message.info("Tahrirlandi");
            setUpdateProductSizeModalIsOpen(false);
            setCurrent({})
            setCurrentProductSize({})
        } else message.error(res.data.message);
    }

    const getCategory = () => {
        listCategory({status: true, page: 0, size: 1000}).then(res => {
            if (res.success) {
                console.log(res);
                let list = res.data.content
                setCategoryList(list);
            }
        });
    }

    const getSizeListForSelect = () => {
        listSize({page: 0, size: 10000, status: true}).then(e => {
            if (e.success) {
                setSelectSizeList(e.data.content);
            }
        })
    }

    return (
        <>
            <Row>
                <Col md={1}>
                    <Button onClick={() => {
                        setCreatModalIsOpen(true);
                        getCategory();
                        setImageId(null)
                    }} type="primary">Qo'shish</Button>
                </Col>


            </Row>
            <ProductFilter categoryList={categoryList} params={getFilterProperties()}
                           onFinished={e => {
                               setFilterProperties(e);
                               setPage(0)
                               getList(getPage());
                           }}/>
            <Table bordered pagination={false} dataSource={list} columns={columns}/>
            <CustomPagenation current={getPage()} totalItems={totalElements} onChange={onChangePage}/>
            {creatModalIsOpen && <ProductModal
                isOpen={creatModalIsOpen}
                categoryList={categoryList}
                setImageId={setImageId}
                imageId={imageId}
                handleOk={create}
                handleCancel={() => {
                    setCreatModalIsOpen(false)
                    setCurrent(null)
                }}/>}

            {updateModalIsOpen && <ProductModal
                isOpen={updateModalIsOpen}
                categoryList={categoryList}
                setImageId={setImageId}
                imageId={imageId}
                handleOk={update}
                data={current}
                handleCancel={() => {
                    setUpdateModalIsOpen(false);
                    setCurrent(null)
                }}/>}

            {creatProductSizeModalIsOpen && <ProductSizeModal
                isOpen={creatProductSizeModalIsOpen}
                // productList={selectProductList}
                sizeList={selectSizeList}
                handleOk={createProductSize}
                handleCancel={() => {
                    setCreatProductSizeModalIsOpen(false)
                    setCurrentProductSize({})
                    setCurrent({})
                }}/>}

            {updateProductSizeModalIsOpen && <ProductSizeModal
                isOpen={updateProductSizeModalIsOpen}
                // productList={selectProductList}
                sizeList={selectSizeList}
                data={currentProductSize}
                handleOk={updateProductSize}
                handleCancel={() => {
                    setUpdateProductSizeModalIsOpen(false)
                    setCurrentProductSize({})
                    setCurrent({})
                }}/>}

        </>
    );

}


export default Product;