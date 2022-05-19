import React from 'react';
import {Button, Form, Input, message} from "antd";
import {login} from "./services/AuthService";
import {useHistory} from "react-router-dom";


const Login = () => {
    const history = useHistory();

    const onFinish = async values => {
        const loginRes = await login(values);
        if (loginRes.success) {
            history.push("/product")
            window.location.reload();
        }else message.error("Parol noto`g`ri")
    };

    const onFinishFailed = errorInfo => {
        console.log('Failed:', errorInfo);
    };

    return (
        <div className="login-page">
            <div className="login-box">
                <div className="illustration-wrapper">
                    <img
                        // src="https://mixkit.imgix.net/art/preview/mixkit-left-handed-man-sitting-at-a-table-writing-in-a-notebook-27-original-large.png?q=80&auto=format%2Ccompress&h=700"
                        src="https://img.freepik.com/free-photo/fashion-polo-shirt-men_74190-4858.jpg?t=st=1652337442~exp=1652338042~hmac=4034f47e0bed9128daf379a87faf9ffb338bdf21748e2109b00e374cc1d16c13&w=1380"
                        // src="https://as2.ftcdn.net/v2/jpg/04/16/21/45/1000_F_416214587_BnUWgH7Px7gIgiU3ZahaCPJ0qe9ymRun.jpg"
                        // src="https://as1.ftcdn.net/v2/jpg/03/34/79/68/1000_F_334796865_VVTjg49nbLgQPG6rgKDjVqSb5XUhBVsW.jpg"
                        // src="https://as1.ftcdn.net/v2/jpg/02/17/73/36/1000_F_217733615_JxYHefgz0JBcFqDDUTeKoLfB4KnQl1Nv.jpg"
                        alt="Login"/>
                </div>
                <Form
                    name="login-form"
                    initialValues={{remember: true}}
                    onFinish={onFinish}
                    onFinishFailed={onFinishFailed}
                >
                    <p className="form-title">Oscar shop</p>
                    <p>Admin panelga kirish</p>
                    <Form.Item
                        name="username"
                        rules={[{required: true, message: 'Please input your username!'}]}
                    >
                        <Input
                            placeholder="Username"
                        />
                    </Form.Item>

                    <Form.Item
                        name="password"
                        rules={[{required: true, message: 'Please input your password!'}]}
                    >
                        <Input.Password
                            placeholder="Password"
                        />
                    </Form.Item>

                    <Form.Item>
                        <Button type="primary" htmlType="submit" className="login-form-button">
                            LOGIN
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        </div>
    );
};
export default Login;