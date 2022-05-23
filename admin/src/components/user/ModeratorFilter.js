import React from 'react';
import {Button, Col, Form, Input, Row, Select} from "antd";
import {Option} from "antd/es/mentions";

const ProductFilter = ({onFinished, params}) => {
    const [form] = Form.useForm();

    return (<Form
        name="productFilter"
        form={form}
        initialValues={params}
        onFinish={onFinished}
        // onFinishFailed={this.onFinishFailed}
        autoComplete="off">
        <Row style={{marginTop: '1rem'}}>

            <Col md={3}>
                <Form.Item
                    label="Qidirish"
                    name="search">
                    <Input
                        placeholder="Ismi, Familyasi, Username ..."

                    />
                </Form.Item></Col>
            <Col md={3} offset={1}>
                <Form.Item name="enabled" label="Status" rules={[{required: false}]}>
                    <Select
                        placeholder="Statusni tanlang"
                        allowClear
                    >
                        <Option value="true">Aktiv</Option>
                        <Option value="false">Aktiv emas</Option>
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