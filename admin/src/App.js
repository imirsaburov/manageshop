import React, {useEffect, useState} from 'react'
import {Layout, Menu, Popconfirm} from 'antd';
import {
    LogoutOutlined,
    SkinOutlined,
    UnorderedListOutlined,
    UserOutlined,
    OrderedListOutlined
} from '@ant-design/icons';
import {Link, Redirect, Route, Switch, useHistory} from "react-router-dom";
import {routes} from "./Routs";
import {userMe} from "./services/AuthService";
import Login from "./Login";

const {Header, Content, Footer, Sider} = Layout;

function getItem(label, key, icon, children) {
    return {
        key,
        icon,
        children,
        label,
    };
}

const items = [
    getItem('Mahsulotlar', '/product', <SkinOutlined/>),
    getItem('Toifalar', '/category', <UnorderedListOutlined/>),
    getItem('O`lchamlar', '/size', <OrderedListOutlined/>),
    getItem('Foydalanuvchilar', '/user', <UserOutlined/>),
];

const SiderDemo = () => {

    const [collapsed, setCollapsed] = useState(false)
    const [authorized, setAuthorized] = useState(false);
    const [currentUser, setCurrentUser] = useState(null);
    const history = useHistory();

    useEffect(() => {
        setAuthorized(false)
    }, [setAuthorized])

    useEffect(async () => {
        const currentUser = await userMe();
        if (currentUser.success) {
            setAuthorized(true);
            setCurrentUser(currentUser.data);
        }
    }, []);

    if (!authorized) {
        // history.push("/login")
        return <Login/>
    }

    return (
        <Layout
            style={{
                minHeight: '100vh',
            }}
        >
            <Sider collapsible collapsed={collapsed} onCollapse={setCollapsed}>
                <div className="logo"/>
                {/*<Menu*/}
                {/*    onClick={(e) =>  {*/}

                {/*        router.push(e.key)*/}
                {/*        this.forceUpdate();*/}
                {/*    }}*/}
                {/*    theme="dark"*/}
                {/*    defaultSelectedKeys={['product']} mode="inline"*/}
                {/*    items={items}/>*/}

                <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                    {items.map((item) =>
                        <Menu.Item key={item.key} icon={item.icon}>
                            <Link to={item.key}>
                                {item.label}
                            </Link>
                        </Menu.Item>)}

                    <Menu.Item key="7" icon={<LogoutOutlined/>}>
                        <Popconfirm placement="topLeft" title={"Saytdan chiqishga istaysizmi?"}
                                    onConfirm={() => {
                                        localStorage.removeItem("access_token");
                                        setAuthorized(false);
                                        setCurrentUser(null);
                                        // history.push("/login");
                                    }} okText="Ha" cancelText="Yo'q">
                            <Link to="/">
                                Chiqish
                            </Link>
                        </Popconfirm>

                    </Menu.Item>
                </Menu>


            </Sider>
            <Layout className="site-layout">

                <Content
                    style={{
                        margin: '2rem 1rem',
                    }}
                >
                    <Switch>
                        <Route path='/' exact render={() => <Redirect to="/product"/>}/>
                        {routes.map(route => {
                            return <Route path={route.path}
                                          render={() => <route.component currentUser={currentUser}/>}
                                          key={route.path}/>
                        })}

                    </Switch>
                </Content>
            </Layout>
        </Layout>
    );

}

export default () => <SiderDemo/>;