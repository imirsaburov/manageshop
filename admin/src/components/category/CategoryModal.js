import React from 'react';
import {Button, Checkbox, Drawer, Form, Input} from "antd";

function CategoryModal({isOpen, handleOk, handleCancel, data}) {
    return (
        <Drawer
            title="Toifa qo`shish"
            width={720}
            onClose={handleCancel}
            visible={isOpen}
            bodyStyle={{paddingBottom: 80}}
        >
            <Form layout="vertical" onFinish={handleOk}
                  initialValues={{
                      ["name"]: data?.name || '',
                      ["status"]: data?.status || ''
                  }}
            >
                <Form.Item

                    name="name"
                    label="Name"
                    rules={[{required: true, message: 'Please enter user name'}]}
                >
                    <Input placeholder="Nomni Kiriting"/>
                </Form.Item>

                <Form.Item name="status" valuePropName="checked">
                    <Checkbox>Status</Checkbox>
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

export default CategoryModal;