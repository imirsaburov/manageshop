import React from 'react';
import {Button, Checkbox, Drawer, Form, Input} from "antd";

function ModeratorChangePasswordModal({isOpen, handleOk, handleCancel}) {
    return (
        <Drawer
            title="Moderator parolni o`zgartirish"
            width={720}
            onClose={handleCancel}
            visible={isOpen}
            bodyStyle={{paddingBottom: 80}}
        >
            <Form layout="vertical" onFinish={handleOk}>
                <Form.Item

                    name="password"
                    label="Parol"
                    rules={[{required: true, message: 'Iltimos parolni kiriting'}]}
                >
                    <Input placeholder="Parolni kiriting"/>
                </Form.Item>

                <Form.Item

                    name="repeatPassword"
                    label="Takroriy parol"
                    rules={[{required: true, message: 'Iltimos parolni kiriting'}]}
                >
                    <Input placeholder="Parolni kiriting"/>
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

export default ModeratorChangePasswordModal;