import React from 'react';
import {Button, Col, Drawer, Form, Image, Input, Row, Select} from "antd";
import {Option} from "antd/es/mentions";

function ProductSizeModal({isOpen, handleOk, handleCancel, data, productList, sizeList}) {
    return (
        <Drawer
            title="Mahsulotga o`lcham qo`shish"
            width={700}
            onClose={handleCancel}
            visible={isOpen}
            bodyStyle={{paddingBottom: 80}}
        >
            <Form layout="vertical" onFinish={handleOk}
                  initialValues={data}
            >


                {/*<Form.Item name="productId" label="Mahsulot" rules={[{required: true}]}>*/}
                {/*    <Select*/}
                {/*        placeholder="Mahsulotni tanlang"*/}
                {/*        allowClear*/}
                {/*    >*/}
                {/*        {productList.map(e =>*/}
                {/*            <Option value={e.id}>{e.title}</Option>*/}
                {/*        )}*/}
                {/*    </Select>*/}
                {/*</Form.Item>*/}

                <Form.Item name="sizeId" label="O`lcham" rules={[{required: true}]}>
                    <Select
                        placeholder="O`lchamni tanlang"
                        allowClear
                    >
                        {sizeList.map(e =>
                            <Option value={e.id}>{e.name}</Option>
                        )}
                    </Select>
                </Form.Item>

                <Form.Item

                    name="count"
                    label="Mahsulot soni"
                    rules={[{required: true, message: 'Iltimos Mahsulot sonini kiriting'}]}
                >
                    <Input type="number" placeholder="Narxni  Mahsulot sonini kiriting"/>
                </Form.Item>

                <Form.Item>
                    <Button type="primary" htmlType="submit" className="login-form-button">
                        Saqlash
                    </Button>
                </Form.Item>
            </Form>

        </Drawer>
    );
}

export default ProductSizeModal;