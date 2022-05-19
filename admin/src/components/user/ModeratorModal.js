import React from 'react';
import {Button, Checkbox, Drawer, Form, Input} from "antd";

function ModeratorModal({isOpen, handleOk, handleCancel, data}) {
    return (
        <Drawer
            title="Moderator qo`shish"
            width={720}
            onClose={handleCancel}
            visible={isOpen}
            bodyStyle={{paddingBottom: 80}}
        >
            <Form layout="vertical" onFinish={handleOk}
                  initialValues={{
                      ["lastName"]: data?.lastName || '',
                      ["firstName"]: data?.firstName || '',
                      ["username"]: data?.username || '',
                      ["enabled"]: data?.enabled || ''
                  }}
            >
                <Form.Item

                    name="firstName"
                    label="Ismi"
                    rules={[{required: true, message: 'Iltimos ismni kiriting'}]}
                >
                    <Input placeholder="Ismni kiriting"/>
                </Form.Item>

                <Form.Item

                    name="lastName"
                    label="Familyai"
                    rules={[{required: true, message: 'Iltimos familyani kiriting'}]}
                >
                    <Input placeholder="Familyani kiriting"/>
                </Form.Item>

                <Form.Item

                    name="username"
                    label="Login"
                    rules={[{required: true, message: 'Iltimos Loginni kiriting'}]}
                >
                    <Input placeholder="Login kiriting"/>
                </Form.Item>


                {data == null && <Form.Item

                    name="password"
                    label="Parol"
                    rules={[{required: true, message: 'Iltimos parolni kiriting'}]}
                >
                    <Input placeholder="Parol kiriting"/>
                </Form.Item>

                }

                <Form.Item name="enabled" valuePropName="checked">
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

export default ModeratorModal;