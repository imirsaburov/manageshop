import React from 'react';
import {Button, Col, Drawer, Form, Image, Input, Row, Select} from "antd";
import {Option} from "antd/es/mentions";
import {FILE_URL} from "../../Constants";
import {getPhoto, uploadPhoto} from "../../services/FileService";

function ProductModal({isOpen, handleOk, handleCancel, data, categoryList, imageId, setImageId}) {
    return (
        <Drawer
            title="Mahsulot qo`shish"
            width={1000}
            onClose={handleCancel}
            visible={isOpen}
            bodyStyle={{paddingBottom: 80}}
        >
            <Form layout="vertical" onFinish={handleOk}
                  initialValues={data}
            >
                <Row>
                    <Col sm={6}> <Form.Item

                        name="image"
                        label="Rasmi"
                        rules={[{required: imageId == null, message: 'Iltimos rasmni kiriting'}]}
                    >
                        <Input type="file" onChange={(e) => {
                            uploadPhoto(e.target.files[0]).then(e => {
                                console.log(e);
                                if (e.success) {
                                    setImageId(e.data.id)
                                }
                            })
                        }}
                               placeholder="Rasmni Kiriting"/>
                    </Form.Item>
                    </Col>
                    {imageId && <Col sm={6}>
                        <Image src={FILE_URL + imageId}/>
                    </Col>}
                </Row>

                <Form.Item

                    name="title"
                    label="Nomi"
                    rules={[{required: true, message: 'Iltimos nomni kiriting'}]}
                >
                    <Input placeholder="Nomni Kiriting"/>
                </Form.Item>

                <Form.Item name="categoryId" label="Katalog" rules={[{required: true}]}>
                    <Select
                        placeholder="Katalogni tanlang"
                        allowClear
                    >
                        {categoryList.map(e =>
                            <Option value={e.id}>{e.name}</Option>
                        )}
                    </Select>
                </Form.Item>

                <Form.Item

                    name="incomingPrice"
                    label="Kelish narxi"
                    rules={[{required: true, message: 'Iltimos narxni kiriting'}]}
                >
                    <Input type="number" placeholder="Narxni Kiriting"/>
                </Form.Item>

                <Form.Item

                    name="sellPrice"
                    label="Aytilish narxi"
                    rules={[{required: true, message: 'Iltimos narxni kiriting'}]}
                >
                    <Input type="number" placeholder="Narxni Kiriting"/>
                </Form.Item>

                <Form.Item

                    name="minSellPrice"
                    label="Oxirgi sotilish narxi"
                    rules={[{required: true, message: 'Iltimos narxni kiriting'}]}
                >
                    <Input type="number" placeholder="Narxni Kiriting"/>
                </Form.Item>

                <Form.Item name="status" label="Status" rules={[{required: true}]}>
                    <Select
                        placeholder="Statusni tanlang"
                        allowClear
                    >
                        <Option value="ACTIVE">Aktiv</Option>
                        <Option value="INACTIVE">Aktiv emas</Option>
                    </Select>
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

export default ProductModal;