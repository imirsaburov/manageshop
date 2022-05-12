import React, {Component} from 'react';
import {Button, Col, Form, Input, Row, Select} from "antd";
import {Option} from "antd/es/mentions";

class ProductFilter extends Component {
    onFinish(e, v) {
        console.log(e, v);
    }

    onFinishFailed(e, v) {
        console.log(e, v);
    }

    render() {
        return (
            <Form
                name="productFilter"
                // labelCol={{span: 8}}
                // wrapperCol={{span: 16}}
                initialValues={{remember: true}}
                onFinish={this.onFinish}
                onFinishFailed={this.onFinishFailed}
                autoComplete="off">
                <Row>

                    <Col md={3}>
                        <Form.Item
                            label="Nomi"
                            name="name"
                            // rules={[{required: false, message: 'Please input your username!'}]}
                        >
                            <Input
                                placeholder="Nomni yozing"

                            />
                        </Form.Item></Col>
                    <Col md={3} offset={1}>
                        <Form.Item name="status" label="Status" rules={[{required: false}]}>
                            <Select
                                placeholder="Statusni tanlang"
                                // onChange={onGenderChange}
                                allowClear
                            >
                                <Option value="true">Aktiv</Option>
                                <Option value="false">Aktiv emas</Option>
                            </Select>
                        </Form.Item>
                    </Col>
                    <Col md={3} offset={1}>
                        <Form.Item name="dimension" label="O'lcham" rules={[{required: false}]}>
                            <Select
                                placeholder="O'lchamni tanlang"
                                // onChange={onGenderChange}
                                allowClear
                                mode='multiple'

                            >
                                <Option value="1">S</Option>
                                <Option value="2">L</Option>
                            </Select>
                        </Form.Item>
                    </Col>

                    <Col md={3} offset={1}>
                        <Form.Item name="category" label="Toifa" rules={[{required: false}]}>
                            <Select
                                placeholder="Toifani tanlang"
                                // onChange={onGenderChange}
                                allowClear
                            >
                                <Option value="1">Shim</Option>
                                <Option value="2">Finka</Option>
                            </Select>
                        </Form.Item>
                    </Col>

                    <Col md={3} offset={1}>
                        <Form.Item name="status">
                            <Button type="primary" htmlType="submit">
                                Qidirish
                            </Button>
                        </Form.Item>
                    </Col>
                </Row>

            </Form>
        );
    }
}

ProductFilter.propTypes = {};

export default ProductFilter;