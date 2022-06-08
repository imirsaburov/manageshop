import React from 'react';
import {Button, Col, Form, Input, Row, Select} from "antd";
import {Option} from "antd/es/mentions";

const ProductFilter = ({onFinished, params, categoryList}) => {
    const [form] = Form.useForm();

    return (<Form
        name="productFilter"
        form={form}
        initialValues={params}
        onFinish={onFinished}
        // onFinishFailed={this.onFinishFailed}
        autoComplete="off">
        <Row style={{marginTop: '1rem'}}>

            <Col md={4}>
                <Form.Item
                    label="Nomi"
                    name="name">
                    <Input
                        placeholder="Nomni yozing"

                    />
                </Form.Item></Col>
            <Col md={4} offset={1}>
                <Form.Item
                    label="Min narx"
                    name="minPrice">
                    <Input
                        type="number"
                        placeholder="Min narx yozing"

                    />
                </Form.Item></Col>

            <Col md={4} offset={1}>
                <Form.Item
                    label="Max narx"
                    name="maxPrice">
                    <Input
                        type="number"
                        placeholder="Max narx yozing"

                    />
                </Form.Item></Col>
            <Col md={4} offset={1}>
                <Form.Item name="categoryId" label="Toifa" rules={[{required: false}]}>
                    <Select
                        placeholder="Toifani tanlang"
                        allowClear
                    >
                        {categoryList.map(e=>
                            <Option key={e.id} value={e.id}>{e.name}</Option>
                        )}

                    </Select>
                </Form.Item>
            </Col>

            <Col md={4} offset={1}>
                <Form.Item name="sizeIdList" label="O`lchamlar" rules={[{required: false}]}>
                    <Select
                        placeholder="O`lchamlarni tanlang"
                        mode="multiple"
                        allowClear
                    >
                        <Option value="0">XL</Option>
                        <Option value="1">XXL</Option>
                    </Select>
                </Form.Item>
            </Col>

            <Col md={4}>
                <Form.Item name="status" label="Status" rules={[{required: false}]}>
                    <Select
                        placeholder="Statusni tanlang"
                        allowClear
                    >
                        <Option value="ACTIVE">Aktiv</Option>
                        <Option value="INACTIVE">Aktiv emas</Option>
                    </Select>
                </Form.Item>
            </Col>


            <Col md={3} offset={1}>
                <Button type="primary" htmlType="submit">
                    Qidirish
                </Button>
            </Col>
        </Row>

    </Form>);
}

export default ProductFilter;